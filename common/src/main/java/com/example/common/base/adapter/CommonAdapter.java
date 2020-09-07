/**
 * 文 件 名:  CommonAdapter
 * 版    权:  QuanTeng Tech. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  HABIN
 * 修改时间:  2020/6/28
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.example.common.base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


/**
 * <通用适配器> <功能详细描述>
 *
 * @author HABIN
 * @version 2020/6/28
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public abstract class CommonAdapter<DATA,RV extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<RV> {

    protected Context mContext;

    protected ArrayList<DATA> mDataList;

    protected final LayoutInflater mInflater;

    private OnItemClick mOnItemClick;

    public void setOnItemClickListener(OnItemClick onItemClick){
        this.mOnItemClick = onItemClick;
    }

    public CommonAdapter(Context mContext) {
        this.mContext = mContext;
        this.mInflater =  LayoutInflater.from(mContext);
    }

    public CommonAdapter(Context mContext, ArrayList<DATA> mDataList) {
        this.mContext = mContext;
        this.mDataList = mDataList;
        this.mInflater =  LayoutInflater.from(mContext);
    }

    public ArrayList<DATA> getAllData(){
        if (mDataList == null) {
            mDataList = new ArrayList<>();
        }
        return mDataList;
    }

    public void setData(ArrayList<DATA> dataList) {
        if (mDataList == null) {
            mDataList = new ArrayList<>();
        } else {
            mDataList.clear();
        }
        mDataList.addAll(dataList);
        notifyDataSetChanged();
    }

    public void notifyItemRangeData(int start,int end,ArrayList<DATA> dataList){
        mDataList.addAll(dataList);
        notifyItemRangeChanged(start,end);
    }
    /**
     * 获取 内容的viewHolder
     */
    protected abstract RV getContentViewHolder(@NonNull ViewGroup parent, int type);

    @NonNull
    @Override
    public RV onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return getContentViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull RV holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClick!=null) {
                    mOnItemClick.onItemClick(holder,position);
                }
            }
        });
    }

    /**
     * @return 测试用 设置显示几个数据
     */
    protected  int setTestCount() {
        return 0;
    }

    @Override
    public int getItemCount() {
        if (setTestCount()!=0) {
            return setTestCount();
        }
        return mDataList == null ? 0 : mDataList.size();
    }

    //定义一个点击事件的接口
    public interface OnItemClick {
        void onItemClick(RecyclerView.ViewHolder holder, int position);
    }



}
