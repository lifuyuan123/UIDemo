package com.example.administrator.uidemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.uidemo.view.swipelayout.MyBaseActivity;

//圆角处理
public class FilletActivity extends MyBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fillet);
    }
}
