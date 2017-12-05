package com.example.administrator.uidemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.uidemo.view.Bezier.Bezier1;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BSRActivity extends AppCompatActivity {

    @BindView(R.id.bszier1)
    Bezier1 bszier1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bsr);
        ButterKnife.bind(this);
    }
}
