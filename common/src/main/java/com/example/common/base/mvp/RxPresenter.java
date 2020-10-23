package com.example.common.base.mvp;


/**
 * Create by HABIN on 2019/11/4
 * Time：23:59
 * Email:739115041@qq.com
 * Rxjava 订阅关系
 * 基于Rx的Presenter封装,控制订阅的生命周期
 * unsubscribe() 这个方法很重要，
 * 因为在 subscribe() 之后， Observable 会持有 Subscriber 的引用，
 * 这个引用如果不能及时被释放，将有内存泄露的风险。
 * @param <T extends BaseContract.BaseView>
 * 只允许BaseView及子类的引用
 *
 */
public class RxPresenter<T extends BaseContract.BaseView> implements BaseContract.BasePresenter<T>  {

    // Presenter持有的View
    protected T mView;

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
    }

}
