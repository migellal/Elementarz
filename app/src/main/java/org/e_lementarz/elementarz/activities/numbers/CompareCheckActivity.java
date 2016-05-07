package org.e_lementarz.elementarz.activities.numbers;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import org.e_lementarz.elementarz.R;
import org.e_lementarz.elementarz.common.ElementarzNumbersActivity;

import butterknife.OnClick;

public class CompareCheckActivity extends ElementarzNumbersActivity {

    private boolean nextActivity = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_check);
        createViews();
    }

    @Override
    @OnClick(R.id.successView)
    public void onClickSuccessView() {
        nextActivity(true, CompareCheckActivity.this, AddingActivity.class);
    }

    @Override
    @OnClick(R.id.fab)
    public void onClickFab() {
        if(nextActivity)
            onClickSuccessView();
        else if(!nextActivity)
        {
            fillScreen(true, true);
            nextActivity = true;
        }
    }

    @Override
    public void createViews() {
        super.createViews();
    }
}
