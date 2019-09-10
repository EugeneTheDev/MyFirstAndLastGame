package app.view;


import app.model.fightgame.FightGame;
import app.model.objects.FightableObject;
import app.model.objects.Hero;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.shape.Rectangle;


public class FightScreenController {
    @FXML
    private ProgressBar heroHealthBar;
    @FXML
    private ProgressBar enemyHealthBar;
    @FXML
    private Label enemyName;
    private FightGame game;


    @FXML
    private void handleInterrupt(){
        game.interruptFight();
    }
    public void updateHeroHealth(Hero hero){
        heroHealthBar.setProgress(hero.getHealth()/hero.getFullHealth());
    }

    public void updateEnemyHealth(FightableObject enemy){
        enemyHealthBar.setProgress(enemy.getHealth()/enemy.getFullHealth());
    }
    public void updateEnemyName(String name){
        enemyName.setText(name);
    }
    public void setGame(FightGame game) {
        this.game = game;
    }
}
