/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbt.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author User
 */
public class InstructionController implements Initializable {

    /**
     * Initializes the controller class.
     */
    private int testId;
    private String testName;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public void setTestName(String name) {
        this.testName = name;
    }

}
