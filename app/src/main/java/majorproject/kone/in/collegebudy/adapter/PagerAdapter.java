package majorproject.kone.in.collegebudy.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import majorproject.kone.in.collegebudy.fragment.ViewPagerPageFragment;

/**
 * Created by kartikey on 02/09/16.
 */
public class PagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;/*
    private String tabTitles[] = new String[]{"Top Result", "User Rating"};
*/

    public PagerAdapter(android.support.v4.app.FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        return ViewPagerPageFragment.newInstance(position + 1);
    }
}