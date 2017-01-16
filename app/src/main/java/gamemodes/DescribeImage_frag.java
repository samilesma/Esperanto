package gamemodes;
import android.annotation.SuppressLint;
import android.content.ClipData;
import android.graphics.Color;
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

import com.example.esperanto.ButtonThread;
import com.example.esperanto.Controller;
import com.example.esperanto.Image;
import com.example.esperanto.R;
import com.github.jinatonic.confetti.CommonConfetti;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class DescribeImage_frag extends Fragment implements View.OnClickListener {

    private TextView t1,tTarget;
    private ImageView i1;
    private Button bReady;
    private String levelType;
    private int currentLevel;
    private Controller c = new Controller(getActivity());
    public ViewGroup container;
    public ButtonThread buttonthread;
    private Random random = new Random();
    private int chooseWords;
    private int [] randomWords = {1,2,3,4,5,6};
    private int [] randomText = {R.id.tText1,R.id.tText2,R.id.tText3,R.id.tText4,R.id.tText5,R.id.tText6};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.describe_image_frag, container, false);

        this.container=container;

        levelType=c.levelType;
        currentLevel=c.currentLevel;
        randomWords=c.RandomizeArray(randomWords);

        JSONArray Jimages=null;
        JSONArray Jtext=null;
        JSONObject Jobject=null;
        try {
            Jimages=c.json.getJSONArray("images");
            Jtext = c.json.getJSONArray("gm");
            Jobject = Jtext.getJSONObject(c.levelLength-1);
            Jtext = Jobject.getJSONArray("list");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        for(int i=1;i<=6;i++) {
            t1 = (TextView) view.findViewById(randomText[i-1]);
            try {
                if(randomWords[i-1]<5) {
                    t1.setText(Jimages.getString(randomWords[i-1]-1));
                }
                else {
                    t1.setText(Jtext.getString(randomWords[i-1]-4-1));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            t1.setOnTouchListener(new ChoiceTouchListener());



        }
        chooseWords = random.nextInt(4);
        tTarget = (TextView) view.findViewById(R.id.tTarget);

        try {
            tTarget.setTag(Jimages.getString(chooseWords));
        } catch (JSONException e) {
            e.printStackTrace();
        }



        i1 = (ImageView) view.findViewById(R.id.iDescribe);
        new Image(i1).execute("http://quickconnect.dk/esperanto/levels/"+levelType+"/"+currentLevel+"/"+(chooseWords+1)+".png");
        bReady = (Button) view.findViewById(R.id.bReady);
        bReady.setOnClickListener(this);
        bReady.setVisibility(View.INVISIBLE);

        tTarget.setOnDragListener(new ChoiceDragListener());

        return view;
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
                    if(dropTarget.getTag().toString().charAt(0) == dropped.getText().toString().charAt(0))
                    {
                        CommonConfetti.rainingConfetti(container ,new int[] { Color.GREEN,Color.BLUE, Color.RED, Color.MAGENTA, Color.BLACK })
                                .stream(3000l);
                        buttonthread = new ButtonThread(bReady);

                        //stop displaying the view where it was before it was dragged
                        view.setVisibility(View.INVISIBLE);
                        //update the text in the target view to reflect the data being dropped
                        dropTarget.setText(dropTarget.getText().toString() + dropped.getText().toString());
                        //make it bold to highlight the fact that an item has been dropped
                        dropTarget.setTypeface(Typeface.DEFAULT_BOLD);
                        //if an item has already been dropped here, there will be a tag
                        Object tag = dropTarget.getTag();
                        //if there is already an item here, set it back visible in its original place
                        if(tag!=null)
                        {

                        }
                        //set the tag in the target view being dropped on - to the ID of the view being dropped
                        dropTarget.setText(dropped.getText());
                        //remove setOnDragListener by setting OnDragListener to null, so that no further drag & dropping on this TextView can be done
                        dropTarget.setOnDragListener(null);
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

    public DescribeImage_frag newInstance(int image1, String t1, String t2, String t3, String t4, String tTarget) {
        DescribeImage_frag f = new DescribeImage_frag();
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
