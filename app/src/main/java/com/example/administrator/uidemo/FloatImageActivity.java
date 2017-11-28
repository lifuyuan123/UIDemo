package com.example.administrator.uidemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.uidemo.view.DragFloatActionButton;
import com.example.administrator.uidemo.view.FloatImage;
import com.example.administrator.uidemo.view.swipelayout.MyBaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FloatImageActivity extends MyBaseActivity {

    @BindView(R.id.image)
    FloatImage image;
    @BindView(R.id.fab)
    DragFloatActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_float_image);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.image, R.id.fab})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.image:
                Toast.makeText(this, "点击了图片", Toast.LENGTH_SHORT).show();
                break;
            case R.id.fab:
                Toast.makeText(this, "点击了fab", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
