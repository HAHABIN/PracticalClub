/**
 * 文 件 名:  TopStatusAdapter
 * 版    权:  QuanTeng Tech. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  HABIN
 * 修改时间:  2020/9/8
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.luck.main.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.base.adapter.CommonAdapter;
import com.luck.main.R;
import com.luck.main.R2;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.common.utils.Utils.getColor;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author HABIN
 * @version 2020/9/8
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class TopNavAdapter extends CommonAdapter<String, TopNavAdapter.ViewHolder> {

    private List<Boolean> isClicks;//控件是否被点击,默认为false，如果被点击，改变值，控件根据值改变自身颜色
    private OnItemClickListener mOnItemClickListener;

    public TopNavAdapter(Context mContext, ArrayList<String> mDataList,OnItemClickListener mOnItemClickListener) {
        super(mContext, mDataList);
        this.mOnItemClickListener = mOnItemClickListener;
        isClicks = new ArrayList<>();
        for (int i = 0; i < mDataList.size(); i++) {
            if (i == 0) {
                //默认第一个状态为点击
                isClicks.add(true);
            } else {
                isClicks.add(false);
            }
        }
    }
    //修改状态
    public void setStaus(int postion) {
        //点击状态全部设置为false
        for (int i = 0; i < isClicks.size(); i++) {
            isClicks.set(i, false);
        }
        //根据传入的postion改变当前状态
        isClicks.set(postion, true);
        //刷新数据
        notifyDataSetChanged();

    }

    @Override
    protected ViewHolder getContentViewHolder(@NonNull ViewGroup parent, int type) {
        return new ViewHolder(mInflater.inflate(R.layout.item_top_nav,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        String s = mDataList.get(position);
        holder.tvTitle.setText(s);
        holder.tvTitle.setTextColor(isClicks.get(position)
                ?getColor(R.color.colorPrimaryDark):getColor(R.color.white));
        holder.tvTitle.setSelected(isClicks.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //点击修改状态
                setStaus(position);
                if (mOnItemClickListener!=null) {
                    mOnItemClickListener.onItemClick(position);
                }
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.tv_title)
        TextView tvTitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

}
