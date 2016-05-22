package org.e_lementarz.elementarz.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import org.e_lementarz.elementarz.R;
import org.e_lementarz.elementarz.activities.numbers.mainly.NumbersActivity;
import org.e_lementarz.elementarz.activities.numbers.mainly.NumbersCustomActivity;
import org.e_lementarz.elementarz.activities.numbers.mainly.NumbersPracticeActivity;
import org.e_lementarz.elementarz.activities.numbers.mainly.NumbersTestActivity;
import org.e_lementarz.elementarz.activities.words.WordsActivity;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickMenu(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.numbersBtn:
                intent = new Intent(MenuActivity.this, NumbersActivity.class);
                break;
            case R.id.numbersPracticeBtn:
                intent = new Intent(MenuActivity.this, NumbersPracticeActivity.class);
                break;
            case R.id.numbersTestBtn:
                intent = new Intent(MenuActivity.this, NumbersTestActivity.class);
                break;
            case R.id.numbersCustomBtn:
                intent = new Intent(MenuActivity.this, NumbersCustomActivity.class);
                break;
            case R.id.wordsBtn:
                intent = new Intent(MenuActivity.this, WordsActivity.class);
                break;
        }
        startActivity(intent);
    }

}
