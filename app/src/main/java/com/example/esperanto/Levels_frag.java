package com.example.esperanto;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;

/**
 * Created by ahmad on 23-11-2016.
 */

public class Levels_frag extends Fragment {
    private TextView tBeginner, tIntermediate, tExpert;
    private Controller c=new Controller(getActivity());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Web json=new Web("http://quickconnect.dk/esperanto/metadata.json",getActivity());

        System.out.println(json.data);
        View show = inflater.inflate(R.layout.levels, container, false);
        return show;
    }
}
