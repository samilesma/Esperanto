package gamemodes;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.SharedPreferences;
import android.graphics.Color;
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

import com.example.esperanto.Audio;
import com.example.esperanto.ButtonThread;
import com.example.esperanto.Controller;
import com.example.esperanto.Image;
import com.example.esperanto.R;
import com.github.jinatonic.confetti.CommonConfetti;

import org.json.JSONArray;
import org.json.JSONException;

import static android.content.Context.MODE_PRIVATE;


public class DragAnddrop_frag extends Fragment implements View.OnClickListener{
    private ImageView imageDrag,imageDrop;
    private TextView text;
    private int[] imagesDrag={R.id.i1,R.id.i2,R.id.i3,R.id.i4},imagesDrop={R.id.iUN1,R.id.iUN2,R.id.iUN3,R.id.iUN4},texts={R.id.t1,R.id.t2,R.id.t3,R.id.t4};
    private Button bReady;
    private String levelType;
    private int correct,currentLevel;
    private Controller c=new Controller(getActivity());
    public ViewGroup container;
    public ButtonThread buttonthread;
    private int[] rand={1,2,3,4};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.drag_and_drop_frag, container, false);

        save(c.levelType,c.currentLevel,c.levelLength);

        levelType = c.levelType;
        currentLevel = c.currentLevel;
        rand=c.RandomizeArray(rand);

        JSONArray Jimages=null;
        try {
            Jimages=c.json.getJSONArray("images");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        for(int i=1;i<=4;i++){
            ImageView imageDrag = (ImageView) view.findViewById(imagesDrag[i - 1]);
            ImageView imageDrop = (ImageView) view.findViewById(imagesDrop[i - 1]);
            text = (TextView) view.findViewById(texts[i-1]);
            new Image(imageDrag).execute("https://raw.githubusercontent.com/samilesma/Esperanto/master/serverfiler/v1/levels/"+levelType+"/"+currentLevel+"/"+rand[i-1]+".png");
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

        this.container=container;
        bReady = (Button) view.findViewById(R.id.bReady);
        bReady.setVisibility(View.INVISIBLE);
        bReady.setOnClickListener(this);
        correct=0;

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
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
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
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
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
                                if(dropped.getTag().toString().equals(""+i)) new Image((ImageView) v).execute("https://raw.githubusercontent.com/samilesma/Esperanto/master/serverfiler/v1/levels/"+levelType+"/"+currentLevel+"/"+i+".png");
                            }
                        }
                        //set the tag in the target view being dropped on - to the ID of the view being dropped
                        dropTarget.setTag(dropped.getId());
                        //remove setOnDragListener by setting OnDragListener to null, so that no further drag & dropping on this TextView can be done
                        dropTarget.setOnDragListener(null);


                        if(correct==4) {
                            new Audio("https://raw.githubusercontent.com/samilesma/Esperanto/master/serverfiler/v1/happy.mp3");
                            CommonConfetti.rainingConfetti(container ,new int[] { Color.GREEN,Color.BLUE, Color.BLACK, Color.MAGENTA, Color.WHITE, Color.YELLOW })
                                    .stream(1500l);
                            buttonthread = new ButtonThread(bReady);
                        }


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

    public void save(String type, int lvl, int len) {
        SharedPreferences.Editor editor = getActivity().getSharedPreferences("saved", MODE_PRIVATE).edit();
        editor.putString("type",type);
        editor.putInt("lvl",lvl);
        editor.putInt("len",len);
        editor.putBoolean("saved",true);
        editor.commit();
    }
}
