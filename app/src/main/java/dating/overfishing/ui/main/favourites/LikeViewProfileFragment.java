package dating.overfishing.ui.main.favourites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dating.overfishing.R;
import dating.overfishing.data.UserProfile;
import dating.overfishing.ui.main.AbstractViewProfileFragment;
import dating.overfishing.ui.main.NestedViewProfileFragment;

public class LikeViewProfileFragment extends NestedViewProfileFragment {

    public static LikeViewProfileFragment newInstance(UserProfile profile) {
        Bundle args = new Bundle();
        args.putSerializable(AbstractViewProfileFragment.PROFILE, profile);
        LikeViewProfileFragment vpf = new LikeViewProfileFragment();
        vpf.setArguments(args);
        return vpf;
    }

    @Override
    protected View getRootView(LayoutInflater inflater, ViewGroup container) {
        View rootView = inflater.inflate(R.layout.fragment_pinned_profile, container, false);
        setToolbar(rootView);

        // TODO handle button presses

        return rootView;
    }
}
