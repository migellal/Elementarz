package org.e_lementarz.elementarz.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.e_lementarz.elementarz.R;
import org.e_lementarz.elementarz.common.Const;
import org.e_lementarz.elementarz.common.ElementarzActivity;
import org.e_lementarz.elementarz.common.MorphingAnimation;
import org.e_lementarz.elementarz.common.StackBricksCreator;
import org.e_lementarz.elementarz.common.StackBricksView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class NumbersCompareActivity extends ElementarzActivity {

    private TextView line;
    private int counterLeft = 0;
    private int counterRight = 0;
    private View[] bricksLeftArray;
    private View[] bricksRightArray;
    private StackBricksCreator stackBricksCreator = new StackBricksCreator();
    private StackBricksView stackBricksView = new StackBricksView();
    private MorphingAnimation morphAnim;
    private boolean firstChange = true;
    @Bind(R.id.numbers_compare_content)
    RelativeLayout numbersCompareActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers_compare);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        setNaviBarColor();
        ButterKnife.bind(this);
        Animation animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);
        numbersCompareActivity.startAnimation(animFadeIn);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        View stackBricksContainerLeft = findViewById(R.id.includeLeftStack);
        View stackBricksContainerRight = findViewById(R.id.includeRightStack);
        bricksLeftArray = stackBricksCreator.createBricksStack(stackBricksContainerLeft);
        bricksRightArray = stackBricksCreator.createBricksStack(stackBricksContainerRight);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.checkFAB);
        assert fab != null;
        morphAnim = new MorphingAnimation(getApplicationContext(), fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO next activity
            }
        });


//        if (savedInstanceState != null) {
//            counterLeft = savedInstanceState.getInt(Const.LEFT_COUNTER);
//            counterRight = savedInstanceState.getInt(Const.RIGHT_COUNTER);
//            if (counterLeft != 0)
//                stackBricksCreator.refreshView(counterLeft, bricksLeftArray, getApplicationContext());
//            if (counterRight != 0)
//                stackBricksCreator.refreshView(counterRight, bricksRightArray, getApplicationContext());
//        }
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
                    stackBricksView.refreshView(bricksLeftArray, counterLeft, getApplicationContext());
                }
                break;
            case R.id.minusLeftBtn:
                if (counterLeft > 0) {
                    counterLeft--;
                    stackBricksView.refreshView(bricksLeftArray, counterLeft, getApplicationContext());
                }
                break;
            case R.id.plusRightBtn:
                if (counterRight < 10) {
                    counterRight++;
                    stackBricksView.refreshView(bricksRightArray, counterRight, getApplicationContext());
                }
                break;
            case R.id.minusRightBtn:
                if (counterRight > 0) {
                    counterRight--;
                    stackBricksView.refreshView(bricksRightArray, counterRight, getApplicationContext());
                }
                break;
        }
        if (counterLeft != counterRight && firstChange) {
            morphAnim.animFab(R.drawable.ic_done_to_undone, false);
            firstChange = false;
        }
        if (counterLeft == counterRight) {
            morphAnim.animFab(R.drawable.ic_undone_to_done, true);
            firstChange = true;
        }
    }
}
