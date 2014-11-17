/**
 * 
 */
package cs414.pos.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import cs414.pos.Main;
import cs414.pos.SaverLoader;
import cs414.pos.Store;
import cs414.pos.ui.UIController;


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
		SpecificCustomerGetterController_Server customerFinder = new SpecificCustomerGetterController_Server(s); 
		server.createContext("/customerAccounts", customerController);
		server.createContext("/SingleCustomerAccount", customerFinder);
		
		server.start();
	}
	
	

}
