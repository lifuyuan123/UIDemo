package com.example.administrator.uidemo.view.Bezier;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
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

    private  float value=0;//当前状态
    private  int oldValue=0;//上一次value

    private Context context;//上下文对象
    private Paint paint;//画笔

    private float[] mData=new float[8];//数据点
    private float[] mContol=new float[16];//控制点
    private Path path;



    private int mWith,mHight;//宽高

    private float changeAllTime=100;//总时间
    private float count =100;//100份
    private float itemTime=changeAllTime/count;//每一份时长
    private float toNowTime=0;//当前时长
    private float percentage=0;//当前所占时长百分比

    private int a=1,b=1,c=1,d=1,e=1,f=0;
    private int type=-1;




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
        path=new Path();
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(20);



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

        path.reset();
        //移到中心位置
        canvas.translate(mWith,mHight);
        //绘制四段贝塞尔
        drawBSR(canvas);


          toNowTime+=itemTime;
          percentage=toNowTime/changeAllTime;

//        if(toNowTime<changeAllTime){
//
//
//            //状态一   右点向右移动
//            if(percentage>=0&&percentage<=0.21){
//                mData[2]+=100/20;
//                mContol[2]+=100/20;
//                mContol[4]+=100/20;
//
//                Log.e("percentage状态一","左x:"+mData[6]+ "右x:"+mData[2]+"   直径："+(mData[2]-mData[6])+"    次数："+percentage);
//            }
//            //状态二
//            if(percentage>0.21&&percentage<=0.5){
//
//                //状态一Y向控制点增加10
//                mContol[3]-=10/30;
//                mContol[5]+=10/30;
//
//
//                //向右移动100
//                mData[0]+=100/30;
//                mData[2]+=100/30;
//                mData[4]+=100/30;
//                mContol[0]+=100/30;
//                mContol[2]+=100/30;
//                mContol[4]+=100/30;
//                mContol[6]+=100/30;
//                mContol[8]+=100/30;
//                mContol[14]+=100/30;
//
////                mData[6]-=100/30;
////                mContol[10]-=100/30;
////                mContol[12]-=100/30;
//
//                //状态二Y向控制点增加10
//                mContol[11]+=10/30;
//                mContol[13]-=10/30;
//
//                Log.e("percentage状态二","左x:"+mData[6]+ "右x:"+mData[2]+"   直径："+(mData[2]-mData[6])+"    次数："+percentage);
//            }
//            //状态三
//            if(percentage>=0.5&&percentage<=0.8){
////                mData[2]-=100/30;
////                mContol[2]-=100/30;
////                mContol[4]-=100/30;
//
//                //向右移动100
//                mData[0]+=100/30;
//                mData[4]+=100/30;
//                mData[6]+=100/30;
//                mContol[0]+=100/30;
//                mContol[6]+=100/30;
//                mContol[8]+=100/30;
//                mContol[10]+=100/30;
//                mContol[12]+=100/30;
//                mContol[14]+=100/30;
//
//
//                //状态一Y向控制退回10
//                mContol[3]+=10/30;
//                mContol[5]-=10/30;
//
//                //状态二Y向控制点退回10
//                mContol[11]-=10/30;
//                mContol[13]+=10/30;
//                Log.e("percentage状态三","左x:"+mData[6]+ "右x:"+mData[2]+"   直径："+(mData[2]-mData[6])+"    次数："+percentage);
//            }
//            //状态四
//            if(percentage>0.8&&percentage<=0.89){
//                mData[6]+=100/10;
//                mContol[10]+=100/10;
//                mContol[12]+=100/10;
//                Log.e("percentage状态四","左x:"+mData[6]+ "右x:"+mData[2]+"   直径："+(mData[2]-mData[6])+"    次数："+percentage);
//            }
//            //状态五
//            if(percentage>0.89&&percentage<=1){
//                mData[6]-=15/10;
//                mContol[10]-=15/10;
//                mContol[12]-=15/10;
//
//                mData[2]-=19/10;
//                mContol[2]-=19/10;
//                mContol[4]-=19/10;
//
//                Log.e("percentage状态五","左x:"+mData[6]+ "右x:"+mData[2]+"   直径："+(mData[2]-mData[6])+"    次数："+percentage);
//            }
//
//        postInvalidateDelayed((long)itemTime);//没隔一份时长刷新一次
//
//        }


            //状态一
            if(percentage>=0&&percentage<=0.2){
                mode1(percentage);
            }

            if(percentage>0.2&&percentage<=0.5){
                mode2(percentage);
            }

           if(percentage>0.5&&percentage<=0.8){
              mode3(percentage);
           }

           if(percentage>0.8&&percentage<=0.9){
              mode4(percentage);
           }

           if(percentage>0.9&&percentage<=1){
               mode5(percentage);
           }
        invalidate();//刷新
        }



    private void mode5(float percentage) {
        mode4(0.9f);
        float value= (percentage -0.9f)*10;
        mData[6]-=20*value;
        mContol[10]-=20*value;
        mContol[12]-=20*value;

        Log.e("value状态五",e+++"");
    }

    private void mode4(float percentage) {
        mode3(0.8f);
        float value= (percentage -0.8f)*10;
        mData[6]+=120*value;
        mContol[10]+=120*value;
        mContol[12]+=120*value;

        Log.e("value状态四",d+++"");
    }

    private void mode3(float percentage) {
        mode2(0.5f);
        float value= (percentage -0.5f)*(10f/3);
        //向右移动100
        mData[0]+=100*value;
        mData[4]+=100*value;
        mData[6]+=100*value;
        mContol[0]+=100*value;
        mContol[6]+=100*value;
        mContol[8]+=100*value;
        mContol[10]+=100*value;
        mContol[12]+=100*value;
        mContol[14]+=100*value;

        //状态一Y向控制退回10
        mContol[3]+=10*value;
        mContol[5]-=10*value;

        //状态二Y向控制点退回10
        mContol[11]-=10*value;
        mContol[13]+=10*value;

        Log.e("value状态三",c+++"");
    }

    private void mode2(float percentage) {
        mode1(0.2f);
        float value= (percentage -0.2f)*(10f/3);
        //状态一Y向控制点增加10
        mContol[3]-=10*value;
        mContol[5]+=10*value;

        //向右移动100
        mData[0]+=100*value;
        mData[2]+=100*value;
        mData[4]+=100*value;
        mContol[0]+=100*value;
        mContol[2]+=100*value;
        mContol[4]+=100*value;
        mContol[6]+=100*value;
        mContol[8]+=100*value;
        mContol[14]+=100*value;

        //状态二Y向控制点增加10
        mContol[11]+=10*value;
        mContol[13]-=10*value;

        Log.e("value状态二",b+++"");
    }

    private void mode1(float percentage) {
        mode0();

        float value=percentage*5;
        mData[2]+=100*value;
        mContol[2]+=100*value;
        mContol[4]+=100*value;
        Log.e("value状态一",a+++"");
    }

    private void mode0() {
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


    //绘制四段贝塞尔
    private void drawBSR(Canvas canvas) {

        path.moveTo(mData[0],mData[1]);
        path.cubicTo(mContol[0],mContol[1],mContol[2],mContol[3],mData[2],mData[3]);
        path.cubicTo(mContol[4],mContol[5],mContol[6],mContol[7],mData[4],mData[5]);
        path.cubicTo(mContol[8],mContol[9],mContol[10],mContol[11],mData[6],mData[7]);
        path.cubicTo(mContol[12],mContol[13],mContol[14],mContol[15],mData[0],mData[1]);
        canvas.drawPath(path,paint);
    }


    //右移动动画效果
    public void startAnimation() {
        path.reset();

        ObjectAnimator animator1 = ObjectAnimator.ofFloat(this, "translationX", 400f);
        animator1.setDuration(1000);

        ValueAnimator valueAnimator=ValueAnimator.ofFloat(0,1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                percentage= (float) valueAnimator.getAnimatedValue();
                Log.e("value",value+""+"    percentage"+percentage);

            }
        });
        valueAnimator.setDuration(1000);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
//         valueAnimator.start();


        AnimatorSet set=new AnimatorSet();
        set.playTogether(animator1,valueAnimator);
        set.setDuration(1000);
        set.start();
    }
}
