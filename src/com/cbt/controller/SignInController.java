package com.cbt.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.cbt.bll.User;
import com.cbt.dao.UserDao;
import com.cbt.utils.Router;
import com.cbt.utils.UserState;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.prefs.Preferences;

/**
 * FXML Controller class
 *
 * @author User
 */
public class SignInController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    TextField idField;

    @FXML
    PasswordField passwordField;

    private Preferences prefs;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void handleLoginClick(ActionEvent e) {

        try {
            UserDao userImpl = (UserDao) Naming.lookup("rmi://localhost/UserService");

            User user = userImpl.validateLogin(idField.getText(), passwordField.getText());
            if (user != null) {
                System.out.println(user.getFirstName());
                UserState.createInstance(user.getFirstName(), user.getUserID(), user.getLevel(), user.getSemester());
                System.out.println(user.getRole());
                if (user.getRole().equals("admin")) {
                    System.out.println("admin here");
                    Router.routeTo("Dashboard.fxml");
                    
                } else {
                    Router.routeTo("StudentDashboard.fxml");
                }
            } else {
                System.out.print("invalid username or password");

                Alert a = new Alert(AlertType.ERROR, "Invalid username or password", ButtonType.OK);

                a.show();
            }
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void handleSignUpClick(ActionEvent e) {
//        setPreference();

        Router.routeTo("SignUp.fxml");

    }

//    public void setPreference(User u) {
//        // This will define a node in which the preferences can be stored
//        prefs = Preferences.userRoot().node(this.getClass().getName());
//        int userId;
//        String firstName;
//        String lastName;
//        int semester;
//        int level;
//        String lastn=Name;
//          
//        
//        
////        prefs.putInt();
////        prefs.putString(firstName,u.getFirstName());
//
////        prefs.setInt(userId,)
//        // now set the values
////        prefs.putBoolean(ID1, false);
////        prefs.put(ID2, "Hello Europa");
////        prefs.putInt(ID3, 45);
////
////        // Delete the preference settings for the first value
////        prefs.remove(ID1);
////
////        // First we will get the values
////        // Define a boolean value
////        System.out.println(prefs.getBoolean(ID1, true));
////        // Define a string with default "Hello World
////        System.out.println(prefs.get(ID2, "Hello World"));
////        // Define a integer with default 50
////        System.out.println(prefs.getInt(ID3, 50));
//
//    }
}
