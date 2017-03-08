package majorproject.kone.in.collegebudy.activity;

/**
 * Created by batra on 25-01-2017.
 */

public class ReminderList {
    private String title, description;
    private int day, month, year;

    public ReminderList() {
    }

    public ReminderList(String title, int day, int month, int year) {
        this.title = title;
        this.description = description;
        this.day = day;
        this.month=month;
        this.year=year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}

