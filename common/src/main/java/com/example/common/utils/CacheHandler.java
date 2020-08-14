package com.example.common.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;


import com.example.common.R;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Date;

public class CacheHandler {
	
	/**
	 * 获取存储的路径， 如果sdcard不存在， 则存储在手机内存空间
	 */
	public static File getCacheDir(Context context){
		File sdDir = null;
		if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			//sdDir = Environment.getExternalStorageDirectory();
			// 存储媒体已经挂载，并且挂载点可读/写
			sdDir = context.getExternalFilesDir("Caches");
		}
		else{
			sdDir = context.getCacheDir();
		}
		File cacheDir = new File(sdDir, context.getResources().getString(R.string.app_name));
		if(!cacheDir.exists()){
			cacheDir.mkdirs();
		}
		return cacheDir;
	}

	/**
	 * 图片缓存目录 
	 */
	public static File getImageCacheDir(Context context){
		File cacheDir = new File(getCacheDir(context), "image");
		if(!cacheDir.exists()){
			cacheDir.mkdirs();
		}
		return cacheDir;
	}

	/**
	 * 保存adver图片目录
	 * unuse
	 */
	public static File getSaveAdverCacheDir(Context context){
		File cacheDir = new File(getCacheDir(context), "adver");
		if(!cacheDir.exists()){
			cacheDir.mkdirs();
		}
		return cacheDir;
	}
	
	/**
	 * 保存图片目录
	 * unuse
	 */
	public static File getSaveImageCacheDir(Context context){
		File cacheDir = new File(getCacheDir(context), "saveimage");
		if(!cacheDir.exists()){
			cacheDir.mkdirs();
		}
		return cacheDir;
	}

	/**
	 * 保存web图片目录
	 */
	public static File getSaveWebImageCacheDir(Context context){
		File cacheDir = new File(getCacheDir(context), "saveWebImage");
		if(!cacheDir.exists()){
			cacheDir.mkdirs();
		}
		return cacheDir;
	}

	/**
	 * 保存web 应用缓存图片目录
	 */
	public static File getSaveWebAppCacheDir(Context context){
		File cacheDir = new File(getCacheDir(context), "saveWebAppCache");
		if(!cacheDir.exists()){
			cacheDir.mkdirs();
		}
		return cacheDir;
	}

	/**
	 * 保存课件目录
	 * unuse
	 */
	public static File getCoursewareCacheDir(Context context){
		File cacheDir = new File(getCacheDir(context), "courseware");
		if(!cacheDir.exists()){
			cacheDir.mkdirs();
		}
		return cacheDir;
	}

	/**
	 * 保存在线教学视频目录
	 * unuse
	 */
	public static File getOnlineTeachingCacheDir(Context context){
		File cacheDir = new File(getCacheDir(context), "video");
		if(!cacheDir.exists()){
			cacheDir.mkdirs();
		}
		return cacheDir;
	}

	/**
	 * user save img
	 */
	public static File getUserSaveImageCacheDir(Context context){
		File cacheDir = new File(getCacheDir(context), "hsejSaveImg");
		if(!cacheDir.exists()){
			cacheDir.mkdirs();
		}
		return cacheDir;
	}
	
	public static File getCameraDir(Context context){
		File cacheDir = new File(getCacheDir(context), "camera");
		if(!cacheDir.exists()){
			cacheDir.mkdirs();
		}
		return cacheDir;
	}
	
	public static File getCameraImgPath(Context context){
		File file = new File(getCameraDir(context), new Date().getTime() + ".jpg");
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return file;
	}

	//cache size
	public static long getFolderSize(File file){
		long size = 0;
		File[] fileList = file.listFiles();
		for (int i = 0; i < fileList.length; i++){
			if (fileList[i].isDirectory()){
				size = size + getFolderSize(fileList[i]);
			}
			else {
				size = size + fileList[i].length();
			}
		}
		return size;
	}

	@SuppressLint("UseValueOf")
	public static String getCacheSizeMb(long size){
		if(size == 0){
			return "0MB";
		}
		//	DecimalFormat df = new DecimalFormat("###.##");
		DecimalFormat df = new DecimalFormat("0.00");
		float f = ((float) (size) / (float) (1024 * 1024));
		return df.format(new Float(f).doubleValue())+ "MB";
	}

	/**
	 * 根据路径删除本地目录及里面的文件
	 * @param path
	 * @return
	 */
	public static boolean deleteDirAndFile(String path){
		if(TextUtils.isEmpty(path)){
			return false;
		}
		boolean success = true ;
		File file = new File(path) ;
		if(file.exists()){
			File[] list = file.listFiles() ;
			if(list != null){
				int len = list.length ;
				for(int i = 0 ; i < len ; ++i){
					if(list[i].isDirectory()){
						deleteDirAndFile(list[i].getPath()) ;
					}
					else {
						if(!list[i].getAbsolutePath().contains("nomedia")){
							boolean ret = list[i].delete() ;
							if(!ret){
								success = false ;
							}
						}
					}
				}
			}
		}
		else{
			success = false ;
		}
		if(success){
			//file.delete() ;
		}
		return success ;
	}
}
