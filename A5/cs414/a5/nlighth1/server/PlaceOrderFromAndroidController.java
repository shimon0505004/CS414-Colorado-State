package cs414.a5.nlighth1.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import cs414.a5.nlighth1.Customer;
import cs414.a5.nlighth1.Item;
import cs414.a5.nlighth1.Order;
import cs414.a5.nlighth1.Store;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class PlaceOrderFromAndroidController implements HttpHandler {

	private Store store;

	public PlaceOrderFromAndroidController(Store s) {
		this.store = s;
	}

	@Override
	public void handle(HttpExchange httpExchange) throws IOException {

		Headers reqHeaders = httpExchange.getRequestHeaders();
		Iterator<String> iterator = reqHeaders.keySet().iterator();
		System.out.println();
		while (iterator.hasNext()) {
			String key = iterator.next();
			List value = reqHeaders.get(key);

			System.out.printf("%s:", key);
			for (int i = 0; i < value.size(); i++) {
				System.out.printf(" %s", value.get(i));
			}
			System.out.print("\n");
		}

		InputStreamReader isr = new InputStreamReader(
				httpExchange.getRequestBody(), "utf-8");
		BufferedReader streamReader = new BufferedReader(isr);
		StringBuilder responseStrBuilder = new StringBuilder();

		String inputStr;
		while ((inputStr = streamReader.readLine()) != null) {
			responseStrBuilder.append(inputStr);
		}

		Customer c = null;
		Order o = null;

		System.out.println("Request received by Place order sever");

		JSONObject object = new JSONObject(responseStrBuilder.toString());
		String customerID = object.get("memberID").toString();

		o = new Order(this.store.getListOfPlacedOrder().size());
		o.setOrderPlacedByApp();

		JSONArray orderList = object.getJSONArray("orderList");

		String orderListStr = orderList.toString();
		String trimorderListStr = orderListStr.substring(1,
				orderListStr.length() - 1);

		for (int i = 0; i < orderList.length(); i++) {
			String itemStr = trimorderListStr.split(",")[i];
			String trimItemStr = itemStr.substring(1, itemStr.length() - 1);

			String itemName = trimItemStr.split("/")[0].toString();
			int itemCount = Integer.parseInt(trimItemStr.split("/")[1]
					.toString());
			Item item = this.store.getItem(itemName);
			o.addItemToOrderByAmount(item, itemCount);
		}

		c = this.store.getMember(customerID);
		o.updateMembershipHoldingCustomer(c, this.store);

		if (customerID.length() != 0 && c != null) {// with membership
			System.out.println("Find customer ID: " + customerID);
			if (o != null) {
				this.store.placeOrderViaApp(o);
				c.addOrder(o);

				System.out
						.println("Request received. Order is created with ID: "
								+ o.getOrderID() + ", with customer ID: "
								+ customerID);
				Gson gson = new GsonBuilder().create();
				String order = gson.toJson(o);
				httpExchange.sendResponseHeaders(200, order.length());
				OutputStream os = httpExchange.getResponseBody();
				os.write(order.getBytes());
				os.close();

			}
		} else if (customerID.length() != 0 && c == null) {// cannot find id
			System.out.println(customerID.length());
			System.out.println(customerID == " ");
			System.out.println("Cannot find customer ID: " + customerID);
			Gson gson = new GsonBuilder().create();
			String order = gson.toJson(o);
			httpExchange.sendResponseHeaders(200, order.length());
			OutputStream os = httpExchange.getResponseBody();
			os.write(order.getBytes());
			os.close();

		} else if (customerID.length() == 0) {// without membership

			if (o != null) {

				this.store.placeOrderViaApp(o);
				System.out
						.println("Request received. Order is created with ID: "
								+ o.getOrderID());
				Gson gson = new GsonBuilder().create();
				String order = gson.toJson(o);
				httpExchange.sendResponseHeaders(200, order.length());
				OutputStream os = httpExchange.getResponseBody();
				os.write(order.getBytes());
				os.close();

			}
		}
	}
}
