package com.example.ruben.rubengerritse_pset5;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by ruben on 9-10-16.
 * This class describes the AddListFragment Fragment. This Fragment allows the user to add a new
 * TodoList to the TodoManager.
 */

public class AddListFragment extends Fragment{

//    Set the layout of this Fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_add_list, container, false);
    }
}
