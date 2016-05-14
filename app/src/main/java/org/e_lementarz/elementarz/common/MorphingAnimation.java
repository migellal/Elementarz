package org.e_lementarz.elementarz.common;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ImageView;

import com.wnafee.vector.compat.ResourcesCompat;

import org.e_lementarz.elementarz.R;

/**
 * Created by micha on 06.04.2016.
 */
public class MorphingAnimation {

    private Context context;
    private FloatingActionButton floatingActionButton;
    private ImageView imageView;
    private int previousAnim = -1;

    public MorphingAnimation(Context context, FloatingActionButton floatingActionButton) {
        this.context = context;
        this.floatingActionButton = floatingActionButton;
    }

    public MorphingAnimation(Context context, ImageView imageView) {
        this.context = context;
        this.imageView = imageView;
    }

    public MorphingAnimation(Context context, FloatingActionButton floatingActionButton, ImageView imageView) {
        this.context = context;
        this.floatingActionButton = floatingActionButton;
        this.imageView = imageView;
    }

    public void animImageView(int resId) {
        final Animatable animatable = (Animatable) ResourcesCompat.getDrawable(context, resId);
        imageView.setImageDrawable((Drawable) animatable);
        animatable.start();
    }

    public void animFab(int resId, int color) {
        if(currentShapeIsDifferent(resId)) {
            animFab(resId);
            floatingActionButton.setBackgroundTintList(context.getResources().getColorStateList(color));
            previousAnim=resId;
        }
    }

    public void animFab(int resId, int color, boolean onlyChange) {
        if((onlyChange&&currentShapeIsDifferent(resId)) || (!onlyChange)) {
            animFab(resId);
            floatingActionButton.setBackgroundTintList(context.getResources().getColorStateList(color));
        }
    }

    private void animFab(int resId) {
        final Animatable icon = (Animatable) ResourcesCompat.getDrawable(context, resId);
        floatingActionButton.setImageDrawable((Drawable) icon);
        icon.start();
    }

    private boolean currentShapeIsDifferent(int resId)
    {
        if(resId==previousAnim)
            return false;
        else
        {
            int pAnim = previousAnim;
            previousAnim = resId;
            if(resId==R.drawable.ic_line_to_undone&&pAnim==R.drawable.ic_done_to_undone)
                return false;
            else if(pAnim==R.drawable.ic_line_to_undone&&resId==R.drawable.ic_done_to_undone)
                return false;
            else if(resId==R.drawable.ic_line_to_done&&pAnim==R.drawable.ic_undone_to_done)
                return false;
            else if(pAnim==R.drawable.ic_line_to_done&&resId==R.drawable.ic_undone_to_done)
                return false;
            else
                return true;
        }
    }
}
