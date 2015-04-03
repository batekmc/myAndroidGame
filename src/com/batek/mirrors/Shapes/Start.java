package com.batek.mirrors.Shapes;

import android.content.res.Resources;
import android.graphics.Bitmap;

import com.batek.mirrors.BitmapLoaders.BitmapLoader;

public class Start extends Shape {
	
	private Bitmap bmp[];

	public Start(int xPos, int yPos, int orientation, Shape[][] gameArea,
			Resources res, BitmapLoader bmpLoad) {
		super(xPos, yPos, orientation, gameArea, res, bmpLoad);
		
		this.toDraw = bmp[orientation];
	}

	@Override
	protected void loadBitmaps() {
		this.bmp = bmpLoader.getBmpFull();
	}

	@Override
	public void reciveLight(int xFrom, int yFrom) {
		
				
	}
	
	public void startLight(){		
		if( orientation == 0){
			this.toDraw = bmp[0];
			if( yPos - 1 >= 0 )
				this.gameArea[xPos][yPos - 1].reciveLight(xPos, yPos);
		}
		
		if( orientation == 1){
			this.toDraw = bmp[1];
			if( xPos + 1 < xLen )
				this.gameArea[xPos + 1][yPos].reciveLight(xPos, yPos);
		}
		
		if( orientation == 2){
			this.toDraw = bmp[2];
			if( yPos + 1 < yLen )
				this.gameArea[xPos][yPos + 1].reciveLight(xPos, yPos);
		}
		
		if( orientation == 3){
			this.toDraw = bmp[3];
			if( xPos - 1 >= 0 )
				this.gameArea[xPos - 1][yPos].reciveLight(xPos, yPos);
		}
		
	}

	@Override
	public boolean isMoveable() {
		return false;
	}

	@Override
	public void clear() {
		
	}

	@Override
	public void setXY(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;
		
	}

}
