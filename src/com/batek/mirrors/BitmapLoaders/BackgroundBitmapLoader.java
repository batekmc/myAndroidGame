package com.batek.mirrors.BitmapLoaders;

import com.example.mirrors.R;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BackgroundBitmapLoader extends BitmapLoader {



	public BackgroundBitmapLoader(Resources res) {
		super(res);
	}

	public Bitmap[] getBmpEmpty() {
		return bmpEmpty;
	}

	public Bitmap[] getBmpFull() {
		return bmpFull;
	}

	@Override
	protected void loadBmpEmpty() {
		this.bmpEmpty = new Bitmap[1];
		this.bmpEmpty[0] = BitmapFactory.decodeResource(res,
				R.drawable.background_empty);

	}

	@Override
	protected void loadBmpFull() {
		this.bmpFull = new Bitmap[3];
		this.bmpFull[1] = BitmapFactory.decodeResource(res,
				R.drawable.background_full_h);
		this.bmpFull[0] = BitmapFactory.decodeResource(res,
				R.drawable.background_full_v);
		this.bmpFull[2] = BitmapFactory.decodeResource(res,
				R.drawable.background_full_hv);
	}





}
