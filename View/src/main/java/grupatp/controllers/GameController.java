package grupatp.controllers;

import grupatp.exceptions.CharForbiddenException;
import grupatp.exceptions.TooLongCharException;
//import grupatp.exceptions.LoadException;
//import grupatp.exceptions.SaveException;
import grupatp.application.States;
import grupatp.exceptions.JdbcException;
import grupatp.model.BacktrackingSudokuSolver;
import grupatp.model.SudokuBoard;
import grupatp.model.SudokuSolver;
import grupatp.serialization.Dao;
//import grupatp.serialization.FileSudokuBoardDao;
import grupatp.serialization.JdbcSudokuBoardDao;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.adapter.JavaBeanIntegerPropertyBuilder;
import javafx.beans.value.ObservableValue;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Tomasz Stachura
 * @author Paweł Ograbek
 */
public class GameController implements Initializable {

    private final static org.slf4j.Logger logger = LoggerFactory.getLogger(GameController.class);
    private TextField[][] textFields;
    private final SudokuSolver sudokuSolver = new BacktrackingSudokuSolver();
    private SudokuBoard sudokuFilled = null;
    private SudokuBoard sudokuWithGaps = new SudokuBoard();

    @FXML
    private Label label;

    @FXML
    private Button back;

    @FXML
    private GridPane gridPane;

    @FXML
    void handleButtonCheck(ActionEvent event) {
        boolean flag = true;
        SudokuBoard tmp = new SudokuBoard();
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (!textFields[x][y].getText().equals("")) {
                    if (!(sudokuFilled.getBoard(x, y) == Integer.parseInt(textFields[x][y].getText()))) {
                        flag = false;
                        break;
                    }
                } else {
                    flag = false;
                    break;
                }
            }
        }
        if (flag) {
            label.setText(States.getInstance().getBundle().getString("label.win"));
        } else {
            label.setText(States.getInstance().getBundle().getString("label.lose"));
        }

    }

    @FXML
    void handleChangingScenes(ActionEvent event) throws IOException {
        Stage stage = null;
        Parent root = null;
        if (event.getSource() == back) {
            stage = (Stage) back.getScene().getWindow();
            root = FXMLLoader.load(getClass().getResource("/fxml/menu.fxml"), States.getInstance().getBundle());
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public static void lengthLimit(final TextField textField){
        textField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            try {
                if (textField.getText().length() > 1) {
                    String s = textField.getText().substring(0, 1);
                    textField.setText(s);
                    throw new TooLongCharException("Too long");
                }
            } catch(TooLongCharException e) {
                logger.error(e.getMessage(), e);
            }
        });
    }

    public static void charLimit(final TextField textField) {
        textField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            try {
                if (!newValue.matches("\\d*") || newValue.equals("0")) {
                    
                    textField.setText(newValue.replaceAll("[^\\d]", ""));
                    if (newValue.equals("0")) {
                        textField.setText(newValue.replaceAll("0", ""));
                    }
                    throw new CharForbiddenException("Forbidden char");
                }
            } catch (CharForbiddenException e) {
                logger.error(e.getMessage(), e);
            }
        });
    }
    @FXML
    void handleButtonSave(ActionEvent event) throws Exception {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game saving");
        boolean flag = true;
        try {
            try {
                Dao<SudokuBoard> save = new JdbcSudokuBoardDao("save");
                save.write(this.sudokuFilled);
            } catch (Exception e) {
                throw new JdbcException("Saving");
            }
        } catch (JdbcException e) {
            flag = false;
            logger.error(e.getMessage(), e);
            alert.setContentText(States.getInstance().getBundle().getString("save.unsuccessful"));
        }
        if (flag) {
            alert.setContentText(States.getInstance().getBundle().getString("save.successful"));
        }

        alert.setHeaderText(null);
        alert.showAndWait();
    }

    @FXML

    void handleButtonLoad(ActionEvent event) throws Exception {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game loading");
        boolean flag = true;
        try {
            try {
                Dao<SudokuBoard> load = new JdbcSudokuBoardDao("save");
                load.read();

            } catch (Exception e) {

                throw new JdbcException("Loading");
            }
        } catch (JdbcException e) {

            logger.error(e.getMessage(), e);
            flag = false;
            alert.setContentText(States.getInstance().getBundle().getString("load.unsuccessful"));
        }

        if (flag) {
            alert.setContentText(States.getInstance().getBundle().getString("load.successful"));
        }
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        sudokuSolver.solve(sudokuWithGaps);
        try {
            sudokuFilled = sudokuWithGaps.clone();
        } catch (CloneNotSupportedException ex) {
            logger.error(ex.getMessage(), ex);
        }
        
        sudokuWithGaps = States.getInstance().getDifficulty().deleteFields(sudokuWithGaps);
        
        textFields = new TextField[9][9];
        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                TextField textField = new TextField();
                textField.setPrefSize(100, 100);
                textField.setAlignment(Pos.CENTER);
                //textField.setStyle("-fx-pref-width: 5em;");
                
                textFields[x][y] = textField;

                JavaBeanIntegerPropertyBuilder javaBeanIntegerPropertyBuilder = new JavaBeanIntegerPropertyBuilder();
                try {
                    textField.textProperty().bindBidirectional(JavaBeanIntegerPropertyBuilder
                            .create()
                            .name("value")
                            .bean(this.sudokuWithGaps.getField(x, y))
                            .build(),
                            new NumberStringConverter());
                } catch (NoSuchMethodException ex) {
                    logger.error(ex.getMessage(), ex);
                }
                
                
                if (sudokuWithGaps.getBoard(x, y) != 0) {
                    textFields[x][y].setText(Integer.toString(sudokuWithGaps.getBoard(x, y)));
                    textFields[x][y].setDisable(true);
                } else {
                    textFields[x][y].setText("");
                    textFields[x][y].setDisable(false);
                }
                GridPane.setConstraints(textField, y, x);
                lengthLimit(textField);
                charLimit(textField);
                gridPane.getChildren().add(textField);
            }
        }

    }

}
