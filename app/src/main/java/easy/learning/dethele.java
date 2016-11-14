package easy.learning;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Button;

import com.esperanto.myapplication.MainActivity;
import com.esperanto.myapplication.R;

/**
 * Created by IbsenB on 07-11-2016.
 */

public class dethele extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dethele);

        if(savedInstanceState==null){
            Fragment fragment = new MainActivity();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentindhold,fragment).commit();
        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }
}
