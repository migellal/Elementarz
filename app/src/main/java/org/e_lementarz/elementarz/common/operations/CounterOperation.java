package org.e_lementarz.elementarz.common.operations;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import org.e_lementarz.elementarz.R;
import org.e_lementarz.elementarz.common.Const;
import org.e_lementarz.elementarz.common.interfaces.Operation;

/**
 * Created by micha on 15.05.2016.
 */
public class CounterOperation implements Operation {

    private ImageView timer;
    private TextView doneCounter;
    private TextView undoneCounter;
    private int dCounter = 0;
    private int uCounter = 0;

    private int time, goodAnswer, badAnswer;
    private boolean practice = true;
    private Animation animation;

    public CounterOperation(Activity rootContainer) {
        ViewGroup content = (ViewGroup) rootContainer.findViewById(R.id.counter_layout);
        content.setVisibility(View.VISIBLE);
        timer = (ImageView) rootContainer.findViewById(R.id.counterTimerIV);
        timer.setVisibility(View.INVISIBLE);
        doneCounter = (TextView) rootContainer.findViewById(R.id.counterDoneTV);
        undoneCounter = (TextView) rootContainer.findViewById(R.id.counterUndoneTV);
    }

    public CounterOperation(Activity rootContainer, Intent intent) {
        this(rootContainer);
        timer.setVisibility(View.VISIBLE);
        getTestClass(intent);
        practice = false;
        animation = new ScaleAnimation(1f, 0f, 1f, 0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setFillAfter(false);
        animation.setDuration(time*1000);
    }

    @Override
    public boolean nextPoint(boolean good) {
        if (good) {
            dCounter++;
            doneCounter.setText(String.valueOf(dCounter));
        } else {
            uCounter++;
            undoneCounter.setText(String.valueOf(uCounter));
        }
        if (practice)
            return false;
        else if(!good)
            return uCounter >= badAnswer;
        else
            return dCounter == goodAnswer;
    }

    public Animation startTime() {
        timer.clearAnimation();
        timer.startAnimation(animation);
        return animation;
    }

    public void stopTime() {
        if(animation.hasStarted())
            animation.cancel();
    }

    private void getTestClass(Intent intent) {
        int c = intent.getIntExtra(Const.CLASS, 0);
        if (c == 1) {
            time = Const.I_CLASS_TIME;
            goodAnswer = Const.I_CLASS_GOOD_ANSWER;
            badAnswer = Const.I_CLASS_BAD_ANSWER;
        } else if (c == 2) {
            time = Const.II_CLASS_TIME;
            goodAnswer = Const.II_CLASS_GOOD_ANSWER;
            badAnswer = Const.II_CLASS_BAD_ANSWER;
        } else {
            time = Const.III_CLASS_TIME;
            goodAnswer = Const.III_CLASS_GOOD_ANSWER;
            badAnswer = Const.III_CLASS_BAD_ANSWER;
        }
    }

}
