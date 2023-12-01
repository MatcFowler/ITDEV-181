package jlfowler.com.thebooky;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.gson.Gson;

import java.util.ArrayList;


public class add_business_page extends AppCompatActivity {

    Button add_business_button;

    EditText business_name_edittext,owner_name_edittext,address_edittext,email_edittext,phone_edittext,username_edittext,code_edittext,check_code_edittext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_business_page);

        business_name_edittext= findViewById(R.id.booking_name_edittext);
        owner_name_edittext= findViewById(R.id.owner_name_edittext);
        address_edittext= findViewById(R.id.address_edittext);
        email_edittext= findViewById(R.id.booking_email_edittext);
        phone_edittext= findViewById(R.id.booking_phone_edittext);
        code_edittext= findViewById(R.id.code_edittext);
        check_code_edittext= findViewById(R.id.check_code_edittext);
        username_edittext=findViewById(R.id.username_edittext);

        add_business_button = findViewById(R.id.booking_submit_button);


        add_business_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isValid = true;

                isValid = isValidInput(business_name_edittext);
                isValid = isValidInput(owner_name_edittext) && isValid;
                isValid = isValidInput(address_edittext) && isValid;
                isValid = isValidInput(email_edittext) && isValid;
                isValid = isValidInput(phone_edittext) && isValid;
                isValid = isValidInput(code_edittext) && isValid;
                isValid = isValidInput(check_code_edittext) && isValid;
                isValid = isValidInput(username_edittext) && isValid;


                if (isValid) {

                    String business_name = business_name_edittext.getText().toString().trim();
                    String owner_name = owner_name_edittext.getText().toString().trim();
                    String address = address_edittext.getText().toString().trim();
                    String email = email_edittext.getText().toString().trim();
                    String phone = phone_edittext.getText().toString().trim();
                    String username = username_edittext.getText().toString().trim();
                    int code = Integer.parseInt(code_edittext.getText().toString().trim());
                    int check_code = Integer.parseInt(check_code_edittext.getText().toString().trim());

                    if (isValidNumberLength(code) && isValidNumberLength(check_code)){
                        if(isMatchingCode(code,check_code)){
                            if (isValidUsername(username_edittext)){
                                Business new_business = new Business(business_name,owner_name,address,email,phone,username,code);
                                BusinessManager.addBusiness(new_business);

                                Intent intent = new Intent(add_business_page.this, user_main_page.class);
                                startActivity(intent);
                                showCustomToast("Business Successfully made");
                                finish();
                            }else {
                                username_edittext.setError("Username is taken");
                            }
                        }
                    }else{
                        code_edittext.setError("Code is not 4 - 8 characters");
                        check_code_edittext.setError("Code is not 4 - 8 characters");
                    }

                }
            }
        });


        ImageView personal_page_imageview = findViewById(R.id.search_page_personal_page_nav_imageview);
        ImageView book_appointment_imageview = findViewById(R.id.add_businessl_page_nav_imageview);
        ImageView business_needs_imageview = findViewById(R.id.business_needs_page_nav_imageview);

        personal_page_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navBarOperations(v);
            }
        });
        book_appointment_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navBarOperations(v);
            }
        });
        business_needs_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navBarOperations(v);
            }
        });


    }
    public void navBarOperations(View view) {
        String tag = view.getTag().toString();
        Class<?> destination;

        switch (tag) {
            case "Add_Business_Page_Users_Page":
                destination = user_main_page.class;
                break;
            case "Add_Business_Page_Book_Appointment_Page":
                destination = search_page.class;
                break;
            default:
                destination = business_needs_page.class;
                break;
        }

        Intent intent = new Intent(this, destination);
        startActivity(intent);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveData();
    }

    private void saveData() {

        ArrayList<Business> businessArrayList = BusinessManager.getBusinesses();
        ArrayList<User_Appointment> appointmentList = BusinessManager.getUserAppointments();

        SharedPreferences sharedPreferences = getSharedPreferences("BookyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(businessArrayList);
        String ason = gson.toJson(appointmentList);

        editor.putString("businesses", json);
        editor.putString("appointments", ason);
        editor.apply();


        Log.i("Add_Business_Status","Saved all data to BookyPrefs file");
    }
    private boolean isValidInput(EditText editText) {
        String userInput = editText.getText().toString().trim();

        if (TextUtils.isEmpty(userInput)) {

            editText.setError("Input can't be Like This");
            return false;
        } else {

            editText.setError(null);

            return true;
        }
    }
    private boolean isValidNumberLength(int number) {

        int length = String.valueOf(number).length();

        return length >= 4 && length <= 8;
    }

    private boolean isValidUsername(EditText editText) {
        String username = editText.getText().toString().trim();
        Log.i("Add_Business_Status", username);
        Boolean valid = false;

        for (int i = 0; i < BusinessManager.getBusinesses().size(); i++) {

            Business business = BusinessManager.getBusinesses().get(i);

            String temp_username = business.getUser_name();
            Log.i("Add_Business_Status", temp_username);

            if (temp_username.equals(username)) {
                Log.i("Add_Business_Status", "Same username found at" + BusinessManager.getBusinesses().get(i).getBusinessName());
                valid = false;
                break;
            } else {
                valid = true;
            }
        }
        if (valid.equals(true)){
            return true;
        }else {
            return false;
        }
    }
    private boolean isMatchingCode(Integer code, Integer check_code) {

        if (code.equals(check_code)) {

            return true;
        } else {

            code_edittext.setError("Codes Dont Match");
            check_code_edittext.setError("Codes Dont Match");

            return false;
        }
    }

    private void showCustomToast(String toast_text) {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.custom_toast_model,
                (ViewGroup) findViewById(R.id.custom_toast_layout));
        TextView text = layout.findViewById(R.id.custom_toast_text);
        text.setText(toast_text);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 200);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}
