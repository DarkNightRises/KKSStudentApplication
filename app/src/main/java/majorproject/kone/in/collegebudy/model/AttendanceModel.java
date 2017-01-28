package majorproject.kone.in.collegebudy.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by kartikey on 30/12/16.
 */

public class AttendanceModel {
    public static String ATTENDANCE = "attendance";
    public static String ORIGINAL_DATETIME= "Original_DATETIME";
    public static String IS_ATTENDANCE_IN_PROGRESS="is_attendance_in_progress";
    public String SUBJECT_NAME = "subject_name";
    public String SUBJECT_ID = "subject_id";
    public String LOCATION = "location";
    public String DATETIME = "datetime";
    private JSONObject attendanceObject;
    public  AttendanceModel(String jsonObject){
        try {
            this.attendanceObject = new JSONObject(jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getSUBJECT_ID() throws JSONException {
        return attendanceObject.getString(SUBJECT_ID);
    }

    public String getSUBJECT_NAME() throws JSONException {
        return attendanceObject.getString(SUBJECT_NAME);
    }

    public String getDATETIME() throws JSONException {

        return attendanceObject.getString(DATETIME);
    }

    public String getLOCATION() throws JSONException {
        return attendanceObject.getString(LOCATION);
    }
}
