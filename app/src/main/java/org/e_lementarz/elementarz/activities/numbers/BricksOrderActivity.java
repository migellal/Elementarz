package org.e_lementarz.elementarz.activities.numbers;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.e_lementarz.elementarz.R;
import org.e_lementarz.elementarz.common.ElementarzNumbersActivity;
import org.e_lementarz.elementarz.common.StackBricksElementsCreator;
import org.e_lementarz.elementarz.common.StackBricksElementsOperation;
import org.e_lementarz.elementarz.common.ElementarzNumbersHelper;

import butterknife.OnClick;

public class BricksOrderActivity extends ElementarzNumbersActivity implements ElementarzNumbersHelper {

    private int counter = 0;
    private View[] bricksStacks;
    private StackBricksElementsOperation stackBricksElementsOperation = new StackBricksElementsOperation();
    private StackBricksElementsCreator stackBricksElementsCreator = new StackBricksElementsCreator();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bricks_order);
        createViews();
    }

    @Override
    @OnClick(R.id.fab)
    public void onClickFab() {
        if (counter < 10) {
            View[] bricksArray = stackBricksElementsCreator.createBricksStack(bricksStacks[counter], counter);
            TextView textView = stackBricksElementsCreator.createTextView(bricksStacks[counter]);
            stackBricksElementsOperation.refreshStackOnlyAdding(bricksArray, counter, getApplicationContext());
            counter++;
            stackBricksElementsOperation.refreshText(textView, counter);
        } else if (counter == 10) {
            counter++;
            fillScreen(true, true);
        }
        else
            onClickSuccessView();
    }

    @Override
    @OnClick(R.id.successView)
    public void onClickSuccessView() {
        nextActivity(true, BricksOrderActivity.this);
    }

    @Override
    public void createViews() {
        super.createViews();

        startAnim(this, R.id.content_bricks_order);
        bricksStacks = stackBricksElementsCreator.createContainers(this);
    }

}
