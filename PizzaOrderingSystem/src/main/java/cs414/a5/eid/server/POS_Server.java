/**
 * 
 */
package cs414.a5.eid.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpServer;

import cs414.a5.eid.Store;

import java.io.IOException;
import java.net.InetSocketAddress;


/**
 * @author SHAIKHSHAWON
 *
 */
public class POS_Server {

    public static final Gson gson = new GsonBuilder().create();

    public POS_Server(Store s) {
        //creates the server on port 8,000
        HttpServer server = null;
        try {
            server = HttpServer.create(new InetSocketAddress(8000), 8000);
        } catch (IOException e) { e.printStackTrace(); }

        // android controllers
        CustomerController_Server customerController = new CustomerController_Server(s);
        SpecificCustomerGetterController_Server customerFinder = new SpecificCustomerGetterController_Server(s);
        StoreGetterController_Server storeServer = new StoreGetterController_Server(s);
        CustomerPointUpdate_Server customerPointServer = new CustomerPointUpdate_Server(s);
        PlaceOrderFromAndroidController androidPlaceOrderServer = new PlaceOrderFromAndroidController(s);
        // in-store controllers
        LoginServerController loginServerController = new LoginServerController(s);
        MenuGetterController menuGetterController = new MenuGetterController(s);
        PlaceOrderServerController placeOrderServerController = new PlaceOrderServerController(s);
        
        // set http contexts
        server.createContext("/customerAccounts", customerController);
        server.createContext("/SingleCustomerAccount", customerFinder);
        server.createContext("/store", storeServer);
        server.createContext("/customerPointUpdate", customerPointServer);
        server.createContext("/login", loginServerController);
        server.createContext("/getMenus", menuGetterController);
        server.createContext("/placeOrder", placeOrderServerController);
        server.createContext("/androidOrder", androidPlaceOrderServer);
        server.start();
        System.out.println("Server started");
    }

    /*
	//important main method is needed to run the server
	public static void main(String[] args) throws Exception{

		Store s = Main.initStore();

		//creates the server on port 8,000
		HttpServer server = HttpServer.create(new InetSocketAddress(8000), 8000);

        // android controllers
		CustomerController_Server customerController = new CustomerController_Server(s);
		SpecificCustomerGetterController_Server customerFinder = new SpecificCustomerGetterController_Server(s); 
		StoreGetterController_Server storeServer = new StoreGetterController_Server(s);
		CustomerPointUpdate_Server customerPointServer = new CustomerPointUpdate_Server(s);
        // in-store controllers
        LoginServerController loginServerController = new LoginServerController(s);

        // set http contexts
		server.createContext("/customerAccounts", customerController);
		server.createContext("/SingleCustomerAccount", customerFinder);
		server.createContext("/store", storeServer);
		server.createContext("/customerPointUpdate", customerPointServer);
        server.createContext("/login", loginServerController);

		server.start();
	}
    */
}
