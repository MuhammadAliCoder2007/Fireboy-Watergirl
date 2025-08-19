import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

class FSE extends BaseFrame{
 Guy guy;
 
 public FSE(){
  super("FSE", 800,600);
  guy = new Guy(500,400,"scorpion",9); 
 } 
 
 public void move(){
  guy.move(keys);
 }
 
 @Override
 public void draw(Graphics g){
  if(guy==null)return;
  g.setColor(Color.WHITE);
  g.fillRect(0,0,800,600);
  guy.draw(g); 
 }
 
 public static void main(String[] args) {
  new FSE();
    } 
}


class Guy{
 private int x,y;
 private Image[]pics;
 private int dir, frame, delay;
 private boolean moving;
 public static final int LEFT = 0, RIGHT = 1, WAIT = 2;
  
 public Guy(int x, int y, String name, int n){
  this.x=x;
  this.y=y;
  dir = RIGHT;
  frame = 0;
  delay = 0;
  moving = false;
  pics = new Image[n];  
  for(int i = 0; i<n; i++){
   pics[i] = new ImageIcon(name+"/"+name+i+".png").getImage();
  }
 }

 public void move(boolean []keys){
  if(keys[KeyEvent.VK_RIGHT]){
   x += 10;
   dir = RIGHT;
   frame++;
  }
  else if(keys[KeyEvent.VK_LEFT]){
   x -= 10;
   dir = LEFT;
   frame++;
  }
 }

 public void draw(Graphics g){  
  int n = pics.length;
  // Draw out all images so you can see which one it pics
  for(int i=0; i<n; i++){
   g.drawImage(pics[i], 50 + i*75, 20, null);
  }
  int current = frame/2 % n;
  g.setColor(Color.RED);
  g.drawRect(50 + current*75, 20, 60, 100);
  
  Image p = pics[current];
  if(dir == RIGHT){
   g.drawImage(p, x, y, null);
  }
  else{
   int w = p.getWidth(null);
   int h = p.getHeight(null);
   g.drawImage(p, x + w, y, -w, h, null);
  }
 }
}
