package app.model.objects;

import app.model.Direction;
import app.model.items.Item;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Eugene on 17.07.17.
 */
public abstract class FightableObject extends GameObject {
    protected int damage, health;
    protected boolean isAlive;
    protected Image image;
    protected int fullHealth;
    protected String name,description,itemsToOffer;
    protected ArrayList<Class<?extends Item>> offerableItems;
    protected Map<String,Integer> parameters;
    protected Rectangle2D viewport;

    public FightableObject(int x, int y, int width, int height, int damage, int health,
                           ImageView imageView,Image image,Direction direction) {
        super(x, y, width, height,imageView);
        this.direction=direction;
        this.damage = damage;
        this.health = health;
        fullHealth=this.health;
        this.image=image;
        this.parameters =new HashMap<>();
        offerableItems=new ArrayList<>();
        isAlive = true;
        initParameters();
    }

    public FightableObject(int x, int y, int damage, int health,ImageView imageView,Image image,Direction direction) {
        super(x, y,imageView);
        this.direction=direction;
        this.damage = damage;
        this.health = health;
        fullHealth=this.health;
        this.image=image;
        this.parameters =new HashMap<>();
        offerableItems=new ArrayList<>();
        isAlive = true;
        initParameters();
    }
    public String getItemsToOffer() {
        return itemsToOffer;
    }

    public String getDescription() {

        return description;
    }

    public ArrayList<Class<?extends Item>> getOfferableItems() {
        return offerableItems;
    }

    public String getName() {
        return name;
    }

    public abstract void playAnimation();

    public double getFullHealth() {
        return fullHealth;
    }

    public void fight(FightableObject object) {
        object.decrementHealth(damage);
    }

    private void decrementHealth(int point) {
        health -= point;
        if (health <= 0) isAlive = false;
    }

    public Rectangle2D getViewport() {
        return viewport;
    }

    protected abstract void initParameters();

    public double getHealth() {
        return health;
    }

    public Image getImage() {
        return image;
    }

    public Direction getDirection() {
        return direction;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public Map<String, Integer> getParameters() {
        return Collections.unmodifiableMap(parameters);
    }
}
