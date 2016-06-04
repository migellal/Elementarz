package org.e_lementarz.elementarz.activities.numbers;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.e_lementarz.elementarz.R;
import org.e_lementarz.elementarz.common.extend.ElementarzNumbersCheckActivity;
import org.e_lementarz.elementarz.common.interfaces.Operation;
import org.e_lementarz.elementarz.common.operations.StackBricksElementsCreator;
import org.e_lementarz.elementarz.common.operations.StackBricksElementsOperation;

import java.util.Random;

import butterknife.Bind;
import butterknife.OnClick;

public class SubtractionCheckActivity extends ElementarzNumbersCheckActivity {

    @Bind(R.id.includeFirstStack)
    View includeFirstStack;
    @Bind(R.id.includeSecondStack)
    View includeSecondStack;
    @Bind(R.id.includeResultStack)
    View includeResultStack;
    private int counter = -1;
    private int result = -1;
    private View[] bricksFirstArray;
    private View[] bricksSecondArray;
    private View[] bricksResultArray;
    private TextView firstStackCounterTV;
    private TextView secondStackCounterTV;
    private TextView resultStackCounterTV;
    private StackBricksElementsCreator stackBricksElementsCreator = new StackBricksElementsCreator();
    private StackBricksElementsOperation stackBricksElementsOperation = new StackBricksElementsOperation();
    private Operation operation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtraction);
        createViews();
    }

    @Override
    @OnClick(R.id.fab)
    public void onClickFab() {
        if (!isScreenFiled()) {
            stopTime();
            fillScreen(counter == result, true);
        } else {
            if (counter == result)
                onClickSuccessView();
            else
                onClickFailureView();
        }
    }

    @OnClick(R.id.addingPlusBtn)
    void add() {
        if (counter < 10) {
            stackBricksElementsOperation.refreshStack(bricksResultArray, counter, getApplicationContext());
            counter++;
            stackBricksElementsOperation.refreshText(resultStackCounterTV, counter);
        }
    }

    @OnClick(R.id.addingMinusBtn)
    void remove() {
        if (counter > 0) {
            counter--;
            stackBricksElementsOperation.refreshStack(bricksResultArray, counter, getApplicationContext());
            stackBricksElementsOperation.refreshText(resultStackCounterTV, counter);
        }
    }

    @Override
    @OnClick(R.id.successView)
    public void onClickSuccessView() {
        if (operation.nextPoint(true))
            nextActivity(true, SubtractionCheckActivity.this);
        else
            onClickCommon();
    }

    @OnClick(R.id.failureView)
    public void onClickFailureView() {
        if (operation.nextPoint(false))
            nextActivity(false, SubtractionCheckActivity.this);
        else
            onClickCommon();
    }

    @Override
    public void onClickCommon() {
        unFillScreen(counter == result, true);
        createReadyView();
    }

    @Override
    public void createViews() {
        super.createViews();

        operation = getOperation();
        startAnim(this, R.id.content_subtraction);
        firstStackCounterTV = (TextView) includeFirstStack.findViewById(R.id.bricksTV);
        secondStackCounterTV = (TextView) includeSecondStack.findViewById(R.id.bricksTV);
        resultStackCounterTV = (TextView) includeResultStack.findViewById(R.id.bricksTV);
        bricksFirstArray = stackBricksElementsCreator.createBricksStack(includeFirstStack);
        bricksSecondArray = stackBricksElementsCreator.createBricksStack(includeSecondStack);
        bricksResultArray = stackBricksElementsCreator.createBricksStack(includeResultStack);
        createReadyView();
    }

    @Override
    public void createReadyView() {
        Random random = new Random();
        int firstRand = random.nextInt(10)+1;
        int secondRand = random.nextInt(firstRand+1);
        stackBricksElementsOperation.showStack(bricksFirstArray, firstRand);
        stackBricksElementsOperation.showStack(bricksSecondArray, secondRand);
        stackBricksElementsOperation.hideStack(bricksResultArray);
        counter = 0;
        stackBricksElementsOperation.refreshText(firstStackCounterTV, firstRand);
        stackBricksElementsOperation.refreshText(secondStackCounterTV, secondRand);
        stackBricksElementsOperation.refreshText(resultStackCounterTV, counter);
        result = firstRand - secondRand;
        startTime();
    }

}
