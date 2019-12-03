/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbt.controller;

import com.cbt.utils.Router;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Symetryn
 */
public class DashController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    private Button logout;
    Router r;
    
    public DashController(){
       r = new Router();
    }
    public void handleLogout(ActionEvent e) throws IOException{
       
        r.routeTo("FXMLDocument.fxml",e);
//        Node source = (Node) e.getSource();
//        Window theStage = source.getScene().getWindow();
//        Parent pane = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
//        theStage.getScene().setRoot(pane);
      
    }
    
}
