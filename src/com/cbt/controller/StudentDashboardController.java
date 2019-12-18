/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbt.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Symetryn
 */
public class StudentDashboardController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private AnchorPane dfr;
    @FXML
    private ImageView studentImage;
    @FXML
    private ImageView testImage;
    @FXML
    private ImageView resultImage;
    @FXML
    private ImageView performanceGraph;
    @FXML
    private ImageView performancePie;
    @FXML
    private ImageView resultImage1;
    @FXML
    private Label username;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
