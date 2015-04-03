package com.bate.mirrors.LevelListActivity;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.batek.mirrors.Database.MySQLiteDb;
import com.batek.mirrors.GameAccessories.Constants;
import com.batek.mirrors.GameAccessories.GameView;
import com.example.mirrors.R;

public class LevelListActivity extends Activity {

	private ListView listView;
	private MySQLiteDb myDb;
	private ArrayList<Level> values;
	private Context context;
	private Constants constants;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level_list_view);

		myDb = new MySQLiteDb(this);
		// Get ListView object from xml
		listView = (ListView) findViewById(R.id.list);

		
		context = this;
		
		constants = Constants.getInstance();

		setOnClickListener();
		fetchList();
	}


	private void setOnClickListener() {

		// ListView Item Click Listener
		listView.setOnItemClickListener(new OnItemClickListener() {

			private GameView game;

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// ListView Clicked item value
				Level itemValue = (Level) listView.getItemAtPosition(arg2);

				// load level based on clicked item
				int lvl = itemValue.getLevel();
				Log.w("Level going to be loaded", Integer.toString(lvl));
				if (lvl < 0 || lvl > Constants.levelCouunt)
					;
				else {
					Log.w("Level: ",""+lvl);
					game = new GameView(context, constants.getMainActivity().getDm(),  lvl);
					setContentView(game);
				}

			}

		});

	}

	//select all levels form db
	private void getValues() {
		try {
			values = this.myDb.selectAll();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void fetchList() {

		getValues();
		LevelsToListAdapter adapter = new LevelsToListAdapter(this, values);
		// Assign adapter to ListView
		listView.setAdapter(adapter);
	}

}
