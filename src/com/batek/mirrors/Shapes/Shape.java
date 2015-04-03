package com.batek.mirrors.Shapes;

import com.batek.mirrors.BitmapLoaders.BitmapLoader;

import android.content.res.Resources;
import android.graphics.Bitmap;

/*
 * @param xPos - x position 
 * @param yPos - y position
 * @param orientation - orientation of the object( 1 - 4 )
 * @param gameArea - area, where objects are drawn
 * @parambmpLoader - class, where are bitmaps stored
 */
public abstract class Shape {
	
	protected int xPos, yPos, xLen, yLen;
	protected int orientation;
	protected Shape gameArea[][];	
	protected Resources res;
	//this field will be drawn on the display
	protected Bitmap toDraw;
	protected BitmapLoader bmpLoader;
	
	
	public Shape( int xPos, int yPos, int orientation,  Shape[][] gameArea, Resources res , BitmapLoader bmpLoad){
		this.xPos = xPos;
		this.yPos = yPos;
		this.gameArea = gameArea;
		this.orientation = orientation;
		this.res = res;
		this.xLen = gameArea.length;
		this.yLen = gameArea[0].length;
		this.bmpLoader = bmpLoad;
		loadBitmaps();
	}
	
	/*
	 * Load 8 bitmaps,
	 * called in constructor
	 */
	protected abstract void loadBitmaps();
	
	/*
	 *@param x x of sender
	 *@param y y of sender
	 *
	 *if is light on, call neighbor`s method(s)
	 */
	public abstract void reciveLight(int xFrom, int yFrom);
	
	/*
	 * @return -true if user can move the shape on the screen
	 * 		   -false if can`t
	 */
	public abstract boolean isMoveable();
	
	/*
	 * set shape to empty state
	 */
	public abstract void clear();
	
	public abstract void setXY( int xPos, int yPos);

	
	public Bitmap getBitmap(){		
		return this.toDraw;		
	}
	
	public void newPosition( int x, int y){
		this.xPos = x;
		this.yPos = y;	
	}

}
