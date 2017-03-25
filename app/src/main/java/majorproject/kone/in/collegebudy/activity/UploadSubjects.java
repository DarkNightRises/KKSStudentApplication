package majorproject.kone.in.collegebudy.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Spinner;

import majorproject.kone.in.collegebudy.R;

public class UploadSubjects extends AppCompatActivity {
    private Spinner subject1,subject2,subject3,subject4,subject5,subject6;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_subjects);
        subject1 = (Spinner) findViewById(R.id.subjectName1);
        subject2 = (Spinner) findViewById(R.id.subjectName2);
        subject3= (Spinner) findViewById(R.id.subjectName3);
        subject4 = (Spinner) findViewById(R.id.subjectName4);
        subject5 = (Spinner) findViewById(R.id.subjectName5);
        subject6 = (Spinner) findViewById(R.id.subjectName6);


    }

}
