package com.example.common.widget.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.common.R;


/**
 * <列表空白界面展位图> <功能详细描述>
 *
 * @author HABIN
 * @version 2020/7/29
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ListEmptyView extends LinearLayout implements View.OnClickListener {

    private ImageView mIvImg;
    private TextView mTvContext;
    private TextView mTvRefresh;

    private OnEmptyListener mOnEmptyListener;

    public ListEmptyView(Context context) {
        super(context);
        init(context);
    }

    public ListEmptyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_default_page, this, true);
        mIvImg = (ImageView) view.findViewById(R.id.iv_img);
        mTvContext = (TextView)view.findViewById(R.id.tv_content);
        mTvRefresh = (TextView)view.findViewById(R.id.tv_refresh);
        mTvRefresh.setOnClickListener(this);
    }

    public void setPhoto(int resId){
        mIvImg.setImageResource(resId);
    }

    public void setContext(String msg){
        mTvContext.setText(msg);
    }

    public void setVisRefresh(boolean visRefresh){
        mTvRefresh.setVisibility(visRefresh?VISIBLE:GONE);
    }


    public void setNoSearchView(){
        mIvImg.setImageResource(R.drawable.ic_default_no_search);
        mTvContext.setText(getContext().getString(R.string.string_default_page_no_search));
        mTvRefresh.setVisibility(GONE);
    }

    public void setNoDataView(){
        mIvImg.setImageResource(R.drawable.ic_default_no_data);
        mTvContext.setText(getContext().getString(R.string.string_default_page_no_data));
        mTvRefresh.setVisibility(VISIBLE);
    }
    public void setNoNetWorkView(){
        mIvImg.setImageResource(R.drawable.ic_default_no_network);
        mTvContext.setText(getContext().getString(R.string.string_default_page_no_network));
        mTvRefresh.setVisibility(VISIBLE);
    }
    public void setOnEmptyListener(OnEmptyListener onEmptyListener){
        this.mOnEmptyListener = onEmptyListener;
    }
    @Override
    public void onClick(View v) {
        if (mOnEmptyListener!=null) {
            mOnEmptyListener.onEmptyRefresh();
        }
    }

    public interface OnEmptyListener{
        void onEmptyRefresh();
    }
}
