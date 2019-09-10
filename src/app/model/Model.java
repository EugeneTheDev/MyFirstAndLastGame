package app.model;


import app.Game;
import app.model.objects.FightableObject;
import app.model.objects.GameObject;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Eugene on 17.07.17.
 */
public class Model {
    public static final int FIELD_CELL_SIZE = 55;
    private int currentLevel = 1;
    private GameObjects gameObjects;
    private HashMap<String,Image> objectsImages=new HashMap<>();
    private LevelLoader loader = new LevelLoader("levels.txt","stories.txt",objectsImages);
    private ArrayList<String> storiesList;
    private double fieldWidth,fieldHeight;


    public void loadObjectsImages(){
        objectsImages.put("hero",new Image(Model.class.getClassLoader().getResourceAsStream("sprites/Hero.png")));
        objectsImages.put("wall",new Image(Model.class.getClassLoader().getResourceAsStream("objects/Wall.jpg"),
                FIELD_CELL_SIZE,FIELD_CELL_SIZE,false,true));
        objectsImages.put("tile",new Image(Model.class.getClassLoader().getResourceAsStream("objects/Tile.jpg"),
                FIELD_CELL_SIZE,FIELD_CELL_SIZE,false,true));
        objectsImages.put("firstAidKit",new Image(Game.class.getClassLoader().getResourceAsStream("objects/Heart.png")));
        objectsImages.put("chest",new Image(Game.class.getClassLoader().getResourceAsStream("objects/Chest.png")));
        objectsImages.put("door",new Image(Game.class.getClassLoader().getResourceAsStream("objects/Door.png")));
        objectsImages.put("key",new Image(Game.class.getClassLoader().getResourceAsStream("objects/Key.png")));
        objectsImages.put("portal",new Image(Model.class.getClassLoader().getResourceAsStream("objects/Portal.gif"),
                FIELD_CELL_SIZE*2,FIELD_CELL_SIZE*1.3,false,true));
        objectsImages.put("sign",new Image(Model.class.getClassLoader().getResourceAsStream("objects/Sign.png"),
                FIELD_CELL_SIZE,FIELD_CELL_SIZE,false,true));
        objectsImages.put("gSkeleton",new Image(Model.class.getClassLoader().getResourceAsStream("sprites/GiantSkeleton.png")));
        objectsImages.put("gold",new Image(Model.class.getClassLoader().getResourceAsStream("objects/Gold.png")));
        objectsImages.put("knight",new Image(Model.class.getClassLoader().getResourceAsStream("decor/Knight.png"),
                FIELD_CELL_SIZE*2.5,FIELD_CELL_SIZE*2.5,false,true));
        objectsImages.put("grass",new Image(Model.class.getClassLoader().getResourceAsStream("decor/Grass.png"),
                FIELD_CELL_SIZE,FIELD_CELL_SIZE,false,true));
        objectsImages.put("bookshelf",new Image(Model.class.getClassLoader().getResourceAsStream("decor/Bookshelf.jpg"),
                FIELD_CELL_SIZE,FIELD_CELL_SIZE,false,true));
        objectsImages.put("blood",new Image(Model.class.getClassLoader().getResourceAsStream("decor/Blood.png"),
                FIELD_CELL_SIZE,FIELD_CELL_SIZE,false,true));
        objectsImages.put("water",new Image(Model.class.getClassLoader().getResourceAsStream("decor/Water.png"),
                FIELD_CELL_SIZE,FIELD_CELL_SIZE,false,true));
        objectsImages.put("candle",new Image(Model.class.getClassLoader().getResourceAsStream("decor/Candle.gif"),
                FIELD_CELL_SIZE,FIELD_CELL_SIZE,false,true));
        objectsImages.put("tree",new Image(Model.class.getClassLoader().getResourceAsStream("decor/Tree.png"),
                FIELD_CELL_SIZE*2,FIELD_CELL_SIZE*2,false,true));
        objectsImages.put("treeMonster",new Image(Model.class.getClassLoader().getResourceAsStream("sprites/TreeMonster.png")));
        objectsImages.put("blueSubstance",new Image(Model.class.getClassLoader().getResourceAsStream("sprites/BlueSubstance.png")));
        objectsImages.put("meat",new Image(Model.class.getClassLoader().getResourceAsStream("objects/Meat.png")));
        objectsImages.put("evilEye",new Image(Model.class.getClassLoader().getResourceAsStream("sprites/EvilEye.png")));
        objectsImages.put("potion",new Image(Model.class.getClassLoader().getResourceAsStream("objects/Potion.png")));
        objectsImages.put("leaf",new Image(Model.class.getClassLoader().getResourceAsStream("objects/Leaf.png")));
        objectsImages.put("pArt",new Image(Model.class.getClassLoader().getResourceAsStream("objects/PowerfulArtifact.png")));
        objectsImages.put("mStatue",new Image(Model.class.getClassLoader().getResourceAsStream("objects/MagesticStatue.png")));
        objectsImages.put("mBall",new Image(Model.class.getClassLoader().getResourceAsStream("objects/MagesticBall.png")));
        objectsImages.put("darkTile",new Image(Model.class.getClassLoader().getResourceAsStream("objects/DarkTile.png"),
                FIELD_CELL_SIZE,FIELD_CELL_SIZE,false,true));
        objectsImages.put("stoneTile",new Image(Model.class.getClassLoader().getResourceAsStream("objects/StoneTile.png"),
                FIELD_CELL_SIZE,FIELD_CELL_SIZE,false,true));
    }


    public boolean isTheEnd(){
        if (currentLevel==4){
            currentLevel=1;
            return true;
        }
        return false;
    }
    private void checkCollisionsAndMove(GameObject object, Direction direction) {
        GameObject gameObject = checkCollisions(object, direction);
        if (gameObject == null) ((Movable) object).move(direction);
    }

    private GameObject checkCollisions(GameObject object, Direction direction) {
        for (GameObject gameObject : gameObjects.getCollisable()) {
            if (gameObject != object) {
                if (object.isCollision(gameObject, direction)) return gameObject;
            }
        }
        return null;
    }

    public void startLevel(int level) {
        currentLevel = level;
        gameObjects = loader.getLevel(level);
        fieldHeight=loader.getFieldHeight();
        fieldWidth=loader.getFieldWidth();
        storiesList =loader.getStory(currentLevel);
    }

    public double getFieldWidth() {
        return fieldWidth;
    }

    public double getFieldHeight() {
        return fieldHeight;
    }

    public ArrayList<String> getStoriesList() {
        return storiesList;
    }

    public void restart() {
        startLevel(currentLevel);
    }

    public void startNextLevel() {
        currentLevel++;
        restart();
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void move(Direction direction) {
        if (direction != Direction.NOTHING)
            gameObjects.getHero().setDirection(direction);
        checkCollisionsAndMove(gameObjects.getHero(), direction);
        animateNpcs();
    }
    public void animateNpcs(){
        gameObjects.getNpcs().forEach((npc)->{
            if(Math.sqrt((npc.getTranslateY()-gameObjects.getHero().getTranslateY())*
                    (npc.getTranslateY()-gameObjects.getHero().getTranslateY())+
                    (npc.getTranslateX()-gameObjects.getHero().getTranslateX())*
                    (npc.getTranslateX()-gameObjects.getHero().getTranslateX()))<2000)
                npc.playAnimation();
        });
    }

   /* public FightableObject fight() {
        GameObject collisions = checkCollisions(gameObjects.getHero(), gameObjects.getHero().getDirection());
        if (collisions != null &&
                collisions instanceof FightableObject) {
            gameObjects.getHero().fight((FightableObject) collisions);
        }
        for (FightableObject npc : new HashSet<>(gameObjects.getNpcs())) {
            if (!npc.isAlive()) {
                gameObjects.getNpcs().remove(npc);
                return npc;
            }
        }
        return null;
    }*/
    public FightableObject fight(){
        GameObject collisions = checkCollisions(gameObjects.getHero(), gameObjects.getHero().getDirection());
        return collisions != null && collisions instanceof FightableObject?(FightableObject) collisions:null;
    }
    public GameObject getNearObject(){
        return checkCollisions(gameObjects.getHero(), gameObjects.getHero().getDirection());
    }
    public GameObjects getGameObjects() {
        return gameObjects;
    }


}
