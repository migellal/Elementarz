package org.e_lementarz.elementarz.common.operations;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.BounceInterpolator;
import android.widget.TextView;

import org.e_lementarz.elementarz.R;
import org.e_lementarz.elementarz.util.ReverseInterpolator;

/**
 * Created by micha on 13.04.2016.
 */
public class StackBricksElementsOperation {

    private Animation animPut;

    public void hideStack(View bricks[]) {
        for (View brick : bricks) {
            brick.setAlpha(0);
            brick.clearAnimation();
        }
    }

    public void hideStack(View bricks[], int height) {
        hideStack(bricks);
        for (; height < 10; height++) {
            bricks[height].setAlpha(1);
        }
    }

    public void showStack(View bricks[]) {
        for (View brick : bricks) {
            brick.setAlpha(1);
        }
    }

    public void showStack(View bricks[], int height) {
        showStack(bricks);
        while (height < 10) {
            bricks[height].setAlpha(0);
            height++;
        }
    }


    public Animation refreshStack(final View[] bricksArray, final int counter, Context context) {
        animPut = AnimationUtils.loadAnimation(context,
                R.anim.put_anim);
        if (bricksArray[counter].getAlpha() != 1) {
            if (counter > 0)
                bricksArray[counter - 1].clearAnimation();
            animPut.setInterpolator(new BounceInterpolator());
            bricksArray[counter].startAnimation(animPut);
            bricksArray[counter].setAlpha(1);
        } else {
            if (counter < 9)
                bricksArray[counter + 1].clearAnimation();
            ReverseInterpolator reverseInterpolator = new ReverseInterpolator();
            animPut.setInterpolator(reverseInterpolator);
            bricksArray[counter].startAnimation(animPut);
            animPut.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    bricksArray[counter].setAlpha(0.99f);
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    bricksArray[counter].setAlpha(0);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });
        }
        return animPut;
    }

    public Animation refreshStackOnlyAdding(View[] bricksArray, int counter, Context context) {
        animPut = AnimationUtils.loadAnimation(context,
                R.anim.put_anim);
        animPut.setInterpolator(new BounceInterpolator());
        int i = 0;
        while (i <= counter) {
            bricksArray[i].setAlpha(1);
            bricksArray[i].startAnimation(animPut);
            i++;
        }
        return animPut;
    }

    public void refreshText(TextView textView, int counter) {
        textView.setText(String.valueOf(counter));
    }
}
