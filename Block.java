import java.awt.*;

public class Block {
    int x;
    int y;
    int width;
    int height;
    Image image;
    String direction;
    int velocityX=0;
    int velocityY = 0;
    int Blocktilesize=32;
    // we are creating this class in order to store that where each tile...
    // would be and what would it store.
    int startX;
    int startY;
    public Block() {
        this.x = 0;
        this.y = 0;
    }
    Block(Image image,int xx,int yy,int width,int height) {
        this.image = image;
        this.x = xx;
        this.y = yy;
        this.width = width;
        this.height = height;
        this.startX = xx;
        this.startY = yy;

    }
    public void reset() {
        this.x = startX;
        this.y = startY;
    }

    public void updateDirection(String new_direction) {
        this.direction = new_direction;
        this.x+=this.velocityX;
        this.y+=this.velocityY;

    }

}
