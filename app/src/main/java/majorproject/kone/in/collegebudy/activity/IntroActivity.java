package majorproject.kone.in.collegebudy.activity;

/**
 * Created by kartikey on 02/09/16.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;


import majorproject.kone.in.collegebudy.R;
import majorproject.kone.in.collegebudy.Utility.SharedPreferencesSingleton;
import majorproject.kone.in.collegebudy.adapter.PagerAdapter;
import majorproject.kone.in.collegebudy.model.Student;


/**
 * Created by koneracks on 12/21/15.
 */
public class IntroActivity extends FragmentActivity {
    int selectedIndex = 0;
    boolean mPageEnd = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpagermain);
        closeKeyboard();
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpagerMain);
        final ImageView iv1 = (ImageView) findViewById(R.id.pi1iv);
        final ImageView iv2 = (ImageView) findViewById(R.id.pi2iv);
        final ImageView iv3 = (ImageView) findViewById(R.id.pi3iv);
        final PagerAdapter mpageradapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mpageradapter);
        ViewPager.OnPageChangeListener mListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
                // TODO Auto-generated method stub
                selectedIndex = arg0;
                switch (selectedIndex) {
                    case 0:
                        iv1.setAlpha((float) 1.0);
                        iv2.setAlpha((float) 0.5);
                        iv3.setAlpha((float) 0.5);
                        break;
                    case 1:
                        iv1.setAlpha((float) 0.5);
                        iv2.setAlpha((float) 1);
                        iv3.setAlpha((float) 0.5);
                        break;
                    case 2:
                        iv1.setAlpha((float) 0.5);
                        iv2.setAlpha((float) 0.5);
                        iv3.setAlpha((float) 1.0);
                        break;
                }
            }
            boolean callHappened;
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub
               Intent pageIntent;
                if (mPageEnd && arg0 == selectedIndex) {
                    if (arg0 == 2) {
                         pageIntent = new Intent(IntroActivity.this,
                                LoginActivity.class);

                        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                        startActivity(pageIntent);
                        finish();
                    }
                    mPageEnd = false;//To avoid multiple calls.
                    callHappened = true;
                } else {
                    mPageEnd = false;
                }

            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub
                if (selectedIndex == mpageradapter.getCount() - 1) {
                    mPageEnd = true;
                }
            }
        };
        viewPager.setOnPageChangeListener(mListener);
    }
    public void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(
                    Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
