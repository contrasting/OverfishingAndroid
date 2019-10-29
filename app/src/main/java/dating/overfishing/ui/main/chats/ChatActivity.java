package dating.overfishing.ui.main.chats;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import dating.overfishing.R;
import dating.overfishing.ViewProfileActivity;
import dating.overfishing.data.Conversation;

import static dating.overfishing.ui.main.AbstractViewProfileFragment.PROFILE;
import static dating.overfishing.ui.main.chats.ChatsAdapter.CONVERSATION;
import static dating.overfishing.views.LeftInsetDividerDecoration.convertDpToPixel;

public class ChatActivity extends AppCompatActivity {

    private ChatViewModel mViewModel;
    private RecyclerView mRecyclerView;
    private MessageAdapter mMessageAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private EditText mMessageText;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Conversation conversation = (Conversation) getIntent().getSerializableExtra(CONVERSATION);

        setActionBar(conversation);

        mViewModel = ViewModelProviders.of(this).get(ChatViewModel.class);

        mRecyclerView = findViewById(R.id.messages_recycler);
        mMessageText = findViewById(R.id.message);
        findViewById(R.id.send_button).setOnClickListener(v -> sendMessage());

        mLinearLayoutManager = new LinearLayoutManager(this);
        mMessageAdapter = new MessageAdapter(conversation.getProfileImage());
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mMessageAdapter);

        // refocus messages, based on solution from StackOverFlow...
        mRecyclerView.addOnLayoutChangeListener((view, i, i1, i2, i3, i4, i5, i6, i7) -> {
            if (i3 < i7  && mLinearLayoutManager.findLastVisibleItemPosition() >
                    mLinearLayoutManager.getItemCount() - 10) { // ten child count seems reasonable

                // HAS to be in post Runnable or won't run
                mRecyclerView.post(() ->
                        mLinearLayoutManager.scrollToPosition(mMessageAdapter.getItemCount() - 1));
            }
        });

        mViewModel.getIsTypingLiveData().observe(this, isTyping -> {
            if (isTyping) {
                getSupportActionBar().setSubtitle("    Typing...");
            } else {
                // https://stackoverflow.com/questions/40776116/removing-toolbar-subtitle
                getSupportActionBar().setSubtitle(null);
            }
        });

        mViewModel.mMessageData.observe(this, mMessageAdapter::submitList);
    }

    private void sendMessage() {
        String message = mMessageText.getText().toString();
        // clear the chat box first, otherwise will trigger listener after send
        mMessageText.setText(null);
        mViewModel.sendMessage(message);
    }

    private void setActionBar(Conversation conversation) {
        if (conversation != null) {
            Toolbar toolbar = findViewById(R.id.chat_toolbar);
            // just give it a few spaces to appear properly lol
            toolbar.setTitle("   " + conversation.getOtherName());
            toolbar.setOnClickListener(v -> {
                Intent i = new Intent(this, ViewProfileActivity.class);
                i.putExtra(PROFILE, conversation.getOtherProfile());
                startActivity(i);
            });
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            int i = (int) convertDpToPixel(40, this);

            Glide.with(this)
                    .asDrawable()
                    .circleCrop()
                    .load(conversation.getProfileImage())
                    .into(new CustomTarget<Drawable>(i, i) {
                        @Override
                        public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                            getSupportActionBar().setIcon(resource);
                        }

                        @Override
                        public void onLoadCleared(@Nullable Drawable placeholder) {

                        }
                    });

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true; // consume it
        }
        return super.onOptionsItemSelected(item);
    }
}
