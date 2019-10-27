package dating.overfishing.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dating.overfishing.R;
import dating.overfishing.data.Conversation;
import dating.overfishing.views.LeftInsetDividerDecoration;

public class ChatsFragment extends Fragment {

    private MainViewModel mViewModel;
    private ChatsAdapter mChatsAdapter;

    public static ChatsFragment newInstance() {
        return new ChatsFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);

        List<Conversation> chatsList = mViewModel.getChats();

        if (chatsList.isEmpty()) {
            // TODO
            // mNoChats.setVisibility(View.VISIBLE);
        } else {
            mChatsAdapter.setConversations(chatsList);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_chats, container, false);

        RecyclerView chatsRecycler = rootView.findViewById(R.id.chats_recycler);
        mChatsAdapter = new ChatsAdapter();
        chatsRecycler.setAdapter(mChatsAdapter);
        // what happens if no layout manager? then no views display lol
        chatsRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        // left inset: 60 + 16 * 2 = 92
        LeftInsetDividerDecoration decoration = new LeftInsetDividerDecoration(getContext(), 92);
        chatsRecycler.addItemDecoration(decoration);

        return rootView;
    }
}
