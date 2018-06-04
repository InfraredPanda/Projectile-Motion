package com.infraredpanda.projectilemotiongame;

public class Time
{
	long startTime;

	public Time()
	{
		startTime = System.currentTimeMillis();
	}

	public long getDisplayTime()
	{
		long elapsedTime = System.currentTimeMillis() - startTime;
		long elapsedSeconds = elapsedTime / 1000;
		long secondsDisplay = elapsedSeconds % 60;
		return secondsDisplay;
	}
	public double getExactTime()
	{
		long elapsedTime = System.currentTimeMillis() - startTime;
		long elapsedSeconds = (elapsedTime / 100);
		return (elapsedSeconds / 10.0);
	}
}
