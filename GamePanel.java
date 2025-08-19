import javax.swing.*; 
import java.awt.event.*; 
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class GamePanel extends JPanel implements KeyListener, ActionListener{
 int x = 100;
 Timer timer;
 boolean []keys;
 
 public GamePanel(){
  setPreferredSize(new Dimension(1100,700));
  keys = new boolean[2000];
  
  setFocusable(true);

  requestFocus();
  addKeyListener(this);
  
  timer = new Timer(10, this);
  timer.start();
 }

 @Override
 public void keyPressed(KeyEvent e){
  keys[e.getKeyCode()] = true;
  
 }

 @Override
 public void keyReleased(KeyEvent e){
  keys[e.getKeyCode()] = false;
 }
 
 @Override
 public void keyTyped(KeyEvent e){}
 
 @Override
 public void actionPerformed(ActionEvent e){
  // VK_A
  if(keys[KeyEvent.VK_LEFT]){
   x -= 10;
  }
  if(keys[KeyEvent.VK_RIGHT]){
   x += 10;
  }
  repaint();
 }
 
 @Override
 public void paint(Graphics g){
  g.setColor(new Color(0,0,0));
  g.fillRect(0, 0, getWidth(), getHeight());

 }
}