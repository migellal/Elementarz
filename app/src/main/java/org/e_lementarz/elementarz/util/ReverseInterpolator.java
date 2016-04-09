package org.e_lementarz.elementarz.util;

import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

/**
 * Created by micha on 25.03.2016.
 */
public class ReverseInterpolator implements Interpolator{

    private Interpolator interpolator;

    public ReverseInterpolator(Interpolator interpolator){

        this.interpolator = interpolator;
    }

    public ReverseInterpolator(){
        this(new LinearInterpolator());
    }

    @Override
    public float getInterpolation(float input) {
        return 1 - interpolator.getInterpolation(input);
    }
}