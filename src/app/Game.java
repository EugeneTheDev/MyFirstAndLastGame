package app;

/**
 * Created by Eugene on 27.10.17.
 */

import app.model.Direction;
import app.model.Model;
import app.model.fightgame.FightGame;
import app.model.items.Item;
import app.model.objects.*;
import app.music.MusicPlayer;
import app.view.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;


import java.io.IOException;

public class Game extends Application {
    private Stage primaryStage;
    private Model model;
    private Scene scene;
    private AnchorPane field,fightScreen,itemDescription,forChest,startScreen,menuScreen,enemyDescription;
    private Pane canvas;
    private Pane gameField;
    private GridPane dialog,sign;
    private StackPane root,victoryScreen,finalScreen;
    private Direction direction=Direction.NOTHING;
    private AnimationTimer timer;
    private VictoryScreenController controller;
    private FieldController fieldController;
    private DialogScreenController dialogController;
    private FightScreenController fightController;
    private ItemDescriptionController descriptionController;
    private ForChestController chestController;
    private SignController signController;
    private EnemyDescriptionController enemyController;
    private MusicPlayer player;
    private FightGame fightGame;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage=primaryStage;
        this.primaryStage.setTitle("White Face: The Game");
        this.primaryStage.getIcons().add(new Image(Game.class.getClassLoader().getResourceAsStream("images/HeartIcon.png")));
        player=new MusicPlayer();
        initPanesAndShow();
        onLoad();

    }
    private void initPanesAndShow(){
        FXMLLoader loaderStart=new FXMLLoader(Game.class.getResource("view/StartScreen.fxml")),
        loaderField=new FXMLLoader(Game.class.getResource("view/Field.fxml")),
        loaderRoot=new FXMLLoader(Game.class.getResource("view/RootLayout.fxml")),
        loaderVictory=new FXMLLoader(Game.class.getResource("view/VictoryScreen.fxml")),
        loader=new FXMLLoader(Game.class.getResource("view/DialogScreen.fxml")),
        loaderFight=new FXMLLoader(Game.class.getResource("view/FightScreen.fxml")),
        loaderDescription=new FXMLLoader(Game.class.getResource("view/ItemDescription.fxml")),
        loaderChest=new FXMLLoader(Game.class.getResource("view/ForChest.fxml")),
        loaderSign=new FXMLLoader(Game.class.getResource("view/Sign.fxml")),
        loaderMenu=new FXMLLoader(Game.class.getResource("view/MenuScreen.fxml")),
        loaderEnemy=new FXMLLoader(Game.class.getResource("view/EnemyDescription.fxml")),
        loaderFinalScreen=new FXMLLoader(Game.class.getResource("view/FinalScreen.fxml"));


        Label label=new Label("Play");
        label.setId("start");
        try {
            root=loaderRoot.load();
            startScreen=loaderStart.load();
            enemyDescription=loaderEnemy.load();
            enemyController=loaderEnemy.getController();
            enemyController.setGame(this);
            dialog=loader.load();
            dialogController=loader.getController();
            dialogController.setGame(this);
            victoryScreen=loaderVictory.load();
            controller=loaderVictory.getController();
            controller.setGame(this);
            ((StartScreenController)loaderStart.getController()).setGame(this);
            field=loaderField.load();
            fieldController=loaderField.getController();
            fieldController.setGame(this);
            itemDescription=loaderDescription.load();
            descriptionController=loaderDescription.getController();
            descriptionController.setGame(this);
            forChest=loaderChest.load();
            chestController=loaderChest.getController();
            chestController.setGame(this);
            canvas=(Pane) field.getChildren().get(0);
            gameField=(Pane)canvas.getChildren().get(0);
            ((Button)((GridPane)startScreen.getChildren().get(0)).getChildren().get(1)).setGraphic(label);
            fightScreen=loaderFight.load();
            fightController=loaderFight.getController();
            sign=loaderSign.load();
            signController=loaderSign.getController();
            signController.setGame(this);
            menuScreen=loaderMenu.load();
            MenuScreenController menuController = loaderMenu.getController();
            menuController.setGame(this);
            finalScreen=loaderFinalScreen.load();
            ((FinalScreenController)loaderFinalScreen.getController()).setGame(this);
            root.getChildren().add(startScreen);
            scene=new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
            player.playTrack("start");
            player.setVolume(0.25);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void onLoad(){
        model=new Model();
        model.loadObjectsImages();
        GridPane pane=(GridPane) fightScreen.getChildren().get(0);
        fightGame=new FightGame(fightController,this,fightScreen,(GridPane) ((AnchorPane) pane.getChildren().get(0)).getChildren().get(0),
                (AnchorPane)pane.getChildren().get(1));
        field.setOnKeyPressed(event ->{
            switch (event.getCode()){
                case W:
                    direction=Direction.UP;
                    break;
                case S:
                    direction=Direction.DOWN;
                    break;
                case D:
                    direction=Direction.RIGHT;
                    break;
                case A:
                    direction=Direction.LEFT;
                    break;
                case E:
                    direction=Direction.NOTHING;
                    interact();
                    break;
                case DIGIT1:
                    direction=Direction.NOTHING;
                    showDescription(1);
                    break;
                case DIGIT2:
                    direction=Direction.NOTHING;
                    showDescription(2);
                    break;
                case DIGIT3:
                    direction=Direction.NOTHING;
                    showDescription(3);
                    break;
                case DIGIT4:
                    direction=Direction.NOTHING;
                    showDescription(4);
                    break;
                case DIGIT5:
                    direction=Direction.NOTHING;
                    showDescription(5);
                    break;
//                //TODO here`s test
//                case I:
//                    canvas.setTranslateY(canvas.getTranslateY()+50);
//                    break;
//                case K:
//                    canvas.setTranslateY(canvas.getTranslateY()-50);
//                    break;
//                case L:
//                    canvas.setTranslateX(canvas.getTranslateX()-50);
//                    break;
//                case J:
//                    canvas.setTranslateX(canvas.getTranslateX()+50);
//                    break;
            }
        });
        field.setOnKeyReleased(event ->direction=Direction.NOTHING);
    }
    public void addItem(Chest chest){
        if (chest!=null&&model.getGameObjects().getHero().getItems().size()<5
                &&chest.getItem()!=null){
            Item item=chest.pickUpItem();
            model.getGameObjects().getHero().getItems().add(item);
            fieldController.addItem(item);
        }
        cancel();
    }
    private void interact(){
        if(model.getGameObjects().getHero().getBoundsInParent().
                intersects(model.getGameObjects().getPortal()
                        .getBoundsInParent())) victory();
        else {
            GameObject object = model.getNearObject();
            if (object != null) {
                if (object instanceof Chest) {
                    chestController.initDescription((Chest) object);
                    root.getChildren().add(forChest);
                    Platform.runLater(() -> forChest.getChildren().get(3).requestFocus());
                } else if (object instanceof Sign) {
                    signController.setText(((Sign) object).getText());
                    root.getChildren().add(sign);
                    Platform.runLater(() -> sign.getChildren().get(0).requestFocus());
                } else if (object instanceof FightableObject) {
                    enemyController.updateDescription((FightableObject) object);
                    root.getChildren().add(enemyDescription);
                    Platform.runLater(() -> enemyDescription.getChildren().get(4).requestFocus());
                }
            }
        }
    }
    private void showDescription(int number){
        Item item=fieldController.getItemByNumber(number);
        if(item!=null){
            descriptionController.initDescription(item,number);
            root.getChildren().add(itemDescription);
            Platform.runLater(()->itemDescription.getChildren().get(3).requestFocus());
        }
    }
    public void cancel(){
        root.getChildren().remove(root.getChildren().size()-1);
        Platform.runLater(()->field.requestFocus());
    }
    public void useItem(int number){
        fieldController.useItem(number);
        cancel();
    }

    private void run(){
        model.move(direction);
        doDisplacement();
    }
    public void playGame(){
        model.startLevel(1);//TODO test
        model.restart();
        /*gameField.setViewport(new Rectangle2D(0,0,model.getFieldWidth(),model.getFieldHeight()));
        gameField.setFitHeight(model.getFieldHeight());
        gameField.setFitWidth(model.getFieldWidth());*/
        gameField.setMinSize(model.getFieldWidth(),model.getFieldHeight());
        canvas.getChildren().addAll(model.getGameObjects().getAll());
        root.getChildren().clear();
        root.getChildren().add(field);
        timer=new AnimationTimer() {
            @Override
            public void handle(long now) {
                run();
            }
        };
        doDisplacement();
        dialogController.setStory(model.getStoriesList());
        root.getChildren().add(dialog);
        fieldController.updateHeroHealth(model.getGameObjects().getHero());
        player.playTrack("game");
        player.setVolume(0.1);
    }

    private void defeat(){
        restart();
    }
    private void victory(){
        timer.stop();
        controller.updateLabel(""+model.getCurrentLevel());
        fieldController.clearInventory();
        if (model.isTheEnd()){
            root.getChildren().add(finalScreen);
            Platform.runLater(() -> ((GridPane) ((GridPane) finalScreen.getChildren().get(0))
                    .getChildren().get(1)).getChildren().get(0).requestFocus());
        }
        else {
            root.getChildren().add(victoryScreen);
            Platform.runLater(() -> ((GridPane) ((GridPane) victoryScreen.getChildren().get(0))
                    .getChildren().get(1)).getChildren().get(1).requestFocus());
        }
        player.playEffect("uhu");
    }
    private void restart() {
        canvas.getChildren().removeAll(model.getGameObjects().getAll());
        model.restart();
        fieldController.clearInventory();
        canvas.getChildren().addAll(model.getGameObjects().getAll());
        doDisplacement();
    }
    public void restartFromMenu(){
        restart();
        cancel();
        timer.start();
        player.play();
    }

    public void startNextLevel() {
        root.getChildren().remove(victoryScreen);
        canvas.getChildren().removeAll(model.getGameObjects().getAll());
        model.startNextLevel();
        gameField.setMinSize(model.getFieldWidth(),model.getFieldHeight());
        canvas.getChildren().addAll(model.getGameObjects().getAll());
        doDisplacement();
        dialogController.setStory(model.getStoriesList());
        root.getChildren().add(dialog);
        fieldController.updateHeroHealth(model.getGameObjects().getHero());
    }
    public void startPlay(){
        root.getChildren().remove(dialog);
        Platform.runLater(()->field.requestFocus());
        timer.start();
    }
    public void continuePlay(){
        timer.start();
        player.play();
        cancel();
    }
    public void pause(){
        if (!root.getChildren().contains(menuScreen)) {
            root.getChildren().add(menuScreen);
            timer.stop();
            player.pause();
        }
    }
    public void exit(){
        timer.stop();
        fieldController.clearInventory();
        canvas.getChildren().removeAll(model.getGameObjects().getAll());
        root.getChildren().clear();
        root.getChildren().add(startScreen);
        player.playTrack("start");
    }
    private void doDisplacement(){
        double[] displacement=model.getGameObjects().getHero().getDisplacement();
        canvas.setLayoutX(displacement[0]);
        canvas.setLayoutY(displacement[1]);
    }
    public void fight(){
        cancel();
        FightableObject enemy;
        if ((enemy=model.fight())!=null) {
            timer.stop();
            root.getChildren().remove(field);
            root.getChildren().add(fightScreen);
            fightGame.updateFightGame(enemy,model.getGameObjects().getHero());
            player.playTrack("fight");
            player.setVolume(0.1);
        }
    }
    public void defeatInFight(){
        direction=Direction.NOTHING;
        root.getChildren().remove(fightScreen);
        root.getChildren().add(field);
        Platform.runLater(()->field.requestFocus());
        defeat();
        timer.start();
        player.playTrack("game");
    }
    public void interruptFight(){
        direction=Direction.NOTHING;
        root.getChildren().remove(fightScreen);
        root.getChildren().add(field);
        fieldController.updateHeroHealth(model.getGameObjects().getHero());
        Platform.runLater(()->field.requestFocus());
        timer.start();
        player.playTrack("game");
        player.setVolume(0.1);
    }
    public void victoryInFight(FightableObject enemy){
        fieldController.updateHeroHealth(model.getGameObjects().getHero());
        direction=Direction.NOTHING;
        model.getGameObjects().getNpcs().remove(enemy);
        canvas.getChildren().remove(enemy);
        root.getChildren().remove(fightScreen);
        root.getChildren().add(field);
        Platform.runLater(()->field.requestFocus());
        timer.start();
        player.playTrack("game");
        player.setVolume(0.1);
    }
    public void increaseHealth(int dHealth,int number){
        model.getGameObjects().getHero().increaseHealth(dHealth);
        fieldController.updateHeroHealth(model.getGameObjects().getHero());
        fieldController.removeItem(number,model.getGameObjects().getHero());
    }
    public void openDoor(int number){
        GameObject object=model.getNearObject();
        if(object instanceof Door){
            ((Door) object).open();
            fieldController.removeItem(number,model.getGameObjects().getHero());
        }
    }
    public void useOfferableItem(Item item,int number){
        GameObject object=model.getNearObject();
        if (object instanceof FightableObject&&((FightableObject) object).getOfferableItems().contains(item.getClass())){
            canvas.getChildren().remove(object);
            model.getGameObjects().getNpcs().remove(object);
            fieldController.removeItem(number,model.getGameObjects().getHero());
        }
    }

    public Model getModel() {
        return model;
    }

    public Scene getScene() {
        return scene;
    }
}