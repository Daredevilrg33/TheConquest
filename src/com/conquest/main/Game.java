/**
 * 
 */
package com.conquest.main;

import java.awt.Canvas;

import com.conquest.window.GameWindow;
import com.conquest.window.InitialScreen;

/**
 * @author Rohit Gupta
 *
 */
public class Game extends Canvas implements Runnable {
		/// Testcode.
	private static final long serialVersionUID = -4669025547633011027L;
	private Thread thread;
	private boolean isRunning = false;
	public Game() {
		new GameWindow(WIDTH,HEIGHT,"THE CONQUEST" , this);

	}
	
	public static void main(String[] args) {

//	new Game();
		InitialScreen initialScreen = new InitialScreen();
		initialScreen.setVisible(true);

	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		isRunning = true;
	}
	
	public synchronized void stop() {
		
		try {
			thread.join();
			isRunning = false;
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	private void tick() {
		
	}
	
	private void render() {
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub

		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(isRunning) {
			long now = System.nanoTime();
			delta += (now - lastTime)/ ns; 
			lastTime = now;
			while(delta >= 1)
			{
				tick();
				delta--;
			}
			if(isRunning) {
				render();
			}
			frames++;
			if(System.currentTimeMillis() - timer > 1000) {
				
				timer +=1000;
				System.out.println(" FPS: " + frames);
				frames = 0;
			}
		}
		stop();
		
	}

}
