package org.e_lementarz.elementarz.activities.numbers;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.e_lementarz.elementarz.R;
import org.e_lementarz.elementarz.common.ElementarzNumbersActivity;
import org.e_lementarz.elementarz.common.MorphingAnimation;
import org.e_lementarz.elementarz.common.StackBricksElementsCreator;
import org.e_lementarz.elementarz.common.StackBricksElementsOperation;

import java.util.Random;

import butterknife.OnClick;

public class ChoiceActivity extends ElementarzNumbersActivity {

    private int selected = -1;
    private int result = -1;
    private boolean screenFiled = false;
    private boolean firstAnim = true;
    private StackBricksElementsCreator stackBricksElementsCreator = new StackBricksElementsCreator();
    private StackBricksElementsOperation stackBricksElementsOperation = new StackBricksElementsOperation();
    private MorphingAnimation morphingAnimation;
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
        nextActivity(true, ChoiceActivity.this);
    }

    @Override
    @OnClick(R.id.fab)
    public void onClickFab() {
        if (screenFiled)
            onClickSuccessView();
        else if (selected == result) {
            fillScreen(true, false);
            screenFiled = true;
        }
    }

    private void checkSize(int n) {
        selected = n;
        if(firstAnim)
        {
            currentNumber.setVisibility(View.VISIBLE);
            if (result == selected)
                morphingAnimation.animFab(R.drawable.ic_line_to_done, R.color.colorSuccessGreen);
            else
                morphingAnimation.animFab(R.drawable.ic_line_to_undone, R.color.colorFailureRed);
            firstAnim=false;
        }
        else {
            if (result == selected)
                morphingAnimation.animFab(R.drawable.ic_undone_to_done, R.color.colorSuccessGreen);
            else
                morphingAnimation.animFab(R.drawable.ic_done_to_undone, R.color.colorFailureRed);
        }
        stackBricksElementsOperation.refreshText(currentNumber, selected);
    }

    @Override
    public void createViews() {
        super.createViews();

        startAnim(this, R.id.content_choice);
        morphingAnimation = new MorphingAnimation(getApplicationContext(), getFab());
        View stackBricksContainer = findViewById(R.id.stack_container);
        View[] bricksArray = stackBricksElementsCreator.createBricksStack(stackBricksContainer);
        currentNumber = stackBricksElementsCreator.createTextView(stackBricksContainer);
        currentNumber.setVisibility(View.INVISIBLE);
        Random random = new Random();
        result = random.nextInt(11);
        stackBricksElementsOperation.showStack(bricksArray, result);
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