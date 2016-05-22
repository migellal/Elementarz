package org.e_lementarz.elementarz.common.extend;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import org.e_lementarz.elementarz.R;
import org.e_lementarz.elementarz.activities.MenuActivity;
import org.e_lementarz.elementarz.activities.numbers.AddingActivity;
import org.e_lementarz.elementarz.activities.numbers.AddingCheckActivity;
import org.e_lementarz.elementarz.activities.numbers.BricksActivity;
import org.e_lementarz.elementarz.activities.numbers.BricksOrderActivity;
import org.e_lementarz.elementarz.activities.numbers.ChoiceActivity;
import org.e_lementarz.elementarz.activities.numbers.ChoiceCheckActivity;
import org.e_lementarz.elementarz.activities.numbers.CompareActivity;
import org.e_lementarz.elementarz.activities.numbers.CompareCheckActivity;
import org.e_lementarz.elementarz.activities.numbers.mainly.NumbersResultActivity;
import org.e_lementarz.elementarz.common.Const;
import org.e_lementarz.elementarz.common.interfaces.ElementarzNumbersHelper;
import org.e_lementarz.elementarz.common.operations.MorphingAnimation;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by micha on 19.03.2016.
 */
public abstract class ElementarzNumbersActivity extends AppCompatActivity implements ElementarzNumbersHelper {

    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.layoutSuccess)
    ViewGroup layoutSuccess;
    @Bind(R.id.successView)
    ImageView successView;
    @Bind(R.id.layoutFailure)
    ViewGroup layoutFailure;
    @Bind(R.id.failureView)
    ImageView failureView;

    private int centerX, centerY, startRadius, endRadius;
    private boolean practice = false;
    private boolean test = false;
    private boolean screenFiled = false;

    public void startAnim(Activity rootContainer, int layoutID) {
        View view = rootContainer.findViewById(layoutID);
        Animation animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);
        view.startAnimation(animFadeIn);
    }


    public Animator fillScreen(boolean success, boolean animMorph) {
        screenFiled = true;
        Animator anim;
        MorphingAnimation morphingAnimation = new MorphingAnimation(getApplicationContext(), fab);
        if (success) {
            anim = ViewAnimationUtils.createCircularReveal(layoutSuccess, centerX, centerY, startRadius, endRadius);
            layoutSuccess.setVisibility(View.VISIBLE);
            if (animMorph)
                morphingAnimation.animFab(R.drawable.ic_line_to_done, R.color.colorSuccessGreen);
        } else {
            anim = ViewAnimationUtils.createCircularReveal(layoutFailure, centerX, centerY, startRadius, endRadius);
            layoutFailure.setVisibility(View.VISIBLE);
            if (animMorph)
                morphingAnimation.animFab(R.drawable.ic_line_to_undone, R.color.colorFailureRed);
        }
        anim.setDuration(Const.ANIM_LONG);
        anim.start();
        return anim;
    }

    public Animator unFillScreen(boolean success, boolean animMorph) {
        screenFiled = false;
        Animator anim;
        MorphingAnimation morphingAnimation = new MorphingAnimation(getApplicationContext(), fab);
        if (success) {
            anim = ViewAnimationUtils.createCircularReveal(layoutSuccess, centerX, centerY, endRadius, startRadius);
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    layoutSuccess.setVisibility(View.GONE);
                }
            });
            if (animMorph)
                morphingAnimation.animFab(R.drawable.ic_done_to_line, R.color.colorAccent);
        } else {
            anim = ViewAnimationUtils.createCircularReveal(layoutFailure, centerX, centerY, endRadius, startRadius);
            anim.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    layoutFailure.setVisibility(View.GONE);
                }
            });
            if (animMorph)
                morphingAnimation.animFab(R.drawable.ic_undone_to_line, R.color.colorAccent);
        }
        anim.setDuration(Const.ANIM_LONG);
        anim.start();
        return anim;
    }

    public void nextActivity(boolean success, final Context packageContext) {
        ValueAnimator colorAnim;
        if (success) {
            MorphingAnimation morphingAnimation = new MorphingAnimation(getApplicationContext(), fab, successView);
            morphingAnimation.animImageView(R.drawable.ic_done_to_line);
            morphingAnimation.animFab(R.drawable.ic_done_to_line, R.color.colorAccent);
            colorAnim = ObjectAnimator.ofInt(layoutSuccess, "backgroundColor",
                    getResources().getColor(R.color.colorSuccessGreen), getResources().getColor(R.color.icons));
        } else {
            MorphingAnimation morphingAnimation = new MorphingAnimation(getApplicationContext(), fab, failureView);
            morphingAnimation.animImageView(R.drawable.ic_undone_to_line);
            morphingAnimation.animFab(R.drawable.ic_undone_to_line, R.color.colorAccent);
            colorAnim = ObjectAnimator.ofInt(layoutFailure, "backgroundColor",
                    getResources().getColor(R.color.colorFailureRed), getResources().getColor(R.color.icons));
        }
        colorAnim.setDuration(400);
        colorAnim.setEvaluator(new ArgbEvaluator());
        colorAnim.start();
        colorAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Intent intent = new Intent(packageContext, getNextActivity(packageContext));
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });
    }


    public void createViews() {
        setNaviBarColor();
        calculateValues();
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setNaviBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            getWindow().setNavigationBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
    }


    private void calculateValues() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        centerX = size.x;
        centerY = size.y;
        startRadius = 0;
        double valueToSqrt = Math.pow((double) centerX, 2) + Math.pow((double) centerY, 2);
        endRadius = (int) Math.sqrt(valueToSqrt);
    }

    private Class getNextActivity(Context context) {
        if(test)
            return NumbersResultActivity.class;
        else if (context.getClass().equals(BricksActivity.class))
            return BricksOrderActivity.class;
        else if (context.getClass().equals(BricksOrderActivity.class))
            return ChoiceActivity.class;
        else if (context.getClass().equals(ChoiceActivity.class))
            return ChoiceCheckActivity.class;
        else if (context.getClass().equals(ChoiceCheckActivity.class))
            return CompareActivity.class;
        else if (context.getClass().equals(CompareActivity.class))
            return CompareCheckActivity.class;
        else if (context.getClass().equals(CompareCheckActivity.class))
            return AddingActivity.class;
        else if (context.getClass().equals(AddingActivity.class))
            return AddingCheckActivity.class;
        else
            return MenuActivity.class;
    }

    public FloatingActionButton getFab() {
        return fab;
    }

    public void setFab(FloatingActionButton fab) {
        this.fab = fab;
    }

    public ViewGroup getLayoutSuccess() {
        return layoutSuccess;
    }

    public void setLayoutSuccess(ViewGroup layoutSuccess) {
        this.layoutSuccess = layoutSuccess;
    }

    public ImageView getSuccessView() {
        return successView;
    }

    public void setSuccessView(ImageView successView) {
        this.successView = successView;
    }

    public ViewGroup getLayoutFailure() {
        return layoutFailure;
    }

    public void setLayoutFailure(ViewGroup layoutFailure) {
        this.layoutFailure = layoutFailure;
    }

    public ImageView getFailureView() {
        return failureView;
    }

    public void setFailureView(ImageView failureView) {
        this.failureView = failureView;
    }

    public boolean isPractice() {
        return practice;
    }

    public void setPractice(boolean practice) {
        this.practice = practice;
    }

    public boolean isTest() {
        return test;
    }

    public void setTest(boolean test) {
        this.test = test;
    }

    public boolean isScreenFiled() {
        return screenFiled;
    }

    public void setScreenFiled(boolean screenFiled) {
        this.screenFiled = screenFiled;
    }
}
