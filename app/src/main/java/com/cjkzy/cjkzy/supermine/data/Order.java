package com.cjkzy.cjkzy.supermine.data;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable
{




    private static final long serialVersionUID = 3738319298768505925L;
    
    private int value = 0;
    
    private Date time;
    
    private String name = "";
    
    private boolean isDone = false;
    
    private int iconId;
    
    private Date endTime;
    
    private String description;
    
    public Order(String name){
        this.name = name;
    }

    public void setValue(int value)
    {
        this.value = value;
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
