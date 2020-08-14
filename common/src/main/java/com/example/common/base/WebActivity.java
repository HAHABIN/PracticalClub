package com.example.common.base;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.GeolocationPermissions;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.common.R;
import com.example.common.R2;
import com.example.common.utils.PictureSelectorUtils;
import com.example.common.utils.Utils;
import com.example.common.widget.view.ColorProgressBar;
import com.example.common.widget.web.BaseWebView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

import butterknife.BindView;

@SuppressLint({"SetJavaScriptEnabled", "DefaultLocale", "InflateParams"})
public class WebActivity extends NavbarActivity {

    public static String kUrl = "kUrl";
    public static String kTitle = "kTitle";



    public static void startActivity(Context context, String title, String url) {
        Intent intent = new Intent(context, WebActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(kUrl, url);
        bundle.putString(kTitle, title);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }




    private String url;
    private String title;

    ValueCallback<Uri[]> mUploadMessageList;

    private WebChromeClient mWebChromeClient = new WebChromeClient() {

        @Override
        public void onProgressChanged(WebView view, int progress) {
            mPbWeb.setProgress(progress);
        }

        @Override
        public void onShowCustomView(View view, CustomViewCallback callback) {
            // TODO Auto-generated method stub
            super.onShowCustomView(view, callback);
        }

        @Override
        public void onGeolocationPermissionsShowPrompt(String origin,
                                                       GeolocationPermissions.Callback callback) {
            // TODO Auto-generated method stub
            super.onGeolocationPermissionsShowPrompt(origin, callback);
            callback.invoke(origin, true, false);
        }

        /**
         * 5.0+
         */
        @Override
        public boolean onShowFileChooser(WebView webView, ValueCallback<Uri[]> filePathCallback, FileChooserParams fileChooserParams) {
            // make sure there is no existing message
            cancelCallBack();
            mUploadMessageList = filePathCallback;
            PictureSelectorUtils.create(WebActivity.this).openDialogInActivity(1, null, true, false);
            return true;
        }
    };

    protected BaseWebView webView;
    ColorProgressBar mPbWeb;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected void initParam() {
        super.initParam();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.url = extras.getString(kUrl);
            this.title = extras.getString(kTitle);
        }
        //TODO : 测试数据
        this.url = "https://gank.io/api/v2/post/5e777432b8ea09cade05263f";
        this.title = "干货 机";
    }

    @Override
    protected void initView() {
        webView = findViewById(R.id.bwv_id);
        mPbWeb = findViewById(R.id.pb_web);
        webView.setWebChromeClient(mWebChromeClient);
        webView.setTitle((TextView) navbar_v.findViewById(R.id.navbar_title_tv_id));
        try {
            if (Utils.getAndroidSDKVersion() >= 11) {
                webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initListener() {
        getRightBg().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        titleText(title);
        setRightBg(R.mipmap.icon_close);
        if (TextUtils.isEmpty(url)) {

        } else {
            webView.loadUrl(url);
        }
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        if (webView != null) {
            webView.onPause();
        }
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        if (webView != null) {
            webView.onResume();
        }
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        if (webView != null) {
            webView.loadDataWithBaseURL(null, "", "text/html", "utf-8", null);
            webView.clearHistory();
            webView.clearCache(true);
            webView.clearFormData();
            if (mainLayout != null) {
                mainLayout.removeView(webView);
            }
            webView.destroy();
            webView = null;
        }
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PictureConfig.CHOOSE_REQUEST:
                if (mUploadMessageList == null) {
                    return;
                }
                if (resultCode != RESULT_OK) {
                    mUploadMessageList.onReceiveValue(null);
                    mUploadMessageList = null;
                } else {
                    List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                    if (selectList.size() > 0) {
                        LocalMedia localMedia = selectList.get(0);
                        String mCompressPath = localMedia.getCompressPath();
                        Uri[] ul = new Uri[1];
                        ul[0] = Utils.getMediaUriFromPath(this, mCompressPath);
                        if (ul[0] != null) {
                            mUploadMessageList.onReceiveValue(ul);
                            mUploadMessageList = null;
                        } else {
                            // TODO: handle exception
                            mUploadMessageList.onReceiveValue(null);
                            mUploadMessageList = null;
                        }

                    }
                }

                break;
        }

    }

    /**
     *  * 防止点击dialog的取消按钮之后，就不再次响应点击事件了
     *  
     */
    public void cancelCallBack() {
        if (mUploadMessageList != null) {
            mUploadMessageList.onReceiveValue(null);
            mUploadMessageList = null;
        }
    }

}
