package org.e_lementarz.elementarz.activities.numbers.mainly;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.e_lementarz.elementarz.R;
import org.e_lementarz.elementarz.activities.numbers.AddingCheckActivity;
import org.e_lementarz.elementarz.activities.numbers.ChoiceCheckActivity;
import org.e_lementarz.elementarz.activities.numbers.CompareCheckActivity;
import org.e_lementarz.elementarz.activities.numbers.SubtractionCheckActivity;
import org.e_lementarz.elementarz.common.Const;

public class NumbersTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers_test);
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
        TextView infoTV = (TextView) findViewById(R.id.infoTV);
        String info = "III class: good answer - " + Const.III_CLASS_GOOD_ANSWER + ", bad answer - " + Const.III_CLASS_BAD_ANSWER + ", time: " + Const.III_CLASS_TIME + "\n";
        info = info + "II class: good answer - " + Const.II_CLASS_GOOD_ANSWER + ", bad answer - " + Const.II_CLASS_BAD_ANSWER + ", time: " + Const.II_CLASS_TIME + "\n";
        info = info + "I class: good answer - " + Const.I_CLASS_GOOD_ANSWER + ", bad answer - " + Const.I_CLASS_BAD_ANSWER + ", time: " + Const.I_CLASS_TIME;
        infoTV.setText(info);
    }

    public void onCLickClassBtn(View view) {
            Intent intent = null;
            switch (view.getId()) {
                case R.id.choiceClassIIIBtn:
                    intent = new Intent(NumbersTestActivity.this, ChoiceCheckActivity.class);
                    intent.putExtra(Const.CLASS, 3);
                    break;
                case R.id.choiceClassIIBtn:
                    intent = new Intent(NumbersTestActivity.this, ChoiceCheckActivity.class);
                    intent.putExtra(Const.CLASS, 2);
                    break;
                case R.id.choiceClassIBtn:
                    intent = new Intent(NumbersTestActivity.this, ChoiceCheckActivity.class);
                    intent.putExtra(Const.CLASS, 1);
                    break;
                case R.id.compareClassIIIBtn:
                    intent = new Intent(NumbersTestActivity.this, CompareCheckActivity.class);
                    intent.putExtra(Const.CLASS, 3);
                    break;
                case R.id.compareClassIIBtn:
                    intent = new Intent(NumbersTestActivity.this, CompareCheckActivity.class);
                    intent.putExtra(Const.CLASS, 2);
                    break;
                case R.id.compareClassIBtn:
                    intent = new Intent(NumbersTestActivity.this, CompareCheckActivity.class);
                    intent.putExtra(Const.CLASS, 1);
                    break;
                case R.id.addingClassIIIBtn:
                    intent = new Intent(NumbersTestActivity.this, AddingCheckActivity.class);
                    intent.putExtra(Const.CLASS, 3);
                    break;
                case R.id.addingClassIIBtn:
                    intent = new Intent(NumbersTestActivity.this, AddingCheckActivity.class);
                    intent.putExtra(Const.CLASS, 2);
                    break;
                case R.id.addingClassIBtn:
                    intent = new Intent(NumbersTestActivity.this, AddingCheckActivity.class);
                    intent.putExtra(Const.CLASS, 1);
                    break;
                case R.id.subtractionClassIIIBtn:
                    intent = new Intent(NumbersTestActivity.this, SubtractionCheckActivity.class);
                    intent.putExtra(Const.CLASS, 3);
                    break;
                case R.id.subtractionClassIIBtn:
                    intent = new Intent(NumbersTestActivity.this, SubtractionCheckActivity.class);
                    intent.putExtra(Const.CLASS, 2);
                    break;
                case R.id.subtractionClassIBtn:
                    intent = new Intent(NumbersTestActivity.this, SubtractionCheckActivity.class);
                    intent.putExtra(Const.CLASS, 1);
                    break;
            }
            assert intent != null;
            intent.putExtra(Const.TEST, true);
            startActivity(intent);
    }
}
