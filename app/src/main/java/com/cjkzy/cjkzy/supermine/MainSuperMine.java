package com.cjkzy.cjkzy.supermine;

import java.util.ArrayList;
import java.util.Random;

import android.graphics.Color;
import android.os.Bundle;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;

import com.cjkzy.cjkzy.supermine.common.Consts;
import com.cjkzy.cjkzy.supermine.common.UIUtils;
import com.cjkzy.cjkzy.supermine.common.UpdateUtils;
import com.cjkzy.cjkzy.supermine.fragments.FactoryFragment;
import com.cjkzy.cjkzy.supermine.fragments.SimpleCardFragment;
import com.cjkzy.cjkzy.supermine.slidingTab.TabEntity;
import com.cjkzy.cjkzy.supermine.slidingTab.ViewFindUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.flyco.tablayout.utils.UnreadMsgUtils;
import com.flyco.tablayout.widget.MsgView;
import com.umeng.analytics.MobclickAgent;

public class MainSuperMine extends AppCompatActivity {

    private static final String ACTIVITY_NAME = "MainSuperMain";
    public static MainSuperMine instance = null;

    private Button roleBtn;

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private String[] mTitles = {"厂矿", "货车", "订阅", "我"};
    private int[] mIconUnselectIds = {
            R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect,
            R.mipmap.tab_contact_unselect, R.mipmap.tab_more_unselect};
    private int[] mIconSelectIds = {
            R.mipmap.tab_home_select, R.mipmap.tab_speech_select,
            R.mipmap.tab_contact_select, R.mipmap.tab_more_select};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private View mDecorView;
    private ViewPager mViewPager;
    private CommonTabLayout mTabLayout_2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        //启动activity时不自动弹出软键盘
        getWindow().setSoftInputMode(LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        instance = this;

        // 检查更新
        /** TODO: 2016/3/2 在线参数没有变化时，友盟的UmengOnlineConfigureListener的
         TODO              onDataReceived只会执行一次,所以最好在每次打开app时，要么提示更新
         TODO              要么取在线参数，交替进行。*/
        UpdateUtils.checkUpdate(this);

        setContentView(R.layout.activity_main_tab);


        // 初始化左上角身份按钮
        roleBtn = (Button) findViewById(R.id.role_button);

        prepareRole();



                mFragments.add(FactoryFragment.getInstance(instance));
                mFragments.add(SimpleCardFragment.getInstance(R.layout.main_tab_choosing));
                mFragments.add(SimpleCardFragment.getInstance(R.layout.main_tab_factory));
                mFragments.add(SimpleCardFragment.getInstance(R.layout.main_tab_setting));


            for (int i = 0; i < mTitles.length; i++) {
                mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
            }

            mDecorView = getWindow().getDecorView();
            mViewPager = ViewFindUtils.find(mDecorView, R.id.vp_2);
            mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

            /** with ViewPager */
            mTabLayout_2 = ViewFindUtils.find(mDecorView, R.id.tl_2);

            tl_2();

            //两位数
            mTabLayout_2.showMsg(0, 55);
            mTabLayout_2.setMsgMargin(0, -5, 5);

            //三位数
            mTabLayout_2.showMsg(1, 100);
            mTabLayout_2.setMsgMargin(1, -5, 5);

            //设置未读消息红点
            mTabLayout_2.showDot(2);
            MsgView rtv_2_2 = mTabLayout_2.getMsgView(2);
            if (rtv_2_2 != null) {
                UnreadMsgUtils.setSize(rtv_2_2, UIUtils.dp2px(this, 7.5f));
            }

            //设置未读消息背景
            mTabLayout_2.showMsg(3, 5);
            mTabLayout_2.setMsgMargin(3, 0, 5);
            MsgView rtv_2_3 = mTabLayout_2.getMsgView(3);
            if (rtv_2_3 != null) {
                rtv_2_3.setBackgroundColor(Color.parseColor("#6D8FB0"));
            }

    }

    Random mRandom = new Random();
    private void tl_2() {
        mTabLayout_2.setTabData(mTabEntities);

        // tab 点击控制view pager滑动
        mTabLayout_2.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);

            }

            @Override
            public void onTabReselect(int position) {
                if (position == 0) {
                    mTabLayout_2.showMsg(0, mRandom.nextInt(100) + 1);
//                    UnreadMsgUtils.show(mTabLayout_2.getMsgView(0), mRandom.nextInt(100) + 1);
                }
            }
        });
        // view pager 滑动控制 tab 选择
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout_2.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager.setCurrentItem(1);
    }


    private void prepareRole() {
        if (MainApp.getCurrentRole().equals(Consts.ROLE_DRIVER)) {
            MainApp.setCurrentRole(Consts.ROLE_DRIVER);
            roleBtn.setText(this.getString(R.string.current_role_driver));
            // TODO change some layout
        } else {
            MainApp.setCurrentRole(Consts.ROLE_OWNER);
            roleBtn.setText(this.getString(R.string.current_role_owner));
            // TODO change some layout
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {  //获取 back键
                Intent intent = new Intent();
                intent.setClass(MainSuperMine.this, Exit.class);
                startActivity(intent);
        }
        return false;
    }


    //设置标题栏右侧按钮的作用
    public void startMainRight(View v) {
        Intent intent = new Intent(MainSuperMine.this, MainTopRightDialog.class);
        startActivity(intent);
        //Toast.makeText(getApplicationContext(), "点击了功能按钮", Toast.LENGTH_LONG).show();
    }

    public void startChat(View v) {      //小黑  对话界面
        Intent intent = new Intent(MainSuperMine.this, OrderDetailActivity.class);
        startActivity(intent);
        //Toast.makeText(getApplicationContext(), "登录成功", Toast.LENGTH_LONG).show();
    }

    public void exitSettings(View v) {                           //退出  伪“对话框”，其实是一个activity
        Intent intent = new Intent(MainSuperMine.this, ExitFromSettings.class);
        startActivity(intent);
    }

    public void startShake(View v) {                                   //手机摇一摇
        Intent intent = new Intent(MainSuperMine.this, ShakeActivity.class);
        startActivity(intent);
    }

    public void changeRole(View v) {
        if (MainApp.getCurrentRole().equals(Consts.ROLE_DRIVER)) {
            MainApp.setCurrentRole(Consts.ROLE_OWNER);
            roleBtn.setText(this.getString(R.string.current_role_owner));
            // TODO change some layout
        } else {
            MainApp.setCurrentRole(Consts.ROLE_DRIVER);
            roleBtn.setText(this.getString(R.string.current_role_driver));
            // TODO change some layout
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        MobclickAgent.onPageStart(ACTIVITY_NAME);
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        MobclickAgent.onPageEnd(ACTIVITY_NAME);
        MobclickAgent.onPause(this);
    }

    /**
     *  谷歌官方认为，ViewPager应该和Fragment一起使用时，此时ViewPager的适配器是FragmentPagerAdapter，
     *  当你实现一个FragmentPagerAdapter,你必须至少覆盖以下方法:
     *  getCount()
     *  getItem()
     *  如果ViewPager没有和Fragment一起，ViewPager的适配器是PagerAdapter，
     *  它是基类提供适配器来填充页面ViewPager内部，当你实现一个PagerAdapter,你必须至少覆盖以下方法:
     *  instantiateItem(ViewGroup, int)
     *  destroyItem(ViewGroup, int, Object)
     *  getCount()
     *  isViewFromObject(View, Object)
     */
    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

}
