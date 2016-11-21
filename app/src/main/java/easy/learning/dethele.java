package easy.learning;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


import com.example.esperanto.R;

import com.example.esperanto.MainFragment;



public class dethele extends AppCompatActivity {

    /*
    ***Activity.java fil, som indeholder koden for sidemenuen og koden for jeres egne ting
    inde i activitien.
    1 Den hænger sammen en ***_activity.xml fil, som definere jeres eget layout inde i activitien
    2 en ***_navigation_view.xml fil, som indeholder DrawerLayout øverst,
    med NavigationView under, som definere sidemenuen
    3 en ***_menu_items.xml, som er menu elementer, som ligger i menu mappen

    generic filer, som der kun skal være en af:
    4 side_nav_bar.xml som i kan tilpasse
    5 nav_header_main.xml som i kan tilpasse
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dethele);

        if (savedInstanceState == null) {
            Fragment fragment = new MainFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentindhold, fragment).commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


}
