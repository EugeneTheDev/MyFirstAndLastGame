package app.model;

import app.model.items.*;
import app.model.objects.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Created by Eugene on 10.08.17.
 */
public class LevelLoader {
    private String levels,stories;
    private HashMap<String, Image> objectsImages;
    private int fieldWidth, fieldHeight;

    public LevelLoader(String levels,String stories,HashMap<String, Image> objectsImages) {
        this.levels = levels;
        this.stories=stories;
        this.objectsImages = objectsImages;
    }

    public GameObjects getLevel(int level) {
        HashSet<Wall> walls = new HashSet<>();
        HashSet<FightableObject> npcs = new HashSet<>();
        HashSet<Tile> tiles = new HashSet<>();
        HashSet<Tile> upperTiles = new HashSet<>();
        HashSet<Chest> chests=new HashSet<>();
        HashSet<Door> doors=new HashSet<>();
        HashSet<Sign> signs=new HashSet<>();
        HashSet<Wall> wallsDecor=new HashSet<>();
        Portal portal=null;
        Hero hero = null;

        int loopLevel;
        if (level > 60) {
            loopLevel = level % 60;
        } else {
            loopLevel = level;
        }
        int x=0;
        int y = 70;
        int maxX=0;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(levels)))) {
            int readLevel = 0;

            boolean isLevelMap = false;

            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("Level:")) {
                    readLevel = Integer.valueOf(line.split(" ")[1]);
                    continue;
                }
                if (readLevel == loopLevel) {
                    if (line.length() == 0) {
                        boolean isEnd = isLevelMap;

                        isLevelMap = !isLevelMap;

                        if (isEnd && !isLevelMap) {
                            break;
                        } else {
                            continue;
                        }
                    }
                    if (isLevelMap) {
                        x = 70;

                        char[] chars = line.toCharArray();
                        for (int i = 0; i <chars.length; i++)
                            {
                            switch (chars[i]) {
                                case 'T':
                                    walls.add(new Wall(x, y, new ImageView(objectsImages.get("tree"))));
                                    tiles.add(chooseTile(chars,i,x,y));
                                    break;
                                case '0':
                                    walls.add(new Wall(x, y,28,65, new ImageView(objectsImages.get("mBall"))));
                                    tiles.add(chooseTile(chars,i,x,y));
                                    break;
                                case 'S':
                                    walls.add(new Wall(x, y,98,128, new ImageView(objectsImages.get("mStatue"))));
                                    tiles.add(chooseTile(chars,i,x,y));
                                    break;
                                case 'X':
                                    walls.add(new Wall(x, y, new ImageView(objectsImages.get("wall"))));
                                    break;
                                case 'C':
                                    wallsDecor.add(new Wall(x, y, new ImageView(objectsImages.get("candle"))));
                                    walls.add(new Wall(x, y, new ImageView(objectsImages.get("wall"))));
                                    break;
                                case 'B':
                                    walls.add(new Wall(x, y, new ImageView(objectsImages.get("bookshelf"))));
                                    break;
                                case 'K':
                                    walls.add(new Wall(x, y, new ImageView(objectsImages.get("knight"))));
                                    tiles.add(chooseTile(chars,i,x,y));
                                    break;
                                case 'm':
                                    Image imageTreeMonster=objectsImages.get("treeMonster");
                                    i++;
                                    switch (chars[i]){
                                        case 'R':
                                            npcs.add(new TreeMonster(x, y, new ImageView(imageTreeMonster),imageTreeMonster,Direction.RIGHT));
                                            break;
                                        case 'L':
                                            npcs.add(new TreeMonster(x, y, new ImageView(imageTreeMonster),imageTreeMonster,Direction.LEFT));
                                            break;
                                        case 'U':
                                            npcs.add(new TreeMonster(x, y, new ImageView(imageTreeMonster),imageTreeMonster,Direction.UP));
                                            break;
                                        case 'D':
                                            npcs.add(new TreeMonster(x, y, new ImageView(imageTreeMonster),imageTreeMonster,Direction.DOWN));
                                            break;

                                    }
                                    tiles.add(chooseTile(chars,i,x,y));
                                    break;
                                case 'b':
                                    Image imageBlueSubstance=objectsImages.get("blueSubstance");
                                    npcs.add(new BlueSubstance(x,y,new ImageView(imageBlueSubstance),imageBlueSubstance));
                                    tiles.add(chooseTile(chars,i,x,y));
                                    break;
                                case 'g':
                                    Image imageSkeleton=objectsImages.get("gSkeleton");
                                    i++;
                                    switch (chars[i]){
                                        case 'R':
                                            npcs.add(new GiantSkeleton(x, y, new ImageView(imageSkeleton),imageSkeleton,Direction.RIGHT));
                                            break;
                                        case 'L':
                                            npcs.add(new GiantSkeleton(x, y, new ImageView(imageSkeleton),imageSkeleton,Direction.LEFT));
                                            break;
                                        case 'U':
                                            npcs.add(new GiantSkeleton(x, y, new ImageView(imageSkeleton),imageSkeleton,Direction.UP));
                                            break;
                                        case 'D':
                                            npcs.add(new GiantSkeleton(x, y, new ImageView(imageSkeleton),imageSkeleton,Direction.DOWN));
                                            break;

                                    }
                                    tiles.add(chooseTile(chars,i,x,y));
                                    break;
                                case 'e':
                                    Image imageEvilEye=objectsImages.get("evilEye");
                                    npcs.add(new EvilEye(x, y, new ImageView(imageEvilEye),imageEvilEye));
                                    tiles.add(chooseTile(chars,i,x,y));
                                    break;
                                case 'D':
                                    doors.add(new Door(x,y,new ImageView(objectsImages.get("door"))));
                                    tiles.add(new Tile(x, y, new ImageView(objectsImages.get("wall"))));
                                    break;
                                case '@':
                                    portal=new Portal(x,y,new ImageView(objectsImages.get("portal")));
                                    tiles.add(chooseTile(chars,i,x,y));
                                    break;
                                case 'H':
                                    i++;
                                    switch (chars[i]){
                                        case '+':
                                            chests.add(new Chest(x,y,new FirstAidKit(objectsImages.get("firstAidKit")),
                                                    new ImageView(objectsImages.get("chest"))));
                                            break;
                                        case '$':
                                            chests.add(new Chest(x,y,new Key(objectsImages.get("key")),
                                                    new ImageView(objectsImages.get("chest"))));
                                            break;
                                        case 'G':
                                            chests.add(new Chest(x,y,new Gold(objectsImages.get("gold")),
                                                    new ImageView(objectsImages.get("chest"))));
                                            break;
                                        case 'M':
                                            chests.add(new Chest(x,y,new Meat(objectsImages.get("meat")),
                                                    new ImageView(objectsImages.get("chest"))));
                                            break;
                                        case 'P':
                                            chests.add(new Chest(x,y,new Potion(objectsImages.get("potion")),
                                                    new ImageView(objectsImages.get("chest"))));
                                            break;
                                        case 'L':
                                            chests.add(new Chest(x,y,new Leaf(objectsImages.get("leaf")),
                                                    new ImageView(objectsImages.get("chest"))));
                                            break;
                                        case 'A':
                                            chests.add(new Chest(x,y,new PowerfulArtifact(objectsImages.get("pArt")),
                                                    new ImageView(objectsImages.get("chest"))));
                                            break;

                                    }
                                    tiles.add(chooseTile(chars,i,x,y));
                                    break;
                                case '#':
                                    String id=""+chars[i];
                                    i++;
                                    id+=chars[i];
                                    signs.add(new Sign(x,y,id,new ImageView(objectsImages.get("sign"))));
                                    tiles.add(chooseTile(chars,i,x,y));
                                    break;
                                case '&':
                                    Image imageHero=objectsImages.get("hero");
                                    hero = new Hero(x, y, new ImageView(imageHero),imageHero);
                                    tiles.add(chooseTile(chars,i,x,y));
                                    break;
                                case '.':
                                    tiles.add(new Tile(x, y, new ImageView(objectsImages.get("tile"))));
                                    break;
                                case '*':
                                    tiles.add(new Tile(x, y, new ImageView(objectsImages.get("stoneTile"))));
                                    break;
                                case '-':
                                    tiles.add(new Tile(x, y, new ImageView(objectsImages.get("darkTile"))));
                                    break;
                                case '"':
                                    upperTiles.add(new Tile(x, y, new ImageView(objectsImages.get("grass"))));
                                    tiles.add(chooseTile(chars,i,x,y));
                                    break;
                                case 'O':
                                    upperTiles.add(new Tile(x, y, new ImageView(objectsImages.get("blood"))));
                                    tiles.add(chooseTile(chars,i,x,y));
                                    break;
                                case 'W':
                                    upperTiles.add(new Tile(x, y, new ImageView(objectsImages.get("water"))));
                                    tiles.add(chooseTile(chars,i,x,y));
                                    break;

                            }
                            if(x>maxX) maxX=x;
                            x += Model.FIELD_CELL_SIZE;
                        }
                        y += Model.FIELD_CELL_SIZE;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        setSings(signs,level);
        fieldWidth =maxX+300;
        fieldHeight =y+200;
        return new GameObjects(wallsDecor,walls, npcs, upperTiles,tiles,chests,doors,signs,portal, hero);
    }
    private Tile chooseTile(char[] chars,int startPosition,int x,int y){
        boolean flag=false;
        for (int i = ++startPosition; i <chars.length ; i++) {
            switch (chars[i]){
                case '.':return new Tile(x, y, new ImageView(objectsImages.get("tile")));
                case '*':return new Tile(x, y, new ImageView(objectsImages.get("stoneTile")));
                case '-':return new Tile(x, y, new ImageView(objectsImages.get("darkTile")));
                case 'X':
                    flag=true;
                break;
            }
            if (flag) break;
        }
        flag=false;
        for (int i = --startPosition; i >=0 ; i--) {
            switch (chars[i]){
                case '.':return new Tile(x, y, new ImageView(objectsImages.get("tile")));
                case '*':return new Tile(x, y, new ImageView(objectsImages.get("stoneTile")));
                case '-':return new Tile(x, y, new ImageView(objectsImages.get("darkTile")));
                case 'X':
                    flag=true;
                    break;
            }
            if (flag) break;
        }
        return new Tile(x, y, new ImageView(objectsImages.get("tile")));
    }
    private void setSings(HashSet<Sign> sings,int level){
        int loopLevel;
        if (level > 60) {
            loopLevel = level % 60;
        } else {
            loopLevel = level;
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(this.stories),"UTF-8"))) {
            int readLevel = 0;
            boolean isText = false;
            String buffer="";
            String line;
            while ((line = reader.readLine()) != null) {

                if (line.contains("Signs:")) {
                    readLevel = Integer.valueOf(line.split(" ")[1]);
                    continue;
                }
                if (readLevel == loopLevel) {
                    if (line.equals("^^^^^^^^^^^^^^")) {
                        boolean isEnd = isText;

                        isText = !isText;

                        if (isEnd && !isText) {
                            break;
                        } else {
                            continue;
                        }
                    }
                    if (isText) {
                        if(line.length()==0){
                            buffer=buffer.trim();
                            String[] forSign=buffer.split(": ");
                            sings.forEach(sign -> {
                                if(sign.checkId(forSign[0])){
                                    sign.setText(forSign[1]);
                                    return;
                                }
                            });
                            buffer="";
                        } else{
                            buffer+=line+"\n";
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getStory(int level) {
        ArrayList<String> storiesList=new ArrayList<>();
        int loopLevel;
        if (level > 60) {
            loopLevel = level % 60;
        } else {
            loopLevel = level;
        }
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(this.stories),"UTF-8"))) {
            int readLevel = 0;
            boolean isText = false;
            String buffer="";
            String line;
            while ((line = reader.readLine()) != null) {

                if (line.contains("Story:")) {
                    readLevel = Integer.valueOf(line.split(" ")[1]);
                    continue;
                }
                if (readLevel == loopLevel) {
                    if (line.equals("^^^^^^^^^^^^^^")) {
                        boolean isEnd = isText;

                        isText = !isText;

                        if (isEnd && !isText) {
                            break;
                        } else {
                            continue;
                        }
                    }
                    if (isText) {
                        if(line.length()==0){
                            storiesList.add(buffer);
                            buffer="";
                        } else{
                            buffer+=line+"\n";
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        storiesList.forEach(s -> s=s.trim());
        return storiesList;
    }

    public int getFieldWidth() {
        return fieldWidth;
    }

    public int getFieldHeight() {
        return fieldHeight;
    }
}

