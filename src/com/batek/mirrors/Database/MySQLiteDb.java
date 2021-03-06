package com.batek.mirrors.Database;

import java.util.ArrayList;

import com.bate.mirrors.LevelListActivity.Level;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class MySQLiteDb extends SQLiteOpenHelper {

	private static final String TABLE_NAME = "tabulka";
	private static final String TIME_COLUMN = "time";
	private static final String LEVEL_COLUMN = "level";
	private static final int DATABASE_VERSION = 2;
	private static final String DATABASE_NAME = "MyDB";

	private static final String MyTable_TABLE_CREATE = "CREATE TABLE "
			+ TABLE_NAME + " (" + LEVEL_COLUMN + " INTEGER PRIMARY KEY, "
			+ TIME_COLUMN + " INTEGER NOT NULL" + ");";

	public MySQLiteDb(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(MyTable_TABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	public void addLevelFinished(int level) {
		try {
			SQLiteDatabase db = this.getWritableDatabase();

			ContentValues values = new ContentValues();
			values.put(LEVEL_COLUMN, level); // Contact Name
			values.put(TIME_COLUMN, 99999999); // Contact Phone Number

			// Inserting Row
			db.insertOrThrow(TABLE_NAME, null, values);
			db.close(); 

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param level
	 *            level played
	 * @param time
	 *            time reached
	 */
	public void updateOrInsertLevelAndTime(int level, int time) {
		try {

			String query = "SELECT * FROM " + TABLE_NAME
					+ " where " + LEVEL_COLUMN + " = " + level + ";";
			SQLiteDatabase db = this.getWritableDatabase();
			Cursor curs = db.rawQuery(query, null);
			if (curs.moveToFirst()) {
				if (time < 0)
					time = 999999;
				int tempTime = Integer.parseInt(curs.getString(1));
				curs.close();
				if (tempTime > time || tempTime <= 0) {
					String update = "UPDATE " + TABLE_NAME + " SET "
							+ TIME_COLUMN + " = " + time + " where "
							+ LEVEL_COLUMN + " = " + level + ";";
					db.execSQL(update);
				}

				db.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public ArrayList<Level> selectAll() {
		try {
			ArrayList<Level> myArrList = new ArrayList<Level>();
			String query = "SELECT * FROM " + TABLE_NAME;
			SQLiteDatabase db = this.getReadableDatabase();
			Cursor curs = db.rawQuery(query, null);
			if (curs.moveToFirst()) { 
				do {
					Level lvl = new Level();
					lvl.setLevel(Integer.parseInt(curs.getString(0)));
					lvl.setTime(Integer.parseInt(curs.getString(1)));
					myArrList.add(lvl);
				} while (curs.moveToNext());
				curs.close();
			}
			db.close();
			return myArrList;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public void deleteAllTableData() {
		try {
			String delete = " DELETE  FROM " + TABLE_NAME + " where "
					+ LEVEL_COLUMN + " > -1;";
			SQLiteDatabase db = this.getReadableDatabase();
			db.execSQL(delete);
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
