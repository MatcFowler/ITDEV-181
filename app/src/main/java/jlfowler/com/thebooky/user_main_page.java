package jlfowler.com.thebooky;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class user_main_page extends AppCompatActivity {

    Button user_appointments_book_button;
    ListView user_appointments_listview;
    ImageView personal_page_imageview,book_appointment_imageview,business_needs_imageview;
    ArrayList<User_Appointment> user_appointments_arraylist;
    User_Appointment_listview_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_appointment_page);

        saveData();

        user_appointments_book_button = findViewById(R.id.user_appointment_book_button);
        user_appointments_listview = findViewById(R.id.user_appointment_listview);
        personal_page_imageview = findViewById(R.id.search_page_personal_page_nav_imageview);
        book_appointment_imageview = findViewById(R.id.add_businessl_page_nav_imageview);
        business_needs_imageview = findViewById(R.id.business_needs_page_nav_imageview);

        user_appointments_arraylist = BusinessManager.getUserAppointments();

        DataManipulator.sortUserAppointmentsByDate(user_appointments_arraylist);

        updateList();


        user_appointments_book_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(user_main_page.this, jlfowler.com.thebooky.search_page.class);
                startActivity(intent);
            }
        });

        user_appointments_listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("User_Appointment_Status","Item being removed at position: " + position);
                showCustomToast("Appointment for " + BusinessManager.getUserAppointments().get(position).getBusiness_name() +" has been removed");
                BusinessManager.getUserAppointments().remove(position);
                updateList();
                saveData();

                return false;
            }
        });

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

    public void updateList(){
        adapter = new User_Appointment_listview_adapter(this,user_appointments_arraylist);
        user_appointments_listview.setAdapter(adapter);
    }

    public void navBarOperations(View view) {
        String tag = view.getTag().toString();
        Class<?> destination;

        switch (tag) {
            case "User_Page_Users_Page":
                destination = user_main_page.class;
                break;
            case "User_Page_Book_Appointment_Page":
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

        SharedPreferences sharedPreferences = getSharedPreferences("Booky_Application_Prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        Gson gson = new Gson();
        String json = gson.toJson(businessArrayList);
        String ason = gson.toJson(appointmentList);

        editor.putString("businesses", json);
        editor.putString("appointments", ason);
        editor.apply();

        Log.i("User_Appointment_Status","Saved all data to Booky_Application_Prefs file");
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