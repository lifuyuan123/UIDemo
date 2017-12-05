package com.example.administrator.uidemo.view.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.uidemo.bean.PieData;

/**
 * author:ggband
 * data:2017/12/1 000114:47
 * email:ggband520@163.com
 * desc:
 */

public class PathView extends View {

    private Paint paint;
    private Context context;

    public PathView(Context context) {
        super(context,null);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        init();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    private void init() {
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(10);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //path相关操作
        Path path=new Path();
        canvas.translate(getWidth()/2,getHeight()/2);
        path.lineTo(200,200);//从上一个位置到此点
        path.moveTo(200,100);//移动到下一次操作的起始点
        path.lineTo(200,0);

        path.setLastPoint(200,-100);//设置之前操作的最后一个点位置

//        path.close();//闭合


      /**
       * 第一类添加基本图形
       * Path.Direction dir   Path.Direction.CW  顺时针  Path.Direction.CCW逆时针
       * */
          //圆
//        addCircle (float x, float y, float radius, Path.Direction dir)
//        // 椭圆
//        addOval (RectF oval, Path.Direction dir)
//        // 矩形
//        addRect (float left, float top, float right, float bottom, Path.Direction dir)
//        addRect (RectF rect, Path.Direction dir)
//        // 圆角矩形
//        addRoundRect (RectF rect, float[] radii, Path.Direction dir)
//        addRoundRect (RectF rect, float rx, float ry, Path.Direction dir)

        /**
         * 第二类添加path
         * */
//        addPath (Path src)
//        addPath (Path src, float dx, float dy)
//        addPath (Path src, Matrix matrix)
//        这个相对比较简单，也很容易理解，就是将两个Path合并成为一个。
//        第三个方法是将src添加到当前path之前先使用Matrix进行变换。
//        第二个方法比第一个方法多出来的两个参数是将src进行了位移之后再添加进当前path中。

        /**
         * 第三类添加(addArc与arcTo)
         * */

        // addArc
//        addArc (RectF oval, float startAngle, float sweepAngle)
//        // arcTo
//        arcTo (RectF oval, float startAngle, float sweepAngle)
//        arcTo (RectF oval, float startAngle, float sweepAngle, boolean forceMoveTo)


        canvas.drawPath(path,paint);
    }
}
