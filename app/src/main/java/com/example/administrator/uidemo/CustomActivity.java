package com.example.administrator.uidemo;

import android.content.Intent;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.administrator.uidemo.adapter.CommonAdapter;
import com.example.administrator.uidemo.bean.PieData;
import com.example.administrator.uidemo.databinding.MainItemBinding;
import com.example.administrator.uidemo.view.custom.CustomView;
import com.example.administrator.uidemo.view.custom.PieView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomActivity extends AppCompatActivity {



    @BindView(R.id.rv_custom)
    RecyclerView rvCustom;
    private LinearLayoutManager manager=new LinearLayoutManager(this);
    private CommonAdapter<String> adapter;
    private List<String> stringList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom);
        ButterKnife.bind(this);

        stringList.add("基本图形");
        stringList.add("饼状图(扇形)");
        stringList.add("path基本操作");
        stringList.add("贝塞尔曲线(一)");
        stringList.add("贝塞尔曲线(二)");
        stringList.add("贝塞尔曲线(心型)");
        stringList.add("贝塞尔曲线(变化的圆球)");

        adapter=new CommonAdapter<String>(stringList,R.layout.main_item) {
            @Override
            protected void bindViewItemData(ViewDataBinding binding, int position, String s) {
                MainItemBinding i= (MainItemBinding) binding;
                i.tvMainItem.setText(s);
            }
        };

        rvCustom.setLayoutManager(manager);
        rvCustom.setAdapter(adapter);
        adapter.setOnItemClickListener(new CommonAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View item, int position) {
                switch (stringList.get(position)){
                    case "基本图形":
                        startActivity(new Intent(CustomActivity.this,BaseViewActivity.class));
                        break;
                    case "饼状图(扇形)":
                        startActivity(new Intent(CustomActivity.this,PieViewActivity.class));
                        break;
                    case "path基本操作":
                        startActivity(new Intent(CustomActivity.this,PathBaseActivity.class));
                        break;
                    case "贝塞尔曲线(一)":
                    startActivity(new Intent(CustomActivity.this,BSRActivity.class));
                        break;
                    case "贝塞尔曲线(二)":
                        startActivity(new Intent(CustomActivity.this,BSR2Activity.class));
                        break;
                    case "贝塞尔曲线(心型)":
                        startActivity(new Intent(CustomActivity.this,HeartActivity.class));
                        break;
                    case "贝塞尔曲线(变化的圆球)":
                        startActivity(new Intent(CustomActivity.this,PelletActivity.class));
                        break;
                }

            }
        });


    }

}
