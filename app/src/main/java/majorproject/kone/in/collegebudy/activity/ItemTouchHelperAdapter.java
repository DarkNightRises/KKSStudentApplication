package majorproject.kone.in.collegebudy.activity;

/**
 * Created by batra on 29-03-2017.
 */

public interface ItemTouchHelperAdapter {

    boolean onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}