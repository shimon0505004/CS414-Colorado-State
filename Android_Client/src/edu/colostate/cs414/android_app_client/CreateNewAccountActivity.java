/**
 * 
 */
package edu.colostate.cs414.android_app_client;

import org.json.JSONObject;

import cs414.a5.eid.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * @author SHAIKHSHAWON
 *
 */
public class CreateNewAccountActivity extends Activity {

	private EditText firstNameText;
	private EditText lastNameText;
	private EditText phoneNumberText;
	private Button okButton;
	private Button cancelButton;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	final Context context = this;
	
	private OnClickListener okButtonListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(v.getId()==R.id.okButton_AccountCreate){
				firstName = firstNameText.getText().toString();
				lastName = lastNameText.getText().toString();
				phoneNumber = phoneNumberText.getText().toString();
				boolean phoneNumberInvalid = checkPhoneNumberValidity();
				boolean nameInvalid = checkName();
				
				if(!nameInvalid && !phoneNumberInvalid){
					/*
					 * now it is safe to store customer data
					 * replace this statement with Java RMI handling
					 */
					//Toast.makeText(getBaseContext(), firstName+" "+lastName+" "+phoneNumber, Toast.LENGTH_LONG).show();
					JSONObject customerDetails = new JSONObject();
					try {
						customerDetails.put("firstName", firstName);
						customerDetails.put("lastName", lastName);
						customerDetails.put("customerPhoneNumber", phoneNumber);
						AsyncTask result = new SetCustomerData().execute(customerDetails);
						try {
							String s = (String)result.get();
							//Toast.makeText(getBaseContext(), s, Toast.LENGTH_LONG).show();
							JSONObject  object =  new JSONObject(s);
							AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
							 
							if(object.get("memberShipNumber") != null){
							
									// set title
									alertDialogBuilder.setTitle("Customer Created!");
									alertDialogBuilder.setMessage("Your Customer ID is :"+object.get("memberShipNumber"))
									.setNeutralButton("OK",new DialogInterface.OnClickListener() {
										public void onClick(DialogInterface dialog,int id) {
											// if this button is clicked, just close
											// the dialog box and do nothing
											dialog.cancel();
											clearText();
										}
									});

							}else{
								 
								// set title
								alertDialogBuilder.setTitle("Customer Not Created!");
								alertDialogBuilder.setMessage("Unfortunately your customer account could not be created. Please try again!")
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
							//Make sure you write code to deal with exceptions
							System.out.println(e.toString());
						}

					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}

				}
				

			
			}
		}


	};
	

	private OnClickListener cancelButtonListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(v.getId()==R.id.cancelButton_AccntCreate){
				firstName = firstNameText.getText().toString();
				lastName = lastNameText.getText().toString();
				phoneNumber = phoneNumberText.getText().toString();
				
				if(firstName.equals("") && lastName.equals("")  && phoneNumber.equals("")){
				{
						onBackPressed();
				}
				}else{
					Toast.makeText(getBaseContext(), ","+firstName+","+lastName+","+phoneNumber+",", Toast.LENGTH_LONG).show();
					
					clearText();
					
				}
				
			}
		}
	};
	
	private void clearText(){
		firstNameText.setText("");
		lastNameText.setText("");
		phoneNumberText.setText("");
	}
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_accountcreate);
	    // TODO Auto-generated method stub
	    firstNameText = (EditText)findViewById(R.id.editText1);
	    lastNameText = (EditText)findViewById(R.id.editText2);
	    phoneNumberText = (EditText)findViewById(R.id.editText3);
	    okButton = (Button)findViewById(R.id.okButton_AccountCreate);
	    cancelButton = (Button)findViewById(R.id.cancelButton_AccntCreate);	
	    okButton.setOnClickListener(okButtonListener);
	    cancelButton.setOnClickListener(cancelButtonListener);
		firstName = firstNameText.getText().toString();
		lastName = lastNameText.getText().toString();
		phoneNumber = phoneNumberText.getText().toString();

	}

	private boolean checkPhoneNumberPatternHasOnlyDigits(String number){
		for(int i=0;i<number.length();i++){
			char c = number.charAt(i);
			if(!Character.isDigit(c)){
				return false;
			}
		}
		return true;
	}
	
	private boolean checkPhoneNumberPatternHas10digits(String number){
		if(number.length()==10){
			return true;
		}else{
			return false;
		}
	}
	
	
	private boolean checkPhoneNumberValidity(){
		if(checkPhoneNumberPatternHasOnlyDigits(phoneNumber)){
			if(checkPhoneNumberPatternHas10digits(phoneNumber)){
			//validity check, number correct. 
				//Toast.makeText(getBaseContext(), firstName+" "+lastName+" "+phoneNumber, Toast.LENGTH_LONG).show();
				return false;
			}else{
				Toast.makeText(getBaseContext(), "Telephone number does not have 10 digits.", Toast.LENGTH_LONG).show();						
				return true;
			}
		}else{
			Toast.makeText(getBaseContext(), "Telephone number format incorrect, number should only contain digits", Toast.LENGTH_LONG).show();											
			return true;
		}

	}
	
	private boolean checkName() {
		// TODO Auto-generated method stub
		if(firstName.equals(null) || firstName.equals("")){
			return true;
		}
		if(lastName.equals(null) || lastName.equals("")){
			return true;
		}
		return false;
	}
}
