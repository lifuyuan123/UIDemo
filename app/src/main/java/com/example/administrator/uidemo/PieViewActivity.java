package com.example.administrator.uidemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.administrator.uidemo.bean.PieData;
import com.example.administrator.uidemo.view.custom.PieView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PieViewActivity extends AppCompatActivity {
    @BindView(R.id.pieview)
    PieView pieview;
    private List<PieData> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_view);
        ButterKnife.bind(this);
        initData();
    }
    private void initData() {
        for (int i = 0; i < 9; i++) {
            list.add(new PieData(new Random().nextInt(10000)));
        }
        pieview.setPieDatas(list);
    }
}
