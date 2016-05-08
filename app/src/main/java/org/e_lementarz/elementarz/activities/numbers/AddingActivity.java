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

import butterknife.Bind;
import butterknife.OnClick;

public class AddingActivity extends ElementarzNumbersActivity {

    private int counter = 0;
    private int result;
    private boolean screenFiled = false;
    private boolean firstChange = true;
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
    private MorphingAnimation morphingAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding);
        createViews();
    }

    @Override
    @OnClick(R.id.fab)
    public void onClickFab() {
        if (screenFiled)
            onClickSuccessView();
        else if (counter == result) {
            fillScreen(true, false);
            screenFiled = true;
        }
    }

    private void createReadyView() {
        Random random = new Random();
        int firstRand = random.nextInt(5) + 1;
        int secondRand = random.nextInt(5) + 1;
        stackBricksElementsOperation.showStack(bricksFirstArray, firstRand);
        stackBricksElementsOperation.showStack(bricksSecondArray, secondRand);
        stackBricksElementsOperation.hideStack(bricksResultArray);
        counter = 0;
        stackBricksElementsOperation.refreshText(firstStackCounterTV, firstRand);
        stackBricksElementsOperation.refreshText(secondStackCounterTV, secondRand);
        stackBricksElementsOperation.refreshText(resultStackCounterTV, counter);
        result = firstRand + secondRand;
    }

    @OnClick(R.id.addingPlusBtn)
    void add() {
        if (counter < 10) {
            stackBricksElementsOperation.refreshStack(bricksResultArray, counter, getApplicationContext());
            counter++;
            stackBricksElementsOperation.refreshText(resultStackCounterTV, counter);
            if (counter != result && firstChange) {
                morphingAnimation.animFab(R.drawable.ic_done_to_undone, R.color.colorFailureRed);
                firstChange = false;
            }
            if (counter == result) {
                morphingAnimation.animFab(R.drawable.ic_undone_to_done, R.color.colorSuccessGreen);
                firstChange = true;
            }
        }
    }

    @OnClick(R.id.addingMinusBtn)
    void remove() {
        if (counter > 0) {
            counter--;
            stackBricksElementsOperation.refreshStack(bricksResultArray, counter, getApplicationContext());
            stackBricksElementsOperation.refreshText(resultStackCounterTV, counter);
            if (counter != result && firstChange) {
                morphingAnimation.animFab(R.drawable.ic_done_to_undone, R.color.colorFailureRed);
                firstChange = false;
            }
            if (counter == result) {
                morphingAnimation.animFab(R.drawable.ic_undone_to_done, R.color.colorSuccessGreen);
                firstChange = true;
            }
        }
    }

    @Override
    @OnClick(R.id.successView)
    public void onClickSuccessView() {
        nextActivity(true, AddingActivity.this, AddingCheckActivity.class);
    }

    @Override
    public void createViews() {
        super.createViews();
        Random random = new Random();
        int firstRand = random.nextInt(6);
        int secondRand = random.nextInt(6);

        startAnim(this, R.id.content_adding);
        morphingAnimation = new MorphingAnimation(getApplicationContext(), getFab());
        firstStackCounterTV = (TextView) includeFirstStack.findViewById(R.id.bricksTV);
        secondStackCounterTV = (TextView) includeSecondStack.findViewById(R.id.bricksTV);
        resultStackCounterTV = (TextView) includeResultStack.findViewById(R.id.bricksTV);
        bricksFirstArray = stackBricksElementsCreator.createBricksStack(includeFirstStack, firstRand);
        bricksSecondArray = stackBricksElementsCreator.createBricksStack(includeSecondStack, secondRand);
        bricksResultArray = stackBricksElementsCreator.createBricksStack(includeResultStack);
        createReadyView();
    }
}