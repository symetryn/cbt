/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbt.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

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
    private ImageView performancePie;

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
//        UserState user = UserState.getInstance();

//        if (user.getName() != null) {
//            userName.setText(user.getName());
//        }
        

//        Platform.runLater(()
//                -> {
//            try {
//                ChartBuilder c = new ChartBuilder();
//                ArrayList<ChartItem> list = new ArrayList<>();
//                list.add(new ChartItem(new Integer[]{50}, "green"));
//
//                ArrayList<ChartItem> list2 = new ArrayList<>();
//                list2.add(new ChartItem(new Integer[]{30}, "red"));
//
//                ArrayList<ChartItem> list3 = new ArrayList<>();
//                list3.add(new ChartItem(new Integer[]{70}, "grey"));
//
//                ArrayList<ChartItem> list4 = new ArrayList<>();
//                list4.add(new ChartItem(new Integer[]{90}, "blue"));
//
////                studentImage.setImage(c.build("radialGauge", null, list, 230, 180));
////                testImage.setImage(c.build("radialGauge", null, list2, 230, 180));
////                resultImage.setImage(c.build("radialGauge", null, list3, 230, 180));
////                statsImage.setImage(c.build("radialGauge", null, list4, 230, 180));
//
//                ArrayList<ChartItem> line = new ArrayList<>();
//                line.add(new ChartItem(new String[]{"dogs"}, new Integer[]{50, 60, 70, 180, 190}, false, "green"));
//                line.add(new ChartItem(new String[]{"cats"}, new Integer[]{100, 200, 300, 400, 500}, false, "red"));
//
//                performanceGraph.setImage(c.build("line", new String[]{"Hi", "Hello", "hi", "fellow", "there"}, line, 863, 463));
//
//                ArrayList<ChartItem> pie = new ArrayList<>();
//                pie.add(new ChartItem(new String[]{"dogs"}, new Integer[]{50, 60, 70, 180, 190}, false, "green"));
//
//                performancePie.setImage(c.build("pie", new String[]{"Hi", "Hello", "hi", "fellow", "there"}, pie, 386, 395));
//
//            } catch (MalformedURLException ex) {
//                Logger.getLogger(DashboardController.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        });

    }

}
