/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbt.model;

import com.google.gson.Gson;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Dhruba
 */
public class ChartItem implements Serializable{

    String[] label;
    Integer[] data;
    Boolean fill;
    String borderColor;
    String backgroundColor;
    
     private static final long serialVersionUID = 5L;

    /**
     *
     * @param data info to represent in chart
     * @param backgroundColor color for the chart
     */
    public ChartItem(Integer[] data, String backgroundColor) {
        this.data = data;
        this.backgroundColor = backgroundColor;
    }

    /**
     *
     * @param label string array of label
     * @param data info to create chart
     * @param fill color to be filled in the chart
     * @param borderColor border color of the chart
     */
    public ChartItem(String[] label, Integer[] data, Boolean fill, String borderColor) {
        this.label = label;
        this.data = data;
        this.fill = fill;
        this.borderColor = borderColor;
    }
// overriding the toString method
    @Override
    public String toString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

}
