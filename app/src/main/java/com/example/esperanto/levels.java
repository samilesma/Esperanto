package com.example.esperanto;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by ahmad on 23-11-2016.
 */

public class levels extends Fragment {
    private TextView tBeginner, tIntermediate, tExpert;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View show = inflater.inflate(R.layout.levels, container, false);
        return show;
    }
}
