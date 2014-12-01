package cs414.a5.nlighth1.android;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.zip.Inflater;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import edu.colostate.cs414.android_app_client.R;
import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnShowListener;
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
import android.widget.Button;
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
	TableLayout menuItemTable, orderTable;
	Spinner menuSpinner;
	JSONArray order;
	Button checkOutButton, viewOrderButton;
	View orderDialogLayout;
	double amountTotal = 0;
	private ArrayList<String> orderList = new ArrayList<String>();
	private String paymentInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order);
		context = (Context) this;
		initializeStore();
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

		checkOutButton = (Button) findViewById(R.id.button_checkout);
		viewOrderButton = (Button) findViewById(R.id.button_viewOrder);
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
						.setOnItemSelectedListener(new MenuSpinnerListener());

			} catch (Exception e) {
				e.printStackTrace();
			}

			orderTable = (TableLayout) findViewById(R.id.table_orderInfo);
			orderTable.setStretchAllColumns(true);
			TableRow tr1 = new TableRow(this);
			TextView r11 = new TextView(this);
			r11.setText("Your Order Total:");
			TextView r12 = new TextView(this);
			r12.setText("0");
			tr1.addView(r11);
			tr1.addView(r12);
			orderTable.addView(tr1);
			// orderTable.addView(tr2);

			viewOrderButton.setOnClickListener(new ViewOrderListener());
			checkOutButton.setOnClickListener(new CheckoutListener());
		}
	}

	class MenuSpinnerListener implements OnItemSelectedListener {

		@Override
		public void onItemSelected(AdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			JSONArray menuArray;

			menuItemTable.removeAllViews();
			TableRow tbrow0 = new TableRow(context);
			TextView t1v = new TextView(context);
			t1v.setText("Name");
			TextView t2v = new TextView(context);
			t2v.setText("Price");
			TextView t3v = new TextView(context);
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
							TableRow tbrow = new TableRow(context);
							TextView tv_name = new TextView(context);
							tv_name.setText(menuItemName);
							TextView tv_price = new TextView(context);
							tv_price.setText(Double.toString(menuItemPrice));
							TextView tv_desc = new TextView(context);
							tv_desc.setText(menuItemDesc);
							tbrow.addView(tv_name);
							tbrow.addView(tv_price);
							tbrow.addView(tv_desc);
							tbrow.setOnClickListener(new menuItemTableRow_Listener());
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

	class menuItemTableRow_Listener implements OnClickListener {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

			TableRow tr = (TableRow) v;
			TextView t1v = (TextView) tr.getChildAt(0);
			TextView t2v = (TextView) tr.getChildAt(1);
			String itemName = t1v.getText().toString();
			double itemPrice = Double.parseDouble(t2v.getText().toString());

			LayoutInflater inflater = getLayoutInflater();
			View layout = inflater.inflate(
					R.layout.activity_customer_enter_amount,
					(ViewGroup) findViewById(R.id.dialog));
			AlertDialog.Builder builder = new AlertDialog.Builder(context);

			TextView tv_name = (TextView) layout
					.findViewById(R.id.textView_itemName);
			TextView tv_price = (TextView) layout
					.findViewById(R.id.textView_itemPrice);
			NumberPicker np = (NumberPicker) layout
					.findViewById(R.id.numberPicker);
			np.setMaxValue(100);
			tv_name.setText("Name: " + itemName);
			tv_price.setText("Price: " + itemPrice);
			builder.setView(layout);
			builder.setTitle("Enter Amount:");

			builder.setPositiveButton("OK", new OrderItemListener());
			builder.setNegativeButton("Cancel", null);
			AlertDialog dialog = builder.create();
			orderDialogLayout = layout;
			dialog.show();

		}
	};

	class OrderItemListener implements DialogInterface.OnClickListener {
		@Override
		public void onClick(DialogInterface dialog, int which) {
			// TODO Auto-generated method stub
			TextView tv_itemName = (TextView) orderDialogLayout
					.findViewById(R.id.textView_itemName);
			String itemName = tv_itemName.getText().toString().split(": ")[1];
			TextView tv_price = (TextView) orderDialogLayout
					.findViewById(R.id.textView_itemPrice);
			String priceStr = tv_price.getText().toString().split(": ")[1];
			double unitPrice = Double.parseDouble(priceStr);

			NumberPicker np = (NumberPicker) orderDialogLayout
					.findViewById(R.id.numberPicker);
			int amount = np.getValue();
			double total = unitPrice * amount;
			amountTotal += total;

			TableLayout orderTable = (TableLayout) OrderActivity.this
					.findViewById(R.id.table_orderInfo);

			TableRow tbr1 = (TableRow) orderTable.getChildAt(0);
			TextView tv_priceTotal = (TextView) tbr1.getChildAt(1);
			tv_priceTotal.setText(String.valueOf(amountTotal));

			addOrdertoArray(orderList, itemName, unitPrice, amount);
		}

		private void addOrdertoArray(ArrayList<String> l, String name,
				double up, int amount) {

			boolean exists = false;
			if (l != null) {
				for (int i = 0; i < l.size(); i++) {
					String s = (String) l.get(i);
					if (s.split("/")[0].equals(name)) {
						int newAmount = Integer.parseInt(s.split("/")[2])
								+ amount;
						String ns = new StringBuilder().append(s.split("/")[0])
								.append("/").append(s.split("/")[1])
								.append("/").append(newAmount).append("/")
								.append(s.split("/")[3]).toString();
						l.set(i, ns);
						exists = true;
					}
				}
			}
			if (!exists) {
				String s = new StringBuilder().append(name).append("/")
						.append(up).append("/").append(amount).append("/")
						.append(up * amount).toString();
				l.add(s);
			}
		}
	}

	class ViewOrderListener implements OnClickListener {

		View viewOrderLayout;

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			LayoutInflater inflater = getLayoutInflater();
			viewOrderLayout = inflater.inflate(R.layout.activity_order_detail,
					(ViewGroup) findViewById(R.id.dialog));
			AlertDialog.Builder builder = new AlertDialog.Builder(context);

			TableLayout tbLayout = (TableLayout) viewOrderLayout
					.findViewById(R.id.tableLayout_orderDetail);
			tbLayout.setStretchAllColumns(true);
			TableRow tr1 = new TableRow(context);
			TextView tv1 = new TextView(context);
			tv1.setText("Name");
			TextView tv2 = new TextView(context);
			tv2.setText("Unit Price");
			TextView tv3 = new TextView(context);
			tv3.setText("Quant.");
			tr1.addView(tv1);
			tr1.addView(tv2);
			tr1.addView(tv3);

			tbLayout.addView(tr1);

			builder.setView(viewOrderLayout);

			builder.setTitle("Order Detail");
			builder.setPositiveButton("OK", new ViewOrderOKListener());
			builder.setNegativeButton("Cancel", null);
			addOrdertoTable(orderList, tbLayout);

			AlertDialog dialog = builder.create();

			dialog.show();

		}

		private void addOrdertoTable(ArrayList<String> l, TableLayout tl) {
			if (l != null) {
				for (String s : l) {
					TableRow tr = new TableRow(context);
					TextView tv1 = new TextView(context);
					TextView tv2 = new TextView(context);
					TextView tv3 = new TextView(context);
					tv1.setText(s.split("/")[0]);
					tv2.setText(s.split("/")[1]);
					tv3.setText(s.split("/")[2]);
					tr.addView(tv1);
					tr.addView(tv2);
					tr.addView(tv3);
					tl.addView(tr);
					tr.setOnClickListener(new EditOrderListener());
				}
			}
		}

		class EditOrderListener implements OnClickListener {
			TableRow tr;
			TextView t1v, t2v, t3v;
			NumberPicker np;

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tr = (TableRow) v;
				t1v = (TextView) tr.getChildAt(0);
				t2v = (TextView) tr.getChildAt(1);
				t3v = (TextView) tr.getChildAt(2);

				String itemName = t1v.getText().toString();
				String itemPrice = t2v.getText().toString();
				int itemAmount = Integer.parseInt(t3v.getText().toString());

				LayoutInflater inflater = getLayoutInflater();
				View layout = inflater.inflate(R.layout.activity_edit_order,
						(ViewGroup) findViewById(R.id.dialog));
				AlertDialog.Builder builder = new AlertDialog.Builder(context);

				TextView tv_name = (TextView) layout
						.findViewById(R.id.textView_editOrder_itemName);
				TextView tv_price = (TextView) layout
						.findViewById(R.id.textView_editOrder_itemPrice);
				np = (NumberPicker) layout
						.findViewById(R.id.numberPicker_editOrder);

				tv_name.setText(itemName);
				tv_price.setText(itemPrice);
				np.setValue(itemAmount);
				np.setMaxValue(100);

				builder.setView(layout);
				builder.setTitle("Edit Order");
				builder.setPositiveButton("OK", new EditOrder_OKListener());
				builder.setNegativeButton("Cancel", null);

				AlertDialog dialog = builder.create();
				dialog.show();
			}

			class EditOrder_OKListener implements
					android.content.DialogInterface.OnClickListener {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					int newNum = np.getValue();
					if (newNum != 0) {
						t3v.setText(String.valueOf(newNum));
					} else {
						TableLayout tbLayout = (TableLayout) viewOrderLayout
								.findViewById(R.id.tableLayout_orderDetail);
						tbLayout.removeView(tr);
					}
				}

			}

		}

		class ViewOrderOKListener implements
				android.content.DialogInterface.OnClickListener {

			double newTotal = 0;

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				TableLayout tb_order = (TableLayout) viewOrderLayout
						.findViewById(R.id.tableLayout_orderDetail);
				ArrayList<String> newOrder = getOrder(tb_order);
				updateOrderArray(newOrder);
				TableLayout tb_orderInfo = (TableLayout) OrderActivity.this
						.findViewById(R.id.table_orderInfo);
				TableRow tbr = (TableRow) tb_orderInfo.getChildAt(0);
				TextView tv_priceTotal = (TextView) tbr.getChildAt(1);
				tv_priceTotal.setText(String.valueOf(newTotal));
			}

			private ArrayList<String> getOrder(TableLayout tb) {
				ArrayList<String> newOrderArray = new ArrayList<String>();
				int orderNum = tb.getChildCount();
				for (int i = 1; i < orderNum; i++) {
					TableRow tr = (TableRow) tb.getChildAt(i);
					String name = ((TextView) tr.getChildAt(0)).getText()
							.toString();
					String upStr = ((TextView) tr.getChildAt(1)).getText()
							.toString();
					String amountStr = ((TextView) tr.getChildAt(2)).getText()
							.toString();
					double up = Double.parseDouble(upStr);
					int amount = Integer.parseInt(amountStr);
					double total = up * amount;
					String newOrder = new StringBuilder().append(name)
							.append("/").append(upStr).append("/")
							.append(amountStr).append("/").append(total)
							.toString();
					newOrderArray.add(newOrder);
					newTotal += total;
				}
				return newOrderArray;
			}

			private void updateOrderArray(ArrayList<String> l) {
				orderList = l;
			}
		}
	}

	class CheckoutListener implements OnClickListener {

		String addr, cardNum, cardCV2;
		View layout;
		AlertDialog dialog;

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if (orderList.size() != 0) {

				new AlertDialog.Builder(context)
						.setIcon(android.R.drawable.ic_dialog_info)
						.setMessage("Do you want to use membership account?")
						.setPositiveButton("Yes",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface d,
											int which) {
										// TODO Auto-generated method stub
										LayoutInflater inflater = getLayoutInflater();
										layout = inflater
												.inflate(
														R.layout.activity_checkout,
														(ViewGroup) findViewById(R.id.dialog));
										TextView tv_memID = (TextView) layout
												.findViewById(R.id.textView_checkout_memID);
										EditText et_memID = (EditText) layout
												.findViewById(R.id.editText_checkout_memID);
										tv_memID.setVisibility(View.VISIBLE);
										et_memID.setVisibility(View.VISIBLE);
										AlertDialog.Builder builder = new AlertDialog.Builder(
												context);
										builder.setView(layout);
										builder.setTitle("Checkout");
										builder.setPositiveButton("OK", null);
										builder.setNegativeButton("Cancel",
												null);
										dialog = builder.create();
										dialog.show();

										// remain the previous dialog
										dialog.getButton(
												AlertDialog.BUTTON_POSITIVE)
												.setOnClickListener(
														new checkoutOKListener());
									}
								})
						.setNegativeButton("No",
								new DialogInterface.OnClickListener() {

									@Override
									public void onClick(DialogInterface d,
											int which) {
										// TODO Auto-generated method stub
										LayoutInflater inflater = getLayoutInflater();
										layout = inflater
												.inflate(
														R.layout.activity_checkout,
														(ViewGroup) findViewById(R.id.dialog));
										AlertDialog.Builder builder = new AlertDialog.Builder(
												context);
										builder.setView(layout);
										builder.setTitle("Checkout");
										builder.setPositiveButton("OK", null);
										builder.setNegativeButton("Cancel",
												null);
										dialog = builder.create();
										dialog.show();

										// remain the previous dialog
										dialog.getButton(
												AlertDialog.BUTTON_POSITIVE)
												.setOnClickListener(
														new checkoutOKListener());
									}
								}).show();

			} else {
				new AlertDialog.Builder(context)
						.setIcon(android.R.drawable.ic_dialog_info)
						.setMessage("You havn't ordered yet!")
						.setPositiveButton("OK", null).show();
			}

		}

		class checkoutOKListener implements OnClickListener {

			@Override
			public void onClick(View v) {// DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

				if (valid()) {
					new AlertDialog.Builder(context).setTitle(null)
							.setIcon(android.R.drawable.ic_dialog_info)
							.setMessage("Pay for " + amountTotal + "?")
							.setPositiveButton("Pay", new confirmPayListener())
							.setNegativeButton("Cancel", null).show();
				} else {
					AlertDialog.Builder builder = new AlertDialog.Builder(
							context);
					builder.setTitle("Warning")
							.setIcon(android.R.drawable.ic_dialog_alert)
							.setMessage(
									"The information you entered is not valid. Please try again.")
							.setPositiveButton("OK", null).show();

				}
			}

			private boolean valid() {
				EditText input_memID = (EditText) layout
						.findViewById(R.id.editText_checkout_memID);
				EditText input_addr = (EditText) layout
						.findViewById(R.id.editText_checkout_address);
				EditText input_cardNum = (EditText) layout
						.findViewById(R.id.editText_checkout_cardNum);
				EditText input_expDate1 = (EditText) layout
						.findViewById(R.id.editText_checkout_expDate);
				EditText input_expDate2 = (EditText) layout
						.findViewById(R.id.editText_checkout_expDate2);
				EditText input_CV2 = (EditText) layout
						.findViewById(R.id.editText_checkout_CV2);

				if (input_memID.getVisibility() == View.VISIBLE) {

					if (input_memID.length() > 0 && input_addr.length() != 0
							&& input_cardNum.length() == 16
							&& input_expDate1.length() == 2
							&& input_expDate2.length() == 2
							&& input_CV2.length() == 3) {
						paymentInfo = new StringBuilder()
								.append(input_memID.getText().toString())
								.append("/")
								.append(input_addr.getText().toString())
								.append("/")
								.append(input_cardNum.getText().toString())
								.append("/")
								.append(input_expDate1.getText().toString())
								.append("/")
								.append(input_expDate2.getText().toString())
								.append("/")
								.append(input_CV2.getText().toString())
								.toString();
						return true;
					} else {
						return false;
					}
				} else {
					if (input_addr.length() != 0
							&& input_cardNum.length() == 16
							&& input_expDate1.length() == 2
							&& input_expDate2.length() == 2
							&& input_CV2.length() == 3) {
						paymentInfo = new StringBuilder()
								.append(input_memID.getText().toString())
								.append("/")
								.append(input_addr.getText().toString())
								.append("/")
								.append(input_cardNum.getText().toString())
								.append("/")
								.append(input_expDate1.getText().toString())
								.append("/")
								.append(input_expDate2.getText().toString())
								.append("/")
								.append(input_CV2.getText().toString())
								.toString();
						return true;
					} else {
						return false;
					}
				}
			}

			class confirmPayListener implements DialogInterface.OnClickListener {

				@Override
				public void onClick(DialogInterface d, int which) {
					// TODO Auto-generated method stub
					dialog.dismiss();

					JSONObject customerOrder = new JSONObject();

					String memID = paymentInfo.split("/")[0];
					String addr = paymentInfo.split("/")[1];
					String cardNum = paymentInfo.split("/")[2];
					String expDate = paymentInfo.split("/")[3].concat("/")
							.concat(paymentInfo.split("/")[4]);
					String cv = paymentInfo.split("/")[5];

					try {
						customerOrder.put("memberID", memID);
						customerOrder.put("address", addr);
						customerOrder.put("cardNumber", cardNum);
						customerOrder.put("expDate", expDate);
						customerOrder.put("cv2", cv);
						// ArrayList<String> order = new ArrayList<String>();
						JSONArray order = new JSONArray();
						for (int i = 0; i < orderList.size(); i++) {
							String tmpOrder = new StringBuilder()
									.append(orderList.get(i).split("/")[0])
									.append("/")
									.append(orderList.get(i).split("/")[2])
									.toString();
							order.put(tmpOrder);
						}


						customerOrder.put("orderList", order);
						AsyncTask result = new SetOrderData()
								.execute(customerOrder);

						String s = (String) result.get();

						JSONObject object = new JSONObject(s);

						if (layout.findViewById(R.id.textView_checkout_memID)
								.getVisibility() == View.VISIBLE) {

							if (object.get("isOrderedByCustomerWithMembership")
									.toString() == "true") {
								successDiag();
							} else {
								new AlertDialog.Builder(context)
										.setIcon(
												android.R.drawable.ic_dialog_info)
										.setMessage(
												"Invalid Customer ID. Please try again!")
										.setPositiveButton("OK", null).show();
							}
						} else{
							successDiag();
						}

					} catch (JSONException e) {
						// TODO Auto-generated catch block
						errorDiag();
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						errorDiag();
						e.printStackTrace();
					} catch (ExecutionException e) {
						// TODO Auto-generated catch block
						errorDiag();
						e.printStackTrace();
					}

				}

				private void successDiag() {

					new AlertDialog.Builder(context)
							.setIcon(android.R.drawable.ic_dialog_info)
							.setMessage("Payment Success!")
							.setPositiveButton("OK",
									new DialogInterface.OnClickListener() {

										@Override
										public void onClick(
												DialogInterface dialog,
												int which) {
											// TODO Auto-generated method stub
											orderList = new ArrayList<String>();
											amountTotal = 0;
											TableLayout orderTable = (TableLayout) OrderActivity.this
													.findViewById(R.id.table_orderInfo);

											TableRow tbr1 = (TableRow) orderTable
													.getChildAt(0);
											TextView tv_priceTotal = (TextView) tbr1
													.getChildAt(1);
											tv_priceTotal.setText(String
													.valueOf(amountTotal));
										}
									}).show();

				}

				private void errorDiag() {
					new AlertDialog.Builder(context)
							.setIcon(android.R.drawable.ic_dialog_info)
							.setMessage(
									"Payment Unsuccessful. Please try again!")
							.setPositiveButton("OK", null).show();
				}
			}
		}
	}
}