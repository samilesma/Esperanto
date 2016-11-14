package easy.testing;


import android.content.ClipData;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.esperanto.myapplication.R;

public class Abc123test extends Fragment implements View.OnTouchListener {
    private ImageView i1,i2,i3,i4,iUN1,iUN2,iUN3,iUN4;
    private TextView t1,t2,t3,t4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.abc123, container, false);

        i1 = (ImageView) view.findViewById(R.id.i1);
        i2 = (ImageView) view.findViewById(R.id.i2);
        i3 = (ImageView) view.findViewById(R.id.i3);
        i4 = (ImageView) view.findViewById(R.id.i4);

        iUN1 = (ImageView) view.findViewById(R.id.iUN1);
        iUN2 = (ImageView) view.findViewById(R.id.iUN2);
        iUN3 = (ImageView) view.findViewById(R.id.iUN3);
        iUN4 = (ImageView) view.findViewById(R.id.iUN4);

        i1.setOnTouchListener(this);
        i2.setOnTouchListener(this);
        i3.setOnTouchListener(this);
        i4.setOnTouchListener(this);

        return view;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            ClipData clipData = ClipData.newPlainText("", "");
            View.DragShadowBuilder dsb = new View.DragShadowBuilder(v);
            v.startDrag(clipData, dsb, v, 0);
            v.setVisibility(View.INVISIBLE);
            return true;
        } else {
            return false;
        }
    }
}
