package com.cjkzy.cjkzy.supermine;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.cjkzy.cjkzy.supermine.common.Consts;

import java.io.File;

/**
 * Created by CLEVO on 2016/2/21.
 */
public class MainApp extends Application {


    private static SharedPreferences mSpSetting;
    private static SharedPreferences roleSetting;
    @Override
    public void onCreate() {
        super.onCreate();
        initSharedPreferences(getApplicationContext());
//        initImageLoad(getApplicationContext());
//        c.i(getApplicationContext()); //<-广告商接口
    }

//    public static void initImageLoad(Context context){
//        File cacheDir = new File(FileUtils.getCachePath());
//        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder((context))
//                .threadPriority(Thread.NORM_PRIORITY - 2)  //线程池内加载的数量
//                .denyCacheImageMultipleSizesInMemory()
//                .discCacheFileNameGenerator(new Md5FileNameGenerator())
//                .tasksProcessingOrder(QueueProcessingType.LIFO)
//                .discCache(new UnlimitedDiscCache(cacheDir))//自定义缓存路径
//                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
//                .build();// 设置config
//        ImageLoader.getInstance().init(config);
//    }

    //初始化SharePreference
    public void initSharedPreferences(Context context) {
        mSpSetting = PreferenceManager.getDefaultSharedPreferences(context);
        roleSetting = getSharedPreferences("role_setting", 0);
    }
    //初次打开
    public static void setIsFirst(boolean is) {
        SharedPreferences.Editor editor = mSpSetting.edit();
        editor.putBoolean(Consts.SP_IS_FIRST, is).commit();
    }

    //判断是不是第一次打开
    public static boolean isFirst() {
        return mSpSetting.getBoolean(Consts.SP_IS_FIRST, true);
    }

    // 设置当前用户身份
    public static void setCurrentRole(String role){
        SharedPreferences.Editor editor = roleSetting.edit();
        editor.putString(Consts.SP_CURRENT_ROLE,role).commit();
    }

    // 获取当前用户身份
    public static String getCurrentRole() {
        return roleSetting.getString(Consts.SP_CURRENT_ROLE, Consts.ROLE_DRIVER);
    }
}


