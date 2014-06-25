package ca.mbabic.headphonecontroller;

import android.app.ListActivity;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;
import ca.mbabic.headphonecontroller.configuration.HCConfigAdapter;
import ca.mbabic.headphonecontroller.models.State;
import ca.mbabic.headphonecontroller.views.CommandAdapter;

public class SelectCommandActivity extends ListActivity {

	public static final String INPUT_SEQUENCE_KEY = "input_sequence_key";
	public static final String[] AVAILABLE_COMMANDS = new String[] {

	"Play/Pause", "Skip Track", "Repeat/Previous Track"

	};

	private String mInputSequence;
	private State mState;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		CommandAdapter viewAdapter;
		HCConfigAdapter configAdapter = new HCConfigAdapter(PreferenceManager.getDefaultSharedPreferences(this), this);
		Bundle args;

		super.onCreate(savedInstanceState);

		setContentView(R.layout.select_command_list_view);

		args = getIntent().getExtras();

		if (args != null) {
			mInputSequence = args.getString(INPUT_SEQUENCE_KEY);
			mState = configAdapter.getState(mInputSequence);
		} else {
			// TODO: Log error? Throw exception? Quit?
		}

		viewAdapter = new CommandAdapter(this, R.layout.select_command_list_view, 
			null);
		
//		adapter = new ArrayAdapter<String>(this,
//				R.layout.select_command_list_item, R.id.command_selection_label,
//				AVAILABLE_COMMANDS) {
//
//			private int mPositionSelected = 0;
//
//			public void setPositionSelected(int newPosition) {
//				mPositionSelected = newPosition;
//			}
//			
//			@Override
//			public View getView(int position, View convertView, 
//					ViewGroup parent) {
//
//				LayoutInflater viewInflater;
//				TextView textView;
//				RadioButton radioButton;
//				View view;
//				
//				view = convertView;
//				
//				if (view == null) {
//					
//					viewInflater = (LayoutInflater) 
//							getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//					
//					view = viewInflater.inflate(
//							R.layout.select_command_list_item, null);
//					
//				}
//				
//				radioButton = (RadioButton) 
//						view.findViewById(R.id.command_selection_button);
//				
//				textView = (TextView) 
//						view.findViewById(R.id.command_selection_label);
//				
//				radioButton.setChecked(position == mPositionSelected);
//				radioButton.setTag(position);
//				radioButton.setOnClickListener(new View.OnClickListener() {
//					
//					@Override
//					public void onClick(View v) {
//						setPositionSelected((int) v.getTag());
//						notifyDataSetInvalidated();
//					}
//				});
//				
//				return view;
//			}
//
//		};
//		
		setListAdapter(viewAdapter);

	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		RadioButton radioButton = (RadioButton)
				v.findViewById(R.id.command_selection_button);
		
		radioButton.setChecked(true);
		
	}
	
}
