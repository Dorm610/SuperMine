package com.cjkzy.cjkzy.supermine.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Order implements Serializable
{

    private static final long serialVersionUID = 3738319298768505925L;
    
    private Date time = new Date();

    private OrderState orderState;

    private ArrayList<OrderTag> tagList = new ArrayList<OrderTag>();

    private String name = "";

    private int publishNum = 0;

    private boolean isDone = false;

    private int iconId;

    private Date endTime;

    private String description;

    public Order(String name){
        this.name = name;
    }

    public OrderState getOrderState() {
        return orderState;
    }

    public void setOrderState(OrderState orderState) {
        this.orderState = orderState;
    }

    public ArrayList<OrderTag> getTagList() {
        return tagList;
    }

    public void setTagList(ArrayList<OrderTag> tagList) {
        this.tagList = tagList;
    }

    public int getPublishNum() {
        return publishNum;
    }

    public void setPublishNum(int publishNum) {
        this.publishNum = publishNum;
    }

    public boolean isIsdone()
    {
        return isDone;
    }

    public void setIsdone(boolean isDone)
    {
        this.isDone = isDone;
    }


    public String getName()
    {
        return name;
    }


    public void setName(String name)
    {
        this.name = name;
    }


    public Date getTime()
    {
        return time;
    }


    public void setTime(Date time)
    {
        this.time = time;
    }


    public int getIconId()
    {
        return iconId;
    }


    public void setIconId(int iconId)
    {
        this.iconId = iconId;
    }
    
    public Date getEndTime()
    {
        return endTime;
    }

    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    @Override
    public String toString() {
        return name;
    }
}
