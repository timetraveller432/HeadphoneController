/*
 * TODO: license
 */
package ca.mbabic.headphonecontroller;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Fragment displaying UI elements related to the list of configurable inputs.
 * 
 * @author Marko Babic
 * 
 */
public class ConfigurationFragment extends ListFragment {

	private static final String[] CONFIGURATION_OPTIONS = new String[] {
			"One Press", "Two Presses", "Three Presses" };

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);

	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		ArrayAdapter<String> adapter;

		super.onActivityCreated(savedInstanceState);

		adapter = new ArrayAdapter<String>(getActivity(),
				R.layout.configuration_list_item, R.id.label,
				CONFIGURATION_OPTIONS);

		setListAdapter(adapter);

	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {

		Intent selectCommandIntent;
		Bundle args;
		String item;

		item = (String) getListView().getItemAtPosition(position);

		selectCommandIntent = new Intent(getActivity(),
				SelectCommandActivity.class);
		
		args = new Bundle();
		args.putString(SelectCommandActivity.INPUT_SEQUENCE_KEY, item);
		
		startActivityForResult(selectCommandIntent, 0);
		

	}

}
