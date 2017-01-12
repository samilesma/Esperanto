package com.example.esperanto;

import android.view.View;
import android.widget.Button;

/**
 * Created by IbsenB on 12-01-2017.
 */

public class ButtonThread {


    public ButtonThread(final Button bReady){
        bReady.setVisibility(View.INVISIBLE);
        bReady.postDelayed(new Runnable() {
            public void run() {
                bReady.setVisibility(View.VISIBLE);
            }
        }, 8000);

    }
}
