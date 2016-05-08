package org.e_lementarz.elementarz.activities.numbers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.e_lementarz.elementarz.R;
import org.e_lementarz.elementarz.common.ElementarzNumbersActivity;

public class NumbersActivity extends ElementarzNumbersActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onClickNumbers(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.bricksBtn:
                intent = new Intent(NumbersActivity.this, BricksActivity.class);
                break;
            case R.id.numbersOrderBtn:
                intent = new Intent(NumbersActivity.this, BricksOrderActivity.class);
                break;
            case R.id.numbersCompareBtn:
                intent = new Intent(NumbersActivity.this, CompareActivity.class);
                break;
            case R.id.numbersCompareCheckBtn:
                intent = new Intent(NumbersActivity.this, CompareCheckActivity.class);
                break;
            case R.id.numbersAddingBricksBtn:
                intent = new Intent(NumbersActivity.this, AddingActivity.class);
                break;
            case R.id.addingCheckBtn:
                intent = new Intent(NumbersActivity.this, AddingCheckActivity.class);
                break;
        }
        startActivity(intent);
    }

    @Override
    public void createViews() {

    }

    @Override
    public void onClickSuccessView() {

    }

    @Override
    public void onClickFab() {

    }
}
