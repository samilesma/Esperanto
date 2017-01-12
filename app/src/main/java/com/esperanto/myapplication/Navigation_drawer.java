package com.esperanto.myapplication;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;

import com.example.esperanto.Controller;
import com.example.esperanto.Hovedmenu_frag;
import com.example.esperanto.Levels_frag;
import com.example.esperanto.R;
import com.example.esperanto.Settings_frag;

import gamemodes.Finish_sentence_frag;
import gamemodes.Fourpic_frag;

public class Navigation_drawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            /*
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentindhold, new Abc123()).commit();
*/
            Fragment fragment = new Hovedmenu_frag();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentindhold, fragment).commit();
        }

    }

    @Override
    public void onBackPressed() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
                    .replace(R.id.fragmentindhold, new Fourpic_frag(), "last").addToBackStack(null).commit();
        } else if (id == R.id.nav_gallery) {
            getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
                    .replace(R.id.fragmentindhold, new Levels_frag(), "last").addToBackStack(null).commit();


        } else if (id == R.id.nav_slideshow) {
            getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
                    .replace(R.id.fragmentindhold, new Settings_frag(), "last").addToBackStack(null).commit();


        }
        else if (id == R.id.nav_slideshow1) {
            getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
                    .replace(R.id.fragmentindhold, new Finish_sentence_frag(), "last").addToBackStack(null).commit();


        }
        /** else if (id == R.id.nav_manage) {

         }/** else if (id == R.id.nav_share) {

         } else if (id == R.id.nav_send) {

         }**/

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // Before 2.0
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        Fragment last = getSupportFragmentManager().findFragmentByTag("last");

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(last.isVisible()) moveTaskToBack(true);
            if (getFragmentManager().getBackStackEntryCount() > 0) {
                getFragmentManager().popBackStack();
            } else {
                super.onBackPressed();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
