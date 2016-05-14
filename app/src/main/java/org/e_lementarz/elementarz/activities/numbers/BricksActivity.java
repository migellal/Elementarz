package org.e_lementarz.elementarz.activities.numbers;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.e_lementarz.elementarz.R;
import org.e_lementarz.elementarz.common.ElementarzNumbersActivity;
import org.e_lementarz.elementarz.common.StackBricksElementsCreator;
import org.e_lementarz.elementarz.common.StackBricksElementsOperation;

import butterknife.OnClick;

public class BricksActivity extends ElementarzNumbersActivity {

    private int counter = 0;
    private TextView counterView;
    private View[] bricksArray;
    private StackBricksElementsOperation stackBricksElementsOperation = new StackBricksElementsOperation();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bricks);
        createViews();
    }

    @Override
    @OnClick(R.id.fab)
    public void onClickFab() {
        if (counter < 10) {
            stackBricksElementsOperation.refreshStack(bricksArray, counter, getApplicationContext());
            counter++;
            stackBricksElementsOperation.refreshText(counterView, counter);
        } else if (counter == 10) {
            counter++;
            fillScreen(true, true);
        }
        else
            onClickSuccessView();
    }

    @OnClick(R.id.minusBtn)
    void remove() {
        if (counter > 0) {
            counter--;
            stackBricksElementsOperation.refreshStack(bricksArray, counter, getApplicationContext());
            stackBricksElementsOperation.refreshText(counterView, counter);
        }
    }

    @Override
    @OnClick(R.id.successView)
    public void onClickSuccessView() {
        nextActivity(true, BricksActivity.this);
    }

    @Override
    public void createViews() {
        super.createViews();

        StackBricksElementsCreator stackBricksElementsCreator = new StackBricksElementsCreator();
        View stackBricksContainer = findViewById(R.id.stack_bricks);
        bricksArray = stackBricksElementsCreator.createBricksStack(stackBricksContainer);
        counterView = stackBricksElementsCreator.createTextView(stackBricksContainer);
    }


}
