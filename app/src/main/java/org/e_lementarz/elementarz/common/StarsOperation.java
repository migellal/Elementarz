package org.e_lementarz.elementarz.common;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import org.e_lementarz.elementarz.R;


/**
 * Created by micha on 15.05.2016.
 */
public class StarsOperation {

    private ImageView[] stars;
    private int starsCounter = 0;

    public StarsOperation(Activity rootContainer) {
        ImageView star1 = (ImageView) rootContainer.findViewById(R.id.starIV1);
        ImageView star2 = (ImageView) rootContainer.findViewById(R.id.starIV2);
        ImageView star3 = (ImageView) rootContainer.findViewById(R.id.starIV3);
        ImageView star4 = (ImageView) rootContainer.findViewById(R.id.starIV4);
        ImageView star5 = (ImageView) rootContainer.findViewById(R.id.starIV5);
        stars = new ImageView[]{star1, star2, star3, star4, star5};
    }

    public void nextStar(boolean good) {
        if (!good)
            stars[starsCounter].setImageResource(R.drawable.ic_star_border_black_48dp);
        stars[starsCounter].setVisibility(View.VISIBLE);
        starsCounter++;
    }

}
