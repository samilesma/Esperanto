package com.example.esperanto;

import android.support.v4.app.FragmentActivity;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import gamemodes.DescribeImage_frag;
import gamemodes.DragAnddrop_frag;
import gamemodes.Finish_sentence_frag;
import gamemodes.Fourpic_frag;
import gamemodes.Number_word_frag;
import gamemodes.Picture_choose_frag;

public class Controller {

    public static Object GM[]=new Object[]{new DescribeImage_frag(),new DragAnddrop_frag(),new Fourpic_frag(),new Picture_choose_frag(), new Finish_sentence_frag(), new Number_word_frag()};
    public static String levelType=null;
    public static int currentLevel=0;
    public static int levelLength=0;
    public static boolean notification = true;
    public static JSONObject json=null;
    public static FragmentActivity activity;

    public Controller(FragmentActivity activity) {
        this.activity=activity;
    }

    public void selectLevel(String type, int lvl) throws JSONException, ExecutionException, InterruptedException {
        String data=new Web().execute("https://raw.githubusercontent.com/samilesma/Esperanto/master/serverfiler/v1/levels/"+type+"/"+lvl+"/index.json").get();
        System.out.println(data);
        JSONObject json=new JSONObject(data);

        currentLevel=lvl;
        levelType=type;
        levelLength=0;
        this.json=json;
    }

    public Object getNextLevel() throws JSONException {
        JSONArray gm = json.getJSONArray("gm");
        JSONObject lvl;
        if(gm.length()>levelLength) lvl = gm.getJSONObject(levelLength++);
        else return new Levels_frag();

        return GM[lvl.getInt("type") - 1];
    }

    public int[] RandomizeArray(int[] array){
        Random rgen = new Random();

        for (int i=0; i<array.length; i++) {
            int randomPosition = rgen.nextInt(array.length);
            int temp = array[i];
            array[i] = array[randomPosition];
            array[randomPosition] = temp;
        }

        return array;
    }

    public Object load(String type, int lvl, int len) throws ExecutionException, InterruptedException, JSONException {
        String data=new Web().execute("https://raw.githubusercontent.com/samilesma/Esperanto/master/serverfiler/v1/levels/"+type+"/"+lvl+"/index.json").get();
        System.out.println(data);
        JSONObject json=new JSONObject(data);

        currentLevel=lvl;
        levelType=type;
        levelLength=len;
        this.json=json;
        return getNextLevel();
    }
}
