package jlfowler.com.thebooky;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class business_appointments_page extends AppCompatActivity {

    Button business_appointments_name_sort_button,business_appointments_date_sort_button;
    ListView appointments_listview;
    ArrayList<Appointment> business_appointments_arraylist;
    Appointment_listview_adapter adapter;
    int spot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.appointments_page);

        spot = getIntent().getIntExtra("passcode", -1);


        business_appointments_name_sort_button = findViewById(R.id.business_appointments_name_sort_button);
        business_appointments_date_sort_button = findViewById(R.id.business_appointments_date_sort_button);
        appointments_listview = findViewById(R.id.business_appointment_listview);

        DataManipulator.sortAppointmentsByDate(business_appointments_arraylist = BusinessManager.getBusinesses().get(spot).getAppointments());




        updateList();


        business_appointments_name_sort_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManipulator.sortAppointmentsByName(business_appointments_arraylist);
                showCustomToast("Appointments Sorted By Name");
                updateList();
            }
        });

        business_appointments_date_sort_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManipulator.sortAppointmentsByDate(business_appointments_arraylist);
                showCustomToast("Appointments Sorted By Date");
                updateList();
            }
        });


        appointments_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+ BusinessManager.getBusinesses().get(spot).getAppointments().get(position).getPhoneNumber()));
                startActivity(intent);
            }
        });


        appointments_listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                showCustomToast("Appointment Removed");
                BusinessManager.removeAppointmentFromBusiness(spot,position);
                updateList();
                Log.i("Business_Appointments_Page",Integer.toString(spot));
                Log.i("Business_Appointments_Page", Integer.toString(position));
                return true;

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
            case "Business_Appointments_Page_Users_Page":
                destination = user_main_page.class;
                break;
            case "Business_Appointments_Page_Book_Appointment_Page":
                destination = search_page.class;
                break;
            default:
                destination = business_needs_page.class;
                break;
        }

        Intent intent = new Intent(this, destination);
        startActivity(intent);
    }


    public void updateList(){

        adapter = new Appointment_listview_adapter(this,business_appointments_arraylist);
        appointments_listview.setAdapter(adapter);
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