package org.e_lementarz.elementarz.activities.numbers.mainly;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.e_lementarz.elementarz.R;
import org.e_lementarz.elementarz.activities.numbers.AddingActivity;
import org.e_lementarz.elementarz.activities.numbers.AddingCheckActivity;
import org.e_lementarz.elementarz.activities.numbers.BricksActivity;
import org.e_lementarz.elementarz.activities.numbers.BricksOrderActivity;
import org.e_lementarz.elementarz.activities.numbers.ChoiceActivity;
import org.e_lementarz.elementarz.activities.numbers.ChoiceCheckActivity;
import org.e_lementarz.elementarz.activities.numbers.CompareActivity;
import org.e_lementarz.elementarz.activities.numbers.CompareCheckActivity;
import org.e_lementarz.elementarz.activities.numbers.SubtractionActivity;
import org.e_lementarz.elementarz.activities.numbers.SubtractionCheckActivity;
import org.e_lementarz.elementarz.common.extend.ElementarzNumbersActivity;

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
            case R.id.orderBtn:
                intent = new Intent(NumbersActivity.this, BricksOrderActivity.class);
                break;
            case R.id.choiceBtn:
                intent = new Intent(NumbersActivity.this, ChoiceActivity.class);
                break;
            case R.id.choiceCheckBtn:
                intent = new Intent(NumbersActivity.this, ChoiceCheckActivity.class);
                break;
            case R.id.compareBtn:
                intent = new Intent(NumbersActivity.this, CompareActivity.class);
                break;
            case R.id.compareCheckBtn:
                intent = new Intent(NumbersActivity.this, CompareCheckActivity.class);
                break;
            case R.id.addingBricksBtn:
                intent = new Intent(NumbersActivity.this, AddingActivity.class);
                break;
            case R.id.addingCheckBtn:
                intent = new Intent(NumbersActivity.this, AddingCheckActivity.class);
                break;
            case R.id.subtractionBtn:
                intent = new Intent(NumbersActivity.this, SubtractionActivity.class);
                break;
            case R.id.subtractionCheckBtn:
                intent = new Intent(NumbersActivity.this, SubtractionCheckActivity.class);
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
