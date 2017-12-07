package com.example.administrator.uidemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.uidemo.dialog.CommonDialog;
import com.example.administrator.uidemo.dialog.PopDialog;
import com.example.administrator.uidemo.view.swipelayout.MyBaseActivity;
import com.jph.takephoto.app.TakePhoto;
import com.jph.takephoto.app.TakePhotoImpl;
import com.jph.takephoto.model.CropOptions;
import com.jph.takephoto.model.InvokeParam;
import com.jph.takephoto.model.TContextWrap;
import com.jph.takephoto.model.TResult;
import com.jph.takephoto.permission.InvokeListener;
import com.jph.takephoto.permission.PermissionManager;
import com.jph.takephoto.permission.TakePhotoInvocationHandler;
import com.squareup.picasso.Picasso;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 有两种方式实现选图裁剪
 * <p>
 * 方式一：通过继承的方式
 * <p>
 * 继承TakePhotoActivity、TakePhotoFragmentActivity、TakePhotoFragment三者之一。
 * 通过getTakePhoto()获取TakePhoto实例进行相关操作。
 * 重写以下方法获取结果
 * void takeSuccess(TResult result);
 * void takeFail(TResult result,String msg);
 * void takeCancel();
 * <p>
 * <p>
 * 方式二：通过组装的方式
 * 1.实现TakePhoto.TakeResultListener,InvokeListener接口。
 * <p>
 * 2.在 onCreate,onActivityResult,onSaveInstanceState方法中调用TakePhoto对用的方法。
 */

public class TakePhotoActivity extends MyBaseActivity implements TakePhoto.TakeResultListener, InvokeListener {

    @BindView(R.id.take_iv)
    ImageView takeIv;
    private TakePhoto takePhoto;
    private InvokeParam invokeParam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getTakePhoto().onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);
        ButterKnife.bind(this);
    }


    /**
     * Activity里的onSaveInstanceState()方法，虽然系统会自动调用它来保存Activity的一些数据，
     * 但当除它默认要保存的数据外，我们还要保存一些其他数据的时候，
     * 我们就需要覆盖onSaveInstanceState()方法来保存Activity的附件信息。
     * 例如在播放视频过程中，横竖屏切换要保持当前播放时间进度，在默认情况下播放时间是不被自动保存的。
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        getTakePhoto().onSaveInstanceState(outState);
        super.onSaveInstanceState(outState);
    }


    //图片保存路径
    public Uri getUri() {
        File file = new File(Environment.getExternalStorageDirectory(), "/uidemo/images/" + System.currentTimeMillis() + ".jpg");
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();

        Uri imageUri = Uri.fromFile(file);
        return imageUri;
    }


    //成功的回调
    @Override
    public void takeSuccess(TResult result) {

        Picasso.with(this).load(new File(result.getImage().getOriginalPath())).into(takeIv);

        //getOriginalPath() 原始图片路径
        //getCompressPath()压缩图片路径
        Log.e("take", "takeSuccess：" + result.getImage().getOriginalPath() + "    " + result.getImage().getCompressPath());
    }

    //失败的回调
    @Override
    public void takeFail(TResult result, String msg) {
        Log.e("take", "takeFail:" + msg);
    }

    //取消的回调
    @Override
    public void takeCancel() {
        Log.e("take", getResources().getString(R.string.msg_operation_canceled));
    }


    /**
     * 3.重写onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults)，添加如下代码。
     * 权限申请回调
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //以下代码为处理Android6.0、7.0动态权限所需
        PermissionManager.TPermissionType type = PermissionManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionManager.handlePermissionsResult(this, type, invokeParam, this);
    }

    /**
     * 4.重写TPermissionType invoke(InvokeParam invokeParam)方法，添加如下代码：
     * 申请权限
     */
    @Override
    public PermissionManager.TPermissionType invoke(InvokeParam invokeParam) {
        PermissionManager.TPermissionType type = PermissionManager.checkPermission(TContextWrap.of(this), invokeParam.getMethod());
        if (PermissionManager.TPermissionType.WAIT.equals(type)) {
            this.invokeParam = invokeParam;
        }
        return type;
    }

    /**
     * 5.添加如下代码获取TakePhoto实例：
     * 获取TakePhoto实例
     */
    public TakePhoto getTakePhoto() {
        if (takePhoto == null) {
            takePhoto = (TakePhoto) TakePhotoInvocationHandler.of(this).bind(new TakePhotoImpl(this, this));
        }
        return takePhoto;
    }

    /**
     * 6.activity回掉处理相关代码
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        getTakePhoto().onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick({R.id.take_bt,R.id.dialog_bt})
    public void onClick(View view) {
        switch (view.getId()) {
            //弹出提示框
            case R.id.dialog_bt:
                final CommonDialog dialog=new CommonDialog(TakePhotoActivity.this);
                //dialog.setCancleVisible(View.GONE);//设置取消按钮显示情况
                dialog.setTitle("提示");
                dialog.setContent("内容");
                dialog.setCancelClickListener("取消", new CommonDialog.CancelClickListener() {
                    @Override
                    public void clickCancel() {
                        Toast.makeText(TakePhotoActivity.this, "取消", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                dialog.setConfirmClickListener("确定", new CommonDialog.ConfirmClickListener() {
                    @Override
                    public void clickConfirm() {
                        Toast.makeText(TakePhotoActivity.this, "确定", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                dialog.show();
                break;
            //弹出选择照片
            case R.id.take_bt:
                final PopDialog popDialog = new PopDialog(TakePhotoActivity.this);
                popDialog.setCallback(new PopDialog.PopCallback() {
                    @Override
                    public void camara() {//照相机
                        popDialog.dismiss();

                        //裁剪参数  setWithOwnCrop(false)裁剪框是否固定大小
                        CropOptions cropOptions = new CropOptions.Builder().setWithOwnCrop(false).create();
                        getTakePhoto().onPickFromCaptureWithCrop(getUri(), cropOptions);
                    }

                    @Override
                    public void picture() {//选择照片
                        popDialog.dismiss();
                        //裁剪参数
                        CropOptions cropOptions1 = new CropOptions.Builder().setWithOwnCrop(false).create();

                        getTakePhoto().onPickFromGalleryWithCrop(getUri(), cropOptions1);
                    }

                    @Override
                    public void cancel() {
                        popDialog.dismiss();
                    }
                });
                popDialog.show();
                break;
        }
    }

}
