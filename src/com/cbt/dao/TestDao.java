package com.cbt.dao;

import com.cbt.model.Result;
import com.cbt.model.Test;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *  Interface defining various methods for test
 * 
 */
public interface TestDao extends java.rmi.Remote {

    public void saveTest(Test test) throws RemoteException;

    public void updateTest(Test test) throws RemoteException;

    public void removeTest(int testId) throws RemoteException;

    public ArrayList<Test> getAllTest() throws RemoteException;

    public ArrayList<Test> getAllUpcomingTest() throws RemoteException;

    public ArrayList<Test> getTestSearch(String searchQuery) throws RemoteException;

    public ArrayList<Test> getTestByLevelSem(int userId,int level, int semester) throws RemoteException;

    public Test getTest(int testId) throws RemoteException;

    public Boolean verifyPassword(int testId, String password) throws RemoteException;

    public Integer saveResult(Result result) throws RemoteException;

    public Result getResult(int resultId) throws RemoteException;

    public ArrayList<Result> getResultByUser(int userId) throws RemoteException;
    
    public ArrayList<Result> getAllResult() throws RemoteException;

}
