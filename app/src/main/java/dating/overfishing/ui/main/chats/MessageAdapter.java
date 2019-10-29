package dating.overfishing.ui.main.chats;

import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import dating.overfishing.R;
import dating.overfishing.data.Message;

public class MessageAdapter extends PagedListAdapter<Message, MessageAdapter.MessageViewHolder> {

    // cf.
    // https://developer.android.com/topic/libraries/architecture/paging/ui

    private String mOtherProfileImage;

    protected MessageAdapter(String otherProfileImage) {
        super(DIFF_CALLBACK);
        mOtherProfileImage = otherProfileImage;
    }

    public static final int VIEW_TYPE_MESSAGE_SENT = 1;
    public static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;

    @Override
    public int getItemViewType(int position) {
        if (getItem(position).isMe()) {
            return VIEW_TYPE_MESSAGE_SENT;
        } else {
            return VIEW_TYPE_MESSAGE_RECEIVED;
        }
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v;

        switch (viewType) {
            case VIEW_TYPE_MESSAGE_SENT:
                v = layoutInflater.inflate(R.layout.item_message_sent, parent, false);
                return new MessageViewHolder(v);
            case VIEW_TYPE_MESSAGE_RECEIVED:
                v = layoutInflater.inflate(R.layout.item_message_received, parent, false);
                return new MessageViewHolder(v);
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        holder.bind(position);
    }

    protected class MessageViewHolder extends RecyclerView.ViewHolder {

        private TextView timeText;
        private TextView messageText;
        private ImageView profileImage;

        MessageViewHolder(@NonNull View itemView) {
            super(itemView);
            timeText = itemView.findViewById(R.id.text_message_time);
            messageText = itemView.findViewById(R.id.text_message_body);
            profileImage = itemView.findViewById(R.id.message_profile_image);
        }

        void bind(int position) {
            Message message = getItem(position);
            if (message != null) {
                messageText.setText(message.getMessage());
                timeText.setText(DateUtils.formatDateTime(itemView.getContext(), message.getTimestamp(), DateUtils.FORMAT_SHOW_TIME));
                setProfileImage(message);
            }
        }

        void setProfileImage(Message message) {
            // only load if received, otherwise profileImage view is null
            if (!message.isMe()) {
                Glide.with(itemView.getContext())
                        .load(mOtherProfileImage)
                        .circleCrop()
                        .into(profileImage);
            }
        }
    }

    private static DiffUtil.ItemCallback<Message> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Message>() {
                @Override
                public boolean areItemsTheSame(@NonNull Message oldItem, @NonNull Message newItem) {
                    return oldItem.getKey().equals(newItem.getKey());
                }

                @Override
                public boolean areContentsTheSame(@NonNull Message oldItem, @NonNull Message newItem) {
                    return oldItem.getMessage().equals(newItem.getMessage());
                }
            };
}
