package com.luck.main.bean;

import com.example.common.bean.entity.Company;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;

public class MyXFormatter extends ValueFormatter{

        private ArrayList<Company> mValues;

        public MyXFormatter(ArrayList<Company> values)
        {
            this.mValues = values;
        }

        @Override
        public String getFormattedValue(float value, AxisBase axis) {
            if (((int) value >= 0 && (int) value < mValues.size()))
                return mValues.get((int) value).getProvince();
            else
                return "";
        }

        /**
         * Called when drawing any label, used to change numbers into formatted strings.
         *
         * @param value float to be formatted
         * @return formatted string label
         */
        @Override
        public String getFormattedValue(float value) {
            if (((int) value >= 0 && (int) value < mValues.size())){
                String name = mValues.get((int) value).getProvince();
                return name.trim();
            } else
                return "";
        }
    }