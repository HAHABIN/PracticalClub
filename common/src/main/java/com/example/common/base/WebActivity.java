
package com.example.common.base;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.common.R;
import com.example.common.databinding.ActivityWebBinding;
import com.example.common.popupwindow.MenuPopWindow;
import com.example.common.utils.ToastUtils;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;


/**
 * 文 件 名: WebActivity
 * 创 建 人: 易冬
 * 创建日期: 2017/4/21 09:24
 * 邮   箱: onlyloveyd@gmail.com
 * 博   客: https://onlyloveyd.cn
 * 描   述：内部网页显示Activity
 */
public class WebActivity extends AppCompatActivity implements View.OnClickListener{

    public static String URL = "URL";

    public static void startActivity(Context context, String url) {
        Intent intent = new Intent(context, WebActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(URL, url);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }

    private TextView mTvNavTitle;
    private FrameLayout mFlBack;
    private FrameLayout mFlRightMore;
    private ImageView mIvShare;
    private String mUrl = null;

    private ActivityWebBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_web);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            mUrl = bundle.getString(URL);
        }

        init();
        initWebViewSettings();

        mBinding.wvContent.removeJavascriptInterface("searchBoxJavaBridge_");
        mBinding.wvContent.removeJavascriptInterface("accessibilityTraversal");
        mBinding.wvContent.removeJavascriptInterface("accessibility");
        mBinding.wvContent.loadUrl(mUrl);
    }

    private void init() {
        mFlBack = mBinding.includeToolbar.findViewById(R.id.fl_back);
        mTvNavTitle = mBinding.includeToolbar.findViewById(R.id.tv_nav_title);
        mFlRightMore = mBinding.includeToolbar.findViewById(R.id.fl_right_more);
        mIvShare = mBinding.includeToolbar.findViewById(R.id.iv_share);
        mFlRightMore.setVisibility(View.VISIBLE);
        mIvShare.setVisibility(View.VISIBLE);
        mFlRightMore.setOnClickListener(this);
        mIvShare.setOnClickListener(this);
        mFlBack.setOnClickListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        mBinding.wvContent.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mBinding.wvContent.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBinding.wvContent.destroy();
    }


    private void initWebViewSettings() {
        WebSettings settings = mBinding.wvContent.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setLoadWithOverviewMode(true);
        settings.setAppCacheEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setSupportZoom(true);
        settings.setSavePassword(false);
        mBinding.wvContent.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mBinding.progressbar.setProgress(newProgress);
                if (newProgress == 100) {
                    mBinding.progressbar.setVisibility(View.GONE);
                } else {
                    mBinding.progressbar.setVisibility(View.VISIBLE);
                }
            }


            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                mTvNavTitle.setText(title);
            }
        });
        mBinding.wvContent.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url != null) view.loadUrl(url);
                return true;
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.fl_back) {
            finish();
        } else if (id == R.id.fl_right_more) {
            MenuPopWindow popupWindow = new MenuPopWindow(WebActivity.this, new MenuPopWindow.MenuPopClick() {
                @Override
                public void WebRefresh() {
                    mBinding.wvContent.reload();
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
            popupWindow.showPopupWindow(mFlRightMore);
        } else if (id == R.id.iv_share) {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, URL);
            startActivity(Intent.createChooser(intent, getTitle()));
        }
    }
}