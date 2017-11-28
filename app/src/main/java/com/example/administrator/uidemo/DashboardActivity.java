package com.example.administrator.uidemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.uidemo.view.dashboard.DashboardView2;
import com.example.administrator.uidemo.view.swipelayout.MyBaseActivity;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

//仪表盘
public class DashboardActivity extends MyBaseActivity {

    @BindView(R.id.dv_pregress)
    DashboardView2 dvPregress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);

        dvPregress.setCreditValueWithAnim(new Random().nextInt(1000));
    }

    @OnClick(R.id.dv_pregress)
    public void onClick() {
        dvPregress.setCreditValueWithAnim(new Random().nextInt(1000));
    }
}
