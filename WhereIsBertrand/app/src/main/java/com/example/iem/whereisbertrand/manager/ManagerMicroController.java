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

        int msignalStrength = 100;
        animation.stop();
        animation = new AnimationDrawable();
        animation.setOneShot(false);
        if(signalStrength < -30.0 && signalStrength > -40.0){
            msignalStrength = 10;
        }
        else if(signalStrength < -40.0 && signalStrength > -50.0){
            msignalStrength = 40;
        }
        else if(signalStrength < -50.0 && signalStrength > -60.0){
            msignalStrength = 70;
        }
        else if(signalStrength < -60.0 && signalStrength > -70.0){
            msignalStrength = 90;
        }
        else if(signalStrength < -70.0 && signalStrength > -80.0){
            msignalStrength = 110;
        }

        for(int i = 1; i <= numberOfImages; i++){
            animation.addFrame(context.getResources().getDrawable(context.getResources().getIdentifier(fileName + "_" + i, "raw", context.getPackageName())), msignalStrength);
            Log.d("CHEVRE", fileName + "_" + i);
        }

        imageView.setImageDrawable(animation);
        animation.start();
    }

    public void makeABIP(){
        Log.d("BIP", "Je suis un ane du BIP");
    }

}
