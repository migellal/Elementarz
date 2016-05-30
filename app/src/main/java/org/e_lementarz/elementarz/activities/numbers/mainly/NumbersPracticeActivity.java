package org.e_lementarz.elementarz.activities.numbers.mainly;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.e_lementarz.elementarz.R;
import org.e_lementarz.elementarz.activities.numbers.AddingCheckActivity;
import org.e_lementarz.elementarz.activities.numbers.ChoiceCheckActivity;
import org.e_lementarz.elementarz.activities.numbers.CompareCheckActivity;
import org.e_lementarz.elementarz.common.Const;

public class NumbersPracticeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers_practice);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Not implemented yet", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void onClickNumbers(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.choiceCheckBtn:
                intent = new Intent(NumbersPracticeActivity.this, ChoiceCheckActivity.class);
                break;
            case R.id.compareCheckBtn:
                intent = new Intent(NumbersPracticeActivity.this, CompareCheckActivity.class);
                break;
            case R.id.addingCheckBtn:
                intent = new Intent(NumbersPracticeActivity.this, AddingCheckActivity.class);
                break;
        }
        assert intent != null;
        intent.putExtra(Const.PRACTICE, true);
        startActivity(intent);
    }

}
