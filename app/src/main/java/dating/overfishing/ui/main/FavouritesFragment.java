package dating.overfishing.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import dating.overfishing.R;

public class FavouritesFragment extends Fragment {

    public static FavouritesFragment newInstance() {
        return new FavouritesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_favourites, container, false);
        TabLayout tabLayout = rootView.findViewById(R.id.favourites_tabs);
        ViewPager viewpager = rootView.findViewById(R.id.favourites_pager);
        tabLayout.setupWithViewPager(viewpager);

        // note must be ChildFragmentManager, or will be blank on return
        // https://stackoverflow.com/questions/42671729/viewpager-from-fragment-returns-blank
        viewpager.setAdapter(new FavouritesAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT));

        return rootView;
    }

    private static class FavouritesAdapter extends FragmentPagerAdapter {

        public FavouritesAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            Fragment fragment;

            if (position == 0) {
                fragment = LikedUserFragment.newInstance();
            } else {
                fragment = PinnedProfileFragment.newInstance();
            }

            return fragment;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return "Liked You";
            } else {
                return "Pinned";
            }
        }
    }
}
