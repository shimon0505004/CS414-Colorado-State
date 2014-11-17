package edu.colostate.cs414.android_app_client;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;

import cs414.pos.Customer;
import cs414.pos.Order;
import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class CustomerDetailsActivity extends ActionBarActivity {

	JSONObject  customer = null;
	String customerName = "";
	Context context;
	String membershipID = null;
	
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
			e.printStackTrace();
		}
		setTitle("Welcome: "+customerName);
		//initView();
	}

	private void initializeCustomer() {
		// TODO Auto-generated method stub
		Intent i = getIntent();
		membershipID = (String)i.getSerializableExtra("memberShipNumber");
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
        TableLayout stk = (TableLayout) findViewById(R.id.CustomerDetailsTable1);
        TableRow tbrow0 = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setText(" Order.No ");
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText(" Price ");
        tbrow0.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText(" Is Completed? ");
        tbrow0.addView(tv2);
        TextView tv3 = new TextView(this);
        tv3.setText(" OrderType: ");
        tbrow0.addView(tv3);
        TextView tv4 = new TextView(this);
        tv4.setText(" Is Home Delivered? ");
        tbrow0.addView(tv4);
        
        stk.addView(tbrow0);
        
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

			Toast.makeText(context, "Clicked "+extractedOrderID, Toast.LENGTH_LONG).show();

			Intent intent = new Intent(context, SpecificOrderByCustomer.class);	
			intent.putExtra("memberShipNumber",membershipID);
			intent.putExtra("orderID",extractedOrderID);							
			startActivity(intent);
			
			
	        
		}
	};
	
}
