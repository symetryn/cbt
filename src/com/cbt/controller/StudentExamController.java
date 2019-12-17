/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbt.controller;

import com.cbt.bll.Test;
import com.cbt.utils.UserState;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 * FXML Controller class
 *
 * @author Dhruba
 */
public class StudentExamController implements Initializable {

    @FXML
    ScrollPane sp;
    @FXML
    VBox option;
    int count = 1;
    AnchorPane anchor;
    
    Test test;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserState user=UserState.getInstance();
        user.getName();
        

    }
    

    @FXML
    public void questionPanel() {

        for (int i = 0; i < 4; i++) {
            Label options = new Label();
            HBox hbox = new HBox();
            CheckBox check = new CheckBox();
            options.setFont(Font.font("System", 21));
            options.setText("yes");
            hbox.getChildren().addAll(check, options);
            option.setSpacing(30);
            hbox.setSpacing(20);
            hbox.setAlignment(Pos.CENTER);
            option.getChildren().addAll(hbox);
        }

        sp.setContent(option);
        sp.setPannable(true);
    }

}
