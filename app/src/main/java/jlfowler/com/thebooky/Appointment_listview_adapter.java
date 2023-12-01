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

public class Appointment_listview_adapter extends ArrayAdapter<Appointment> {

    private final Context context;
    private final ArrayList<Appointment> appointments;

    public Appointment_listview_adapter(@NonNull @NotNull Context context, ArrayList<Appointment> list) {
        super(context, R.layout.customer_appointment_listview_listitem_model,list);
        this.context = context;
        this.appointments = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.customer_appointment_listview_listitem_model,parent,false);

        TextView customer_name_textview = (TextView) rowView.findViewById(R.id.customer_model_name_textview);
        TextView phone_number_textview = (TextView) rowView.findViewById(R.id.customer_model_phone_number_textview);
        TextView customer_services_textview = (TextView) rowView.findViewById(R.id.customer_services_textview);
        TextView email_textview = (TextView) rowView.findViewById(R.id.customer_model_email_textview);
        TextView date_textview = (TextView) rowView.findViewById(R.id.customer_model_date_textview);
        TextView time_textview = (TextView) rowView.findViewById(R.id.customer_model_time_textview);



        customer_name_textview.setText(appointments.get(position).getName());
        phone_number_textview.setText(appointments.get(position).getPhoneNumber());
        customer_services_textview.setText(appointments.get(position).getServiceType());
        email_textview.setText(appointments.get(position).getEmail());
        date_textview.setText(appointments.get(position).getDate());
        time_textview.setText(appointments.get(position).getTime());





        return rowView;


    }
}


