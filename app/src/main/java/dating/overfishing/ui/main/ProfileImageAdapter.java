package dating.overfishing.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;

import java.util.List;

import dating.overfishing.R;

public class ProfileImageAdapter extends PagerAdapter {

    private List<String> mImages;

    @Override
    public int getCount() {
        if (mImages == null) {
            return 0;
        }
        return mImages.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        // note this returns the root view
        View layoutView = LayoutInflater.from(container.getContext()).inflate(R.layout.item_profile_image, container, false);
        ImageView img = layoutView.findViewById(R.id.profile_image);

        Glide.with(container.getContext())
                .load(mImages.get(position))
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(img);

        container.addView(layoutView); // is this necessary? edit: yes

        return layoutView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public void setImages(List<String> images) {
        mImages = images;
        notifyDataSetChanged();
    }

}
