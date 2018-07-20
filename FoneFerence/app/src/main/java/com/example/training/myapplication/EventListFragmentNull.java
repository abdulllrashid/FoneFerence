package com.example.training.myapplication;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class EventListFragmentNull extends Fragment {
    FloatingActionButton fab;
    public static final String PREFS_NAME = "usertype";

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.event_list_null_fragement, null);

        fab = (FloatingActionButton) view.findViewById(R.id.floatingActionADD);
        SharedPreferences preferences = getActivity().getSharedPreferences(PREFS_NAME,0);
        String value = preferences.getString("usertype", "");
        if (value.equals("Audiance")) {
            fab.setVisibility(View.GONE);
        }
        else if(value.equals("Speaker")) {
            fab.setVisibility(View.GONE);
        }
        else if (value.equals("Event Host")){
            fab.setVisibility(View.VISIBLE);
        }
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }
}
