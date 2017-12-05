package com.example.administrator.uidemo.bean;

/**
 * author:ggband
 * data:2017/12/1 000113:18
 * email:ggband520@163.com
 * desc:
 */

public class PieData {

    private float value;//数值
    private float percentage;//百分比
    private float angle;//角度
    private int color;//颜色

    public PieData(float value) {
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "PieData{" +
                "value=" + value +
                ", percentage=" + percentage +
                ", angle=" + angle +
                ", color=" + color +
                '}';
    }
}
