package greedySnake;
import javax.swing.*;
public class GreedySnake {
	static final int locationx=600,locationy=100,width=1024,height=760;
	public static void main(String args[]) {
   JFrame SnakeWindow=new JFrame("̰����  ���Ʒ���: W��  S��  A�� D�� ���¿ո�ɿ�ʼ��Ϸ,����Ϸ�а��¿ո�ʵ����ͣ");
   SnakeWindow.setBounds(locationx,locationy,width,height);
   SnakeWindow.setResizable(false);
   SnakeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   GreedySnakePanel SnakePanel=new GreedySnakePanel();
   SnakeWindow.add(SnakePanel);
   SnakeWindow.setVisible(true);
	}
}
