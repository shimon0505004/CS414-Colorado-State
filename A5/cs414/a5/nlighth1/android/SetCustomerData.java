/**
 * 
 */
package cs414.a5.nlighth1.android;

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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cs414.a5.nlighth1.*;
import android.os.AsyncTask;

/**
 * @author SHAIKHSHAWON
 *
 */
public class SetCustomerData extends AsyncTask<JSONObject,Void,String> {

	@Override
	protected String doInBackground(JSONObject... params) {
		// TODO Auto-generated method stub
		int count = params.length;
        // 1. create HttpClient
        HttpClient httpclient = new DefaultHttpClient();
        // 2. make POST request to the given URL        
        //http://localhost:8000/customerAccounts
        HttpPost httpPost = new HttpPost("http://10.0.2.2:8000/customerAccounts");
        InputStream inputStream = null;
        String store= "";
		for(int i = 0;i<count;i++){
			JSONObject temp = params[i];
			try {
				httpPost.setEntity(new StringEntity(temp.toString(4)));
	            httpPost.setHeader("Accept", "application/json");
	            httpPost.setHeader("Content-type", "application/json");
	            HttpResponse httpResponse = httpclient.execute(httpPost);
	            InputStream tempInputStream = httpResponse.getEntity().getContent();
	            if(tempInputStream != null){
	                
	                BufferedReader streamReader = new BufferedReader(new InputStreamReader(tempInputStream, "UTF-8")); 

	                /*
	                Gson gson = new Gson();
	                store = gson.fromJson(streamReader, Store.class);
	            	*/
	                String s = null;
	                while((s=streamReader.readLine())!=null){
		            	store += s;
	                }
	            }
	            
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return store;
	}




}
