package dating.overfishing.ui.main;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;
import androidx.palette.graphics.Palette;

import dating.overfishing.R;
import dating.overfishing.data.UserProfile;

public class NestedViewProfileFragment extends ViewProfileFragment {

    private Drawable mBackArrow;

    public static NestedViewProfileFragment newInstance(UserProfile profile) {
        Bundle args = new Bundle();
        args.putSerializable(PROFILE, profile);
        NestedViewProfileFragment vpf = new NestedViewProfileFragment();
        vpf.setArguments(args);
        return vpf;
    }

    @Override
    protected View getRootView(LayoutInflater inflater, ViewGroup container) {
        View rootView = super.getRootView(inflater, container);
        Toolbar toolbar = rootView.findViewById(R.id.view_profile_toolbar);
        mBackArrow = getResources().getDrawable(R.drawable.ic_arrow_back_24dp);
        toolbar.setNavigationIcon(mBackArrow);
        toolbar.setNavigationOnClickListener(v -> getActivity().finish());

        // TODO handle button presses

        return rootView;
    }

    @Override
    protected void setPalette(Palette palette) {
        mBackArrow.setTint(palette.getVibrantColor(Color.WHITE));
    }
}
