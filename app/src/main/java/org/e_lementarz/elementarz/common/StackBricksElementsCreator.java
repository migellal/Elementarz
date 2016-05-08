package org.e_lementarz.elementarz.common;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.widget.TextView;

import org.e_lementarz.elementarz.R;
import org.e_lementarz.elementarz.util.ReverseInterpolator;

/**
 * Created by micha on 21.03.2016.
 */
public class StackBricksElementsCreator {


    public View[] createBricksStack(View container) {
        View bricks1 = container.findViewById(R.id.brick1V);
        View bricks2 = container.findViewById(R.id.brick2V);
        View bricks3 = container.findViewById(R.id.brick3V);
        View bricks4 = container.findViewById(R.id.brick4V);
        View bricks5 = container.findViewById(R.id.brick5V);
        View bricks6 = container.findViewById(R.id.brick6V);
        View bricks7 = container.findViewById(R.id.brick7V);
        View bricks8 = container.findViewById(R.id.brick8V);
        View bricks9 = container.findViewById(R.id.brick9V);
        View bricks10 = container.findViewById(R.id.brick10V);
        return new View[]{bricks1, bricks2, bricks3, bricks4, bricks5, bricks6,
                bricks7, bricks8, bricks9, bricks10};
    }

    public View[] createBricksStack(View container, int height) {
        View[] v = createBricksStack(container);
        for (int i = 0; i < height; i++)
            v[i].setAlpha(1);
        return v;
    }

    public View[] createContainers(Activity rootContainer) {
        final View viewStack1 = rootContainer.findViewById(R.id.includeOrderStack1);
        final View viewStack2 = rootContainer.findViewById(R.id.includeOrderStack2);
        final View viewStack3 = rootContainer.findViewById(R.id.includeOrderStack3);
        final View viewStack4 = rootContainer.findViewById(R.id.includeOrderStack4);
        final View viewStack5 = rootContainer.findViewById(R.id.includeOrderStack5);
        final View viewStack6 = rootContainer.findViewById(R.id.includeOrderStack6);
        final View viewStack7 = rootContainer.findViewById(R.id.includeOrderStack7);
        final View viewStack8 = rootContainer.findViewById(R.id.includeOrderStack8);
        final View viewStack9 = rootContainer.findViewById(R.id.includeOrderStack9);
        final View viewStack10 = rootContainer.findViewById(R.id.includeOrderStack10);
        return new View[]{viewStack1, viewStack2, viewStack3, viewStack4, viewStack5,
                viewStack6, viewStack7, viewStack8, viewStack9, viewStack10};
    }

    public TextView createTextView(View container){
        return (TextView) container.findViewById(R.id.bricksTV);
    }
}
