package org.e_lementarz.elementarz.common;

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
import android.view.Display;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import org.e_lementarz.elementarz.R;

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

    int centerX, centerY, startRadius, endRadius;
    ImageView[] stars;
    boolean starsContainerCreated = false;
    int starsCounter = 0;

    public void startAnim(Activity rootContainer, int layoutID) {
        View view = rootContainer.findViewById(layoutID);
        Animation animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fade_in);
        view.startAnimation(animFadeIn);
    }


    public Animator fillScreen(boolean success, boolean animMorph) {
        Animator anim;
        MorphingAnimation morphingAnimation = new MorphingAnimation(getApplicationContext(), fab);
        if (success) {
            anim = ViewAnimationUtils.createCircularReveal(layoutSuccess, centerX, centerY, startRadius, endRadius);
            layoutSuccess.setVisibility(View.VISIBLE);
            if(animMorph)
                morphingAnimation.animFab(R.drawable.ic_line_to_done);
        } else {
            anim = ViewAnimationUtils.createCircularReveal(layoutFailure, centerX, centerY, startRadius, endRadius);
            layoutFailure.setVisibility(View.VISIBLE);
            if(animMorph)
                morphingAnimation.animFab(R.drawable.ic_line_to_undone, R.color.colorFailureRed);
        }
        anim.setDuration(Const.ANIM_LONG);
        anim.start();
        return anim;
    }

    public Animator unFillScreen(boolean success, boolean animMorph) {
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
            if(animMorph)
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
            if(animMorph)
                morphingAnimation.animFab(R.drawable.ic_undone_to_line, R.color.colorAccent);
        }
        anim.setDuration(Const.ANIM_LONG);
        anim.start();
        return anim;
    }

    public void nextActivity(boolean success, final Context packageContext, final Class<?> cls) {
        ValueAnimator colorAnim;
        if(success) {
            MorphingAnimation morphingAnimation = new MorphingAnimation(getApplicationContext(), fab, successView);
            morphingAnimation.animImageView(R.drawable.ic_done_to_line);
            morphingAnimation.animFab(R.drawable.ic_done_to_line, R.color.colorAccent);
            colorAnim = ObjectAnimator.ofInt(layoutSuccess, "backgroundColor",
                    getResources().getColor(R.color.colorSuccessGreen), getResources().getColor(R.color.icons));
        }
        else
        {
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
                Intent intent = new Intent(packageContext, cls);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
            }
        });
    }

    public void nextStar(Activity rootContainer, boolean good)
    {
        if(!starsContainerCreated) {
            ImageView star1 = (ImageView) rootContainer.findViewById(R.id.starIV1);
            ImageView star2 = (ImageView) rootContainer.findViewById(R.id.starIV2);
            ImageView star3 = (ImageView) rootContainer.findViewById(R.id.starIV3);
            ImageView star4 = (ImageView) rootContainer.findViewById(R.id.starIV4);
            ImageView star5 = (ImageView) rootContainer.findViewById(R.id.starIV5);
            stars = new ImageView[]{star1, star2, star3, star4, star5};
            starsContainerCreated = true;
        }
        if (!good)
            stars[starsCounter].setImageResource(R.drawable.ic_star_border_black_48dp);
        stars[starsCounter].setVisibility(View.VISIBLE);
        starsCounter++;
    }


    public void createViews() {
        setNaviBarColor();
        calculateValues();
        ButterKnife.bind(this);
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
}
