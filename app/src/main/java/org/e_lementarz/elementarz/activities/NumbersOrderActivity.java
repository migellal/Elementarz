package org.e_lementarz.elementarz.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import org.e_lementarz.elementarz.R;
import org.e_lementarz.elementarz.common.ElementarzActivity;
import org.e_lementarz.elementarz.common.StackBricksViewGroup;

public class NumbersOrderActivity extends ElementarzActivity {

    private int counter = 0;
    private View[] bricksStacks;
    private View[] bricksArray;
    private Animation animPut, animMorph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        animPut = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.put_anim);
        final StackBricksViewGroup stackBricksViewGroup = new StackBricksViewGroup();
        bricksStacks = stackBricksViewGroup.getContainers(this);
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counter<10) {
                    counter++;
                    bricksArray = stackBricksViewGroup.getBricksStack(bricksStacks[counter], counter);
                    stackBricksViewGroup.refreshAllViewsOnlyAdding(counter, bricksArray, animPut);
                    if(counter==10) {
                        fab.setImageResource(R.drawable.ic_done_white_48dp);
                    }
                }
            }
        });
        setNaviBarColor();
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
