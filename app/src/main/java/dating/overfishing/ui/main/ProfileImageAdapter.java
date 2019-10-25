package dating.overfishing.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.palette.graphics.Palette;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
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

        Glide.with(container.getContext())
                .load(mImages.get(position))
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .listener(GlidePalette.with(mImages.get(position))
                        .intoCallBack(palette -> {
                            mPalettes[position] = palette;
                            if (position == 0) mPaletteListener.onPaletteLoaded(palette);
                        }))
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
