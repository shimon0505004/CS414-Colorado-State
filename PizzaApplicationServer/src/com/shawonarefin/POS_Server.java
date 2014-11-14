/**
 * 
 */
package com.shawonarefin;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.ArrayList;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import cs414.pos.Main;
import cs414.pos.Store;


/**
 * @author SHAIKHSHAWON
 *
 */
public class POS_Server {

	
	//important main method is needed to run the server
	public static void main(String[] args) throws Exception{
		Store s = Main.initStore();
		//creates the server on port 8,000
		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 8000);
		CustomerController_Server customerController = new CustomerController_Server(s);
		
		server.createContext("/customerAccounts", customerController);
		server.start();
	}

}
