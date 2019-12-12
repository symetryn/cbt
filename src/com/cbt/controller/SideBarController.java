/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbt.controller;

import com.cbt.utils.Router;
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
public class SideBarController implements Initializable {

    @FXML
    private JFXButton dashboard;
    @FXML
    private JFXButton addQuestion;
    @FXML
    private JFXButton result;
    @FXML
    private JFXButton logout;

//    Router r;
    public SideBarController() {
//        r = new Router();
    }

    /**
     *
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void gotoDashboard(ActionEvent event) {
        Router.routeTo("Dashboard.fxml", event);
    }

    @FXML
    private void gotoExam(ActionEvent event) {
        Router.routeTo("Exam.fxml", event);
    }

    @FXML
    private void gotoResult(ActionEvent event) {
        Router.routeTo("Result.fxml", event);
    }

    @FXML
    private void logout(ActionEvent event) {
        Router.routeTo("SignIn.fxml", event);
    }

}
