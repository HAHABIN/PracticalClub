
package com.luck.main.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.base.WebActivity;
import com.example.common.base.adapter.CommonAdapter;
import com.example.common.bean.beanEnum.TypeEnum;
import com.example.common.bean.entity.CategoryEntity;
import com.example.common.utils.StringUtils;
import com.example.common.utils.Utils;
import com.example.common.widget.view.RoundImageView;
import com.luck.main.R;
import com.luck.main.R2;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author HABIN
 * @version 2020/9/3
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class NewsAdapter extends CommonAdapter<CategoryEntity.ResultBean, NewsAdapter.ViewHolder> {



    public NewsAdapter(Context mContext) {
        super(mContext);
    }

    public NewsAdapter(Context mContext, ArrayList<CategoryEntity.ResultBean> mDataList) {
        super(mContext, mDataList);
    }

    @Override
    protected ViewHolder getContentViewHolder(@NonNull ViewGroup parent, int type) {
        return new ViewHolder(mInflater.inflate(R.layout.item_news, parent, false));
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoryEntity.ResultBean resultBean = mDataList.get(position);
        if (resultBean.getImages()!=null&& resultBean.getImages().size() > 0) {
            holder.rivPic.setVisibility(View.VISIBLE);
            Utils.Glideload(resultBean.getImages().get(0),holder.rivPic);
        } else {
            holder.rivPic.setVisibility(View.GONE);
        }
        holder.tvCategory.setText(TypeEnum.getNameByType(resultBean.getType()));
        holder.tvAuthor.setText(resultBean.getAuthor());
        holder.tvDesc.setText(resultBean.getDesc());
        holder.tvTitle.setText(resultBean.getTitle());
        long l = StringUtils.dateToStamp(resultBean.getCreatedAt());
        holder.tvTime.setText(StringUtils.getTimeFormatText(l));
        holder.itemView.setOnClickListener(v -> {
            WebActivity.startActivity(holder.itemView.getContext()
                    ,StringUtils.formatString(R.string.string_url,resultBean.get_id()));
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R2.id.riv_pic)
        RoundImageView rivPic;
        @BindView(R2.id.tv_category)
        TextView tvCategory;
        @BindView(R2.id.tv_title)
        TextView tvTitle;
        @BindView(R2.id.tv_desc)
        TextView tvDesc;
        @BindView(R2.id.tv_author)
        TextView tvAuthor;
        @BindView(R2.id.tv_time)
        TextView tvTime;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
