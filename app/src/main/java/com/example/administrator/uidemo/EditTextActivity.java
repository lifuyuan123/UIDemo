package com.example.administrator.uidemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.administrator.uidemo.view.swipelayout.MyBaseActivity;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rengwuxian.materialedittext.validation.RegexpValidator;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EditTextActivity extends MyBaseActivity {

    @BindView(R.id.edit)
    MaterialEditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);
        ButterKnife.bind(this);
        edit.setError("错误");
        edit.isValid("\\d+");  //正则表达式
        edit.validate("\\d+", "Only Integer Valid!");//带错误提示的正则表达式


        //单一条件检查
        edit.validateWith(new RegexpValidator("Only Integer Valid!", "\\d+"));

        //复合条件检查
//        edit.addValidator(new CustomValidator1())
//                .addValidator(new CustomValidator2())
//                .addValidator(new RegexpValidator("Only Integer Valid!", "\\d+"));

    }
}
