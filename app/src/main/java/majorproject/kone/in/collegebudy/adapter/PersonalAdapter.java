package majorproject.kone.in.collegebudy.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by kartikey on 23/12/16.
 */

public class PersonalAdapter extends RecyclerView.Adapter<PersonalAdapter.PersonalCardHolder>{
    private Context mContext;
    public PersonalAdapter(Context mContext){
        this.mContext = mContext;
    }
    @Override
    public PersonalCardHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(PersonalCardHolder holder, int position) {

    }
    @Override
    public int getItemCount() {
        return 0;
    }

    //View Holder for Personal Recyler View
    public class PersonalCardHolder extends RecyclerView.ViewHolder{
        public TextView tv;
        public PersonalCardHolder(View itemView) {
            super(itemView);
        }
    }
}
