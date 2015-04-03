package com.batek.mirrors.BitmapLoaders;

import com.example.mirrors.R;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;

public class StartBitmapLoader extends BitmapLoader {


	public StartBitmapLoader(Resources res) {
		super(res);
	}

	@Override
	protected void loadBmpEmpty() {
		this.bmpEmpty = null;
	}



	@Override
	protected void loadBmpFull() {
		this.bmpFull = new Bitmap[4];

		this.bmpFull[0] = BitmapFactory.decodeResource(this.res,
				R.drawable.start0);
		Matrix matrix = new Matrix();
		this.heigth = bmpFull[0].getHeight();
		this.width = bmpFull[0].getWidth();
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
