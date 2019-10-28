package dating.overfishing.ui.main.chats;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;

import dating.overfishing.R;
import dating.overfishing.ViewProfileActivity;
import dating.overfishing.data.Conversation;
import dating.overfishing.ui.main.MainViewModel;

import static dating.overfishing.ui.main.chats.ChatsAdapter.CONVERSATION;
import static dating.overfishing.ui.main.AbstractViewProfileFragment.PROFILE;
import static dating.overfishing.views.LeftInsetDividerDecoration.convertDpToPixel;

public class ChatActivity extends AppCompatActivity {

    private MainViewModel mViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        Conversation conversation = (Conversation) getIntent().getSerializableExtra(CONVERSATION);

        setActionBar(conversation);

    }

    private void setActionBar(Conversation conversation) {
        if (conversation != null) {
            Toolbar toolbar = findViewById(R.id.chat_toolbar);
            // just give it a few spaces to appear properly lol
            toolbar.setTitle("   " + conversation.getOtherName());
            toolbar.setOnClickListener(v -> {
                Intent i = new Intent(this, ViewProfileActivity.class);
                i.putExtra(PROFILE, mViewModel.getProfileFromId(conversation.getOtherID()));
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
