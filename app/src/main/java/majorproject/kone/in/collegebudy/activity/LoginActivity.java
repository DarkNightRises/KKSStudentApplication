package majorproject.kone.in.collegebudy.activity;

/**
 * Created by kartikey on 02/09/16.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

/**
 * Created by Shobhit on 03-07-2015.
 */

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import android.app.Activity;

import com.gc.materialdesign.views.ButtonFlat;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import majorproject.kone.in.collegebudy.Config;
import majorproject.kone.in.collegebudy.R;
import majorproject.kone.in.collegebudy.listener.NetworkResponseListener;
import majorproject.kone.in.collegebudy.model.Student;
import majorproject.kone.in.collegebudy.network.FetchData;


public class LoginActivity extends Activity implements NetworkResponseListener, View.OnClickListener {
    public EditText email;
    public EditText mPasswordView;
    private TextView register, forgotPassword;
    public ProgressBar progressBar;
    private boolean isInternetConnected;
    private TextView signUp;
    private SharedPreferences sharedpreferences;
    private TextView skipLogin;
    private Intent intent;
    public boolean forCompulsaryLogin = false;
    private FetchData fetchDataforLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout_new);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        findViewById(R.id.sign_up).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });
        signUp = (TextView) findViewById(R.id.sign_up);
        signUp.setOnClickListener(this);
        email = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        ButtonFlat mEmailSignInButton = (ButtonFlat) findViewById(R.id.login);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  if (networkDetector.isConnectingToInternet()) {
                try {
                    attemptLogin();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                //}
            }
        });

    }

    private void startMainActivityIntent(Context context) {
        intent = new Intent(context, MainActivity.class);
        startActivity(intent);
    }

    public void attemptLogin() throws JSONException, UnsupportedEncodingException {
        // Reset errors.
        email.setError(null);
        mPasswordView.setError(null);
        // Store values at the time of the login attempt.
        String mobile_number = email.getText().toString();
        String password = mPasswordView.getText().toString();
        Log.d("Username and password", "Username" + mobile_number + password);
        boolean cancel = false;
        View focusView = null;
        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError("Field Required");
            focusView = mPasswordView;
            cancel = true;
        }
        // Check for a valid email address.
        if (TextUtils.isEmpty(mobile_number)) {
            email.setError("Field Required");
            focusView = email;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        } else {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("email", mobile_number);
            jsonObject.put("password", password);
            fetchDataforLists = new FetchData(LoginActivity.this, this);
            fetchDataforLists.setUrl(Config.BASE_URL + Config.LOGIN);
            fetchDataforLists.setData(jsonObject);
            fetchDataforLists.setType_of_request(Config.POST);
            fetchDataforLists.execute();
//            Intent intent = new Intent(LoginActivity.this,NavigationActivity.class);
//            startActivity(intent);
        }
    }

    @Override
    public void preRequest() throws MalformedURLException {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void postRequest(String result) throws MalformedURLException {
        progressBar.setVisibility(View.GONE);
        Log.d("Final Result", "  " + result);
        if(FetchData.isOnline(this)&&result!=null) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                if ((jsonObject.getBoolean("success")) == true) {
//                JSONArray jsonArray = jsonObject.get
                    JSONObject data = (JSONObject) (jsonObject.getJSONArray("data")).get(0);
                    Toast.makeText(LoginActivity.this, "Login succesfull Data is " + data, Toast.LENGTH_SHORT).show();
                    //Saving student credentials
                    Student student = new Student(data);
                    Intent intent = new Intent(LoginActivity.this, NavigationActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Login Unsuccessful... Invalid email id or password.", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else
            Toast.makeText(LoginActivity.this,"Please Check your Internet Connectivity",Toast.LENGTH_LONG).show();
        //Toast.makeText(LoginActivity.this,"Toast in Login is "+result,Toast.LENGTH_SHORT).show();
    }


    public void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.sign_up:
                intent = new Intent(LoginActivity.this, SignUpActivity.class);
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
