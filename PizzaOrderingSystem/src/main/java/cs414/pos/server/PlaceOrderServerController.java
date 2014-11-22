package cs414.pos.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import cs414.pos.Order;
import cs414.pos.Store;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * @author ct.
 */
public class PlaceOrderServerController implements HttpHandler {

    final Store store;

    public PlaceOrderServerController(Store s) {
        this.store = s;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        Order order = POS_Server.gson.fromJson(new BufferedReader(new InputStreamReader(
                httpExchange.getRequestBody(), "utf-8")), Order.class);
        System.out.println("Order recieved:"+order);
        boolean success = store.placeOrder(order);

        OutputStream os = httpExchange.getResponseBody();
        String ret = POS_Server.gson.toJson(success);
        httpExchange.sendResponseHeaders(200, ret.length());
        os.write(ret.getBytes());
        os.close();
    }
}
