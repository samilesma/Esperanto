package com.example.esperanto;

import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.concurrent.ExecutionException;
import gamemodes.DescribeImage_frag;
import gamemodes.DragAnddrop_frag;
import gamemodes.Fourpic_frag;
import gamemodes.Picture_choose_frag;

public class Controller {
    public SharedPreferences level;
    public static Object GM[]=new Object[]{new DescribeImage_frag(),new DragAnddrop_frag(),new Fourpic_frag(),new Picture_choose_frag()};
    public static String levelType=null;
    public static int currentLevel=0;
    public static int levelLength=0;
    public static JSONObject json=null;

    public Controller(FragmentActivity activity) {
        /*
        level = activity.getSharedPreferences(
                "level", Context.MODE_PRIVATE);
        level = activity.getSharedPreferences(
                "underLevel", Context.MODE_PRIVATE);
                */
    }

    public void setNiveau(int level, int underLevel){
        this.level.edit().putInt("level", level).apply();
        this.level.edit().putInt("underLevel", underLevel).apply();
    }

    public void selectLevel(String type, int lvl) throws JSONException, ExecutionException, InterruptedException {
        String data=new Web().execute("http://quickconnect.dk/esperanto/levels/"+type+"/"+lvl+"/index.json").get();
        System.out.println(data);
        JSONObject json=new JSONObject(data);

        currentLevel=lvl;
        levelType=type;
        this.json=json;
    }

    public Object getNextLevel() throws JSONException {
        JSONArray gm=json.getJSONArray("gm");
        JSONObject lvl=gm.getJSONObject(levelLength);
        levelLength++;

        return GM[lvl.getInt("type")-1];
    }


}
