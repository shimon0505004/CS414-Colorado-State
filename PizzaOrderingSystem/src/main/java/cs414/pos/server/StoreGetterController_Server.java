/**
 * 
 */
package cs414.pos.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import cs414.pos.*;
import cs414.pos.ui.UIController;

/**
 * @author SHAIKHSHAWON
 *
 */
public class StoreGetterController_Server implements HttpHandler{

	private Store store;
	
	public StoreGetterController_Server(Store s){
		this.store = s;
	}
	@Override
	public void handle(HttpExchange arg0) throws IOException {
		// TODO Auto-generated method stub
		
		try {
			Store s = SaverLoader.load(SaverLoader.SAVE_FILE);
			this.store = s;
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Something is wrong in StoreGetterController_Server");
		}

        if(this.store!=null)
        {
    	    System.out.println("Request received. Store found:"+ store.getStoreName());
    	   
    	    Gson gson = new GsonBuilder().create();
    		String store = gson.toJson(this.store);
    		
    		arg0.sendResponseHeaders(200, store.length());
    	    OutputStream os = arg0.getResponseBody();
    	    os.write(store.getBytes());
    	    os.close();
    	    
        	
        }else{
    	    System.out.println("Request received. But no Store can be found.");
        	
        }
	
	    
	}	
	
}
