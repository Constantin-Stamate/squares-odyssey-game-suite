package games;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.odyssey.Choice;
import org.example.odyssey.DefeatSlidingPuzzle;
import org.example.odyssey.MedalSlidingPuzzle;

import java.util.Random;

public class SlidingPuzzle extends Application {

    private ImageView[] tileViews;
    private final int size = 4;
    private final int nbTiles = size * size - 1;
    private final int[] tiles = new int[size * size];
    private int blankPos;
    private boolean gameOver = true;
    private final int dimension = 526;
    private final int margin = 30;
    private int tileSize;
    private static final Random RANDOM = new Random();
    private final Image[] tileImages = new Image[16];

    @Override
    public void start(Stage primaryStage) {
        tileSize = (dimension - 2 * margin) / size;
        int gridSize = dimension - 2 * margin;

        Image backgroundImage = new Image(getClass().getResource("/static/images/amphitheaters/arena_3.jfif").toExternalForm());
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(1000);
        backgroundImageView.setFitHeight(700);

        Text puzzleText = new Text("Sliding Puzzle!");
        puzzleText.setTranslateY(-280);
        puzzleText.getStyleClass().add("text-game");

        Button back = new Button();
        Image imageBack = new Image(getClass().getResource("/static/images/assets/back_button.png").toExternalForm());
        ImageView imageBackView = new ImageView(imageBack);
        back.setGraphic(imageBackView);
        imageBackView.setFitWidth(48);
        imageBackView.setFitHeight(48);
        back.getStyleClass().add("round-image-button");
        back.setTranslateY(-300);
        back.setTranslateX(400);

        back.setOnAction(e -> {
            Choice choice = new Choice();
            choice.start(primaryStage);
        });

        StackPane layout = new StackPane();
        layout.getChildren().addAll(backgroundImageView, puzzleText, back);

        loadImages();
        initializeTileViews(layout);

        Scene scene = new Scene(layout, 1000, 700);
        scene.getStylesheets().add(getClass().getResource("/static/css/style.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setTitle("The Squares’ Odyssey");
        primaryStage.show();

        newGame();
        draw();
    }

    private void loadImages() {
        String[] imagePaths = {"/static/images/scraps/fragment_1.png", "/static/images/scraps/fragment_2.png",
                "/static/images/scraps/fragment_3.png", "/static/images/scraps/fragment_5.png",
                "/static/images/scraps/fragment_6.png", "/static/images/scraps/fragment_7.png",
                "/static/images/scraps/fragment_8.png", "/static/images/scraps/fragment_9.png",
                "/static/images/scraps/fragment_10.png", "/static/images/scraps/fragment_11.png",
                "/static/images/scraps/fragment_12.png", "/static/images/scraps/fragment_13.png",
                "/static/images/scraps/fragment_14.png", "/static/images/scraps/fragment_15.png",
                "/static/images/scraps/fragment_16.png"};

        for (int i = 0; i < nbTiles; i++) {
            tileImages[i] = new Image(getClass().getResource(imagePaths[i]).toExternalForm());
        }
    }

    private void initializeTileViews(StackPane layout) {
        tileViews = new ImageView[tiles.length];
        for (int i = 0; i < tiles.length; i++) {
            ImageView tileView = new ImageView();
            tileView.setFitWidth(tileSize);
            tileView.setFitHeight(tileSize);
            tileView.getStyleClass().add("animation-shine");
            tileView.getStyleClass().add("border-animation");
            layout.getChildren().add(tileView);
            tileViews[i] = tileView;

            final int tileIndex = i;
            tileView.setOnMouseClicked(e -> handleTileClick(tileIndex));
        }
    }

    private void newGame() {
        reset();
        shuffle();

        if (!isSolvable()) {
            Stage primaryStage = (Stage) tileViews[0].getScene().getWindow();
            DefeatSlidingPuzzle defeatSlidingPuzzle = new DefeatSlidingPuzzle();
            defeatSlidingPuzzle.start(primaryStage);
        }

        gameOver = false;
        draw();
    }


    private void reset() {
        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = (i + 1) % tiles.length;
        }
        blankPos = tiles.length - 1;
    }

    private void shuffle() {
        int n = nbTiles;
        while (n > 1) {
            int r = RANDOM.nextInt(n--);
            int tmp = tiles[r];
            tiles[r] = tiles[n];
            tiles[n] = tmp;
        }
    }

    private boolean isSolvable() {
        int countInversions = 0;
        for (int i = 0; i < nbTiles; i++) {
            for (int j = 0; j < i; j++) {
                if (tiles[j] > tiles[i]) countInversions++;
            }
        }
        return countInversions % 2 == 0;
    }

    private boolean isSolved() {
        if (tiles[tiles.length - 1] != 0) return false;
        for (int i = nbTiles - 1; i >= 0; i--) {
            if (tiles[i] != i + 1) return false;
        }
        return true;
    }

    private void handleTileClick(int clickedIndex) {
        if (gameOver) {
            newGame();
        } else {
            int c1 = clickedIndex % size;
            int r1 = clickedIndex / size;

            int c2 = blankPos % size;
            int r2 = blankPos / size;

            int dir = 0;

            if (c1 == c2 && Math.abs(r1 - r2) > 0) dir = (r1 - r2) > 0 ? size : -size;
            else if (r1 == r2 && Math.abs(c1 - c2) > 0) dir = (c1 - c2) > 0 ? 1 : -1;

            if (dir != 0) {
                do {
                    int newBlankPos = blankPos + dir;
                    tiles[blankPos] = tiles[newBlankPos];
                    blankPos = newBlankPos;
                } while (blankPos != clickedIndex);

                tiles[blankPos] = 0;
            }

            gameOver = isSolved();

            if (gameOver) {
                MedalSlidingPuzzle medalSlidingPuzzle = new MedalSlidingPuzzle();
                Stage primaryStage = (Stage) tileViews[0].getScene().getWindow();
                medalSlidingPuzzle.start(primaryStage);
            }
        }

        draw();
    }

    private void draw() {
        for (int i = 0; i < tiles.length; i++) {
            ImageView tileView = tileViews[i];
            if (tiles[i] == 0) {
                tileView.setVisible(false);
            } else {
                tileView.setImage(tileImages[tiles[i] - 1]);
                tileView.setVisible(true);
            }

            int r = i / size;
            int c = i % size;
            double x = margin + c * tileSize - dimension / 2.0 + tileSize / 2.0;
            double y = margin + r * tileSize - dimension / 2.0 + tileSize / 2.0;
            tileView.setTranslateX(x);
            tileView.setTranslateY(y);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}