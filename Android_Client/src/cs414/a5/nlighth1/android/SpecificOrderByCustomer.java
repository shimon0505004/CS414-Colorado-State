package cs414.a5.nlighth1.android;

import org.json.JSONArray;
import org.json.JSONObject;

import edu.colostate.cs414.android_app_client.R;
import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class SpecificOrderByCustomer extends ActionBarActivity {

	String membershipID;
	JSONObject customer;
	String customerName = "";
	String extractedOrderID;
	Context context;
	JSONObject order;
	double totalPrice = 0.0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_specific_order_by_customer);
		context = (Context)this;
		initializeOrder();
		setTitle("Order Number:"+extractedOrderID);
		initView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.specific_order_by_customer, menu);
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
	
	
	private void initializeOrder() {
		// TODO Auto-generated method stub
		Intent i = getIntent();
		membershipID = (String)i.getSerializableExtra("memberShipNumber");
		extractedOrderID = (String)i.getSerializableExtra("orderID");
		Toast.makeText(context, membershipID+" "+extractedOrderID, Toast.LENGTH_LONG).show();
		
		JSONObject loginID = new JSONObject();
		try {
			loginID.put("LoginID", membershipID);
			AsyncTask result = new GetCustomerData().execute(loginID);
			try {
				String s = (String)result.get();
				//Toast.makeText(context, s, Toast.LENGTH_LONG).show();
				
				try {
					  customer =  new JSONObject(s);
					  customerName =  customerName.concat(customer.getString("firstName"));
					  customerName =  customerName.concat(" "+customer.getString("lastName"));
					  
				        
				        if(customer!=null){
				        	try {
				                JSONArray getArray = customer.getJSONArray("customerOrders");
				            	for(int loopCounter=0;loopCounter<getArray.length();loopCounter++){
				            		JSONObject row = getArray.getJSONObject(loopCounter);
				            		String orderID = (String) row.get("orderID").toString();
				            		if(orderID.equals(extractedOrderID)){
				            			//Toast.makeText(context, orderID, Toast.LENGTH_LONG).show();
				            			order = row;
				            			totalPrice = order.getDouble("totalPrice");
				            		}
				            	}
				            }catch (Exception e) {
									// TODO: handle exception
				            	e.printStackTrace();
				            }
				        }
				        
				        
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
	
	
	public void initView() {
        TableLayout stk = (TableLayout) findViewById(R.id.OrderItem_TableLayout1);
        
        TableRow tbrow0 = new TableRow(this);
        TextView tv0 = new TextView(this);
        tv0.setText(" Item Name ");
        tbrow0.addView(tv0);
        TextView tv1 = new TextView(this);
        tv1.setText(" Quantity ");
        tbrow0.addView(tv1);
        TextView tv2 = new TextView(this);
        tv2.setText(" Item Quantity Price ");
        tbrow0.addView(tv2);
        
        stk.addView(tbrow0);
        
        if(order!=null){
        	
        	
        	try {
                JSONArray getArray = order.getJSONArray("setOfItems");
            	for(int i=0;i<getArray.length();i++){
            		JSONObject row = getArray.getJSONObject(i);
            		String itemName = row.getJSONObject("item").getString("itemName");
            		int quantity = row.getInt("quantity");
            		double itemPrice = row.getDouble("subTotal");
                    
                	TableRow tbrow = new TableRow(this);
                    TextView t1v = new TextView(this);
                    t1v.setText("" + itemName);
                    t1v.setGravity(Gravity.CENTER);
                    tbrow.addView(t1v);
                    TextView t2v = new TextView(this);
                    t2v.setText("" + quantity);
                    t2v.setGravity(Gravity.CENTER);
                    tbrow.addView(t2v);
                    TextView t3v = new TextView(this);
                    t3v.setText("$" +itemPrice);
                    t3v.setGravity(Gravity.CENTER);
                    tbrow.addView(t3v);
                    
                    stk.addView(tbrow);
            	}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

        }

        
        TableRow tbrow2 = new TableRow(this);
        TextView tv0_tbrow2 = new TextView(this);
        tv0_tbrow2.setText(" Item Name ");
        tbrow2.addView(tv0_tbrow2);
        TextView tv1_tbrow2 = new TextView(this);
        tv1_tbrow2.setText(" Quantity ");
        tbrow2.addView(tv1_tbrow2);
        TextView tv2_tbrow2 = new TextView(this);
        tv2_tbrow2.setText(" --------Total Price ");
        tbrow2.addView(tv2_tbrow2);
        
        stk.addView(tbrow2);
        
        TableRow tbrow3 = new TableRow(this);
        TextView tv0_tbrow3 = new TextView(this);
        tv0_tbrow3.setText(" ---- ---- ");
        tbrow3.addView(tv0_tbrow3);
        TextView tv1_tbrow3 = new TextView(this);
        tv1_tbrow3.setText(" -------- ");
        tbrow3.addView(tv1_tbrow3);
        TextView tv2_tbrow3 = new TextView(this);
        tv2_tbrow3.setText("               $"+totalPrice);
        tbrow3.addView(tv2_tbrow3);
        
        stk.addView(tbrow3);
        
    }
	
	
	
}
