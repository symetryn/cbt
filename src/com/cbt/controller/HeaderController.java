/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbt.controller;

import com.cbt.utils.Router;
import com.cbt.utils.UserState;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 *
 */
public class HeaderController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    Label pageTitle;

    @FXML
    Label userName;

    /**
     *
     * @param title To accept the name of the page title
     */
    public void setPageTitle(String title) {

        System.out.println("title set" + title);
    }
     /**
     *
     * @param url The url used to resolve relative paths for the root object, or null if the location is not known.
     * @param rb The rb used to localize the root object, or null if the root object was not localized.
     *
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        System.out.println("header called");
        pageTitle.setText(Router.currentPage);
        userName.setText(UserState.getInstance().getName());

    }

}
