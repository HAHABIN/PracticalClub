package com.example.common.base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.common.R;
import com.example.common.utils.Utils;

/**
 * 有返回按钮
 */
public abstract class NavbarActivity extends BaseActivity {

    protected View navbar_v;

    @Override
    protected void addNavBar() {
        navbar_v = ViewGroup.inflate(mContext, R.layout.view_navbar,null);
        mainLayout.addView(navbar_v, new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, Utils.dipPx(48)));
        navbar_v.findViewById(R.id.fl_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    protected void hideBackButton() {
        navbar_v.findViewById(R.id.fl_back).setVisibility(View.GONE);
    }

    protected void titleText(String text) {
        ((TextView) findViewById(R.id.tv_title)).setText(text);
    }

    protected TextView getTitleView() {
        return  ((TextView) navbar_v.findViewById(R.id.tv_title ));
    }

    protected void titleText(int resId) {
        titleText(getResources().getString(resId));
    }

    protected void setRightTxt(String text){
        ((TextView) navbar_v.findViewById(R.id.textview_right_text)).setText(text);
    }

    protected void setRightMoreVisiable(){
        navbar_v.findViewById(R.id.fl_right_more).setVisibility(View.VISIBLE);
    }

    protected void setShareVisiable() {
        navbar_v.findViewById(R.id.iv_share).setVisibility(View.VISIBLE);
    }
    protected void setRightBg(int resID){
        setRightMoreVisiable();
        navbar_v.findViewById(R.id.iv_right).setBackgroundResource(resID);
    }

    protected View getRightBg(){
       return navbar_v.findViewById(R.id.fl_right_more);
    }

    protected TextView getRightText(){
        return (TextView) navbar_v.findViewById(R.id.textview_right_text);
    }

    protected void setBottomLineGone(){
        navbar_v.findViewById(R.id.view_botton).setVisibility(View.GONE);
    }

    protected void setNavBg(int bgColor,int textColor){
        navbar_v.setBackgroundResource(bgColor);
        navbar_v.findViewById(R.id.view_botton).setVisibility(View.GONE);
        ((TextView) navbar_v.findViewById(R.id.tv_title)).setTextColor(textColor);
    }
}
