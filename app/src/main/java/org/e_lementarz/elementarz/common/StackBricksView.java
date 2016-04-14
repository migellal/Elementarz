package org.e_lementarz.elementarz.common;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;

import org.e_lementarz.elementarz.R;
import org.e_lementarz.elementarz.util.ReverseInterpolator;

/**
 * Created by micha on 13.04.2016.
 */
public class StackBricksView {

    private Animation animPut;

    public View[] hideStack(View bricks[]) {
        for (int i = 1; i < bricks.length; i++) {
            bricks[i].setVisibility(View.INVISIBLE);
        }
        return bricks;
    }

    public View[] hideStack(View bricks[], int height) {
        height++;
        for (int i = 1; i < height; i++) {
            bricks[i].setVisibility(View.INVISIBLE);
        }
        for (; height <= 10; height++) {
            bricks[height].setVisibility(View.VISIBLE);
        }

        return bricks;
    }

    public View[] showStack(View bricks[]) {
        for (int i = 1; i < bricks.length; i++) {
            bricks[i].setVisibility(View.VISIBLE);
        }
        return bricks;
    }

    public View[] showStack(View bricks[], int height) {
        height++;
        for (int i = 1; i < height; i++) {
            bricks[i].setVisibility(View.VISIBLE);
        }
        for (; height <= 10; height++) {
            bricks[height].setVisibility(View.INVISIBLE);
        }

        return bricks;
    }


    public void refreshView(View[] bricksArray, int counter, Context context) {
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

    public void refreshAllViewsOnlyAdding(View[] bricksArray, int counter, Context context) {
        animPut = AnimationUtils.loadAnimation(context,
                R.anim.put_anim);
        animPut.setInterpolator(new BounceInterpolator());
        for (int i = 1; i <= counter; i++) {
            bricksArray[i].startAnimation(animPut);
        }
    }
}
