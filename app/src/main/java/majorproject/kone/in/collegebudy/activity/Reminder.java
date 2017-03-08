package majorproject.kone.in.collegebudy.activity;

import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
    private int REQUEST_CODE=1;
    private RecyclerView mRecyclerView;
    public static RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static List<ReminderList> reminderList;
    public static ReminderDbHelper reminderDbHelper;


    public static void getAllReminder(){
        reminderList.clear();
        reminderList.addAll(Reminder.reminderDbHelper.getAllReminders());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        reminderDbHelper = new ReminderDbHelper(Reminder.this);
        noReminderText = (TextView) findViewById(R.id.no_notification_textview);
        noImageReminder = (ImageView) findViewById(R.id.no_notification_imageview);
        mRecyclerView=(RecyclerView) findViewById(R.id.reminder_Recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        ReminderDbHelper reminderDbHelper=new ReminderDbHelper(this);
        reminderList=reminderDbHelper.getAllReminders();
        mAdapter =new RecyclerViewAdapter(reminderList);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               startActivity(new Intent(Reminder.this,AddReminder.class));


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==REQUEST_CODE && resultCode==RESULT_OK)
        {

        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
