package com.batek.mirrors.GameAccessories;

import com.bate.mirrors.MainActivity.MainActivity;

/**
 * 
 * @author Batek
 * 
 *         Singelton class,
 * 
 *         keeps instance of main activity and some constants
 * 
 */
public class Constants {

	public static final int levelCouunt = 7;
	public static final String lvlTook = "This level took you ";
	public static final String sec = " seconds";

	private static Constants constants;

	private MainActivity mainActivityt;
	private GameView gameView;
	private boolean soundOn = false;

	public boolean isSoundOn() {
		return soundOn;
	}

	public void setSoundOn(boolean soundOn) {
		this.soundOn = soundOn;
	}

	public GameView getGameView() {
		return gameView;
	}

	public void setGameView(GameView gameView) {
		this.gameView = gameView;
	}

	public MainActivity getMainActivity() {
		return mainActivityt;
	}

	public void setMainActivity(MainActivity mainActivityt) {
		this.mainActivityt = mainActivityt;
	}

	private Constants() {
	};

	public static Constants getInstance() {
		if (constants == null)
			constants = new Constants();
		return constants;
	}

}
