package dating.overfishing.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import dating.overfishing.R;

public class LikedUserFragment extends Fragment {

    private MainViewModel mViewModel;
    private LikedUserAdapter mAdapter;

    public static LikedUserFragment newInstance() {
        return new LikedUserFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        mAdapter.setUserProfiles(mViewModel.getLikedUsers());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_liked_user, container, false);
        RecyclerView likedRecycler = rootView.findViewById(R.id.liked_recycler);
        mAdapter = new LikedUserAdapter();
        likedRecycler.setAdapter(mAdapter);
        likedRecycler.setLayoutManager(new GridLayoutManager(getContext(), 2));

        return rootView;
    }
}
