package org.example.odyssey;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class Introduction extends Application {

    @Override
    public void start(Stage primaryStage) {
        Media media = new Media(getClass().getResource("/static/images/videos/video_intro.mp4").toExternalForm());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);

        mediaView.setFitWidth(765);
        mediaView.setPreserveRatio(true);

        StackPane layout = new StackPane();
        layout.getChildren().add(mediaView);

        Scene scene = new Scene(layout, 765, 700);
        primaryStage.setScene(scene);
        primaryStage.setTitle("The Square's Odyssey");
        primaryStage.show();

        mediaPlayer.play();

        mediaPlayer.setOnEndOfMedia(() -> {
            Choice choicePage = new Choice();
            choicePage.start(primaryStage);
        });

        mediaPlayer.setOnError(() -> {
            System.out.println("Media Error: " + mediaPlayer.getError().getMessage());
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}