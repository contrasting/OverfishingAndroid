package dating.overfishing.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import dating.overfishing.R;
import dating.overfishing.data.UserProfile;

public class PinnedProfileFragment extends AbstractViewProfileFragment {

    public static PinnedProfileFragment newInstance() {
        return new PinnedProfileFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        UserProfile profile = mViewModel.getPinnedProfile();

        // should not be null
        if (profile != null) {
            displayUserProfile(profile);
        }
    }

    @Override
    protected View getRootView(LayoutInflater inflater, ViewGroup container) {
        View rootView = inflater.inflate(R.layout.fragment_pinned_profile, container, false);
        rootView.findViewById(R.id.view_profile_catch).setOnClickListener(v -> {
            // TODO
        });
        return rootView;
    }

}
