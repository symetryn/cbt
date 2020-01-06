/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbt.controller;

import com.cbt.dao.StatsDao;
import com.cbt.model.ChartItem;
import com.cbt.model.StatItem;
import com.cbt.model.User;
import com.cbt.utils.ChartBuilder;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author User
 */
public class DashboardController implements Initializable {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private ImageView studentImage;

    @FXML
    private ImageView testImage;

    @FXML
    private ImageView resultImage;

    @FXML
    private ImageView statsImage;

    @FXML
    private ImageView performanceGraph;

    @FXML
    private VBox performancePie;

    @FXML
    private Label userName;
    @FXML
    private AnchorPane dfr;
    @FXML
    private Label studentName;

    private void handleButtonAction(ActionEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("Exam.fxml"));
        rootPane.getChildren().setAll(pane);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Platform.runLater(()
                -> {
            try {
                StatsDao s = (StatsDao) Naming.lookup("rmi://localhost/StatService");
                ArrayList<ChartItem> list = s.getTotalStudents();
                ArrayList<ChartItem> list2 = s.getTotalExams();
                ArrayList<ChartItem> list3 = s.getPassRate();
                ArrayList<ChartItem> list4 = s.getUpcomingTests();
                ArrayList<User> topUsers = s.getTopStudents();
                StatItem testMap = s.getTestsData();

                System.out.println(list.toString());
                ChartBuilder c = new ChartBuilder();

                studentImage.setImage(c.build("radialGauge", null, list, 230, 180));
                testImage.setImage(c.build("radialGauge", null, list2, 230, 180));
                resultImage.setImage(c.build("radialGauge", null, list3, 230, 180));
                statsImage.setImage(c.build("radialGauge", null, list4, 230, 180));

               
                performanceGraph.setImage(c.build("bar", testMap.getLabelList(), testMap.getChartList(), 863, 463));


                int count = 1;
                for (User u : topUsers) {
                    System.out.println("Iterated");
                    System.out.println(u.toString());
                    Pane p = new Pane();
                    p.setStyle("-fx-background-color:#F5F5F5");

                    p.setPrefHeight(35);

                    Label l = new Label(count + ". " + u.getFirstName() + " " + u.getLastName());
                    l.setPadding(new Insets(5));
                    count++;
                    Label passRate = new Label(Integer.toString(u.getLevel()) + "%");
                    passRate.setPadding(new Insets(5));
                    passRate.setLayoutX(380);
                    p.getChildren().addAll(l, passRate);
                    performancePie.getChildren().addAll(p);

                }

            } catch (MalformedURLException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NotBoundException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (RemoteException ex) {
                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

}
