package com.cbt.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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

    Router router;

    public SignInController() {
        router = new Router();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void handleLoginClick(ActionEvent e) {
        try {
            UserDao userImpl = (UserDao) Naming.lookup("rmi://localhost/UserService");

            String result = userImpl.validateLogin(idField.getText(), passwordField.getText());
//            User user = new User();
//            user.setFirstName("rojan");
//            userImpl.registerUser(user);
            System.out.print(result);
            if (result.equals("admin")) {
                router.routeTo("Dashboard.fxml", e);
            } else {
                System.out.print("invalid username or password");

                Alert a = new Alert(AlertType.ERROR, "Invalid username or password", ButtonType.OK);
//              a.setTitle("Authentication failed");
//              
//              a.setContentText("Invalid username or password"); 
                a.show();
            }
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void handleSignUpClick(ActionEvent e) {

        router.routeTo("SignUp.fxml", e);

    }

}
