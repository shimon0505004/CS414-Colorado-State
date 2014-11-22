package cs414.pos.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import cs414.pos.Store;

import java.io.IOException;

/**
 * @author ct.
 */
public class MenuGetterController implements HttpHandler {

    final Store store;
    public MenuGetterController(Store s) { this.store = s; }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String menusJson = POS_Server.gson.toJson(store.getSetOfMenus());
        httpExchange.sendResponseHeaders(200, menusJson.length());
        httpExchange.getResponseBody().write(menusJson.getBytes());
        System.out.println("Menu retrieved.");
        httpExchange.getResponseBody().close();
    }
}
