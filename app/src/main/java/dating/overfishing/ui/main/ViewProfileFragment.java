package dating.overfishing.ui.main;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import dating.overfishing.R;

public class ViewProfileFragment extends Fragment {

    private MainViewModel mViewModel;

    public static ViewProfileFragment newInstance() {
        return new ViewProfileFragment();
    }

    // observe: this is how to add views programmatically

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.view_profile_fragment, container, false);
        LinearLayout itemsContainer = rootView.findViewById(R.id.profile_items_container);

        TextView profileName = (TextView) inflater.inflate(R.layout.item_profile_field, null);
        profileName.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_info_outline_24dp, 0, 0, 0);
        itemsContainer.addView(profileName);

        TextView school = (TextView) inflater.inflate(R.layout.item_profile_field, null);
        school.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_school_24dp, 0, 0, 0);
        itemsContainer.addView(school);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        // TODO: Use the ViewModel
    }

}
