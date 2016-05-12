package org.e_lementarz.elementarz.activities.numbers;

import android.os.Bundle;
import android.widget.Toast;

import org.e_lementarz.elementarz.R;
import org.e_lementarz.elementarz.common.ElementarzNumbersActivity;
import org.e_lementarz.elementarz.common.NumbersPadElements;

import butterknife.OnClick;

public class ChoiceCheckActivity extends ElementarzNumbersActivity {

    private NumbersPadElements numbersPadElementsOperation;
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

        numbersPadElementsOperation = new NumbersPadElements(this);
    }

    @OnClick(R.id.button0)
    void onClick0()
    {
        checkSize(0);
    }
    @OnClick(R.id.button1)
    void onClick1()
    {
        checkSize(1);
    }
    @OnClick(R.id.button2)
    void onClick2()
    {
        checkSize(2);
    }
    @OnClick(R.id.button3)
    void onClick3()
    {
        checkSize(3);
    }
    @OnClick(R.id.button4)
    void onClick4()
    {
        checkSize(4);
    }
    @OnClick(R.id.button5)
    void onClick5()
    {
        checkSize(5);
    }
    @OnClick(R.id.button6)
    void onClick6()
    {
        checkSize(6);
    }
    @OnClick(R.id.button7)
    void onClick7()
    {
        checkSize(7);
    }
    @OnClick(R.id.button8)
    void onClick8()
    {
        checkSize(8);
    }
    @OnClick(R.id.button9)
    void onClick9()
    {
        checkSize(9);
    }
    @OnClick(R.id.button10)
    void onClick10()
    {
        checkSize(10);
    }

    public boolean checkSize(int n)
    {
        return number==n;
    }

}
