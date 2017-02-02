package gamemodes;

import android.content.SharedPreferences;
import android.graphics.Color;
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
import com.example.esperanto.ButtonThread;
import com.example.esperanto.Controller;
import com.example.esperanto.Image;
import com.example.esperanto.R;
import com.github.jinatonic.confetti.CommonConfetti;

import org.json.JSONException;

import java.util.Random;

import static android.content.Context.MODE_PRIVATE;


public class Picture_choose_frag extends Fragment implements View.OnClickListener {

    private ImageView iPicture1, iPicture2, iPicture3, iPicture4;
    private TextView tElekti;
    private Button bReady;
    private MediaPlayer mPlayer;
    private int[] images = {R.id.iPicture1, R.id.iPicture2, R.id.iPicture3, R.id.iPicture4};
    private int[] sounds = {R.raw.sound1, R.raw.sound2, R.raw.sound3, R.raw.sound4};
    private Random random = new Random();
    private int soundPlay;
    private Controller c = new Controller(getActivity());
    private String levelType;
    private int level;
    private ViewGroup container;
    private ButtonThread buttonthread;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.picture_choose_frag, container, false);

        save(c.levelType,c.currentLevel,c.levelLength);

        this.container = container;
        iPicture1 = (ImageView) view.findViewById(R.id.iPicture1);
        iPicture2 = (ImageView) view.findViewById(R.id.iPicture2);
        iPicture3 = (ImageView) view.findViewById(R.id.iPicture3);
        iPicture4 = (ImageView) view.findViewById(R.id.iPicture4);

        tElekti = (TextView) view.findViewById(R.id.tElekti);

        levelType = c.levelType;
        level = c.currentLevel;

        new Image(iPicture1).execute("https://raw.githubusercontent.com/samilesma/Esperanto/master/serverfiler/v1/levels/"+levelType+"/"+level+"/1.png");
        new Image(iPicture2).execute("https://raw.githubusercontent.com/samilesma/Esperanto/master/serverfiler/v1/levels/"+levelType+"/"+level+"/2.png");
        new Image(iPicture3).execute("https://raw.githubusercontent.com/samilesma/Esperanto/master/serverfiler/v1/levels/"+levelType+"/"+level+"/3.png");
        new Image(iPicture4).execute("https://raw.githubusercontent.com/samilesma/Esperanto/master/serverfiler/v1/levels/"+levelType+"/"+level+"/4.png");

        soundPlay=random.nextInt(4)+1;
        System.out.println(soundPlay);
        new Audio("https://raw.githubusercontent.com/samilesma/Esperanto/master/serverfiler/v1/levels/"+levelType+"/"+level+"/"+soundPlay+".mp3");



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
            tElekti.setText("");
            new Audio("https://raw.githubusercontent.com/samilesma/Esperanto/master/serverfiler/v1/happy.mp3");
            CommonConfetti.rainingConfetti(this.container ,new int[] { Color.GREEN,Color.BLUE, Color.MAGENTA, Color.BLACK, Color.WHITE, Color.YELLOW })
                    .stream(3000l);
            buttonthread = new ButtonThread(bReady);
        }

        else{
            Toast.makeText(getActivity(), "malĝusta",
                    Toast.LENGTH_SHORT).show();
        }

        if(v==bReady){

            try {
                getFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
                        .replace(R.id.fragmentindhold, (Fragment) c.getNextLevel()).addToBackStack(null).commit();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    public void save(String type, int lvl, int len) {
        SharedPreferences.Editor editor = getActivity().getSharedPreferences("saved", MODE_PRIVATE).edit();
        editor.putString("type",type);
        editor.putInt("lvl",lvl);
        editor.putInt("len",len);
        editor.putBoolean("saved",true);
        editor.commit();
    }
}
