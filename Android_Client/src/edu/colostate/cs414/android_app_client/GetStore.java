package edu.colostate.cs414.android_app_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;

public class GetStore extends  AsyncTask<Void, Void, JSONObject> {



	@Override
	protected JSONObject doInBackground(Void... params) {
		int count = params.length;
        // 1. create HttpClient
        HttpClient httpclient = new DefaultHttpClient();
        // 2. make POST request to the given URL        
        //http://localhost:8000/customerAccounts
        HttpPost httpPost = new HttpPost("http://10.0.2.2:8000/store");
        InputStream inputStream = null;
        String store= "";
	    HttpResponse httpResponse = null;
	    InputStream tempInputStream = null;
		try {
			httpResponse = httpclient.execute(httpPost);
			tempInputStream = httpResponse.getEntity().getContent();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    if(tempInputStream != null){
	                
	         BufferedReader streamReader=null;
			try {
				streamReader = new BufferedReader(new InputStreamReader(tempInputStream, "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

	                /*
	                Gson gson = new Gson();
	                store = gson.fromJson(streamReader, Store.class);
	            	*/
					if(streamReader!=null){
		                String s = null;
		                try {
							while((s=streamReader.readLine())!=null){
								store += s;
							}
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

	     }
	    JSONObject object=null;
		try {
			object = new JSONObject(store);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
	}
	

}
