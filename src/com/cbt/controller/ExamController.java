/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbt.controller;

import com.cbt.bll.Test;
import com.cbt.dao.TestDao;
import com.cbt.utils.Router;
import com.jfoenix.controls.JFXButton;
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
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author User
 */
public class ExamController implements Initializable {
    
    @FXML
    HBox examContainer;
    
    @FXML
    ScrollPane examScroll;
    
    @FXML
    HBox examUpcomingContainer;
    
    @FXML
    ScrollPane examUpcomingScroll;
    
    @FXML
    TilePane searchTile;
    
    @FXML
    TextField searchField;
    
    public ExamController() {
        
    }

    /**
     * Initializes the controller class.
     *
     * @params
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        createExamList();
        examScroll.setOnScroll(event -> {
            event.consume();
            if (event.getDeltaX() == 0 && event.getDeltaY() != 0) {
                examScroll.setHvalue(examScroll.getHvalue() - event.getDeltaY() * 2 / this.examContainer.getWidth());
            }
        });
    }
    
    @FXML
    private void gotoAddQuestion(ActionEvent e) {
        Router.routeTo("AddQuestion.fxml", e);
        
    }
    
    private void createExams() {
        
    }
    
    private void createExamList() {
        try {
            TestDao t = (TestDao) Naming.lookup("rmi://localhost/TestService");
            ArrayList<Test> testList = t.getAllTest();
            ArrayList<Test> upcomingTestList = t.getAllUpcomingTest();
            testList.forEach((item) -> {
                examContainer.getChildren().addAll(createExamItem(item.getId(),item.getTitle(), item.getDate()));
            });
            
            upcomingTestList.forEach((item) -> {
                examUpcomingContainer.getChildren().addAll(createExamItem(item.getId(),item.getTitle(), item.getDate()));
            });
            
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(ExamController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    private Pane createExamItem(int id,String name, Date date) {
        System.out.println("created item");
       
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
        
        p.addEventFilter(MouseEvent.MOUSE_CLICKED,event->{
            Router r= new Router();
            r.routeToViewExam(id);
        });
        
//        JFXButton btn= new JFXButton(p);
//                .addAll(p);

        JFXRippler rippler = new JFXRippler(p);
        rippler.setRipplerFill(Color.valueOf("#000"));
       
        return rippler;
    }
    
    @FXML
    private void search(ActionEvent e) {
        try {
            TestDao t = (TestDao) Naming.lookup("rmi://localhost/TestService");
            ArrayList<Test> searchResult = t.getTestSearch(searchField.getText());
            
            searchTile.getChildren().clear();
            
            searchResult.forEach((element) -> {
                System.out.println(element.getTitle());
                
                searchTile.getChildren().addAll(createExamItem(element.getId(),element.getTitle(), element.getDate()));
            });
            
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(ExamController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
