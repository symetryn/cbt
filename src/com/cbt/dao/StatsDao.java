package com.cbt.dao;

import com.cbt.model.ChartItem;
import com.cbt.model.Result;
import com.cbt.model.StatItem;
import com.cbt.model.Test;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 *
 */
public interface StatsDao extends java.rmi.Remote {

   
    public ArrayList<ChartItem> getTotalStudents() throws RemoteException;
    
    public ArrayList<ChartItem> getTotalExams() throws RemoteException;
    
    public ArrayList<ChartItem> getPassRate() throws RemoteException;
    
    public ArrayList<ChartItem> getUpcomingTests() throws RemoteException;
    
    public StatItem getTestsData() throws RemoteException;
    
    public ArrayList<ChartItem> getTopStudents() throws RemoteException;
    
   

}
