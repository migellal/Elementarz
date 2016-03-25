package org.e_lementarz.elementarz.common;

import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

import org.e_lementarz.elementarz.R;

/**
 * Created by micha on 21.03.2016.
 */
public class StackBricksViewGroup {

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


    public void refreshView(int counter, View[] bricksArray, Animation animation, boolean animOnlyLast) {
            if (counter>0&&bricksArray[counter].getVisibility() == View.INVISIBLE) {
                if(counter>1&&animOnlyLast)
                    bricksArray[counter-1].clearAnimation();
                bricksArray[counter].setVisibility(View.VISIBLE);
                animation.setInterpolator(new BounceInterpolator());
                bricksArray[counter].startAnimation(animation);
            } else {
                if(counter<9&&animOnlyLast)
                    bricksArray[counter + 2].clearAnimation();
                bricksArray[counter + 1].setVisibility(View.INVISIBLE);
                ReverseInterpolator reverseInterpolator = new ReverseInterpolator();
                animation.setInterpolator(reverseInterpolator);
                bricksArray[counter+1].startAnimation(animation);
            }



//        for(int i = 1; i<=10; i++)
//        {
//            if(i<=counter) {
//                bricksArray[i].setVisibility(View.VISIBLE);
//                bricksArray[i].startAnimation(animation);
//            }
//            else {
//                if(bricksArray[i].getVisibility()!=View.INVISIBLE) {
//                    bricksArray[i].setVisibility(View.INVISIBLE);
//                    animation.setRepeatMode(Animation.REVERSE);
//                    bricksArray[i].startAnimation(animation);
//                }
//            }
//        }
    }
}
