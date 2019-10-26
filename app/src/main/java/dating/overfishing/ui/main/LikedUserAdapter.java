package dating.overfishing.ui.main;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.github.florent37.glidepalette.BitmapPalette;
import com.github.florent37.glidepalette.GlidePalette;

import java.util.List;

import dating.overfishing.R;
import dating.overfishing.data.UserProfile;

public class LikedUserAdapter extends RecyclerView.Adapter<LikedUserAdapter.LikedViewHolder> {

    private List<UserProfile> mUserProfiles;

    @NonNull
    @Override
    public LikedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_liked_user, parent, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        v.setLayoutParams(lp);
        return new LikedViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LikedViewHolder holder, int position) {
        holder.bind(mUserProfiles.get(position));
    }

    @Override
    public int getItemCount() {
        if (mUserProfiles == null) return 0;
        return mUserProfiles.size();
    }

    public void setUserProfiles(List<UserProfile> userProfiles) {
        mUserProfiles = userProfiles;
        notifyDataSetChanged();
    }

    protected class LikedViewHolder extends RecyclerView.ViewHolder {

        private TextView mName;
        private ImageView mImage;

        public LikedViewHolder(@NonNull View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.item_liked_name);
            mImage = itemView.findViewById(R.id.item_liked_image);
        }

        public void bind(UserProfile profile) {
            mName.setText(profile.getName() + ", " + profile.getAge());
            Glide.with(itemView.getContext())
                    .load(profile.getProfileImages().get(0))
                    .listener(GlidePalette.with(profile.getProfileImages().get(0))
                            .intoCallBack(palette -> {
                                if (palette != null) {
                                    mName.setTextColor(palette.getVibrantColor(Color.WHITE));
                                }
                            }))
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(mImage);
        }
    }
}