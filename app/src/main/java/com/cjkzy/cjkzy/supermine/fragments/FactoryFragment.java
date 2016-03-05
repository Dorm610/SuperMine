package com.cjkzy.cjkzy.supermine.fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.cjkzy.cjkzy.supermine.R;
import com.cjkzy.cjkzy.supermine.data.Order;
import com.tuesda.walker.circlerefresh.CircleRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CLEVO on 2016/3/4.
 */
public class FactoryFragment extends Fragment {

    private CircleRefreshLayout mRefreshLayout;
    private ListView mList;
    private Button mStop;
    public Activity activity;
    private List<Order> orders = null;
    private View v;
    private OrderListAdapter adapter;
    int count = 3;

    public static FactoryFragment getInstance(Activity activity) {
        FactoryFragment sf = new FactoryFragment();
        sf.activity = activity;
        return sf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.main_tab_factory, null);

        mRefreshLayout = (CircleRefreshLayout) v.findViewById(R.id.refresh_layout);
        mList = (ListView) v.findViewById(R.id.list);
        mStop = (Button) v.findViewById(R.id.stop_refresh);


        mStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRefreshLayout.finishRefreshing();
            }
        });

        CircleRefreshLayout.OnCircleRefreshListener refreshListener;
        refreshListener = new CircleRefreshLayout.OnCircleRefreshListener() {
            @Override
            public void refreshing() {
                // do something when refresh starts
//                count++;
//                FactoryFragment.this.addOrder(new Order("测试" + count));
//                mRefreshLayout.finishRefreshing();
//                Log.d("circle", "after Finishing.");
                count++;
                FactoryFragment.this.addOrder(new Order("测试" + count));
                mRefreshLayout.finishRefreshing();
            }


            @Override
            public void completeRefresh() {

                // do something when refresh complete

            }
        };

        mRefreshLayout.setOnRefreshListener(refreshListener);

        initList();
        return v;
    }

    public void addOrder(Order order){
        if (null!=orders){
            orders.add(order);
            initList();
        }
    }


    private void initList()
    {
        if (orders == null){
            orders = new ArrayList<>();
            orders.add(new Order("测试1"));
            orders.add(new Order("测试2"));
            orders.add(new Order("测试3"));
        }

        adapter = new OrderListAdapter(activity, R.layout.order_list_item, orders);

        mList.setAdapter(adapter);
        mList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Because of existence of image button, event will not focus on
                // parents.
                Toast.makeText(activity,"Current order is "+ orders.get(position).toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
