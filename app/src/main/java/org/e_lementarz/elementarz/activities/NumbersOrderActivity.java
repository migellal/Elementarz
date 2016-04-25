package org.e_lementarz.elementarz.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.e_lementarz.elementarz.R;
import org.e_lementarz.elementarz.common.ElementarzActivity;
import org.e_lementarz.elementarz.common.MorphingAnimation;
import org.e_lementarz.elementarz.common.StackBricksCreator;
import org.e_lementarz.elementarz.common.StackBricksView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NumbersOrderActivity extends ElementarzActivity {

    private int counter = 0;
    private View[] bricksStacks;
    private View[] bricksArray;
    @Bind(R.id.layoutSuccess)
    View layoutSuccess;
    @Bind(R.id.contentNumbersOrderLayout)
    LinearLayout layoutContentNumbersOrder;
    @Bind(R.id.successView)
    ImageView successV;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        final StackBricksCreator stackBricksCreator = new StackBricksCreator();
        final StackBricksView stackBricksView = new StackBricksView();
        bricksStacks = stackBricksCreator.createContainers(this);
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        this.fab = fab;
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter < 10) {
                    counter++;
                    bricksArray = stackBricksCreator.createBricksStack(bricksStacks[counter], counter);
                    stackBricksView.refreshAllViewsOnlyAdding(bricksArray, counter, getApplicationContext());
                    if (counter == 10) {
                        animateFab(R.drawable.ic_line_to_done, true);
                    }
                } else if (counter == 10) {
                    counter++;
                    double valueToSqrt = Math.pow((double) layoutContentNumbersOrder.getWidth(), 2) + Math.pow((double) layoutContentNumbersOrder.getHeight(), 2);
                    int endRadius = (int) Math.sqrt(valueToSqrt);
                    Animator anim =
                            ViewAnimationUtils.createCircularReveal(layoutSuccess, layoutContentNumbersOrder.getRight(), layoutContentNumbersOrder.getBottom(), 0, endRadius);
                    layoutSuccess.setVisibility(View.VISIBLE);
                    anim.start();
                } else {
                    clickSuccessView();
                }
            }
        });
        setNaviBarColor();
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @OnClick(R.id.successView)
    void onClickSuccessView() {
        clickSuccessView();
    }

    private void clickSuccessView() {
        MorphingAnimation morphingAnimationView = new MorphingAnimation(getApplicationContext(), successV);
        morphingAnimationView.animImageView(R.drawable.ic_done_to_line);
        animateFab(R.drawable.ic_done_to_line, false);
        ValueAnimator colorAnim = ObjectAnimator.ofInt(layoutSuccess, "backgroundColor",
                getResources().getColor(R.color.colorSuccessGreen), getResources().getColor(R.color.icons));
        colorAnim.setDuration(500);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.start();
        colorAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Intent intent = new Intent(NumbersOrderActivity.this, NumbersCompareActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });

    }

    private void animateFab(int animId, boolean green) {
        MorphingAnimation morphAnim = new MorphingAnimation(getApplicationContext(), fab);
        if(green)
            morphAnim.animFab(animId, R.color.colorSuccessGreen);
        else
            fab.setBackgroundTintList(getApplicationContext().getResources().getColorStateList(R.color.colorAccent));
    }

}
