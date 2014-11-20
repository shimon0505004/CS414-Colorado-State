package edu.colostate.cs414.android_app_client;

import java.util.concurrent.ExecutionException;

import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;

public class OrderActivity extends ActionBarActivity {

	JSONObject store = null;
	Context context;
	TableLayout leftTableLayout , rightTableLayout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order);
		context = (Context)this;
		initializeStore();
		leftTableLayout = (TableLayout) findViewById(R.id.leftLayout1_Order);
		rightTableLayout = (TableLayout) findViewById(R.id.rightTableLayout1_Order);
		
		
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
	
	private void initializeStore(){
		AsyncTask<Void, Void, JSONObject> storeResult = new GetStore().execute();
		try {
			store = (JSONObject)storeResult.get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.d("Oncreate OrderActivity",e.getStackTrace().toString());
		} 
	}
}
