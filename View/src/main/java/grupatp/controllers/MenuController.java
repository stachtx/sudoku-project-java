/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package grupatp.controllers;
 
import grupatp.application.Difficulty;
import grupatp.application.States;
import java.io.IOException;

import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import org.slf4j.LoggerFactory;


//import grupatp.model.Difficulty.diffEnum;

/**
 * FXML Controller class
 *
 * @author Tomasz Stachura
 * @author Paweł Ograbek
 */
public class MenuController implements Initializable {

    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(MenuController.class);
    
    @FXML
    private Button start;
    @FXML
    private Button authors;
    @FXML
    private Button pl;
    @FXML
    private Button eng;
    
    
    @FXML
    private void handleButtonEasy(ActionEvent event) {
        States.getInstance().setDifficulty(Difficulty.Easy);

    }

    @FXML
    private void handleButtonMedium(ActionEvent event) {

        States.getInstance().setDifficulty(Difficulty.Medium);

    }

    @FXML
    private void handleButtonHard(ActionEvent event) {

        States.getInstance().setDifficulty(Difficulty.Medium);

    }

      @FXML
    private void handleButtonPL(ActionEvent event) {
        changeLanguage(new Locale("pl", "PL"), event);

    }

    @FXML
    private void handleButtonEng(ActionEvent event) {
        changeLanguage(new Locale("en", "EN"), event);

    }
    
    
    private void changeLanguage(Locale locale, ActionEvent event) {
        try {
            States.getInstance().setBundle(ResourceBundle.getBundle("bundles.lang", locale));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/menu.fxml"), ResourceBundle.getBundle("bundles.lang", locale));
            Parent parent = loader.load();
            Scene sudokuBoardScene = new Scene(parent);
            Stage primaryStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            primaryStage.setScene(sudokuBoardScene);
            primaryStage.show();

        } catch (IOException ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
    @FXML
    private void handleChangingScenes(ActionEvent event) throws IOException {

        Stage stage = null;
        Parent root = null;
        FXMLLoader loader=new FXMLLoader();
        
       
        if (event.getSource() == start) {
            //get reference to the button's stage         
            stage = (Stage) start.getScene().getWindow();
            //load up OTHER FXML docume
             root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/game.fxml"), States.getInstance().getBundle());
            
             
        }else if (event.getSource()== authors){
            stage = (Stage) authors.getScene().getWindow();
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/authors.fxml"), States.getInstance().getBundle());
        }
        //create a new scene with root and set the stage
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}
