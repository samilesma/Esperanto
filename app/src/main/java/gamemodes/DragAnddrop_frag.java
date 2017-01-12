package gamemodes;


import android.annotation.SuppressLint;
import android.content.ClipData;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.esperanto.Controller;
import com.example.esperanto.Image;
import com.example.esperanto.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;


public class DragAnddrop_frag extends Fragment implements View.OnClickListener{
    private ImageView imageDrag,imageDrop;
    private TextView text;
    private int[] imagesDrag={R.id.i1,R.id.i2,R.id.i3,R.id.i4},imagesDrop={R.id.iUN1,R.id.iUN2,R.id.iUN3,R.id.iUN4},texts={R.id.t1,R.id.t2,R.id.t3,R.id.t4};
    private Button bReady;
    private int correct;
    private Controller c=new Controller(getActivity());
    private String levelType;
    private int currentLevel;
    private int[] rand={1,2,3,4};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.drag_and_drop_frag, container, false);
        levelType = c.levelType;
        currentLevel = c.currentLevel;
        rand=c.RandomizeArray(rand);

        for(int i=1;i<=4;i++) System.out.println(rand[i-1]+" ");

        JSONArray Jimages=null;
        try {
            Jimages=c.json.getJSONArray("images");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for(int i=1;i<=4;i++){
            imageDrag = (ImageView) view.findViewById(imagesDrag[i-1]);
            imageDrop = (ImageView) view.findViewById(imagesDrop[i-1]);
            text = (TextView) view.findViewById(texts[i-1]);
            new Image(imageDrag).execute("http://quickconnect.dk/esperanto/levels/"+levelType+"/"+currentLevel+"/"+rand[i-1]+".png");
            imageDrag.setOnTouchListener(new ChoiceTouchListener());
            imageDrop.setOnDragListener(new ChoiceDragListener());
            imageDrag.setTag(rand[i-1]);
            imageDrop.setTag(i);
            try {
                text.setText(Jimages.getString(i-1));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        bReady = (Button) view.findViewById(R.id.bReady);
        bReady.setVisibility(View.INVISIBLE);
        bReady.setOnClickListener(this);
        correct=0;
        return view;
    }

    @Override
    public void onClick(View v) {
        if(v==bReady){
            getFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right)
                    .replace(R.id.fragmentindhold, new Picture_choose_frag()).addToBackStack(null).commit();
        }
    }

    private final class ChoiceTouchListener implements View.OnTouchListener {
        @SuppressLint("NewApi")
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            /*
             * Drag details: we only need default behavior
             * - clip data could be set to pass data as part of drag
             * - shadow can be tailored
             */
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                //start dragging the item touched
                view.startDrag(data, shadowBuilder, view, 0);
                return true;
            } else {
                return false;
            }
        }
    }
    @SuppressLint("NewApi")
    private class ChoiceDragListener implements View.OnDragListener {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DROP:

                    //handle the dragged view being dropped over a drop view
                    View view = (View) event.getLocalState();
                    //view dragged item is being dropped on
                    ImageView dropTarget = (ImageView) v;
                    //view being dragged and dropped
                    ImageView dropped = (ImageView) view;
                    //checking whether first character of dropTarget equals first character of dropped
                    if(dropTarget.getTag() == dropped.getTag())
                    {
                        //stop displaying the view where it was before it was dragged
                        view.setVisibility(View.INVISIBLE);
                        //update the text in the target view to reflect the data being dropped
                        // dropTarget.setText(dropTarget.getText().toString() + dropped.getText().toString());
                        //make it bold to highlight the fact that an item has been dropped
                        //dropTarget.setTypeface(Typeface.DEFAULT_BOLD);
                        //if an item has already been dropped here, there will be a tag
                        Object tag = dropTarget.getTag();
                        //if there is already an item here, set it back visible in its original place
                        if(tag!=null)
                        {
                            correct++;
                            for(int i=1;i<=4;i++){
                                if(dropped.getTag().toString().equals(""+i)) new Image((ImageView) v).execute("http://quickconnect.dk/esperanto/levels/"+levelType+"/"+currentLevel+"/"+i+".png");
                            }
                        }
                        //set the tag in the target view being dropped on - to the ID of the view being dropped
                        dropTarget.setTag(dropped.getId());
                        //remove setOnDragListener by setting OnDragListener to null, so that no further drag & dropping on this TextView can be done
                        dropTarget.setOnDragListener(null);

                        if(correct==4) bReady.setVisibility(View.VISIBLE);
                    }
                    else
                        //displays message if first character of dropTarget is not equal to first character of dropped
                        Toast.makeText(getActivity(), "Malgûsta",
                                Toast.LENGTH_LONG).show();
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    //no action necessary
                    break;
                default:
                    break;
            }
            return true;
        }
    }
}
