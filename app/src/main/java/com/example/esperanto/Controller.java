package com.example.esperanto;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

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

}
