package com.example.administrator.uidemo;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.uidemo.Utils.StatusBarUtil;
import com.example.administrator.uidemo.adapter.CommonAdapter;
import com.example.administrator.uidemo.databinding.MainItemBinding;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.srf)
    SmartRefreshLayout srf;
    @BindView(R.id.toolbar_main)
    Toolbar toolbarMain;
    private CommonAdapter<String> adapter;
    private LinearLayoutManager manager;
    private List<String> list = new ArrayList<>();
    private HeaderAndFooterWrapper headerAndFooterWrapper;//头尾布局适配器 传入我们的adapter
    private View footView;

    private long firstTime=0;
    private long secondTime=0;
    private long differenceTime=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbarMain);
        toolbarMain.setTitle("main");
        //沉浸式状态
        StatusBarUtil.darkMode(this);
        StatusBarUtil.setPaddingSmart(this, toolbarMain);

        //设置 Header 为 Material样式
        srf.setRefreshHeader(new MaterialHeader(this).setShowBezierWave(true));
        //设置 Footer 为 球脉冲
        srf.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));




        adapter = new CommonAdapter<String>(list, R.layout.main_item) {
            @Override
            protected void bindViewItemData(ViewDataBinding binding, int position, String s) {
                MainItemBinding itemBinding = (MainItemBinding) binding;
                itemBinding.tvMainItem.setText(s);
            }
        };

        footView= LayoutInflater.from(this).inflate(R.layout.foot_view,null);
        footView.setVisibility(View.GONE);
        headerAndFooterWrapper =new HeaderAndFooterWrapper(adapter);
        headerAndFooterWrapper.addFootView(footView);

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(headerAndFooterWrapper);//将之前的adapter交付给headerAndFooterWrapper之后现在传入的是headerAndFooterWrapper

        srf.setOnRefreshLoadmoreListener(new OnRefreshLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                srf.finishLoadmore();
                footView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                srf.finishRefresh();
                footView.setVisibility(View.GONE);
            }
        });

        adapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View item, int position) {
                switch (list.get(position)){
                    case "加载动画":
                       startActivity(new Intent(MainActivity.this,LoadAnimationActivity.class));
                        break;
                    case "开始动画":
                        startActivity(new Intent(MainActivity.this,StartActivity.class));
                        break;
                    case "仪表盘":
                        startActivity(new Intent(MainActivity.this,DashboardActivity.class));
                        break;
                    case "圆形头像":
                        startActivity(new Intent(MainActivity.this,CircleimageActivity.class));
                        break;
                    case "模糊遮挡":
                        startActivity(new Intent(MainActivity.this,VagueActivity.class));
                        break;
                    case "圆角":
                        startActivity(new Intent(MainActivity.this,FilletActivity.class));
                        break;
                    case "EditText":
                        startActivity(new Intent(MainActivity.this,EditTextActivity.class));
                        break;
                    case "沉浸放大效果":
                        startActivity(new Intent(MainActivity.this,EnlargeActivity.class));
                        break;
                    case "沉浸title动画效果":
                        startActivity(new Intent(MainActivity.this,AnimaTitleActivity.class));
                        break;
                    case "可拖动按钮":
                        startActivity(new Intent(MainActivity.this,FloatImageActivity.class));
                        break;
                    case "自定义view":
                        startActivity(new Intent(MainActivity.this,CustomActivity.class));
                        break;
                    case "TakePhoto":
                        startActivity(new Intent(MainActivity.this,TakePhotoActivity.class));
                        break;

                }

            }
        });

        //获取数据源
        getdata();
    }

    private void getdata() {
        list.add("加载动画");
        list.add("开始动画");
        list.add("仪表盘");
        list.add("圆形头像");
        list.add("模糊遮挡");
        list.add("圆角");
        list.add("EditText");
        list.add("沉浸放大效果");
        list.add("沉浸title动画效果");
        list.add("可拖动按钮");
        list.add("自定义view");
        list.add("TakePhoto");


    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode==KeyEvent.KEYCODE_BACK){
            firstTime=System.currentTimeMillis();
            differenceTime=firstTime-secondTime;//时间差
            secondTime=firstTime;//将第一次进入的时间赋值给secondTime
            if (differenceTime>2000){
                Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            }else {
                System.exit(0);//退出程序
            }
        }

        return true;//返回值和 onTouch事件相似   返回为true表示不再向下事件分发   false表示向下分发
    }
}
