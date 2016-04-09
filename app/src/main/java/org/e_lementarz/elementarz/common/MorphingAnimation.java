package org.e_lementarz.elementarz.common;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.support.design.widget.FloatingActionButton;

import com.wnafee.vector.compat.ResourcesCompat;

import org.e_lementarz.elementarz.R;

/**
 * Created by micha on 06.04.2016.
 */
public class MorphingAnimation {

    private Context context;
    private FloatingActionButton floatingActionButton;

    public MorphingAnimation(Context context, FloatingActionButton floatingActionButton) {
        this.context = context;
        this.floatingActionButton = floatingActionButton;
    }

    public void animFab(int resId) {
        final Animatable icon = (Animatable) ResourcesCompat.getDrawable(context, resId);
        floatingActionButton.setImageDrawable((Drawable) icon);
        icon.start();
        floatingActionButton.setBackgroundTintList(context.getResources().getColorStateList(R.color.colorSuccessGreen));
    }

    public void animFab(int resId, boolean success) {
        final Animatable icon = (Animatable) ResourcesCompat.getDrawable(context, resId);
        floatingActionButton.setImageDrawable((Drawable) icon);
        icon.start();
        if(success)
            floatingActionButton.setBackgroundTintList(context.getResources().getColorStateList(R.color.colorSuccessGreen));
        else
            floatingActionButton.setBackgroundTintList(context.getResources().getColorStateList(R.color.colorFailureRed));
    }
}
