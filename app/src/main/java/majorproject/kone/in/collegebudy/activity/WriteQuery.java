package majorproject.kone.in.collegebudy.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.net.MalformedURLException;

import majorproject.kone.in.collegebudy.R;
import majorproject.kone.in.collegebudy.listener.NetworkResponseListener;

public class WriteQuery extends AppCompatActivity implements View.OnClickListener,NetworkResponseListener {
    AutoCompleteTextView autoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_query);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        autoCompleteTextView =(AutoCompleteTextView)findViewById(R.id.query_auto);
        String[] teacherList=getResources().getStringArray(R.array.list_of_teachers);
        ArrayAdapter adapter =new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,teacherList);
        autoCompleteTextView.setAdapter(adapter);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }

    @Override
    public void onClick(View view) {
       /* switch (view.getId()){
            case R.id.sendQuery:
                //Enter your json data here
                try {
                    if(fetchData!=null){
                        fetchData = null;
                    }
                    fetchData = new FetchData(Query.this,Query.this);
                    fetchData.setType_of_request(Config.POST);
                    jsonObject = new JSONObject();
                    fetchData.setData(jsonObject);
                    fetchData.execute();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }*/
    }

    @Override
    public void preRequest() throws MalformedURLException {

    }

    @Override
    public void postRequest(String result) throws MalformedURLException {

    }
}


