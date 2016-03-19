package org.e_lementarz.elementarz;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class NumbersBricksActivity extends AppCompatActivity {

    private static int counter = 0;
    private TextView counterView;
    private View bricks1;
    private View bricks2;
    private View bricks3;
    private View bricks4;
    private View bricks5;
    private View bricks6;
    private View bricks7;
    private View bricks8;
    private View bricks9;
    private View bricks10;
    private View[] bricksArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers_bricks);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        counterView = (TextView) findViewById(R.id.currentValueTV);
        bricks1 = findViewById(R.id.brick1V);
        bricks2 = findViewById(R.id.brick2V);
        bricks3 = findViewById(R.id.brick3V);
        bricks4 = findViewById(R.id.brick4V);
        bricks5 = findViewById(R.id.brick5V);
        bricks6 = findViewById(R.id.brick6V);
        bricks7 = findViewById(R.id.brick7V);
        bricks8 = findViewById(R.id.brick8V);
        bricks9 = findViewById(R.id.brick9V);
        bricks10 = findViewById(R.id.brick10V);
        bricksArray = new View[]{null, bricks1, bricks2, bricks3, bricks4, bricks5, bricks6,
                bricks7, bricks8, bricks9, bricks10};
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addNumberFAB);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counter<10) {
                    counter++;
                    refreshView();
                }
            }
        });
        Button minus = (Button) findViewById(R.id.minusBtn);
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(counter!=0)
                {
                    counter--;
                    refreshView();
                }
            }
        });
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        refreshView();
    }

    private void refreshView()
    {
        counterView.setText(""+counter);
        for(int i = 1; i<=10; i++)
        {
            if(i<=counter)
                bricksArray[i].setVisibility(View.VISIBLE);
            else
                bricksArray[i].setVisibility(View.INVISIBLE);
        }
    }

}
