package com.example.administrator.uidemo.view.Bezier;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * author:ggband
 * data:2017/12/4 000410:03
 * email:ggband520@163.com
 * desc:
 */

public class Bezier2 extends View {

    private Point starOne,endOne,contolOne,contolTwo;

    public boolean ismode() {
        return ismode;
    }

    public void setIsmode(boolean ismode) {
        this.ismode = ismode;
    }

    private boolean ismode=true;
    private Context context;
    private Paint paint;
    private int centerX,centerY;


    public Bezier2(Context context) {
        super(context,null);
    }

    public Bezier2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();//初始化画笔相关和点
    }

    private void init() {
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(5);

        starOne=new Point(0,0);
        endOne=new Point(0,0);
        contolOne=new Point(0,0);
        contolTwo=new Point(0,0);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX=w/2;
        centerY=h/2;

        starOne.set(centerX-200,centerY);
        endOne.set(centerX+200,centerY);
        contolOne.set(centerX-80,centerY+100);
        contolTwo.set(centerX+100,centerY+150);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //第一控制点
        if(ismode){
            contolOne.set((int)event.getX(),(int)event.getY());
        //第二控制点
        }else {
            contolTwo.set((int)event.getX(),(int)event.getY());
        }
        invalidate();//刷新
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //辅助线
        paint.setColor(Color.GRAY);
        paint.setStrokeWidth(5);
        canvas.drawLine(starOne.x,starOne.y,contolOne.x,contolOne.y,paint);
        canvas.drawLine(contolOne.x,contolOne.y,contolTwo.x,contolTwo.y,paint);
        canvas.drawLine(contolTwo.x,contolTwo.y,endOne.x,endOne.y,paint);

        Path path=new Path();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(8);
        path.moveTo(starOne.x,starOne.y);
        path.cubicTo(contolOne.x,contolOne.y,contolTwo.x,contolTwo.y,endOne.x,endOne.y);
        canvas.drawPath(path,paint);
    }
}
