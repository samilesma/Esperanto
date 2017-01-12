package gamemodes;

import android.media.AudioManager;
import android.media.MediaExtractor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.esperanto.Audio;
import com.example.esperanto.Controller;
import com.example.esperanto.Image;
import com.example.esperanto.R;

import org.json.JSONArray;
import org.json.JSONException;

public class Fourpic_frag extends Fragment implements View.OnClickListener {

    private TextView text;
    private ImageView image;
    private int texts[]={R.id.tLearn1,R.id.tLearn2,R.id.tLearn3,R.id.tLearn4};
    private int images[]={R.id.ivLearn1,R.id.ivLearn2,R.id.ivLearn3,R.id.ivLearn4};
    private ImageView ivSound1, ivSound2, ivSound3, ivSound4;
    private Button bReady;
    private Controller c=new Controller(getActivity());
    private String levelType;
    private int currentLevel;
    private MediaPlayer mediePlayer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View show = inflater.inflate(R.layout.fourpic_frag, container, false);
        levelType = c.levelType;
        currentLevel = c.currentLevel;
        JSONArray Jimages=null;
        try {
            Jimages=c.json.getJSONArray("images");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for(int i=1;i<=4;i++){
            text = (TextView) show.findViewById(texts[i-1]);
            image = (ImageView) show.findViewById(images[i-1]);
            new Image(image).execute("http://quickconnect.dk/esperanto/levels/"+levelType+"/"+currentLevel+"/"+i+".png");
            setOnClick(image,i);
            try {
                text.setText(Jimages.getString(i-1));
            } catch (JSONException e) {
                e.printStackTrace();
            }
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

    private void setOnClick(ImageView v, final int i){
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Audio("http://quickconnect.dk/esperanto/levels/" + levelType + "/" + currentLevel + "/" + i + ".mp3");
            }
        });
    }



}
