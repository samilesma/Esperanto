package com.example.esperanto;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import gamemodes.Fourpic_frag;
import gamemodes.Picture_choose_frag;

public class Hovedmenu_frag extends Fragment implements View.OnClickListener {

    private Button bOK, bTest;
    Controller c;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View show = inflater.inflate(R.layout.hovedmenu_frag, container, false);

        c = new Controller(getActivity());

        bOK = (Button) show.findViewById(R.id.bOK);
        bTest = (Button) show.findViewById(R.id.bTest);

        bOK.setOnClickListener(this);
        bTest.setOnClickListener(this);

        getFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
                .replace(R.id.fragmentindhold, new Levels_frag()).addToBackStack(null).commit();


        return show;
    }

    @Override
    public void onClick(View v) {
        if(v==bOK){
            getFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
                    .replace(R.id.fragmentindhold, new Fourpic_frag()).addToBackStack(null).commit();
        }
        if(v==bTest){
            getFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
                    .replace(R.id.fragmentindhold, new Picture_choose_frag()).addToBackStack(null).commit();
        }
    }
}
