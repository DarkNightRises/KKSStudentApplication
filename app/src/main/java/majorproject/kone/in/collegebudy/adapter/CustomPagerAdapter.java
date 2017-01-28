package majorproject.kone.in.collegebudy.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.res.ResourcesCompat;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;

import majorproject.kone.in.collegebudy.R;
import majorproject.kone.in.collegebudy.fragment.HomeFragment;
import majorproject.kone.in.collegebudy.fragment.PersonalFragment;

/**
 * Created by kartikey on 23/12/16.
 */

public class CustomPagerAdapter extends FragmentPagerAdapter{
    private Context mContext;
    private Fragment fragment;
    private String tabTitles[] = new String[] { "Attendance", "Profile"};
    public CustomPagerAdapter(FragmentManager fm,Context mContext) {
        super(fm);
        this.mContext = mContext;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position+1){
            case 1:
                fragment = new HomeFragment();
                break;
            case 2:
                fragment = new PersonalFragment();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];

    }
}
