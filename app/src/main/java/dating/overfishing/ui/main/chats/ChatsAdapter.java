package dating.overfishing.ui.main.chats;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.List;

import dating.overfishing.R;
import dating.overfishing.data.Conversation;

public class ChatsAdapter extends RecyclerView.Adapter<ChatsAdapter.ChatViewHolder> {

    private List<Conversation> mConversations;

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_conversation, parent, false);
        // layout params appear to be unnecessary
        return new ChatViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        holder.bind(mConversations.get(position));
    }

    @Override
    public int getItemCount() {
        if (mConversations == null) return 0;
        return mConversations.size();
    }

    public void setConversations(List<Conversation> conversations) {
        mConversations = conversations;
        notifyDataSetChanged();
    }

    public class ChatViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImage;
        private TextView mName;
        private TextView mLastMessage;

        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);

            mImage = itemView.findViewById(R.id.item_conversation_image);
            mName = itemView.findViewById(R.id.item_conversation_name);
            mLastMessage = itemView.findViewById(R.id.item_conversation_last_message);
        }

        public void bind(Conversation conversation) {
            mName.setText(conversation.getOtherName());
            mLastMessage.setText(conversation.getLastMessage());
            Glide.with(itemView.getContext())
                    .load(conversation.getProfileImage())
                    .circleCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(mImage);
        }
    }
}
