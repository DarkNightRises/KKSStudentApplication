package majorproject.kone.in.collegebudy.model;

import android.content.SharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import majorproject.kone.in.collegebudy.Utility.SharedPreferencesSingleton;

/**
 * Created by kartikey on 15/01/17.
 */

public class Student {
    JSONObject studentInfo ;
    private SharedPreferences sharedPreferences;
    public static String id = "id";
    public static String name = "name";
    public static String section_id = "section_id";
    public static String college_id = "college_id";
    public static String email = "email";
    public static String mob_no = "mob_no";
    public static String password = "password";
    public static String gcm_id = "gcm_id";
    public static String device_id = "device_id";
    public static String api_token = "api_token";

    private SharedPreferences.Editor editor;
    public Student(JSONObject jsonObject){
        this.studentInfo = jsonObject;
        saveStudentInfo();
    }
    private void saveStudentInfo(){
//      sharedPreferences = SharedPreferencesSingleton.getSharedPreference();
      editor = SharedPreferencesSingleton.getSharedPreferenceEditor();
        try {
            editor.putInt(id,studentInfo.getInt(id));
            editor.putString(name,studentInfo.getString(name));
            editor.putInt(section_id,studentInfo.getInt(section_id));
            editor.putInt(college_id,studentInfo.getInt(college_id));
            editor.putString(email,studentInfo.getString(email));
            editor.putString(mob_no,studentInfo.getString(mob_no));
            editor.putString(gcm_id,studentInfo.getString(gcm_id));
            editor.putString(device_id,studentInfo.getString(device_id));
            editor.putString(api_token,studentInfo.getString(api_token));
            editor.commit();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
