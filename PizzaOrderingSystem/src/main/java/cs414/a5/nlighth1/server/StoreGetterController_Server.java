/**
 *
 */
package cs414.a5.nlighth1.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import cs414.a5.nlighth1.Store;

import java.io.IOException;
import java.io.OutputStream;

/**
 * @author SHAIKHSHAWON
 *
 */
public class StoreGetterController_Server implements HttpHandler {

    private Store store;

    public StoreGetterController_Server(Store s) {
        this.store = s;
    }

    @Override
    public void handle(HttpExchange arg0) throws IOException {
        if(this.store != null) {
            System.out.println("Request received. Store found:" + store.getStoreName());

            Gson gson = new GsonBuilder().create();
            String store = gson.toJson(this.store);

            arg0.sendResponseHeaders(200, store.length());
            OutputStream os = arg0.getResponseBody();
            os.write(store.getBytes());
            os.close();

        } else {
            System.out.println("Request received. But no Store can be found.");

        }

    }

}
