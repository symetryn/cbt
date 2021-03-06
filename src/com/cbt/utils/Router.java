/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbt.utils;

import com.cbt.controller.ExamInstructionController;
import com.cbt.controller.HeaderController;
import com.cbt.controller.ResultController;
import com.cbt.controller.StudentExamController;
import com.cbt.controller.ViewExamController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;


public class Router {

    /**
     *  global stage object
     */
    public static Stage stage;

    /**
     * current page title
     */
    public static String currentPage = "unavailable";

    /**
     * if routing is allowed
     */
    public static boolean routeEnabled=true;

    
    public Router() {

    }

    /**
     *  Route to given fxml page
     * @param fxml fxml file name
     * @param pageTitle screen title to be used
     */
    public static void routeTo(String fxml, String pageTitle) {
        if (!routeEnabled) {
            return;
        }
        try {
            currentPage = pageTitle;
            Parent pane = FXMLLoader.load(new Object() {
            }.getClass().getResource("/com/cbt/views/" + fxml));
            stage.getScene().setRoot(pane);
            System.out.println("Routing to" + fxml);

        } catch (IOException ex) {
            Logger.getLogger(Router.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setHeader(String headerName) {
        if (!routeEnabled) {
            return;
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/cbt/views/" + "header.fxml"));

            Parent root = (Parent) fxmlLoader.load();
            HeaderController controller
                    = fxmlLoader.<HeaderController>getController();
            controller.setPageTitle(headerName);
        } catch (IOException ex) {
            Logger.getLogger(Router.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * route to view exam
     * @param testId id to be passed to view particular exam
     */
    public void routeToViewExam(int testId) {
        if (!routeEnabled) {
            return;
        }
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

    /**
     * route to student exam instruction
     * @param testId id to be passed to view particular exam
     * @param testName name to be passed to view particular exam
     */
    public void routeToStudentInstruction(int testId, String testName) {
        if (!routeEnabled) {
            return;
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/cbt/views/" + "examInstruction.fxml"));

            Parent root = (Parent) fxmlLoader.load();
            ExamInstructionController controller
                    = fxmlLoader.<ExamInstructionController>getController();
            controller.setTestId(testId);
            controller.setTestName(testName);
            stage.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Router.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * routes to student exam with testId
     * @param testId id to be passed to view particular exam
     */
    public void routeToStudentExam(int testId) {
        if (!routeEnabled) {
            return;
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/cbt/views/" + "StudentExam.fxml"));

            Parent root = (Parent) fxmlLoader.load();
            StudentExamController controller
                    = fxmlLoader.<StudentExamController>getController();
            controller.setTestId(testId);
            stage.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Router.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *routes to result with resultId
     * @param resultId
     */
    public void routeToResult(int resultId) {
        if (!routeEnabled) {
            return;
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/cbt/views/" + "Result.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            ResultController controller
                    = fxmlLoader.<ResultController>getController();
            controller.setResultId(resultId);
            stage.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Router.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * route to admin result with resultId
     * @param resultId id to be passed to view particular result
     */
    public void routeToAdminResult(int resultId) {
        if (!routeEnabled) {
            return;
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/cbt/views/" + "AdminResult.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            ResultController controller
                    = fxmlLoader.<ResultController>getController();
            controller.setResultId(resultId);
            stage.getScene().setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Router.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
