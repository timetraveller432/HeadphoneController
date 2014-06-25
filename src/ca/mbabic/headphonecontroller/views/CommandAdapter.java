package ca.mbabic.headphonecontroller.views;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;
import ca.mbabic.headphonecontroller.R;
import ca.mbabic.headphonecontroller.models.Command;

/**
 * Array adapter for the display of HCCommands in a view.
 * 
 * @author Marko Babic
 * 
 */
public class CommandAdapter extends ArrayAdapter<Command> {

	private ArrayList<Command> objs;
	private Context cxt;

	public CommandAdapter(Context context, int textViewResourceId,
			ArrayList<Command> objects) {
		super(context, textViewResourceId, objects);
		objs = objects;
		cxt = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater viewInflater;
		TextView textView;
		View view;
		Command cmd;

		view = convertView;

		if (view == null) {

			viewInflater = (LayoutInflater) cxt
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

			view = viewInflater
					.inflate(R.layout.select_command_list_item, null);

		}

		cmd = objs.get(position);

		textView = (TextView) view.findViewById(R.id.command_selection_label);

		textView.setText(cmd.getName());

		return view;
	}
}
