package jlfowler.com.thebooky;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.*;

public class data_handling extends AppCompatActivity {
    //Pre-Load Busniesses method
    public static void loadBusinesses(){
        Log.i("Loading","Loading is about to happen");
        int position = 0;


        BusinessManager.addBusiness( new Business("Gigis CupCakes",
                "Gigi Zembles",
                "414-836-1009",
                "GigiZ@gmail.com",
                "10092 North Gugu Drive",
                "Gigi1029",
                12345678));

        BusinessManager.addAppointmentToBusiness(position, new  Appointment("Daniel","Lessons","414-143-0214","Daniel@hun.com","12/8/2023","10:00AM"));
        BusinessManager.addAppointmentToBusiness(position, new Appointment("Jugh","Lessons","414-945-0223","Jugh@hun.com","12/14/2023","10:00AM"));
        BusinessManager.addAppointmentToBusiness(position, new  Appointment("Daniel","Lessons","414-443-0276","Daniel@hun.com","12/10/2023","10:00AM"));
        BusinessManager.addAppointmentToBusiness(position, new Appointment("Creg","Lessons","414-941-0655","Creg@hun.com","12/8/2023","10:00AM"));
        BusinessManager.addAppointmentToBusiness(position, new  Appointment("Fange","Lessons","414-913-0435","Fange@hun.com","12/14/2023","10:00AM"));
        BusinessManager.addAppointmentToBusiness(position, new Appointment("Gutty","Lessons","414-513-8715","Gutty@hun.com","12/10/2023","10:00AM"));
        BusinessManager.addAppointmentToBusiness(position, new  Appointment("Stan","Lessons","414-723-6515","Stan@hun.com","12/8/2023","10:00AM"));
        BusinessManager.addAppointmentToBusiness(position, new Appointment("Cooper","Lessons","414-713-4315","Cooper@hun.com","12/8/2023","10:00AM"));
        position++;


        BusinessManager.addBusiness( new Business("Elvas Nails",
                "Elva Gen",
                "414-943-0215",
                "ElvaG@gmail.com",
                "South Jorgin Street",
                "Elva1028",
                90127834));

        BusinessManager.addAppointmentToBusiness(position, new  Appointment("Shurel","Lessons","414-512-6227","Shurel@hun.com","12/8/2023","10:00AM"));
        BusinessManager.addAppointmentToBusiness(position, new Appointment("John","Lessons","414-427-6465","John@hun.com","12/10/2023","10:00AM"));
        BusinessManager.addAppointmentToBusiness(position, new  Appointment("Eli","Lessons","414-321-6546","Eli@hun.com","12/10/2023","10:00AM"));
        BusinessManager.addAppointmentToBusiness(position, new Appointment("Leo","Lessons","414-327-4333","Leo@hun.com","12/8/2023","10:00AM"));
        BusinessManager.addAppointmentToBusiness(position, new  Appointment("Frank","Lessons","624-234-7144","Frank@hun.com","12/8/2023","10:00AM"));
        BusinessManager.addAppointmentToBusiness(position, new Appointment("Juuugbe","Lessons","724-234-1321","Juuugbe@hun.com","12345678","10:00AM"));
        BusinessManager.addAppointmentToBusiness(position, new  Appointment("Jienice","Lessons","884-234-1766","Jienice@hun.com","12/8/2023","10:00AM"));
        BusinessManager.addAppointmentToBusiness(position, new Appointment("Jenny","Lessons","534-345-0215","Jenny@hun.com","12/14/2023","10:00AM"));
        position++;

        BusinessManager.addBusiness( new Business("Jorges Handyman Services",
                "Jorge Jussn",
                "414-928-7676",
                "jorgeJ@gmail.com",
                "2177 West Elm Drive",
                "Jorge1028",
                54321098));

        BusinessManager.addAppointmentToBusiness(position, new Appointment("Victor","Lessons","414-144-8672","Victor@hun.com","12/8/2023","10:00AM"));
        BusinessManager.addAppointmentToBusiness(position, new Appointment("Vic","Lessons","414-613-7442","Vic@hun.com","12/10/2023","10:00AM"));
        BusinessManager.addAppointmentToBusiness(position, new  Appointment("Courd","Lessons","414-123-8653","Courd@hun.com","12/8/2023","10:00AM"));
        BusinessManager.addAppointmentToBusiness(position, new Appointment("Steve","Lessons","414-183-6654","Steve@hun.com","12345678","10:00AM"));
        BusinessManager.addAppointmentToBusiness(position, new  Appointment("Eleven","Lessons","414-123-3454","Eleven@hun.com","12/8/2023","10:00AM"));
        BusinessManager.addAppointmentToBusiness(position, new Appointment("Jack","Lessons","414-144-2488","Jack@hun.com","12/8/2023","10:00AM"));
        BusinessManager.addAppointmentToBusiness(position, new  Appointment("Dugg","Lessons","414-156-6542","Dugg@hun.com","12/10/2023","10:00AM"));
        BusinessManager.addAppointmentToBusiness(position, new Appointment("Fran","Lessons","414-666-1236","Fran@hun.com","12/14/2023","10:00AM"));
        position++;

        BusinessManager.addBusiness( new Business("Randys Lawn Care",
                "Randy Sulano",
                "414-286-3018",
                "RandyS@gmail.com",
                "10298 East Concord Drive",
                "Randy1029",
                76859403));

        BusinessManager.addAppointmentToBusiness(position, new  Appointment("Huggo","Lessons","414-321-0215","Huggo@hun.com","12/8/2023","10:00AM"));
        BusinessManager.addAppointmentToBusiness(position, new Appointment("Bob","Lessons","414-543-3248","Bob@hun.com","12/12/2023","10:00AM"));
        BusinessManager.addAppointmentToBusiness(position, new  Appointment("Linda","Lessons","414-543-2398","Linda@hun.com","12/8/2023","10:00AM"));
        BusinessManager.addAppointmentToBusiness(position, new Appointment("Gene","Lessons","414-765-4354","Gene@hun.com","12/10/2023","10:00AM"));
        BusinessManager.addAppointmentToBusiness(position, new  Appointment("Luis","Lessons","414-234-2987","Luis@hun.com","12/8/2023","10:00AM"));
        BusinessManager.addAppointmentToBusiness(position, new Appointment("Ted","Lessons","414-765-6233","Ted@hun.com","12/14/2023","10:00AM"));
        BusinessManager.addAppointmentToBusiness(position, new  Appointment("Mort","Lessons","414-123-5646","Mort@hun.com","12/10/2023","10:00AM"));
        BusinessManager.addAppointmentToBusiness(position, new Appointment("George","Lessons","414-432-2321","George@hun.com","12/8/2023","10:00AM"));
        position++;

        BusinessManager.addBusiness( new Business("Jennys Trailer Service",
                "Jenny Floura",
                "262-986-2018",
                "JennyF@gmail.com",
                "1029 George Wasking Lane",
                "Jenny1026",
                57483920));

        BusinessManager.addAppointmentToBusiness(position, new  Appointment("Chandler Nigh","Lessons","414-943-0215","Chandler@hun.com","12/10/2023","10:00AM"));
        BusinessManager.addAppointmentToBusiness(position, new Appointment("Joey","Lessons","414-232-3243","Joey@hun.com","12/10/2023","10:00AM"));
        BusinessManager.addAppointmentToBusiness(position, new  Appointment("Rachel","Lessons","414-148-3543","Rachel@hun.com","12/8/2023","10:00AM"));
        BusinessManager.addAppointmentToBusiness(position, new Appointment("Ross","Lessons","414-123-4537","Ross@hun.com","12/8/2023","10:00AM"));
        BusinessManager.addAppointmentToBusiness(position, new  Appointment("Monica","Lessons","414-876-7652","Monica@hun.com","12/8/2023","10:00AM"));
        BusinessManager.addAppointmentToBusiness(position, new Appointment("Fibi","Lessons","414-432-2348","Fibi@hun.com","12/12/2023","10:00AM"));
        BusinessManager.addAppointmentToBusiness(position, new  Appointment("Paul","Lessons","414-453-3249","Paul@hun.com","12/10/2023","10:00AM"));
        BusinessManager.addAppointmentToBusiness(position, new Appointment("Judd","Lessons","414-343-4323","james@Judd.com","12/12/2023","10:00AM"));
        Log.i("Status","About to add user appointment");

        position++;



    }
    //Pre-loading appointments method
    public static void loadAppointments(){

        BusinessManager.addAppointmentToBusiness(4, new Appointment("Joshua","Lessons","414-943-0215","josh2012@gmail.com","12/10/2023","12:00PM"));
        BusinessManager.addAppointmentToUser(new User_Appointment(BusinessManager.getBusinesses().get(4).getBusinessName(),
                BusinessManager.getBusinesses().get(4).getAddress(),
                BusinessManager.getBusinesses().get(4).getPhoneNumber(),
                BusinessManager.getBusinesses().get(4).getEmail(),
                "joshbag2012@gmail.com",
                "12/14/2023",
                "2:00PM",
                "1 hour trailer rental"));

        BusinessManager.addAppointmentToBusiness(1, new Appointment("Joshua","Lessons","414-943-0215","josh2012@gmail.com","12/12/2023","12:00PM"));
        BusinessManager.addAppointmentToUser(new User_Appointment(BusinessManager.getBusinesses().get(1).getBusinessName(),
                BusinessManager.getBusinesses().get(1).getAddress(),
                BusinessManager.getBusinesses().get(1).getPhoneNumber(),
                BusinessManager.getBusinesses().get(1).getEmail(),
                "joshbag2012@gmail.com",
                "12/02/2023",
                "12:00PM",
                "pre soak and 1 coat of gloss on all nails"));

        BusinessManager.addAppointmentToBusiness(2, new Appointment("Joshua","Lessons","414-943-0215","josh2012@gmail.com","12/12/2023","1:25PM"));
        BusinessManager.addAppointmentToUser(new User_Appointment(BusinessManager.getBusinesses().get(2).getBusinessName(),
                BusinessManager.getBusinesses().get(2).getAddress(),
                BusinessManager.getBusinesses().get(2).getPhoneNumber(),
                BusinessManager.getBusinesses().get(2).getEmail(),
                "joshbag2012@gmail.com",
                "12/12/2023",
                "1:25PM",
                "2 Ceiling fan installations"));


    }


//Testing only

    /*public static void logAllBusinessesCredentials(){
        for (int i = 0; i < BusinessManager.getBusinesses().size(); i++) {

            Business business = BusinessManager.getBusinesses().get(i);

            int passcode = business.getPasscode();
            String username = business.getUser_name();

            Log.i("Business UserName",username);
            Log.i("Business Passcode",Integer.toString(passcode));
        }
    }*/


    //Just Copies

    /*private void saveData() {

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


        Toast.makeText(this, "Saved Array List to Shared preferences. ", Toast.LENGTH_SHORT).show();
    }

    private void loadData() {
        // method to load arraylist from shared prefs
        // initializing our shared prefs with name as
        // shared preferences.

        SharedPreferences sharedPreferences = getSharedPreferences("BookyPrefs", MODE_PRIVATE);

        // creating a variable for gson.
        Gson gson = new Gson();

        // below line is to get to string present from our
        // shared prefs if not present setting it as null.
        String json = sharedPreferences.getString("businesses", null);

        if (json==null){
            data_handling.loadBusinesses();
            Log.i("Loading Status","No Prefs Present, loading new Businesses");
        }else{
            // below line is to get the type of our array list.
            Type type = new TypeToken<ArrayList<Business>>() {
            }.getType();
            // in below line we are getting data from gson
            // and saving it to our array list
            ArrayList<Business> businessArrayList = gson.fromJson(json, type);

            Log.i("Starting_Page_Status", "About to load Businesses");
            // checking below if the array list is empty or not

            BusinessManager.appendBusiness(businessArrayList);
            Log.i("Starting_Page_Status", "Businesses successfully loaded");
        }

        json = sharedPreferences.getString("appointments", null);

        if (json==null){
            data_handling.loadAppointments();
            Log.i("Loading Status","No Prefs Present, loading pre-loaded appointments");
        }else{
            // below line is to get the type of our array list.
            Type type = new TypeToken<ArrayList<User_Appointment>>() {
            }.getType();
            // in below line we are getting data from gson
            // and saving it to our array list
            ArrayList<User_Appointment> appointmentArrayList = gson.fromJson(json, type);

            Log.i("Starting_Page_Status", "About to load appointments");
            // checking below if the array list is empty or not

            BusinessManager.appendUserAppointmnets(appointmentArrayList);
            Log.i("Starting_Page_Status", "Appointments successfully loaded");
        }

    }*/
}

class DataManipulator {

    // Sort appointments by name
    public static void sortAppointmentsByName(List<Appointment> appointments) {
        appointments.sort(Comparator.comparing(Appointment::getName));
    }

    public static void sortUserAppointmentsByDate(List<User_Appointment> user_appointments) {
        Collections.sort(user_appointments, new Comparator<User_Appointment>() {
            @Override
            public int compare(User_Appointment appointment1, User_Appointment appointment2) {
                Date date1 = appointment1.getParsedDate();
                Date date2 = appointment2.getParsedDate();

                if (date1 != null && date2 != null) {
                    return date1.compareTo(date2);
                }

                return 0; // Handle if parsing fails
            }
        });
    }


    // Sort appointments by date
    public static void sortAppointmentsByDate(List<Appointment> appointments) {
        Collections.sort(appointments, new Comparator<Appointment>() {
            @Override
            public int compare(Appointment appointment1, Appointment appointment2) {
                Date date1 = appointment1.getParsedDate();
                Date date2 = appointment2.getParsedDate();

                if (date1 != null && date2 != null) {
                    return date1.compareTo(date2);
                }

                return 0; // Handle if parsing fails
            }
        });
    }

    // Sort businesses by name
    public static void sortBusinessesByName(List<Business> businesses) {
        businesses.sort(Comparator.comparing(Business::getBusinessName));
    }

    // Search businesses by name
    public static ArrayList<Business> searchBusinessesByName(ArrayList<Business> businesses, String query) {
        ArrayList<Business> results = new ArrayList<>();
        for (Business business : businesses) {
            if (business.getBusinessName().toLowerCase().contains(query.toLowerCase())) {
                results.add(business);
            }
        }
        return results;
    }
}
