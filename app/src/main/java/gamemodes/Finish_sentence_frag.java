package gamemodes;


import android.annotation.SuppressLint;
import android.content.ClipData;
import android.graphics.Typeface;
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
import org.json.JSONObject;

import static com.example.esperanto.R.id.t1;
import static com.example.esperanto.R.id.t2;


public class Finish_sentence_frag extends Fragment {

    Controller c=new Controller(getActivity());
    TextView t, tT;
    ImageView i1,i2;
    private int[] ts={R.id.tText1,R.id.tText2,R.id.tText3,R.id.tText4,R.id.tText5,R.id.tText6};
    private int[] tTs={R.id.tTarget1,R.id.tTarget2,R.id.tTarget3,R.id.tTarget4};
    private int[] rand={1,2,3,4,5,6};
    private String levelType;
    private int currentLevel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.finish_the_setence_frag, container, false);
        levelType = c.levelType;
        currentLevel = c.currentLevel;
        rand=c.RandomizeArray(rand);
        JSONArray Jimages1=null;
        JSONArray Jimages2=null;
        JSONArray images=null;
        try {
            Jimages1=c.json.getJSONArray("GM");
            JSONObject Jimages3=Jimages1.getJSONObject(c.levelLength-1);
            Jimages1=Jimages3.getJSONArray("list1");
            Jimages2=Jimages3.getJSONArray("list2");
            images=Jimages3.getJSONArray("images");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for(int i=1;i<=6;i++) {
            t = (TextView) view.findViewById(ts[i-1]);
            try {
                if(i<5) t.setText(Jimages1.getString(i-1));
                else t.setText(Jimages2.getString(i-1));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            t.setOnTouchListener(new ChoiceTouchListener());
        }

        for(int i=1;i<=4;i++) {
            tT = (TextView) view.findViewById(tTs[i-1]);
            try {
                tT.setTag(Jimages1.getString(i-1));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            tT.setOnDragListener(new ChoiceDragListener());
        }

        i1 = (ImageView) view.findViewById(R.id.iDescribe1);
        i2 = (ImageView) view.findViewById(R.id.iDescribe2);
        try {
            new Image(i1).execute("http://quickconnect.dk/esperanto/levels/"+levelType+"/"+currentLevel+"/"+images.getInt(0)+".png");
            new Image(i2).execute("http://quickconnect.dk/esperanto/levels/"+levelType+"/"+currentLevel+"/"+images.getInt(1)+".png");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return view;
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
                    TextView dropTarget = (TextView) v;
                    //view being dragged and dropped
                    TextView dropped = (TextView) view;
                    //checking whether first character of dropTarget equals first character of dropped
                    if(dropTarget.getTag().toString().contains(dropped.getText().toString())    )
                    {
                        //stop displaying the view where it was before it was dragged
                        view.setVisibility(View.INVISIBLE);
                        //update the text in the target view to reflect the data being dropped
                        //make it bold to highlight the fact that an item has been dropped
                        dropTarget.setTypeface(Typeface.DEFAULT_BOLD);
                        //if an item has already been dropped here, there will be a tag
                        Object tag = dropTarget.getTag();
                        //if there is already an item here, set it back visible in its original place
                        if(tag!=null)
                        {

                        }
                        //set the tag in the target view being dropped on - to the ID of the view being dropped
                        dropTarget.setText(dropTarget.getText() + " " + dropped.getText());
                        //remove setOnDragListener by setting OnDragListener to null, so that no further drag & dropping on this TextView can be done
                     //   dropTarget.setOnDragListener(null);
                    }
                    else
                        //displays message if first character of dropTarget is not equal to first character of dropped
                        Toast.makeText(getActivity(), "Malgûsta",
                                Toast.LENGTH_LONG).show();                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    //no action necessary
                    break;
                default:
                    break;
            }
            return true;
        }
    }

    public Finish_sentence_frag newInstance(int image1, String t1, String t2, String t3, String t4, String tTarget) {
        Finish_sentence_frag f = new Finish_sentence_frag();
        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putInt("image", R.drawable.domo);
        args.putString("Text1","Plomo");
        args.putString("Text2","Auto");
        args.putString("Text3","Domo");
        args.putString("Text4","Cevalo");
        args.putString("Text5","Plata");
        args.putString("tTarget","Domo");
        f.setArguments(args);
        return f;
    }


}
