import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

class FSE extends BaseFrame {

 private Image background;
 private Image fireboyGif, watergirlGif;
 private int fx = 100, fy = 720; // Fireboy position
 private int wx = 100, wy = 610; // Watergirl position
 private int speed = 5;

 // Fireboy physics
 private double v = 15, gravity = 1;
 private boolean isJumping = false;
 
 // Watergirl physics
 private double wv = 15;
 private boolean isWJumping = false;

 // Timer
 private long startTime;
 private Font timerFont;

 private int[][] map;
 
 int[][] colors;

 public FSE() {
  super("FSE", 1071, 813);
  background = loadImage("background1.0.png");
  fireboyGif = new ImageIcon("FireboyIdle.gif").getImage();
  watergirlGif = new ImageIcon("Watergirl_icon.png").getImage();

  startTime = System.currentTimeMillis();
  timerFont = new Font("Arial", Font.BOLD, 24);

  // Load map from image
  map = loadMap("background1.0.png");
 }

@Override
public void move() {
    // Fireboy Movement
    if (keys[LEFT]) {
        fx -= speed;
    }
    if (keys[RIGHT]) {
        fx += speed;
    }
    if (keys[UP] && !isJumping && isJumpAllowed()) { 
        v = -15;
        isJumping = true;
    }
    fy += v;
    v += gravity;

    // Fireboy collision
    if (isOnPlatform(fx, fy + 70)) {
        fy = (fy / 10) * 10;
        v = 0;
        isJumping = false;
    }

    // Watergirl Movement
    if (keys['A']) {
        wx -= speed;
    }
    if (keys['D']) {
        wx += speed;
    }
    if (keys['W'] && !isWJumping && isWJumpAllowed()) {
        wv = -15;
        isWJumping = true;
    }
    wy += wv;
    wv += gravity;

    // Watergirl collision
    if (isOnPlatform(wx, wy + 70)) {
        wy = (wy / 10) * 10;
        wv = 0;
        isWJumping = false;
    }
}

 
private boolean isJumpAllowed() {
    int headTopY = fy;           // Start at the head
    int headBottomY = fy - 70;   // Check up to 70px above

    int leftX = fx;
    int rightX = fx + 49; // character is 50px wide

    for (int y = headTopY; y >= headBottomY && y >= 0; y--) {
        for (int x = leftX; x <= rightX && x < map.length; x++) {
            if (x >= 0 && y < map[0].length) {
                if (map[x][y] == 1) {
                    return false;
                }
            }
        }
    }
    return true;
}
private boolean isWJumpAllowed() {
    int headTopY = wy;
    int headBottomY = wy - 70;

    int leftX = wx;
    int rightX = wx + 49; // Watergirl's width

    for (int y = headTopY; y >= headBottomY && y >= 0; y--) {
        for (int x = leftX; x <= rightX && x < map.length; x++) {
            if (x >= 0 && y < map[0].length) {
                if (map[x][y] == 1) {
                    return false;
                }
            }
        }
    }
    return true;
}




 @Override
 public void draw(Graphics g) {
  g.drawImage(background, 0, 0, null);
  g.drawImage(fireboyGif, fx, fy, 50, 70, null);
  g.drawImage(watergirlGif, wx, wy, 50, 70, null);

  long elapsedTime = (System.currentTimeMillis() - startTime) / 1000;
  g.setColor(Color.YELLOW);
  g.setFont(timerFont);
  g.drawString("Time: " + elapsedTime, 480, 40);
 }

 public int[][] loadMap(String name) {
    try {
        BufferedImage pic = ImageIO.read(new File(name));
        int w = pic.getWidth(), h = pic.getHeight();
         int[][] result = new int[w][h];
        colors = new int[w][h];

        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                int rgb = pic.getRGB(x, y);
                
                colors[x][y] = rgb;
                
                /*
                 Brick color hex code: approximately #4C4A26 (a dark olive/greenish brown)

Line (mortar) hex code: approximately #1E1E10 (a very dark olive/green-black) 
                 */
                 
                if (rgb == -9279436 ) {  // Light brown
                    result[x][y] = 1; 
                } else {
                    result[x][y] = 0;
                }
            }
        }

        return result;
    } catch (IOException e) {
        System.out.println("Map loading error: " + e);
        return null;
    }
 }

 public boolean isOnPlatform(int x, int y) {
   if (x < 0 || x >= map.length || y < 0 || y >= map[0].length){ 
     return false;
   }
     // Check if any pixels under character are solid
     for (int i = 0; i < 50; i++) { // character width
         int checkX = x + i;
         if (checkX < map.length && y < map[0].length) {
             if (map[checkX][y] == 1) 
               return true;
         }
     }
     return false;
 }

 public static void main(String[] args) {
  new FSE();
 }
}