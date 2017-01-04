package gamemodes;


import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.esperanto.R;


public class Picture_choose_frag extends Fragment implements View.OnClickListener {

    private ImageView iPicture1, iPicture2, iPicture3, iPicture4;
    private TextView tElekti;
    private MediaPlayer mPlayer;

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

        iPicture1.setOnClickListener(this);
        iPicture2.setOnClickListener(this);
        iPicture3.setOnClickListener(this);
        iPicture4.setOnClickListener(this);

        iPicture1.setTag(1);
        iPicture2.setTag(2);
        iPicture3.setTag(3);
        iPicture4.setTag(4);

        mPlayer = MediaPlayer.create(getContext(), R.raw.cevalo);
        mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mPlayer.start();

        return view;
    }

    @Override
    public void onClick(View v) {
        if(v==iPicture1){
            System.out.println("Der trykkes på billede 1");
            mPlayer.start();
        }
        if(v==iPicture2) {
            System.out.println("Der trykkes på billede 2");
        }
        if(v==iPicture3) {
            System.out.println("Der trykkes på billede 3");
        }
        if(v==iPicture4) {
            System.out.println("Der trykkes på billede 4");
        }

    }
}
