/**
 * 文 件 名:  Category
 * 版    权:  QuanTeng Tech. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  <描述>
 * 修 改 人:  HABIN
 * 修改时间:  2020/8/21
 * 跟踪单号:  <跟踪单号>
 * 修改单号:  <修改单号>
 * 修改内容:  <修改内容>
 */
package com.example.common.bean.entity;


import android.os.Parcel;
import android.os.Parcelable;

import com.example.common.bean.HttpItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author HABIN
 * @version 2020/8/21
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class CategoryEntity extends HttpItem {
    /**{
     "data": [
     {
     "_id": "5e959250808d6d2fe6b56eda",
     "author": "\u9e22\u5a9b",
     "category": "Girl",
     "createdAt": "2020-05-25 08:00:00",
     "desc": "\u4e0e\u5176\u5b89\u6170\u81ea\u5df1\u5e73\u51e1\u53ef\u8d35\uff0c\n\u4e0d\u5982\u62fc\u5c3d\u5168\u529b\u6d3b\u5f97\u6f02\u4eae\u3002 \u200b \u200b\u200b\u200b\u200b",
     "images": [
     "http://gank.io/images/f4f6d68bf30147e1bdd4ddbc6ad7c2a2"
     ],
     "likeCounts": 4,
     "publishedAt": "2020-05-25 08:00:00",
     "stars": 1,
     "title": "\u7b2c96\u671f",
     "type": "Girl",
     "url": "http://gank.io/images/f4f6d68bf30147e1bdd4ddbc6ad7c2a2",
     "views": 5788
     },
     {
     "_id": "5e959197808d6d2fe6b56ed4",
     "author": "\u9e22\u5a9b",
     "category": "Girl",
     "createdAt": "2020-05-17 08:00:00",
     "desc": "\u53ea\u8981\u7ed3\u5c40\u662f\u559c\u5267\uff0c\u8fc7\u7a0b\u4f60\u8981\u6211\u600e\u4e48\u54ed\u90fd\u884c\uff0c\u5e78\u798f\u53ef\u4ee5\u6765\u7684\u6162\u4e00\u4e9b\uff0c\n\u53ea\u8981\u5b83\u662f\u771f\u7684\uff0c\u5982\u679c\u6700\u540e\u80fd\u5728\u4e00\u8d77\uff0c\u665a\u70b9\u6211\u771f\u7684\u65e0\u6240\u8c13\u7684\u3002",
     "images": [
     "http://gank.io/images/f0c192e3e335400db8a709a07a891b2e"
     ],
     "likeCounts": 0,
     "publishedAt": "2020-05-17 08:00:00",
     "stars": 1,
     "title": "\u7b2c88\u671f",
     "type": "Girl",
     "url": "http://gank.io/images/f0c192e3e335400db8a709a07a891b2e",
     "views": 1416
     }
     ],
     "page": 1,
     "page_count": 10,
     "status": 100,
     "total_counts": 96
     }*/
    private ArrayList<ResultBean> data;

    public ArrayList<ResultBean> getData() {
        return data;
    }

    public void setData(ArrayList<ResultBean> data) {
        this.data = data;
    }

    public static class ResultBean implements Parcelable {
        private String _id;

        private String author;

        private String category;

        private String createdAt;

        private String desc;

        private List<String> images;

        private int likeCounts;

        private String publishedAt;

        private int stars;

        private String title;

        private String type;

        private String url;

        private int views;

        protected ResultBean(Parcel in) {
            _id = in.readString();
            author = in.readString();
            category = in.readString();
            createdAt = in.readString();
            desc = in.readString();
            images = in.createStringArrayList();
            likeCounts = in.readInt();
            publishedAt = in.readString();
            stars = in.readInt();
            title = in.readString();
            type = in.readString();
            url = in.readString();
            views = in.readInt();
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

        public String get_id() {
            return _id;
        }

        public void set_id(String _id) {
            this._id = _id;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public int getLikeCounts() {
            return likeCounts;
        }

        public void setLikeCounts(int likeCounts) {
            this.likeCounts = likeCounts;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public int getStars() {
            return stars;
        }

        public void setStars(int stars) {
            this.stars = stars;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getViews() {
            return views;
        }

        public void setViews(int views) {
            this.views = views;
        }

        @Override
        public String toString() {
            return "ResultBean{" +
                    "_id='" + _id + '\'' +
                    ", author='" + author + '\'' +
                    ", category='" + category + '\'' +
                    ", createdAt='" + createdAt + '\'' +
                    ", desc='" + desc + '\'' +
                    ", images=" + images +
                    ", likeCounts=" + likeCounts +
                    ", publishedAt='" + publishedAt + '\'' +
                    ", stars=" + stars +
                    ", title='" + title + '\'' +
                    ", type='" + type + '\'' +
                    ", url='" + url + '\'' +
                    ", views=" + views +
                    '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(_id);
            dest.writeString(author);
            dest.writeString(category);
            dest.writeString(createdAt);
            dest.writeString(desc);
            dest.writeStringList(images);
            dest.writeInt(likeCounts);
            dest.writeString(publishedAt);
            dest.writeInt(stars);
            dest.writeString(title);
            dest.writeString(type);
            dest.writeString(url);
            dest.writeInt(views);
        }
    }

    @Override
    public String toString() {
        return "CategoryEntity{" +
                "data=" + data +
                '}';
    }
}
