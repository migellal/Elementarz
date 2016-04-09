package org.e_lementarz.elementarz.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.e_lementarz.elementarz.R;
import org.e_lementarz.elementarz.common.Const;
import org.e_lementarz.elementarz.common.ElementarzActivity;
import org.e_lementarz.elementarz.common.StackBricks;

public class NumbersBricksActivity extends ElementarzActivity {

    private int counter = 0;
    private TextView counterView;
    private View[] bricksArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setNaviBarColor();
        setContentView(R.layout.activity_numbers_bricks);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        counterView = (TextView) findViewById(R.id.bricksTV);
        final StackBricks stackBricksViewGroup = new StackBricks();
        final View stackBricksContainer = findViewById(R.id.stack_bricks);
        bricksArray = stackBricksViewGroup.getBricksStack(stackBricksContainer);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addNumberFAB);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counter<10) {
                    counter++;
                    stackBricksViewGroup.refreshView(counter, bricksArray, getApplicationContext());
                    refreshText();
                }
            }
        });
        Button minus = (Button) findViewById(R.id.minusBtn);
        assert minus != null;
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter > 0) {
                    counter--;
                    stackBricksViewGroup.refreshView(counter, bricksArray, getApplicationContext());
                    refreshText();
                }
            }
        });
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(savedInstanceState!=null) {
            counter = savedInstanceState.getInt(Const.COUNTER);
            if(counter!=0)
                stackBricksViewGroup.refreshView(counter, bricksArray, getApplicationContext());
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt(Const.COUNTER, counter);
    }

    private void refreshText()
    {
        String counterText = ""+counter;
        counterView.setText(counterText);
    }


}
