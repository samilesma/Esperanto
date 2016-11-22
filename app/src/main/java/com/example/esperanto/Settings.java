package com.example.esperanto;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Switch;

public class Settings extends Fragment {
    private Switch sSound, sNotification;
    private Button b1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_settings, container, false);

        sSound = (Switch) view.findViewById(R.id.sSound);
        sNotification = (Switch) view.findViewById(R.id.sNotification);

        return view;
    }
}