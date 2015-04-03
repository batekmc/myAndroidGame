package com.batek.mirrors.Shapes;

import android.content.res.Resources;
import android.graphics.Bitmap;

import com.batek.mirrors.BitmapLoaders.BitmapLoader;

public class TMirror extends Shape{
	
	private Bitmap bmpEmpty[];
	private Bitmap bmpFull[];

	public TMirror(int xPos, int yPos, int orientation, Shape[][] gameArea,
			Resources res, BitmapLoader bmpLoad) {
		super(xPos, yPos, orientation, gameArea, res, bmpLoad);
		this.toDraw = bmpEmpty[orientation];
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
				if( xPos - 1 >= 0)
					this.gameArea[xPos - 1 ][yPos].reciveLight(xPos, yPos);
				if( xPos + 1 < xLen)
					this.gameArea[xPos + 1][yPos].reciveLight(xPos, yPos);
			}
			
		}
		
		if( orientation == 1){
			if( xPos == xFrom-1 && yPos == yFrom){
				this.toDraw = bmpFull[orientation];
				if( yPos -1 >= 0 )
					this.gameArea[xPos][yPos - 1].reciveLight(xPos, yPos);
				if( yPos + 1 < yLen)
					this.gameArea[xPos][yPos + 1].reciveLight(xPos, yPos);
			}
			
		}
		
		if( orientation == 2){
			if( xPos == xFrom && yPos == yFrom-1){
				this.toDraw = bmpFull[orientation];
				if( xPos - 1 > 0)
					this.gameArea[xPos - 1][yPos].reciveLight(xPos, yPos);
				if( xPos + 1 < xLen)
					this.gameArea[xPos + 1][yPos].reciveLight(xPos, yPos);
				
			}
		}
		
		if( orientation == 3){
			if( xPos  == xFrom + 1 && yPos == yFrom ){
				this.toDraw = bmpFull[orientation];
				if(yPos - 1 > 0)
					this.gameArea[xPos][yPos - 1].reciveLight(xPos, yPos);
				if( yPos + 1 < yLen)
					this.gameArea[xPos][yPos + 1].reciveLight(xPos, yPos);
			}
		}
	}

	@Override
	public boolean isMoveable() {
		return true;
	}

	@Override
	public void clear() {
		this.toDraw = bmpEmpty[orientation];
		
	}
	
	@Override
	public void setXY(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		
	}

}
