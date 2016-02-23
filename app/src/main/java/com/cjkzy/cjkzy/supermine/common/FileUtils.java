package com.cjkzy.cjkzy.supermine.common;

import android.os.Environment;

import java.io.File;

/**
 * Created by CLEVO on 2016/2/22.
 */
public class FileUtils {

    /*
     * 获取该应用的根目录
     */
    public static String getAppPath(){
        if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            return "";
        }
        File sdRoot=Environment.getExternalStorageDirectory();
        File file=new File(sdRoot.getAbsolutePath()+"/ScanBook");
        if (!file.exists())
            file.mkdir();
        return file.getAbsolutePath();
    }

    /*
     * 获取缓存图片的位置
     */
    public static String getCachePath(){
        if(!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            return "";
        }
        File file=new File(getAppPath()+"/cache");
        if (!file.exists())
            file.mkdir();
        return file.getAbsolutePath();
    }

}
