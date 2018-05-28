package com.example.iem.whereisbertrand.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.iem.whereisbertrand.R;


public class WifiResearchFragment extends BaseFragment {
    private View v;
    private Context context;

    public static WifiResearchFragment newInstance() {
        
        Bundle args = new Bundle();
        
        WifiResearchFragment fragment = new WifiResearchFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v = inflater.inflate(R.layout.fragment_home, container, false);
        context = getActivity().getApplicationContext();


        return v;
    }
}
