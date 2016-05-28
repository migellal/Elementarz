package org.e_lementarz.elementarz.activities.numbers.mainly;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.e_lementarz.elementarz.R;
import org.e_lementarz.elementarz.activities.numbers.AddingCheckActivity;
import org.e_lementarz.elementarz.activities.numbers.ChoiceCheckActivity;
import org.e_lementarz.elementarz.activities.numbers.CompareCheckActivity;
import org.e_lementarz.elementarz.common.Const;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NumbersCustomActivity extends AppCompatActivity {

    @Bind(R.id.goodAnswerET)
    EditText goodAnswerAmount;
    @Bind(R.id.badAnswerET)
    EditText badAnswerAmount;
    @Bind(R.id.timeET)
    EditText timeAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers_custom);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.choiceBTN)
    void onClickChoice() {
        if (goodAnswerAmount.getText() != null && goodAnswerAmount.getText().length()!=0
                && badAnswerAmount.getText() != null && badAnswerAmount.getText().length()!=0
                && timeAmount.getText() != null && timeAmount.getText().length()!=0) {
            Intent intent = new Intent(NumbersCustomActivity.this, ChoiceCheckActivity.class);
            intent.putExtra(Const.CUSTOM, true);
            intent.putExtra(Const.CUSTOM_TIME, Integer.parseInt(timeAmount.getText().toString()));
            intent.putExtra(Const.CUSTOM_GOOD_ANSWER, Integer.parseInt(goodAnswerAmount.getText().toString()));
            intent.putExtra(Const.CUSTOM_BAD_ANSWER, Integer.parseInt(badAnswerAmount.getText().toString()));
            startActivity(intent);
        } else
            Toast.makeText(getApplicationContext(), "Some value is empty", Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.compareBTN)
    void onClickCompare() {
        if (goodAnswerAmount.getText() != null && goodAnswerAmount.getText().length()!=0
                && badAnswerAmount.getText() != null && badAnswerAmount.getText().length()!=0
                && timeAmount.getText() != null && timeAmount.getText().length()!=0) {
            Intent intent = new Intent(NumbersCustomActivity.this, CompareCheckActivity.class);
            intent.putExtra(Const.CUSTOM, true);
            intent.putExtra(Const.CUSTOM_TIME, Integer.parseInt(timeAmount.getText().toString()));
            intent.putExtra(Const.CUSTOM_GOOD_ANSWER, Integer.parseInt(goodAnswerAmount.getText().toString()));
            intent.putExtra(Const.CUSTOM_BAD_ANSWER, Integer.parseInt(badAnswerAmount.getText().toString()));
            startActivity(intent);
        } else
            Toast.makeText(getApplicationContext(), "Some value is empty", Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.addingBTN)
    void onClickAdding() {
        if (goodAnswerAmount.getText() != null && goodAnswerAmount.getText().length()!=0
                && badAnswerAmount.getText() != null && badAnswerAmount.getText().length()!=0
                && timeAmount.getText() != null && timeAmount.getText().length()!=0) {
            Intent intent = new Intent(NumbersCustomActivity.this, AddingCheckActivity.class);
            intent.putExtra(Const.CUSTOM, true);
            intent.putExtra(Const.CUSTOM_TIME, Integer.parseInt(timeAmount.getText().toString()));
            intent.putExtra(Const.CUSTOM_GOOD_ANSWER, Integer.parseInt(goodAnswerAmount.getText().toString()));
            intent.putExtra(Const.CUSTOM_BAD_ANSWER, Integer.parseInt(badAnswerAmount.getText().toString()));
            startActivity(intent);
        } else
            Toast.makeText(getApplicationContext(), "Some value is empty", Toast.LENGTH_LONG).show();
    }


}
