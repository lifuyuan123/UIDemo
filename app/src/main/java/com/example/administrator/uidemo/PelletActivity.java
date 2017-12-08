package com.example.administrator.uidemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.uidemo.view.Bezier.Pellet;
import com.example.administrator.uidemo.view.MagicCircle;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PelletActivity extends AppCompatActivity {

    @BindView(R.id.MagicCircle)
    MagicCircle pellt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pellet);
        ButterKnife.bind(this);
        pellt.startAnimation();
    }
}
