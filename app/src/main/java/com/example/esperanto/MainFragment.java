package com.example.esperanto;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.esperanto.myapplication.Navigation_drawer;
import com.example.esperanto.R;

import easy.controller;
import easy.learning.Abc123;
import easy.testing.dragAnddrop;
import easy.testing.picture_choose;

public class MainFragment extends Fragment implements View.OnClickListener {

    private Button bOK, bTest, bNav;
    Controller c;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View show = inflater.inflate(R.layout.activity_main, container, false);

        c = new Controller(getActivity());

        bOK = (Button) show.findViewById(R.id.bOK);
        bTest = (Button) show.findViewById(R.id.bTest);
        bNav = (Button) show.findViewById(R.id.bNav);

        bOK.setOnClickListener(this);
        bTest.setOnClickListener(this);
        bNav.setOnClickListener(this);

        return show;
    }

    @Override
    public void onClick(View v) {
        if(v==bOK){
            getFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
                    .replace(R.id.fragmentindhold, new Abc123()).addToBackStack(null).commit();
        }
        if(v==bTest){
            getFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
                    .replace(R.id.fragmentindhold, new picture_choose()).addToBackStack(null).commit();
        }
        if(v==bNav){
            Intent intent = new Intent(getContext(), Navigation_drawer.class);
            startActivity(intent);


        }

    }
}