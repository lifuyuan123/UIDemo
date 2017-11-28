package com.example.administrator.uidemo;

import android.databinding.ViewDataBinding;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.uidemo.Utils.DpUtils;
import com.example.administrator.uidemo.Utils.StatusBarUtil;
import com.example.administrator.uidemo.Utils.Uitll;
import com.example.administrator.uidemo.adapter.CommonAdapter;
import com.example.administrator.uidemo.databinding.MainItemBinding;
import com.example.administrator.uidemo.view.WaveView;
import com.example.administrator.uidemo.view.swipelayout.MyBaseActivity;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class AnimaTitleActivity extends MyBaseActivity implements AppBarLayout.OnOffsetChangedListener {

    private static final int PERCENTAGE_TO_ANIMATE_AVATAR = 20;//定义的滑动距离    大于25开始头像隐藏的动画
    @BindView(R.id.rv_anima)
    RecyclerView rvAnima;
    private boolean mIsAvatarShown = true; //头像显示隐藏

    @BindView(R.id.profile_backdrop)
    ImageView imageView;
    @BindView(R.id.view_toolbar)
    View viewToolbar;
    @BindView(R.id.materialup_title_container)
    LinearLayout linearLayout;
    @BindView(R.id.toolbar_4)
    Toolbar toolbar;
    @BindView(R.id.waveline)
    WaveView waveline;
    @BindView(R.id.main_collapsing)
    CollapsingToolbarLayout ctl;
    @BindView(R.id.materialup_appbar)
    AppBarLayout appbarLayout;
    @BindView(R.id.materialup_profile_image)
    CircleImageView mProfileImage;

    private LinearLayoutManager manager=new LinearLayoutManager(this);
    private CommonAdapter<String> adapter;
    private List<String> list=new ArrayList<>();

    private int mMaxScrollSize;   //最大滑动距离

    private String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1511864674658&di=6bf4899a8e0929e1470194db85a630c1&imgtype=0&src=http%3A%2F%2Fd.hiphotos.baidu.com%2Fimage%2Fpic%2Fitem%2Fb7003af33a87e950ee3dbe2e1b385343fbf2b44e.jpg";
    private Map<String, Target> map = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anima_title);
        ButterKnife.bind(this);
        //沉浸式状态
        StatusBarUtil.darkMode(this);

        ctl.setTitle("我的");
        ctl.setCollapsedTitleGravity(Gravity.CENTER);//上啦结束文字居中
        ctl.setCollapsedTitleTextColor(Color.WHITE);//上啦结束字体为白色
        ctl.setExpandedTitleGravity(Gravity.TOP | Gravity.LEFT);//位置坐上开始
        ctl.setExpandedTitleColor(getResources().getColor(R.color.transparent));//字体开始透明

        //设置bitmap会卡顿（耗资源）  设置drawable不会卡顿
//        imageView.setImageDrawable(getDrawable(R.drawable.timg));
        /**
         * 这里有个问题（第一次进入只执行onPrepareLoad方法  并不会执行图片加载成功的方法）
         * 解决方法是把 Target 通过强引用（比如类的属性或者Map之类）保存起来
         * */
        Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                Log.e("onBitmapLoaded", "onBitmapLoaded");
                //模糊处理
                Drawable drawable = Uitll.bitmap2Drawable(getResources(), Uitll.fastBlur(bitmap, 1, 22, true, getApplicationContext()));
                imageView.setBackground(drawable);
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {
                Log.e("onBitmapFailed", "onBitmapFailed");
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                Log.e("onBitmaponPrepareLoad", "onPrepareLoad");
            }
        };
        map.put("target", target);
        Picasso.with(this).load(url).resize(DpUtils.dpToPx(this, 500), DpUtils.dpToPx(this, 500)).into(map.get("target"));


        appbarLayout.addOnOffsetChangedListener(this);//设置滑动监听
        mMaxScrollSize = appbarLayout.getTotalScrollRange();//获取最大滑动距离


        for (int i = 0; i < 15; i++) {
            list.add("数据"+i);
        }

        adapter=new CommonAdapter<String>(list,R.layout.main_item) {
            @Override
            protected void bindViewItemData(ViewDataBinding binding, int position, String s) {
                MainItemBinding itemBinding= (MainItemBinding) binding;
                itemBinding.tvMainItem.setText(s);
            }
        };

        rvAnima.setLayoutManager(manager);
        rvAnima.setAdapter(adapter);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        if (mMaxScrollSize == 0)
            mMaxScrollSize = appBarLayout.getTotalScrollRange();

        //Math.abs  取绝对值  没有负数返回的是double型
        int percentage = (Math.abs(verticalOffset)) * 100 / mMaxScrollSize;

        //判断是否滑动大于25  并且头像显示（mIsAvatarShown）  隐藏头像
        if (percentage >= PERCENTAGE_TO_ANIMATE_AVATAR && mIsAvatarShown) {
            mIsAvatarShown = false;

            mProfileImage.animate()
                    .scaleY(0).scaleX(0)
                    .setDuration(500)
                    .start();
            linearLayout.animate().scaleX(0).scaleY(0).setDuration(300).start();
        }

        //相反显示头像
        if (percentage <= PERCENTAGE_TO_ANIMATE_AVATAR && !mIsAvatarShown) {
            mIsAvatarShown = true;

            mProfileImage.animate()
                    .scaleY(1).scaleX(1)
                    .start();

            linearLayout.animate().scaleX(1).scaleY(1).start();
        }
    }
}
