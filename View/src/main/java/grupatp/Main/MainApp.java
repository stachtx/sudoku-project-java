package grupatp.Main;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import grupatp.application.States;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author Tomasz Stachura
 * @author Paweł Ograbek
 */
public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("bundles.lang", new Locale("en", "EN"));
        States.getInstance().setBundle(resourceBundle);
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/menu.fxml"), resourceBundle);
        stage.setTitle("Sudoku Game");

        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
