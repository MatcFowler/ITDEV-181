package jlfowler.com.thebooky;

import android.content.Intent;
import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class search_page extends AppCompatActivity {

    ListView businesses_listview;
    EditText seach_page_search_edittext;
    ArrayList<Business> businesses_arraylist;
    Business_listview_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_page);

        businesses_listview = findViewById(R.id.business_appointment_listview);
        seach_page_search_edittext = findViewById(R.id.seach_page_search_edittext);


        businesses_arraylist = BusinessManager.getBusinesses();
        DataManipulator.sortBusinessesByName(businesses_arraylist);

        updateList(businesses_arraylist);


        seach_page_search_edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ArrayList<Business> displayList;

                if (charSequence.length() > 0) {
                    // Filter the original list based on user input
                    displayList = DataManipulator.searchBusinessesByName(businesses_arraylist, charSequence.toString());
                } else {
                    // User input is empty, use the original list directly
                    displayList = businesses_arraylist;
                }

                updateList(displayList);

            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

       businesses_listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent = new Intent(jlfowler.com.thebooky.search_page.this, jlfowler.com.thebooky.book_appointment_ui.class);
               intent.putExtra("index",position);
               startActivity(intent);
           }
       });

       businesses_listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
               Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+ BusinessManager.getBusinesses().get(position).getPhoneNumber()));
               startActivity(intent);
               return false;
           }
       });
        ImageView personal_page_imageview = findViewById(R.id.personal_page_nav_imageview);
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

    //Callable List updater that can be passed any Business Arraylist and display it
    public void updateList(ArrayList<Business> adapter_list){
        adapter = new Business_listview_adapter(this,adapter_list);
        businesses_listview.setAdapter(adapter);
    }

    //Navigation module, that tells the Imageviews apart based on thier tag attributes
    public void navBarOperations(View view) {
        String tag = view.getTag().toString();
        Class<?> destination;

        switch (tag) {
            case "Search_Page_Users_Page":
                destination = user_main_page.class;
                break;
            case "Search_Page_Book_User_Page":
                destination = search_page.class;
                break;
            default:
                destination = business_needs_page.class;
                break;
        }

        Intent intent = new Intent(this, destination);
        startActivity(intent);
    }
}