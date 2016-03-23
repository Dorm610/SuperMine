package com.cjkzy.cjkzy.supermine.data;

import java.io.Serializable;

/**
 * Created by CLEVO on 2016/3/23.
 */
public class OrderTag implements Serializable {
    private String tagText = "";

    public OrderTag(String text) {
        this.tagText = text;
    }
}
