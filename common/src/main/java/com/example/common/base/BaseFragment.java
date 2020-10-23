package com.example.common.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.common.dialog.PictureDialog;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 继承BaseFragment基类
 */
public abstract class BaseFragment extends Fragment {

    protected String TAG;
    protected Activity mActivity;
    private Unbinder mUnBinder;
    private View mRoot;
    protected PictureDialog dialog;

    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * 初始化参数,包括intent传递过来的参数
     */
    protected void initParam(){}

    protected void processLogic() {
    }

    protected abstract void initView(View view);

    protected abstract void initListener();

    protected abstract void initData();



    /******************************lifecycle area*****************************************/
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initParam();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mActivity = getActivity();
        if (mRoot != null) {
            ViewGroup parent = (ViewGroup) mRoot.getParent();
            if (parent != null) {
                parent.removeView(mRoot);
            }
        }
        mRoot = inflater.from(getActivity()).inflate(getLayoutId(), container, false);
        mUnBinder = ButterKnife.bind(this, mRoot);
        TAG = getName();
        //绑定Presenter
        processLogic();
        initView(mRoot);
        initData();
        initListener();
        return mRoot;
    }


    protected void startActivity(Class clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(mActivity, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    protected void startActivity(Class clz, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(mActivity, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnBinder.unbind();
    }

    /**************************公共类*******************************************/
    public String getName() {
        return getClass().getName();
    }

    protected <VT> VT getViewById(int id) {
        if (mRoot == null) {
            return null;
        }
        return (VT) mRoot.findViewById(id);
    }

    /**
     * loading dialog
     */
    protected void showPleaseDialog() {
        if (!getActivity().isFinishing()) {
            dismissDialog();
            dialog = new PictureDialog(getContext());
            dialog.show();
        }
    }

    /**
     * dismiss dialog
     */
    protected void dismissDialog() {
        try {
            if (dialog != null && dialog.isShowing()) {
                dialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
