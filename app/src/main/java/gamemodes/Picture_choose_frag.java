package gamemodes;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.esperanto.Audio;
import com.example.esperanto.Controller;
import com.example.esperanto.Image;
import com.example.esperanto.R;
import org.json.JSONArray;
import org.json.JSONException;



import java.util.Random;




public class Picture_choose_frag extends Fragment implements View.OnClickListener {

    private ImageView iPicture1, iPicture2, iPicture3, iPicture4;
    private TextView tElekti;
    private Button bReady;
    private MediaPlayer mPlayer;
    private int[] images = {R.id.iPicture1, R.id.iPicture2, R.id.iPicture3, R.id.iPicture4};
    private int[] sounds = {R.raw.sound1, R.raw.sound2, R.raw.sound3, R.raw.sound4};
    private Random random = new Random();
    private int soundPlay;
    private Controller c;
    private String levelType;
    private int level;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.picture_choose_frag, container, false);




        iPicture1 = (ImageView) view.findViewById(R.id.iPicture1);
        iPicture2 = (ImageView) view.findViewById(R.id.iPicture2);
        iPicture3 = (ImageView) view.findViewById(R.id.iPicture3);
        iPicture4 = (ImageView) view.findViewById(R.id.iPicture4);

        tElekti = (TextView) view.findViewById(R.id.tElekti);

        levelType = c.levelType;
        level = c.currentLevel;

        new Image(iPicture1).execute("http://quickconnect.dk/esperanto/levels/"+levelType+"/"+level+"/1.png");
        new Image(iPicture2).execute("http://quickconnect.dk/esperanto/levels/"+levelType+"/"+level+"/2.png");
        new Image(iPicture3).execute("http://quickconnect.dk/esperanto/levels/"+levelType+"/"+level+"/3.png");
        new Image(iPicture4).execute("http://quickconnect.dk/esperanto/levels/"+levelType+"/"+level+"/4.png");

        soundPlay=random.nextInt(4)+1;
        System.out.println(soundPlay);
        new Audio("http://quickconnect.dk/esperanto/levels/"+levelType+"/"+level+"/"+soundPlay+".mp3");



        bReady = (Button) view.findViewById(R.id.bReady);

        iPicture1.setOnClickListener(this);
        iPicture2.setOnClickListener(this);
        iPicture3.setOnClickListener(this);
        iPicture4.setOnClickListener(this);

        bReady.setOnClickListener(this);
        bReady.setVisibility(View.INVISIBLE);

        return view;
    }

    @Override
    public void onClick(View v) {
        if((v==iPicture1 && soundPlay==1) || (v==iPicture2 && soundPlay==2) || (v==iPicture3 && soundPlay==3)
                || (v==iPicture4 && soundPlay==4 )){
            tElekti.setText("Yaaaaaayyyyy!!!!");
            tElekti.setTextColor(Color.GREEN);
            bReady.setVisibility(View.VISIBLE);
        }

        else{
            Toast.makeText(getActivity(), "malĝusta",
                    Toast.LENGTH_SHORT).show();
        }

        if(v==bReady){
            getFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
                    .replace(R.id.fragmentindhold, new DescribeImage_frag()).addToBackStack(null).commit();
        }

    }
}
