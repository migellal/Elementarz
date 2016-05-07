package org.e_lementarz.elementarz.common;

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
        for (int i = 0; i < bricks.length; i++) {
            bricks[i].setVisibility(View.INVISIBLE);
        }
    }

    public void hideStack(View bricks[], int height) {
        for (int i = 0; i < height; i++) {
            bricks[i].setVisibility(View.INVISIBLE);
        }
        for (; height < 10; height++) {
            bricks[height].setVisibility(View.VISIBLE);
        }
    }

    public void showStack(View bricks[]) {
        for (int i = 0; i < bricks.length; i++) {
            bricks[i].setVisibility(View.VISIBLE);
        }
    }

    public void showStack(View bricks[], int height) {
        for (int i = 0; i < height; i++) {
            bricks[i].setVisibility(View.VISIBLE);
        }
        for (; height < 10; height++) {
            bricks[height].setVisibility(View.INVISIBLE);
        }
    }


    public Animation refreshStack(View[] bricksArray, int counter, Context context) {
        animPut = AnimationUtils.loadAnimation(context,
                R.anim.put_anim);
        if (counter >= 0 && bricksArray[counter].getVisibility() == View.INVISIBLE) {
            if (counter > 0)
                bricksArray[counter - 1].clearAnimation();
            bricksArray[counter].setVisibility(View.VISIBLE);
            animPut.setInterpolator(new BounceInterpolator());
            bricksArray[counter].startAnimation(animPut);
        } else {
            if (counter < 9)
                bricksArray[counter + 1].clearAnimation();
            bricksArray[counter].setVisibility(View.INVISIBLE);
            ReverseInterpolator reverseInterpolator = new ReverseInterpolator();
            animPut.setInterpolator(reverseInterpolator);
            bricksArray[counter].startAnimation(animPut);
        }
        return animPut;
    }

    public Animation refreshStackOnlyAdding(View[] bricksArray, int counter, Context context) {
        animPut = AnimationUtils.loadAnimation(context,
                R.anim.put_anim);
        animPut.setInterpolator(new BounceInterpolator());
        for (int i = 0; i <= counter; i++) {
            bricksArray[i].startAnimation(animPut);
        }
        return animPut;
    }

    public void refreshText(TextView textView, int counter) {
        textView.setText(String.valueOf(counter));
    }
}
