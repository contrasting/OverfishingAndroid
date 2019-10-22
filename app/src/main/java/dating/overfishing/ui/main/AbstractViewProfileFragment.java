package dating.overfishing.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import dating.overfishing.R;
import dating.overfishing.data.UserProfile;
import me.relex.circleindicator.CircleIndicator;

public abstract class AbstractViewProfileFragment extends Fragment {

    protected MainViewModel mViewModel;
    protected TextView mName;
    protected TextView mDistance;
    protected TextView mAbout;
    protected TextView mSchool;
    private ViewPager mProfilePager;
    private CircleIndicator mIndicator;
    private ProfileImageAdapter mPagerAdapter;

    // observe: this is how to add views programmatically

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = getRootView(inflater, container);
        setViewPager(rootView);
        LinearLayout itemsContainer = rootView.findViewById(R.id.profile_items_container);
        mName = itemsContainer.findViewById(R.id.profile_name);
        mDistance = itemsContainer.findViewById(R.id.profile_distance);

        mSchool = (TextView) inflater.inflate(R.layout.item_profile_field, null);
        mSchool.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_school_24dp, 0, 0, 0);
        itemsContainer.addView(mSchool);

        mAbout = (TextView) inflater.inflate(R.layout.item_profile_field, null);
        mAbout.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_info_outline_24dp, 0, 0, 0);
        itemsContainer.addView(mAbout);

        return rootView;
    }

    protected void displayUserProfile(UserProfile profile) {

        if (profile.getProfileImages() != null) {
            mPagerAdapter.setImages(profile.getProfileImages());
        }

        if (profile.getName() != null && profile.getAge() != null) {
            mName.setText(profile.getName() + ", " + profile.getAge());
            mName.setVisibility(View.VISIBLE);
        }

        if (profile.getDistance() != null) {
            mDistance.setText(profile.getDistance() + " miles away");
            mDistance.setVisibility(View.VISIBLE);
        }

         if (profile.getAbout() != null) {
             mAbout.setText(profile.getAbout());
             mAbout.setVisibility(View.VISIBLE);
         }

        if (profile.getSchool() != null) {
            mSchool.setText(profile.getSchool());
            mSchool.setVisibility(View.VISIBLE);
        }
    }

    private void setViewPager(View rootView) {
        mProfilePager = rootView.findViewById(R.id.profile_image_pager);
        mIndicator = rootView.findViewById(R.id.profile_image_indicator);
        mPagerAdapter = new ProfileImageAdapter();
        mProfilePager.setAdapter(mPagerAdapter);
        mIndicator.setViewPager(mProfilePager);
        //image list is empty at the moment, so no indicators are generated. We need to notify indicator as well
        mPagerAdapter.registerDataSetObserver(mIndicator.getDataSetObserver());
    }

    protected abstract View getRootView(LayoutInflater inflater, ViewGroup container);
}
