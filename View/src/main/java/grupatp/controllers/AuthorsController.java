/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupatp.controllers;

import grupatp.application.States;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Tomasz Stachura
 * @author Paweł Ograbek
 */
public class AuthorsController implements Initializable {

    @FXML
    private Button back1;
    @FXML
    private Label label1;
    @FXML
    private Label label2;
        
    @FXML
    private void handleChangingScenes(ActionEvent event) throws IOException {
      
        Stage stage = null;
        Parent root = null;
        if (event.getSource()== back1){
            stage = (Stage) back1.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/fxml/menu.fxml"),States.getInstance().getBundle());
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          rb = ResourceBundle.getBundle("grupatp.bundles.resourceList_lang",
                States.getInstance().getBundle().getLocale());
        label1.setText((String)rb.getObject("First"));
        label2.setText((String)rb.getObject("Second"));
     
    }    

    
    
}
