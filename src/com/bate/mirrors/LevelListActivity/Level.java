package com.bate.mirrors.LevelListActivity;

public class Level {
	
	private int time;
	private int level;
	
	
	
	public Level(){};
	
	/**
	 * 
	 * @param time - time in milliseconds
	 * @param level - level number, > 1
	 */
	public Level( int time, int level){
		this.time = time;
		this.level = level;
		
	}
	
	
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	

}
