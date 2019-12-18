package com.cbt.dao;

import com.cbt.bll.Result;
import com.cbt.bll.Test;
import java.rmi.RemoteException;
import java.util.ArrayList;

/**
 *
 * @author Symetryn
 */
public interface TestDao extends java.rmi.Remote {

    public void saveTest(Test test) throws RemoteException;

    public void updateTest(Test test) throws RemoteException;

    public void removeTest(int testId) throws RemoteException;

    public ArrayList<Test> getAllTest() throws RemoteException;

    public ArrayList<Test> getAllUpcomingTest() throws RemoteException;

    public ArrayList<Test> getTestSearch(String searchQuery) throws RemoteException;

    public ArrayList<Test> getTestByLevelSem(int level, int semester) throws RemoteException;

    public Test getTest(int testId) throws RemoteException;

    public Boolean verifyPassword(int testId, String password) throws RemoteException;

    public Integer saveResult(Result result) throws RemoteException;

    public Result getResult(int resultId) throws RemoteException;
}
