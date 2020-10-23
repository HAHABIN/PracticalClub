package com.example.common.popupwindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.common.R;
import com.example.common.utils.Utils;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author HABIN
 * @version 2020/8/19
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class MenuPopWindow extends PopupWindow implements View.OnClickListener {

    private View mainView;

    private Context mContext;

    private MenuPopClick mMenuPopClick;

    private LinearLayout mLlRefresh;
    private LinearLayout mLlCopyLink;
    private LinearLayout mLlOpenBrowser;

    public MenuPopWindow(Context context,MenuPopClick menuPopClick) {
        this(context, Utils.dipPx(180),Utils.dipPx(160));
        this.mMenuPopClick = menuPopClick;
    }

    public MenuPopWindow(Context context, int width, int height) {
        super(context);
        this.mContext = context;
        setWidth(width);
        setHeight(height);
        setFocusable(true);
        setOutsideTouchable(true);
        setTouchable(true);
        //设置背景透明
        setBackgroundDrawable(new ColorDrawable(0));
        mainView = LayoutInflater.from(context).inflate(R.layout.pop_menu_view, null);
        setContentView(mainView);
        mLlRefresh = mainView.findViewById(R.id.ll_refresh);
        mLlCopyLink = mainView.findViewById(R.id.ll_copy_link);
        mLlOpenBrowser = mainView.findViewById(R.id.ll_open_browser);
        mLlRefresh.setOnClickListener(this);
        mLlCopyLink.setOnClickListener(this);
        mLlOpenBrowser.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.ll_refresh) {
            mMenuPopClick.WebRefresh();
        } else if (id == R.id.ll_copy_link) {
            mMenuPopClick.CopyLink();
        } else if (id == R.id.ll_open_browser) {
            mMenuPopClick.OpenBrowser();
        }
        dismiss();
    }


    public interface MenuPopClick {
        void WebRefresh();

        void OpenBrowser();

        void CopyLink();
    }


    /**
     * 显示popupWindow
     *
     * @param parent
     */
    public void showPopupWindow(View parent) {
        if (!this.isShowing()) {
            // 以下拉方式显示popupwindow
            this.showAsDropDown(parent, -5, 0);
        } else {
            this.dismiss();
        }
    }
}
