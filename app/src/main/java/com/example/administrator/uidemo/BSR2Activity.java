package com.example.administrator.uidemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;

import com.example.administrator.uidemo.view.Bezier.Bezier2;
import com.kyleduo.switchbutton.SwitchButton;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BSR2Activity extends AppCompatActivity {

    @BindView(R.id.sb1)
    SwitchButton sb1;
    @BindView(R.id.bezier2)
    Bezier2 bezier2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bsr2);
        ButterKnife.bind(this);

        sb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                     bezier2.setIsmode(!b);//设置控制点

            }
        });
    }
}
