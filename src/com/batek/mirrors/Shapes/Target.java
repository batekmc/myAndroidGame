package com.batek.mirrors.Shapes;

import android.content.res.Resources;
import android.graphics.Bitmap;

import com.batek.mirrors.BitmapLoaders.BitmapLoader;

public class Target extends Shape{
	
	private Bitmap bmpEmpty[];
	private Bitmap bmpFull[];
	private boolean isOKay = false;

	public Target(int xPos, int yPos, int orientation, Shape[][] gameArea,
			Resources res, BitmapLoader bmpLoad) {
		super(xPos, yPos, orientation, gameArea, res, bmpLoad);
		toDraw = bmpEmpty[orientation];
	}

	@Override
	protected void loadBitmaps() {
		this.bmpEmpty = bmpLoader.getBmpEmpty();
		this.bmpFull = bmpLoader.getBmpFull();
	}

	@Override
	public void reciveLight(int xFrom, int yFrom) {
		
		if( orientation == 0){
			if( xPos == xFrom && yPos == yFrom +1 ){
				this.toDraw = bmpFull[orientation];
				this.isOKay = true;
			}
		}
		
		else if( orientation == 1){
			if( xPos == xFrom -1 && yPos == yFrom  ){
				this.toDraw = bmpFull[orientation];
				this.isOKay = true;
			}
			
		}
		
		else if( orientation == 2){
			if( xPos == xFrom  && yPos == yFrom -1 ){
				this.toDraw = bmpFull[orientation];
				this.isOKay = true;
			}
			
		}
		
		else if( orientation == 3){
			if( xPos == xFrom +1 && yPos == yFrom  ){
				this.toDraw = bmpFull[orientation];
				this.isOKay = true;
			}
			
		}
		
		
	}

	@Override
	public boolean isMoveable() {
		return false;
	}

	@Override
	public void clear() {
		this.isOKay = false;
		this.toDraw = bmpEmpty[orientation];
	}
	
	@Override
	public void setXY(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		
	}
	
	public boolean isFinished(){
		return this.isOKay;
	}

}
