package games;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.odyssey.Choice;
import org.example.odyssey.DefeatMemoryMastery;
import org.example.odyssey.MedalMemoryMastery;

import java.util.ArrayList;
import java.util.Collections;

public class MemoryMastery extends Application {

    private final String[] cardList = {
            "card_1", "card_2", "card_3", "card_4", "card_5", "card_6"
    };

    private final int cardWidth = 106;
    private final int cardHeight = 158;
    private int triesCount = 0;
    private static final int MAX_TRIES = 14;


    private final ArrayList<Card> cardSet = new ArrayList<>();
    private Image cardBackImage;

    private Button firstCardSelected;
    private Button secondCardSelected;
    private boolean gameReady = false;
    private final ArrayList<Button> board = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        setupCards();
        shuffleCards();

        Image backgroundImage = new Image(getClass().getResource("/static/images/amphitheaters/arena_2.jpg").toExternalForm());
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(1000);
        backgroundImageView.setFitHeight(700);

        Text textMemoryMastery = new Text("Memory Mastery!");
        textMemoryMastery.setTranslateY(-280.0);
        textMemoryMastery.getStyleClass().add("text-game");

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

        StackPane root = new StackPane();

        GridPane boardGrid = new GridPane();
        boardGrid.setAlignment(Pos.CENTER);
        boardGrid.setHgap(10);
        boardGrid.setVgap(10);
        boardGrid.setTranslateX(0);
        boardGrid.setTranslateY(13);

        for (Card card : cardSet) {
            Button cardButton = new Button();
            cardButton.setPrefSize(cardWidth, cardHeight);
            cardButton.getStyleClass().add("animation-shine");
            cardButton.setStyle(
                    "-fx-background-image: url('" + cardBackImage.getUrl() + "'); " +
                            "-fx-background-size: cover; " +
                            "-fx-padding: 0; " +
                            "-fx-border-width: 0;"
            );
            cardButton.setOnAction(e -> handleCardClick(cardButton, card));
            board.add(cardButton);
            int columns = 4;
            boardGrid.add(cardButton, board.indexOf(cardButton) % columns, board.indexOf(cardButton) / columns);
        }

        root.getChildren().addAll(backgroundImageView, textMemoryMastery, boardGrid, back);

        Scene scene = new Scene(root, 1000, 700);
        scene.getStylesheets().add(getClass().getResource("/static/css/style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("The Squares’ Odyssey");
        primaryStage.show();

        gameReady = true;
    }

    private void setupCards() {
        for (String cardName : cardList) {
            Image cardImage = new Image(getClass().getResource("/static/images/icons/" + cardName + ".jpg").toExternalForm());
            cardSet.add(new Card(cardName, cardImage));
            cardSet.add(new Card(cardName, cardImage));
        }

        cardBackImage = new Image(getClass().getResource("/static/images/icons/back_card.jpg").toExternalForm());
    }

    private void shuffleCards() {
        Collections.shuffle(cardSet);
    }

    private void handleCardClick(Button cardButton, Card card) {
        if (!gameReady || cardButton == firstCardSelected || cardButton == secondCardSelected) return;

        ImageView cardImageView = new ImageView(card.cardImage);
        cardImageView.setFitWidth(cardWidth);
        cardImageView.setFitHeight(cardHeight);
        cardImageView.setPreserveRatio(false);
        cardButton.setGraphic(cardImageView);
        cardButton.setUserData(card);
        cardButton.getStyleClass().add("animation-shine");

        if (firstCardSelected == null) {
            firstCardSelected = cardButton;
        } else if (secondCardSelected == null) {
            secondCardSelected = cardButton;
            gameReady = false;

            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(e -> checkMatch());
            pause.play();
        }
    }

    private void checkMatch() {
        Card firstCard = (Card) firstCardSelected.getUserData();
        Card secondCard = (Card) secondCardSelected.getUserData();

        triesCount++;

        if (!firstCard.cardName.equals(secondCard.cardName)) {
            firstCardSelected.setGraphic(null);
            secondCardSelected.setGraphic(null);
            firstCardSelected.setStyle(
                    "-fx-background-image: url('" + cardBackImage.getUrl() +
                            "'); -fx-background-size: cover; -fx-padding: 0; -fx-border-width: 0;"
            );
            secondCardSelected.setStyle(
                    "-fx-background-image: url('" + cardBackImage.getUrl() +
                            "'); -fx-background-size: cover; -fx-padding: 0; -fx-border-width: 0;"
            );
        }

        firstCardSelected = null;
        secondCardSelected = null;
        gameReady = true;

        checkLose();
        checkWin();
    }

    private void checkLose() {
        if (triesCount > MAX_TRIES) {
            DefeatMemoryMastery defeatMemoryMastery = new DefeatMemoryMastery();
            Stage primaryStage = (Stage) board.get(0).getScene().getWindow();
            defeatMemoryMastery.start(primaryStage);
        }
    }


    private void checkWin() {
        boolean allCardsRevealed = board.stream().allMatch(button -> button.getGraphic() != null);

        if (allCardsRevealed) {
            MedalMemoryMastery medalMemoryMastery = new MedalMemoryMastery();
            Stage primaryStage = (Stage) board.get(0).getScene().getWindow();
            medalMemoryMastery.start(primaryStage);
        }
    }

    static class Card {
        String cardName;
        Image cardImage;

        Card(String cardName, Image cardImage) {
            this.cardName = cardName;
            this.cardImage = cardImage;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}