package com.example.administrator.uidemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.uidemo.view.LauncherView;
import com.example.administrator.uidemo.view.swipelayout.MyBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartActivity extends MyBaseActivity {

    @BindView(R.id.launcher)
    LauncherView launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_start)
    public void onClick() {
        launcher.start();
    }
}
