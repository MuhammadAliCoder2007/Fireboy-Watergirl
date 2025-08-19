import javax.swing.*; 
import java.awt.event.*; 
import java.awt.*;
import java.util.ArrayList;

class FBWG extends JFrame {
 GamePanel game;
 
    public FBWG (){
     super("Basic Game Example");
      
     setResizable(false);
     game = new GamePanel();
     add(game);
     pack();   // Set size of JFame == size of stuff on it.
     
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  setVisible(true);
    }
    
    
    public static void main(String [] args){
     FBWG eg1 = new FBWG();
    }
}