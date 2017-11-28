package com.example.administrator.uidemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.administrator.uidemo.view.swipelayout.MyBaseActivity;
import com.github.mmin18.widget.RealtimeBlurView;

import butterknife.BindView;
import butterknife.ButterKnife;

//模糊
public class VagueActivity extends MyBaseActivity {

    @BindView(R.id.blur_view)
    RealtimeBlurView blur_view;
    @BindView(R.id.blur_radius)
    SeekBar blurRadius;
    @BindView(R.id.list)
    ListView list;
    @BindView(R.id.blur_radius_val)
    TextView blurRadiusText;
    @BindView(R.id.drag)
    Button drag;
    @BindView(R.id.blur_frame)
    RelativeLayout blurFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vague);
        ButterKnife.bind(this);
        blurRadius.setProgress(10);
        blurRadius.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateRadius();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
        updateRadius();
        list.setAdapter(new MyListAdapter(this));
        drag.setOnTouchListener(new View.OnTouchListener() {
            float dx, dy;
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                View target = findViewById(R.id.blur_frame);
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    dx = target.getX() - event.getRawX();
                    dy = target.getY() - event.getRawY();
                } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
                    target.setX(event.getRawX() + dx);
                    target.setY(event.getRawY() + dy);
                }
                return true;
            }
        });


    }

    private void updateRadius() {
        blur_view.setBlurRadius(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, blurRadius.getProgress(), getResources().getDisplayMetrics()));
    }

    private boolean slideUp;
    public void doSlide(View v) {
        final View view = findViewById(R.id.blur_frame);
        view.animate().translationYBy((slideUp ? -1 : 1) * view.getHeight()).setDuration(1000).start();
        slideUp = !slideUp;
    }
}
