package majorproject.kone.in.collegebudy.activity;

/**
 * Created by batra on 25-01-2017.
 */

public class ReminderList {
        private String title, description, date;

        public ReminderList() {
        }

        public ReminderList(String title, String description, String date) {
            this.title = title;
            this.description = description;
            this.date = date;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }

