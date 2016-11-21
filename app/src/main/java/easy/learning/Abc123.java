package easy.learning;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.esperanto.R;

import easy.testing.Abc123test;


public class Abc123 extends Fragment implements View.OnClickListener {

    private TextView tLearn1,tLearn2,tLearn3,tLearn4;
    private ImageView ivLearn1, ivLearn2, ivLearn3,ivLearn4;
    private ImageView ivSound1, ivSound2, ivSound3, ivSound4;
    private Button bReady;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View show = inflater.inflate(R.layout.abc123, container, false);

        tLearn1 = (TextView) show.findViewById(R.id.tLearn1);
        tLearn2 = (TextView) show.findViewById(R.id.tLearn2);
        tLearn3 = (TextView) show.findViewById(R.id.tLearn3);
        tLearn4 = (TextView) show.findViewById(R.id.tLearn4);

        ivLearn1 = (ImageView) show.findViewById(R.id.ivLearn1);
        ivLearn2 = (ImageView) show.findViewById(R.id.ivLearn2);
        ivLearn3 = (ImageView) show.findViewById(R.id.ivLearn3);
        ivLearn4 = (ImageView) show.findViewById(R.id.ivLearn4);

        bReady = (Button) show.findViewById(R.id.bReady);

        return show;
    }

    @Override
    public void onClick(View v) {
        if(v==bReady){
            getFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
                    .replace(R.id.fragmentindhold, new Abc123()).addToBackStack(null).commit();

        }


    }
}
