package majorproject.kone.in.collegebudy.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by kartikey on 15/01/17.
 */

public class College {
    public JSONArray collegeArray;
    public ArrayList<String> collegeNames;
    public ArrayList<Integer> collegeId;

    public College(JSONArray jsonArray){
        this.collegeArray = jsonArray;
        setCollegeList();
    }
    public void setCollegeList(){
        collegeNames = new ArrayList<>();
        collegeId = new ArrayList<>();
        for (int i=0;i<collegeArray.length();i++){
            try {
                JSONObject collegeObject = (JSONObject) collegeArray.get(i);
                collegeNames.add(collegeObject.getString("name"));
                collegeId.add(collegeObject.getInt("id"));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    public ArrayList<String> getCollegeList(){
        return this.collegeNames;
    }
    public int getId(int position){
        return collegeId.get(position);
    }
}
