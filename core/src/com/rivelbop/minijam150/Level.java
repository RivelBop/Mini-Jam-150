package com.rivelbop.minijam150;

import java.util.ArrayList;

public abstract class Level {
	public ArrayList<Spike> spikes = new ArrayList<>();
	public ArrayList<Wolf> wolves = new ArrayList<>();
	public ArrayList<Ghost> ghosts = new ArrayList<>();
	public int portalX, portalY;
	
	public abstract void obstacles();
}