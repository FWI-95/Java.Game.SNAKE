package simpleSNAKE;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MyFrame extends JFrame implements KeyListener{
	
	int gridx;
	int gridy;
	
	int multiplyerx;
	int multiplyery;
	
	int applex;
	int appley;
	
	int[][]snake;
	
	boolean running = true;
	
	int direction = 3;
	int nextdirection = 3;
	
	JPanel pan;
	
	int score = 0;
	
	public MyFrame(String title, int gx, int gy, int sizex, int sizey) {
		this.setTitle(title);
		this.setSize(sizex, sizey);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		
		this.addKeyListener(this);
		
		gridx = gx;
		gridy = gy;
		
		multiplyerx = this.getHeight() / gridx;
		multiplyery = this.getWidth() / gridy;
	}
	
	public void update(int[][]s, int ax, int ay, int sc) {
		snake = s;
		applex = ax;
		appley = ay;
		
		direction = nextdirection;
		
		score = sc;
	}
		
	@Override
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		if(snake != null) {
			
			g.setColor(Color.RED);
			g.fillRect(applex * 50 + 4, appley * 50 + 26, 48, 48);
			
			g.setColor(Color.green);
			for(int x = 0; x < snake.length; x++) {
				g.fillRect(snake[x][0] * 50 + 4, snake[x][1] * 50 + 26, 48, 48);
			}	
		}
		
		g.setColor(Color.WHITE);
		g.setFont(new Font("Arial", Font.ITALIC, 14));
		g.drawString("Score: " + score, 5, 40);
	}
	
	public boolean isRunning() {
		return running;
	}
	
	public int getDirection() {
		return direction;
	}
	
	public void setDirection(int dir) {
		direction = dir;
		nextdirection = direction;
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		if(ke.getKeyCode()==KeyEvent.VK_ESCAPE) {
			running = false;
		}else {
			switch(ke.getKeyCode()) {
			case KeyEvent.VK_UP:
				if(direction != 3)nextdirection = 1;
				break;
			case KeyEvent.VK_RIGHT:
				if(direction != 4)nextdirection = 2;
				break;
			case KeyEvent.VK_DOWN:
				if(direction != 1)nextdirection = 3;
				break;
			case KeyEvent.VK_LEFT:
				if(direction != 2)nextdirection = 4;
				break;
			default:
				break;
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent ke) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent ke) {
		// TODO Auto-generated method stub
		
	}

}
