package com.batek.mirrors.Shapes;

import android.content.res.Resources;
import android.graphics.Bitmap;

import com.batek.mirrors.BitmapLoaders.BackgroundBitmapLoader;

public class Background extends Shape {

	private Bitmap[] bmpEmpty;
	private Bitmap[] bmpFull;
	private boolean isFull = false;

	public Background(int xPos, int yPos, int orientation, Shape[][] gameArea,
			Resources res, BackgroundBitmapLoader bmpLoad) {
		super(xPos, yPos, orientation, gameArea, res, bmpLoad);

		this.toDraw = bmpEmpty[0];
	}

	@Override
	protected void loadBitmaps() {

		this.bmpEmpty = bmpLoader.getBmpEmpty();
		this.bmpFull = bmpLoader.getBmpFull();

	}

	@Override
	public void reciveLight(int xFrom, int yFrom) {

		// From the top
		if (xFrom == xPos && yFrom + 1 == yPos) {
			if (isFull)
				this.toDraw = bmpFull[2];
			else {
				this.toDraw = bmpFull[1];
				this.isFull = true;
			}
			if (yPos + 1 < yLen)
				this.gameArea[xPos][yPos + 1].reciveLight(xPos, yPos);
		}

		// From the bottom
		else if (xFrom == xPos && yFrom - 1 == yPos) {
			if (isFull)
				this.toDraw = bmpFull[2];
			else {
				this.toDraw = bmpFull[1];
				this.isFull = true;
			}
			if (yPos - 1 >= 0)
				this.gameArea[xPos][yPos - 1].reciveLight(xPos, yPos);
		}

		// From right
		else if (xPos == xFrom - 1 && yPos == yFrom) {
			if (isFull)
				this.toDraw = bmpFull[2];
			else {
				this.toDraw = bmpFull[0];
				this.isFull = true;
			}
			if (xPos - 1 >= 0)
				this.gameArea[xPos - 1][yPos].reciveLight(xPos, yPos);
		}

		// From left
		else if (xPos == xFrom + 1 && yPos == yFrom) {
			if (isFull)
				this.toDraw = bmpFull[2];
			else {
				this.toDraw = bmpFull[0];
				this.isFull = true;
			}
			if (xPos + 1 < xLen)
				this.gameArea[xPos + 1][yPos].reciveLight(xPos, yPos);
		}

	}

	@Override
	public boolean isMoveable() {
		return false;
	}

	@Override
	public void clear() {
		this.isFull = false;
		this.toDraw = bmpEmpty[0];
	}

	@Override
	public void setXY(int xPos, int yPos) {
		this.xPos = xPos;
		this.yPos = yPos;

	}

}
