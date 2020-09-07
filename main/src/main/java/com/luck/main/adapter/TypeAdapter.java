/**
 * 文 件 名:  TypeAdapter
 * 版    权:  QuanTeng Tech. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  HABIN
 * 修改时间:  2020/9/7
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.luck.main.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.base.adapter.CommonAdapter;
import com.luck.main.R;
import com.luck.main.R2;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author HABIN
 * @version 2020/9/7
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class TypeAdapter extends CommonAdapter<String, TypeAdapter.ViewHolder> {


    public TypeAdapter(Context mContext, ArrayList mDataList) {
        super(mContext, mDataList);
    }

    @Override
    protected ViewHolder getContentViewHolder(@NonNull ViewGroup parent, int type) {
        return new ViewHolder(mInflater.inflate(R.layout.view_type_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.tvName.setText(mDataList.get(position));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.tv_name)
        TextView tvName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
