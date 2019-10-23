package dating.overfishing.data;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import org.json.JSONObject;

import static dating.overfishing.ui.main.FiltersFragment.*;

public class Filters {

    private SharedPreferences mPreferences;

    private int mMinAge;
    private int mMaxAge;
    private int mMaxDist;

    public Filters(Context context) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        setFilters();
    }

    private void setFilters() {
        mMinAge = mPreferences.getInt(AGE_PREF_MIN, AGE_MIN_DEF);
        mMaxAge = mPreferences.getInt(AGE_PREF_MAX, AGE_MAX_DEF);
        mMaxDist = mPreferences.getInt(DISTANCE_PREF, DIST_DEF);
    }

    public JSONObject getJSONfromFilters() {
        // TODO
        return null;
    }
}
