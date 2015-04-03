package com.batek.mirrors.GameAccessories;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.ArrayList;

import com.batek.mirrors.BitmapLoaders.BackgroundBitmapLoader;
import com.batek.mirrors.BitmapLoaders.SimpleMirrorBitmapLoader;
import com.batek.mirrors.BitmapLoaders.StartBitmapLoader;
import com.batek.mirrors.BitmapLoaders.TMirrorBitmapLoader;
import com.batek.mirrors.BitmapLoaders.TargetBitmapLoader;
import com.batek.mirrors.Shapes.*;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

public class Init {

	public Init(Context context, DisplayMetrics metrics, Resources res) {

		this.context = context;
		this.metrics = metrics;
		this.res = res;

		loadBitmaps();
		initDisplayMetrics();

	}

	private ArrayList<int[]> myArrayList;
	private Context context;
	private DisplayMetrics metrics;
	private Resources res;
	private float dp, xPixels, yPixels;

	public int[] startPos;
	public float dpX40;
	public Shape gameArea[][];
	public int x, y;
	private AssetManager am;
	private static int bmSize = 40;

	private BackgroundBitmapLoader backgroundBitmap;
	private StartBitmapLoader startBitmap;
	private SimpleMirrorBitmapLoader simpleMirrorBitmap;
	private TMirrorBitmapLoader tMirrorBitmap;
	private TargetBitmapLoader taregetBitmap;

	/*
	 * Function loads a game level from specified file - fileName and saves
	 * shape info as int[4] into variable mArrayList. Variable mArrayList
	 * contains level info, each element in list is one shape.
	 */
	public void levelFromFile(String fileName) throws FileNotFoundException {

		try {
			am = this.context.getAssets();
			InputStream is = am.open(fileName);
			InputStreamReader inputStreamReader = new InputStreamReader(is);
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			String temp = "ahoj";
			this.myArrayList = new ArrayList<int[]>();
			while (true) {
				int info[] = new int[4];
				temp = bufferedReader.readLine();
				if (temp == null)
					break;
				String[] row = temp.split(",");
				info[0] = Integer.parseInt(row[0]);
				info[1] = Integer.parseInt(row[1]);
				info[2] = Integer.parseInt(row[2]);
				info[3] = Integer.parseInt(row[3]);
				this.myArrayList.add(info);
			}

			levelToGame();

		} catch (Exception e) {
			// TODO
			Log.w("Exception", e);
		}

	}

	/*
	 * 0 - Start 1 - Finish 2 - SimpleMirror 3 - TMirror
	 */
	private void levelToGame() {
		this.gameArea = new Shape[x][y];
		for (int i = 0; i < x; i++)
			for (int j = 0; j < y; j++) {
				// if at i j is different object than background
				int temp[] = isShape(i, j);
				if (temp[0] < 0) {
					this.gameArea[i][j] = new Background(i, j, 0,
							this.gameArea, this.res, backgroundBitmap);
				} else {
					switch (temp[3]) {

					case 0:
						this.gameArea[i][j] = new Start(i, j, temp[2],
								this.gameArea, this.res, startBitmap);
						this.startPos = new int[2];
						this.startPos[0] = i;
						this.startPos[1] = j;
						break;

					case 1:
						this.gameArea[i][j] = new Target(i, j, temp[2],
								this.gameArea, this.res, taregetBitmap);
						break;

					case 2:
						this.gameArea[i][j] = new SimpleMirror(i, j, temp[2],
								this.gameArea, this.res, simpleMirrorBitmap);
						break;

					case 3:
						this.gameArea[i][j] = new TMirror(i, j, temp[2],
								this.gameArea, this.res, tMirrorBitmap);
						break;

					}

				}
			}

	}

	/*
	 * function testing if at given x and y is some game object or background
	 */
	private int[] isShape(int x, int y) {
		int[] souradnice = new int[4];
		for (int[] a : this.myArrayList) {
			System.arraycopy(a, 0, souradnice, 0, a.length);
			if (souradnice[0] == x && souradnice[1] == y) {
				return souradnice;
			} else
				souradnice[0] = -1;
		}
		return souradnice;
	}

	private void initDisplayMetrics() {

		this.dp = metrics.density;
		this.xPixels = metrics.widthPixels;
		this.yPixels = metrics.heightPixels;

		this.x = (int) (xPixels / (bmSize * dp));
		this.y = (int) (yPixels / (bmSize * dp));

		this.dpX40 = bmSize * dp;
		Log.w("DP==============", Float.toString(dp));

	}

	private void loadBitmaps() {
		this.backgroundBitmap = new BackgroundBitmapLoader(res);
		this.tMirrorBitmap = new TMirrorBitmapLoader(res);
		this.startBitmap = new StartBitmapLoader(res);
		this.taregetBitmap = new TargetBitmapLoader(res);
		this.simpleMirrorBitmap = new SimpleMirrorBitmapLoader(res);

	}

}
