package dating.overfishing.ui.main;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.palette.graphics.Palette;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.github.florent37.glidepalette.GlidePalette;

import java.util.List;

import dating.overfishing.R;

public class ProfileImageAdapter extends PagerAdapter {

    public interface PaletteListener {
        void onPaletteLoaded(Palette palette);
    }

    private PaletteListener mPaletteListener;

    public ProfileImageAdapter(PaletteListener paletteListener) {
        mPaletteListener = paletteListener;
    }

    private List<String> mImages;
    private Palette[] mPalettes;

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
        ProgressBar progress = layoutView.findViewById(R.id.profile_image_progress);

        Glide.with(container.getContext())
                .load(mImages.get(position))
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .addListener(GlidePalette.with(mImages.get(position))
                        .intoCallBack(palette -> {
                            mPalettes[position] = palette;
                            if (position == 0) mPaletteListener.onPaletteLoaded(palette);
                        }))
                .addListener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progress.setVisibility(View.GONE);
                        return false;
                    }
                })
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
        mPalettes = new Palette[mImages.size()];
        notifyDataSetChanged();
    }

    public Palette getPaletteAt(int position) {
        return mPalettes[position];
    }

}
