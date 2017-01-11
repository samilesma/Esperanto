package gamemodes;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.esperanto.Controller;
import com.example.esperanto.R;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static android.R.attr.type;
import static com.example.esperanto.R.id.imageView;


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

        ivLearn1.setImageResource(R.mipmap.auto1);
        ivLearn2.setImageResource(R.mipmap.banano);
        ivLearn3.setImageResource(R.mipmap.citrono);
        ivLearn4.setImageResource(R.mipmap.cevaloo);

        /*
        levelType = c.levelType;
        System.out.println(levelType);
        currentLevel = c.currentLevel;
        System.out.println(currentLevel);

        URL url = null;
        try {
            url = new URL("http://quickconnect.dk/esperanto/levels/"+levelType+"/"+currentLevel+"/1.jpg");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Bitmap bmp = null;
        try {
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        ivLearn1.setImageBitmap(bmp);
        */

        tLearn1.setText("Auto");
        tLearn2.setText("Banano");
        tLearn3.setText("Citrono");
        tLearn4.setText("Cevalo");

        bReady = (Button) show.findViewById(R.id.bReady);
        bReady.setOnClickListener(this);

        return show;
    }

    @Override
    public void onClick(View v) {
        if(v==bReady){
            getFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
                    .replace(R.id.fragmentindhold, new DragAnddrop_frag()).addToBackStack(null).commit();
        }
    }
}
