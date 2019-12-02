/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbt.views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AddQuestionController implements Initializable {
    int count=0;
    int yAxis=85;

    /**
     * Initializes the controller class.
     */
    @FXML
    private ScrollPane scrollpane;
    
    @FXML
    private Pane pane;
    
    @FXML
    private void addOptionClick(ActionEvent e) throws Exception{
        TextField option =new TextField();
        Button drop =new Button("Drop");
        CheckBox correct= new CheckBox();
        
        option.setLayoutX(11);
        option.setLayoutY(yAxis);
        option.setPrefSize(633,42);
        count+=1;
        yAxis+=70;
        option.setId("option"+count);
       
        pane.getChildren().addAll(option,drop,correct);
        scrollpane.setContent(pane);
        scrollpane.setPannable(true);
        
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    
}
