package majorproject.kone.in.collegebudy.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

import majorproject.kone.in.collegebudy.R;
import majorproject.kone.in.collegebudy.activity.Reminder;
import majorproject.kone.in.collegebudy.activity.ReminderList;

/**
 * Created by batra on 25-01-2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    public List<Reminder> reminderList;

    public RecyclerViewAdapter(List<Reminder> reminderList) {
        this.reminderList = reminderList;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public EditText title, description, date;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (EditText) itemView.findViewById(R.id.title);
            description = (EditText) itemView.findViewById(R.id.description);
            date = (EditText) itemView.findViewById(R.id.date);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_content,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        /*Reminder reminders=reminderList.get(position);
        holder.title.setText(reminders.getTitle());
        holder.date.setText(reminders.getDate());
        holder.description.setText(reminders.getDescription());*/

    }

    @Override
    public int getItemCount() {
        return reminderList.size();
    }


}
