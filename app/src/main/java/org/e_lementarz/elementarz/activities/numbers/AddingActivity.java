package org.e_lementarz.elementarz.activities.numbers;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.e_lementarz.elementarz.R;
import org.e_lementarz.elementarz.common.ElementarzNumbersActivity;
import org.e_lementarz.elementarz.common.StackBricksElementsCreator;
import org.e_lementarz.elementarz.common.StackBricksElementsOperation;

import java.util.Random;

import butterknife.Bind;
import butterknife.OnClick;

public class AddingActivity extends ElementarzNumbersActivity {

    private int counter = 0;
    private int result;
    private int gameCounter = 0;
    private boolean firstRun = true;
    private boolean screenFiled = false;
    @Bind(R.id.includeFirstStack)
    View includeFirstStack;
    @Bind(R.id.includeSecondStack)
    View includeSecondStack;
    @Bind(R.id.includeResultStack)
    View includeResultStack;
    private View[] bricksFirstArray;
    private View[] bricksSecondArray;
    private View[] bricksResultArray;
    private TextView firstStackCounterTV;
    private TextView secondStackCounterTV;
    private TextView resultStackCounterTV;
    private StackBricksElementsCreator stackBricksElementsCreator = new StackBricksElementsCreator();
    private StackBricksElementsOperation stackBricksElementsOperation = new StackBricksElementsOperation();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding);
        createViews();
    }

    @Override
    @OnClick(R.id.fab)
    public void onClickFab() {
        if (gameCounter < 5) {
            if (counter == result) {
                if (!screenFiled) {
                    fillScreen(true, true);
                    screenFiled = true;
                } else
                    onClickSuccessView();
            } else {
                if (!screenFiled) {
                    fillScreen(false, true);
                    screenFiled = true;
                } else
                    onClickFailureView();
            }
        }
    }

    private void createReadyView() {
        Random random = new Random();
        int firstRand = random.nextInt(6);
        int secondRand = random.nextInt(6);
        if (firstRun) {
            firstStackCounterTV = (TextView) includeFirstStack.findViewById(R.id.bricksTV);
            secondStackCounterTV = (TextView) includeSecondStack.findViewById(R.id.bricksTV);
            resultStackCounterTV = (TextView) includeResultStack.findViewById(R.id.bricksTV);
            bricksFirstArray = stackBricksElementsCreator.createBricksStack(includeFirstStack, firstRand);
            bricksSecondArray = stackBricksElementsCreator.createBricksStack(includeSecondStack, secondRand);
            bricksResultArray = stackBricksElementsCreator.createBricksStack(includeResultStack);
            firstRun = false;
        } else {
            stackBricksElementsOperation.showStack(bricksFirstArray, firstRand);
            stackBricksElementsOperation.showStack(bricksSecondArray, secondRand);
            stackBricksElementsOperation.hideStack(bricksResultArray);
            if (counter != 0)
                bricksResultArray[counter-1].setAlpha(0);
        }
        counter = 0;
        firstStackCounterTV.setText(String.valueOf(firstRand));
        secondStackCounterTV.setText(String.valueOf(secondRand));
        resultStackCounterTV.setText(String.valueOf(counter));
        result = firstRand + secondRand;
    }

    @OnClick(R.id.addingPlusBtn)
    void add() {
        if (counter < 10) {
            bricksResultArray[counter].setAlpha(1);
            stackBricksElementsOperation.refreshStack(bricksResultArray, counter, getApplicationContext());
            counter++;
            resultStackCounterTV.setText(String.valueOf(counter));
        }
    }

    @OnClick(R.id.addingMinusBtn)
    void remove() {
        if (counter > 0) {
            counter--;
            stackBricksElementsOperation.refreshStack(bricksResultArray, counter, getApplicationContext());
            resultStackCounterTV.setText(String.valueOf(counter));
        }
    }

    @Override
    @OnClick(R.id.successView)
    public void onClickSuccessView() {
        nextStar(this, true);
        gameCounter++;
        if (gameCounter == 5)
            nextActivity(true, AddingActivity.this, NumbersActivity.class);
        else {
            createReadyView();
            unFillScreen(true, true);
            screenFiled = false;
        }
    }

    @OnClick(R.id.failureView)
    void onClickFailureView() {
        nextStar(this, false);
        gameCounter++;
        if (gameCounter == 5)
            nextActivity(false, AddingActivity.this, NumbersActivity.class);
        else {
            createReadyView();
            unFillScreen(false, true);
            screenFiled = false;
        }
    }

    @Override
    public void createViews() {
        super.createViews();

        startAnim(this, R.id.content_adding);
        createReadyView();
    }
}