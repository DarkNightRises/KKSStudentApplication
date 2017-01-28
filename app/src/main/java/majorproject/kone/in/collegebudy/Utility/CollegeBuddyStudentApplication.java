package majorproject.kone.in.collegebudy.Utility;

import android.app.Application;
import android.support.multidex.MultiDexApplication;

import dalvik.system.DexClassLoader;

/**
 * Created by kartikey on 30/12/16.
 */

public class CollegeBuddyStudentApplication extends MultiDexApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        initialiseSingletons();
    }

    private void initialiseSingletons() {
        SharedPreferencesSingleton.initSharedPreference(this);
    }
}
