package com.example.common.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.example.common.dialog.DialogPhotoView;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.permissions.Permission;
import com.luck.picture.lib.permissions.RxPermissions;


import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * <选中拍照相片工具> <功能详细描述>
 *
 * @author HABIN
 * @version 2020/6/30
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class PictureSelectorUtils {

    private DialogPhotoView mDialog;

    private final WeakReference<Activity> mActivity;
    private final WeakReference<Fragment> mFragment;

    private PictureSelectorUtils(Activity activity) {
        this(activity, null);
    }

    private PictureSelectorUtils(Fragment fragment) {
        this(fragment.getActivity(), fragment);
    }

    private PictureSelectorUtils(Activity activity, Fragment fragment) {
        mActivity = new WeakReference<>(activity);
        mFragment = new WeakReference<>(fragment);
    }



    public static PictureSelectorUtils create(Activity activity) {
        return new PictureSelectorUtils(activity);
    }

    public static PictureSelectorUtils create(Fragment fragment) {
        return new PictureSelectorUtils(fragment);
    }

    /**
     * @return Activity.
     */
    @Nullable
    Activity getActivity() {
        return mActivity.get();
    }

    /**
     * @return Fragment.
     */
    @Nullable
    Fragment getFragment() {
        return mFragment != null ? mFragment.get() : null;
    }

    /**
     * 头像选择,包括裁剪以及压缩
     */
    public void openForHeaderInActivity() {
        openDialogInActivity(1, null, true, true);
    }

    /**
     * @param maxSelectNum 最大张数
     * @param selectList   已选择的图片
     * @param crop         是否裁剪
     */
    @SuppressLint("CheckResult")
    public void openDialogInActivity(final int maxSelectNum, final List<LocalMedia> selectList, final boolean crop, final boolean isHeader) {
        RxPermissions rxPermission = new RxPermissions(mActivity.get());
        rxPermission.requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
                .subscribe(new Consumer<Permission>() {
                    @Override
                    public void accept(Permission permission) {
                        if (permission.granted) {// 用户已经同意该权限
                            showDialog(new DialogPhotoView.onClickListener() {
                                @Override
                                public void ToPhotoGraph(DialogPhotoView dialog) {
                                    takePhoto(maxSelectNum, selectList, crop, isHeader);
                                }

                                @Override
                                public void ToPhotoAlbum(DialogPhotoView dialog) {
                                    openAlbum(maxSelectNum, selectList, crop, isHeader);
                                }

                                @Override
                                public void cancelClick(DialogPhotoView dialog) {

                                }
                            });

                        } else {
                            Toast.makeText(mActivity.get(), "拒绝", Toast.LENGTH_SHORT).show();
                        }

                    }
                });

    }


    /**
     *
     * 单独拍照
     * @param maxSelectNum  最大图片选择数量
     * @param selectList 已选图片列表 不传设置null
     * @param crop 是否裁剪
     * @param isHeader 是否是头像
     */
    private void takePhoto(int maxSelectNum, List<LocalMedia> selectList, boolean crop, boolean isHeader) {
        PictureSelector.create(mActivity.get()).openCamera(PictureMimeType.ofImage())// 单独拍照，也可录像或也可音频 看你传入的类型是图片or视频
                .maxSelectNum(maxSelectNum)// 最大图片选择数量
                .selectionMedia(selectList)// 是否传入已选图片
                .enableCrop(crop)
                .compress(true)
                .withAspectRatio(isHeader?1:5,isHeader?1:3)//int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .scaleEnabled(true)//裁剪是否可放大缩小图片 true or false
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .previewImage(false)// 是否可预览图片
                .circleDimmedLayer(isHeader)
                .showCropFrame(!isHeader)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                .showCropGrid(!isHeader)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }


    /**
     * 进入相册
     * @param maxSelectNum  最大图片选择数量
     * @param selectList 已选图片列表 不传设置null
     * @param crop 是否裁剪
     * @param isHeader 是否是头像
     */
    private void openAlbum(int maxSelectNum, List<LocalMedia> selectList, boolean crop, boolean isHeader) {
        PictureSelector.create(mActivity.get()).openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .maxSelectNum(maxSelectNum)// 最大图片选择数量
                .selectionMedia(selectList)// 是否传入已选图片
                .enableCrop(crop)
                .withAspectRatio(isHeader?1:5,isHeader?1:3)//int 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .scaleEnabled(true)//裁剪是否可放大缩小图片 true or false
                .compress(true)
                .isCamera(false)// 是否显示拍照按钮
                .minimumCompressSize(100)// 小于100kb的图片不压缩
                .previewImage(false)// 是否可预览图片
                .circleDimmedLayer(isHeader)
                .showCropFrame(!isHeader)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                .showCropGrid(!isHeader)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                .isDragFrame(false)// 是否可拖动裁剪框(固定)
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }


    /**
     * 弹出选择窗
     * @param listener 显示监听
     * @return
     */
    private DialogPhotoView showDialog(DialogPhotoView.onClickListener listener) {
        if (mDialog == null) {
            mDialog = new DialogPhotoView(getActivity(),listener);
        }
        if (!getActivity().isFinishing()) {
            mDialog.show();
        }
        return mDialog;
    }




}
