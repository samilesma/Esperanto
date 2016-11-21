package com.example.esperanto;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;

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
}
