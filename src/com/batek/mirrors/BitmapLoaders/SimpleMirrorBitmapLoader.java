package com.batek.mirrors.BitmapLoaders;

import com.example.mirrors.R;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

public class SimpleMirrorBitmapLoader extends BitmapLoader {



	public SimpleMirrorBitmapLoader(Resources res) {
		super(res);
	}


	@Override
	protected void loadBmpEmpty() {
		this.bmpEmpty = new Bitmap[4];
		this.bmpEmpty[0] = BitmapFactory.decodeResource(this.res,
				R.drawable.mirror_empty0);
		//Rotate bitmaps
		Matrix matrix = new Matrix();
		this.heigth = bmpEmpty[0].getHeight();
		this.width = bmpEmpty[0].getWidth();
		matrix.postRotate(90);
		//vreateBitmap is rotating bitmap by given angle from matrix
		this.bmpEmpty[1] = Bitmap.createBitmap(bmpEmpty[0], 0, 0,
				this.width, this.heigth, matrix, true);
		matrix.postRotate(90);
		this.bmpEmpty[2] = Bitmap.createBitmap(bmpEmpty[0], 0, 0,
				this.width, this.heigth, matrix, true);
		matrix.postRotate(90);
		this.bmpEmpty[3] = Bitmap.createBitmap(bmpEmpty[0], 0, 0,
				this.width, this.heigth, matrix, true);

	}

	@Override
	protected void loadBmpFull() {
		this.bmpFull = new Bitmap[4];

		this.bmpFull[0] = BitmapFactory.decodeResource(this.res,
				R.drawable.mirror_full0);
		Matrix matrix = new Matrix();
		matrix.postRotate(90);
		this.bmpFull[1] = Bitmap.createBitmap(bmpFull[0], 0, 0,
				this.width, this.heigth, matrix, true);
		matrix.postRotate(90);
		this.bmpFull[2] = Bitmap.createBitmap(bmpFull[0], 0, 0,
				this.width, this.heigth, matrix, true);
		matrix.postRotate(90);
		this.bmpFull[3] = Bitmap.createBitmap(bmpFull[0], 0, 0,
				this.width, this.heigth, matrix, true);
		matrix.postRotate(90);

	}

}
