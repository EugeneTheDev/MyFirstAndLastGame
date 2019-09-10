package app.model.objects;





import javafx.scene.image.ImageView;


/**
 * Created by Eugene on 28.07.2017.
 */
public class Wall extends GameObject {
    public Wall(int x, int y,ImageView imageView) {
        super(x, y,imageView);
        getChildren().add(this.imageView);
    }
    public Wall(int x, int y,double width,double height,ImageView imageView){
        super(x,y,width,height,imageView);
        getChildren().add(this.imageView);
    }


}
