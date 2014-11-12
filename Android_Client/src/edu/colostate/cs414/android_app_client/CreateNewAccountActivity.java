/**
 * 
 */
package edu.colostate.cs414.android_app_client;

import android.app.Activity;
import android.content.Intent;
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
	
	private OnClickListener okButtonListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(v.getId()==R.id.button1){
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
					Toast.makeText(getBaseContext(), firstName+" "+lastName+" "+phoneNumber, Toast.LENGTH_LONG).show();
					
				}
				

			
			}
		}


	};
	

	private OnClickListener cancelButtonListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(v.getId()==R.id.button2){
				firstName = firstNameText.getText().toString();
				lastName = lastNameText.getText().toString();
				phoneNumber = phoneNumberText.getText().toString();
				
				if(firstName.equals("") && lastName.equals("")  && phoneNumber.equals("")){
				{
						onBackPressed();
				}
				}else{
					Toast.makeText(getBaseContext(), ","+firstName+","+lastName+","+phoneNumber+",", Toast.LENGTH_LONG).show();
					
					firstNameText.setText("");
					lastNameText.setText("");
					phoneNumberText.setText("");
					
				}
				
			}
		}
	};
	
	
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_accountcreate);
	    // TODO Auto-generated method stub
	    firstNameText = (EditText)findViewById(R.id.editText1);
	    lastNameText = (EditText)findViewById(R.id.editText2);
	    phoneNumberText = (EditText)findViewById(R.id.editText3);
	    okButton = (Button)findViewById(R.id.button1);
	    cancelButton = (Button)findViewById(R.id.button2);	
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
