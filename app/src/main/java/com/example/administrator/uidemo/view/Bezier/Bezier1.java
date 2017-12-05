package com.example.administrator.uidemo.view.Bezier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * author:ggband
 * data:2017/12/1 000115:46
 * email:ggband520@163.com
 * desc:
 */

public class Bezier1 extends View {

    private Context context;
    private int centerX,centerY;
    private Point startP,endP,controlP;
    private Paint paint;


    public Bezier1(Context context) {
        super(context,null);
    }

    public Bezier1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init();


    }

    private void init() {
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(20);
        startP=new Point(0,0);
        endP=new Point(0,0);
        controlP=new Point(0,0);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX=w/2;
        centerY=h/2;

        //初始化位置
        startP.set(centerX-200,centerY);
        endP.set(centerX+200,centerY);
        controlP.set(centerX,centerY-200);

    }

    //点击控制点
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.e("point",event.getX()+"   "+event.getY());
        controlP.set((int)event.getX(), (int)event.getY());
        invalidate();//改变了位置一定记住sh
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //画辅助线
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(5);
        canvas.drawLine(startP.x,startP.y,controlP.x,controlP.y,paint);
        canvas.drawLine(controlP.x,controlP.y,endP.x,endP.y,paint);


        Path path=new Path();

        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(8);
        path.moveTo(startP.x,startP.y);
        path.quadTo(controlP.x,controlP.y,endP.x,endP.y);//贝塞尔曲线
        canvas.drawPath(path,paint);

    }
}
