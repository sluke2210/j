import java.awt.*;
import java.io.Serializable;

public class block  implements Serializable {
    int x;
    int y;
    Color col;
    int size;

    public block(int x, int y, Color col, int size){
        this.col=col;
        this.x=x;
        this.size= size;
        this.y = y;
    }
    public int getX(){return this.x;}
    public int getY(){return this.y;}
    public Color getCol(){return this.col;};
    public void setCol(Color col){
        this.col = col;
    }
    public String toString(){
        return this.x + " " + this.y + " " + this.col.getRed() + " " + this.col.getBlue() + " "  + this.col.getGreen();
    }

}
