package com.infraredpanda.projectilemotiongame;

/**
 * @author InfraredPanda
 */
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Game extends Canvas implements Runnable
{

	private static final long serialVersionUID = -9177429522906199994L;

	public static final int WIDTH = 640, HEIGHT = 480;

	private Thread thread;
	private boolean running = false;

	private Handler handler;
	public JFrame frame;

	public Game()
	{
		new Window(WIDTH, HEIGHT, "Projectile Motion Game", this);
	}

	// Game Loop
	public void run()
	{
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();

		while (running)
		{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1)
			{
				tick();
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000)
			{
				timer += 1000;
				// System.out.println("Fps " + frames);
				frames = 0;
			}

		}
		stop();
	}

	private void init()
	{
		handler = new Handler();

		double velocity = Double.parseDouble(JOptionPane.showInputDialog(this, "Please enter an initial velocity"));
		System.out.println(velocity);
		double angle = Double.parseDouble(JOptionPane.showInputDialog(this, "Please enter an angle of launch."));
		System.out.println(angle);
		String[] planets = new String[] { "Earth", "Jupiter", "Other" };
		String gravity = (String) JOptionPane.showInputDialog(frame, "Pick a planet's gravity to emulate.", "Pick already", JOptionPane.PLAIN_MESSAGE, null, planets, planets[0]);

		switch (gravity)
		{
			case "Earth":
				gravity = "9.8";
				break;
			case "Jupiter":
				gravity = "24.79";
				break;
			case "Other":
				gravity = JOptionPane.showInputDialog(this, "Please enter an absolute value for gravity.");
				break;
			default:
				gravity = "9.8";
		}
		Projectile projectile = new Projectile(velocity, angle, gravity);
		handler.addObject(projectile);
	}

	private void tick()
	{
		handler.tick();
	}

	private void render()
	{
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null)
		{
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.black);

		g.fillRect(0, 0, WIDTH, HEIGHT);

		handler.render(g);

		 g.dispose();
		 bs.show();
	}

	public synchronized void start()
	{
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop()
	{
		try
		{
			thread.join();
			running = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void main(String args[])
	{
		new Game();
	}

}
