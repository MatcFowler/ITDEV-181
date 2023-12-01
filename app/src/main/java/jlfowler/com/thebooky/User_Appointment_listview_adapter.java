package jlfowler.com.thebooky;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class User_Appointment_listview_adapter extends ArrayAdapter<User_Appointment> {

    private final Context context;
    private final ArrayList<User_Appointment> appointments;

    public User_Appointment_listview_adapter(@NonNull @NotNull Context context, ArrayList<User_Appointment> list) {
        super(context, R.layout.customer_appointment_listview_listitem_model,list);
        this.context = context;
        this.appointments = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.user_appointment_listview_model,parent,false);


        TextView user_business_name = (TextView) rowView.findViewById(R.id.user_model_business_name_textview);
        TextView user_business_address = (TextView) rowView.findViewById(R.id.user_model_business_address_textview);
        TextView user_business_phone = (TextView) rowView.findViewById(R.id.user_model_business_phone_textview);
        TextView user_business_email = (TextView) rowView.findViewById(R.id.user_model_business_email_textview);
        TextView user_email = (TextView) rowView.findViewById(R.id.user_model_user_email_textview);
        TextView user_services = (TextView) rowView.findViewById(R.id.user_model_services_textview);
        TextView user_date_textview = (TextView) rowView.findViewById(R.id.user_model_date_textview);
        TextView user_time_textview = (TextView) rowView.findViewById(R.id.user_model_time_textview);


        user_business_name.setText(appointments.get(position).getBusiness_name());
        user_business_address.setText(appointments.get(position).getBusiness_address());
        user_business_phone.setText(appointments.get(position).getBusiness_phone());
        user_business_email.setText(appointments.get(position).getBusiness_email());
        user_email.setText(appointments.get(position).getEmail());
        user_services.setText(appointments.get(position).getServiceType());
        user_date_textview.setText(appointments.get(position).getDate());
        user_time_textview.setText(appointments.get(position).getTime());


        return rowView;


    }
}
