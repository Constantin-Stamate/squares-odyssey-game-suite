package games;

import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.odyssey.Choice;
import org.example.odyssey.DefeatGodsBattle;
import org.example.odyssey.MedalGodsBattle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GodsBattle extends Application {

    private int countPositions = 0;
    private double coefficientY = -210;
    private double coefficientX = 200;
    private final double coefficientLeft = 200;
    private final double coefficientDown = 50;
    private boolean verification = false;
    private int countTries = 0;
    private int countGods = 0;
    private int countGoddess = 0;
    List<Integer> gameLogic = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {
        StackPane layout = new StackPane();

        List<ImageView> deityViews = new ArrayList<>();
        List<Integer> gameLogicImage = new ArrayList<>();

        Random random = new Random();

        int count = 0;
        while (count < 4) {
            int x = random.nextInt(9) + 1;
            boolean ok = true;
            for (int val : gameLogic)
                if (val == x) {
                    ok = false;
                    break;
                }
            if (ok) {
                count++;
                gameLogic.add(x);
            }
        }

        Image backgroundImage = new Image(getClass().getResource("/static/images/amphitheaters/arena_1.jfif").toExternalForm());
        ImageView backgroundImageView = new ImageView(backgroundImage);
        backgroundImageView.setFitWidth(1000);
        backgroundImageView.setFitHeight(700);

        Image parchmentImage = new Image(getClass().getResource("/static/images/assets/parchment.png").toExternalForm());
        ImageView parchmentImageView = new ImageView(parchmentImage);
        parchmentImageView.setFitWidth(500);
        parchmentImageView.setFitHeight(680);
        parchmentImageView.setTranslateX(230);
        parchmentImageView.setTranslateY(95);
        parchmentImageView.getStyleClass().add("animation-shine");

        Image god = new Image(getClass().getResource("/static/images/entities/god.png").toExternalForm());
        ImageView godView = new ImageView(god);

        Image deity = new Image(getClass().getResource("/static/images/entities/deity.png").toExternalForm());
        ImageView deityView = new ImageView(deity);

        Image tabel = new Image(getClass().getResource("/static/images/grids/tabel_1.png").toExternalForm());
        ImageView tabelView = new ImageView(tabel);
        tabelView.getStyleClass().add("border-animation");
        tabelView.getStyleClass().add("animation-shine");
        tabelView.setFitWidth(120 * 2.5);
        tabelView.setFitHeight(30 * 2.5);
        tabelView.setTranslateY(-230 + coefficientDown);
        tabelView.setTranslateX(0 - coefficientLeft);

        Image grid = new Image(getClass().getResource("/static/images/grids/tabel_2.jpg").toExternalForm());
        ImageView gridView = new ImageView(grid);
        gridView.setFitHeight(300);
        gridView.setFitWidth(300);
        gridView.setTranslateX(-coefficientLeft);
        gridView.setTranslateY(coefficientDown);

        Image letter1 = new Image(getClass().getResource("/static/images/symbols/symbol_1.jpg").toExternalForm());
        ImageView letterView1 = new ImageView(letter1);
        letterView1.setFitWidth(70);
        letterView1.setFitHeight(70);
        Button button1 = new Button();
        button1.setTranslateX(-95 - coefficientLeft);
        button1.setTranslateY(-94 + coefficientDown);
        button1.getStyleClass().add("border-animation");
        button1.setGraphic(letterView1);
        button1.setOnAction(event -> {
            if (countTries < 9)
                letterButtonAction(letter1, layout, deityViews, gameLogicImage, godView, deityView);
        });

        Image letter2 = new Image(getClass().getResource("/static/images/symbols/symbol_2.jpg").toExternalForm());
        ImageView letterView2 = new ImageView(letter2);
        letterView2.setFitWidth(70);
        letterView2.setFitHeight(70);
        Button button2 = new Button();
        button2.setTranslateX(0 - coefficientLeft);
        button2.setTranslateY(-94 + coefficientDown);
        button2.getStyleClass().add("border-animation");
        button2.setGraphic(letterView2);
        button2.setOnAction(event -> {
            if (countTries < 9)
                letterButtonAction(letter2, layout, deityViews, gameLogicImage, godView, deityView);
        });

        Image letter3 = new Image(getClass().getResource("/static/images/symbols/symbol_3.jpg").toExternalForm());
        ImageView letterView3 = new ImageView(letter3);
        letterView3.setFitWidth(70);
        letterView3.setFitHeight(70);
        Button button3 = new Button();
        button3.setTranslateX(94 - coefficientLeft);
        button3.setTranslateY(-94 + coefficientDown);
        button3.getStyleClass().add("border-animation");
        button3.setGraphic(letterView3);
        button3.setOnAction(event -> {
            if (countTries < 9)
                letterButtonAction(letter3, layout, deityViews, gameLogicImage, godView, deityView);
        });

        Image letter4 = new Image(getClass().getResource("/static/images/symbols/symbol_4.jpg").toExternalForm());
        ImageView letterView4 = new ImageView(letter4);
        letterView4.setFitWidth(70);
        letterView4.setFitHeight(70);
        Button button4 = new Button();
        button4.setTranslateX(-95 - coefficientLeft);
        button4.setTranslateY(1 + coefficientDown);
        button4.getStyleClass().add("border-animation");
        button4.setGraphic(letterView4);
        button4.setOnAction(event -> {
            if (countTries < 9)
                letterButtonAction(letter4, layout, deityViews, gameLogicImage, godView, deityView);
        });

        Image letter5 = new Image(getClass().getResource("/static/images/symbols/symbol_5.jpg").toExternalForm());
        ImageView letterView5 = new ImageView(letter5);
        letterView5.setFitWidth(70);
        letterView5.setFitHeight(70);
        Button button5 = new Button();
        button5.setTranslateX(0 - coefficientLeft);
        button5.setTranslateY(1 + coefficientDown);
        button5.getStyleClass().add("border-animation");
        button5.setGraphic(letterView5);
        button5.setOnAction(event -> {
            if (countTries < 9)
                letterButtonAction(letter5, layout, deityViews, gameLogicImage, godView, deityView);
        });

        Image letter6 = new Image(getClass().getResource("/static/images/symbols/symbol_6.jpg").toExternalForm());
        ImageView letterView6 = new ImageView(letter6);
        letterView6.setFitWidth(70);
        letterView6.setFitHeight(70);
        Button button6 = new Button();
        button6.setTranslateX(94 - coefficientLeft);
        button6.setTranslateY(1 + coefficientDown);
        button6.getStyleClass().add("border-animation");
        button6.setGraphic(letterView6);
        button6.setOnAction(event -> {
            if (countTries < 9)
                letterButtonAction(letter6, layout, deityViews, gameLogicImage, godView, deityView);
        });

        Image letter7 = new Image(getClass().getResource("/static/images/symbols/symbol_7.jpg").toExternalForm());
        ImageView letterView7 = new ImageView(letter7);
        letterView7.setFitWidth(70);
        letterView7.setFitHeight(70);
        Button button7 = new Button();
        button7.setTranslateX(-95 - coefficientLeft);
        button7.setTranslateY(96 + coefficientDown);
        button7.getStyleClass().add("border-animation");
        button7.setGraphic(letterView7);
        button7.setOnAction(event -> {
            if (countTries < 9)
                letterButtonAction(letter7, layout, deityViews, gameLogicImage, godView, deityView);
        });

        Image letter8 = new Image(getClass().getResource("/static/images/symbols/symbol_8.jpg").toExternalForm());
        ImageView letterView8 = new ImageView(letter8);
        letterView8.setFitWidth(70);
        letterView8.setFitHeight(70);
        Button button8 = new Button();
        button8.setTranslateX(0 - coefficientLeft);
        button8.setTranslateY(95 + coefficientDown);
        button8.getStyleClass().add("border-animation");
        button8.setGraphic(letterView8);
        button8.setOnAction(event -> {
            if (countTries < 9)
                letterButtonAction(letter8, layout, deityViews, gameLogicImage, godView, deityView);
        });

        Image letter9 = new Image(getClass().getResource("/static/images/symbols/symbol_9.jpg").toExternalForm());
        ImageView letterView9 = new ImageView(letter9);
        letterView9.setFitWidth(70);
        letterView9.setFitHeight(70);
        Button button9 = new Button();
        button9.setTranslateX(94 - coefficientLeft);
        button9.setTranslateY(94 + coefficientDown);
        button9.getStyleClass().add("border-animation");
        button9.setGraphic(letterView9);
        button9.setOnAction(event -> {
            if (countTries < 9)
                letterButtonAction(letter9, layout, deityViews, gameLogicImage, godView, deityView);
        });

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

        Text textGodsBattle = new Text("Battle of the Gods!");
        textGodsBattle.setTranslateY(-280);
        textGodsBattle.getStyleClass().add("text-game");

        layout.getChildren().addAll(backgroundImageView, textGodsBattle, tabelView, gridView, button1, button2, button3, button4,
                button5, button6, button7, button8, button9, back, parchmentImageView);

        for (int i : gameLogic) {
            System.out.println(i);
        }

        System.out.println();

        Scene introducereScene = new Scene(layout, 1000, 700);
        layout.getStylesheets().add(getClass().getResource("/static/css/style.css").toExternalForm());
        primaryStage.setScene(introducereScene);
        primaryStage.setTitle("The Squares’ Odyssey");
        primaryStage.show();
    }

    private void letterButtonAction(Image letter, StackPane layout, List<ImageView> deityViews, List<Integer> gameLogicImage, ImageView godView, ImageView deityView) {
        ImageView letterView = new ImageView(letter);
        if (countPositions < 4 && isElementAbsent(deityViews, letterView)) {
            letterView.setFitWidth(63);
            letterView.setFitHeight(66);

            switch (countPositions) {
                case 0:
                    letterView.setTranslateY(-230 + coefficientDown);
                    letterView.setTranslateX(-114 - coefficientLeft);
                    break;
                case 1:
                    letterView.setTranslateY(-230 + coefficientDown);
                    letterView.setTranslateX(-38 - coefficientLeft);
                    break;
                case 2:
                    letterView.setTranslateY(-230 + coefficientDown);
                    letterView.setTranslateX(38 - coefficientLeft);
                    break;
                case 3:
                    letterView.setTranslateY(-230 + coefficientDown);
                    letterView.setTranslateX(114 - coefficientLeft);
                    break;
            }

            countPositions++;
            layout.getChildren().add(letterView);
            deityViews.add(letterView);

            if (countPositions >= 4) {
                PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
                pause.setOnFinished(e -> {
                    countTries++;

                    List<ImageView> letterViewsAdd = new ArrayList<>();

                    for (ImageView view : deityViews) {
                        letterViewsAdd.add(view);
                        layout.getChildren().remove(view);
                    }

                    ImageView godViewCopy = new ImageView(godView.getImage());
                    ImageView deityViewCopy = new ImageView(deityView.getImage());

                    letterViewsAdd.add(godViewCopy);
                    letterViewsAdd.add(deityViewCopy);

                    deityViews.clear();
                    countPositions = 0;
                    int countElement = 0;

                    coefficientX = 100;
                    for (ImageView view : letterViewsAdd) {
                        countElement++;
                        if (countElement != 5) {
                            view.setFitHeight(50);
                            view.setFitWidth(50);
                        } else {
                            view.setFitHeight(50);
                            view.setFitWidth(50);
                        }

                        view.setTranslateY(coefficientY + 40);
                        view.setTranslateX(coefficientX);
                        coefficientX += 55;
                        layout.getChildren().add(view);

                        Image image = view.getImage();
                        String imageUrl = image.getUrl();
                        char c = imageUrl.charAt(imageUrl.length() - 5);

                        switch (c) {
                            case '1':
                                gameLogicImage.add(1);
                                break;
                            case '2':
                                gameLogicImage.add(2);
                                break;
                            case '3':
                                gameLogicImage.add(3);
                                break;
                            case '4':
                                gameLogicImage.add(4);
                                break;
                            case '5':
                                gameLogicImage.add(5);
                                break;
                            case '6':
                                gameLogicImage.add(6);
                                break;
                            case '7':
                                gameLogicImage.add(7);
                                break;
                            case '8':
                                gameLogicImage.add(8);
                                break;
                            case '9':
                                gameLogicImage.add(9);
                                break;
                        }

                        if (countElement == 4) {
                            int countI = 0, countJ = 0;
                            countGods = 0;
                            countGoddess = 0;
                            for (int i : gameLogic) {
                                countI++;
                                for (int j : gameLogicImage) {
                                    countJ++;
                                    if (i == j && countI == countJ) {
                                        countGods++;
                                    } else {
                                        if (i == j)
                                            countGoddess++;
                                    }
                                }
                                countJ = 0;
                            }
                        }

                        if (countElement == 4) {
                            Text godText = new Text("" + countGods);
                            godText.setTranslateY(coefficientY + 68);
                            godText.setTranslateX(coefficientX);
                            godText.setStyle("-fx-fill: #008080");
                            layout.getChildren().add(godText);
                        }

                        if (countElement == 5) {
                            Text deityText = new Text("" + countGoddess);
                            deityText.setTranslateY(coefficientY + 68);
                            deityText.setTranslateX(coefficientX);
                            deityText.setStyle("-fx-fill: #008080");
                            layout.getChildren().add(deityText);
                        }
                    }

                    coefficientY += 63;
                    letterViewsAdd.clear();
                    verification = gameLogic.equals(gameLogicImage);

                    gameLogicImage.clear();
                    if (countGods == 4) {
                        MedalGodsBattle medalGodsBattle = new MedalGodsBattle();
                        Stage stage = (Stage) layout.getScene().getWindow();
                        medalGodsBattle.start(stage);
                    } else if (countTries == 8) {
                        DefeatGodsBattle defeatGodsBattle = new DefeatGodsBattle();
                        Stage stage = (Stage) layout.getScene().getWindow();
                        defeatGodsBattle.start(stage);
                    }
                });
                pause.play();
            }
        }
    }

    private boolean isElementAbsent(List<ImageView> letterView, ImageView element) {
        for (ImageView view : letterView)
            if (view.getImage().equals(element.getImage()))
                return false;
        return true;
    }

    private List<Integer> solveGodsAndGoddesses(List<Integer> secretList, List<Integer> availableSymbols, int maxTries) {
        List<Integer> guess = new ArrayList<>();
        Random rand = new Random();

        for (int tryCount = 0; tryCount < maxTries; tryCount++) {
            guess.clear();

            while (guess.size() < 4) {
                int symbol = availableSymbols.get(rand.nextInt(availableSymbols.size()));
                if (!guess.contains(symbol)) {
                    guess.add(symbol);
                }
            }

            int gods = countGods(secretList, guess);
            int goddesses = countGoddesses(secretList, guess);

            if (gods == 4) {
                return guess;
            } else {
                updateSymbols(availableSymbols, guess);
            }
        }
        return null;
    }

    private int countGods(List<Integer> secretList, List<Integer> guess) {
        int gods = 0;
        for (int i = 0; i < secretList.size(); i++) {
            if (secretList.get(i).equals(guess.get(i))) {
                gods++;
            }
        }
        return gods;
    }

    private int countGoddesses(List<Integer> secretList, List<Integer> guess) {
        int goddesses = 0;
        for (Integer symbol : guess) {
            if (secretList.contains(symbol) && !secretList.get(guess.indexOf(symbol)).equals(symbol)) {
                goddesses++;
            }
        }
        return goddesses;
    }

    private void updateSymbols(List<Integer> availableSymbols, List<Integer> guess) {
        List<Integer> symbolsToRemove = new ArrayList<>(guess);
        for (int i = 0; i < guess.size(); i++) {
            if (guess.get(i).equals(gameLogic.get(i))) {
                symbolsToRemove.remove(guess.get(i));
            }
        }

        for (Integer symbol : guess) {
            if (gameLogic.contains(symbol) && !gameLogic.get(guess.indexOf(symbol)).equals(symbol)) {
                symbolsToRemove.remove(symbol);
            }
        }

        availableSymbols.removeAll(symbolsToRemove);
    }
}