package org.e_lementarz.elementarz.activities.numbers;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.e_lementarz.elementarz.R;
import org.e_lementarz.elementarz.common.extend.ElementarzNumbersCheckActivity;
import org.e_lementarz.elementarz.common.interfaces.Operation;
import org.e_lementarz.elementarz.common.operations.StackBricksElementsCreator;
import org.e_lementarz.elementarz.common.operations.StackBricksElementsOperation;

import java.util.Random;

import butterknife.OnClick;

public class CompareCheckActivity extends ElementarzNumbersCheckActivity {

    private int counterLeft = -1;
    private int counterRight = -1;
    private View[] bricksLeftArray;
    private View[] bricksRightArray;
    private TextView leftStackCounterTV;
    private TextView rightStackCounterTV;
    private StackBricksElementsCreator stackBricksElementsCreator = new StackBricksElementsCreator();
    private StackBricksElementsOperation stackBricksElementsOperation = new StackBricksElementsOperation();
    private Operation operation;

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
                    stackBricksElementsOperation.refreshText(leftStackCounterTV, counterLeft);
                }
                break;
            case R.id.minusLeftBtn:
                if (counterLeft > 0) {
                    counterLeft--;
                    stackBricksElementsOperation.refreshStack(bricksLeftArray, counterLeft, getApplicationContext());
                    stackBricksElementsOperation.refreshText(leftStackCounterTV, counterLeft);
                }
                break;
            case R.id.plusRightBtn:
                if (counterRight < 10) {
                    stackBricksElementsOperation.refreshStack(bricksRightArray, counterRight, getApplicationContext());
                    counterRight++;
                    stackBricksElementsOperation.refreshText(rightStackCounterTV, counterRight);
                }
                break;
            case R.id.minusRightBtn:
                if (counterRight > 0) {
                    counterRight--;
                    stackBricksElementsOperation.refreshStack(bricksRightArray, counterRight, getApplicationContext());
                    stackBricksElementsOperation.refreshText(rightStackCounterTV, counterRight);
                }
                break;
        }
    }

    @Override
    @OnClick(R.id.successView)
    public void onClickSuccessView() {
        if (operation.nextPoint(true))
            nextActivity(true, CompareCheckActivity.this);
        else
            onClickCommon();
    }

    @Override
    @OnClick(R.id.failureView)
    public void onClickFailureView() {
        if (operation.nextPoint(false))
            nextActivity(false, CompareCheckActivity.this);
        else
            onClickCommon();
    }

    @Override
    @OnClick(R.id.fab)
    public void onClickFab() {
        if (!isScreenFiled()) {
            stopTime();
            fillScreen(counterRight == counterLeft, true);
        } else {
            if (counterRight == counterLeft)
                onClickSuccessView();
            else
                onClickFailureView();
        }
    }

    @Override
    public void onClickCommon() {
        stackBricksElementsOperation.hideStack(bricksLeftArray);
        stackBricksElementsOperation.hideStack(bricksRightArray);
        unFillScreen(counterRight == counterLeft, true);
        createReadyView();
    }

    @Override
    public void createViews() {
        super.createViews();

        operation = getOperation();
        startAnim(this, R.id.content_compare);
        View stackBricksContainerLeft = findViewById(R.id.includeLeftStack);
        View stackBricksContainerRight = findViewById(R.id.includeRightStack);
        bricksLeftArray = stackBricksElementsCreator.createBricksStack(stackBricksContainerLeft);
        bricksRightArray = stackBricksElementsCreator.createBricksStack(stackBricksContainerRight);
        assert stackBricksContainerLeft != null;
        assert stackBricksContainerRight != null;
        leftStackCounterTV = (TextView) stackBricksContainerLeft.findViewById(R.id.bricksTV);
        rightStackCounterTV = (TextView) stackBricksContainerRight.findViewById(R.id.bricksTV);
        createReadyView();
    }

    @Override
    public void createReadyView() {
        Random random = new Random();
        int leftRand = random.nextInt(11);
        int rightRand = random.nextInt(11);
        counterLeft = leftRand;
        counterRight = rightRand;
        stackBricksElementsOperation.showStack(bricksLeftArray, leftRand);
        stackBricksElementsOperation.showStack(bricksRightArray, rightRand);
        stackBricksElementsOperation.refreshText(leftStackCounterTV, leftRand);
        stackBricksElementsOperation.refreshText(rightStackCounterTV, rightRand);
        startTime();
    }
}
