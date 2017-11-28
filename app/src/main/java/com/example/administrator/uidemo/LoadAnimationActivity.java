package com.example.administrator.uidemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.administrator.uidemo.Utils.StatusBarUtil;
import com.example.administrator.uidemo.view.swipelayout.MyBaseActivity;
import com.example.administrator.uidemo.view.titlebar.TitleBar;
import com.mingle.widget.LoadingView;

import butterknife.BindView;
import butterknife.ButterKnife;

//加载动画
public class LoadAnimationActivity extends MyBaseActivity {

    @BindView(R.id.loadView)
    LoadingView loadView;
    @BindView(R.id.titlte)
    TitleBar titlte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_animation);
        ButterKnife.bind(this);
        titlte.setTitle("加载动画");
        titlte.setBackgroundColor(getColor(R.color.circle));
        titlte.setLeftImageResource(R.drawable.im_back);
        titlte.setLeftText("   返回");
        titlte.setLeftTextColor(Color.BLACK);
        titlte.setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        titlte.setDividerColor(Color.GRAY);//下划线
        StatusBarUtil.darkMode(this);//沉浸式
        titlte.setImmersive(true);//沉浸式
        loadView.setLoadingText("加载中...");
    }
}
