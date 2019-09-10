package app.model.fightgame;

import app.Game;
import app.animation.SpriteAnimation;
import app.model.objects.FightableObject;
import app.model.objects.Hero;
import app.view.FightScreenController;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class FightGame {
    private AnchorPane fightField,root;
    private GridPane showField;
    private FightScreenController controller;
    private HashSet<Cube> cubes;
    private Player player;
    private AnimationTimer timer;
    private FightableObject enemy;
    private Hero hero;
    private SpriteAnimation heroAnimation,enemyAnimation;
    private int count;
    private Game game;
    private Map<String,Integer> heroParameters,enemyParameters;
    private Timeline heroTimeline,enemyTimeline;
    private Map<String,Image> images;

    public FightGame(FightScreenController controller,Game game, AnchorPane root, GridPane showField, AnchorPane fightField) {
        this.controller = controller;
        this.controller.setGame(this);
        this.game=game;
        this.root=root;
        this.showField=showField;
        this.fightField=fightField;
        images=new LinkedHashMap<>();
        images.put("laser",new Image(FightGame.class.getClassLoader().getResourceAsStream("objects/Laser.png")));
        images.put("laserShield",new Image(FightGame.class.getClassLoader()
                .getResourceAsStream("objects/LaserShield.png")));
        images.put("sword",new Image(FightGame.class.getClassLoader().getResourceAsStream("objects/Sword.png")));
        images.put("swordShield",new Image(FightGame.class.getClassLoader()
                .getResourceAsStream("objects/SwordShield.png")));
        images.put("fire",new Image(FightGame.class.getClassLoader().getResourceAsStream("objects/Fire.png")));
        images.put("fireShield",new Image(FightGame.class
                .getClassLoader().getResourceAsStream("objects/FireShield.png")));
        root.setOnKeyPressed(event -> {
            switch (event.getCode()){
                case Q:
                    player.setImage("laser",images.get("laserShield"));
                    break;
                case W:
                    player.setImage("sword",images.get("swordShield"));
                    break;
                case E:
                    player.setImage("fire",images.get("fireShield"));
                    break;
            }
        });
        timer=new AnimationTimer() {
            @Override
            public void handle(long now) {
                run();
            }
        };
    }

    public void updateFightGame(FightableObject enemy, Hero hero) {
        Platform.runLater(()->((GridPane)root.getChildren().get(0)).getChildren().get(2).requestFocus());
        cubes=new HashSet<>();
        this.enemy=enemy;
        this.hero=hero;
        count=0;
        addPlayer();
        addCube();
        initAnimation();
        onLoad();
        timer.start();
    }
    private void initAnimation(){
        ImageView heroView=new ImageView(hero.getImage()),
                enemyView=new ImageView(enemy.getImage());
        heroParameters=hero.getParameters();
        enemyParameters=enemy.getParameters();
        heroAnimation=new SpriteAnimation(heroView, Duration.millis(heroParameters.get("defaultDuration")),heroParameters.get("count"),
                heroParameters.get("count"), 0, heroParameters.get("defaultOffsetY"),(int)hero.getWidth(),(int)hero.getHeight()+13);
        heroAnimation.playFull(heroParameters.get("defaultCount"));
        enemyAnimation=new SpriteAnimation(enemyView, Duration.millis(enemyParameters.get("defaultDuration")),enemyParameters.get("count"),
                enemyParameters.get("count"),0,enemyParameters.get("defaultOffsetY"),
                enemyParameters.containsKey("width")?enemyParameters.get("width"):(int)enemy.getWidth(),(int)enemy.getHeight());
        enemyAnimation.playFull(enemyParameters.get("defaultCount"));
        showField.add(heroView,1,1);
        showField.add(enemyView,2,1);
    }

    private void onLoad(){
        controller.updateEnemyHealth(enemy);
        controller.updateHeroHealth(hero);
        controller.updateEnemyName(enemy.getName());
        heroTimeline=new Timeline(new KeyFrame(Duration.millis(heroParameters.get("fightDuration")), event ->{
            hero.fight(enemy);
            controller.updateEnemyHealth(enemy);
            heroAnimation.stop();
            heroAnimation.setDuration(heroParameters.get("defaultDuration"));
            heroAnimation.setOffsetY(heroParameters.get("defaultOffsetY"));
            heroAnimation.playFull(heroParameters.get("defaultCount"));
            if(!enemy.isAlive()){
                endFightGame();
                game.victoryInFight(enemy);
            } else timer.start();
        }));
        heroTimeline.setCycleCount(1);
        enemyTimeline=new Timeline(new KeyFrame(Duration.millis(enemyParameters.get("fightDuration")), event ->{
            enemy.fight(hero);
            controller.updateHeroHealth(hero);
            enemyAnimation.stop();
            enemyAnimation.setDuration(enemyParameters.get("defaultDuration"));
            enemyAnimation.setOffsetY(enemyParameters.get("defaultOffsetY"));
            enemyAnimation.playFull(enemyParameters.get("defaultCount"));
            if (!hero.isAlive()){
                endFightGame();
                game.defeatInFight();
            } else timer.start();
        }));
        enemyTimeline.setCycleCount(1);
    }
    private void run(){
        for(Cube cube:cubes) cube.move(enemyParameters.get("difficulty"));
        addCube();
        updatePlayer();
        checkBounds();
        updateRectangles();
        checkCollisions();
    }
    private void addPlayer(){
        double x=30;
        double y=109.2;
        player=new Player(x,y,images.get("laserShield"),"laser");
        fightField.getChildren().add(player);
    }
    private void updatePlayer(){
        player.setTranslateY(fightField.getHeight()/2.0-50);
    }
    private void addCube(){
        double height=fightField.getHeight();
        double x=fightField.getWidth()-60;
        Random random=new Random();
        Cube cube;
        switch (random.nextInt(3)){
            case 1:
                cube=new Cube(x,0,150,height,images.get("sword"),"sword");
                break;
            case 2:
                cube=new Cube(x,0,100,height,images.get("fire"),"fire");
                break;
            default:
                cube=new Cube(x,0,20,height,images.get("laser"),"laser");
        }

        fightField.getChildren().add(cube);
        for (Cube cube1:cubes) {
            if(Math.abs(cube1.getTranslateX()-cube.getTranslateX())<200-random.nextInt(30)){
                fightField.getChildren().remove(cube);
                return;
            }
        }
        cubes.add(cube);
    }
    private void checkBounds(){
        for(Cube cube:new HashSet<>(cubes)){
            if(cube.getTranslateX()<=0||cube.getTranslateX()>=fightField.getWidth()){
                fightField.getChildren().remove(cube);
                cubes.remove(cube);
            }
        }
    }
    private void attack(boolean isHeroAttack){
        checkBounds();
        updateRectangles();
        timer.stop();
        if (isHeroAttack) {
            heroAnimation.stop();
            heroAnimation.setOffsetY(heroParameters.get("fightOffsetY"));
            heroAnimation.setDuration(heroParameters.get("fightDuration"));
            heroAnimation.play(heroParameters.get("fightCount"));
            heroTimeline.play();
        } else {
            enemyAnimation.stop();
            enemyAnimation.setOffsetY(enemyParameters.get("fightOffsetY"));
            enemyAnimation.setDuration(enemyParameters.get("fightDuration"));
            enemyAnimation.play(enemyParameters.get("fightCount"));
            enemyTimeline.play();
        }
    }
    public void interruptFight(){
        endFightGame();
        game.interruptFight();
    }
    private void endFightGame(){
        timer.stop();
        enemyTimeline.stop();
        heroTimeline.stop();
        cubes.clear();
        fightField.getChildren().clear();
        showField.getChildren().clear();
    }
    private void updateRectangles(){
        cubes.forEach(cube -> cube.updateRectangle(fightField.getHeight()));
    }
    private void checkCollisions(){
        boolean flag=false;
        for(Cube cube:new HashSet<>(cubes)){
            if(player.getTranslateX()>=cube.getTranslateX()-15){
                flag=true;
               if(!player.getImage().equals(cube.getImage())){
                   attack(false);
                   cubes.remove(cube);
                   fightField.getChildren().remove(cube);
                   return;
               }
                cubes.remove(cube);
                fightField.getChildren().remove(cube);
               break;
            }
        }
        if (flag) count++;
        if (count>=enemyParameters.get("forPunch")){
            count=0;
            attack(true);
        }

    }
}