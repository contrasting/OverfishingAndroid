package dating.overfishing.ui.main;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import dating.overfishing.R;
import dating.overfishing.data.UserProfile;
import me.relex.circleindicator.CircleIndicator;

public class ViewProfileFragment extends Fragment {

    private MainViewModel mViewModel;
    private TextView mName;
    private TextView mDistance;
    private TextView mAbout;
    private TextView mSchool;
    private ViewPager mProfilePager;
    private CircleIndicator mIndicator;
    private ProfileImageAdapter mPagerAdapter;

    public static ViewProfileFragment newInstance() {
        return new ViewProfileFragment();
    }

    // observe: this is how to add views programmatically

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.view_profile_fragment, container, false);
        setViewPager(rootView);
        LinearLayout itemsContainer = rootView.findViewById(R.id.profile_items_container);
        mName = itemsContainer.findViewById(R.id.profile_name);
        mDistance = itemsContainer.findViewById(R.id.profile_distance);

        mAbout = (TextView) inflater.inflate(R.layout.item_profile_field, null);
        mAbout.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_info_outline_24dp, 0, 0, 0);
        itemsContainer.addView(mAbout);

        mSchool = (TextView) inflater.inflate(R.layout.item_profile_field, null);
        mSchool.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_school_24dp, 0, 0, 0);
        itemsContainer.addView(mSchool);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        displayUserProfile();
    }

    private void displayUserProfile() {
        UserProfile profile = mViewModel.getLatestUserProfile();

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

}
