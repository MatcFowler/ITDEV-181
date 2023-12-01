package jlfowler.com.thebooky;

import java.util.ArrayList;

public class BusinessManager {
    private static ArrayList<Business> businesses_arraylist = new ArrayList<>();
    private static ArrayList<User_Appointment> user_appointment_arrayList = new ArrayList<>();

    public static ArrayList<Business> getBusinesses() {
        return businesses_arraylist;
    }
    public static ArrayList<User_Appointment> getUserAppointments() {
        return user_appointment_arrayList;
    }

    public static void addBusiness(Business business) {
        businesses_arraylist.add(business);
    }

    public static void appendBusiness(ArrayList<Business> businessArrayList){
        businesses_arraylist = businessArrayList;
    }

    public static void appendUserAppointmnets(ArrayList<User_Appointment> appointment_arraylist){
        user_appointment_arrayList = appointment_arraylist;
    }

    public static void addAppointmentToBusiness(int position, Appointment appointment){
        businesses_arraylist.get(position).getAppointments().add(appointment);
    }
    public static void removeAppointmentFromBusiness(int position, int appointment_position){
        businesses_arraylist.get(position).getAppointments().remove(appointment_position);
    }


    public static void addAppointmentToUser(User_Appointment appointment){
        user_appointment_arrayList.add(appointment);
    }

}
