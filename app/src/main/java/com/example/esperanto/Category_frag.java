package com.example.esperanto;

import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import gamemodes.DragAnddrop_frag;
import gamemodes.Fourpic_frag;

/**
 * Created by IbsenB on 10-01-2017.
 */

public class Category_frag extends Fragment implements View.OnClickListener{
    private ImageButton iBeginner,iIntermediate, iExpert;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.levels, container, false);

        iBeginner = (ImageButton) view.findViewById(R.id.iBeginner);
        iIntermediate = (ImageButton) view.findViewById(R.id.iIntermediate);
        iExpert = (ImageButton) view.findViewById(R.id.iExpert);

        iBeginner.setOnClickListener(this);
        iIntermediate.setOnClickListener(this);
        iExpert.setOnClickListener(this);

        getFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
                .replace(R.id.fragmentindhold, new Category_frag()).addToBackStack(null).commit();



        return view;
    }

    @Override
    public void onClick(View v) {
        if(v==iBeginner){
            getFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
                    .replace(R.id.fragmentindhold, new Fourpic_frag()).addToBackStack(null).commit();

        }
        if(v==iIntermediate){
            getFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
                    .replace(R.id.fragmentindhold, new Fourpic_frag()).addToBackStack(null).commit();
        }
        if(v==iExpert){
            getFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
                    .replace(R.id.fragmentindhold, new Fourpic_frag()).addToBackStack(null).commit();

        }

    }

}
