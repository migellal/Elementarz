package org.e_lementarz.elementarz.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers_bricks_adding);
        setNaviBarColor();
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        morphAnim = new MorphingAnimation(getApplicationContext(), fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (starCounter < 5) {
                    if (counter == result) {
                        morphAnim.animFab(R.drawable.ic_line_to_done, true);
                    } else {
                        morphAnim.animFab(R.drawable.ic_line_to_undone, false);
                    }
                    addStar(counter == result);
                    createReadyView();
                } else {
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
            bricksResultArray = stackBricksView.showStack(bricksResultArray);
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

    private void addStar(boolean result) {
        if (!result)
            starContainer[starCounter].setImageResource(R.drawable.ic_star_border_black_48dp);
        starContainer[starCounter].setVisibility(View.VISIBLE);
        starCounter++;
    }

}
