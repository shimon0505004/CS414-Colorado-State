package edu.colostate.cs414.android_app_client;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class OrderActivity extends ActionBarActivity {

	JSONObject store = null;
	Context context;
	// TableLayout leftTableLayout , rightTableLayout;
	TableLayout menuItemTable;
	Spinner menuSpinner;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order);
		context = (Context) this;
		initializeStore();
		// leftTableLayout = (TableLayout) findViewById(R.id.leftLayout1_Order);
		// rightTableLayout = (TableLayout)
		// findViewById(R.id.rightTableLayout1_Order);
		try {
			initView();
		} catch (Exception e) {
			Log.d("Oncreate in OrderActivity", e.getStackTrace().toString());
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.order, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void initializeStore() {
		AsyncTask<Void, Void, JSONObject> storeResult = new GetStore()
				.execute();
		try {
			store = (JSONObject) storeResult.get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.d("Oncreate OrderActivity", e.getStackTrace().toString());
		}
	}

	public void initView() {
		menuSpinner = (Spinner) findViewById(R.id.spinner_MenuDropList);
		menuItemTable = (TableLayout) findViewById(R.id.tableLayout_MenuItem);
		menuItemTable.setStretchAllColumns(true);
		if (store != null) {

			try {
				JSONArray menuArray = store.getJSONArray("setOfMenus");

				List<String> menuList = new ArrayList<String>();

				for (int i = 0; i < menuArray.length(); i++) {
					JSONObject menu = menuArray.getJSONObject(i);
					String menuName = menu.getString("menuName");
					menuList.add(menuName);
				}

				ArrayAdapter<String> spinAdapter = new ArrayAdapter<String>(
						this, android.R.layout.simple_spinner_dropdown_item,
						menuList);
				spinAdapter
						.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				menuSpinner.setAdapter(spinAdapter);

				menuSpinner
						.setOnItemSelectedListener(new menuSpinnerListener());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private class menuSpinnerListener implements OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			JSONArray menuArray;

			menuItemTable.removeAllViews();
			TableRow tbrow0 = new TableRow(OrderActivity.this);
			TextView t1v = new TextView(OrderActivity.this);
			t1v.setText("Name");
			TextView t2v = new TextView(OrderActivity.this);
			t2v.setText("Price");
			TextView t3v = new TextView(OrderActivity.this);
			t3v.setText("Description");
			tbrow0.addView(t1v);
			tbrow0.addView(t2v);
			tbrow0.addView(t3v);
			menuItemTable.addView(tbrow0);

			try {
				menuArray = store.getJSONArray("setOfMenus");
				String menuName = parent.getItemAtPosition(position).toString();
				for (int i = 0; i < menuArray.length(); i++) {
					JSONObject menu = menuArray.getJSONObject(i);
					if (menuName.equals(menu.getString("menuName"))) {
						JSONArray menuItemArray = menu
								.getJSONArray("menuItemsSet");

						for (int j = 0; j < menuItemArray.length(); j++) {

							JSONObject menuItem = menuItemArray
									.getJSONObject(j);
							String menuItemName = menuItem
									.getString("itemName");
							double menuItemPrice = menuItem
									.getDouble("itemPrice");
							String menuItemDesc = menuItem
									.getString("itemDescription");
							TableRow tbrow = new TableRow(OrderActivity.this);
							TextView tv_name = new TextView(OrderActivity.this);
							tv_name.setText(menuItemName);
							TextView tv_price = new TextView(OrderActivity.this);
							tv_price.setText(Double.toString(menuItemPrice));
							TextView tv_desc = new TextView(OrderActivity.this);
							tv_desc.setText(menuItemDesc);
							tbrow.addView(tv_name);
							tbrow.addView(tv_price);
							tbrow.addView(tv_desc);
							tbrow.setOnClickListener(menuItemTableRow_Listener);
							menuItemTable.addView(tbrow);

						}

					}
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub

		}
	}

	private OnClickListener menuItemTableRow_Listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			TableRow tr = (TableRow) v;
			TextView t1v = (TextView) tr.getChildAt(0);
			TextView t2v = (TextView) tr.getChildAt(1);
			String itemName = t1v.getText().toString();
			double itemPrice = Double.parseDouble(t2v.getText().toString());

			LayoutInflater inflater = getLayoutInflater();
			final View layout = inflater.inflate(
					R.layout.activity_customer_enter_amount,
					(ViewGroup) findViewById(R.id.dialog));
			AlertDialog.Builder builder = new AlertDialog.Builder(
					OrderActivity.this);

			TextView tv_name = (TextView) layout.findViewById(R.id.textView_itemName);
			TextView tv_price = (TextView) layout.findViewById(R.id.textView_itemPrice);
			final NumberPicker np = (NumberPicker) layout.findViewById(R.id.numberPicker);
			np.setMaxValue(100);
			tv_name.setText("Name: "+itemName);
			tv_price.setText("Price: "+itemPrice);
			builder.setView(layout);
			builder.setTitle("Enter Amount:");
			
			builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					int amount = np.getValue();
				}
			});
			builder.setNegativeButton("Cancel", null);
			AlertDialog dialog = builder.create();
			dialog.show();
			
		}
	};

}
