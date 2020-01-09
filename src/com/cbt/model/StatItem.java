package com.cbt.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Model for storing chart data with labels
 * @author Symetryn
 */
public class StatItem implements Serializable {
    private ArrayList<ChartItem> chartList;
    private String [] labelList;
    
    private static final long serialVersionUID = 6L;
    
    public StatItem(){
        chartList= new ArrayList();
    }

    public StatItem( String[] labelList,ArrayList<ChartItem> chartList) {
        this.chartList = chartList;
        this.labelList = labelList;
    }
    
    // getter and setter method for stat item model

    public ArrayList<ChartItem> getChartList() {
        return chartList;
    }

    public void setChartList(ArrayList<ChartItem> chartList) {
        this.chartList = chartList;
    }

    public String[] getLabelList() {
        return labelList;
    }

    public void setLabelList(String[] labelList) {
        this.labelList = labelList;
    }

  
    
    
}

