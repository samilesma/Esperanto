package com.esperanto.myapplication;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import easy.learning.Abc123;

public class MainActivity extends Fragment implements View.OnClickListener {

    private Button bOK;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View show = inflater.inflate(R.layout.activity_main, container, false);

        bOK = (Button) show.findViewById(R.id.bOK);

        bOK.setOnClickListener(this);

        return show;
    }

    @Override
    public void onClick(View v) {
        if(v==bOK){
            getFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
                    .replace(R.id.fragmentindhold, new Abc123()).addToBackStack(null).commit();
        }

    }
}
