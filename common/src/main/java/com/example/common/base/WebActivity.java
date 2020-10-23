package com.example.common.base;


import android.annotation.SuppressLint;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.common.R;
import com.example.common.R2;
import com.example.common.popupwindow.MenuPopWindow;
import com.example.common.utils.ToastUtils;
import com.orhanobut.logger.Logger;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.OnClick;

public class WebActivity extends NavbarActivity {

    public static String KURL = "kUrl";


    public static void startActivity(Context context, String url) {
        Intent intent = new Intent(context, WebActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(KURL, url);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }


    WebView mWvContent;
    ProgressBar mProgressbar;


    private String mUrl;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web;
    }

    @Override
    protected void initParam() {
        super.initParam();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.mUrl = extras.getString(KURL);
        }
    }

    @Override
    protected void initView() {
        initWeb();
        setRightMoreVisiable();
        setShareVisiable();
    }

    /**
     * 初始化WebView
     */
    private void initWeb() {

        mWvContent = findViewById(R.id.wv_content);
        mProgressbar = findViewById(R.id.progressbar);

        mWvContent.getSettings().setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        mWvContent.getSettings().setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        mWvContent.getSettings().setDisplayZoomControls(true); //隐藏原生的缩放控件
        mWvContent.getSettings().setBlockNetworkImage(false);//解决图片不显示
        mWvContent.getSettings().setLoadsImagesAutomatically(true); //支持自动加载图片
        mWvContent.getSettings().setDefaultTextEncodingName("utf-8");//设置编码格式

        mWvContent.removeJavascriptInterface("searchBoxJavaBridge_");
        mWvContent.removeJavascriptInterface("accessibilityTraversal");
        mWvContent.removeJavascriptInterface("accessibility");
        mWvContent.loadUrl(mUrl);
        Logger.d("监控界面加载的url为: " + mUrl);

        //该界面打开更多链接
        mWvContent.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                webView.loadUrl(s);
                return true;
            }
        });
        //监听网页的加载进度
        mWvContent.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView webView, int newProgress) {
                mProgressbar.setProgress(newProgress);
                if (newProgress == 100) {
                    mProgressbar.setVisibility(View.GONE);
                } else {
                    mProgressbar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
            }
        });

        mWvContent.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url != null) view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    /**
     * 激活 js 调用，设置 webView 活跃状态
     */
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public void onResume() {
        super.onResume();
        mWvContent.onResume();
        mWvContent.getSettings().setJavaScriptEnabled(true);
    }

    /**
     * ：退出界面暂停 webView的活跃，并且关闭 JS 支持
     */
    @Override
    protected void onPause() {
        super.onPause();
        mWvContent.onPause();
    }

    /**
     * 销毁 防止内存泄漏
     */
    @Override
    protected void onDestroy() {
        if (this.mWvContent != null) {
            mWvContent.destroy();
        }
        super.onDestroy();

    }

    @OnClick({R2.id.iv_share, R2.id.fl_right_more})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.iv_share) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, mUrl);
            startActivity(Intent.createChooser(intent, getTitle()));
        } else if (id == R.id.fl_right_more) {
            MenuPopWindow menuPopWindow = new MenuPopWindow(mContext, new MenuPopWindow.MenuPopClick() {
                @Override
                public void WebRefresh() {
                    mWvContent.reload();
                }

                @Override
                public void OpenBrowser() {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(mUrl));
                    startActivity(intent);
                }

                @Override
                public void CopyLink() {
                    ClipboardManager clipboardManager = (ClipboardManager) getSystemService(
                            CLIPBOARD_SERVICE);
                    if (clipboardManager != null) {
                        clipboardManager.setText(mUrl);
                    }
                    ToastUtils.show_s("已复制到剪切板");
                }
            });

            menuPopWindow.showPopupWindow(findViewById(R.id.fl_right_more));
        }
    }
}