package gamemodes;


import android.media.AudioManager;
import android.media.Image;
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

import com.example.esperanto.R;



import java.util.Random;


public class Picture_choose_frag extends Fragment implements View.OnClickListener {

    private ImageView iPicture1, iPicture2, iPicture3, iPicture4;
    private TextView tElekti;
    private Button bReady;
    private MediaPlayer mPlayer;
    private int[] images = {R.id.iPicture1, R.id.iPicture2, R.id.iPicture3, R.id.iPicture4};
    private int[] sounds = {R.raw.sound1, R.raw.sound2, R.raw.sound3, R.raw.sound4};
    private Random random = new Random();
    int soundPlay;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.picture_choose_frag, container, false);




        iPicture1 = (ImageView) view.findViewById(R.id.iPicture1);
        iPicture2 = (ImageView) view.findViewById(R.id.iPicture2);
        iPicture3 = (ImageView) view.findViewById(R.id.iPicture3);
        iPicture4 = (ImageView) view.findViewById(R.id.iPicture4);

        tElekti = (TextView) view.findViewById(R.id.tElekti);

        iPicture1.setImageResource(R.mipmap.auto1);
        iPicture2.setImageResource(R.mipmap.banano);
        iPicture3.setImageResource(R.mipmap.citrono);
        iPicture4.setImageResource(R.mipmap.cevaloo);

        bReady = (Button) view.findViewById(R.id.bReady);

        iPicture1.setOnClickListener(this);
        iPicture2.setOnClickListener(this);
        iPicture3.setOnClickListener(this);
        iPicture4.setOnClickListener(this);

        bReady.setOnClickListener(this);
        bReady.setVisibility(View.INVISIBLE);


        soundPlay=random.nextInt(4);
        System.out.println(soundPlay);
        playSound(soundPlay);



        return view;
    }

    @Override
    public void onClick(View v) {
        if((v==iPicture1 && soundPlay==0) || (v==iPicture2 && soundPlay==1) || (v==iPicture3 && soundPlay==2)
                || (v==iPicture4 && soundPlay==3 )){
            tElekti.setText("Yaaaaaayyyyy!!!!");
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

    public void playSound(int sound){
        mPlayer = MediaPlayer.create(this.getContext(), sounds[sound]);
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mPlayer.start();
    }

}
