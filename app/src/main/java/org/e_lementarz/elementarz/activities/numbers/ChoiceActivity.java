package org.e_lementarz.elementarz.activities.numbers;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.e_lementarz.elementarz.R;
import org.e_lementarz.elementarz.common.ElementarzNumbersActivity;

public class ChoiceActivity extends ElementarzNumbersActivity {

    int number = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        createViews();
    }

    @Override
    public void onClickSuccessView() {

    }

    @Override
    public void onClickFab() {

    }

    @Override
    public void createViews() {
        super.createViews();
    }

    public boolean checkSize(int n)
    {
        return number==n;
    }

}
