package edu.colostate.cs414.android_app_client;

import org.json.JSONObject;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MembershipLoginActivity extends ActionBarActivity {

	private Button cancelButton;
	private Button okButton;
	private Context context;
	
	private EditText membershipIDText;
	private String membershipID;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_membership_login);
		context = (Context)this;
		okButton = (Button)findViewById(R.id.okButton_Mmbr_Login);
		cancelButton = (Button)findViewById(R.id.cancelButton_Mmbr_login);
		membershipIDText  = (EditText)findViewById(R.id.editText1_MembershipLogin);
		okButton.setOnClickListener(okButton_Mmbr_Login_Listener);
		cancelButton.setOnClickListener(cancelButton_Mmbr_Login_Listener);
		membershipID = membershipIDText.getText().toString();
	}

private OnClickListener okButton_Mmbr_Login_Listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(v.getId()==R.id.okButton_Mmbr_Login){
					membershipID = membershipIDText.getText().toString();
					JSONObject loginID = new JSONObject();
					try {
						loginID.put("LoginID", membershipID);
						AsyncTask result = new GetCustomerData().execute(loginID);
						try {
							String s = (String)result.get();
							//Toast.makeText(getBaseContext(), s, Toast.LENGTH_LONG).show();
							AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
							
							if(!s.equals(null))
							{
								try {
									JSONObject  object =  new JSONObject(s);
									
									if(object.get("memberShipNumber") != null){
									
											// set title
										   
										
											alertDialogBuilder.setTitle("Customer Found!");
											alertDialogBuilder.setMessage("Welcome :"+object.get("firstName")+" " +object.get("lastName"))
											.setNeutralButton("OK",new DialogInterface.OnClickListener() {
												public void onClick(DialogInterface dialog,int id) {
													// if this button is clicked, just close
													// the dialog box and do nothing
													Intent intent = new Intent(context, CustomerDetailsActivity.class);	
													intent.putExtra("memberShipNumber",membershipID);
													startActivity(intent);
												}
											});
		
									}else{
										alertDialogBuilder.setTitle("Customer Not Found");
										alertDialogBuilder.setMessage("Unfortunately your customer account could not be Found. Please try again!")
										.setNeutralButton("OK",new DialogInterface.OnClickListener() {
											public void onClick(DialogInterface dialog,int id) {
												// if this button is clicked, just close
												// the dialog box and do nothing
												dialog.cancel();
												clearText();
											}
										});
									}
								} catch (Exception e) {
									// set title
									alertDialogBuilder.setTitle("Customer Not Found");
									alertDialogBuilder.setMessage("Unfortunately your customer account could not be Found. Please try again!")
									.setNeutralButton("OK",new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog,int id) {
											// if this button is clicked, just close
											// the dialog box and do nothing
											dialog.cancel();
											clearText();
										}
									});
								}
								
							}else{
								// set title
								alertDialogBuilder.setTitle("Customer Not Found");
								alertDialogBuilder.setMessage("Unfortunately your customer account could not be Found. Please try again!")
								.setNeutralButton("OK",new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,int id) {
										// if this button is clicked, just close
										// the dialog box and do nothing
										dialog.cancel();
										clearText();
									}
								});
							}
							// create alert dialog
							AlertDialog alertDialog = alertDialogBuilder.create();
			 
							// show it
							alertDialog.show();
						}
						catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
			}
	};
	
	private OnClickListener cancelButton_Mmbr_Login_Listener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(v.getId()==R.id.cancelButton_Mmbr_login){
				membershipID = membershipIDText.getText().toString();
				
				if(membershipID.equals("") ){
				{
						onBackPressed();
				}
				}else{
					//Toast.makeText(getBaseContext(), ","+firstName+","+lastName+","+phoneNumber+",", Toast.LENGTH_LONG).show();
					
					clearText();
					
				}
				
			}
		}


	};
		
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.membership_login, menu);
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

	private void clearText() {
		// TODO Auto-generated method stub
		membershipIDText.setText("");
	}
}
