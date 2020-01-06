package com.cbt.controller;

import com.cbt.model.Test;
import com.cbt.dao.TestDao;
import com.cbt.utils.Router;
import com.jfoenix.controls.JFXRippler;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * 
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

    @FXML
    private AnchorPane rootPane;

    TestDao t;

    /**
     *  TO initialize the Test
     */
    public ExamController() {
        try {
            t = (TestDao) Naming.lookup("rmi://localhost/TestService");
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(ExamController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Initializes the controller class.
     *
     * @param url The url used to resolve relative paths for the root object, or null if the location is not known.
     * @param rb The rb used to localize the root object, or null if the root object was not localized.
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
        Router.routeTo("AddQuestion.fxml", "Add Question");

    }

    private void createExamList() {
        try {
            ArrayList<Test> testList = t.getAllTest();
            System.out.println(testList.size());
            ArrayList<Test> upcomingTestList = t.getAllUpcomingTest();
            testList.forEach((item) -> {
                examContainer.getChildren().addAll(createExamItem(item.getId(), item.getTitle(), item.getDate()));
            });
            upcomingTestList.forEach((item) -> {
                examUpcomingContainer.getChildren().addAll(createExamItem(item.getId(), item.getTitle(), item.getDate()));
            });
        } catch (RemoteException ex) {
            Logger.getLogger(ExamController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private Pane createExamItem(int id, String name, Date date) {
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
        title.setLayoutX(170);
        title.setLayoutY(30);
        title.fontProperty();
        title.setStyle("-fx-font-weight: bold;-fx-font-size:20");

        Label dateLabel = new Label();
        dateLabel.setText(date.toString());
        dateLabel.setLayoutX(170);
        dateLabel.setLayoutY(55);
        dateLabel.fontProperty();
        dateLabel.setStyle("-fx-font-size:14");

        Button b = new Button("Delete");
        b.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Warning");
            alert.setContentText("Are you sure you want to delete " + name + " ?");
            alert.showAndWait();
            if (alert.getResult().getButtonData() == ButtonData.OK_DONE) {
                try {
                    t.removeTest(id);
                    examContainer.getChildren().clear();
                    examUpcomingContainer.getChildren().clear();
                    createExamList();
                } catch (RemoteException ex) {
                    Logger.getLogger(ExamController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            event.consume();

        });
        b.setLayoutX(170);
        b.setLayoutY(150);
        b.setStyle("-fx-background-color:red;-fx-font-weight: bold;-fx-text-fill:white");

        Random rand = new Random();
        String url = "src/image/";
        String[] images = new String[]{"exam2", "exam3", "exam5"};

        File file = new File(url + images[rand.nextInt(3)] + ".png");

        System.out.println(file.exists());
        Image image = new Image(file.toURI().toString());

        ImageView img = new ImageView();
        img.setLayoutX(5);
        img.setLayoutY(20);
        img.setFitHeight(165);
        img.setFitWidth(250);

        img.setPreserveRatio(true);

        img.setImage(image);

        p.getChildren().addAll(title, dateLabel, img, b);
        p.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            Router r = new Router();
            r.routeToViewExam(id);
        });

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

                searchTile.getChildren().addAll(createExamItem(element.getId(), element.getTitle(), element.getDate()));
            });

        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(ExamController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
