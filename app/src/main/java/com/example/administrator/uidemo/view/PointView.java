package com.example.administrator.uidemo.view;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.PointFEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.example.administrator.uidemo.R;

/**
 * author:ggband
 * data:2017/12/5 000516:08
 * email:ggband520@163.com
 * desc:
 */

public class PointView extends View {
    private Context context;
    private Paint paint;
    private float radius=100;

    private int []mBgColors ;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        invalidate();//刷新
    }

    private int color=Color.RED;

    PointF point;

    public PointView(Context context) {
        super(context,null);
    }

    public PointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init();

    }



    private void init() {
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);

        mBgColors = new int[]{
                ContextCompat.getColor(getContext(), R.color.color_red),
                ContextCompat.getColor(getContext(), R.color.color_orange),
                ContextCompat.getColor(getContext(), R.color.color_yellow),
                ContextCompat.getColor(getContext(), R.color.color_green),
                ContextCompat.getColor(getContext(), R.color.color_blue)};
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(color);
        if(point==null){
            point=new PointF(getWidth()/2,radius);
            canvas.drawCircle(point.x,point.y,radius,paint);
            startAnima();
        }else {
            canvas.drawCircle(point.x,point.y,radius,paint);
        }

    }

    private void startAnima() {

        PointF start=new PointF(getWidth()/2,radius);
        PointF end=new PointF(getWidth()/2,getHeight()-radius);

        ValueAnimator valueAnimator=ValueAnimator.ofObject(new PointFEvaluator(),start,end);
        //监听动画的值
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                point=(PointF)valueAnimator.getAnimatedValue();
                invalidate();//刷新
            }
        });
        valueAnimator.setDuration(3000);
        valueAnimator.setInterpolator(new BounceInterpolator());//反弹数次后停止
//        valueAnimator.start();

        ObjectAnimator colorValue=ObjectAnimator.ofInt(this,"color",mBgColors[0],mBgColors[mBgColors.length-1]);
        colorValue.setEvaluator(new ArgbEvaluator());//TypeEvaluator的作用到底是什么呢？简单来说，就是告诉动画系统如何从初始值过度到结束值
        colorValue.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                color= (int) valueAnimator.getAnimatedValue();
                invalidate();//刷新
            }
        });
        AnimatorSet animatorSet=new AnimatorSet();
        animatorSet.playTogether(colorValue,valueAnimator);
        animatorSet.setDuration(3000);
        animatorSet.start();



    }
}
