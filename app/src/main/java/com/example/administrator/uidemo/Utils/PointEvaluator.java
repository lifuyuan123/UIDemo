package com.example.administrator.uidemo.Utils;

import android.animation.TypeEvaluator;
import android.graphics.Point;

/**
 * author:ggband
 * data:2017/12/5 000515:55
 * email:ggband520@163.com
 * desc:
 */
//告诉动画系统如何从初始值过度到结束值
class FloatEvaluator implements TypeEvaluator {

/**
 * 第一个参数fraction非常重要，这个参数用于表示动画的完成度的，
 * 我们应该根据它来计算当前动画的值应该是多少，第二第三个参数分别表示动画的初始值和结束值。
 * 那么上述代码的逻辑就比较清晰了，用结束值减去初始值，算出它们之间的差值，然后乘以fraction这个系数，
 * 再加上初始值，那么就得到当前动画的值了
 * */

//返回一个值
//    @Override
//    public Object evaluate(float fraction, Object startValue, Object endValue) {
//        float start=((Number)startValue).floatValue();
//        float end=((Number)endValue).floatValue();
//        return start+(end-start)*fraction;
//    }


//返回一个点
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        Point start= (Point) startValue;
        Point end= (Point) endValue;
        float x=start.x+(end.x-start.x)*fraction;
        float y=start.y+(end.y-start.y)*fraction;
        Point point=new Point((int)x,(int)y);
        return point;
    }
}
