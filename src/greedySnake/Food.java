package greedySnake;

import java.awt.*;
import java.util.Random;

import javax.swing.ImageIcon;
public class Food {
  private int sizex=20,sizey=20;
  private int posx,posy;
  private ImageIcon img;
 public void GenerateFood() {
	  Random Position=new Random();
	  posx=Position.nextInt(630)+180;
	  posy=Position.nextInt(530)+100;
	  }
  public void draw(Graphics g) {
	  img=new ImageIcon("Image/frog.png");
	  g.drawImage(img.getImage(), posx, posy, sizex, sizey, img.getImageObserver());
  }
  public int GetPosx() {
	  return posx;
  }
  public int GetPosy() {
	  return posy;
  }
}
