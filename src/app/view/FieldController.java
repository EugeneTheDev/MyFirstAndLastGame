package app.view;

import app.Game;
import app.model.items.Item;
import app.model.objects.Hero;
import javafx.fxml.FXML;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;

import java.util.HashMap;


/**
 * Created by Eugene on 28.10.17.
 */
public class FieldController {
    @FXML
    private ImageView first;
    @FXML
    private ImageView second;
    @FXML
    private ImageView third;
    @FXML
    private ImageView fourth;
    @FXML
    private ImageView fifth;
    @FXML
    private ProgressBar healthBar;
    private HashMap<ImageView,Item> items;
    private Game game;

    @FXML
    public void initialize(){
        items=new HashMap<>(5);
        items.put(first,null);
        items.put(second,null);
        items.put(third,null);
        items.put(fourth,null);
        items.put(fifth,null);
    }
    @FXML
    private void handleMenu(){
        game.pause();
    }
    public void updateHeroHealth(Hero hero){
        healthBar.setProgress(hero.getHealth()/hero.getFullHealth());
        //healthBar.setProgress(0.5);

    }
    public void addItem(Item item){
        if(items.get(first)==null){
            first.setImage(item.getImage());
            items.put(first,item);
        } else if(items.get(second)==null) {
            second.setImage(item.getImage());
            items.put(second, item);
        } else if(items.get(third)==null) {
            third.setImage(item.getImage());
            items.put(third, item);
        } else if(items.get(fourth)==null) {
            fourth.setImage(item.getImage());
            items.put(fourth, item);
        } else if(items.get(fifth)==null) {
            fifth.setImage(item.getImage());
            items.put(fifth, item);
        }
    }
    public void useItem(int number){
        if(number==1&&items.get(first)!=null){
            Item item=items.get(first);
            item.doEffect(game,number);
        } else if(number==2&&items.get(second)!=null){
            Item item=items.get(second);
            item.doEffect(game,number);
        } else if(number==3&&items.get(third)!=null){
            Item item=items.get(third);
            item.doEffect(game,number);
        }  else if(number==4&&items.get(fourth)!=null){
            Item item=items.get(fourth);
            item.doEffect(game,number);
        }  else if(number==5&&items.get(fifth)!=null){
            Item item=items.get(fifth);
            item.doEffect(game,number);
        }
    }
    public void removeItem(int number,Hero hero){
        if(number==1){
            hero.getItems().remove(items.get(first));
            first.setImage(null);
            items.put(first,null);
        } else if(number==2){
            hero.getItems().remove(items.get(second));
            second.setImage(null);
            items.put(second,null);
        } else if(number==3){
            hero.getItems().remove(items.get(third));
            third.setImage(null);
            items.put(third,null);
        } else if(number==4){
            hero.getItems().remove(items.get(fourth));
            fourth.setImage(null);
            items.put(fourth,null);
        } else if(number==5){
            hero.getItems().remove(items.get(fifth));
            fifth.setImage(null);
            items.put(fifth,null);
        }
    }
    public void clearInventory(){
        if(items.get(first)!=null){
            first.setImage(null);
            items.put(first,null);
        }
        if(items.get(second)!=null) {
            second.setImage(null);
            items.put(second, null);
        }
        if(items.get(third)!=null) {
            third.setImage(null);
            items.put(third, null);
        }
        if(items.get(fourth)!=null) {
            fourth.setImage(null);
            items.put(fourth, null);
        }
        if(items.get(fifth)!=null) {
            fifth.setImage(null);
            items.put(fifth, null);
        }
    }
    public Item getItemByNumber(int number){
        switch (number){
            case 1:
                return items.get(first);
            case 2:
                return items.get(second);
            case 3:
                return items.get(third);
            case 4:
                return items.get(fourth);
            case 5:
                return items.get(fifth);
            default:
                return null;
        }
    }
    public void setGame(Game game) {
        this.game = game;
    }
}
