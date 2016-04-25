package org.e_lementarz.elementarz.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.e_lementarz.elementarz.R;
import org.e_lementarz.elementarz.common.ElementarzActivity;

public class NumbersActivity extends ElementarzActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);
        setNaviBarColor();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onClickNumbers(View view) {
        Intent intent = null;
        switch (view.getId())
        {
            case R.id.numbersBricksBtn:
                intent = new Intent(NumbersActivity.this, NumbersBricksActivity.class);
                break;
            case R.id.numbersOrderBtn:
                intent = new Intent(NumbersActivity.this, NumbersOrderActivity.class);
                break;
            case R.id.numbersCompareBtn:
                intent = new Intent(NumbersActivity.this, NumbersCompareActivity.class);
                break;
            case R.id.numbersCompareCheckBtn:
                intent = new Intent(NumbersActivity.this, NumbersCompareCheckActivity.class);
                break;
            case R.id.numbersAddingBricksBtn:
                intent = new Intent(NumbersActivity.this, NumbersBricksAdding.class);
                break;
        }
        startActivity(intent);
    }
}
