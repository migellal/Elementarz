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

public class ChoiceCheckActivity extends ElementarzNumbersCheckActivity {

    int result = -1;
    int selected = -1;
    private View[] bricksArray;
    private StackBricksElementsCreator stackBricksElementsCreator = new StackBricksElementsCreator();
    private StackBricksElementsOperation stackBricksElementsOperation = new StackBricksElementsOperation();
    private Operation operation;
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
        if(operation.nextPoint(true))
            nextActivity(true, ChoiceCheckActivity.this);
        else {
            onClickCommon();
            unFillScreen(true, true);
        }
    }

    @Override
    @OnClick(R.id.failureView)
    public void onClickFailureView() {
        if(operation.nextPoint(false))
            nextActivity(false, ChoiceCheckActivity.this);
        else {
            onClickCommon();
            unFillScreen(false, true);
        }
    }

    @Override
    @OnClick(R.id.fab)
    public void onClickFab() {
        if(!isScreenFiled()) {
            stopTime();
            fillScreen(selected == result, true);
        }
        else
        {
            if(selected==result)
                onClickSuccessView();
            else
                onClickFailureView();
        }
    }

    @Override
    public void onClickCommon() {
        stackBricksElementsOperation.hideStack(bricksArray);
        createReadyView();
    }

    @Override
    public void createViews() {
        super.createViews();

        operation = getOperation();
        startAnim(this, R.id.content_choice);
        View stackBricksContainer = findViewById(R.id.stack_container);
        bricksArray = stackBricksElementsCreator.createBricksStack(stackBricksContainer);
        currentNumber = stackBricksElementsCreator.createTextView(stackBricksContainer);
        createReadyView();
    }

    @Override
    public void createReadyView()
    {
        currentNumber.setVisibility(View.INVISIBLE);
        Random random = new Random();
        result = random.nextInt(11);
        stackBricksElementsOperation.showStack(bricksArray, result);
        startTime();
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
