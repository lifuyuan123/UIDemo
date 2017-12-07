package com.example.administrator.uidemo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.administrator.uidemo.R;
import com.flyco.roundview.RoundTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author:ggband
 * data:2017/12/7 000714:49
 * email:ggband520@163.com
 * desc:
 */

public class PopDialog extends Dialog  {


    @BindView(R.id.tv_camara)
    TextView tvCamara;
    @BindView(R.id.tv_picture)
    TextView tvPicture;
    @BindView(R.id.tv_cancel)
    RoundTextView tvCancel;

    public PopDialog(@NonNull Context context) {
        super(context, R.style.pop_list_dialog_style);
        setCancelable(false);//点击外部是否隐藏
    }

    public PopDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, R.style.pop_list_dialog_style);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pop_layout);
        ButterKnife.bind(this);
        init();

    }

    private void init() {
        Window dialogWindow = this.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.y = 20;
        dialogWindow.setAttributes(lp);

    }


    @OnClick({R.id.tv_camara, R.id.tv_picture, R.id.tv_cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_camara:
                if(callback!=null){
                    callback.camara();
                }
                break;
            case R.id.tv_picture:
                if(callback!=null){
                    callback.picture();
                }
                break;
            case R.id.tv_cancel:
                if(callback!=null){
                    callback.cancel();
                }
                break;
        }
    }


    public interface PopCallback {
        void camara();
        void picture();
        void cancel();
    }

    private PopCallback callback;

    public void setCallback(PopCallback callback) {
        this.callback = callback;
    }
}
