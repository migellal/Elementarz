package org.e_lementarz.elementarz.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.e_lementarz.elementarz.R;
import org.e_lementarz.elementarz.common.ElementarzActivity;
import org.e_lementarz.elementarz.common.MorphingAnimation;
import org.e_lementarz.elementarz.common.StackBricksCreator;
import org.e_lementarz.elementarz.common.StackBricksView;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NumbersBricksAdding extends ElementarzActivity {

    @Bind(R.id.includeFirstStack)
    View includeFirstStack;
    @Bind(R.id.includeSecondStack)
    View includeSecondStack;
    @Bind(R.id.includeResultStack)
    View includeResultStack;
    private View[] bricksFirstArray;
    private View[] bricksSecondArray;
    private View[] bricksResultArray;
    @Bind({R.id.starIV1, R.id.starIV2, R.id.starIV3, R.id.starIV4, R.id.starIV5})
    ImageView[] starContainer;
    private TextView firstStackCounterTV;
    private TextView secondStackCounterTV;
    private TextView resultStackCounterTV;
    private StackBricksCreator stackBricksCreator = new StackBricksCreator();
    private StackBricksView stackBricksView = new StackBricksView();
    private Random random = new Random();
    private int counter = 0;
    private int firstRand;
    private int secondRand;
    private int result;
    private int starCounter = 0;
    private MorphingAnimation morphAnim;
    private boolean firstRun = true;
    @Bind(R.id.contentNumbersBricksAddingRL)
    RelativeLayout contentNumbersBricksAdding;
    @Bind(R.id.layoutSuccess)
    View layoutSuccess;
    @Bind(R.id.layoutFailure)
    View layoutFailure;
    private int centerX;
    private int centerY;
    private int startRadius = 0;
    private int endRadius;
    private FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers_bricks_adding);
        setNaviBarColor();
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        morphAnim = new MorphingAnimation(getApplicationContext(), fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (starCounter < 5) {
                    calculateValues();
                    if (counter == result) {
                        morphAnim.animFab(R.drawable.ic_line_to_done, true);
                        Animator anim =
                                ViewAnimationUtils.createCircularReveal(layoutSuccess, centerX, centerY, startRadius, endRadius);
                        layoutSuccess.setVisibility(View.VISIBLE);
                        anim.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                addStar(counter == result);
                                createReadyView();
                            }
                        });
                        anim.start();
                    } else {
                        morphAnim.animFab(R.drawable.ic_line_to_undone, false);
                        Animator anim =
                                ViewAnimationUtils.createCircularReveal(layoutFailure, centerX, centerY, startRadius, endRadius);
                        layoutFailure.setVisibility(View.VISIBLE);
                        anim.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                addStar(counter == result);
                                createReadyView();
                            }
                        });
                        anim.start();
                    }
                } else {
                    fab.setClickable(false);
                } // TODO start nowego activity
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        createReadyView();
    }

    private void createReadyView() {
        firstRand = random.nextInt(6);
        secondRand = random.nextInt(6);
        if (firstRun) {
            firstStackCounterTV = (TextView) includeFirstStack.findViewById(R.id.bricksTV);
            secondStackCounterTV = (TextView) includeSecondStack.findViewById(R.id.bricksTV);
            resultStackCounterTV = (TextView) includeResultStack.findViewById(R.id.bricksTV);
            bricksFirstArray = stackBricksCreator.createBricksStack(includeFirstStack, firstRand);
            bricksSecondArray = stackBricksCreator.createBricksStack(includeSecondStack, secondRand);
            bricksResultArray = stackBricksCreator.createBricksStack(includeResultStack);
            firstRun = false;
        } else {
            bricksFirstArray = stackBricksView.showStack(bricksFirstArray, firstRand);
            bricksSecondArray = stackBricksView.showStack(bricksSecondArray, secondRand);
            bricksResultArray = stackBricksView.hideStack(bricksResultArray);
            if(counter!=0)
                bricksResultArray[counter].setAlpha(0);
        }
        counter = 0;
        firstStackCounterTV.setText(String.valueOf(firstRand));
        secondStackCounterTV.setText(String.valueOf(secondRand));
        resultStackCounterTV.setText(String.valueOf(counter));
        result = firstRand + secondRand;
    }


    @OnClick(R.id.addingPlusBtn)
    void add() {
        if (counter < 10) {
            counter++;
            bricksResultArray[counter].setAlpha(1);
            stackBricksView.refreshView(bricksResultArray, counter, getApplicationContext());
            resultStackCounterTV.setText(String.valueOf(counter));
        }
    }

    @OnClick(R.id.addingMinusBtn)
    void remove() {
        if (counter > 0) {
            counter--;
            stackBricksView.refreshView(bricksResultArray, counter, getApplicationContext());
            resultStackCounterTV.setText(String.valueOf(counter));
        }
    }

    @OnClick(R.id.successView)
    void hideResultWhenSuccess() {
        Animator animate = ViewAnimationUtils.createCircularReveal(layoutSuccess, centerX, centerY, endRadius, startRadius);
        animate.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                layoutSuccess.setVisibility(View.GONE);
            }
        });
        animate.start();
        morphAnim.animFab(R.drawable.ic_done_to_line, R.color.colorAccent);
    }

    @OnClick(R.id.failureView)
    void hideResultWhenFailure() {
        Animator animate = ViewAnimationUtils.createCircularReveal(layoutFailure, centerX, centerY, endRadius, startRadius);
        animate.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                layoutFailure.setVisibility(View.GONE);
            }
        });
        animate.start();
        morphAnim.animFab(R.drawable.ic_undone_to_line, R.color.colorAccent);
    }

    private void addStar(boolean result) {
        if (!result)
            starContainer[starCounter].setImageResource(R.drawable.ic_star_border_black_48dp);
        starContainer[starCounter].setVisibility(View.VISIBLE);
        starCounter++;
    }

    void calculateValues() {
        centerX = contentNumbersBricksAdding.getRight();
        centerY = contentNumbersBricksAdding.getBottom();
        double valueToSqrt = Math.pow((double) contentNumbersBricksAdding.getWidth(), 2) + Math.pow((double) contentNumbersBricksAdding.getHeight(), 2);
        endRadius = (int) Math.sqrt(valueToSqrt);
    }

}