package com.example.esperanto;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Wordlist_frag extends Fragment {
    Button bWord;
    LinearLayout lLay;

    // Array of strings for ListView Title
    String[] listviewTitle = new String[]{
            "ListView Title 1", "ListView Title 2", "ListView Title 3", "ListView Title 4",
    };

    int[] listviewImage = new int[]{
            R.drawable.back, R.drawable.domo, R.drawable.elefanto, R.drawable.glass,
    };

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.wordlist_frag, container, false);

        lLay = (LinearLayout) view.findViewById(R.id.lLay);
        setButtons();

        List<HashMap<String, String>> aList = new ArrayList<HashMap<String, String>>();

        for (int i = 0; i < listviewImage.length; i++) {
            HashMap<String, String> hm = new HashMap<String, String>();
            hm.put("listview_title", listviewTitle[i]);
            hm.put("listview_image", Integer.toString(listviewImage[i]));
            aList.add(hm);
        }

        String[] from = {"listview_image", "listview_title", "listview_discription"};
        int[] to = {R.id.listview_image, R.id.listview_item_title, R.id.listview_item_short_description};

        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), aList, R.layout.listview_activity, from, to);
        ListView androidListView = (ListView) view.findViewById(R.id.list);
        androidListView.setAdapter(simpleAdapter);

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
            bWord.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,220));
            bWord.setTextSize(TypedValue.COMPLEX_UNIT_PX, 130);
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
            if(i==91) c=false;
        }
    }
}