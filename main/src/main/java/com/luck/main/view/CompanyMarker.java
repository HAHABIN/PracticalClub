/**
 * 文 件 名:  CompanyMarker
 * 版    权:  QuanTeng Tech. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  HABIN
 * 修改时间:  2020/10/13
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.luck.main.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;

import com.example.common.bean.entity.Company;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;
import com.luck.main.R;

import java.util.List;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author HABIN
 * @version 2020/10/13
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@SuppressLint("ViewConstructor")
public class CompanyMarker extends MarkerView {

    private TextView mTvContent;
    private TextView mTvCount;

    private List<Company> companies;

    public CompanyMarker(Context context, List<Company> companies) {
        super(context, R.layout.view_marker);
        this.companies = companies;
        mTvContent = findViewById(R.id.tv_name);
        mTvCount = findViewById(R.id.tv_count);
    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        Company item = companies.get((int) e.getX());
        mTvContent.setText(item.getProvince());
        mTvCount.setText(item.getCount()+"");
        super.refreshContent(e,highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(float)(getWidth()/2),-getHeight() -10);
    }
}
