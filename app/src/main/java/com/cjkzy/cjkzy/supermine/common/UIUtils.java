package com.cjkzy.cjkzy.supermine.common;

import android.content.Context;

/**
 * Created by CLEVO on 2016/3/3.
 */
public class UIUtils {

    public static  int dp2px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
