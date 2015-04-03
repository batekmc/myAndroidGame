package com.batek.mirrors.GameAccessories;

import java.io.FileNotFoundException;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.util.DisplayMetrics;
import android.view.View;

import com.bate.mirrors.MainActivity.MainActivity;
import com.batek.mirrors.Database.MySQLiteDb;
import com.batek.mirrors.Shapes.Shape;
import com.batek.mirrors.Shapes.Start;
import com.batek.mirrors.Shapes.Target;

@SuppressLint({ "ViewConstructor", "ViewConstructor" })
public class GameView extends View {

	float dpX40;
	private int x, y;
	private DisplayMetrics metrics;
	private Context context;
	private Shape gameArea[][];
	private Resources res;
	private TouchControl tControl;
	private Init init;
	private int[] startPos;// coordinates of start object
	private Start ptStart;// pointer to starting object
	private int level = 1;
	private long timer1, timer2;
	private MySQLiteDb myDb;

	private Constants constants;

	public GameView(Context context, DisplayMetrics metrics, int lvl) {

		super(context);
		setFocusable(true);
		setFocusableInTouchMode(true);
		requestFocus();

		constants = Constants.getInstance();
		constants.setGameView(this);

		this.metrics = metrics;
		this.context = context;
		res = getResources();
		this.init = new Init(this.context, this.metrics, this.res);

		this.x = init.x;
		this.y = init.y;
		this.dpX40 = init.dpX40;
		
		this.level = lvl;

		this.myDb = new MySQLiteDb(this.context);

		tControl = new TouchControl(dpX40, this);
		setOnTouchListener(tControl);

		nexLevel();

		refreshLight();
	}

	public void refreshLight() {

		clear();
		try {
			this.ptStart.startLight();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Refresh screen
		invalidate();

		if (isLevelComplete()) {
			timer2 = System.currentTimeMillis();
			try {
				this.myDb.updateOrInsertLevelAndTime(this.level - 1,
						(int) timeOfLevel());
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (level <= Constants.levelCouunt)
				alertDialog();
			else {
				alertGameFinished();
			}
		}

	}

	private long timeOfLevel() {

		return (long) (timer2 - timer1);
	}

	private void alertDialog() {
		final AlertDialog alertDialog = new AlertDialog.Builder(context)
				.create();
		// level - 1 is because value is incremented in nextLevel()
		alertDialog.setTitle("Level " + (this.level - 1) + " complete!");
		alertDialog.setMessage(Constants.lvlTook + timeOfLevel() / 1000.0
				+ Constants.sec);
		alertDialog.setCanceledOnTouchOutside(false);
		alertDialog.setButton(-1, "Next Level ",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						alertDialog.setTitle("Loading...");
						nexLevel();
					}
				});
		alertDialog.show();
	}

	private void alertGameFinished() {
		final AlertDialog alertDialog = new AlertDialog.Builder(context)
				.create();
		alertDialog.setTitle("Game finished!");
		alertDialog.setMessage(Constants.lvlTook + timeOfLevel() / 1000.0
				+ Constants.sec);
		alertDialog.setCanceledOnTouchOutside(false);
		alertDialog.setButton(-1, "OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				switchToMainActivity();
			}
		});
		alertDialog.show();
	}

	private void clear() {
		for (int i = 0; i < x; i++)
			for (int j = 0; j < y; j++)
				this.gameArea[i][j].clear();
	}

	private boolean isLevelComplete() {
		for (int i = 0; i < x; i++)
			for (int j = 0; j < y; j++)
				if (this.gameArea[i][j] instanceof Target) {
					Target temp = (Target) this.gameArea[i][j];
					if (!temp.isFinished())
						return false;
				}

		return true;

	}
	
	private void switchToMainActivity(){
		Intent i = new Intent(constants.getMainActivity().context, MainActivity.class);
		context.startActivity(i);
		
	}

	private void nexLevel() {
		try {
			timer1 = System.currentTimeMillis();
			StringBuffer temp = new StringBuffer("level").append(level).append(
					".txt");
			this.init.levelFromFile(temp.toString());
			this.gameArea = init.gameArea;
			this.startPos = init.startPos;
			this.ptStart = (Start) this.gameArea[startPos[0]][startPos[1]];

			try {
				this.myDb.addLevelFinished(level);
			} catch (Exception e) {
				e.printStackTrace();
			}
			// next call will be in new level
			level++;
			this.tControl.setGameArea(gameArea);
			refreshLight();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	@Override
	protected void onDraw(Canvas canvas) {

		for (int i = 0; i < x; i++)
			for (int j = 0; j < y; j++) {
				canvas.drawBitmap(this.gameArea[i][j].getBitmap(), i
						* this.dpX40, j * this.dpX40, null);
			}

	}

}
