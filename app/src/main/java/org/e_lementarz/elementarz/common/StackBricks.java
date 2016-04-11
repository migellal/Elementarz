package org.e_lementarz.elementarz.common;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;

import org.e_lementarz.elementarz.R;
import org.e_lementarz.elementarz.util.ReverseInterpolator;

/**
 * Created by micha on 21.03.2016.
 */
public class StackBricks {

    private Animation animPut;

    public View[] getBricksStack(View container) {
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
        return new View[]{null, bricks1, bricks2, bricks3, bricks4, bricks5, bricks6,
                bricks7, bricks8, bricks9, bricks10};
    }

    public View[] getBricksStack(View container, int height) {
        View[] v = getBricksStack(container);
        for (int i = 1; i < height; i++)
            v[i].setVisibility(View.VISIBLE);
        return v;
    }

    public View[] getContainers(Activity rootContainer) {
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
        return new View[]{null, viewStack1, viewStack2, viewStack3, viewStack4, viewStack5,
                viewStack6, viewStack7, viewStack8, viewStack9, viewStack10};
    }

    public View[] refreshStack(View bricks[]) {
        for (int i = 1; i < bricks.length; i++) {
            bricks[i].setVisibility(View.INVISIBLE);
        }
        return bricks;
    }

    public View[] refreshStack(View bricks[], int height) {
        for (int i = 1; i < height; i++) {
            bricks[i].setVisibility(View.VISIBLE);
        }
        if (height == 0) {
            bricks = refreshStack(bricks);
        } else {
            for (; height <= 10; height++) {
                bricks[height].setVisibility(View.INVISIBLE);
            }
        }
        return bricks;
    }


    public void refreshView(int counter, View[] bricksArray, Context context) {
        animPut = AnimationUtils.loadAnimation(context,
                R.anim.put_anim);
        if (counter > 0 && bricksArray[counter].getVisibility() == View.INVISIBLE) {
            if (counter > 1)
                bricksArray[counter - 1].clearAnimation();
            bricksArray[counter].setVisibility(View.VISIBLE);
            animPut.setInterpolator(new BounceInterpolator());
            bricksArray[counter].startAnimation(animPut);
        } else {
            if (counter < 9)
                bricksArray[counter + 2].clearAnimation();
            bricksArray[counter + 1].setVisibility(View.INVISIBLE);
            ReverseInterpolator reverseInterpolator = new ReverseInterpolator();
            animPut.setInterpolator(reverseInterpolator);
            bricksArray[counter + 1].startAnimation(animPut);
        }

    }

    public void refreshAllViewsOnlyAdding(int counter, View[] bricksArray, Context context) {
        animPut = AnimationUtils.loadAnimation(context,
                R.anim.put_anim);
        animPut.setInterpolator(new BounceInterpolator());
        for (int i = 1; i <= counter; i++) {
            bricksArray[i].startAnimation(animPut);
        }
    }
}
