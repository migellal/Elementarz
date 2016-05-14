package org.e_lementarz.elementarz.activities.numbers;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.e_lementarz.elementarz.R;
import org.e_lementarz.elementarz.common.ElementarzNumbersActivity;
import org.e_lementarz.elementarz.common.MorphingAnimation;
import org.e_lementarz.elementarz.common.StackBricksElementsCreator;
import org.e_lementarz.elementarz.common.StackBricksElementsOperation;

import butterknife.OnClick;

public class CompareActivity extends ElementarzNumbersActivity {

    private int counterLeft = 0;
    private int counterRight = 0;
    private boolean firstChange = true;
    private boolean nextActivity = false;
    private View[] bricksLeftArray;
    private View[] bricksRightArray;
    private TextView firstStackCounterTV;
    private TextView secondStackCounterTV;
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
                    stackBricksElementsOperation.refreshText(firstStackCounterTV, counterLeft);
                }
                break;
            case R.id.minusLeftBtn:
                if (counterLeft > 0) {
                    counterLeft--;
                    stackBricksElementsOperation.refreshStack(bricksLeftArray, counterLeft, getApplicationContext());
                    stackBricksElementsOperation.refreshText(firstStackCounterTV, counterLeft);
                }
                break;
            case R.id.plusRightBtn:
                if (counterRight < 10) {
                    stackBricksElementsOperation.refreshStack(bricksRightArray, counterRight, getApplicationContext());
                    counterRight++;
                    stackBricksElementsOperation.refreshText(secondStackCounterTV, counterRight);
                }
                break;
            case R.id.minusRightBtn:
                if (counterRight > 0) {
                    counterRight--;
                    stackBricksElementsOperation.refreshStack(bricksRightArray, counterRight, getApplicationContext());
                    stackBricksElementsOperation.refreshText(secondStackCounterTV, counterRight);
                }
                break;
        }
        if (firstChange && counterLeft != counterRight) {
            morphingAnimation.animFab(R.drawable.ic_line_to_undone, R.color.colorFailureRed);
        }
        else if (firstChange && counterLeft == counterRight) {
            morphingAnimation.animFab(R.drawable.ic_line_to_done, R.color.colorSuccessGreen);
        }
        else if (!firstChange && counterLeft != counterRight) {
            morphingAnimation.animFab(R.drawable.ic_done_to_undone, R.color.colorFailureRed);
        }
        else if (!firstChange && counterLeft == counterRight) {
            morphingAnimation.animFab(R.drawable.ic_undone_to_done, R.color.colorSuccessGreen);
        }
        firstChange=false;
    }

    @Override
    @OnClick(R.id.fab)
    public void onClickFab() {
        if(nextActivity)
            onClickSuccessView();
        else if(counterLeft==counterRight)
        {
            fillScreen(true, false);
            nextActivity = true;
        }
    }

    @Override
    @OnClick(R.id.successView)
    public void onClickSuccessView() {
        nextActivity(true, CompareActivity.this);
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
        assert stackBricksContainerLeft != null;
        assert stackBricksContainerRight != null;
        firstStackCounterTV = (TextView) stackBricksContainerLeft.findViewById(R.id.bricksTV);
        secondStackCounterTV = (TextView) stackBricksContainerRight.findViewById(R.id.bricksTV);

    }


}
