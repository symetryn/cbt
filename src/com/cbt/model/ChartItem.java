/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbt.model;

import com.google.gson.Gson;
import java.util.ArrayList;

/**
 *
 * @author Symetryn
 */
public class ChartItem {

    String[] label;
    Integer[] data;
    Boolean fill;
    String borderColor;
    String backgroundColor;

    public ChartItem(Integer[] data, String backgroundColor) {
        this.data = data;
        this.backgroundColor = backgroundColor;
    }

    public ChartItem(String[] label, Integer[] data, Boolean fill, String borderColor) {
        this.label = label;
        this.data = data;
        this.fill = fill;
        this.borderColor = borderColor;
    }

    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
