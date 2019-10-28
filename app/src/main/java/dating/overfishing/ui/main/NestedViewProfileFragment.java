package dating.overfishing.ui.main;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.palette.graphics.Palette;

import dating.overfishing.R;
import dating.overfishing.data.UserProfile;

public class NestedViewProfileFragment extends AbstractViewProfileFragment {

    /**
     *  This class is intended to be nested inside an Activity with passed args
     */

    private Drawable mBackArrow;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        UserProfile profile = null;

        if (getArguments() != null) {
            profile = (UserProfile) getArguments().getSerializable(AbstractViewProfileFragment.PROFILE);
        }

        if (profile != null) {
            displayUserProfile(profile);
        }
    }

    public static NestedViewProfileFragment newInstance(UserProfile profile) {
        Bundle args = new Bundle();
        args.putSerializable(AbstractViewProfileFragment.PROFILE, profile);
        NestedViewProfileFragment vpf = new NestedViewProfileFragment();
        vpf.setArguments(args);
        return vpf;
    }

    @Override
    protected View getRootView(LayoutInflater inflater, ViewGroup container) {
        View rootView = inflater.inflate(R.layout.content_profile, container, false);
        setToolbar(rootView);
        return rootView;
    }

    protected void setToolbar(View rootView) {
        Toolbar toolbar = rootView.findViewById(R.id.view_profile_toolbar);
        mBackArrow = getResources().getDrawable(R.drawable.ic_arrow_back_24dp);
        toolbar.setNavigationIcon(mBackArrow);
        toolbar.setNavigationOnClickListener(v -> getActivity().finish());
    }

    @Override
    protected void setPalette(Palette palette) {
        mBackArrow.setTint(palette.getVibrantColor(Color.WHITE));
    }
}
