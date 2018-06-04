package com.infraredpanda.projectilemotiongame;

import java.awt.Color;
import java.awt.Graphics;

public class Projectile
{
	double vel, xPos, yPos, gravity, velX, velY, distance, angle, totalTime;

	Time timer;

	public Projectile(double vel, double angle, String gravity)
	{
		timer = new Time();
		this.vel = vel;
		this.xPos = 0;
		this.yPos = 0;
		this.angle = angle;
		this.gravity = Double.parseDouble(gravity);
		this.velX = (vel * Math.cos(Math.toRadians(angle)));

		this.totalTime = (2 * this.vel * Math.sin(Math.toRadians(angle))) / this.gravity;
		System.out.println(totalTime);
		this.distance = this.velX * this.totalTime;
	}

	public void tick()
	{
		System.out.println(timer.getExactTime());
		if (xPos <= this.distance)
		{
			this.velY = (vel * (Math.sin(Math.toRadians(this.angle)))) - (gravity * timer.getExactTime());
			xPos += velX;

			yPos += velY;
		}
	}

	public void render(Graphics g)
	{
		g.setColor(Color.red);
		g.fillRect((int) xPos, ((int) yPos + Game.HEIGHT), 10, 10);
		g.drawString("t = " + timer.getDisplayTime(), Game.WIDTH - 100, Game.HEIGHT - 50);
		g.drawString("d = " + xPos, Game.WIDTH - 100, Game.HEIGHT - 100);
		g.drawString("h = " + yPos, Game.WIDTH - 100, Game.HEIGHT - 150);
	}


	public double getxPos()
	{
		return xPos;
	}

	public void setxPos(double xPos)
	{
		this.xPos = xPos;
	}

	public double getyPos()
	{
		return yPos;
	}

	public void setyPos(double yPos)
	{
		this.yPos = yPos;
	}

	public double getGravity()
	{
		return gravity;
	}

	public void setGravity(double gravity)
	{
		this.gravity = gravity;
	}
}
