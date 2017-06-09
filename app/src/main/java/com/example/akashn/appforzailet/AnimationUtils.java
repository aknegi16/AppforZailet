package com.example.akashn.appforzailet;

import android.support.v7.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

/**
 * Created by akashn on 08-06-2017.
 */

public class AnimationUtils {

    public static void animate(RecyclerView.ViewHolder holder, boolean goesDown) {

     YoYo.with(Techniques.FlipInX)
             .duration(1000)
             .playOn(holder.itemView);


 }

    public static void animate2(RecyclerView.ViewHolder holder, boolean goesDown) {
 if(goesDown)
        YoYo.with(Techniques.FadeInUp)
                .duration(2000)
                .playOn(holder.itemView);

        else
     YoYo.with(Techniques.FadeInDown)
             .duration(2000)
             .playOn(holder.itemView);


    }

}
