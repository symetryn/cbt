/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbt.controller;

import com.cbt.dao.TestDao;
import com.cbt.utils.Router;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

/**
 * FXML Controller class
 *
 * @author Dhruba
 */
public class ExamInstructionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private int testId;
    private String testName;

    @FXML
    Label testLabel;

    @FXML
    PasswordField passwordField;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Platform.runLater(() -> {
            testLabel.setText(testName);
        });
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public void setTestName(String name) {
        this.testName = name;
    }

    @FXML
    private void verifyPassword() {
        try {
            TestDao t = (TestDao) Naming.lookup("rmi://localhost/TestService");
            Boolean result = t.verifyPassword(testId, passwordField.getText());
            if (result) {
                Router r = new Router();
                r.routeToStudentExam(testId);
            } else {
                Alert msg = new Alert(Alert.AlertType.ERROR, "Incorrect password", ButtonType.OK);
                msg.show();
            }

        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(ExamInstructionController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
