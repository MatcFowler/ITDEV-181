package jlfowler.com.thebooky;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.gson.Gson;

import java.util.ArrayList;

public class intro_page extends AppCompatActivity {

    Button intro_page_enter_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_page);

        intro_page_enter_button = findViewById(R.id.intro_page_enter_button);


        intro_page_enter_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveEnter();
                Intent intent = new Intent(intro_page.this, user_main_page.class);
                startActivity(intent); // Sets intent to Send user to the user main page from this page and then starts the intent
                finish();//So user can not come back to this page
            }
        });


    }

    public void saveEnter(){

        SharedPreferences sharedPreferences = getSharedPreferences("Booky_Application_Prefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        String decision = "true";
        Gson gson = new Gson();
        String json = gson.toJson(decision);

        editor.putString("intro_decision", json);
        editor.apply();

        Log.i("User_Appointment_Status","Saved all data to Booky_Application_Prefs file");
    }
}