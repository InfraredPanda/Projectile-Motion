package com.infraredpanda.projectilemotiongame;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler
{
	LinkedList<Projectile> object = new LinkedList<Projectile>();

	public void tick()
	{
		for (int i = 0; i < object.size(); i++)
		{
			Projectile tempObject = object.get(i);

			tempObject.tick();
		}
	}

	public void render(Graphics g)
	{
		for (int i = 0; i < object.size(); i++)
		{
			Projectile tempObject = object.get(i);

			tempObject.render(g);
		}
	}

	public void addObject(Projectile object)
	{
		this.object.add(object);
	}

	public void removeObject(Projectile object)
	{
		this.object.remove(object);
	}
}
