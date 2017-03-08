package majorproject.kone.in.collegebudy.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Date;

import majorproject.kone.in.collegebudy.R;

public class AddReminder extends AppCompatActivity implements View.OnClickListener {
    EditText title, description;
    DatePicker datePicker;
    Button save;
    boolean flag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        title = (EditText) findViewById(R.id.title);
        description = (EditText) findViewById(R.id.description);
        datePicker = (DatePicker) findViewById(R.id.reminderDatePicker);
        save = (Button) findViewById(R.id.save);
        save.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        String titleData = title.getText().toString();
        String descriptionData = description.getText().toString();
        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth() + 1;
        int year = datePicker.getYear();
        ReminderDbHelper dbHelper = new ReminderDbHelper(this);
        flag=dbHelper.insertReminders(titleData, day, month, year);
        if(flag)
        {
            Log.d("1","false or true value");
        }
        dbHelper.getAllReminders();
        onBackPressed();

    }
}