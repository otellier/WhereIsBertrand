package com.example.iem.whereisbertrand.view.fragment;

import android.support.v4.app.Fragment;
import android.content.Context;

import com.example.iem.whereisbertrand.view.MainActivity;
import com.example.iem.whereisbertrand.manager.*;

/**
 * Created by iem on 30/04/2018.
 */

public class BaseFragment extends Fragment {

    //region variable

    MainActivity activity;

    //endregion

    //region methodes
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (MainActivity)context;
    }

    public void showFragment(Fragment f) {
        this.activity.showFragment(f);
    }
    //endregion
}
