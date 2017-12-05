package com.example.administrator.uidemo.view.Bezier;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.Transformation;


/**
 * author:ggband
 * data:2017/12/4 000414:13
 * email:ggband520@163.com
 * desc:
 */

public class Pellet extends View {
    private float mRadius=100;//圆半径
    private static final float C = 0.551915024494f;     // 一个常量，用来计算绘制圆形贝塞尔曲线控制点的位置
    private float mDifference = mRadius*C;        // 圆形的控制点与数据点的差值

    private Context context;//上下文对象
    private Paint paint;//画笔

    private float[] mData=new float[8];//数据点
    private float[] mContol=new float[16];//控制点



    private int mWith,mHight;//宽高

    private float changeAllTime=100;//总时间
    private float count =100;//100份
    private float itemTime=changeAllTime/count;//每一份时长
    private float toNowTime=0;//当前时长
    private float percentage=0;//当前所占时长百分比




    public Pellet(Context context) {
        super(context,null);
    }

    public Pellet(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;

        init();//初始化
    }

    //初始化
    private void init() {
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(20);

        //数据点
        mData[0]=0;
        mData[1]=mRadius;

        mData[2]=mRadius;
        mData[3]=0;

        mData[4]=0;
        mData[5]=-mRadius;

        mData[6]=-mRadius;
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
        mWith=(int) mRadius*2;
        mHight=h/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //移到中心位置
        canvas.translate(mWith,mHight);
        //绘制四段贝塞尔
        drawBSR(canvas);


          toNowTime+=itemTime;
          percentage=toNowTime/changeAllTime;

        if(toNowTime<changeAllTime){


            //状态一   右点向右移动
            if(percentage>=0&&percentage<=0.21){
                mData[2]+=100/20;
                mContol[2]+=100/20;
                mContol[4]+=100/20;

                Log.e("percentage状态一","左x:"+mData[6]+ "右x:"+mData[2]+"   直径："+(mData[2]-mData[6])+"    次数："+percentage);
            }
            //状态二
            if(percentage>0.21&&percentage<=0.5){

                //状态一Y向控制点增加10
                mContol[3]-=10/30;
                mContol[5]+=10/30;


                //向右移动100
                mData[0]+=100/30;
                mData[2]+=100/30;
                mData[4]+=100/30;
                mContol[0]+=100/30;
                mContol[2]+=100/30;
                mContol[4]+=100/30;
                mContol[6]+=100/30;
                mContol[8]+=100/30;
                mContol[14]+=100/30;

//                mData[6]-=100/30;
//                mContol[10]-=100/30;
//                mContol[12]-=100/30;

                //状态二Y向控制点增加10
                mContol[11]+=10/30;
                mContol[13]-=10/30;

                Log.e("percentage状态二","左x:"+mData[6]+ "右x:"+mData[2]+"   直径："+(mData[2]-mData[6])+"    次数："+percentage);
            }
            //状态三
            if(percentage>=0.5&&percentage<=0.8){
//                mData[2]-=100/30;
//                mContol[2]-=100/30;
//                mContol[4]-=100/30;

                //向右移动100
                mData[0]+=100/30;
                mData[4]+=100/30;
                mData[6]+=100/30;
                mContol[0]+=100/30;
                mContol[6]+=100/30;
                mContol[8]+=100/30;
                mContol[10]+=100/30;
                mContol[12]+=100/30;
                mContol[14]+=100/30;


                //状态一Y向控制退回10
                mContol[3]+=10/30;
                mContol[5]-=10/30;

                //状态二Y向控制点退回10
                mContol[11]-=10/30;
                mContol[13]+=10/30;
                Log.e("percentage状态三","左x:"+mData[6]+ "右x:"+mData[2]+"   直径："+(mData[2]-mData[6])+"    次数："+percentage);
            }
            //状态四
            if(percentage>0.8&&percentage<=0.89){
                mData[6]+=100/10;
                mContol[10]+=100/10;
                mContol[12]+=100/10;
                Log.e("percentage状态四","左x:"+mData[6]+ "右x:"+mData[2]+"   直径："+(mData[2]-mData[6])+"    次数："+percentage);
            }
            //状态五
            if(percentage>0.89&&percentage<=1){
                mData[6]-=15/10;
                mContol[10]-=15/10;
                mContol[12]-=15/10;

                mData[2]-=19/10;
                mContol[2]-=19/10;
                mContol[4]-=19/10;

                Log.e("percentage状态五","左x:"+mData[6]+ "右x:"+mData[2]+"   直径："+(mData[2]-mData[6])+"    次数："+percentage);
            }

        postInvalidateDelayed((long)itemTime);//没隔一份时长刷新一次

        }

    }


    //绘制四段贝塞尔
    private void drawBSR(Canvas canvas) {
        Path path=new Path();
        path.moveTo(mData[0],mData[1]);
        path.cubicTo(mContol[0],mContol[1],mContol[2],mContol[3],mData[2],mData[3]);
        path.cubicTo(mContol[4],mContol[5],mContol[6],mContol[7],mData[4],mData[5]);
        path.cubicTo(mContol[8],mContol[9],mContol[10],mContol[11],mData[6],mData[7]);
        path.cubicTo(mContol[12],mContol[13],mContol[14],mContol[15],mData[0],mData[1]);
        canvas.drawPath(path,paint);
    }



    //右移动动画效果
    public void startAnimation() {
        ObjectAnimator translationUp = ObjectAnimator.ofFloat(this, "X",
                this.getX(), 400);
        translationUp.setInterpolator(new AccelerateDecelerateInterpolator());
        translationUp.setDuration(2000);
        translationUp.start();
    }
}
