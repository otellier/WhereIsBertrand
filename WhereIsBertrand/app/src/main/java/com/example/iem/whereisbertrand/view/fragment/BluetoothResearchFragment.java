package com.example.iem.whereisbertrand.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.iem.whereisbertrand.R;
import com.example.iem.whereisbertrand.manager.ManagerMicroController;

public class BluetoothResearchFragment extends BaseFragment {
    private View v;
    private Context context;
    private ImageView imageView;
    private Button buttonBIP;

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

        imageView = v.findViewById(R.id.imageWave);
        buttonBIP = v.findViewById(R.id.buttonBIP);
        ManagerMicroController.getInstance().displayAnimation(imageView, "frame", 17, context);
        buttonBIP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("CHEVRE", "La chèvre malicieuse est de retour, comme dans tout code android qui se respecte");
                ManagerMicroController.getInstance().makeABIP();
            }
        });
        
        return v;
    }
}