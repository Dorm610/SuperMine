package com.cjkzy.cjkzy.supermine.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cjkzy.cjkzy.supermine.R;
import com.cjkzy.cjkzy.supermine.data.Order;

import java.util.List;

public class OrderListAdapter extends ArrayAdapter<Order> {

    private int resourceId;

    public OrderListAdapter(Context context, int textViewResourceId, List<Order> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Order order = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (null == convertView) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.publisherIcon = (ImageView) view.findViewById(R.id.publisher_iv);
            viewHolder.orderTitle = (TextView) view.findViewById(R.id.publish_tv);
            viewHolder.phoneIcon = (ImageView) view.findViewById(R.id.phone_iv);
            viewHolder.publisherName = (TextView) view.findViewById(R.id.name_tv);
            viewHolder.publishedNum = (TextView) view.findViewById(R.id.publishNum_tv);
            viewHolder.publishedTime = (TextView) view.findViewById(R.id.publishTime_tv);
            viewHolder.tagField = (LinearLayout) view.findViewById(R.id.tag_field);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        for (int i = 1; i<=3; i++)
        {
            MyTag tag = new MyTag(getContext(), "标签"+i);
            viewHolder.tagField.addView(tag);
        }

        viewHolder.publisherName.setText(order.toString());

        viewHolder.phoneIcon.setTag(order);
        viewHolder.phoneIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "打电话", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    class ViewHolder {
        ImageView publisherIcon;
        TextView orderTitle;
        TextView publisherName;
        TextView publishedNum;
        LinearLayout tagField;
        TextView publishedTime;
        ImageView phoneIcon;
    }

}
