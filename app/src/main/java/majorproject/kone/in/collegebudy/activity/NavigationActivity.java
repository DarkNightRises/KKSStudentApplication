package majorproject.kone.in.collegebudy.activity;

import android.Manifest;
import android.app.Dialog;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.nfc.tech.NfcA;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresPermission;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ScrollView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.internal.game.Screenshot;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import majorproject.kone.in.collegebudy.Config;
import majorproject.kone.in.collegebudy.R;
import majorproject.kone.in.collegebudy.Utility.SharedPreferencesSingleton;
import majorproject.kone.in.collegebudy.adapter.CustomPagerAdapter;
import majorproject.kone.in.collegebudy.fragment.PersonalFragment;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private ViewPager dashboard_pager;

    //Variable declaration
    private CustomPagerAdapter customPagerAdapter;
    private PagerSlidingTabStrip tabStrip;      //Strip that shows title for pager of dashboard
    private Intent intent;      //intent for drawer click activity switch
    private LatLng currentLocation;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private Location mLastLocation;
    private ScrollView scrollViewHome;
    private ReminderDbHelper reminderDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initialiseViewPager();
        saveProfile();
        scrollViewHome = (ScrollView) findViewById(R.id.scrollview_homefragment);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        currentLocation = new LatLng(28.6767091, 77.4997618);
        Log.d("Current Location", "" + currentLocation.toString());
        FirebaseApp.initializeApp(this);
        FirebaseMessaging.getInstance().subscribeToTopic("f18IT4");
        FirebaseMessaging.getInstance().subscribeToTopic("f1CSE4");
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NavigationActivity.this, WriteQuery.class);
                startActivity(intent);
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d("Refershered token", token);
        mSharedPreferences = SharedPreferencesSingleton.getSharedPreference();
        mEditor = SharedPreferencesSingleton.getSharedPreferenceEditor();
        reminderDbHelper = new ReminderDbHelper(NavigationActivity.this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        intent = null;
        // Handle navigation view item clicks in drawer.
        int id = item.getItemId();
        if (id == R.id.nav_attendance) {

            intent = new Intent(NavigationActivity.this, Attendance.class);

        } else if (id == R.id.nav_query) {
            intent = new Intent(NavigationActivity.this, WriteQuery.class);
        } else if (id == R.id.nav_notificaton) {
            intent = new Intent(NavigationActivity.this, NotificationActivity.class);
        } else if (id == R.id.nav_write_review) {
            intent = new Intent(NavigationActivity.this, WriteeReview.class);
        } else if (id == R.id.nav_share) {
            Intent i = new Intent(android.content.Intent.ACTION_SEND);
            i.setType("text/plain");
            i.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject test");
            i.putExtra(android.content.Intent.EXTRA_TEXT, "extra text that you want to put");
            startActivity(Intent.createChooser(i, "Share via"));
            intent = null;
        } else if (id == R.id.nav_profile) {
            intent = new Intent(NavigationActivity.this, PersonalFragment.class);
        }
        if (intent != null)
            startActivity(intent);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //View Pager initialisation with its tab strip
    public void initialiseViewPager() {
        dashboard_pager = (ViewPager) findViewById(R.id.pager_dashboard);
        tabStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        customPagerAdapter = new CustomPagerAdapter(getSupportFragmentManager(), NavigationActivity.this);
        dashboard_pager.setAdapter(customPagerAdapter);
        tabStrip.setViewPager(dashboard_pager);
    }

    public void reminderButton(View view) {
        Intent intent = new Intent(NavigationActivity.this, Reminder.class);
        startActivity(intent);
    }

    public void checkAttendance(View view) {
        Intent intent = new Intent(NavigationActivity.this, Attendance.class);
        startActivity(intent);
    }

    public void checkNotification(View view) {
        Intent intent = new Intent(NavigationActivity.this, NotificationActivity.class);
        startActivity(intent);
    }

    public void saveProfile()
    {

    }
}
