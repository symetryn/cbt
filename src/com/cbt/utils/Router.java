/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbt.utils;

import com.cbt.controller.ViewExamController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 *
 * @author Symetryn
 */
public class Router {

    public static Stage stage;

    public Router() {

    }

    public static void routeTo(String fxml, ActionEvent e) {
        try {
//            Node source = (Node) e.getSource();
//            Window stage = source.getScene().getWindow();
            Parent pane = FXMLLoader.load(new Object() {
            }.getClass().getResource("/com/cbt/views/" + fxml));
            stage.getScene().setRoot(pane);

        } catch (IOException ex) {
            Logger.getLogger(Router.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void routeToViewExam(int testId) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/cbt/views/" + "ViewExam.fxml"));

            Parent root = (Parent) fxmlLoader.load();
            ViewExamController controller
                    = fxmlLoader.<ViewExamController>getController();
            controller.setTest(testId);
            stage.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Router.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
