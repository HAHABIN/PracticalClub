package com.example.common.base;

/**
 *MVPFragment基类
 * @param <T extends BaseContract.BasePresenter>
 * 只允许BasePresenter及子类的引用
 *
 */
public abstract class BaseMVPFragment<T extends BaseContract.BasePresenter> extends BaseFragment
        implements BaseContract.BaseView{

    protected T mPresenter;

    protected abstract T bindPresenter();

    @Override
    protected void processLogic(){
        mPresenter = bindPresenter();
        mPresenter.attachView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }
}
