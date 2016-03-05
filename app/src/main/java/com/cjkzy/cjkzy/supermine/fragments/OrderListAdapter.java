package com.cjkzy.cjkzy.supermine.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cjkzy.cjkzy.supermine.R;
import com.cjkzy.cjkzy.supermine.data.Order;

import java.util.List;

public class OrderListAdapter extends ArrayAdapter<Order>
{

    private int resourceId;

    public OrderListAdapter(Context context, int textViewResourceId, List<Order> objects)
    {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Order order = getItem(position);
        View view;
        ViewHolder viewHolder;
        if (null == convertView)
        {
            view = LayoutInflater.from(getContext()).inflate(resourceId, null);
            viewHolder = new ViewHolder();
            viewHolder.targetIcon = (ImageView) view.findViewById(R.id.target_icon);
            viewHolder.targetText = (TextView) view.findViewById(R.id.target_name);
            viewHolder.targetDone = (ImageView) view.findViewById(R.id.target_done);
            view.setTag(viewHolder);
        }
        else
        {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.targetText.setText(order.toString());

        viewHolder.targetDone.setTag(order);
        viewHolder.targetDone.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
            }
        });
        return view;
    }

    class ViewHolder
    {
        ImageView targetIcon;
        TextView targetText;
        ImageView targetDone;
    }

}
