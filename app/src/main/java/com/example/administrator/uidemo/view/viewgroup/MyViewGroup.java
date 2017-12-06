package com.example.administrator.uidemo.view.viewgroup;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * author:ggband
 * data:2017/12/6 00069:38
 * email:ggband520@163.com
 * desc:
 */

public class MyViewGroup extends ViewGroup {


    private Context context;

    public MyViewGroup(Context context) {
        super(context,null);
    }

    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;

    }



    //确定view大小
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //自定义ViewGroup来适应margin的情况
        MarginLayoutParams params = null;

        //获取宽高测量方式
        int withMode=MeasureSpec.getMode(widthMeasureSpec);
        int hightMode=MeasureSpec.getMode(heightMeasureSpec);

        //获取宽高尺寸
        int withSize=MeasureSpec.getSize(widthMeasureSpec);
        int hightSize=MeasureSpec.getSize(heightMeasureSpec);

        //只要子控件不是wrap_content都会测量精准
        measureChildren(widthMeasureSpec,heightMeasureSpec);

        //开始处理wrap_content

        //如果一个子元素都没有，就设置为0
        if(getChildCount()<=0){
            setMeasuredDimension(0,0);//设置父布局宽高
        }
        //ViewGroup，宽，高都是wrap_content，根据我们的需求，宽度是子控件的宽度，高度则是所有子控件的总和
        if(withMode==MeasureSpec.AT_MOST&&hightMode==MeasureSpec.AT_MOST){
            View view;
            int with=0;
            int hight=0;
            for (int i = 0; i < getChildCount(); i++) {
                view=getChildAt(i);
                params = (MarginLayoutParams) view.getLayoutParams();
                //得出子view中宽度最大的值作为最后的父布局宽度，高度叠加
                if(view.getMeasuredWidth()+params.leftMargin+params.rightMargin>with){
                    with=view.getMeasuredWidth()+params.leftMargin+params.rightMargin;
                }
                hight+=view.getMeasuredHeight()+params.bottomMargin+params.topMargin;
            }
            setMeasuredDimension(with,hight);
        }
        //宽是wrap_content
        if(withMode==MeasureSpec.AT_MOST){
            View view;
            int with=0;
            for (int i = 0; i < getChildCount(); i++) {
                view=getChildAt(i);
                params = (MarginLayoutParams) view.getLayoutParams();
                if(view.getMeasuredWidth()+params.leftMargin+params.rightMargin>with){
                    with=view.getMeasuredWidth()+params.leftMargin+params.rightMargin;
                }
            }
            setMeasuredDimension(with,hightSize);

        }

        //高是wrap_content
        if(hightMode==MeasureSpec.AT_MOST){
            View view;
            int hight=0;
            for (int i = 0; i < getChildCount(); i++) {
                view=getChildAt(i);
                params = (MarginLayoutParams) view.getLayoutParams();
                hight+=view.getMeasuredHeight()+params.bottomMargin+params.topMargin;
            }
            setMeasuredDimension(withSize,hight);
        }

    }

    //确定view位置
    @Override
    protected void onLayout(boolean b, int left, int top, int right, int bottom) {

        int hight=0;
        int count =getChildCount();
        View view;
        for (int j = 0; j < count; j++) {
            view=getChildAt(j);
            MarginLayoutParams params = (MarginLayoutParams) view.getLayoutParams();//用于计算view的margin
            //这里需要注意  layout的四个参数定义   第三个参数不应加入params.RighMargin  第四个参数不应加入params.bottomMargin  不然空间会增大相应margin
            view.layout(params.leftMargin,hight+params.topMargin,view.getMeasuredWidth()+params.leftMargin,hight+view.getMeasuredHeight()+params.topMargin);
            hight+=view.getMeasuredHeight()+params.bottomMargin+params.topMargin;
        }

    }



    //注意这个语句params = (MarginLayoutParams) childOne.getLayoutParams(); 如果不重写layoutParams相关的代码，
    // 这样直接转换会出现问题。所以，我们需要重写如下代码：让他返回MarginLayoutParams类型的对象
    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
    }
}
