package com.example.administrator.uidemo.view.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.administrator.uidemo.bean.PieData;
import com.example.administrator.uidemo.view.LauncherView;

import java.util.List;

/**
 * author:ggband
 * data:2017/12/1 000113:15
 * email:ggband520@163.com
 * desc:饼状图（扇形）
 */

public class PieView extends View {

    private Paint paint;
    private Context context;
    private int[] color={0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};

    private int wight,hight;//宽高

    private float mStartAngle;//初始角度
    private List<PieData> pieDatas;//数据源


    public PieView(Context context) {
        super(context,null);
    }

    public PieView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);//抗锯齿
        paint.setStyle(Paint.Style.FILL);//填充
    }

    //最终宽高
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        wight=w;
        hight=h;
    }

    //画图
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(pieDatas==null||pieDatas.size()==0)
            return;

        canvas.translate(wight/2,hight/2);
        float startAngle=mStartAngle;
        float r= (float) ((Math.min(wight,hight)/2)*0.8);
        RectF rectF=new RectF(-r,-r,r,r);
        for (int i = 0; i < pieDatas.size(); i++) {
            PieData pieData=pieDatas.get(i);
            paint.setColor(pieData.getColor());
            canvas.drawArc(rectF,startAngle,pieData.getAngle(),true,paint);//画圆弧
            startAngle+=pieData.getAngle();

        }

    }


    /**
     * 设置数据都需要刷新试图   调用 invalidate()方法刷新
     * */

    //外界设置数据源
    public void setPieDatas(List<PieData> pieDatas) {
        this.pieDatas = pieDatas;
        initData(pieDatas);//初始化外界获取的数据
        invalidate();//刷新
    }

    //外界设置启始角度
    public void setmStartAngle(float mStartAngle) {
        this.mStartAngle = mStartAngle;
        invalidate();//刷新
    }


    //初始化外界获取的数据
    private void initData(List<PieData> pieDatas) {
         if(pieDatas==null||pieDatas.size()==1)
             return;

        //第一个for循环获取总数值
        float allvalue=0;//总数值
        for (int i = 0; i < pieDatas.size(); i++) {
            PieData pieData=pieDatas.get(i);
            allvalue+=pieData.getValue();
            pieData.setColor(color[i]);
        }
       //第二个for循环通过总数值获取百分比和所占角度
        float allangle=0;//总角度
        for (int i = 0; i < pieDatas.size(); i++) {
            PieData pieData=pieDatas.get(i);
            float percentage=pieData.getValue()/allvalue;//百分比
            float angle=percentage*360;// 对应的角度
            pieData.setPercentage(percentage);
            pieData.setAngle(angle);
            allangle+=pieData.getAngle();

            Log.e("angle", "" + pieData.getAngle()+"       allangle"+allangle);
        }

    }
}
