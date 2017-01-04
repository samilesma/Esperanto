package com.example.esperanto;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import gamemodes.DescribeImage_frag;

public class Controller {
    SharedPreferences level;

    public Controller(FragmentActivity activity) {
        level = activity.getSharedPreferences(
                "level", Context.MODE_PRIVATE);
        level = activity.getSharedPreferences(
                "underLevel", Context.MODE_PRIVATE);
    }

    public void setNiveau(int level, int underLevel){
        this.level.edit().putInt("level", level).apply();
        this.level.edit().putInt("underLevel", underLevel).apply();
    }

    private String web(int type, int difficulty, int num) {
        String url="http://quickconnect.dk/";

        if(type==1) url=url+"images/";
        else url=url+"sounds/";

        if(difficulty==1) url=url+"beginner/";
        else if(difficulty==2) url=url+"intermediate/";
        else url=url+"expert/";

        if(type==1) url=url+num+".jpg";
        else url=url+num+".mp3";

        URL oracle = null;
        try {
            oracle = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        BufferedReader in = null;
        try {
            in = new BufferedReader(
                    new InputStreamReader(oracle.openStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String inputLine;
        String output="";
        try {
            while ((inputLine = in.readLine()) != null)
                    output=output+inputLine+"\n";
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    public static String getUrl(String url) throws IOException {
        URL quickconnect = new URL(url);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        quickconnect.openStream()));

        String full="";
        String inputLine;

        while ((inputLine = in.readLine()) != null) full=full+"\n"+inputLine;

        in.close();

        return full;

    }

    public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream inputstream = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(inputstream, "src name");
            return d;
        } catch (Exception e) {
            return null;
        }
    }

    public static DescribeImage_frag newInstance(int image1, String t1, String t2, String t3, String t4, String tTarget) {
        DescribeImage_frag f = new DescribeImage_frag();
        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt("image", R.drawable.domo);
        args.putString("Text1","Plomo");
        args.putString("Text2","Auto");
        args.putString("Text3","Domo");
        args.putString("Text4","Cevalo");
        args.putString("Text5","Plata");
        args.putString("tTarget","Domo");
        f.setArguments(args);
        return f;
    }

}
