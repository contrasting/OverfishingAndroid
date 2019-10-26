package dating.overfishing.ui.main;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.palette.graphics.Palette;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.appbar.CollapsingToolbarLayout;

import dating.overfishing.R;
import dating.overfishing.data.UserProfile;
import me.relex.circleindicator.CircleIndicator;

public abstract class AbstractViewProfileFragment extends Fragment implements ProfileImageAdapter.PaletteListener {

    public static final String PROFILE = "profile";
    protected MainViewModel mViewModel;
    protected TextView mDistance;
    protected TextView mAbout;
    protected TextView mSchool;
    protected CollapsingToolbarLayout mToolbarLayout;
    private ViewPager mProfilePager;
    private CircleIndicator mIndicator;
    private ProfileImageAdapter mPagerAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // note scope ViewModel to activity instead of this
        mViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
    }

    // observe: this is how to add views programmatically

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = getRootView(inflater, container);
        setViewPager(rootView);
        setToolbarLayout(rootView);

        LinearLayout itemsContainer = rootView.findViewById(R.id.profile_items_container);
        mAbout = rootView.findViewById(R.id.profile_about);

        mDistance = addField(R.drawable.ic_location_on_24dp, inflater, itemsContainer);
        mSchool = addField(R.drawable.ic_school_24dp, inflater, itemsContainer);

        return rootView;
    }

    private static TextView addField(int res, @NonNull LayoutInflater inflater, LinearLayout itemsContainer) {
        TextView textView = (TextView) inflater.inflate(R.layout.item_profile_field, null);
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(res, 0, 0, 0);
        itemsContainer.addView(textView);
        return textView;
    }

    protected void displayUserProfile(UserProfile profile) {

        if (profile.getProfileImages() != null) {
            mPagerAdapter.setImages(profile.getProfileImages());
        }

        if (profile.getName() != null && profile.getAge() != null) {
            mToolbarLayout.setTitle(profile.getName() + ", " + profile.getAge());
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

    private void setToolbarLayout(View rootView) {
        mToolbarLayout = rootView.findViewById(R.id.view_profile_toolbar_layout);
        // mToolbarLayout.setExpandedTitleColor(getThemeAttribute(R.attr.colorPrimary, getContext()));
        mToolbarLayout.setExpandedTitleTypeface(Typeface.DEFAULT_BOLD);
        mToolbarLayout.setCollapsedTitleTextColor(getThemeAttribute(R.attr.colorPrimaryDark, getContext()));
    }

    private void setViewPager(View rootView) {
        mProfilePager = rootView.findViewById(R.id.profile_image_pager);
        mIndicator = rootView.findViewById(R.id.profile_image_indicator);
        mPagerAdapter = new ProfileImageAdapter(this);
        mProfilePager.setAdapter(mPagerAdapter);
        mIndicator.setViewPager(mProfilePager);
        //image list is empty at the moment, so no indicators are generated. We need to notify indicator as well
        mPagerAdapter.registerDataSetObserver(mIndicator.getDataSetObserver());
        mProfilePager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                if (mPagerAdapter.getPaletteAt(position) != null) {
                    mToolbarLayout.setExpandedTitleColor(mPagerAdapter.getPaletteAt(position).getVibrantColor(Color.WHITE));
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });
    }

    protected abstract View getRootView(LayoutInflater inflater, ViewGroup container);

    public static int getThemeAttribute(int attr, final Context context) {
        final TypedValue value = new TypedValue();
        context.getTheme().resolveAttribute(attr, value, true);
        return value.data;
    }

    @Override
    public void onPaletteLoaded(Palette palette) {
        mToolbarLayout.setExpandedTitleColor(palette.getVibrantColor(Color.WHITE));
    }
}
