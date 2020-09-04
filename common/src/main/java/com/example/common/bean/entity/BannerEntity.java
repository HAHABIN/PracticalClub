/**
 * 文 件 名:  BannerEntity
 * 版    权:  QuanTeng Tech. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  HABIN
 * 修改时间:  2020/8/27
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.example.common.bean.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.common.bean.HttpItem;
import com.example.common.http.HttpClient;
import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author HABIN
 * @version 2020/8/27
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class BannerEntity extends HttpItem {

    /**
     *
     * {
     *   "data": [
     *     {
     *       "image": "http://gank.io/images/cfb4028bfead41e8b6e34057364969d1",
     *       "title": "\u5e72\u8d27\u96c6\u4e2d\u8425\u65b0\u7248\u66f4\u65b0",
     *       "url": "https://gank.io/migrate_progress"
     *     },
     *     {
     *       "image": "http://gank.io/images/aebca647b3054757afd0e54d83e0628e",
     *       "title": "- \u6625\u6c34\u521d\u751f\uff0c\u6625\u6797\u521d\u76db\uff0c\u6625\u98ce\u5341\u91cc\uff0c\u4e0d\u5982\u4f60\u3002",
     *       "url": "https://gank.io/post/5e51497b6e7524f833c3f7a8"
     *     },
     *     {
     *       "image": "https://pic.downk.cc/item/5e7b64fd504f4bcb040fae8f.jpg",
     *       "title": "\u76d8\u70b9\u56fd\u5185\u90a3\u4e9b\u514d\u8d39\u597d\u7528\u7684\u56fe\u5e8a",
     *       "url": "https://gank.io/post/5e7b5a8b6d2e518fdeab27aa"
     *     }
     *   ],
     *   "status": 100
     * }
     * */

    private ArrayList<ResultBean> data;

    public ArrayList<ResultBean> getData() {
        return data;
    }

    public void setData(ArrayList<ResultBean> data) {
        this.data = data;
    }

    public static class ResultBean extends SimpleBannerInfo implements Parcelable {

        private String title;

        private String image;

        private String url;

        protected ResultBean(Parcel in) {
            title = in.readString();
            image = in.readString();
            url = in.readString();
        }

        public static final Creator<ResultBean> CREATOR = new Creator<ResultBean>() {
            @Override
            public ResultBean createFromParcel(Parcel in) {
                return new ResultBean(in);
            }

            @Override
            public ResultBean[] newArray(int size) {
                return new ResultBean[size];
            }
        };

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(title);
            dest.writeString(image);
            dest.writeString(url);
        }

        @Override
        public Object getXBannerUrl() {
            return url;
        }
    }
}
