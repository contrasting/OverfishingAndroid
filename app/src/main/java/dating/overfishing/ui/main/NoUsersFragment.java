package dating.overfishing.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import dating.overfishing.R;

public class NoUsersFragment extends Fragment {

    private MainViewModel mViewModel;

    public static NoUsersFragment newInstance() {
        return new NoUsersFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_no_users, container, false);

        rootView.findViewById(R.id.refresh_profiles_button).setOnClickListener(v -> {
            mViewModel.refresh();
        });

        return rootView;
    }
}
