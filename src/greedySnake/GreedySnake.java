package greedySnake;
import javax.swing.*;
public class GreedySnake {
	static final int locationx=600,locationy=100,width=1024,height=760;
	public static void main(String args[]) {
   JFrame SnakeWindow=new JFrame("贪吃蛇  控制方向: W↑  S↓  A← D→ 按下空格可开始游戏,在游戏中按下空格实现暂停");
   SnakeWindow.setBounds(locationx,locationy,width,height);
   SnakeWindow.setResizable(false);
   SnakeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   GreedySnakePanel SnakePanel=new GreedySnakePanel();
   SnakeWindow.add(SnakePanel);
   SnakeWindow.setVisible(true);
	}
}
