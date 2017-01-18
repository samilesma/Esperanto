package com.example.esperanto;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import org.json.JSONException;
import java.util.concurrent.ExecutionException;
import gamemodes.DescribeImage_frag;
import gamemodes.Picture_choose_frag;
import static android.content.Context.MODE_PRIVATE;

public class Hovedmenu_frag extends Fragment implements View.OnClickListener {
    private Button bOK, bTest;
    private Controller c=new Controller(getActivity());

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View show = inflater.inflate(R.layout.hovedmenu_frag, container, false);

        bOK = (Button) show.findViewById(R.id.bOK);
        bTest = (Button) show.findViewById(R.id.bTest);

        bOK.setOnClickListener(this);
        bTest.setOnClickListener(this);

        SharedPreferences load = getActivity().getSharedPreferences("First",MODE_PRIVATE);
        boolean first = load.getBoolean("First",false);

        if(first){
            SharedPreferences saved = getActivity().getSharedPreferences("saved",MODE_PRIVATE);
            boolean save = saved.getBoolean("saved",false);
            if(save)
            {
                String type=saved.getString("type","beginner");
                int lvl=saved.getInt("lvl",1);
                int len=saved.getInt("len",1);
                try {
                    getFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
                            .replace(R.id.fragmentindhold, (Fragment) c.load(type,lvl,len-1)).addToBackStack(null).commit();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            else {
                getFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right).replace(R.id.fragmentindhold, new Levels_frag()).addToBackStack(null).commit();
            }
        }
        else{
            SharedPreferences.Editor editor = getActivity().getSharedPreferences("First",MODE_PRIVATE).edit();
            editor.putBoolean("First",true);
            editor.commit();
            getFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right).replace(R.id.fragmentindhold, new Category_frag(), "last").addToBackStack(null).commit();
        }

        return show;
    }

    @Override
    public void onClick(View v) {
        SharedPreferences.Editor editor = getActivity().getSharedPreferences("saved",MODE_PRIVATE).edit();
        editor.putBoolean("saved",false);
        editor.commit();

        if(v==bOK){
            getFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
                    .replace(R.id.fragmentindhold, new DescribeImage_frag()).addToBackStack(null).commit();
        }
        if(v==bTest){
            getFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
                    .replace(R.id.fragmentindhold, new Picture_choose_frag()).addToBackStack(null).commit();
        }
    }
}
