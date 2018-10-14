package greedySnake;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
public class GreedySnakePanel extends JPanel implements ActionListener,KeyListener{
	private JButton Start,Quit,Level;
	private JLabel Scoreyard;
	private LevelWindow levelWindow;
	private ImageIcon img;
	private static final int GameSizex=660,GameSizey=560;
	private static final int LeftBound=180, RightBound=840, UpBound=100, DownBound=660;
	public static final int Up=1,Down=2,Left=3,Right=4;
	private Boolean GameState=false,Pause=false,Failed=false;
	private Boolean NeedFood=true,FoodInBody=false;
	private int score=0;
	private int level=60;
	Snake snake;
	Food food;
	PaintThread go=new PaintThread();
	public GreedySnakePanel() {
		setLayout(null);
		img=new ImageIcon("Image/start.png");
		Start=new JButton();
		Start.setIcon(img);
		Start.setBounds(25,250,150,70);
		Start.setFocusPainted(false);
		add(Start);
		img=new ImageIcon("Image/quit.png");
		Quit=new JButton();
		Quit.setIcon(img);
		Quit.setBounds(25,580,150,70);
		Quit.setFocusPainted(false);
		add(Quit);
		img=new ImageIcon("Image/score.png");
		Scoreyard=new JLabel();
		Scoreyard.setIcon(img);
		Scoreyard.setBounds(25, 450, 150, 100);
		add(Scoreyard);
		img=new ImageIcon("Image/level.png");
		Level=new JButton();
		Level.setIcon(img);
		Level.setBounds(25,350,150,70);
		Level.setFocusPainted(false);
		add(Level);
		img=new ImageIcon("Image/snake.jpg");
		GreedySnakeBackground Background=new GreedySnakeBackground(img.getImage());
		Background.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
		add(Background);
		Start.addActionListener(this);
		Quit.addActionListener(this);
		Level.addActionListener(this);
		this.addKeyListener(this);
		this.setOpaque(true);
		snake=new Snake(this);
		food=new Food();
	}
	public void keyPressed(KeyEvent e) {
		if(GameState==true) { 
		switch(e.getKeyCode()) {
		case  KeyEvent.VK_W:
			if(snake.GetDirection()!=Down) snake.SetDirection(Up);
			break;
		case  KeyEvent.VK_S:
			if(snake.GetDirection()!=Up) 
				snake.SetDirection(Down);
			break;
		case  KeyEvent.VK_A:
			if(snake.GetDirection()!=Right) snake.SetDirection(Left);
			break;
		case  KeyEvent.VK_D:
			if(snake.GetDirection()!=Left) snake.SetDirection(Right);
			break;
		case KeyEvent.VK_SPACE:
			Pause=!Pause;
			break;
			default:
				break;
		}
		}
	}
	public void keyTyped(KeyEvent e){}
	public void keyReleased(KeyEvent e) {}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==Start) {
			Start.setEnabled(false);
			Level.setEnabled(false);
			score=0;
			GameState=true;
			Failed=false;
			snake=new Snake(GreedySnakePanel.this);
			new Thread(go).start();
			go.setRunning(true);
		}
		if(e.getSource()==Quit) {
			GameState=false;
			System.exit(0);
		}
		if(e.getSource() == Level) {
			this.setAllButtonsEnable(false);
			if(levelWindow==null) {
			levelWindow= new LevelWindow(GreedySnakePanel.this);
			}
			else levelWindow.setVisible(true);
		}
		this.requestFocus();
	}
	public int getScore() {
		return score;
	}
    public void setScore(int score) {
    	this.score=score;
    }
	public void setGameState(Boolean NewGameState) {
		GameState=NewGameState;
	}
	public void setFailed(Boolean Result) {
		Failed=Result;
	}
    public int getLevel() {
    	return  level;
    }
	public void setLevel(int NewLevel) {
		level=NewLevel;
	}
	public void setAllButtonsEnable(Boolean newState) {
		Start.setEnabled(newState);
		Quit.setEnabled(newState);
	    Level.setEnabled(newState);
	}
    public void setFocusOfStart() {
	   Start.requestFocus();
   }
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(new BasicStroke(3.0f));
		Font Reminder=new Font("ËÎÌו",Font.BOLD,20);
		g.setFont(Reminder);
		g.setColor(Color.white);
		g.fillRect(LeftBound, UpBound, GameSizex, GameSizey);
		g.setColor(Color.black);
		g2.drawLine(LeftBound,UpBound,LeftBound,DownBound);
		g2.drawLine(LeftBound,DownBound,RightBound,DownBound);
		g2.drawLine(RightBound,DownBound,RightBound,UpBound);
		g2.drawLine(RightBound,UpBound,LeftBound,UpBound);
		if(Pause==true) {
	        img=new ImageIcon("Image/space.png");
	        g.drawImage(img.getImage(), 270,350,img.getIconWidth(),img.getIconHeight(),img.getImageObserver());
			snake.draw(g);
			food.draw(g);
		}
		if(Failed==true) {
			img=new ImageIcon("Image/gameover.png");
	        g.drawImage(img.getImage(), 300,270,img.getIconWidth(),img.getIconHeight(),img.getImageObserver());
	        Start.setEnabled(true);
	        Level.setEnabled(true);
	        Start.requestFocus();
	        go.setRunning(false);
		}
		if(NeedFood) {
			NeedFood=false;
			while(true) {
			food.GenerateFood();
			int TempFoodx=food.GetPosx(),TempFoody=food.GetPosy();
			for(int i=0;i<snake.SnakeBody.size();i++){
					if (Math.abs((snake.SnakeBody.get(i).GetX()+10)-(TempFoodx+10))<20&&Math.abs((snake.SnakeBody.get(i).GetY()+10)-(TempFoody+10))<20){
					FoodInBody=true;
					break;
				}
				else FoodInBody=false;
			}
			if(FoodInBody==true) continue;
			else break;
		}
		}
		if(snake.Eatfood(food)) {
			repaint();
			NeedFood=true;
		}
		if(Pause==false&&GameState==true)
		{
		snake.MoveForward();
		snake.draw(g);
		food.draw(g);
		}
		if(score==95||(score==245&&level<90)||(score==345&&level<90)) {
			img=new ImageIcon("Image/warn.png");
	        g.drawImage(img.getImage(),250,655,img.getIconWidth(),img.getIconHeight(),img.getImageObserver());
		}
		accelerate();
		g.setColor(Color.blue);
		g.drawString(""+score,85,530);
	}
	public void accelerate() {
		if(score==100) {
			if(level==30) level=40;
			else if(level==60) level=75;
			else if(level==90) level=100;
		}
		else if(score==250) {
			if(level==40) level=50;
			else if(level==75) level=90;
		}
		else if(score==350) {
			if(level==50) level=60;
		}
	}
	public class PaintThread implements Runnable{
		Boolean running=false;
	  public void run() {
	    	while(running) {
	    		repaint();
	    		try{
	    		Thread.sleep(4500/level);
	    	}
	    	catch(InterruptedException e) {   		
	    	}
	    }
	}
  	public void setRunning(Boolean NewState) {
		running=NewState;
	}
	}
}