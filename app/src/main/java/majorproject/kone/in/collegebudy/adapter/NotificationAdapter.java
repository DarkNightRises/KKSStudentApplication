package majorproject.kone.in.collegebudy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by kartikey on 23/12/16.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder>{
    private Context mContext;
    public NotificationAdapter(Context mContext){
        this.mContext = mContext;
    }
    @Override
    public NotificationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(NotificationViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder {
        public NotificationViewHolder(View itemView) {
            super(itemView);
        }
    }


}
