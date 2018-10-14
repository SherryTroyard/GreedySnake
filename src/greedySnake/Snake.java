package greedySnake;
import java.awt.*;
import java.util.ArrayList;
public class Snake {
    ArrayList<Node> SnakeBody=new ArrayList<Node>();
    private Node head;
    private int direction;
    private int size=20;
    private GreedySnakePanel TempPanel;
    public Snake(GreedySnakePanel Panel) {
    	TempPanel=Panel;
    	Node Head=new Node(380,340);
    	SnakeBody.add(Head);
    	Node Body1=new Node(400,340);
    	SnakeBody.add(Body1);
    	Node Body2=new Node(420,340);
    	SnakeBody.add(Body2);
    	direction=GreedySnakePanel.Left;
    }
    public int GetDirection() {
    	return direction;
    }
    public void SetDirection(int NewDirection) {
    	direction=NewDirection;
    }
    public void GrowInHead() {
    	switch(direction) {
    	case GreedySnakePanel.Up:
    		head=new Node(SnakeBody.get(0).GetX(),SnakeBody.get(0).GetY()-size);
    		break;
    	case GreedySnakePanel.Down:
    		head=new Node(SnakeBody.get(0).GetX(),SnakeBody.get(0).GetY()+size);
    		break;
    	case GreedySnakePanel.Left:
    		head=new Node(SnakeBody.get(0).GetX()-size,SnakeBody.get(0).GetY());
    		break;
    	case GreedySnakePanel.Right:
    		head=new Node(SnakeBody.get(0).GetX()+size,SnakeBody.get(0).GetY());
    		break;
    	}
    	SnakeBody.add(0,head);
    }
    public void RemoveTheTail() {
    	int TailIndex=SnakeBody.size()-1;
    	SnakeBody.remove(TailIndex);
    }
    public Boolean BiteItself() {
    	for(int i=1;i<SnakeBody.size();i++) {
    		if((SnakeBody.get(0).GetX()==SnakeBody.get(i).GetX())&&(SnakeBody.get(0).GetY()==SnakeBody.get(i).GetY()))
    			return true;
    	}
    	return false;
    }
    public Boolean HelloWall() {
    	if(SnakeBody.get(0).GetX()<180||SnakeBody.get(0).GetX()>=840)
    		return true;
    	if(SnakeBody.get(0).GetY()<100||SnakeBody.get(0).GetY()>=660)
    		return true;
    	return false;
    }
    public void MoveForward() {
    	GrowInHead();
    	RemoveTheTail();
        if(BiteItself()||HelloWall()){
        	TempPanel.setFailed(true);
        	TempPanel.setGameState(false);
        }
    }
    public Boolean Eatfood(Food food) {
    	int headx=SnakeBody.get(0).GetX(),heady=SnakeBody.get(0).GetY();
    	int tempx=food.GetPosx(),tempy=food.GetPosy();
    	if (Math.abs((headx+size/2)-(tempx+size/2))<20&&Math.abs((heady+size/2)-(tempy+size/2))<20){
            GrowInHead();
    		TempPanel.setScore(TempPanel.getScore()+5);
    		return true;
    		}
    	return false;
    }
    public void draw(Graphics g) {
    	for(int i=0;i<SnakeBody.size();i++) {
    		if(i==0) { 
    			g.setColor(Color.green);
    			g.fillOval(SnakeBody.get(i).GetX(), SnakeBody.get(i).GetY(), 20, 20);
    	}
    		else {
    			g.setColor(Color.orange);
    			g.fillOval(SnakeBody.get(i).GetX(), SnakeBody.get(i).GetY(), 20, 20);
    		}
    	}
    }
}
