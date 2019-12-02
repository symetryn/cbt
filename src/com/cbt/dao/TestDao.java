/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbt.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.cbt.bll.Test;
import java.rmi.RemoteException;

/**
 *
 * @author Symetryn
 */
public interface TestDao extends java.rmi.Remote{
    
    public void saveTest(Test test) throws RemoteException;

    public void removeTest(int testId) throws RemoteException;

    public void getAllQuestions(int tId) throws RemoteException;
    
}
