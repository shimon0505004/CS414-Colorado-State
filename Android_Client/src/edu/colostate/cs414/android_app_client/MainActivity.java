package edu.colostate.cs414.android_app_client;

import cs414.a5.eid.Order;
import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

	private Button createNewAccountButton;
	private Button loginButton;
	private Button viewMenuButton;
	private Context c;
	
	private OnClickListener createNewAccountButtonListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(v.getId()==R.id.CreateCustomerAccountButton){
				if(c!=null){
					Intent intent = new Intent(c, CreateNewAccountActivity.class);					
					startActivity(intent);
				}
			}
		}
	};
	
	private OnClickListener loginButtonListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(v.getId()==R.id.LoginWithMembershipIDButton){
				if(c!=null){
					Intent intent = new Intent(c, MembershipLoginActivity.class);					
					startActivity(intent);
				}				
			}
		}
	};

	private OnClickListener viewMenuButtonListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(v.getId()==R.id.ViewMenuAndPlaceOrderButton){
				if(c!=null){
					Intent intent = new Intent(c, OrderActivity.class);					
					startActivity(intent);
				}
			}
		}
	};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		c = (Context) this;
		createNewAccountButton = (Button)findViewById(R.id.CreateCustomerAccountButton);
		loginButton = (Button)findViewById(R.id.LoginWithMembershipIDButton);
		viewMenuButton = (Button)findViewById(R.id.ViewMenuAndPlaceOrderButton);
		
		createNewAccountButton.setOnClickListener(createNewAccountButtonListener);
		loginButton.setOnClickListener(loginButtonListener);
		viewMenuButton.setOnClickListener(viewMenuButtonListener);
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
}
