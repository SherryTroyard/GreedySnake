package greedySnake;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

public class GreedySnakeBackground extends JPanel{
	private Image im;
	public GreedySnakeBackground(Image img){
	this.im=img;
	this.setOpaque(true);
	}
	public void paintComponent(Graphics g){
	super.paintComponents(g);
	g.drawImage(im,0,0,this.getWidth(),this.getHeight(),this);
	}
}
