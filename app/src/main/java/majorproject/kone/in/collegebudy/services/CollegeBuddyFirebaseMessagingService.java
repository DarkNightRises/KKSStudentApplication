package majorproject.kone.in.collegebudy.services;

import android.Manifest;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import majorproject.kone.in.collegebudy.Config;
import majorproject.kone.in.collegebudy.R;
import majorproject.kone.in.collegebudy.Utility.GPSTracker;
import majorproject.kone.in.collegebudy.Utility.SharedPreferencesSingleton;
import majorproject.kone.in.collegebudy.activity.MainActivity;
import majorproject.kone.in.collegebudy.activity.NavigationActivity;
import majorproject.kone.in.collegebudy.model.AttendanceModel;

/**
 * Created by kartikey on 26/12/16.
 */

public class CollegeBuddyFirebaseMessagingService extends FirebaseMessagingService implements  GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {
    private static final String TAG = "MyFirebaseMsgService";
    private GoogleApiClient googleApiClient;
    private Location mLastLocation;
    private String message,dataPayload;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private AttendanceModel attendanceModel;
    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // [START_EXCLUDE]
        // There are two types of messages data messages and notification messages. Data messages are handled
        // here in onMessageReceived whether the app is in the foreground or background. Data messages are the type
        // traditionally used with GCM. Notification messages are only received here in onMessageReceived when the app
        // is in the foreground. When the app is in the background an automatically generated notification is displayed.
        // When the user taps on the notification they are returned to the app. Messages containing both notification
        // and data payloads are treated as notification messages. The Firebase console always sends notification
        // messages. For more see: https://firebase.google.com/docs/cloud-messaging/concept-options
        // [END_EXCLUDE]

        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        sharedPreferences = SharedPreferencesSingleton.getSharedPreference();
        editor = SharedPreferencesSingleton.getSharedPreferenceEditor();
        // Check if message contains a data payload.
        dataPayload = remoteMessage.getData().get("payload");
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + dataPayload);
            try {
                JSONObject jsonObject = new JSONObject(dataPayload);
                if(jsonObject.getString("reason").equals(AttendanceModel.ATTENDANCE)){
                    attendanceModel = new AttendanceModel(jsonObject.getString(Config.DATA));
                    Log.d("Attendance model",attendanceModel.getDATETIME()+"    "+attendanceModel.getSUBJECT_NAME());
                    editor.putBoolean(AttendanceModel.IS_ATTENDANCE_IN_PROGRESS,true);
                    editor.putLong(AttendanceModel.ORIGINAL_DATETIME,System.currentTimeMillis());
                    editor.putString(attendanceModel.SUBJECT_ID,attendanceModel.getSUBJECT_ID());
                    editor.putString(attendanceModel.LOCATION,attendanceModel.getLOCATION());
                    editor.putString(attendanceModel.DATETIME,attendanceModel.getDATETIME());
                    editor.putString(attendanceModel.SUBJECT_NAME,attendanceModel.getSUBJECT_NAME());
                    editor.commit();
                    if(googleApiClient == null){
                        googleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                                .addConnectionCallbacks(this)
                                .addOnConnectionFailedListener(this)
                                .addApi(LocationServices.API)
                                .build();

                    }
                    googleApiClient.connect();
                }
                else{
                    message =remoteMessage.getNotification().getBody();
                    sendNotification(message);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }



        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }
    // [END receive_message]

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    private void sendNotification(String messageBody) {
        Intent intent = new Intent(this, NavigationActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.custom_login_icon)
                .setContentTitle("FCM Message")
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (android.os.Build.VERSION.SDK_INT >= 23 && ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_DENIED) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                mLastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
               }
        }
        else{
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);

        }

        String latLng = (sharedPreferences.getString(attendanceModel.LOCATION,""));
        latLng = latLng.substring(2,latLng.length()-1);
        String[] latlngarray = latLng.split(",");
        Location teacherLocation = new Location("");
        teacherLocation.setLatitude(Double.parseDouble(latlngarray[0]));
        teacherLocation.setLongitude(Double.parseDouble(latlngarray[1]));
        Location studentLocation = new Location("");
        studentLocation.setLatitude(mLastLocation.getLatitude());
        studentLocation.setLongitude(mLastLocation.getLongitude());
        Log.d("Location","Distance is "+ teacherLocation.distanceTo(studentLocation));

        sendNotification(message);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {

    }
}
