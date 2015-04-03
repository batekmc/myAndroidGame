package com.bate.mirrors.LevelListActivity;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mirrors.R;

public class LevelsToListAdapter extends ArrayAdapter<Level> {

	private ArrayList<Level> values;
	private Context context;

	/**
	 * 
	 * @param context
	 *            - context
	 * @param values
	 *            - arraylist of levels
	 */
	public LevelsToListAdapter(Context context, ArrayList<Level> values) {
		super(context, android.R.layout.simple_list_item_2, values);
		this.values = values;
		this.context = context;
	}

	@Override
	public long getItemId(int position) {
		return values.get(position).getLevel();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		try {
			View rowView = convertView;
			if (rowView == null) {
				LayoutInflater inflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				rowView = inflater.inflate(R.layout.my_adapter, null);
			}
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			rowView = inflater.inflate(R.layout.my_adapter, parent, false);
			ImageView imageView = (ImageView) rowView
					.findViewById(R.id.imageView);
			TextView textView1 = (TextView) rowView
					.findViewById(R.id.firstLine);
			TextView textView2 = (TextView) rowView
					.findViewById(R.id.secondLine);
			textView1.setText("Level"
					+ Integer.toString(values.get(position).getLevel()));
			textView2
					.setText("Yours best time: "
							+ Double.toString((values.get(position).getTime() / 1000.0))
							+ " s.");
			imageView.setImageResource(R.drawable.ic);
			return rowView;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
