package com.example.iem.whereisbertrand.modele;

import android.app.ListActivity;
import android.bluetooth.BluetoothAdapter;
import android.os.Handler;

/**
 * Created by iem on 23/05/2018.
 */

public class DeviceScanActivity extends ListActivity {

    private BluetoothAdapter mBluetoothAdapter;
    private boolean mScanning;
    private Handler mHandler;
    private BluetoothAdapter.LeScanCallback mLeScanCallback;

    // Stops scanning after 10 seconds.
    private static final long SCAN_PERIOD = 10000;
    private void scanLeDevice(final boolean enable) {
        if (enable) {
            // Stops scanning after a pre-defined scan period.
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mScanning = false;
                    mBluetoothAdapter.stopLeScan(mLeScanCallback);
                }
            }, SCAN_PERIOD);

            mScanning = true;
            mBluetoothAdapter.startLeScan(mLeScanCallback);
        } else {
            mScanning = false;
            mBluetoothAdapter.stopLeScan(mLeScanCallback);
        }

    }

}