package com.cjkzy.cjkzy.supermine.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


@SuppressLint("ValidFragment")
public class SimpleCardFragment extends Fragment {
    private int rID;

    public static SimpleCardFragment getInstance(int id) {
        SimpleCardFragment sf = new SimpleCardFragment();
        sf.rID = id;
        return sf;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(this.rID, null);

        return v;
    }
}