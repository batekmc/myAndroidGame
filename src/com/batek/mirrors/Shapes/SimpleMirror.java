package com.batek.mirrors.Shapes;

import android.content.res.Resources;
import android.graphics.Bitmap;

import com.batek.mirrors.BitmapLoaders.BitmapLoader;

/*
 * |\
 * | \
 * |__\
 * 
 */
public class SimpleMirror extends Shape{
	
	public SimpleMirror(int xPos, int yPos, int orientation,
			Shape[][] gameArea, Resources res, BitmapLoader bmpLoad) {
		super(xPos, yPos, orientation, gameArea, res, bmpLoad);
	
		this.toDraw = bmpEmpty[orientation];
	}

	private Bitmap[] bmpEmpty;
	private Bitmap[] bmpFull;	


	@Override
	protected void loadBitmaps() {
		
		this.bmpEmpty = bmpLoader.getBmpEmpty();
		this.bmpFull = bmpLoader.getBmpFull();
		
	}

	@Override
	public void reciveLight(int xFrom, int yFrom) {
		
		if( this.orientation == 0){
			//Receive from the top
			if( this.xPos == xFrom && this.yPos == (yFrom + 1)){
				toDraw = this.bmpFull[this.orientation];
				if( xPos + 1 < xLen )
					gameArea[xPos + 1][yPos].reciveLight(xPos, yPos);
			}
			//Receive from the right
			if( this.xPos == (xFrom - 1) && this.yPos == yFrom ){		
				toDraw = this.bmpFull[this.orientation];
				if( yPos -1 >= 0 )
					gameArea[xPos][yPos - 1].reciveLight(xPos, yPos);
			}
		}
		
		else if( this.orientation == 1){
			//From right
			if( this.xPos == (xFrom - 1) && this.yPos == yFrom ){
				toDraw = this.bmpFull[this.orientation];
				if( yPos + 1 < yLen )
					gameArea[xPos][yPos + 1].reciveLight(xPos, yPos);
			}
			//From down
			if( this.xPos == xFrom && this.yPos == (yFrom - 1)){
				toDraw = this.bmpFull[this.orientation];
				if( xPos + 1 < xLen)
					gameArea[xPos + 1][yPos].reciveLight(xPos, yPos);
			}
		}
		
		else if( this.orientation == 2){
			//From down
			if( this.xPos == xFrom && this.yPos == (yFrom - 1)){
				toDraw = this.bmpFull[this.orientation];
				if( xPos - 1 >= 0 )
					gameArea[xPos - 1][yPos].reciveLight(xPos, yPos);
			}
			//From left
			if( this.xPos == (xFrom + 1) && this.yPos == yFrom ){
				toDraw = this.bmpFull[this.orientation];
				if( yPos + 1 < yLen )
					gameArea[xPos][yPos + 1].reciveLight(xPos, yPos);
			}			
		}
		
		else if( this.orientation == 3){
			//Receive from the top
			if( this.xPos == xFrom && this.yPos == (yFrom + 1)){
				toDraw = this.bmpFull[this.orientation];
				if( xPos - 1 >= 0 )
					gameArea[xPos - 1][yPos].reciveLight(xPos, yPos);
			}
			//From left
			if( this.xPos == (xFrom + 1) && this.yPos == yFrom ){
				toDraw = this.bmpFull[this.orientation];
				if( yPos - 1 >= 0 )
					gameArea[xPos][yPos - 1].reciveLight(xPos, yPos);
			}	
			
		}
		
		
	}

	@Override
	public boolean isMoveable() {
		return true;
	}

	@Override
	public void clear() {
		this.toDraw = this.bmpEmpty[orientation];
		
	}

	@Override
	public void setXY(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		
	}

	
}
