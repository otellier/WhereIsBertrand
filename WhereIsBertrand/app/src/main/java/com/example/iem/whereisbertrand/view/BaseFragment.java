package com.example.iem.whereisbertrand.view;

import android.support.v4.app.Fragment;
import android.content.Context;

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
