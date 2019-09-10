package app.model;

import app.model.objects.*;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Created by Eugene on 10.08.17.
 */
public class GameObjects {
    private HashSet<Wall> walls;
    private HashSet<FightableObject> npcs;
    private HashSet<Tile> tiles;
    private HashSet<Chest> chests;
    private HashSet<Door> doors;
    private HashSet<Sign> signs;
    private HashSet<Tile> upperTiles;
    private HashSet<Wall> wallsDecor;
    private Portal portal;
    private Hero hero;

    public GameObjects(HashSet<Wall> wallsDecor,HashSet<Wall> walls, HashSet<FightableObject> npcs,HashSet<Tile> upperTiles,HashSet<Tile> tiles,
                       HashSet<Chest> chests,HashSet<Door> doors,HashSet<Sign> signs,Portal portal, Hero hero){
        this.hero=hero;
        this.npcs=npcs;
        this.walls=walls;
        this.wallsDecor=wallsDecor;
        this.tiles=tiles;
        this.chests=chests;
        this.doors=doors;
        this.portal=portal;
        this.upperTiles=upperTiles;
        this.signs=signs;
    }

    public Hero getHero() {
        return hero;
    }

    public HashSet<FightableObject> getNpcs() {
        return npcs;
    }

    public LinkedHashSet<GameObject> getAll(){
        LinkedHashSet<GameObject> gameObjects=new LinkedHashSet<>();
        gameObjects.addAll(tiles);
        gameObjects.addAll(upperTiles);
        gameObjects.addAll(walls);
        gameObjects.addAll(wallsDecor);
        gameObjects.addAll(npcs);
        gameObjects.addAll(chests);
        gameObjects.addAll(doors);
        gameObjects.addAll(signs);
        gameObjects.add(portal);
        gameObjects.add(hero);
        return gameObjects;
    }
    public HashSet<GameObject> getCollisable(){
        HashSet<GameObject> data=new HashSet<>();
        data.addAll(walls);
        data.addAll(npcs);
        data.addAll(chests);
        data.addAll(doors);
        data.addAll(signs);
        return data;
    }
    public Portal getPortal() {
        return portal;
    }
}
