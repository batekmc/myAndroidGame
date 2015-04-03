package com.batek.mirrors.GameAccessories;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

import com.batek.mirrors.Shapes.Background;
import com.batek.mirrors.Shapes.Shape;

public class TouchControl implements OnTouchListener {

	private float dpX40;
	private float xDown, yDown, xMove, yMove;
	private int xD, yD, xM, yM;
	private Shape gameArea[][];
	private float xL;// x coordinate of first touch
	private float yL;// y coordinate of first touch
	private GameView gV;
	private int xDim, yDim;
	private int approximate;

	/*
	 * @param dpX40 - size of the side of square representing game objects in
	 * pixels
	 *
	 * @param g - view, representing game area
	 */
	public TouchControl(float dpX40, GameView g) {
		this.dpX40 = dpX40;
		this.gV = g;
		this.approximate = (int) (dpX40 / 2);
		if (gameArea != null) {
			this.xDim = gameArea.length;
			this.yDim = gameArea[0].length;
		}
	}

	public void setGameArea(Shape[][] gArea) {
		this.gameArea = gArea;
		if (gameArea != null) {
			this.xDim = gameArea.length;
			this.yDim = gameArea[0].length;
		}
	}

	// is object at given coordinates able to move on game area?
	// Method sets new xD and yD
	private boolean areCoordinatesMooveable() {
		xDown = xL / dpX40;
		yDown = yL / dpX40;

		xD = (int) xDown;
		yD = (int) yDown;

		if (xD < xDim && yD < yDim)
			return gameArea[xD][yD].isMoveable();
		else return false;
	}

	// if no, then try to find close object in range(square) given by
	// approximate
	private boolean approximateTouch() {
		int a, b;
		a = (int) xL;
		b = (int) yL;
		
		for (int i = (int) (a - approximate); i < a + approximate; i++){
			for (int j = (int) (b - approximate); j < b + approximate; j++) {
				if (xL < 0)
					xL = 0;
				if (yL < 0)
					yL = 0;
				xL = i;
				yL = j;
				if (areCoordinatesMooveable()) {
					return true;
				}
			}
		}
		return false;

	}

	/*
	 * Method is switching neighbor objects if its necessary.
	 * After switching refreshing screen.
	 *
	 */
	private void switchObjects() {
		// if there is new position on game area
		if ((xM != xD || yM != yD) && xM < xDim && yM < yDim) {
			if (this.gameArea[xM][yM] instanceof Background) {
				// Switch ojects at game area,
				// before the screen is refreshed by
				// refreshLisht() call, and then refresh:)
				Shape a = gameArea[xD][yD];
				a.setXY(xM, yM);
				Shape b = gameArea[xM][yM];
				b.setXY(xD, yD);

				gameArea[xD][yD] = b;
				gameArea[xM][yM] = a;

				xD = xM;
				yD = yM;

				gV.refreshLight();

			}
		}
	}

	public boolean onTouch(View v, MotionEvent event) {

		switch (event.getAction()) {

		case MotionEvent.ACTION_DOWN:
			//Log.w("A_DOWNx-----, y :", event.getX() + ", " + event.getY());
			xL = event.getX();
			yL = event.getY();

			// if user hit object at first time
			if (areCoordinatesMooveable()){
				//Log.w("Are moveable?", "FAIIIIIIIIIIIIILLLLLLLL");
				return true;
			}
			// if not, try to help
			else
				return approximateTouch();


		case MotionEvent.ACTION_MOVE:
			if (areCoordinatesMooveable()) {
				// get coordinates of new position
				xL = event.getX();
				yL = event.getY();

				// calculate coordinates of game area
				xMove = xL / dpX40;
				yMove = yL / dpX40;

				xM = (int) xMove;
				yM = (int) yMove;

				switchObjects();

			}

			return true;

		case MotionEvent.ACTION_UP:

			return true;

		}
		return true;

	}
}
