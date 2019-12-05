/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbt.controller;

import com.cbt.utils.Router;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ExamController implements Initializable {

    Router r;
    
     public ExamController() {
         r= new Router();
    }
    
    /**
     * Initializes the controller class.
     * @params
     * 
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    
    @FXML
    private void gotoAddQuestion(ActionEvent e){
        r.routeTo("AddQuestion.fxml",e);
       
    }

   
    
}
