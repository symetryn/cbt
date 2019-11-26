/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbt.utils;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Window;

/**
 *
 * @author Symetryn
 */
public class Router {
    public void routeTo(String fxml,ActionEvent e){
        try {
            Node source = (Node) e.getSource();
            Window theStage = source.getScene().getWindow();
            Parent pane = FXMLLoader.load(getClass().getResource("/com/cbt/views/"+fxml));
            theStage.getScene().setRoot(pane);
        } catch (IOException ex) {
            Logger.getLogger(Router.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
