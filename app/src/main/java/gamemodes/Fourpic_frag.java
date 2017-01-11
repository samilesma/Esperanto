package gamemodes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.esperanto.Controller;
import com.example.esperanto.Image;
import com.example.esperanto.R;

import org.json.JSONArray;
import org.json.JSONException;

public class Fourpic_frag extends Fragment implements View.OnClickListener {

    private TextView tLearn1,tLearn2,tLearn3,tLearn4;
    private ImageView ivLearn1, ivLearn2, ivLearn3,ivLearn4;
    private ImageView ivSound1, ivSound2, ivSound3, ivSound4;
    private Button bReady;
    private Controller c;
    private String levelType;
    private int currentLevel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View show = inflater.inflate(R.layout.fourpic_frag, container, false);

        tLearn1 = (TextView) show.findViewById(R.id.tLearn1);
        tLearn2 = (TextView) show.findViewById(R.id.tLearn2);
        tLearn3 = (TextView) show.findViewById(R.id.tLearn3);
        tLearn4 = (TextView) show.findViewById(R.id.tLearn4);

        ivLearn1 = (ImageView) show.findViewById(R.id.ivLearn1);
        ivLearn2 = (ImageView) show.findViewById(R.id.ivLearn2);
        ivLearn3 = (ImageView) show.findViewById(R.id.ivLearn3);
        ivLearn4 = (ImageView) show.findViewById(R.id.ivLearn4);

        levelType = c.levelType;
        currentLevel = c.currentLevel;

        new Image(ivLearn1).execute("http://quickconnect.dk/esperanto/levels/"+levelType+"/"+currentLevel+"/1.png");
        new Image(ivLearn2).execute("http://quickconnect.dk/esperanto/levels/"+levelType+"/"+currentLevel+"/2.png");
        new Image(ivLearn3).execute("http://quickconnect.dk/esperanto/levels/"+levelType+"/"+currentLevel+"/3.png");
        new Image(ivLearn4).execute("http://quickconnect.dk/esperanto/levels/"+levelType+"/"+currentLevel+"/4.png");

        try {
            JSONArray images=c.json.getJSONArray("images");

            tLearn1.setText(images.getString(0));
            tLearn2.setText(images.getString(1));
            tLearn3.setText(images.getString(2));
            tLearn4.setText(images.getString(3));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        bReady = (Button) show.findViewById(R.id.bReady);
        bReady.setOnClickListener(this);


        return show;
    }

    @Override
    public void onClick(View v) {
        if(v==bReady){
            try {
                getFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
                        .replace(R.id.fragmentindhold, (Fragment) c.getNextLevel()).addToBackStack(null).commit();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
