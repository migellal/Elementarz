package org.e_lementarz.elementarz.common;

import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import org.e_lementarz.elementarz.R;

/**
 * Created by micha on 19.03.2016.
 */
public abstract class ElementarzActivity extends AppCompatActivity {

    public void setNaviBarColor()
    {
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP)
            getWindow().setNavigationBarColor(this.getResources().getColor(R.color.colorPrimaryDark));
    }
}
