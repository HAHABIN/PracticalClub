package com.luck.main.fragment.main;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.common.base.WebActivity;
import com.example.common.base.adapter.CommonAdapter;
import com.example.common.base.mvp.BaseMVPFragment;
import com.example.common.bean.HttpItem;
import com.example.common.bean.beanEnum.CategoryEnum;
import com.example.common.bean.beanEnum.HotEnum;
import com.example.common.bean.beanEnum.TypeEnum;
import com.example.common.bean.entity.BannerEntity;
import com.example.common.bean.entity.CategoryEntity;
import com.example.common.bean.entity.HotEntity;
import com.example.common.http.ApiError;
import com.example.common.http.HttpHelper;
import com.example.common.popupwindow.TypePopWindow;
import com.example.common.widget.view.ListEmptyView;
import com.example.common.widget.view.SwipeRecyclerView;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.luck.main.R;
import com.luck.main.R2;
import com.luck.main.adapter.NewsAdapter;
import com.luck.main.adapter.TypeAdapter;
import com.luck.main.contract.HotContract;
import com.luck.main.presenter.HotPresenter;
import com.luck.main.view.HotHeadView;

import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * <一句话功能简述> <功能详细描述>
 *
 * @author HABIN
 * @version 2020/8/25
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class HotFragment extends BaseMVPFragment<HotContract.Presenter> implements HotContract.View {


    @BindView(R2.id.hot_head_view)
    HotHeadView mHotHeadView;
    @BindView(R2.id.swipe_rec)
    SwipeRecyclerView mSwipeRec;
    @BindView(R2.id.ll_top_view)
    LinearLayout mLlTopView;
    @BindView(R2.id.tv_date_type)
    TextView mTvDateType;
    @BindView(R2.id.tv_category_type)
    TextView mTvCategoryType;
    @BindView(R2.id.tv_sort_type)
    TextView mTvSortType;
    @BindView(R2.id.iv_date_type_arrow)
    ImageView mIvDateTypeArrow;
    @BindView(R2.id.iv_category_type_arrow)
    ImageView mIvCategoryTypeArrow;
    @BindView(R2.id.iv_sort_type_arrow)
    ImageView mIvSortTypeArrow;
    @BindView(R2.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout mCollapsingToolbarLayout;
    @BindView(R2.id.appBar_hot)
    AppBarLayout mAppBarHot;
    @BindView(R2.id.srl_hot)
    SwipeRefreshLayout mSrlHot;


    /**
     * 默认文章
     */
    private CategoryEnum categoryEnum = CategoryEnum.Article;

    /**
     * 默认Android
     */
    private TypeEnum typeEnum = TypeEnum.ANDROID;

    /**
     * 默认浏览数
     */
    private HotEnum hotEnum = HotEnum.views;

    private NewsAdapter mAdapter;

    /**
     * 当前页
     */
    protected int mStart = 1;
    /**
     * 每页请求数
     */
    protected int mSize = 10;
    /**
     * 数据类型  热门 随机
     */
    private ArrayList<String> mDateType;
    /**
     * 分类类型  Article | GanHuo | Girl
     */
    private ArrayList<String> mCategoryType;
    /**
     * 热门类型 可接受参数 views（浏览数） | likes（点赞数） | comments（评论数）
     */
    private ArrayList<String> mSortHotType;
    /**
     * 随机类型 可接受参数 Android | iOS | Flutter | Girl
     */
    private ArrayList<String> mSortRandomType;

    private String mCurrCategoryType = CategoryEnum.Article.getType();

    private String mCurrHotType = HotEnum.views.getType();

    private String mCurrRandomType = TypeEnum.All.getType();
    /**
     * 是否是热门
     */
    private boolean isHot = true;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    public void onResume() {
        super.onResume();
        mHotHeadView.startTurning();
        mTvDateType.setText(isHot ? getString(R.string.title_hot) : getString(R.string.string_random));
        mTvCategoryType.setText(CategoryEnum.getNameByType(mCurrCategoryType));
        mTvSortType.setText(isHot ? HotEnum.getNameByType(mCurrHotType) : TypeEnum.getNameByType(mCurrRandomType));
    }

    @Override
    public void onPause() {
        super.onPause();
        mHotHeadView.stopTurning();
    }

    @Override
    protected void initView(View view) {
        mAdapter = new NewsAdapter(getContext());
        mSwipeRec.setEmptyView(new ListEmptyView(getContext()));
        mSwipeRec.setAdapter(mAdapter);
        initRefreshStyle();
        setPopDate();
    }
    private void initRefreshStyle(){
        mSrlHot.setProgressViewEndTarget(false,200);
        //加载颜色是循环播放的，只要没有完成刷新就会一直循环
        mSrlHot.setColorSchemeResources(
                android.R.color.holo_red_light,
                android.R.color.holo_green_light,
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_purple
        );

    }
    @Override
    protected void initListener() {

        mHotHeadView.setHotHeaderViewListener(new HotHeadView.HotHeaderViewListener() {
            @Override
            public void openBanner(BannerEntity.ResultBean item) {
                WebActivity.startActivity(getContext(), item.getUrl());
            }
        });

        mAppBarHot.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                /*向上拉 偏移量为0*/
                if (verticalOffset >= 0) {
                    mSrlHot.setEnabled(true);
                    mSwipeRec.getSwipeRefreshLayout().setEnabled(false);
                } else {
                    mSrlHot.setEnabled(false);
                    mSwipeRec.getSwipeRefreshLayout().setEnabled(true);
                }
            }
        });

        mSrlHot.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mStart = 1;
                RequestData();
                mSwipeRec.getSwipeRefreshLayout().setEnabled(true);
            }
        });

        mSwipeRec.setOnLoadListener(new SwipeRecyclerView.OnLoadListener() {
            @Override
            public void onRefresh() {
                mStart = 1;
                RequestData();
            }

            @Override
            public void onLoadMore() {
                mStart++;
                RequestData();
            }
        });
        mSwipeRec.addLoadMoreView();
        mSwipeRec.setRefreshing(true);

    }

    @Override
    protected void initData() {
        mPresenter.getBanner();

    }

    /**
     * 设置弹窗显示类型数据
     */
    private void setPopDate() {
        /** ----   类型 ----*/
        if (mDateType == null) {
            mDateType = new ArrayList<>();
            mDateType.add(getString(R.string.title_hot));
            mDateType.add(getString(R.string.string_random));
        }
        /** ----   分类 ----*/
        if (mCategoryType == null) {
            mCategoryType = new ArrayList<>();
            mCategoryType.add(CategoryEnum.Article.getName());
            mCategoryType.add(CategoryEnum.GanHuo.getName());
            mCategoryType.add(CategoryEnum.Girl.getName());
        }
        /** ----  随机类型 ---*/
        if (mSortRandomType == null) {
            mSortRandomType = new ArrayList<>();
            mSortRandomType.add(TypeEnum.All.getName());
            mSortRandomType.add(TypeEnum.ANDROID.getName());
            mSortRandomType.add(TypeEnum.IOS.getName());
            mSortRandomType.add(TypeEnum.Flutter.getName());
            mSortRandomType.add(TypeEnum.Girl.getName());
        }

        /** ----  热门类型 ----*/
        if (mSortHotType == null) {
            mSortHotType = new ArrayList<>();
            mSortHotType.add(HotEnum.views.getName());
            mSortHotType.add(HotEnum.likes.getName());
            mSortHotType.add(HotEnum.comments.getName());
        }

    }


    @Override
    protected HotContract.Presenter bindPresenter() {
        return new HotPresenter();
    }


    public void RequestData() {
        if (isHot) {
            mPresenter.getHotData(mCurrHotType, mCurrCategoryType);
        } else {
            mPresenter.getRandom(mCurrCategoryType, mCurrRandomType);
        }

    }


    @Override
    public void taskStarted(HttpHelper.TaskType type) {

    }

    @Override
    public void onSuccess(HttpHelper.TaskType type, HttpItem item) {
        dismissDialog();
        mSwipeRec.stopLoad();
        mSrlHot.setRefreshing(false);
        switch (type) {
            case Banners:
                if (item instanceof BannerEntity) {
                    ArrayList<BannerEntity.ResultBean> data = ((BannerEntity) item).getData();
                    mHotHeadView.setBannerList(data);
                }
                break;
            case Hot:
                if (item instanceof HotEntity) {
                    ArrayList<CategoryEntity.ResultBean> data = ((HotEntity) item).getData();
                    mAdapter.setData(data);
                    mSwipeRec.noMoreData();
                }
                break;
            case Random:
                if (item instanceof CategoryEntity) {
                    ArrayList<CategoryEntity.ResultBean> data = ((CategoryEntity) item).getData();
                    mAdapter.setData(data);
                    mSwipeRec.noMoreData();
                }
                break;
        }
    }


    /**
     * @param types        数据
     * @param typePosition 宿主位置  0 dataType  1 category 2 sort
     * @param tvType       宿主位置文字
     * @param ivArrow      宿主位置指向
     */
    private void popTypeView(ArrayList<String> types, int typePosition, TextView tvType, ImageView ivArrow) {

        TypePopWindow<String> popTeamWindow = new TypePopWindow<>();
        popTeamWindow.setListener(new TypePopWindow.TypePopListener<String>() {
            @Override
            public void onItemClick(int position) {
                switch (typePosition) {
                    case 0:
                        mTvSortType.setText((isHot = position == 0)
                                ? HotEnum.views.getName()
                                : TypeEnum.All.getName());
                        break;
                    case 1:
                        mCurrCategoryType = CategoryEnum.getTypeByName(types.get(position));
                        break;
                    case 2:
                        // 如果是热门类型
                        if (isHot) {
                            mCurrHotType = HotEnum.getTypeByName(types.get(position));
                        } else {
                            mCurrRandomType = TypeEnum.getTypeByName(types.get(position));
                        }
                        break;
                }
                tvType.setText(types.get(position));
                showPleaseDialog();
                RequestData();
            }

            @Override
            public CommonAdapter addAdapter(Activity activity, ArrayList<String> items) {
                return new TypeAdapter(activity, items);
            }

            @Override
            public void dismiss() {
                ivArrow.setSelected(false);
            }
        });
        popTeamWindow.showPop(getActivity(), mLlTopView, types);
    }

    @Override
    public void onSuccess(HttpHelper.TaskType type, JSONObject object) {

    }

    @Override
    public void onFailure(HttpHelper.TaskType type, ApiError e) {
        dismissDialog();
        mSwipeRec.stopLoad();
        Toast.makeText(mActivity, e.getMessage(), Toast.LENGTH_SHORT).show();
    }


    @OnClick({R2.id.ll_type, R2.id.ll_sort_type, R2.id.ll_category_type})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id == R.id.ll_type) {
            popTypeView(mDateType, 0, mTvDateType, mIvDateTypeArrow);
            mIvDateTypeArrow.setSelected(true);
        } else if (id == R.id.ll_sort_type) {
            popTypeView(isHot ? mSortHotType : mSortRandomType, 2, mTvSortType, mIvSortTypeArrow);
            mIvSortTypeArrow.setSelected(true);
        } else if (id == R.id.ll_category_type) {
            popTypeView(mCategoryType, 1, mTvCategoryType, mIvCategoryTypeArrow);
            mIvCategoryTypeArrow.setSelected(true);
        }
    }
}
