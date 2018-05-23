package com.example.iem.whereisbertrand.view.fragment;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.iem.whereisbertrand.R;
import com.example.iem.whereisbertrand.manager.ManagerMicroController;

public class BluetoothResearchFragment extends BaseFragment {
    private View v;
    private Context context;
    private ImageView imageView;
    private Button buttonBIP;
    private TextView textViewRange;
    BluetoothManager btManager;
    BluetoothAdapter btAdapter;
    BluetoothLeScanner btScanner;
    private BluetoothDevice device;
    private final static int REQUEST_ENABLE_BT = 1;
    private static final int PERMISSION_REQUEST_COARSE_LOCATION = 1;
    private Boolean stopScan;

    public static BluetoothResearchFragment newInstance() {
        
        Bundle args = new Bundle();
        
        BluetoothResearchFragment fragment = new BluetoothResearchFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v = inflater.inflate(R.layout.fragment_bluetooth_research, container, false);
        context = getActivity().getApplicationContext();
        stopScan = false;


        btManager = (BluetoothManager)context.getSystemService(Context.BLUETOOTH_SERVICE);
        btAdapter = btManager.getAdapter();
        btScanner = btAdapter.getBluetoothLeScanner();
        if (btAdapter != null && !btAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent, 2 );
        }
        if (context.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
            builder.setTitle("This app needs location access");
            builder.setMessage("Please grant location access so this app can detect peripherals.");
            builder.setPositiveButton(android.R.string.ok, null);
            builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, PERMISSION_REQUEST_COARSE_LOCATION);
                }
            });
            builder.show();
        }

        startScanning();

        imageView = v.findViewById(R.id.imageWave);
        buttonBIP = v.findViewById(R.id.buttonBIP);
        textViewRange = v.findViewById(R.id.textViewRange);
        buttonBIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("CHEVRE", "La chèvre malicieuse est de retour, comme dans tout code android qui se respecte");
                ManagerMicroController.getInstance().makeABIP();
            }
        });
        
        return v;
    }


    private ScanCallback leScanCallback = new ScanCallback() {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
           Log.d("IF", stopScan + result.getDevice().getAddress());
            if (!stopScan && result.getDevice().getAddress().equals("E0:76:D0:91:67:AB")) {
                    stopScan = true;
                    int rssi = result.getRssi();
                    device = result.getDevice();
                    stopScanning();
                    textViewRange.setText(Integer.toString(rssi));

                    ManagerMicroController.getInstance().displayAnimation(imageView, "frame", 17, context, rssi);


                new CountDownTimer(7000, 800) {

                    public void onTick(long millisUntilFinished) {

                    }

                    public void onFinish() {
                        stopScan = false;
                        Log.d("TEST", "ANE DU REFRESH");
                        startScanning();
                    }
                }.start();
            }

        }
    };

    public void startScanning() {
        System.out.println("start scanning");
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                btScanner.startScan(leScanCallback);
            }
        });
    }

    public void stopScanning() {
        System.out.println("stopping scanning");
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                btScanner.stopScan(leScanCallback);
            }
        });
    }

    protected double calculateDistance(float txPower, double rssi) {

        if (rssi == 0) {
            return -1.0; // if we cannot determine distance, return -1.
        }

        double ratio = rssi * 1.0 / txPower;

        if (ratio < 1.0) {
            return Math.pow(ratio, 10);
        } else {
            double accuracy = (0.89976) * Math.pow(ratio, 7.7095) + 0.111;
            return accuracy;
        }
    }
}