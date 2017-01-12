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
import com.example.esperanto.R;


public class Finish_sentence_frag extends Fragment {



        Controller c;
        TextView t1, t2, t3, t4, t5, t6, tTarget1, tTarget2, tTarget3, tTarget4;
        ImageView i1,i2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.finish_the_setence_frag, container, false);
        Bundle args = getArguments();


        t1 = (TextView) view.findViewById(R.id.tText1);
        t2 = (TextView) view.findViewById(R.id.tText2);
        t3 = (TextView) view.findViewById(R.id.tText3);
        t4 = (TextView) view.findViewById(R.id.tText4);
        t5 = (TextView) view.findViewById(R.id.tText5);
        t6 = (TextView) view.findViewById(R.id.tText6);
<<<<<<< HEAD

        tTarget1 = (TextView) view.findViewById(R.id.tTarget1);
        tTarget2 = (TextView) view.findViewById(R.id.tTarget2);
        tTarget3 = (TextView) view.findViewById(R.id.tTarget3);
        tTarget4 = (TextView) view.findViewById(R.id.tTarget4);


        // Twoletters.class.getMethods();
        tTarget1.setTag("Uno");
        tTarget2.setTag("Auto");
        tTarget3.setTag("Dos");
        tTarget4.setTag("Banano");

        i1 = (ImageView) view.findViewById(R.id.iDescribe1);
        i2 = (ImageView) view.findViewById(R.id.iDescribe2);



        t1.setText("Auto");
        t2.setText("Cevalo");
        t3.setText("Dos");
        t4.setText("Uno");
        t5.setText("Banano");
        t6.setText("Citrono");
=======
        tTarget = (TextView) view.findViewById(R.id.tTarget);

4
        tTarget.setTag("Citrono" + "Plato" + "O");
        tTarget.setText("This is");

        i1 = (ImageView) view.findViewById(R.id.iDescribe);

        //t1.setText(args.getString("Text1"));
        //t2.setText(args.getString("Text2"));
        //t3.setText(args.getString("Text3"));
        //t4.setText(args.getString("Text4"));
        //t5.setText(args.getString("Text5"));

        t1.setText("Plato");
        t2.setText("O");
        t3.setText("Plomo");
        t4.setText("Domo");
        t5.setText("Citrono");
        t6.setText("Auto");
>>>>>>> origin/master
        i1.setImageResource(R.mipmap.citrono);

        t1.setOnTouchListener(new ChoiceTouchListener());
        t2.setOnTouchListener(new ChoiceTouchListener());
        t3.setOnTouchListener(new ChoiceTouchListener());
        t4.setOnTouchListener(new ChoiceTouchListener());
        t5.setOnTouchListener(new ChoiceTouchListener());
        t6.setOnTouchListener(new ChoiceTouchListener());

        tTarget1.setOnDragListener(new ChoiceDragListener());
        tTarget2.setOnDragListener(new ChoiceDragListener());
        tTarget3.setOnDragListener(new ChoiceDragListener());
        tTarget4.setOnDragListener(new ChoiceDragListener());

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
                        dropTarget.setText(dropped.getText());
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
