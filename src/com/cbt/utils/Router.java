/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cbt.utils;

import com.cbt.bll.Result;
import com.cbt.controller.ExamInstructionController;
import com.cbt.controller.InstructionController;
import com.cbt.controller.ResultController;
import com.cbt.controller.StudentExamController;
import com.cbt.controller.ViewExamController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 *
 * @author Symetryn
 */
public class Router {

    public static Stage stage;

    public Router() {

    }

    public static void routeTo(String fxml) {
        try {

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

    public void routeToStudentInstruction(int testId, String testName) {
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

    public void routeToStudentExam(int testId) {
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

    public void routeToResult(int resultId) {
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

}
