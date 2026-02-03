package org.example.odyssey;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Game extends Application {

    @Override
    public void start(Stage primaryStage) {
        Image backgroundImage = new Image(getClass().getResource("/static/images/amphitheaters/arena_5.jfif").toExternalForm());
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(1000);
        backgroundImageView.setFitHeight(700);

        Text textGame = new Text("Welcome to The Squares’ Odyssey!");
        textGame.setTranslateY(-280);
        textGame.getStyleClass().add("text-main");

        Image imageButtonPlay = new Image(getClass().getResource("/static/images/assets/play_button.png").toExternalForm());
        ImageView imageViewButton = new ImageView(imageButtonPlay);
        imageViewButton.setFitWidth(130);
        imageViewButton.setFitHeight(50);

        Button playButton = new Button();
        playButton.getStyleClass().add("border-animation");
        playButton.setGraphic(imageViewButton);
        playButton.setTranslateY(-215.0);
        playButton.setOnAction(event -> {
            Introduction introduction = new Introduction();
            introduction.start(primaryStage);
        });

        StackPane root = new StackPane();
        root.getChildren().addAll(backgroundImageView, textGame, playButton);

        Scene scene = new Scene(root, 1000, 700);
        scene.getStylesheets().add(getClass().getResource("/static/css/style.css").toExternalForm());

        primaryStage.setTitle("The Squares’ Odyssey");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(Game.class, args);
    }
}