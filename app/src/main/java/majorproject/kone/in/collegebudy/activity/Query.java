package majorproject.kone.in.collegebudy.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import majorproject.kone.in.collegebudy.Config;
import majorproject.kone.in.collegebudy.R;
import majorproject.kone.in.collegebudy.listener.NetworkResponseListener;
import majorproject.kone.in.collegebudy.network.FetchData;

/**
 * Created by kartikey on 23/12/16.
 */

public class Query extends AppCompatActivity implements View.OnClickListener,NetworkResponseListener{
    TextView query_text;
    FetchData fetchData;
    Button sendQuery;
    JSONObject jsonObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getActionBar().setDisplayHomeAsUpEnabled(true);*/
        setContentView(R.layout.query);
        query_text = (TextView) findViewById(R.id.query_text);
        sendQuery = (Button) findViewById(R.id.sendQuery);
        sendQuery.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
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
        }
    }

    @Override
    public void preRequest() throws MalformedURLException {

    }

    @Override
    public void postRequest(String result) throws MalformedURLException {

    }
}
