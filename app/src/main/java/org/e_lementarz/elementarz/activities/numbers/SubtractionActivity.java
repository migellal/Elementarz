package org.e_lementarz.elementarz.activities.numbers;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.e_lementarz.elementarz.R;
import org.e_lementarz.elementarz.common.extend.ElementarzNumbersActivity;
import org.e_lementarz.elementarz.common.operations.MorphingAnimation;
import org.e_lementarz.elementarz.common.operations.StackBricksElementsCreator;
import org.e_lementarz.elementarz.common.operations.StackBricksElementsOperation;

import java.util.Random;

import butterknife.Bind;
import butterknife.OnClick;

public class SubtractionActivity extends ElementarzNumbersActivity {

    @Bind(R.id.includeFirstStack)
    View includeFirstStack;
    @Bind(R.id.includeSecondStack)
    View includeSecondStack;
    @Bind(R.id.includeResultStack)
    View includeResultStack;
    private int counter = 0;
    private int result = 0;
    private boolean firstChange = true;
    private View[] bricksResultArray;
    private TextView resultStackCounterTV;
    private StackBricksElementsCreator stackBricksElementsCreator = new StackBricksElementsCreator();
    private StackBricksElementsOperation stackBricksElementsOperation = new StackBricksElementsOperation();
    private MorphingAnimation morphingAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subtraction);
        createViews();
    }

    @OnClick(R.id.addingPlusBtn)
    void add() {
        if (counter < 10) {
            stackBricksElementsOperation.refreshStack(bricksResultArray, counter, getApplicationContext());
            counter++;
            stackBricksElementsOperation.refreshText(resultStackCounterTV, counter);
            changeFab();
        }
    }

    @OnClick(R.id.addingMinusBtn)
    void remove() {
        if (counter > 0) {
            counter--;
            stackBricksElementsOperation.refreshStack(bricksResultArray, counter, getApplicationContext());
            stackBricksElementsOperation.refreshText(resultStackCounterTV, counter);
            changeFab();
        }
    }

    @Override
    @OnClick(R.id.successView)
    public void onClickSuccessView() {
        nextActivity(true, SubtractionActivity.this);
    }

    @Override
    @OnClick(R.id.fab)
    public void onClickFab() {
        if (isScreenFiled())
            onClickSuccessView();
        else if (counter == result)
            fillScreen(true, false);
    }

    @Override
    public void createViews() {
        super.createViews();
        Random random = new Random();
        int firstRand = random.nextInt(10)+1;
        int secondRand = random.nextInt(firstRand+1);

        startAnim(this, R.id.content_subtraction);
        morphingAnimation = new MorphingAnimation(getApplicationContext(), getFab());
        TextView firstStackCounterTV = (TextView) includeFirstStack.findViewById(R.id.bricksTV);
        TextView secondStackCounterTV = (TextView) includeSecondStack.findViewById(R.id.bricksTV);
        resultStackCounterTV = (TextView) includeResultStack.findViewById(R.id.bricksTV);
        stackBricksElementsCreator.createBricksStack(includeFirstStack, firstRand);
        stackBricksElementsCreator.createBricksStack(includeSecondStack, secondRand);
        bricksResultArray = stackBricksElementsCreator.createBricksStack(includeResultStack);
        stackBricksElementsOperation.refreshText(firstStackCounterTV, firstRand);
        stackBricksElementsOperation.refreshText(secondStackCounterTV, secondRand);
        stackBricksElementsOperation.refreshText(resultStackCounterTV, counter);
        result = firstRand - secondRand;
    }

    private void changeFab()
    {
        if (firstChange && counter == result)
            morphingAnimation.animFab(R.drawable.ic_line_to_done, R.color.colorSuccessGreen);
        else if (firstChange && counter != result)
            morphingAnimation.animFab(R.drawable.ic_line_to_undone, R.color.colorFailureRed);
        else if (!firstChange && counter == result)
            morphingAnimation.animFab(R.drawable.ic_undone_to_done, R.color.colorSuccessGreen);
        else if (!firstChange && counter != result)
            morphingAnimation.animFab(R.drawable.ic_done_to_undone, R.color.colorFailureRed);
        firstChange = false;
    }
}
