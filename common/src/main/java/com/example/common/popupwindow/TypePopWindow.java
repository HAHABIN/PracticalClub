/**
 * 文 件 名:  TypePopWindow
 * 版    权:  QuanTeng Tech. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  HABIN
 * 修改时间:  2020/9/7
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.example.common.popupwindow;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.common.R;
import com.example.common.base.adapter.CommonAdapter;
import com.example.common.utils.Utils;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author HABIN
 * @version 2020/9/7
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class TypePopWindow<T> {


    public TypePopWindow() {
    }

    public void setListener(TypePopListener listener) {
        this.listener = listener;
    }

    public interface TypePopListener <T>{
        void dismiss();
        void onItemClick(int position);
        CommonAdapter addAdapter(Activity activity, ArrayList<T> items);
    }

    private TypePopListener listener;

    private PopupWindow mPopupWindow;

    private WeakReference<Activity> mActivity;

    private ArrayList<T> menuList = new ArrayList<>();

    public void showPop(Activity activity, View view, ArrayList<T> result) {
        mActivity = new WeakReference<>(activity);
        menuList.addAll(result);
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflate = inflater.inflate(R.layout.view_pop_type, null, false);//引入弹窗布局
        setListener(inflate);
        mPopupWindow = new PopupWindow(inflate, ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT, true);
        //设置进出动画
        mPopupWindow.setAnimationStyle(R.style.style_pop_anim_alpha);
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                mPopupWindow = null;
                if (listener!=null) listener.dismiss();
            }
        });
        //相对于父控件的位置（例如正中央Gravity.CENTER，下方Gravity.BOTTOM等），可以设置偏移或无偏移
        mPopupWindow.showAsDropDown(view, 0, 0, Gravity.BOTTOM);
    }

    private void setListener(View inflate) {
        View bottom = inflate.findViewById(R.id.view_pop_store_bottom);
        bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });
        RecyclerView rv = inflate.findViewById(R.id.rv_pop_store_goodsType);
        rv.setLayoutManager(new GridLayoutManager(Utils.getContext(),4));

        CommonAdapter adapter = listener.addAdapter(mActivity.get(),menuList);
        rv.setAdapter(adapter);


        adapter.setOnItemClickListener(new CommonAdapter.OnItemClick() {
            @Override
            public void onItemClick(RecyclerView.ViewHolder holder, int position) {
                if (listener != null) {
                    listener.onItemClick(position);
                }
                mPopupWindow.dismiss();
            }
        });
    }
}
