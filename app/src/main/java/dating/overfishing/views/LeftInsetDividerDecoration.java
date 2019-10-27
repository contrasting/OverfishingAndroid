package dating.overfishing.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class LeftInsetDividerDecoration extends RecyclerView.ItemDecoration {

    private static final int[] ATTRS = new int[]{android.R.attr.listDivider};

    private Drawable mDivider;
    private final Rect mBounds = new Rect();
    private int mLeftPixels;

    public LeftInsetDividerDecoration(Context context, float dp) {
        final TypedArray styledAttributes = context.obtainStyledAttributes(ATTRS);
        mDivider = styledAttributes.getDrawable(0);
        styledAttributes.recycle();
        mLeftPixels = (int) convertDpToPixel(dp, context);
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        if (parent.getLayoutManager() == null || mDivider == null) {
            return;
        }

        drawVertical(c, parent);
    }

    // cf. as in DividerItemDecoration.java
    private void drawVertical(Canvas canvas, RecyclerView parent) {
        canvas.save();

        final int right = parent.getWidth();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            parent.getDecoratedBoundsWithMargins(child, mBounds);
            final int bottom = mBounds.bottom + Math.round(child.getTranslationY());
            final int top = bottom - mDivider.getIntrinsicHeight();
            mDivider.setBounds(mLeftPixels, top, right, bottom);
            mDivider.draw(canvas);
        }
        canvas.restore();
    }

    public static float convertDpToPixel(float dp, Context context){
        return dp * ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT);
    }
}
