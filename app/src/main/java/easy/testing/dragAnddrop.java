package easy.testing;


import android.annotation.SuppressLint;
import android.content.ClipData;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.esperanto.R;



public class dragAnddrop extends Fragment {
    private ImageView i1,i2,i3,i4,iUN1,iUN2,iUN3,iUN4;
    private TextView t1,t2,t3,t4;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.abc123test, container, false);

        iUN1 = (ImageView) view.findViewById(R.id.iUN1);
        iUN2 = (ImageView) view.findViewById(R.id.iUN2);
        iUN3 = (ImageView) view.findViewById(R.id.iUN3);
        iUN4 = (ImageView) view.findViewById(R.id.iUN4);

        t1 = (TextView) view.findViewById(R.id.t1);
        t2 = (TextView) view.findViewById(R.id.t2);
        t3 = (TextView) view.findViewById(R.id.t3);
        t4 = (TextView) view.findViewById(R.id.t4);

        i1.setOnTouchListener(new ChoiceTouchListener());
        i2.setOnTouchListener(new ChoiceTouchListener());
        i3.setOnTouchListener(new ChoiceTouchListener());
        i4.setOnTouchListener(new ChoiceTouchListener());

        iUN1.setOnDragListener(new ChoiceDragListener());
        iUN2.setOnDragListener(new ChoiceDragListener());
        iUN3.setOnDragListener(new ChoiceDragListener());
        iUN4.setOnDragListener(new ChoiceDragListener());

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
                            //the tag is the view id already dropped here
                            ((ImageView) v).setImageResource(R.drawable.undropped);
                        }
                        //set the tag in the target view being dropped on - to the ID of the view being dropped
                        dropTarget.setTag(dropped.getId());
                        //remove setOnDragListener by setting OnDragListener to null, so that no further drag & dropping on this TextView can be done
                        dropTarget.setOnDragListener(null);
                    }
                    else
                        //displays message if first character of dropTarget is not equal to first character of dropped
                        Toast.makeText(getActivity(), "Wrong",
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
    public void reset(View view)
    {
    }

    public void setImages(ImageView i1,ImageView i2,ImageView i3, ImageView i4){
        this.i1 = i1;
        this.i2 = i2;
        this.i3 = i3;
        this.i4 = i4;
    }

    public void setText(String t1, String t2, String t3, String t4){
        this.t1.setText(t1);
        this.t2.setText(t2);
        this.t3.setText(t3);
        this.t4.setText(t4);
    }

    public void setTags(int i1,int i2,int i3, int i4, int t1, int t2, int t3, int t4){
        this.i1.setTag(i1);
        this.i2.setTag(i2);
        this.i3.setTag(i3);
        this.i4.setTag(i4);

        this.iUN1.setTag(t1);
        this.iUN2.setTag(t2);
        this.iUN3.setTag(t3);
        this.iUN4.setTag(t4);
    }
}