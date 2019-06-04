package lk.ijse.pos.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class AppInitializer extends Application {

    public static AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();

    public static void main(String[] args) throws IOException {

        FileHandler fileHandler = new FileHandler("error.log", true);
        fileHandler.setFormatter(new SimpleFormatter());
        Logger.getLogger("").addHandler(fileHandler);
        launch(args);
    }

    public static void navigateToHome(Node node, Stage mainStage) throws IOException {

        Parent root = FXMLLoader.load(AppInitializer.class.getResource("/fxml/MainForm.fxml"));
        Scene mainScene = new Scene(root);
        mainStage.setScene(mainScene);
        mainStage.centerOnScreen();
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        ctx.register(AppConfig.class);
        ctx.refresh();

        Parent root = FXMLLoader.load(this.getClass().getResource("/fxml/MainForm.fxml"));
        Scene mainScene = new Scene(root);
        primaryStage.setScene(mainScene);
        primaryStage.setTitle("Simple POS App - DEP 3");
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.centerOnScreen();
    }
}
