package com.example.common.bean.request;

/**
 * <分类数据> <功能详细描述>
 *
 * @author HABIN
 * @version 2020/8/21
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class CategoryRequest extends BaseRequest{

   /** 分类 可接受参数 All(所有分类) | Article | GanHuo | Girl*/
   private String category;
   /** type 可接受参数 All(全部类型) | Android | iOS | Flutter | Girl | app | backend |frontend
    *  当category 为girl 类型只能是Girl
    *  Article 类型 除了 Girl 都行
    *  Ganhuo 类型 除了Girl backend 都行
    *
    * 即分类API返回的类型数据*/
   private String type;

   public String getCategory() {
      return category;
   }

   public void setCategory(String category) {
      this.category = category;
   }

   public String getType() {
      return type;
   }

   public void setType(String type) {
      this.type = type;
   }
}
