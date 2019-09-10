package app.animation;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

/**
 * Created by Eugene on 27.10.17.
 */
public class SpriteAnimation  extends Transition {
    private final ImageView imageView;
    private int count;
    private final int columns;
    private int offsetX;
    private int offsetY;
    private final double width;
    private final double height;

    public SpriteAnimation(
            ImageView imageView,
            Duration duration,
            int count, int columns,
            int offsetX, int offsetY,
            double width, double height
    ){
        this.imageView = imageView;
        this.count = count;
        this.columns = columns;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;
        setCycleDuration(duration);
        setCycleCount(1);
        setInterpolator(Interpolator.LINEAR);
        this.imageView.setViewport(new Rectangle2D(offsetX, offsetY, width, height));

    }
    public void play(int count){
        setCycleCount(1);
        this.count=count;
        play();
    }
    public void setDuration(int ms){
        setCycleDuration(Duration.millis(ms));
    }
    public void playFull(){
        setCycleCount(Animation.INDEFINITE);
        super.play();
    }
    public void playFull(int count){
        this.count=count;
        setCycleCount(Animation.INDEFINITE);
        super.play();
    }

    @Override
    public void play() {
        setCycleCount(1);
        super.play();
    }

    public void setOffsetX(int x){
        this.offsetX = x;
    }
    public void setOffsetY(int y){
        this.offsetY = y;
    }
    protected void interpolate(double frac) {
        final int index = Math.min((int)Math.floor(count*frac), count-1);
        final double x = (index%columns)*width+offsetX;
        final double y = (index/columns)*height+offsetY;
        imageView.setViewport(new Rectangle2D(x, y, width, height));
    }
}
