package majorproject.kone.in.collegebudy.activity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import majorproject.kone.in.collegebudy.R;
import majorproject.kone.in.collegebudy.adapter.RecyclerViewAdapter;

public class Reminder extends AppCompatActivity implements DialogInterface{

    EditText title, date, description, time;
    String titleData, dateData, descriptionData, timeData;
    public static TextView noReminderText;
    public static ImageView noImageReminder;

    private int REQUEST_CODE = 1;
    private RecyclerView mRecyclerView;
    public static RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static ArrayList<ReminderList> reminderList;
    public static ReminderDbHelper reminderDbHelper;
    private Intent intent;
    public static int INTENT_CODE=100;
    private ImageView no_notification_view;
    public static void getAllReminder() {
        reminderList.clear();
        reminderList.addAll(Reminder.reminderDbHelper.getAllReminders());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        no_notification_view = (ImageView) findViewById(R.id.no_notification_imageview);
        reminderDbHelper = new ReminderDbHelper(Reminder.this);
        //noReminderText = (TextView) findViewById(R.id.no_notification_textview);
        noImageReminder = (ImageView) findViewById(R.id.no_notification_imageview);
        mRecyclerView = (RecyclerView) findViewById(R.id.reminder_Recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        reminderDbHelper = new ReminderDbHelper(this);

        reminderList = reminderDbHelper.getAllReminders();
        if(reminderList.size()>0) {
            noImageReminder.setVisibility(View.GONE);
            no_notification_view.setVisibility(View.GONE);
           // noReminderText.setVisibility(View.GONE);
        }
        mAdapter = new RecyclerViewAdapter(reminderList);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Reminder.this, AddReminder.class);
                startActivityForResult(intent,INTENT_CODE);


            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == INTENT_CODE) {
            reminderList.clear();
            Log.d("Length ","Length is "+reminderDbHelper.getAllReminders().size());
            reminderList.addAll(reminderDbHelper.getAllReminders());
            if(reminderList.size()>0) {
                noImageReminder.setVisibility(View.GONE);
                no_notification_view.setVisibility(View.GONE);
            }mAdapter.notifyDataSetChanged();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    public void reminderDelete(View view)
    {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                Reminder.this);

        alertDialogBuilder.setTitle("Reminders");
        alertDialogBuilder.setCancelable(true);
        alertDialogBuilder
                .setMessage("Are you sure you want to delete the reminder")
                .setCancelable(true)
                .setPositiveButton( "Yes",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        try {
                        } catch (Exception e) {
                        }
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();    }

    @Override
    public void cancel() {

    }

    @Override
    public void dismiss() {

    }
}


