package com.example.iem.whereisbertrand.view.fragment;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;
import com.example.iem.whereisbertrand.R;
import com.example.iem.whereisbertrand.manager.ManagerMicroController;

public class HomeFragment extends BaseFragment {

    private View v;
    private Context context;
    private ImageView imageView;
    private Button buttonSearch;
    private AlertDialog dialog;


    public static HomeFragment newInstance() {

        Bundle args = new Bundle();

        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v = inflater.inflate(R.layout.fragment_home, container, false);
        context = getActivity().getApplicationContext();
        imageView = v.findViewById(R.id.logoBertrand);
        buttonSearch = (Button) v.findViewById(R.id.buttonSearch);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                Log.d("CHEVRE", "La chèvre malicieuse est de retour, comme dans tout code android qui se respecte");
                                                if(ManagerMicroController.getInstance().isDeviceInRange()){
                                                    showFragment(BluetoothResearchFragment.newInstance());
                                                }else{
                                                    createDialog();
                                                }

                                            }
                                        });
        GlideDrawableImageViewTarget imageViewTarget = new GlideDrawableImageViewTarget(imageView);
        Glide.with(this).load(R.raw.findbertrand).into(imageViewTarget);

        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().finish();
    }

    private void createDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                if(activity!= null) {
                    showFragment(WifiResearchFragment.newInstance());
                }
            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.setMessage("Betrand n'est pas près d'ici, voulez vous faire une recherche WIFI ?")
                .setTitle("Betrand is missing");
        dialog = builder.create();
        dialog.show();
    }
}
