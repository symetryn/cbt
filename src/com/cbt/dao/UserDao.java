/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbt.dao;

import com.cbt.model.User;
import java.rmi.RemoteException;

/**
 *  Interface defining method to validate the user
 *
 */
public interface UserDao extends java.rmi.Remote {
     User validateLogin(String Username,String Password)throws RemoteException;
     
     void registerUser(User user)throws RemoteException;
}
