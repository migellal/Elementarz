package org.e_lementarz.elementarz.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.e_lementarz.elementarz.R;
import org.e_lementarz.elementarz.common.Const;
import org.e_lementarz.elementarz.common.ElementarzActivity;
import org.e_lementarz.elementarz.common.StackBricksViewGroup;

public class NumbersCompareActivity extends ElementarzActivity {

    private TextView line;
    private int counterLeft = 0;
    private int counterRight = 0;
    private View[] bricksLeftArray;
    private View[] bricksRightArray;
    private StackBricksViewGroup stackBricksViewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers_compare);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setNaviBarColor();
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        stackBricksViewGroup = new StackBricksViewGroup();
        View stackBricksContainerLeft = findViewById(R.id.includeLeftStack);
        View stackBricksContainerRight = findViewById(R.id.includeRightStack);
        bricksLeftArray = stackBricksViewGroup.getBricksStack(stackBricksContainerLeft);
        bricksRightArray = stackBricksViewGroup.getBricksStack(stackBricksContainerRight);
        if (savedInstanceState != null) {
            counterLeft = savedInstanceState.getInt(Const.LEFT_COUNTER);
            counterRight = savedInstanceState.getInt(Const.RIGHT_COUNTER);
            if (counterLeft != 0)
                stackBricksViewGroup.refreshView(counterLeft, bricksLeftArray, null, true);
            if (counterRight != 0)
                stackBricksViewGroup.refreshView(counterRight, bricksRightArray, null, true);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt(Const.LEFT_COUNTER, counterLeft);
        outState.putInt(Const.RIGHT_COUNTER, counterRight);
    }

    public void onClickCompareOperations(View view) {
        switch (view.getId()) {
            case R.id.plusLeftBtn:
                if (counterLeft < 10) {
                    counterLeft++;
                    stackBricksViewGroup.refreshView(counterLeft, bricksLeftArray, null, true);
                }
                break;
            case R.id.minusLeftBtn:
                if (counterLeft > 0) {
                    counterLeft--;
                    stackBricksViewGroup.refreshView(counterLeft, bricksLeftArray, null, true);
                }
                break;
            case R.id.plusRightBtn:
                if (counterRight < 10) {
                    counterRight++;
                    stackBricksViewGroup.refreshView(counterRight, bricksRightArray, null, true);
                }
                break;
            case R.id.minusRightBtn:
                if (counterRight > 0) {
                    counterRight--;
                    stackBricksViewGroup.refreshView(counterRight, bricksRightArray, null, true);
                }
                break;
        }
    }
}
