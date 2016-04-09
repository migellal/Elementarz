package org.e_lementarz.elementarz.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.e_lementarz.elementarz.R;
import org.e_lementarz.elementarz.common.ElementarzActivity;
import org.e_lementarz.elementarz.common.MorphingAnimation;
import org.e_lementarz.elementarz.common.StackBricks;

public class NumbersOrderActivity extends ElementarzActivity {

    private int counter = 0;
    private View[] bricksStacks;
    private View[] bricksArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers_order);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final StackBricks stackBricksViewGroup = new StackBricks();
        bricksStacks = stackBricksViewGroup.getContainers(this);
        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counter<10) {
                    counter++;
                    bricksArray = stackBricksViewGroup.getBricksStack(bricksStacks[counter], counter);
                    stackBricksViewGroup.refreshAllViewsOnlyAdding(counter, bricksArray, getApplicationContext());
                    if(counter==10) {
                        MorphingAnimation morphAnim = new MorphingAnimation(getApplicationContext(), fab);
                        morphAnim.animFab(R.drawable.ic_line_to_done);
                    }
                }
            }
        });
        setNaviBarColor();
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
