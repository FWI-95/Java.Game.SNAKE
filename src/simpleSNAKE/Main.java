package simpleSNAKE;

import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
	static final int speed = 3;
	static final int gridx = 10;
	static final int gridy = 10;
	static final int sizex = 556;
	static final int sizey = 579;
	static MyFrame frame;
	static JPanel gpanel;
	static int[][] snake;
	static int applex;
	static int appley;
	static long LastFrame;
	static boolean lost = true;
	
	static int score = 0;
	
	public static void main(String[] args) {
		frame = new MyFrame("SNAKE", gridx, gridy, sizex, sizey);
		
		frame.setVisible(true);
		
		LastFrame = System.currentTimeMillis();
		while(frame.isRunning()) {
			tick();
			frame.repaint();
			waitfor();
		}
		System.exit(0);
	}
	
	public static void tick() {
		if(lost) {
			init();
			lost = false;
		}
		
		int[][] tmpsnake = snake;
		int nextx = tmpsnake[0][0];
		int nexty = tmpsnake[0][1];
		
		switch(frame.getDirection()) {
		case 1:
			//up
			nexty--;
			break;
		case 2:
			//right
			nextx++;
			break;
		case 3:
			//down
			nexty++;
			break;
		case 4:
			//left
			nextx--;
			break;
		default:
			break;
		}
		
		if(nextx > gridx) {
			nextx = 0;
		}
		if(nextx < 0) {
			nextx = gridx;
		}
		if(nexty > gridy) {
			nexty = 0;
		}
		if(nexty < 0) {
			nexty = gridy;
		}
		
		if(nextx == applex && nexty == appley) {
			snake = new int[tmpsnake.length +1][2];
			snake[0][0] = nextx;
			snake[0][1] = nexty;
			for(int x = 0; x < tmpsnake.length; x++) {
				snake[x + 1][0] = tmpsnake[x][0];
				snake[x + 1][1] = tmpsnake[x][1];
			}
			spawnApple();
			score++;
		}else {
			snake = new int[tmpsnake.length][2];
			for(int x = tmpsnake.length; x > 1; x--) {
				snake[x - 1][0] = tmpsnake[x - 2][0];
				snake[x - 1][1] = tmpsnake[x - 2][1];
				

			}
			snake[0][0] = nextx;
			snake[0][1] = nexty;
			
			for(int x = 1; x < snake.length; x++) {
				if(nextx == snake[x][0] && nexty == snake[x][1]) {
					lost = true;
				}
			}
		}
		
		frame.update(snake, applex, appley, score);
	}
	
	public static void waitfor() {
		long ThisFrame = System.currentTimeMillis();
		while((ThisFrame - LastFrame) < speed * 100) {
			ThisFrame = System.currentTimeMillis();
		}
		LastFrame = System.currentTimeMillis();
	}
	
	public static void spawnApple() {
		boolean emptySpace = false;
		while(!emptySpace) {
			emptySpace = true;
			applex = (int) (Math.random() * gridx);
			appley = (int) (Math.random() * gridy);
			
			for(int x = 0; x < snake.length; x++) {
				if((snake[x][0] == applex)&&(snake[x][1] == appley)){
					emptySpace = false;
				}
			}
		}
	}
	
	public static void init() {
		score = 0;
		frame.setDirection(3);
		snake = new int[5][2];
		for(int x = 0; x < snake.length; x++) {
			snake[x][0] = (int) (gridx / 2);
			snake[x][1] = (int) (gridy / 2);
		}
		spawnApple();
	}

}
