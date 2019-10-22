package dating.overfishing.ui.main;

import android.content.Context;
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

    public static final String PROFILE = "profile";
    private String mUserId;

    public interface ProfileActionListener {
        void likeUser(String id);
        void passUser(String id);
        void pinUser(String id);
    }

    private ProfileActionListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (ProfileActionListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement " + getClass().getSimpleName());
        }
    }

    public static ViewProfileFragment newInstance(UserProfile profile) {
        ViewProfileFragment vpf = new ViewProfileFragment();
        Bundle args = new Bundle();
        args.putSerializable(PROFILE, profile);
        vpf.setArguments(args);
        return vpf;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        UserProfile profile = (UserProfile) getArguments().getSerializable(PROFILE);

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
            mListener.likeUser(mUserId);
        });

        rootView.findViewById(R.id.view_profile_no).setOnClickListener(v -> {
            mListener.passUser(mUserId);
        });

        rootView.findViewById(R.id.view_profile_pin).setOnClickListener(v -> {
            mListener.pinUser(mUserId);
        });

        return rootView;
    }
}
