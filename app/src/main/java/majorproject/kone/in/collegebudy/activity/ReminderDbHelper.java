package majorproject.kone.in.collegebudy.activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by batra on 04-03-2017.
 */

public class ReminderDbHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "SQLiteReminder.db";
    private static final int DATABASE_VERSION = 1;
    public static final String REMINDER_TABLE_NAME = "Reminders";
    public static final String Reminder_COLUMN_ID = "_id";
    public static final String REMINDER_COLUMN_TITLE = "title";
    public static final String REMINDER_COLUMN_DAY = "day";
    public static final String REMINDER_COLUMN_MONTH = "month";
    public static final String REMINDER_COLUMN_YEAR = "year";
    Cursor cursor;

    public ReminderDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + REMINDER_TABLE_NAME + "(" +

                REMINDER_COLUMN_TITLE + " TEXT, " +
                REMINDER_COLUMN_YEAR + " INTEGER , " +
                REMINDER_COLUMN_DAY + " INTEGER, " +
                REMINDER_COLUMN_MONTH + " INTEGER)"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+REMINDER_TABLE_NAME);
        onCreate(db);
    }
    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();

        int numRows = (int) DatabaseUtils.queryNumEntries(db, REMINDER_TABLE_NAME);
        return numRows;
    }
    public boolean insertReminders(String title, int day, int month, int year) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(REMINDER_COLUMN_TITLE, title);
        contentValues.put(REMINDER_COLUMN_DAY, day);
        contentValues.put(REMINDER_COLUMN_MONTH, month);
        contentValues.put(REMINDER_COLUMN_YEAR, year);
        db.insert(REMINDER_TABLE_NAME, null, contentValues);
        Log.d("Titles are",title+"  "+day+"  "+month+"  "+year);
        return true;
    }

    public ArrayList<ReminderList> getAllReminders() {

        ArrayList<ReminderList> reminderList = new ArrayList<>();
        int numberOFRows = numberOfRows();
        Log.d("Number of rows","    "+numberOFRows);
        if(numberOFRows>0){
            String selectQuery = "SELECT  * FROM " + REMINDER_TABLE_NAME;
            SQLiteDatabase db = this.getReadableDatabase();
            cursor = db.rawQuery(selectQuery, null);
            cursor.moveToFirst();
            while (cursor.isAfterLast()==false) {
                ReminderList reminders = new ReminderList();
                reminders.setTitle(cursor.getString(cursor.getColumnIndex(REMINDER_COLUMN_TITLE)));
                reminders.setDay(Integer.parseInt(cursor.getString(cursor.getColumnIndex(REMINDER_COLUMN_DAY))));
                reminders.setMonth(Integer.parseInt(cursor.getString(cursor.getColumnIndex(REMINDER_COLUMN_MONTH))));
                reminders.setYear(Integer.parseInt(cursor.getString(cursor.getColumnIndex(REMINDER_COLUMN_YEAR))));
                reminderList.add(reminders);
                cursor.moveToNext();
            }
            cursor.close();
            Log.d("Length in db","Length"+reminderList.size());
        }
        return reminderList;
    }
    }
