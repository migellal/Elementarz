package org.e_lementarz.elementarz.activities.numbers;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.e_lementarz.elementarz.R;
import org.e_lementarz.elementarz.common.CounterOperation;
import org.e_lementarz.elementarz.common.ElementarzNumbersActivity;
import org.e_lementarz.elementarz.common.StackBricksElementsCreator;
import org.e_lementarz.elementarz.common.StackBricksElementsOperation;
import org.e_lementarz.elementarz.common.StarsOperation;

import java.util.Random;

import butterknife.OnClick;

public class ChoiceCheckActivity extends ElementarzNumbersActivity {

    int result = -1;
    int selected = -1;
    private boolean screenFiled = false;
    private boolean nextActivity = false;
    private int gameCounter = 0;
    private View[] bricksArray;
    private StackBricksElementsCreator stackBricksElementsCreator = new StackBricksElementsCreator();
    private StackBricksElementsOperation stackBricksElementsOperation = new StackBricksElementsOperation();
    private StarsOperation starsOperation;
    private CounterOperation counterOperation;
    private TextView currentNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        createViews();
    }

    @Override
    @OnClick(R.id.successView)
    public void onClickSuccessView() {
        if (isPractice()) {
            stackBricksElementsOperation.hideStack(bricksArray);
            createReadyView();
            counterOperation.count(true);
            unFillScreen(true, true);
            screenFiled = false;
        } else {
            starsOperation.nextStar(true);
            gameCounter++;
            if (gameCounter == 5)
                nextActivity(true, ChoiceCheckActivity.this);
            else {
                stackBricksElementsOperation.hideStack(bricksArray);
                createReadyView();
                unFillScreen(true, true);
                screenFiled = false;
            }
        }
    }

    @OnClick(R.id.failureView)
    void onClickFailureView() {
        if (isPractice()) {
            stackBricksElementsOperation.hideStack(bricksArray);
            createReadyView();
            counterOperation.count(false);
            unFillScreen(false, true);
            screenFiled = false;
        } else {
            starsOperation.nextStar(false);
            gameCounter++;
            if (gameCounter == 5)
                nextActivity(false, ChoiceCheckActivity.this);
            else {
                stackBricksElementsOperation.hideStack(bricksArray);
                createReadyView();
                unFillScreen(false, true);
                screenFiled = false;
            }
        }
    }

    @Override
    @OnClick(R.id.fab)
    public void onClickFab() {
        if (nextActivity) {
            if (selected == result)
                onClickSuccessView();
            else
                onClickFailureView();
        } else if (!nextActivity&&!screenFiled) {
            fillScreen(selected == result, true);
            screenFiled = true;
            if (gameCounter == 5)
                nextActivity = true;
        }
        else if(screenFiled&&selected == result)
            onClickSuccessView();
        else if(screenFiled&&selected != result)
            onClickFailureView();
    }

    @Override
    public void createViews() {
        super.createViews();

        startAnim(this, R.id.content_choice);
        View stackBricksContainer = findViewById(R.id.stack_container);
        bricksArray = stackBricksElementsCreator.createBricksStack(stackBricksContainer);
        currentNumber = stackBricksElementsCreator.createTextView(stackBricksContainer);
        if (isPractice())
            counterOperation = new CounterOperation(this);
        else
            starsOperation = new StarsOperation(this);
        createReadyView();
    }

    private void createReadyView()
    {
        currentNumber.setVisibility(View.INVISIBLE);
        Random random = new Random();
        result = random.nextInt(11);
        stackBricksElementsOperation.showStack(bricksArray, result);
    }

    private void checkSize(int n) {
        selected = n;
        if(currentNumber.getVisibility()==View.INVISIBLE)
            currentNumber.setVisibility(View.VISIBLE);
        stackBricksElementsOperation.refreshText(currentNumber, selected);
    }

    @OnClick(R.id.button0)
    void onClick0() {
        checkSize(0);
    }

    @OnClick(R.id.button1)
    void onClick1() {
        checkSize(1);
    }

    @OnClick(R.id.button2)
    void onClick2() {
        checkSize(2);
    }

    @OnClick(R.id.button3)
    void onClick3() {
        checkSize(3);
    }

    @OnClick(R.id.button4)
    void onClick4() {
        checkSize(4);
    }

    @OnClick(R.id.button5)
    void onClick5() {
        checkSize(5);
    }

    @OnClick(R.id.button6)
    void onClick6() {
        checkSize(6);
    }

    @OnClick(R.id.button7)
    void onClick7() {
        checkSize(7);
    }

    @OnClick(R.id.button8)
    void onClick8() {
        checkSize(8);
    }

    @OnClick(R.id.button9)
    void onClick9() {
        checkSize(9);
    }

    @OnClick(R.id.button10)
    void onClick10() {
        checkSize(10);
    }

}
