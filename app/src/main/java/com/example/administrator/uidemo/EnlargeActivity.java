package com.example.administrator.uidemo;

import android.databinding.ViewDataBinding;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.uidemo.Utils.StatusBarUtil;
import com.example.administrator.uidemo.adapter.CommonAdapter;
import com.example.administrator.uidemo.databinding.MainItemBinding;
import com.example.administrator.uidemo.view.swipelayout.MyBaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EnlargeActivity extends MyBaseActivity {

    @BindView(R.id.titlebar_view)
    View titlebarView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.rv)
    RecyclerView rv;
    private LinearLayoutManager manager=new LinearLayoutManager(this);
    private CommonAdapter<String> adapter;
    private List<String> list=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enlarge);
        ButterKnife.bind(this);

        //沉浸式状态
        StatusBarUtil.darkMode(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        collapsingToolbarLayout.setTitle("标题");

        initData();


        rv.setLayoutManager(manager);
        rv.setAdapter(adapter);
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            list.add("数据"+i);
        }

        adapter=new CommonAdapter<String>(list, R.layout.main_item) {
            @Override
            protected void bindViewItemData(ViewDataBinding binding, int position, String s) {
                MainItemBinding itemBinding= (MainItemBinding) binding;
                itemBinding.tvMainItem.setText(s);
            }
        };
    }
}
