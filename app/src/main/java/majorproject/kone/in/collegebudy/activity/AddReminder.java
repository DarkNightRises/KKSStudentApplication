package majorproject.kone.in.collegebudy.activity;

import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import majorproject.kone.in.collegebudy.R;

public class AddReminder extends AppCompatActivity implements View.OnClickListener {
    EditText date, title, description, time;
    Button save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
        date = (EditText) findViewById(R.id.date);
        time = (EditText) findViewById(R.id.time);
        title = (EditText) findViewById(R.id.title);
        description = (EditText) findViewById(R.id.description);
        save=(Button)findViewById(R.id.save);
        save.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        Reminder.noReminderText.setVisibility(View.GONE);
        Reminder.noImageReminder.setVisibility(View.GONE);
        Reminder.cardViewReminder.setVisibility(View.VISIBLE);
        Intent intent =new Intent(AddReminder.this, Reminder.class);
        intent.putExtra("TitleData",title.getText().toString());
        intent.putExtra("DescriptonData",description.getText().toString());
        intent.putExtra("DateData",date.getText().toString());
        intent.putExtra("TimeData",time.getText().toString());
        AddReminder.this.startActivity(intent);

    }
}