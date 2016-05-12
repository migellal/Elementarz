package org.e_lementarz.elementarz.common;

import android.app.Activity;
import android.widget.Button;

import org.e_lementarz.elementarz.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by mgellert on 2016-05-12.
 */
public class NumbersPadElements {

    @Bind({R.id.button0, R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5,
            R.id.button6, R.id.button7, R.id.button8, R.id.button9, R.id.button10})
    Button[] numbersPad;

    public NumbersPadElements(Activity activity) {
        ButterKnife.bind(activity);
    }

}
