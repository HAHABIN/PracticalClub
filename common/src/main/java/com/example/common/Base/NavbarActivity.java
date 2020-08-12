package com.example.common.Base;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.common.R;
import com.example.common.UiUtils.Utils;


public abstract class NavbarActivity extends BaseActivity {

    protected View navbar_v;

    @Override
    protected void addNavBar() {
        navbar_v = ViewGroup.inflate(mContext, R.layout.view_navbar,null);
        mainLayout.addView(navbar_v, new ViewGroup.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, Utils.dipPx(mContext,48)));
        navbar_v.findViewById(R.id.back_button_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    protected void hideBackButton() {
        navbar_v.findViewById(R.id.back_button_id).setVisibility(View.GONE);
    }

    protected void titleText(String text) {
        ((TextView) navbar_v.findViewById(R.id.navbar_title_tv_id)).setText(text);
    }

    protected TextView getTitleView() {
        return  ((TextView) navbar_v.findViewById(R.id.navbar_title_tv_id));
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
        ((TextView) navbar_v.findViewById(R.id.navbar_title_tv_id)).setTextColor(textColor);
    }
}
