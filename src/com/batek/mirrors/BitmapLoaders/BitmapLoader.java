package com.batek.mirrors.BitmapLoaders;

import android.content.res.Resources;
import android.graphics.Bitmap;



/*
 * This is parent for all class loaders of bitmaps.
 * Every shape has its own bitmap loader which is child
 * of this class;)
 *
 */
public abstract class BitmapLoader {

	protected Bitmap[] bmpEmpty;//bitmaps with no light
	protected Bitmap[] bmpFull;//bitmaps with light
	protected Resources res;//sources of images
	protected int width;//width of bitmap to draw
	protected int heigth;//height of bitmap to draw

	public BitmapLoader( Resources res ){
		this.res = res;

		loadBmpEmpty();
		loadBmpFull();
	}

	public Bitmap[] getBmpEmpty() {
		return bmpEmpty;
	}

	public Bitmap[] getBmpFull() {
		return bmpFull;
	}

	protected abstract void loadBmpEmpty();

	protected abstract void loadBmpFull();


}
