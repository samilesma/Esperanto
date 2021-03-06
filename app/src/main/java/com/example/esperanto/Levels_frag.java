package com.example.esperanto;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.concurrent.ExecutionException;

import static android.content.Context.MODE_PRIVATE;

public class Levels_frag extends Fragment {
    private Controller c=new Controller(getActivity());
    private int beginner,intermediate,expert;
    Display display;

    LinearLayout ll;
    Button b;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.levels, container, false);

        SharedPreferences.Editor editor = getActivity().getSharedPreferences("saved",MODE_PRIVATE).edit();
        editor.putBoolean("saved",false);
        editor.commit();
        display = getActivity().getWindowManager().getDefaultDisplay();

        String data="";
        try {
            data = new Web().execute("https://raw.githubusercontent.com/samilesma/Esperanto/master/serverfiler/v1/metadata.json").get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        try {
            JSONObject json=new JSONObject(data);
            beginner=json.getInt("beginner");
            intermediate=json.getInt("intermediate");
            expert=json.getInt("expert");
            System.out.println("Beginner: "+beginner);
            System.out.println("Intermediate: "+intermediate);
            System.out.println("Expert: "+expert);
            int width = display.getWidth(); // ((display.getWidth()*20)/100)
            int height = display.getHeight();// ((display.getHeight()*30)/100)
            LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(width/4,height/7);
            int t=1;
            ll = (LinearLayout)view.findViewById(R.id.beginner_linear);
            for(int i=1;i<=Integer.parseInt(json.getString("beginner"));i++) {

                b = new Button(getActivity().getApplicationContext());
                b.setText(""+i);
                b.setLayoutParams(parms);
                b.setTextSize(TypedValue.COMPLEX_UNIT_PX, width/7);
                ll.addView(b);
                setOnClick(b,t++);
            }

            ll = (LinearLayout)view.findViewById(R.id.intermediate_linear);
            for(int i=1;i<=Integer.parseInt(json.getString("intermediate"));i++) {
                b = new Button(getActivity().getApplicationContext());
                b.setText(""+i);
                b.setLayoutParams(parms);
                b.setTextSize(TypedValue.COMPLEX_UNIT_PX, width/7);
                ll.addView(b);
                setOnClick(b,t++);
            }

            ll = (LinearLayout)view.findViewById(R.id.expert_linear);
            for(int i=1;i<=Integer.parseInt(json.getString("expert"));i++) {
                b = new Button(getActivity().getApplicationContext());
                b.setText(""+i);
                b.setLayoutParams(parms);
                b.setTextSize(TypedValue.COMPLEX_UNIT_PX, width/7);
                ll.addView(b);
                setOnClick(b,t++);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
    }

    private void setOnClick(final Button b, final int i){
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int lvl;
                String type;
                if(i>beginner){
                    if(i>beginner+intermediate){//expert
                        lvl=i-intermediate-beginner;
                        type="expert";
                    }
                    else{//intermediate
                        lvl=i-beginner;
                        type="intermediate";
                    }
                }
                else{//Beginner
                    lvl=i;
                    type="beginner";
                }

                try {
                    c.selectLevel(type,lvl);
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    getFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
                            .replace(R.id.fragmentindhold, (Fragment) c.getNextLevel()).addToBackStack(null).commit();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
