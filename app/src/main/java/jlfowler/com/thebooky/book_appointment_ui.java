package jlfowler.com.thebooky;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
import java.util.Calendar;

public class book_appointment_ui extends AppCompatActivity {

    EditText customer_name_edittext,email_edittext,phone_edittext,services_edittext,date_edittext,time_edittext;
    Button submit_button,date_picking_button,time_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_appointment_ui);


        int position = getIntent().getIntExtra("index",0);
        Log.i("Book_Appointment_Status","The position passed is " + position);

        customer_name_edittext= findViewById(R.id.booking_name_edittext);
        email_edittext= findViewById(R.id.booking_email_edittext);
        phone_edittext= findViewById(R.id.booking_phone_edittext);
        services_edittext= findViewById(R.id.booking_services_edittext);
        date_edittext= findViewById(R.id.booking_date_edittext);
        date_picking_button=findViewById(R.id.booking_date_picking_button);
        time_edittext= findViewById(R.id.booking_time_edittext);
        time_button = findViewById(R.id.booking_time_picking_button);
        submit_button = findViewById(R.id.booking_submit_button);

        date_edittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {showDatePickerDialog();}
        });
        date_picking_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        time_edittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });
        time_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {showTimePickerDialog();}
        });


        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean isValid = true;
                isValid = isValidInput(customer_name_edittext) && isValid;
                isValid = isValidInput(email_edittext) && isValid;
                isValid = isValidInput(phone_edittext) && isValid;
                isValid = isValidInput(services_edittext) && isValid;
                isValid = isValidInput(date_edittext) && isValid;
                isValid = isValidInput(time_edittext) && isValid;


                if (isValid) {

                    String customer_name = customer_name_edittext.getText().toString().trim();
                    String customer_email = email_edittext.getText().toString().trim();
                    String customer_phone = phone_edittext.getText().toString().trim();
                    String customer_services = services_edittext.getText().toString().trim();
                    String customer_date = date_edittext.getText().toString().trim();
                    String customer_time = time_edittext.getText().toString().trim();

                    Appointment new_appointment = new Appointment(customer_name,customer_services,customer_phone,customer_email,customer_date,customer_time);
                    User_Appointment new_user_appointment = new User_Appointment(
                            BusinessManager.getBusinesses().get(position).getBusinessName(),
                            BusinessManager.getBusinesses().get(position).getAddress(),
                            BusinessManager.getBusinesses().get(position).getPhoneNumber(),
                            BusinessManager.getBusinesses().get(position).getEmail(),
                            customer_email,
                            customer_date,
                            customer_time,
                            customer_services);
                    BusinessManager.addAppointmentToUser(new_user_appointment);
                    BusinessManager.addAppointmentToBusiness(position,new_appointment);

                    Log.i("Book_Appointment_Status","Appointment for: " + customer_name + "  has been made for the business: " + BusinessManager.getBusinesses().get(position).getBusinessName());

                    Intent intent = new Intent(jlfowler.com.thebooky.book_appointment_ui.this, user_main_page.class);
                    showCustomToast("Appointment at: " + BusinessManager.getBusinesses().get(position).getBusinessName() + "  has been made");
                    startActivity(intent);
                }else {
                    showCustomToast("Fix something!");
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
            case "Book_Appointment_Page_Users_Page":
                destination = user_main_page.class;
                break;
            case "Book_Appointment_Page_Book_Appointment_Page":
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

        Log.i("Book_Appointment_Status","Saved all data to BookyPrefs file");
    }

    private void showDatePickerDialog() {
        // Get the current date
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Update the EditText with the selected date
                        String selectedDate = (month + 1) + "/" + dayOfMonth + "/" + year;
                        date_edittext.setText(selectedDate);
                    }
                },
                year,
                month,
                dayOfMonth
        );

        // Show the DatePickerDialog
        datePickerDialog.show();
    }
    private void showTimePickerDialog() {
        // Get the current time
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        // Create a TimePickerDialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // Update the EditText with the selected time
                        String selectedTime = convertTo12HourFormat(hourOfDay,minute);
                        time_edittext.setText(selectedTime);
                    }
                },
                hour,
                minute,
                false // Set to true if you want 24-hour format
        );

        // Show the TimePickerDialog
        timePickerDialog.show();
    }
    private String convertTo12HourFormat(int hourOfDay, int minute) {
        String period;

        if (hourOfDay >= 12) {
            period = "PM";
            if (hourOfDay > 12) {
                hourOfDay -= 12;
            }
        } else {
            period = "AM";
            if (hourOfDay == 0) {
                hourOfDay = 12;
            }
        }

        return String.format("%02d:%02d %s", hourOfDay, minute, period);
    }

    private boolean isValidInput(EditText editText) {
        String userInput = editText.getText().toString().trim();

        if (TextUtils.isEmpty(userInput)) {
            // If the input is empty, set an error on the EditText
            editText.setError("Input can't be empty");
            return false;
        } else {
            // Clear any previous errors
            editText.setError(null);

            // TODO: Add additional validation logic if needed

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