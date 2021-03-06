package com.bate.mirrors.MainActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;

import com.bate.mirrors.LevelListActivity.LevelListActivity;
import com.batek.mirrors.Database.MySQLiteDb;
import com.batek.mirrors.GameAccessories.Constants;
import com.batek.mirrors.GameAccessories.GameView;
import com.example.mirrors.R;

public class MainActivity extends Activity {

	private GameView game;
	private DisplayMetrics dm;
	private Constants constants;
	public Context context;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		
		constants = Constants.getInstance();
		constants.setMainActivity(this);
		context = this;

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void onClickNewGame(View v) {
		
		game = new GameView(this, dm,  0);
		setContentView(game);
	}

	public void onClickContinue(View v) {
	
		Intent myIntent = new Intent(this, LevelListActivity.class);
		try {
			startActivityForResult(myIntent, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void onClickExit(View v) {
		finish();
		System.exit(0);

	}
	
	public void startLevel(int level) {
		game = new GameView(this, dm,  level);
		setContentView(game);
	}

	/*
	 * If pressed always displays menu
	 */
	@Override
	public void onBackPressed() {
		setContentView(R.layout.activity_main);
	}
	
	public DisplayMetrics getDm() {
		return dm;
	}

}
