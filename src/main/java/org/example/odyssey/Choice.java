package org.example.odyssey;

import games.MemoryMastery;
import games.GodsBattle;
import games.SlidingPuzzle;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Choice extends Application {

    private Stage stage;

    @Override
    public void start(Stage primaryStage) {
        stage = primaryStage;

        Image backgroundImage = new Image(getClass().getResource("/static/images/amphitheaters/arena_4.jfif").toExternalForm());
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(1000);
        backgroundImageView.setFitHeight(700);

        Text textChoice = new Text("The Squares' Odyssey!");
        textChoice.setTranslateY(-280);
        textChoice.getStyleClass().add("text-main");

        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setTranslateY(0);
        buttonBox.setPadding(new Insets(10, 10, 40, 10));

        ImageView god1 = new ImageView(new Image(getClass().getResource("/static/images/deities/god_1.png").toExternalForm()));
        ImageView god2 = new ImageView(new Image(getClass().getResource("/static/images/deities/god_2.png").toExternalForm()));
        ImageView god3 = new ImageView(new Image(getClass().getResource("/static/images/deities/god_3.png").toExternalForm()));

        god1.setFitWidth(120);
        god1.setFitHeight(170);

        god2.setFitWidth(110);
        god2.setFitHeight(220);

        god3.setFitWidth(95);
        god3.setFitHeight(180);

        god1.setOnMouseClicked(event -> {
            handleGodClick(god1);
            PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
            pause.setOnFinished(e -> {
                GodsBattle battle = new GodsBattle();
                battle.start(stage);
            });
            pause.play();
        });

        god2.setOnMouseClicked(event -> {
            handleGodClick(god2);
            PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
            pause.setOnFinished(e -> {
                SlidingPuzzle puzzle = new SlidingPuzzle();
                puzzle.start(stage);
            });
            pause.play();
        });

        god3.setOnMouseClicked(event -> {
            handleGodClick(god3);
            PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
            pause.setOnFinished(e -> {
                MemoryMastery cards = new MemoryMastery();
                cards.start(stage);
            });
            pause.play();
        });

        buttonBox.getChildren().addAll(god1, god2, god3);

        StackPane root = new StackPane();
        root.getChildren().addAll(backgroundImageView, buttonBox, textChoice);

        Scene scene = new Scene(root, 1000, 700);
        stage.setScene(scene);
        scene.getStylesheets().add(getClass().getResource("/static/css/style.css").toExternalForm());
        stage.setTitle("The Squares' Odyssey");
        stage.show();
    }

    private void handleGodClick(ImageView zeu) {
        FadeTransition fade = new FadeTransition(Duration.seconds(2), zeu);
        fade.setFromValue(1);
        fade.setToValue(0);
        fade.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}