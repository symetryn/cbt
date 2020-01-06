/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbt.controller;

import com.cbt.model.Test;
import com.cbt.dao.TestDao;
import com.cbt.utils.Router;
import com.cbt.utils.UserState;
import com.jfoenix.controls.JFXRippler;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Symetryn
 */
public class StudentViewExamController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TilePane examTile;

    /**
     * Initializes the controller class.

     * @param url The url used to resolve relative paths for the root object, or null if the location is not known.
     * @param rb The rb used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        createExamList();

    }

    private void createExamList() {
        try {
            TestDao t = (TestDao) Naming.lookup("rmi://localhost/TestService");
            UserState state = UserState.getInstance();
            ArrayList<Test> testList = t.getTestByLevelSem(state.getUserId(),state.getLevel(), state.getSemester());
            ArrayList<Test> upcomingTestList = t.getAllUpcomingTest();
            testList.forEach((item) -> {
                examTile.getChildren().addAll(createExamItem(item.getId(), item.getTitle(), item.getDate()));
            });

        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(ExamController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private Pane createExamItem(int id, String name, Date date) {

        //dropshadow
        DropShadow dropShadow = new DropShadow();
        dropShadow.setRadius(8.69);
        dropShadow.setOffsetX(1.0);
        dropShadow.setOffsetY(2.0);
        dropShadow.setColor(Color.color(0, 0, 0, 0.16));
        dropShadow.setWidth(20.0);
        dropShadow.setHeight(16.75);
        dropShadow.setSpread(0);
        dropShadow.setBlurType(BlurType.THREE_PASS_BOX);

        //item pane
        Pane p = new Pane();
        p.setStyle("-fx-background-color: white; -fx-background-radius: 10px;-fx-cursor: hand");
        p.setPrefHeight(200);
        p.setPrefWidth(322);
        p.setEffect(dropShadow);

        //pane content
        Label title = new Label();
        title.setText(name);
        title.setLayoutX(20);
        title.setLayoutY(15);
        title.fontProperty();
        title.setStyle("-fx-font-weight: bold;-fx-font-size:20");

        p.getChildren().addAll(title);

        p.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
            Router r = new Router();
            r.routeToStudentInstruction(id, name);
        });

        JFXRippler rippler = new JFXRippler(p);
        rippler.setRipplerFill(Color.valueOf("#000"));

        return rippler;
    }

}
