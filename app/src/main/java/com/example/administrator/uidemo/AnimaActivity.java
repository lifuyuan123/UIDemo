package com.example.administrator.uidemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AnimaActivity extends AppCompatActivity {

    @BindView(R.id.iv_anima)
    ImageView ivAnima;
    @BindView(R.id.bt1)
    Button bt1;
    @BindView(R.id.iv_anima2)
    ImageView ivAnima2;
    @BindView(R.id.iv_anima3)
    ImageView ivAnima3;


    /**
     * ObjectAnimator、PropertyValuesHolder、ValueAnimator、AnimatorSet
     * <p>
     * 使用ObjectAnimator进行更加精细的控制，只控制一个对象的一个属性值，尔斯用多个ObjectAnimator组合到AnimatorSet形成一个动画
     * <p>
     * 常用的属性值
     * tanslationX、translationY：view对象从他布局容器的左上角坐标偏移的位置
     * 　rotation、rotationX、rotationY：控制view围绕支点进行2D、3D的旋转
     * 　scaleX、scaleY：控制view对象围绕支点进行2D缩放
     * 　pivotX、pivotY：view对象的支点位置，围绕这个支点旋转缩放，默认为view对象的中心店
     * 　x、y：view在容器中的位置
     * 　alpha：view的透明度
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anima);
        ButterKnife.bind(this);

        //平移    动画对象  动画属性   可变数组（从0移动到500）  注意ofFloat  ofInt  单位问题
        ObjectAnimator animator = ObjectAnimator.ofFloat(bt1, "translationX", 0, 500);
        animator.setDuration(1000);//动画时间
        animator.start();


        //PropertyValuesHolder针对一个对象的多个属性进行操控
        PropertyValuesHolder pvh1 = PropertyValuesHolder.ofFloat("scaleX", 1f, 0, 1f);
        PropertyValuesHolder pvh2 = PropertyValuesHolder.ofFloat("scaleY", 1f, 0, 1f);
        PropertyValuesHolder pvh3 = PropertyValuesHolder.ofFloat("translationX", 0, 500);
        ObjectAnimator.ofPropertyValuesHolder(ivAnima, pvh1, pvh2, pvh3).setDuration(3000).start();

        //ValueAnimator本身不提供任何动画效果，它更像一个数值发生器，
        // 用来产生一定规律的数字，从而让调动着来控制动画的实践过程

        //setStartDelay()方法来设置动画延迟播放的时间，调用setRepeatCount()和setRepeatMode()
        // 方法来设置动画循环播放的次数以及循环播放的模式，
        // 循环模式包括RESTART和REVERSE两种，分别表示重新播放和倒序播放的意思

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 100);
        valueAnimator.setTarget(bt1);
        valueAnimator.setDuration(1000).start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float value = (Float) animation.getAnimatedValue();
                Log.e("value", value + "");
            }
        });

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(2000);
        animatorSet.playTogether(valueAnimator, animator);//playTogether同时播放
        animatorSet.start();

        //动画监听
        animatorSet.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                Log.e("valueanima", "结束");
            }

            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                Log.e("valueanima", "开始");
            }
        });


        /**
         * 在属性动画中AnimatorSet正是通过
         * playTogether()  同时
         * 、playSequentially()  顺序
         * 、animSet.play().with()  和某个动画一起
         * 、before()   之前
         * 、after()    之后
         * 这些方法来控制多个动画协同工作，从而做到对动画播放顺序的精确控制。
         *
         * set.setInterpolator(TimeInterpolator interpolator);设置一个效果
         *
         * */
        //AnimatorSet 对于一个属性同时作用多个属性动画效果，可以用PropertyValuesHolder实现，
        // 但是用AnimatorSet不仅能实现这样的效果还可以更加精确的顺序控制
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(ivAnima2, "translationX", 500f);
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(ivAnima2, "scaleX", 1f, 0f, 1f);
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(ivAnima2, "scaleY", 1f, 0f, 1f);
        AnimatorSet set = new AnimatorSet();
        set.setDuration(2000);
        set.playSequentially(animator1, animator2, animator3);
        set.start();


        ObjectAnimator animator4 = ObjectAnimator.ofFloat(ivAnima3, "translationX", 500f);
        AnimatorSet set2 = new AnimatorSet();
        set2.setDuration(2000);
        set2.setInterpolator(new AccelerateDecelerateInterpolator());
        set2.playSequentially(animator4);
        set2.start();

    }

    @OnClick(R.id.bt)
    public void onClick() {
        startActivity(new Intent(AnimaActivity.this,FreeFallActivity.class));
    }
}
