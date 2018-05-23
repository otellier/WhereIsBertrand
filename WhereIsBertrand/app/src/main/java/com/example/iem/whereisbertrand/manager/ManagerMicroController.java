package com.example.iem.whereisbertrand.manager;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by iem on 30/04/2018.
 */

public class ManagerMicroController {
    private static final ManagerMicroController ourInstance = new ManagerMicroController();
    AnimationDrawable animation;

    public static ManagerMicroController getInstance() {
        return ourInstance;
    }

    private ManagerMicroController() {
        animation = new AnimationDrawable();
    }

    public boolean isDeviceInRange(){
        return true;
    }

    public void displayAnimation(final ImageView imageView, String fileName, int numberOfImages, Context context, double signalStrength){

        double msignalStrength = 20 + Math.abs(signalStrength) ;
        animation.stop();
        animation = new AnimationDrawable();

        animation.setOneShot(false);

        for(int i = 1; i <= numberOfImages; i++){
            animation.addFrame(context.getResources().getDrawable(context.getResources().getIdentifier(fileName + "_" + i, "raw", context.getPackageName())), (int)(msignalStrength));
            Log.d("CHEVRE", fileName + "_" + i);
        }

        imageView.setImageDrawable(animation);
        animation.start();
    }

    public void makeABIP(){
        Log.d("BIP", "Je suis un ane du BIP");
    }

}
