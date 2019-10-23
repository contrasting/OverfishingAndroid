package dating.overfishing.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import dating.overfishing.R;
import dating.overfishing.data.UserProfile;

public class OwnProfileFragment extends AbstractViewProfileFragment {

    public static OwnProfileFragment newInstance() {
        return new OwnProfileFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        UserProfile profile = mViewModel.getOwnProfile();

        // should not be null
        if (profile != null) {
            displayUserProfile(profile);
        }
    }

    @Override
    protected View getRootView(LayoutInflater inflater, ViewGroup container) {
        View rootView = inflater.inflate(R.layout.fragment_own_profile, container, false);

        rootView.findViewById(R.id.edit_profile_button).setOnClickListener(v -> {

        });

        return rootView;
    }
}
