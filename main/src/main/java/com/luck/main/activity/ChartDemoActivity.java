package com.luck.main.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.example.common.base.BaseActivity;
import com.example.common.bean.entity.Company;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;
import com.github.mikephil.charting.utils.ViewPortHandler;
import com.luck.main.R;
import com.luck.main.bean.MyXFormatter;
import com.luck.main.view.CompanyMarker;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ChartDemoActivity extends BaseActivity {


    private LineChart lineChart;
    private BarChart barChart;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chart_demo;
    }

    @Override
    protected void initView() {
        lineChart = findViewById(R.id.line_chart);
        barChart = findViewById(R.id.bar_chart);
    }


    @Override
    protected void initListener() {
    /***------------------ 折线图 ---------------------*/

    /**
     * Entry 坐标点对象  构造函数 第一个参数为x点坐标 第二个为y点
     */
        ArrayList<Entry> values1 = new ArrayList<>();
        ArrayList<Entry> values2 = new ArrayList<>();

        values1.add(new Entry(9.1f, 10));
        values1.add(new Entry(9.2f, 15));
        values1.add(new Entry(9.3f, 20));
        values1.add(new Entry(9.4f, 5));
        values1.add(new Entry(9.5f, 30));

//        values2.add(new Entry(3,110));
//        values2.add(new Entry(6,115));
//        values2.add(new Entry(9,130));
//        values2.add(new Entry(12,85));
//        values2.add(new Entry(15,90));

        //LineDataSet每一个对象就是一条连接线
        LineDataSet set1;
//        LineDataSet set2;

        //判断图表中原来是否有数据
        if (lineChart.getData() != null &&
                lineChart.getData().getDataSetCount() > 0) {
            //获取数据1
            set1 = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
            set1.setValues(values1);
//            set2= (LineDataSet) lineChart.getData().getDataSetByIndex(1);
//            set2.setValues(values2);
            //刷新数据
            lineChart.getData().notifyDataChanged();
            lineChart.notifyDataSetChanged();
        } else {
            //设置数据1  参数1：数据源 参数2：图例名称
            set1 = new LineDataSet(values1, "测试数据1");
            set1.setColor(Color.BLACK);
            set1.setCircleColor(Color.BLACK);
            set1.setLineWidth(1f);//设置线宽
            set1.setCircleRadius(3f);//设置焦点圆心的大小
            set1.enableDashedHighlightLine(10f, 5f, 0f);//点击后的高亮线的显示样式
            set1.setHighlightLineWidth(2f);//设置点击交点后显示高亮线宽
            set1.setHighlightEnabled(true);//是否禁用点击高亮线
            set1.setHighLightColor(Color.RED);//设置点击交点后显示交高亮线的颜色
            set1.setValueTextSize(9f);//设置显示值的文字大小
            set1.setDrawFilled(false);//设置禁用范围背景填充

            //格式化显示数据
            final DecimalFormat mFormat = new DecimalFormat("###,###,##0");
            set1.setValueFormatter(new ValueFormatter() {
                @Override
                public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                    return mFormat.format(value);
                }
            });
            if (Utils.getSDKInt() >= 18) {
                // fill drawable only supported on api level 18 and above
                Drawable drawable = ContextCompat.getDrawable(this, R.drawable.arrow_down);
                set1.setFillDrawable(drawable);//设置范围背景填充
            } else {
                set1.setFillColor(Color.BLACK);
            }

            //设置数据2
//            set2 = new LineDataSet(values2, "测试数据2");
//            set2.setColor(Color.GRAY);
//            set2.setCircleColor(Color.GRAY);
//            set2.setLineWidth(1f);
//            set2.setCircleRadius(3f);
//            set2.setValueTextSize(10f);

            //保存LineDataSet集合
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1); // add the datasets
//            dataSets.add(set2);
            //创建LineData对象 属于LineChart折线图的数据集合
            LineData data = new LineData(dataSets);
            // 添加到图表中
            lineChart.setData(data);
            //绘制图表
            lineChart.invalidate();
        }

        /***------------------ 柱状图 ---------------------*/
    }

    @Override
    protected void initData() {
        /***------------------ 折线图 ---------------------*/
        //创建描述信息
        Description description = new Description();
        description.setText("测试图表");
        description.setTextColor(Color.RED);
        description.setTextSize(20);
        lineChart.setDescription(description);//设置图表描述信息


        lineChart.setNoDataText("没有数据熬");//没有数据时显示的文字
        lineChart.setNoDataTextColor(Color.BLUE);//没有数据时显示文字的颜色
        lineChart.setDrawGridBackground(false);//chart 绘图区后面的背景矩形将绘制
        lineChart.setDrawBorders(false);//禁止绘制图表边框的线
        //lineChart.setBorderColor(); //设置 chart 边框线的颜色。
        //lineChart.setBorderWidth(); //设置 chart 边界线的宽度，单位 dp。
        //lineChart.setLogEnabled(true);//打印日志
        //lineChart.notifyDataSetChanged();//刷新数据
        //lineChart.invalidate();//重绘
        lineChart.setDragEnabled(false);
        lineChart.setScaleEnabled(false);
        lineChart.setScaleXEnabled(false);

//        lineChart.getDescription().setEnabled(false);
        /***------------------ 柱状图 ---------------------*/


        //去除图标描述
        barChart.getDescription().setEnabled(false);
        //去除左下角图例
        barChart.getLegend().setEnabled(false);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(false);//禁止竖直缩放
        barChart.setPinchZoom(false);//禁止水平缩放
        barChart.setNoDataText("没有数据熬");//没有数据时显示的文字
        barChart.setNoDataTextColor(Color.BLUE);//没有数据时显示文字的颜色
        barChart.setDrawGridBackground(false);//chart 绘图区后面的背景矩形将绘制
        barChart.setDrawBorders(false);//禁止绘制图表边框的线


        ArrayList<Company> companies = generateData(0);
        MyXFormatter myXFormatter = new MyXFormatter(companies);

        /** 点击数据展示*/
        barChart.setDrawMarkers(true);
        CompanyMarker companyMarker = new CompanyMarker(this, companies);
        companyMarker.setChartView(barChart);
        barChart.setMarker(companyMarker);


        ArrayList<BarEntry> barEntries = new ArrayList<>();
        float start = 1f;
        for (int i=0; i<companies.size(); i++){
            barEntries.add(new BarEntry(i*start, companies.get(i).getCount()));
        }



        /** X轴*/
        XAxis xAxis = barChart.getXAxis();
        //设置x轴显示在下方
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置x轴文字颜色
        xAxis.setTextColor(Color.parseColor("#1F1F1F"));
        //设置x轴文字大小
        xAxis.setTextSize(11f);
        //x轴文字旋转
        xAxis.setLabelRotationAngle(-25);
        //移除x轴线
        xAxis.setDrawAxisLine(false);
        xAxis.setValueFormatter(myXFormatter);
        //移除表格中竖线
        xAxis.setDrawGridLines(false);
        //x轴显示数值数量
        xAxis.setLabelCount(10);
        /** 移除右侧y轴*/
        barChart.getAxisRight().setEnabled(false);
        /** 设置左侧y轴数据*/
        YAxis yAxis = barChart.getAxisLeft();
        //设置y轴文字颜色
        yAxis.setTextColor(Color.parseColor("#1F1F1F"));
        yAxis.setDrawAxisLine(false);
        //设置y轴文字大小
        yAxis.setTextSize(11f);

        /** 柱子参数设置*/
        BarDataSet dataSet = new BarDataSet(barEntries,"");
        //柱子颜色
        dataSet.setColor(Color.parseColor("#5CBBA3"));
        //柱子高亮颜色
        dataSet.setHighLightColor(Color.parseColor("#117e62"));

        BarData data = new BarData(dataSet);
        //去除柱子上方的数值
        data.setDrawValues(false);
        //设置数据
        barChart.setData(data);


        /** 柱子数量*/
        barChart.setVisibleXRange(0,10);
    }


    private ArrayList<Company> generateData(int i) {
        List<Company> companies = new ArrayList<>();
        companies.add(new Company("Jack"+i,1100));
        companies.add(new Company("Mak"+i,2300));
        companies.add(new Company("Tom"+i,124));
        companies.add(new Company("Lisa"+i,344));
        companies.add(new Company("Tony"+i,12));
        companies.add(new Company("Copy"+i,333));
        companies.add(new Company("Test"+i,4444));
        companies.add(new Company("Jack"+i,1100));
        companies.add(new Company("Mak"+i,2300));
        companies.add(new Company("Tom"+i,124));
        companies.add(new Company("Lisa"+i,344));
        companies.add(new Company("Tony"+i,12));
        companies.add(new Company("Copy"+i,333));
        companies.add(new Company("Test"+i,4444));
        return (ArrayList<Company>) companies;
    }
}