package com.example.common.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.common.R;


public class LoadMoreView extends LinearLayout {

    public static final int normal = 0;
    public static final int loading = 1;
    public static final int loadFinish = 2;

    private TextView titleView;
    private ProgressBar pbLoading;

    public LoadMoreView(Context context) {
        this(context, null);
    }

    public LoadMoreView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadMoreView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        loadView();
    }

    public void setStatus(int status) {
        pbLoading.setVisibility(VISIBLE);
        setVisibility(VISIBLE);
        switch (status) {
            case normal:
                setVisibility(GONE);
                break;
            case loading:
                titleView.setText("正在加载中...");
                break;
            case loadFinish:
                pbLoading.setVisibility(GONE);
                titleView.setText("没有更多数据了");
                titleView.setVisibility(VISIBLE);
                break;
        }
    }

    private void loadView() {
        setLayoutParams(new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_footer_view, this);

        titleView = view.findViewById(R.id.show_text_id);
        pbLoading = view.findViewById(R.id.pb_loading);

    }
}
