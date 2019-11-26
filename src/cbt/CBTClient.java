/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbt;

import com.cbt.dao.TestDao;
import com.cbt.dao.UserDao;
import java.net.MalformedURLException;
import java.rmi.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;



/**
 *
 * @author Symetryn
 */
public class CBTClient extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/cbt/views/FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
         try {
//             System.setProperty("java.security.policy", "client.policy");
//if (System.getSecurityManager() == null)
//System.setSecurityManager(new RMISecurityManager());
            
            
//           TestDao test= (TestDao)Naming.lookup("rmi://localhost/TestService");
//            test.saveTest();
            
            
//                System.out.println("hello");
//        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
//            Logger.getLogger(cbt.class.getName()).log(Level.SEVERE, null, ex);
//        } 
         }catch(Exception e){
         System.out.println(e);}
        launch(args); 
//        launch(args);
      
        
    }
    
}
