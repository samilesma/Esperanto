package com.esperanto.myapplication;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by ahmad on 22-11-2016.
 */

public class Menu extends AppCompatActivity {

    // Activity code here
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item:
                // do what you want here
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
