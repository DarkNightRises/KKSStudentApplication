package majorproject.kone.in.collegebudy.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import majorproject.kone.in.collegebudy.R;
import majorproject.kone.in.collegebudy.adapter.RecyclerViewAdapter;

public class Reminder extends AppCompatActivity {

    EditText title, date, description, time;
    String titleData, dateData, descriptionData, timeData;
    public static TextView noReminderText;
    public static ImageView noImageReminder;
    public static CardView cardViewReminder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        title = (EditText) findViewById(R.id.title_edittext);
        description = (EditText) findViewById(R.id.description_edittext);
        date = (EditText) findViewById(R.id.date_edittext);
        time = (EditText) findViewById(R.id.time_edittext);
        noReminderText =(TextView)findViewById(R.id.no_notification_textview);
        noImageReminder=(ImageView)findViewById(R.id.no_notification_imageview);
        cardViewReminder=(CardView)findViewById(R.id.cardview_reminder);

        Intent intent = this.getIntent();
        if (intent != null) {
            titleData = intent.getStringExtra("TilteData");
            dateData = intent.getStringExtra("DateData");
            descriptionData = intent.getStringExtra("DescriptionData");
            timeData = intent.getStringExtra("TimeData");
            title.setText(timeData);
            date.setText(dateData);
            description.setText(descriptionData);
            time.setText(timeData);
        }


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Reminder.this, AddReminder.class);
                startActivity(intent);

            }
        });
    }
}
