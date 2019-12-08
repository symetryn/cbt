/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbt.controller;

import com.cbt.bll.User;
import com.cbt.dao.UserDao;
import com.cbt.utils.Router;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Window;

/**
 *
 * @author Symetryn
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private Button login;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
        try {
            UserDao userImpl= (UserDao)Naming.lookup("rmi://localhost/UserService");
            String result=userImpl.validateLogin("1720971", "hash");
            User user = new User();
            user.setFirstName("rojan");
            userImpl.registerUser(user);
            System.out.print(result);
            if(result.equals("student")){
                 Router router= new Router();
                 router.routeTo("dash.fxml",event);
            }
        } catch (NotBoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (RemoteException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
