/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbt.controller;

import com.cbt.utils.Router;
import com.cbt.utils.UserState;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Symetryn
 */
public class StudentSidebarController implements Initializable {

    @FXML
    private JFXButton dashboard;
    @FXML
    private JFXButton exam;
    @FXML
    private JFXButton result;
    @FXML
    private JFXButton logout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void gotoDashboard(ActionEvent event) {
         Router.routeTo("StudentDashboard.fxml","Dashboard");
    }

    @FXML
    private void gotoExam(ActionEvent event) {
        Router.routeTo("StudentViewExam.fxml","My Exams");
    }

    @FXML
    private void gotoResult(ActionEvent event) {
        Router.routeTo("StudentViewResult.fxml","My Results");
    }

    @FXML
    private void logout(ActionEvent event) {
         UserState instance = UserState.getInstance();
        if (instance != null) {
            instance.resetState();
        }
        Router.routeTo("SignIn.fxml","");
    }

}
