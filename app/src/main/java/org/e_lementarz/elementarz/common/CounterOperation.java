package org.e_lementarz.elementarz.common;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.e_lementarz.elementarz.R;

/**
 * Created by micha on 15.05.2016.
 */
public class CounterOperation {

    private ImageView timer;
    private TextView doneCounter;
    private TextView undoneCounter;
    private int dCounter = 0;
    private int uCounter = 0;

    public CounterOperation(Activity rootContainer) {
        ViewGroup content = (ViewGroup) rootContainer.findViewById(R.id.counter_layout);
        content.setVisibility(View.VISIBLE);
        timer = (ImageView) rootContainer.findViewById(R.id.counterTimerIV);
        timer.setVisibility(View.INVISIBLE);
        doneCounter = (TextView) rootContainer.findViewById(R.id.counterDoneTV);
        undoneCounter = (TextView) rootContainer.findViewById(R.id.counterUndoneTV);
    }

    public void count(boolean good) {
        if (good) {
            dCounter++;
            doneCounter.setText(String.valueOf(dCounter));
        } else {
            uCounter++;
            undoneCounter.setText(String.valueOf(uCounter));
        }
    }
}
