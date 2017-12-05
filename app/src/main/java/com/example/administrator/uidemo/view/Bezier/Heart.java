package com.example.administrator.uidemo.view.Bezier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * author:ggband
 * data:2017/12/4 000411:06
 * email:ggband520@163.com
 * desc:
 */

public class Heart extends View {
    private float mCircleRadius = 200;                  // 圆的半径
    private static final float C = 0.551915024494f;     // 一个常量，用来计算绘制圆形贝塞尔曲线控制点的位置
    private float mDifference = mCircleRadius*C;        // 圆形的控制点与数据点的差值

    private float[] mData=new float[8];//4个数据点
    private float[] mContol=new float[16];//8个控制点

    private float chageAllTime=1000;//变化总时长
    private float toNowTime=0;//当前进行时长
    private float count=100;//将总时长分为100份；
    private float itemTime=chageAllTime/count;//每份时长

    private int mWith,mHeight;

    private Paint paint;//画笔
    private Context context;




    public Heart(Context context) {
        super(context,null);
    }

    public Heart(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init();
    }

    private void init() {
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);
        paint.setTextSize(20);


        //数据点
        mData[0]=0;
        mData[1]=mCircleRadius;

        mData[2]=mCircleRadius;
        mData[3]=0;

        mData[4]=0;
        mData[5]=-mCircleRadius;

        mData[6]=-mCircleRadius;
        mData[7]=0;

        //控制点
        mContol[0]=mData[0]+mDifference;
        mContol[1]=mData[1];

        mContol[2]=mData[2];
        mContol[3]=mData[3]+mDifference;

        mContol[4]=mData[2];
        mContol[5]=mData[3]-mDifference;

        mContol[6]=mData[4]+mDifference;
        mContol[7]=mData[5];

        mContol[8]=mData[4]-mDifference;
        mContol[9]=mData[5];

        mContol[10]=mData[6];
        mContol[11]=mData[7]-mDifference;

        mContol[12]=mData[6];
        mContol[13]=mData[7]+mDifference;

        mContol[14]=mData[0]-mDifference;
        mContol[15]=mData[1];


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWith=w/2;
        mHeight=h/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画坐标系
        drawCoordinate(canvas);

        canvas.translate(mWith, mHeight); // 将坐标系移动到画布中央
        canvas.scale(1,-1);

        // 绘制辅助线和控制点
        drawGuide(canvas);

        //四段三阶赛贝尔
        drawSBR(canvas);

        toNowTime+=itemTime;//叠加当前时长
        if(toNowTime<chageAllTime){//判断当前所用时长是否小于总时长   1

            /**
             * 120是控制Y负方向的凹形尺寸   80是控制y正方向凸型尺寸  30是控制腰的位置   10是控制x方向位置
             * */
            mData[1]-=120/count;// mData[1]=mData[1]-12;
            mContol[7]+=80/count;//mContol[7]=mContol[7]+8;
            mContol[9]+=80/count;//mContol[9]=mContol[9]+8;

            mContol[4]-=30/count;//mContol[4]=mContol[4]-2;
            mContol[10]+=30/count;//mContol[10]=mContol[10]+2;


            mData[2]-=10/count;
            mData[6]+=10/count;

          postInvalidateDelayed((long)itemTime);//没隔一份时长刷新一次
        }
    }
    //四段三阶赛贝尔
    private void drawSBR(Canvas canvas) {
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);

        Path path=new Path();
        path.moveTo(mData[0],mData[1]);//移动的到起笔点
        //四段贝塞尔曲线
        path.cubicTo(mContol[0],mContol[1],mContol[2],mContol[3],mData[2],mData[3]);
        path.cubicTo(mContol[4],mContol[5],mContol[6],mContol[7],mData[4],mData[5]);
        path.cubicTo(mContol[8],mContol[9],mContol[10],mContol[11],mData[6],mData[7]);
        path.cubicTo(mContol[12],mContol[13],mContol[14],mContol[15],mData[0],mData[1]);
        canvas.drawPath(path,paint);

    }

    // 绘制辅助线和控制点
    private void drawGuide(Canvas canvas) {

        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(20);

        for (int i = 0; i <mData.length; i+=2) {//注意  这里是i+=2 一次取了两个值
            canvas.drawPoint(mData[i],mData[i+1],paint);
        }

        for (int i = 0; i < mContol.length; i+=2) {//注意  这里是i+=2 一次取了两个值
            canvas.drawPoint(mContol[i],mContol[i+1],paint);
        }

        // 绘制辅助线
        paint.setStrokeWidth(5);
        for (int i = 2,j=2;  i < mData.length; i+=2,j+=4) {
            canvas.drawLine(mData[i],mData[i+1],mContol[j],mContol[j+1],paint);//第一条
            canvas.drawLine(mData[i],mData[i+1],mContol[j+2],mContol[j+3],paint);//第二条
        }

        //还差两条  y轴负方向两条
            canvas.drawLine(mData[0],mData[1],mContol[0],mContol[1],paint);
            canvas.drawLine(mData[0],mData[1],mContol[14],mContol[15],paint);
    }


    //画坐标系
    private void drawCoordinate(Canvas canvas) {
        canvas.save();//保存状态

        canvas.translate(mWith, mHeight); // 将坐标系移动到画布中央
        canvas.scale(1,-1);                 // 翻转Y轴

        Paint coorPain=new Paint(Paint.ANTI_ALIAS_FLAG);
        coorPain.setColor(Color.BLACK);
        coorPain.setStyle(Paint.Style.FILL);
        coorPain.setTextSize(20);
        coorPain.setStrokeWidth(8);

        //画xy坐标
        canvas.drawLine(0,-2000,0,2000,coorPain);
        canvas.drawLine(-2000,0,2000,0,coorPain);
        canvas.restore();

    }
}
