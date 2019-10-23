package dating.overfishing.ui.main;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import dating.overfishing.R;
import io.apptik.widget.MultiSlider;

public class FiltersFragment extends Fragment {

    public static final String AGE_PREF_MAX = "agePrefMax";
    public static final String AGE_PREF_MIN = "agePrefMin";
    public static final String DISTANCE_PREF = "distancePref";
    public static final int AGE_MIN_DEF = 18;
    public static final int AGE_MAX_DEF = 30;
    public static final int DIST_DEF = 30;

    private SharedPreferences mPreferences;
    private int mAgeMin;
    private int mAgeMax;

    public static FiltersFragment newInstance() {
        return new FiltersFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_filters, container, false);
        LinearLayout itemsContainer = rootView.findViewById(R.id.filter_items_container);

        setAgePref(itemsContainer, inflater);
        setDistancePref(itemsContainer, inflater);

        return rootView;
    }

    private void setDistancePref(LinearLayout itemsContainer, LayoutInflater inflater) {

        CardView distContainer = getSeekBarPref(inflater, "Distance");
        TextView distValue = distContainer.findViewById(R.id.seekbar_pref_value);
        MultiSlider slider = distContainer.findViewById(R.id.seekbar_pref_slider);

        int distanceRange = mPreferences.getInt(DISTANCE_PREF, DIST_DEF);

        slider.setMax(100);
        slider.setMin(1);
        slider.setNumberOfThumbs(1);
        slider.getThumb(0).setValue(distanceRange);

        distValue.setText(distanceRange + " miles");

        slider.setOnThumbValueChangeListener((multiSlider, thumb, thumbIndex, value) -> {
            mPreferences.edit().putInt(DISTANCE_PREF, value).apply();
            distValue.setText(value + " miles");
        });

        itemsContainer.addView(distContainer);
    }

    private void setAgePref(LinearLayout itemsContainer, LayoutInflater inflater) {

        mAgeMin = mPreferences.getInt(AGE_PREF_MIN, AGE_MIN_DEF);
        mAgeMax = mPreferences.getInt(AGE_PREF_MAX, AGE_MAX_DEF);

        CardView ageContainer = getSeekBarPref(inflater, "Age");
        TextView ageValue = ageContainer.findViewById(R.id.seekbar_pref_value);
        MultiSlider slider = ageContainer.findViewById(R.id.seekbar_pref_slider);

        slider.setNumberOfThumbs(2);
        slider.setMax(60);
        slider.setMin(18, true);
        slider.getThumb(0).setValue(mAgeMin);
        slider.getThumb(1).setValue(mAgeMax);

        displayAgeRange(ageValue);

        slider.setOnThumbValueChangeListener(
                (multiSlider, thumb, thumbIndex, value) -> {
                    if (thumbIndex == 0) {
                        mAgeMin = value;
                        mPreferences.edit().putInt(AGE_PREF_MIN, value).apply();
                    } else if (thumbIndex == 1) {
                        mAgeMax = value;
                        mPreferences.edit().putInt(AGE_PREF_MAX, value).apply();
                    }

                    displayAgeRange(ageValue);
                }
        );

        itemsContainer.addView(ageContainer);
    }

    private CardView getSeekBarPref(LayoutInflater inflater, String name) {
        CardView ageContainer = (CardView) inflater.inflate(R.layout.item_filter_seekbar, null);
        ((TextView) ageContainer.findViewById(R.id.seekbar_pref_name)).setText(name);

        // margins must be set programmatically since root not passed
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0, 0, 0, (int) getResources().getDimension(R.dimen.padding_master));
        ageContainer.setLayoutParams(layoutParams);

        return ageContainer;
    }

    private void displayAgeRange(TextView ageText) {

        if (mAgeMax == 60) {
            ageText.setText(mAgeMin + " - 60+");
        } else {
            ageText.setText(mAgeMin + " - " + mAgeMax);
        }
    }
}
