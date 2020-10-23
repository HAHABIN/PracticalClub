package com.example.common.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.example.common.R;


/**
 * <底部弹窗-相册> <功能详细描述>
 *
 * @author HABIN
 * @version 2020/6/15
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class DialogPhotoView extends Dialog {



    /** 拍照*/
    private LinearLayout mLlPhotoGraph;
    /** 相册*/
    private LinearLayout mLlPhotoAlbum;
    /** 取消*/
    private Button mBtCancel;
    private Activity mActivity;

    private onClickListener listener;

    public void setListener(onClickListener listener) {
        this.listener = listener;
    }
    public DialogPhotoView(@NonNull Activity activity) {
        super(activity, R.style.custom_dialog);
        this.mActivity = activity;
    }
    public DialogPhotoView(@NonNull Activity activity, onClickListener listener) {
        super(activity, R.style.custom_dialog);
        this.mActivity = activity;
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_select_imagepicke);
        /** 点击背景是否取消弹窗*/
        setCanceledOnTouchOutside(true);
        setCancelable(true);
        setupView();
    }

    private void setupView() {
        mLlPhotoAlbum = findViewById(R.id.ll_photo_album);
        mLlPhotoGraph = findViewById(R.id.ll_photo_graph);
        mBtCancel = findViewById(R.id.bt_cancel);

        mLlPhotoGraph.setOnClickListener(setViewClick());
        mLlPhotoAlbum.setOnClickListener(setViewClick());
        mBtCancel.setOnClickListener(setViewClick());
    }
    private View.OnClickListener setViewClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    if (v.getId() == R.id.bt_cancel) {
                        listener.cancelClick(DialogPhotoView.this);
                    } else if (v.getId() == R.id.ll_photo_album){
                        listener.ToPhotoAlbum(DialogPhotoView.this);
                    } else {
                        listener.ToPhotoGraph(DialogPhotoView.this);
                    }
                    dismiss();
                }
            }
        };
    }

    public interface onClickListener {
        void ToPhotoGraph(DialogPhotoView dialog);
        void ToPhotoAlbum(DialogPhotoView dialog);
        void cancelClick(DialogPhotoView dialog);
    }

    @Override
    public void show() {
        super.show();
        /**
         * 设置宽度全屏，要设置在show的后面
         */
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity= Gravity.BOTTOM;
        layoutParams.width= WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height= WindowManager.LayoutParams.WRAP_CONTENT;

        getWindow().getDecorView().setPadding(0, 0, 0, 0);

        getWindow().setAttributes(layoutParams);

    }
}
