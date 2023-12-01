package jlfowler.com.thebooky;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class starting_page extends AppCompatActivity {

    boolean intro_decision = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starting_page);

        loadData(); //Loads all the data for the application to run

        int delayMillis = 2500; // Delay amount before running the handler
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent;

                if (intro_decision){ //decision for introduction page or into your personal page
                    intent = new Intent(starting_page.this, intro_page.class);
                }else{
                    intent = new Intent(starting_page.this, user_main_page.class);
                }


                startActivity(intent); // Sets intent to Send user to the user main page from this page and then starts the intent
                finish();//So user can not come back to this page

            }
        }, delayMillis);


    }


    private void loadData() {

        SharedPreferences sharedPreferences = getSharedPreferences("Booky_Application_Prefs", MODE_PRIVATE); //Checks for specific preference file


        Gson gson = new Gson();
        String json = null;

        json = sharedPreferences.getString("businesses", null); // Checks file for string thats associated with the key businesses, to retrieve the Businesses arraylist


        if (json==null){
            data_handling.loadBusinesses();
            Log.i("Starting_Page_Status","No Prefs Present in json, proceeding to load new Businesses");
        }else{
            Type type = new TypeToken<ArrayList<Business>>() {
            }.getType();
            ArrayList<Business> businessArrayList = gson.fromJson(json, type);

            Log.i("Starting_Page_Status", "About to load Businesses");

            BusinessManager.appendBusiness(businessArrayList);
            Log.i("Starting_Page_Status", "Businesses successfully loaded");
        }

        json = sharedPreferences.getString("appointments", null); // Checks file for string thats associated with the key appointments, to retrieve the appointments arraylist for the user



        if (json==null){
            data_handling.loadAppointments(); // Loads user appointments from program if none are present in user preferences file
            Log.i("Starting_Page_Status Status","No Prefs Present, loading pre-loaded appointments");
        }else{
            Type type = new TypeToken<ArrayList<User_Appointment>>() {
            }.getType();
            ArrayList<User_Appointment> appointmentArrayList = gson.fromJson(json, type);

            Log.i("Starting_Page_Status", "About to load appointments");

            BusinessManager.appendUserAppointmnets(appointmentArrayList);
            Log.i("Starting_Page_Status", "Appointments successfully loaded");
        }

        json = sharedPreferences.getString("intro_decision", null); //

        //setting decision based on preferences
        if (json==null){
             intro_decision = true;
        }else{
            intro_decision = false;
        }

    }
}
