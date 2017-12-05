package com.example.administrator.uidemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.uidemo.view.custom.PathView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PathBaseActivity extends AppCompatActivity {

    @BindView(R.id.pathview)
    PathView pathview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path_base);
        ButterKnife.bind(this);
    }
}
