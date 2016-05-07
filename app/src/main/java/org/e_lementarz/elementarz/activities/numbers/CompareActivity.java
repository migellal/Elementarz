package org.e_lementarz.elementarz.activities.numbers;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.e_lementarz.elementarz.R;
import org.e_lementarz.elementarz.common.ElementarzNumbersActivity;
import org.e_lementarz.elementarz.common.MorphingAnimation;
import org.e_lementarz.elementarz.common.StackBricksElementsCreator;
import org.e_lementarz.elementarz.common.StackBricksElementsOperation;

import butterknife.OnClick;

public class CompareActivity extends ElementarzNumbersActivity {

    private int counterLeft = 0;
    private int counterRight = 0;
    private boolean firstChangeDone = true;
    private boolean firstChangeUndone = true;
    private boolean nextActivity = false;
    private View[] bricksLeftArray;
    private View[] bricksRightArray;
    private StackBricksElementsCreator stackBricksElementsCreator = new StackBricksElementsCreator();
    private StackBricksElementsOperation stackBricksElementsOperation = new StackBricksElementsOperation();
    private MorphingAnimation morphingAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);
        createViews();
    }

    public void onClickCompareOperations(View view) {
        switch (view.getId()) {
            case R.id.plusLeftBtn:
                if (counterLeft < 10) {
                    stackBricksElementsOperation.refreshStack(bricksLeftArray, counterLeft, getApplicationContext());
                    counterLeft++;
                }
                break;
            case R.id.minusLeftBtn:
                if (counterLeft > 0) {
                    counterLeft--;
                    stackBricksElementsOperation.refreshStack(bricksLeftArray, counterLeft, getApplicationContext());
                }
                break;
            case R.id.plusRightBtn:
                if (counterRight < 10) {
                    stackBricksElementsOperation.refreshStack(bricksRightArray, counterRight, getApplicationContext());
                    counterRight++;
                }
                break;
            case R.id.minusRightBtn:
                if (counterRight > 0) {
                    counterRight--;
                    stackBricksElementsOperation.refreshStack(bricksRightArray, counterRight, getApplicationContext());
                }
                break;
        }
        if (counterLeft != counterRight && firstChangeUndone) {
            morphingAnimation.animFab(R.drawable.ic_done_to_undone, R.color.colorFailureRed);
            firstChangeUndone = false;
            firstChangeDone = true;
        }
        if (counterLeft == counterRight && firstChangeDone) {
            morphingAnimation.animFab(R.drawable.ic_undone_to_done, R.color.colorSuccessGreen);
            firstChangeDone = false;
            firstChangeUndone = true;
        }
    }

    @Override
    @OnClick(R.id.fab)
    public void onClickFab() {
        if(nextActivity)
            onClickSuccessView();
        else if(!firstChangeDone&&firstChangeUndone)
        {
            fillScreen(true, false);
            nextActivity = true;
        }
    }

    @Override
    @OnClick(R.id.successView)
    public void onClickSuccessView() {
        nextActivity(true, CompareActivity.this, CompareCheckActivity.class);
    }


    @Override
    public void createViews() {
        super.createViews();

        startAnim(this, R.id.content_compare);
        View stackBricksContainerLeft = findViewById(R.id.includeLeftStack);
        View stackBricksContainerRight = findViewById(R.id.includeRightStack);
        bricksLeftArray = stackBricksElementsCreator.createBricksStack(stackBricksContainerLeft);
        bricksRightArray = stackBricksElementsCreator.createBricksStack(stackBricksContainerRight);
        morphingAnimation = new MorphingAnimation(getApplicationContext(), getFab());
    }


}
