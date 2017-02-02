package com.example.esperanto;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static android.R.attr.data;


public class Wordlist_frag extends Fragment {
    public final int IMG_DIM=300;
    Button bWord;
    LinearLayout lLay;
    ListView li;
    LinearLayout images;
    View view;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.wordlist_frag, container, false);

        lLay = (LinearLayout) view.findViewById(R.id.lLay);
        li=(ListView) view.findViewById(R.id.list);
        images=(LinearLayout) view.findViewById(R.id.images);
        setButtons();
        this.view = view;
        return view;
    }

    private void setButtons() {
        boolean c=true,t=true;
        int i=65;
        char b=' ';
        while(c) {
            if(i==81 || i==87){
                if(i==81) i++;
                else i+=3;
                continue;
            }
            bWord = new Button(getActivity().getApplicationContext());
            Display display = getActivity().getWindowManager().getDefaultDisplay();
            int width = display.getWidth(); // ((display.getWidth()*20)/100)
            int height = display.getHeight();// ((display.getHeight()*30)/100)

            LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(width/5,height/7);
            bWord.setLayoutParams(parms);
            bWord.setTextSize(TypedValue.COMPLEX_UNIT_PX, width/9);
            if((i!=68 && i!=72 && i!=73 && i!=75 && i!=84 && i!=86) || !t)
            {
                b=(char)i;
                t=true;
                i++;
            }
            else {
                if(i==68) b='Ĉ';
                else if(i==72) b='Ĝ';
                else if(i==73) b='Ĥ';
                else if(i==75) b='Ĵ';
                else if(i==84) b='Ŝ';
                else if(i==86) b='Ŭ';
                t=false;
            }
            bWord.setText(""+b);
            lLay.addView(bWord);
            setOnClick(bWord);
            if(b=='A') bWord.performClick();
            if(i==91) c=false;
        }
    }

    private void setOnClick(final Button b) {
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data="";
                try {
                    System.out.println("https://raw.githubusercontent.com/samilesma/Esperanto/master/serverfiler/v1/list/"+b.getText().toString()+"/index.json");
                    data = new Web().execute("https://raw.githubusercontent.com/samilesma/Esperanto/master/serverfiler/v1/list/"+b.getText().toString()+"/index.json").get();
                    System.out.println(data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                JSONArray arr=null;
                try {
                    JSONObject json=new JSONObject(data);
                    arr=json.getJSONArray("");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                images.removeAllViews();
                ArrayList<String> ar=new ArrayList<String>();
                for(int i=1; i<=arr.length(); i++) {
                    try {
                        ImageView img = new ImageView(getActivity());
                        new Image(img).execute("https://raw.githubusercontent.com/samilesma/Esperanto/master/serverfiler/v1/list/"+b.getText().toString()+"/"+arr.get(i-1).toString().toLowerCase()+".png").get();
                        images.addView(img);
                        Display display = getActivity().getWindowManager().getDefaultDisplay();
                        int width = display.getWidth(); // ((display.getWidth()*20)/100)
                        int height = display.getHeight();// ((display.getHeight()*30)/100)
                        LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(width/4,height/6);
                        img.setLayoutParams(parms);
    //                    img.getLayoutParams().height = IMG_DIM;
    //                    img.getLayoutParams().width = IMG_DIM;
                        ar.add(arr.get(i-1).toString());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(ar);

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.wordlist_text,ar);
                li.setAdapter(arrayAdapter);
            }
        });
    }
}