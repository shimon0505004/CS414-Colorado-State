package com.shawonarefin;

import java.io.IOException;
import java.io.OutputStream;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import cs414.pos.*;

public class CustomerController_Server implements HttpHandler{

	private Store store;
	
	public CustomerController_Server(Store s){
		this.store = s;
	}
	@Override
	public void handle(HttpExchange arg0) throws IOException {
		// TODO Auto-generated method stub
		
		Gson gson = new GsonBuilder().create();
		String store_1 = gson.toJson(store);
		
	    arg0.sendResponseHeaders(200, store_1.length());
	    OutputStream os = arg0.getResponseBody();
	    os.write(store_1.getBytes());
	    os.close();
	    System.out.println("Request received.");
	
	    
	}

}
