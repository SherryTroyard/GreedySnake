package greedySnake;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class LevelWindow extends JFrame implements ActionListener {
    private JButton easy, normal, hard;
    private ImageIcon img;
    private GreedySnakePanel tempPanel;
    public LevelWindow(GreedySnakePanel Panel){
        tempPanel = Panel;
    	setLayout(null);
    	this.setTitle("Choose Level(Ä¬ÈÏNormalÄÑ¶È)");
    	this.setAlwaysOnTop(true);
        this.setBounds(600, 130, 640, 360);
        this.setResizable(false);
        img = new ImageIcon("Image/easy.png");
        easy = new JButton();   //"EASY"
        easy.setBounds(60, 165, 130, 130);
        easy.setIcon(img);
        easy.setFocusPainted(false);
        this.add(easy);
        img = new ImageIcon("Image/normal.png");
        normal = new JButton();   //"NORMAL"
        normal.setBounds(255, 165, 130, 130);
        normal.setIcon(img);
        this.add(normal);
        img = new ImageIcon("Image/hard.png");
        hard = new JButton();   //"HARD"
        hard.setBounds(450, 165, 130, 130);
        hard.setIcon(img);
        this.add(hard);
        img = new ImageIcon("Image/lbg.png");
        GreedySnakeBackground Bg = new GreedySnakeBackground(img.getImage());
        Bg.setBounds(0, 0, img.getIconWidth(), img.getIconHeight());
        add(Bg);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                tempPanel.setAllButtonsEnable(true);
            }
        });
        easy.addActionListener(this);
        normal.addActionListener(this);
        hard.addActionListener(this);
        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == easy){
            tempPanel.setLevel(30);
            tempPanel.setAllButtonsEnable(true);
            tempPanel.setFocusOfStart();
            this.setVisible(false);
        }
        if (e.getSource() == normal){
            tempPanel.setLevel(50);
            tempPanel.setAllButtonsEnable(true);
            tempPanel.setFocusOfStart();
            this.setVisible(false);
        }
        if (e.getSource() == hard){
            tempPanel.setLevel(90);
            tempPanel.setAllButtonsEnable(true);
            tempPanel.setFocusOfStart();
            this.setVisible(false);
        }
	}
}