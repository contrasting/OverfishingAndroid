package dating.overfishing.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

import dating.overfishing.R;
import dating.overfishing.data.UserProfile;

public class ViewProfileFragment extends AbstractViewProfileFragment {

    private String mUserId;

    public static AbstractViewProfileFragment newInstance() {
        return new ViewProfileFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // note that we scope the ViewModel to the activity, not to this fragment
        mViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        UserProfile profile = mViewModel.getLatestUserProfile();

        if (profile != null) {
            mUserId = profile.getId();
            displayUserProfile(profile);
        }
    }

    @Override
    protected View getRootView(LayoutInflater inflater, ViewGroup container) {
        View rootView = inflater.inflate(R.layout.fragment_view_profile, container, false);

        rootView.findViewById(R.id.view_profile_catch).setOnClickListener(v -> {
            mViewModel.likeUser(mUserId);
            nextProfile();
        });

        rootView.findViewById(R.id.view_profile_no).setOnClickListener(v -> {
            mViewModel.passUser(mUserId);
            nextProfile();
        });

        rootView.findViewById(R.id.view_profile_pin).setOnClickListener(v -> {
            mViewModel.pinUser(mUserId);
            nextProfile();
        });

        return rootView;
    }

    private void nextProfile() {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, newInstance())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
}
