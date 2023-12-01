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
import java.util.List;

public class Business_listview_adapter extends ArrayAdapter<Business> {

    private final Context context;
    private final ArrayList<Business> values;

    public Business_listview_adapter(@NonNull @NotNull Context context, ArrayList<Business> list) {
        super(context, R.layout.business_listview_listitem_model,list);
        this.context = context;
        this.values = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.business_listview_listitem_model,parent,false);

        TextView business_name_textview = (TextView) rowView.findViewById(R.id.customer_model_date_textview);
        TextView business_owner_textview = (TextView) rowView.findViewById(R.id.customer_model_time_textview);

        business_name_textview.setText(values.get(position).getBusinessName());
        business_owner_textview.setText(values.get(position).getOwnerName());


        return rowView;


    }

    public void setData(List<Business> data) {
        clear();
        addAll(data);
        notifyDataSetChanged();
    }
}
