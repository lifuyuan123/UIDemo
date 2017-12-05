package com.example.administrator.uidemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.uidemo.view.custom.CustomView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BaseViewActivity extends AppCompatActivity {
    @BindView(R.id.custom)
    CustomView custom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_view);
        ButterKnife.bind(this);
    }
}
