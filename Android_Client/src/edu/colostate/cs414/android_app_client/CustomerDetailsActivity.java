package edu.colostate.cs414.android_app_client;

import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;
import com.google.gson.Gson;
import cs414.pos.Customer;
import cs414.pos.Order;
import android.R.integer;
import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class CustomerDetailsActivity extends ActionBarActivity {

	JSONObject  customer = null;
	String customerName = "";
	Context context;
	String membershipID = null;
	int rewardsPoint = 0;
	String phoneNumber = "";
	int minReqRewardsPoint = 0;
	TableLayout stk;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_customer_details);
		context = (Context)this;
		initializeCustomer();
		try {
			initView();
		} catch (Exception e) {
			// TODO: handle exception
			Log.d("Oncreate in CustomerDetailsActivity", e.getStackTrace().toString());
		}
		setTitle("Welcome: "+customerName);
		//initView();
	}

	private void initializeCustomer() {
		// TODO Auto-generated method stub
		Intent i = getIntent();
		membershipID = (String)i.getSerializableExtra("memberShipNumber");
		minReqRewardsPoint = (int)i.getIntExtra("minReqPoints", minReqRewardsPoint);
		
		JSONObject loginID = new JSONObject();
		try {
			loginID.put("LoginID", membershipID);
			AsyncTask result = new GetCustomerData().execute(loginID);
			try {
				String s = (String)result.get();
				try {
					  customer =  new JSONObject(s);
					  customerName =  customerName.concat(customer.getString("firstName"));
					  customerName =  customerName.concat(" "+customer.getString("lastName"));
					  rewardsPoint = customer.getInt("rewardsPoint");
					  phoneNumber = phoneNumber.concat(customer.getString("customerPhoneNumber"));
					  
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
			
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.customer_details, menu);
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
	
	
	public void initView() {
        stk = (TableLayout) findViewById(R.id.CustomerDetailsTable1);
        
        TableRow tbrow0 = new TableRow(this);
       
        TextView tv0_tbrow0 = new TextView(this);
        tv0_tbrow0.setText(" Name: ");
        tbrow0.addView(tv0_tbrow0);
        
        TextView tv1_tbrow0 = new TextView(this);
        tv1_tbrow0.setText(""+customerName);
        tbrow0.addView(tv1_tbrow0);
        
        stk.addView(tbrow0);
        
       
        TableRow tbrow1 = new TableRow(this);
        
        TextView tv0_tbrow1 = new TextView(this);
        tv0_tbrow1.setText(" Rewards Point: ");
        tbrow1.addView(tv0_tbrow1);
        
        TextView tv1_tbrow1 = new TextView(this);
        tv1_tbrow1.setText(""+rewardsPoint);
        tbrow1.addView(tv1_tbrow1);
        
        stk.addView(tbrow1);
       
        TableRow tbrow2 = new TableRow(this);
        
        TextView tv0_tbrow2 = new TextView(this);
        tv0_tbrow2.setText(" Phone Number: ");
        tbrow2.addView(tv0_tbrow2);
        
        TextView tv1_tbrow2 = new TextView(this);
        tv1_tbrow2.setText(""+phoneNumber);
        tbrow2.addView(tv1_tbrow2);
        
        stk.addView(tbrow2);
        
        
        TableRow tbrow3 = new TableRow(this);
        
        TextView tv0_tbrow3 = new TextView(this);
        tv0_tbrow3.setText(" Order.No ");
        tbrow3.addView(tv0_tbrow3);
        TextView tv1_tbrow3 = new TextView(this);
        tv1_tbrow3.setText(" Price ");
        tbrow3.addView(tv1_tbrow3);
        TextView tv2_tbrow3 = new TextView(this);
        tv2_tbrow3.setText(" Is Completed? ");
        tbrow3.addView(tv2_tbrow3);
        TextView tv3_tbrow3 = new TextView(this);
        tv3_tbrow3.setText(" OrderType: ");
        tbrow3.addView(tv3_tbrow3);
        TextView tv4_tbrow4 = new TextView(this);
        tv4_tbrow4.setText(" Is Home Delivered? ");
        tbrow3.addView(tv4_tbrow4);
        
        stk.addView(tbrow3);
        
        if(rewardsPoint>=minReqRewardsPoint && minReqRewardsPoint!=0){
        	//get free pizza certificate
        	Button freePizzaButton = new Button(this);
        	freePizzaButton.setText("Get your Free Pizza.");
            TableRow tbrow4 = new TableRow(this);
            tbrow4.addView(freePizzaButton);
            stk.addView(tbrow4);
            freePizzaButton.setOnClickListener(freePizzaButton_Listener);
        }
        
        
        if(customer!=null){
        	
        	
        	try {
                JSONArray getArray = customer.getJSONArray("customerOrders");
            	for(int i=0;i<getArray.length();i++){
            		JSONObject row = getArray.getJSONObject(i);
            		int orderID = row.getInt("orderID");
                	double orderPrice = row.getDouble("totalPrice");
                	String isCompleted = (row.getBoolean("isComplete")?"Yes":"No");
                	String isDelivered = (row.getBoolean("isDelivered")?"Yes":"No");
                	String DeliveryType = row.getString("typeOfOrder");
                    
                	TableRow tbrow = new TableRow(this);
                    TextView t1v = new TextView(this);
                    t1v.setText("" + orderID);
                    t1v.setGravity(Gravity.CENTER);
                    tbrow.addView(t1v);
                    TextView t2v = new TextView(this);
                    t2v.setText("$" + orderPrice);
                    t2v.setGravity(Gravity.CENTER);
                    tbrow.addView(t2v);
                    TextView t3v = new TextView(this);
                    t3v.setText("" +isCompleted);
                    t3v.setGravity(Gravity.CENTER);
                    tbrow.addView(t3v);
                    TextView t4v = new TextView(this);
                    t4v.setText("" +DeliveryType);
                    t4v.setGravity(Gravity.CENTER);
                    tbrow.addView(t4v);
                    TextView t5v = new TextView(this);
                    t5v.setText("" +isDelivered);
                    t5v.setGravity(Gravity.CENTER);
                    tbrow.addView(t5v);
                    
                    tbrow.setClickable(true);
                    tbrow.setOnClickListener(tableRow_Listener);
                    stk.addView(tbrow);
            	}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

        }

    }
	
	private OnClickListener tableRow_Listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

	        TableRow tr1=(TableRow)v;
	        TextView tv1= (TextView)tr1.getChildAt(0);
	        String extractedOrderID = tv1.getText().toString();

			//Toast.makeText(context, "Clicked "+extractedOrderID, Toast.LENGTH_LONG).show();

			Intent intent = new Intent(context, SpecificOrderByCustomer.class);	
			intent.putExtra("memberShipNumber",membershipID);
			intent.putExtra("orderID",extractedOrderID);							
			startActivity(intent);
			
			
	        
		}
	};
	
	@SuppressLint("NewApi")
	private OnClickListener freePizzaButton_Listener = new OnClickListener() {
        public void onClick(View v) {
			JSONObject customerID_Point = new JSONObject();
			int updatedMinPoint = minReqRewardsPoint ;
			try {
				int point = (rewardsPoint - minReqRewardsPoint);
				customerID_Point.put("updatedPoint", point);
				customerID_Point.put("membershipID", membershipID);
				AsyncTask result = new UpdateCustomerPoints().execute(customerID_Point);
				JSONObject j = new JSONObject(((String)result.get())) ;
				updatedMinPoint = j.getInt("rewardsPoint");
				Log.d("App" ,j.getString("rewardsPoint"));
				minReqRewardsPoint = updatedMinPoint;
				
				
			} catch (Exception e) {
				// TODO: handle exception
				Log.d("App" ,e.getStackTrace().toString());
			} 
			
			Log.d("App" ,Integer.toString(updatedMinPoint));
			     	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
	        	alertDialogBuilder.setTitle("Free Pizza!");
				alertDialogBuilder.setTitle("");
				
				alertDialogBuilder.setMessage("Please show this to the pizza store!")
				.setNeutralButton("OK",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, just close
						// the dialog box and do nothing
						dialog.cancel();
						//clearText();
						
					}
				});
				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();
				recreate();
			
    }
	};
	
}
