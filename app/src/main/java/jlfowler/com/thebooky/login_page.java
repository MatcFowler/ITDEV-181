package jlfowler.com.thebooky;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class login_page extends AppCompatActivity {

    TextView vault_title_textview;
    EditText vault_passcode_edittext,vault_username_edittext;
    Button vault_submit_button;
    int userInputPasscode = 0,businessIndex = -1;
    String inputted_passcode,inputted_username;
    boolean userCredentialsMatch = false;
    ArrayList<Business> businesses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        vault_title_textview = findViewById(R.id.vault_title_textview);
        vault_passcode_edittext = findViewById(R.id.vault_passcode_edittext);
        vault_username_edittext = findViewById(R.id.vault_username_edittext);
        vault_submit_button = findViewById(R.id.vault_submit_button);

        vault_submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isValid = true;

                isValid = isValidInput(vault_username_edittext) && isValid;
                isValid = isValidInput(vault_passcode_edittext) && isValid;


                if (isValid) {

                    inputted_passcode = vault_passcode_edittext.getText().toString();
                    inputted_username = vault_username_edittext.getText().toString();

                    //passes string version of int, to integer version of int
                    userInputPasscode = Integer.parseInt(inputted_passcode);

                    try {
                        businesses = BusinessManager.getBusinesses();
                        Log.i("Login_Page_Status","Got businessess");

                    } catch (IndexOutOfBoundsException e) {
                        Log.i("Login_Page_Status","No Busnesses?");
                    }

                    for (int i = 0; i < businesses.size(); i++) {

                        Business business = businesses.get(i);

                        int passcode = business.getPasscode();
                        String username = business.getUser_name();

                        Log.i("Business UserName",username);
                        Log.i("Business Passcode",Integer.toString(passcode));

                        Log.i("User Input Username", inputted_username);
                        Log.i("User Input Passcode: ", String.valueOf(userInputPasscode));

                        if (userInputPasscode == passcode && inputted_username.equals(username)) {
                            userCredentialsMatch = true;
                            businessIndex = i; // Store the index where the match was found
                            Log.i("Bussiness located: ", Integer.toString(i));
                            break;
                        }
                    }

                    if (userCredentialsMatch) {
                        Log.i("Login_Page_Status","Attempting to enter");
                        try {
                            ArrayList<Appointment> appointmentArrayList = BusinessManager.getBusinesses().get(businessIndex).getAppointments();

                            Intent intent = new Intent(login_page.this, business_appointments_page.class);
                            intent.putExtra("passcode", businessIndex);
                            Log.i("information being sent " ,Integer.toString(businessIndex));
                            showCustomToast("Welcome to Your Business Page!");
                            startActivity(intent);
                            finish();

                        } catch (IndexOutOfBoundsException e) {

                            showCustomToast("Sorry no appointments yet! Come back later");
                            Intent intent = new Intent(login_page.this, user_main_page.class);
                            return;
                        }
                        showCustomToast("Credentials Valid");
                    } else {
                        showCustomToast("Credentials Invalid");
                        Log.i("","Username: " + inputted_username+"  Passcode: " +inputted_passcode);
                    }
                }

                }
        });



        // Nav bar initialization
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

    //Method for navigation bar control
    public void navBarOperations(View view) {
        String tag = view.getTag().toString();
        Class<?> destination;

        switch (tag) {
            case "Login_Page_Users_Page":
                destination = user_main_page.class;
                break;
            case "Login_Page_Book_Appointment_Page":
                destination = search_page.class;
                break;
            default:
                destination = business_needs_page.class;
                break;
        }

        Intent intent = new Intent(this, destination);
        startActivity(intent);
    }

    //Method for checking if edittext is empty or passable
    private boolean isValidInput(EditText editText) {
        String userInput = editText.getText().toString().trim();

        if (TextUtils.isEmpty(userInput)) {
            editText.setError("Input can't be empty");
            return false;
        } else {
            editText.setError(null);

            return true;
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