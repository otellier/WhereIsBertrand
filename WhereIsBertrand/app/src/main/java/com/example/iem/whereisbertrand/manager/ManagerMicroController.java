package com.example.iem.whereisbertrand.manager;

/**
 * Created by iem on 30/04/2018.
 */

public class ManagerMicroController {
    private static final ManagerMicroController ourInstance = new ManagerMicroController();

    public static ManagerMicroController getInstance() {
        return ourInstance;
    }

    private ManagerMicroController() {
    }

    public boolean isDeviceInRange(){
        return true;
    }
}
