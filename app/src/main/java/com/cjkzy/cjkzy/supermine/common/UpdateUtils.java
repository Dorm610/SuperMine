package com.cjkzy.cjkzy.supermine.common;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.widget.Toast;

import com.cjkzy.cjkzy.supermine.MainApp;
import com.umeng.onlineconfig.OnlineConfigAgent;
import com.umeng.update.UmengDialogButtonListener;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UpdateStatus;

/**
 * Created by CLEVO on 2016/3/2.
 */
public class UpdateUtils {

    /**
     * 检查更新
     * @param main 显示检查更新的页面
     */
    public static void checkUpdate(Activity main){
        // 首次打开不更新
        if (MainApp.isFirst())
        {
            MainApp.setIsFirst(false);
        }
        else
        {
            String value = OnlineConfigAgent.getInstance().getConfigParams(main, Consts.OC_UPDATE_MODE);
            String versionCode = OnlineConfigAgent.getInstance().getConfigParams(main, Consts.OC_UPDATE_FORCE_VERSION);
            int vc;
            try{
                vc = Integer.parseInt(versionCode);
            }catch (NumberFormatException e){
                vc = -1;
            }
            // 当强制更新参数为“f"时，且此时本地版本小于远程要求的最小版本时，才进行强制更新
            if ((vc>0)&&Consts.OC_UPDATE_MODE_FORCED.equals(value)&&(getAppVersionCode(main)<vc)){
                forcedUpdate(main);
            }else{
                normalUpdate(main);
            }
        }
    }

    // 普通更新，可推后
    public static void normalUpdate(Activity main){
        UmengUpdateAgent.update(main);
    }

    // 强制更新，不更新直接退出
    public static void forcedUpdate(final Activity main){

        UmengUpdateAgent.setDialogListener(new UmengDialogButtonListener() {

            @Override
            public void onClick(int status) {
                switch (status) {
                    case UpdateStatus.Update:
                        break;
                    default:
                        //close the app
                        main.finish();
                }
            }
        });

        normalUpdate(main);
    }

    public static int getAppVersionCode(Activity main) {
            String pkName = main.getPackageName();
            int versionCode = 0;
        try {
            versionCode = main.getPackageManager()
                    .getPackageInfo(pkName, 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }
}
