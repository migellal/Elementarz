package org.e_lementarz.elementarz.common.extend;

import android.content.Intent;
import android.view.animation.Animation;

import org.e_lementarz.elementarz.common.Const;
import org.e_lementarz.elementarz.common.interfaces.Operation;
import org.e_lementarz.elementarz.common.operations.CounterOperation;
import org.e_lementarz.elementarz.common.operations.StarsOperation;

/**
 * Created by micha on 19.03.2016.
 */
public abstract class ElementarzNumbersCheckActivity extends ElementarzNumbersActivity {

    private Operation operation;
    private CounterOperation counterOperation;
    private boolean isClicked = false;

    public void createViews() {
        super.createViews();

        Intent intent = getIntent();
        setPractice(intent.getBooleanExtra(Const.PRACTICE, false));
        setTest(intent.getBooleanExtra(Const.TEST, false));
        setCustom(intent.getBooleanExtra(Const.CUSTOM, false));
        if (isPractice())
            operation = new CounterOperation(this);
        else if (isTest()||isCustom()) {
            operation = new CounterOperation(this, getIntent());
            counterOperation = new CounterOperation(this, getIntent());
        } else
            operation = new StarsOperation(this);
    }

    public void startTime() {
        if (isTest()||isCustom()) {
            isClicked = false;
            counterOperation.startTime().setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    if (!isClicked)
                        fillScreen(false, true);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });
        }

    }

    public void stopTime() {
        if (isTest()||isCustom())
            counterOperation.stopTime();
        isClicked = true;
    }

    public abstract void onClickFailureView();

    public abstract void onClickCommon();

    public abstract void createReadyView();

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
    }
}
