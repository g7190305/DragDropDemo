package com.example.g7190305.dragdropdemo;

import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class CrossWordActivity extends ActionBarActivity {
    // private ImageView ivA;
    // private ImageView ivB;
    // private ImageView ivTarget;
    String[] letter = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    int[] ResourceTv = {
            R.id.tvA, R.id.tvB, R.id.tvC, R.id.tvD, R.id.tvE, R.id.tvF, R.id.tvG,
            R.id.tvH, R.id.tvI, R.id.tvJ, R.id.tvK, R.id.tvL, R.id.tvM, R.id.tvN,
            R.id.tvO, R.id.tvP, R.id.tvQ, R.id.tvR, R.id.tvS, R.id.tvT, R.id.tvU,
            R.id.tvV, R.id.tvW, R.id.tvX, R.id.tvY, R.id.tvZ
    };
    TextView[] tv = new TextView[26];
    // private static final String IMAGEVIEW_TAG = "The Android Logo";
    // private static final int IMAGEVIEW_TAG_KEY = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cross_word);

        for (int i=0; i < letter.length;i++) {
            tv[i] = (TextView) findViewById(ResourceTv[i]);
            tv[i].setTag(R.id.view_tag, letter[i]);
            tv[i].setOnTouchListener(new MyTouchListener());
        }

        TextView tvResult1 = (TextView) findViewById(R.id.tvResult1);
        TextView tvResult2 = (TextView) findViewById(R.id.tvResult2);
        TextView tvResult3 = (TextView) findViewById(R.id.tvResult3);
        TextView tvResult4 = (TextView) findViewById(R.id.tvResult4);
        TextView tvResult5 = (TextView) findViewById(R.id.tvResult5);

        tvResult1.setOnDragListener(new MyDragListener());
        tvResult2.setOnDragListener(new MyDragListener());
        tvResult3.setOnDragListener(new MyDragListener());
        tvResult4.setOnDragListener(new MyDragListener());
        tvResult5.setOnDragListener(new MyDragListener());
    }

    // This defines your touch listener
    private final class MyTouchListener implements View.OnTouchListener {
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ClipData.Item item = new ClipData.Item((CharSequence)view.getTag(R.id.view_tag));

                String[] mimeTypes = { ClipDescription.MIMETYPE_TEXT_PLAIN };
                ClipData data = new ClipData(view.getTag(R.id.view_tag).toString(), mimeTypes, item);

                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
                view.startDrag(data, shadowBuilder, view, 0);
                view.setVisibility(View.INVISIBLE);
                return true;
            } else {
                return false;
            }
        }
    }

    class MyDragListener implements View.OnDragListener {
        // Drawable enterShape = getResources().getDrawable(R.color.target_color);
        // Drawable normalShape = getResources().getColor(R.color.normal_color);
        View view;

        @Override
        public boolean onDrag(View v, DragEvent event) {
            int action = event.getAction();
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    // do nothing
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    Log.d("DEBUG", "ACTION DRAG ENTERED" );
                    // v.setBackgroundDrawable(enterShape);
                    // v.setBackgroundResource(R.color.target_color);
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    Log.d("DEBUG", "ACTION DRAP EXITED" );
                    // v.setBackgroundDrawable(normalShape);
                    // v.setBackgroundResource(R.color.normal_color);
                    break;
                case DragEvent.ACTION_DROP:
                    Log.d("DEBUG", "ACTION DROP" );
                    //if(v == findViewById(R.id.ivtarget)) {
                    view = (View) event.getLocalState();
                    ViewGroup viewgroup = (ViewGroup) view.getParent();
                        // viewgroup.removeView(view);
                    String tag = (String)view.getTag(R.id.view_tag);

                    ((TextView) v).setText(tag);
                        // for (String aLetter : letter) {
                        //     if (tag.equals(aLetter)) {
                        //         ((TextView) v).setText(aLetter);
                        //     }
                        // }
                        // v.setBackgroundResource(R.color.blue_color);
                        // ((ImageView)v).setImageResource(R.mipmap.ic_letter_a);
                        // ((ImageView)v).setImageResource(tag);
                        view.setVisibility(View.VISIBLE);
                    //}
                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    Log.d("DEBUG", "ACTION DRAG ENDED" );
                    view = (View) event.getLocalState();
                    view.setVisibility(View.VISIBLE);
                    // v.setBackgroundResource(R.color.target_color);
                    break;
                default:
                    break;
            }
            return true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cross_word, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
