/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package layout;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author User
 */
public class DashboardController implements Initializable {
    
    @FXML
    private AnchorPane rootPane;
    
     @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        
       AnchorPane pane =  FXMLLoader.load(getClass().getResource("Exam.fxml"));
       rootPane.getChildren().setAll(pane);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
