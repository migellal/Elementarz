package org.e_lementarz.elementarz.activities.numbers;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.e_lementarz.elementarz.R;
import org.e_lementarz.elementarz.common.operations.CounterOperation;
import org.e_lementarz.elementarz.common.extend.ElementarzNumbersActivity;
import org.e_lementarz.elementarz.common.operations.StackBricksElementsCreator;
import org.e_lementarz.elementarz.common.operations.StackBricksElementsOperation;
import org.e_lementarz.elementarz.common.operations.StarsOperation;

import java.util.Random;

import butterknife.OnClick;

public class CompareCheckActivity extends ElementarzNumbersActivity {

    private int counterLeft = 0;
    private int counterRight = 0;
    private int gameCounter = 0;
    private boolean screenFiled = false;
    private boolean nextActivity = false;
    private View[] bricksLeftArray;
    private View[] bricksRightArray;
    private TextView leftStackCounterTV;
    private TextView rightStackCounterTV;
    private StackBricksElementsCreator stackBricksElementsCreator = new StackBricksElementsCreator();
    private StackBricksElementsOperation stackBricksElementsOperation = new StackBricksElementsOperation();
    private StarsOperation starsOperation;
    private CounterOperation counterOperation;

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
        if (isPractice()) {
            stackBricksElementsOperation.hideStack(bricksLeftArray);
            stackBricksElementsOperation.hideStack(bricksRightArray);
            createReadyView();
            counterOperation.nextPoint(true);
            unFillScreen(true, true);
            screenFiled = false;
        } else {
            starsOperation.nextPoint(true);
            gameCounter++;
            if (gameCounter == 5)
                nextActivity(true, CompareCheckActivity.this);
            else {
                stackBricksElementsOperation.hideStack(bricksLeftArray);
                stackBricksElementsOperation.hideStack(bricksRightArray);
                createReadyView();
                screenFiled = false;
                unFillScreen(true, true);
            }
        }
    }

    @OnClick(R.id.failureView)
    void onClickFailureView() {
        if (isPractice()) {
            stackBricksElementsOperation.hideStack(bricksLeftArray);
            stackBricksElementsOperation.hideStack(bricksRightArray);
            createReadyView();
            counterOperation.nextPoint(false);
            unFillScreen(false, true);
            screenFiled = false;
        } else {
            starsOperation.nextPoint(false);
            gameCounter++;
            if (gameCounter == 5)
                nextActivity(false, CompareCheckActivity.this);
            else {
                stackBricksElementsOperation.hideStack(bricksLeftArray);
                stackBricksElementsOperation.hideStack(bricksRightArray);
                createReadyView();
                screenFiled = false;
                unFillScreen(false, true);
            }
        }
    }

    private void createReadyView() {
        Random random = new Random();
        int leftRand = random.nextInt(11);
        int rightRand = random.nextInt(11);
        counterLeft = leftRand;
        counterRight = rightRand;
        stackBricksElementsOperation.showStack(bricksLeftArray, leftRand);
        stackBricksElementsOperation.showStack(bricksRightArray, rightRand);
        stackBricksElementsOperation.refreshText(leftStackCounterTV, leftRand);
        stackBricksElementsOperation.refreshText(rightStackCounterTV, rightRand);
    }

    @Override
    @OnClick(R.id.fab)
    public void onClickFab() {
        if (nextActivity) {
            if (counterLeft == counterRight)
                onClickSuccessView();
            else
                onClickFailureView();
        } else if (!nextActivity&&!screenFiled) {
            fillScreen(counterRight == counterLeft, true);
            screenFiled = true;
            if (gameCounter == 5)
                nextActivity = true;
        }
        else if(screenFiled&&counterRight == counterLeft)
        {
            onClickSuccessView();
        }
        else if(screenFiled&&counterRight != counterLeft)
        {
            onClickFailureView();
        }
    }

    @Override
    public void createViews() {
        super.createViews();

        startAnim(this, R.id.content_compare);
        View stackBricksContainerLeft = findViewById(R.id.includeLeftStack);
        View stackBricksContainerRight = findViewById(R.id.includeRightStack);
        bricksLeftArray = stackBricksElementsCreator.createBricksStack(stackBricksContainerLeft);
        bricksRightArray = stackBricksElementsCreator.createBricksStack(stackBricksContainerRight);
        assert stackBricksContainerLeft != null;
        assert stackBricksContainerRight != null;
        leftStackCounterTV = (TextView) stackBricksContainerLeft.findViewById(R.id.bricksTV);
        rightStackCounterTV = (TextView) stackBricksContainerRight.findViewById(R.id.bricksTV);
        if (isPractice())
            counterOperation = new CounterOperation(this);
        else
            starsOperation = new StarsOperation(this);
        createReadyView();
    }
}
