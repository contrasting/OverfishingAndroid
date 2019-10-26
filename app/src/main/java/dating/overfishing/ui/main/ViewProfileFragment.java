package dating.overfishing.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import dating.overfishing.R;
import dating.overfishing.data.UserProfile;

public class ViewProfileFragment extends AbstractViewProfileFragment {

    private String mUserId;

    public static ViewProfileFragment newInstance() {
        return new ViewProfileFragment();
    }

    public static ViewProfileFragment newInstance(UserProfile profile) {
        Bundle args = new Bundle();
        args.putSerializable(PROFILE, profile);
        ViewProfileFragment vpf = new ViewProfileFragment();
        vpf.setArguments(args);
        return vpf;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        UserProfile profile;

        if (getArguments() != null) {
            profile = (UserProfile) getArguments().getSerializable(PROFILE);
        } else {
            // if not passed from bundle then get from ViewModel
            profile = mViewModel.getCurrentProfile().getValue();
        }

        // should not be null
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
        });

        rootView.findViewById(R.id.view_profile_no).setOnClickListener(v -> {
            mViewModel.passUser(mUserId);
        });

        rootView.findViewById(R.id.view_profile_pin).setOnClickListener(v -> {
            mViewModel.pinUser(mUserId);
        });

        return rootView;
    }
}
