package org.example.odyssey;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.nio.file.Paths;

public class MedalMemoryMastery extends Application {

    @Override
    public void start(Stage primaryStage) {
        StackPane root = new StackPane();

        Image backgroundImage = new Image(getClass().getResource("/static/images/awards/medalie_3.png").toExternalForm());
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(800);
        backgroundImageView.setFitHeight(780);

        Text textMedalMemoryMastery = new Text("You won the Memory Mastery Game!");
        textMedalMemoryMastery.setTranslateY(-280);
        textMedalMemoryMastery.getStyleClass().add("text-medal");

        Button back = new Button();
        Image imageView = new Image(getClass().getResource("/static/images/assets/back_button.png").toExternalForm());
        ImageView imageBackView = new ImageView(imageView);
        back.setGraphic(imageBackView);
        imageBackView.setFitWidth(48);
        imageBackView.setFitHeight(48);
        back.getStyleClass().add("round-image-button");
        back.setTranslateY(-330);
        back.setTranslateX(350);

        back.setOnAction(e -> {
            Choice choice = new Choice();
            choice.start(primaryStage);
        });

        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        String path = Paths.get("src/main/resources/templates/pride-memory-mastery.html").toUri().toString();
        webView.setStyle("-fx-background-color: transparent;");
        webView.setOpacity(0.1);

        webEngine.load(path);

        root.getChildren().add(backgroundImageView);
        root.getChildren().add(webView);
        root.getChildren().add(textMedalMemoryMastery);
        root.getChildren().add(back);
        root.setOpacity(1);

        Scene scene = new Scene(root, 800, 750);
        scene.getStylesheets().add(getClass().getResource("/static/css/style.css").toExternalForm());

        primaryStage.setTitle("The Squares’ Odyssey");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}