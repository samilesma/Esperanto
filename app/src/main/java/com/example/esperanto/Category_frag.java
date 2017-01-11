package com.example.esperanto;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import org.json.JSONException;

import java.util.concurrent.ExecutionException;

import gamemodes.Fourpic_frag;



public class Category_frag extends Fragment implements View.OnClickListener{
    private ImageButton iBeginner,iIntermediate, iExpert;
    Controller c=new Controller(getActivity());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.category_frag, container, false);

        iBeginner = (ImageButton) view.findViewById(R.id.iBeginner);
        iIntermediate = (ImageButton) view.findViewById(R.id.iIntermediate);
        iExpert = (ImageButton) view.findViewById(R.id.iExpert);

        iBeginner.setOnClickListener(this);
        iIntermediate.setOnClickListener(this);
        iExpert.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        String type="";
        if(v==iBeginner) type="beginner";
        if(v==iIntermediate) type="intermediate";
        if(v==iExpert) type="expert";

        try {
            c.selectLevel(type,1);
            getFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
                    .replace(R.id.fragmentindhold, (Fragment) c.getNextLevel(), "last").addToBackStack(null).commit();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
