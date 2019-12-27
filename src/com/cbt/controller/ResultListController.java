/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbt.controller;

import com.cbt.model.Question;
import com.cbt.model.Result;
import com.cbt.dao.TestDao;
import com.cbt.utils.Router;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Symetryn
 */
public class ResultListController implements Initializable {

    @FXML
    private Label userName;

    @FXML
    private TableView<Result> resultTable;

    @FXML
    private TableColumn<Result, String> resultId;

    @FXML
    private TableColumn<Result, String> name;

    @FXML
    private TableColumn<Result, String> testName;

    @FXML
    private TableColumn<Result, String> marks;

    @FXML
    private TableColumn<Result, String> status;
    @FXML
    private TableColumn<Result, String> passMarks;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            TestDao t = (TestDao) Naming.lookup("rmi://localhost/TestService");
            ArrayList<Result> resultList = t.getAllResult();
            System.out.println(resultList.toString());
            resultId.setCellValueFactory(cellData -> {
                String name;
                Result result = cellData.getValue();
                if (result == null) {
                    name = null;
                } else {
                    name = Integer.toString(result.getUser().getUserID());
                }
                return new SimpleStringProperty(name);
            }
            );
            name.setCellValueFactory(cellData -> {
                String name;
                Result result = cellData.getValue();
                if (result == null) {
                    name = null;
                } else {
                    name = result.getUser().getFirstName();
                }
                return new SimpleStringProperty(name);
            });
            testName.setCellValueFactory(cellData -> {
                String name;
                Result result = cellData.getValue();
                if (result == null) {
                    name = null;
                } else {
                    name = result.getTest().getTitle();
                }
                return new SimpleStringProperty(name);
            });
            marks.setCellValueFactory(new PropertyValueFactory<>("marks"));
            passMarks.setCellValueFactory(cellData -> {
                String marks;
                Result result = cellData.getValue();
                if (result == null) {
                    marks = null;
                } else {
                    marks = Integer.toString(result.getTest().getPassMarks());
                    System.out.println(result.getTest().toString());

                }
                return new SimpleStringProperty(marks);
            });
            status.setCellValueFactory(cellData -> {
                String status;
                Result result = cellData.getValue();
                if (result == null) {
                    status = null;
                } else {
                    if (result.getStatus()) {
                        status = "pass";

                    } else {
                        status = "fail";
                    }
                }
                return new SimpleStringProperty(status);
            });
            ObservableList results = FXCollections.observableArrayList(resultList);
            resultTable.setItems(results);
            resultTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {

                Router r = new Router();
                r.routeToAdminResult(newSelection.getId());
            });

        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            Logger.getLogger(ResultListController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
