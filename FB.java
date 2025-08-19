import javax.swing.*; 
import java.awt.event.*; 
import java.awt.*;
import java.util.ArrayList;

public class FB {
  private int x, y, d; // x and y positions, and d stores direction (0: up, 1: left, 2: down, 3: right)
  private Image img; // Frog images for each direction
  private final int width = 50;  // The width of the frog
  private final int height = 50; // The height of the frog
  public FB(int xx, int yy, Image img) {
    x = xx;
    y = yy;

  }
  public void moveLeft() {
    x -= 10;
  }

  public void moveRight() {
    x += 10;
  }
  
 public void draw(Graphics g) {
   g.drawImage(img,x,y,50,50,null);
  }
}