/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbt;

import com.cbt.bll.Answer;
import com.cbt.bll.Question;
import com.cbt.bll.Test;
import com.cbt.dao.TestDao;
import com.cbt.dao.UserDao;
import java.net.MalformedURLException;
import java.rmi.*;
import java.util.ArrayList;
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
        Parent root = FXMLLoader.load(getClass().getResource("/com/cbt/views/SignIn.fxml"));
        
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
            
            
           TestDao test= (TestDao)Naming.lookup("rmi://localhost/TestService");
          
           
           ArrayList<Question> qList =new ArrayList<Question>();
           Question q= new Question();
           
           ArrayList<Answer> aList =new ArrayList<Answer>();
           
           
           Answer a= new Answer("test answer",true);
           Answer a2= new Answer("test answer",true);
           Answer a3= new Answer("test a",true);
           
           aList.add(a);
           aList.add(a2);
           aList.add(a3);
           
           q.setAnswers(aList);
           qList.add(q);
           qList.add(q);
           
           Test t = new Test();
           t.setTitle("first test");
           t.setQuestions(qList);
           
           test.saveTest(t);
             launch(args); 
            
//                System.out.println("hello");
//        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
//            Logger.getLogger(cbt.class.getName()).log(Level.SEVERE, null, ex);
//        } 
         }catch(Exception e){
         System.out.println(e);}
       
//        launch(args);
      
        
    }
    
}
