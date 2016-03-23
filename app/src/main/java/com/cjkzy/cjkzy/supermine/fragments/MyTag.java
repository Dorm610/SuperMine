package com.cjkzy.cjkzy.supermine.fragments;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cjkzy.cjkzy.supermine.R;

/**
 * Created by CLEVO on 2016/3/22.
 */
public class MyTag extends TextView {

    public MyTag(Context context, String text) {
        super(context);
        this.setTextColor(ContextCompat.getColor(getContext(), R.color.tag_blue));
        initMyTag();
        this.setText(text);
    }

    public MyTag(Context context) {
        super(context);
        this.setTextColor(ContextCompat.getColor(getContext(), R.color.tag_blue));
        initMyTag();
        this.setText("测试");
    }

    public MyTag(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MyTagView);
        int color = ta.getColor(R.styleable.MyTagView_rect_color, ContextCompat.getColor(getContext(), R.color.tag_blue));
        this.setTextColor(color);
        ta.recycle();

        initMyTag();
        this.setText("test");
    }

    private void initMyTag() {
        this.setPadding(5, 0, 5, 0);
        this.setGravity(Gravity.CENTER);
        this.setBackgroundResource(R.drawable.tag_bg);
        this.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, 0, 5, 0);
        this.setLayoutParams(lp);
    }

}
