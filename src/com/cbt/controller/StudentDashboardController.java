/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbt.controller;

import com.cbt.dao.StatsDao;
import com.cbt.dao.TestDao;
import com.cbt.model.ChartItem;
import com.cbt.model.StatItem;
import com.cbt.model.Test;
import com.cbt.model.User;
import com.cbt.utils.ChartBuilder;
import com.cbt.utils.UserState;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

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
    private ImageView completed;
    @FXML
    private ImageView passed;
    @FXML
    private ImageView failed;
    @FXML
    private ImageView performanceGraph;
    @FXML
    private VBox performancePie;
    @FXML
    private ImageView resultImage1;
    @FXML
    private Label username;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            int userId = UserState.getInstance().getUserId();
            int level = UserState.getInstance().getLevel();
            int semester = UserState.getInstance().getSemester();

            StatsDao s = (StatsDao) Naming.lookup("rmi://localhost/StatService");
            ArrayList<ChartItem> passCount = s.getPassCount(userId);
            ArrayList<ChartItem> failCount = s.getFailedCount(userId);
            ArrayList<ChartItem> totalCount = s.getTotalCount(userId);
            StatItem testMap = s.getUserTestData(userId);

            TestDao t = (TestDao) Naming.lookup("rmi://localhost/TestService");
            ArrayList<Test> testList = t.getTestByLevelSem(userId, level, semester);
            ChartBuilder c = new ChartBuilder();

            completed.setImage(c.build("radialGauge", null, totalCount, 230, 180));
            passed.setImage(c.build("radialGauge", null, passCount, 230, 180));
            failed.setImage(c.build("radialGauge", null, failCount, 230, 180));

            performanceGraph.setImage(c.build("line", testMap.getLabelList(), testMap.getChartList(), 863, 463));

            int count = 1;
            if (testList.isEmpty()) {
                Label l = new Label("No upcoming exams ");
                l.setPadding(new Insets(190,0,0,150));
                performancePie.getChildren().add(l);
            }
            for (Test u : testList) {
                System.out.println("Iterated");
                System.out.println(u.toString());
                Pane p = new Pane();
                p.setStyle("-fx-background-color:#F5F5F5");

                p.setPrefHeight(35);

                Label l = new Label(count + ". " + u.getTitle());
                l.setPadding(new Insets(5));
                count++;
                Label passRate = new Label(u.getDate().toLocalDate().toString());
                passRate.setPadding(new Insets(5));
                passRate.setLayoutX(340);
                p.getChildren().addAll(l, passRate);
                performancePie.getChildren().addAll(p);

            }

        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(StudentDashboardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
