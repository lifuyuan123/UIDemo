package com.example.administrator.uidemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.uidemo.view.Bezier.Heart;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HeartActivity extends AppCompatActivity {

    @BindView(R.id.heart)
    Heart heart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart);
        ButterKnife.bind(this);
    }
}
