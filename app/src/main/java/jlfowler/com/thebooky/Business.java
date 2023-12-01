package jlfowler.com.thebooky;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Business {
    private String businessName;
    private String ownerName;
    private String phoneNumber;
    private String email;
    private String address;
    private String user_name;
    private int passcode;
    private ArrayList<Appointment> appointments;

    public Business(String businessName, String ownerName, String phoneNumber, String email, String address,String user_name, int passcode) {
        this.businessName = businessName;
        this.ownerName = ownerName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
        this.user_name = user_name;
        this.passcode = passcode;
        this.appointments = new ArrayList<Appointment>();
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getPasscode() {
        return passcode;
    }

    public void setPasscode(int passcode) {
        this.passcode = passcode;
    }

    public ArrayList<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(ArrayList<Appointment> appointments) {

        this.appointments = appointments;
    }
}
    class Appointment {
        private String name;
        private String phoneNumber;
        private String email;
        private String date;
        private String time;
        private String serviceType;

        public Appointment(String name, String serviceType, String phoneNumber, String email, String date, String time) {
            this.name = name;
            this.serviceType = serviceType;
            this.phoneNumber = phoneNumber;
            this.email = email;
            this.date = date;
            this.time = time;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getServiceType() {
            return serviceType;
        }

        public void setServiceType(String serviceType) {
            this.serviceType = serviceType;
        }

        public Date getParsedDate() {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
            try {
                return sdf.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
    }
class User_Appointment {
    private String  email, date, time, business_name,business_address,business_phone,business_email,service_type;

    public User_Appointment(String business_name,String business_address, String business_phone, String business_email, String email, String date, String time, String service_type) {
        this.business_name = business_name;
        this.business_address = business_address;
        this.business_phone = business_phone;
        this.business_email = business_email;
        this.service_type = service_type;
        this.email = email;
        this.date = date;
        this.time = time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public String getBusiness_address() {
        return business_address;
    }

    public void setBusiness_address(String business_address) {
        this.business_address = business_address;
    }

    public String getBusiness_phone() {
        return business_phone;
    }

    public void setBusiness_phone(String business_phone) {
        this.business_phone = business_phone;
    }

    public String getBusiness_email() {
        return business_email;
    }

    public void setBusiness_email(String business_email) {
        this.business_email = business_email;
    }
    public String getServiceType() {
        return service_type;
    }

    public void setServiceType(String service_type) {
        this.service_type = service_type;
    }

    public Date getParsedDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());
        try {
            return sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
