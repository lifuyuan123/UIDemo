package com.example.administrator.uidemo.view.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * author:ggband
 * data:2017/12/1 000111:12
 * email:ggband520@163.com
 * desc:基本操作
 */

public class CustomView extends View {

    private Paint paint;//画笔
    private Context context;
    private int radiu;//半径

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        initPaint();//初始化画笔
    }

    //初始化画笔
    private void initPaint() {
        // 实例化画笔并打开抗锯齿  Paint.ANTI_ALIAS_FLAG
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);

        /*
     * 设置画笔样式为描边，圆环嘛……当然不能填充不然就么意思了
     *
     * 画笔样式分三种：
     * 1.Paint.Style.STROKE：描边
     * 2.Paint.Style.FILL_AND_STROKE：描边并填充
     * 3.Paint.Style.FILL：填充
     */
        paint.setStyle(Paint.Style.STROKE);

        // 设置画笔颜色为浅灰色
        paint.setColor(Color.BLACK);

    /*
     * 设置描边的粗细，单位：像素px
     * 注意：当setStrokeWidth(0)的时候描边宽度并不为0而是只占一个像素
     */
        paint.setStrokeWidth(10);
    }


    //画图相关操作
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画点  坐标（getWidth()/2,getHeight()/2）
        canvas.drawPoint(200,200,paint);

        //画直线  点（100，100，）到点（200，200）
        canvas.drawLine(100,100,200,100,paint);

        //画矩形
        Rect rect=new Rect(300,300,500,500);//int型
        RectF rectF=new RectF(300,100,600,300);//float型
        canvas.drawRect(rectF,paint);

        //画圆角(rx,ry大于宽高的一半就是椭圆)
        RectF rectF1=new RectF(700,100,1000,300);
        canvas.drawRoundRect(rectF1,30,30,paint);//椭圆的两个半径  rx30,ry30  rx大于宽度的一半，ry大于高度的一半时 参数均按照一半来处理

        //绘制椭圆
        RectF rectF2=new RectF(100,400,600,600);
        canvas.drawOval(rectF2,paint);

        //绘制圆
        canvas.drawCircle(800,500,50,paint);  // 绘制一个圆心坐标在(500,500)，半径为400 的圆。

        //绘制圆弧
        // 开始角度 startAngle
        // 扫过角度sweepAngle
        // 是否使用中心useCenter
        RectF rectF3=new RectF(100,700,500,900);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
        canvas.drawRect(rectF3,paint);//绘制矩形
        paint.setColor(Color.BLACK);
        canvas.drawArc(rectF3,0,90,true,paint);//boolean类型是否使用中心（不使用中心就是一段切弧）







    }
}
