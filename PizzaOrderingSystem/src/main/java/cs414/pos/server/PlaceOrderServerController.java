package cs414.pos.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import cs414.pos.Customer;
import cs414.pos.Item;
import cs414.pos.Main;
import cs414.pos.Order;
import cs414.pos.SaverLoader;
import cs414.pos.Store;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author ct.
 */
public class PlaceOrderServerController implements HttpHandler {

    private Store store;

    public PlaceOrderServerController(Store s) {
        this.store = s;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
//        Order order = POS_Server.gson.fromJson(new BufferedReader(new InputStreamReader(
//                httpExchange.getRequestBody(), "utf-8")), Order.class);
//        System.out.println("Order recieved:"+order);
//        boolean success = store.placeOrder(order);
//
//        OutputStream os = httpExchange.getResponseBody();
//        String ret = POS_Server.gson.toJson(success);
//        httpExchange.sendResponseHeaders(200, ret.length());
//        os.write(ret.getBytes());
//        os.close();
    	try {
			Store s = SaverLoader.load(SaverLoader.SAVE_FILE);
			this.store = s;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Something is wrong in PlaceOrderServerController_Server");
		}
    
    	Headers reqHeaders = httpExchange.getRequestHeaders();
    	Iterator<String> iterator = reqHeaders.keySet().iterator();
    	System.out.println();
    	while (iterator.hasNext()){
    		String key = iterator.next();
    		List value = reqHeaders.get(key);
    		
    		System.out.printf("%s:", key);
    		for(int i = 0; i < value.size(); i++){
    			System.out.printf(" %s", value.get(i));
    		}
    		System.out.print("\n");
    	}
    	
    	InputStreamReader isr = new InputStreamReader(httpExchange.getRequestBody(),"utf-8");
    	BufferedReader streamReader = new BufferedReader(isr);
    	StringBuilder responseStrBuilder = new StringBuilder();
    	
    	String inputStr;
    	while((inputStr = streamReader.readLine()) != null)
    		responseStrBuilder.append(inputStr);
    	
    	Customer c = null;
    	Order o = null;
    	
    	System.out.println("Request received by Place order sever");
    	
    	JSONObject object = new JSONObject(responseStrBuilder.toString());
    	String customerID = object.get("memberID").toString();
    	
		c = this.store.getMember(customerID);

		o = new Order(this.store.getListOfPlacedOrder().size());
		o.setOrderPlacedByApp();
		System.out.println(customerID);

		JSONArray orderList = object.getJSONArray("orderList");
//		System.out.println(customerID);

//		System.out.println(orderList.toString());
		String orderListStr = orderList.toString();
		String trimorderListStr = orderListStr.substring(1, orderListStr.length()-1);
//		System.out.println(trimorderListStr.toString().split(",")[0]);
//		System.out.println(trimorderListStr.toString().split(",")[1]);
//		System.out.println(orderList.length());
		for(int i = 0; i < orderList.length(); i++){
			String itemStr = trimorderListStr.split(",")[i];
			String trimItemStr = itemStr.substring(1, itemStr.length()-1);
//			System.out.println(trimItemStr);

			String itemName = trimItemStr.split("/")[0].toString();
			int itemCount = Integer.parseInt(trimItemStr.split("/")[1].toString());
			System.out.println("itemName:" + itemName);

			Item item = this.store.getItem(itemName);

			System.out.println(o.getOrderID());
			
			System.out.println(item==null);
			o.addItemToOrderByAmount(item, itemCount);
			System.out.println("HEllo2");

		}
		
		this.store.placeOrderViaApp(o);
    }
}
