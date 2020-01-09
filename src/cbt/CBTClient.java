
package cbt;

import com.cbt.utils.Router;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CBTClient extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/com/cbt/views/SignIn.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
        Router.stage = stage;

    }

    /**
     * @param args the command line arguments
     * The main method for the client side applicationS
     */
    public static void main(String[] args) {
        launch(args);

    }

}
