package cs414.a5.nlighth1.server;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import cs414.a5.nlighth1.Employee;
import cs414.a5.nlighth1.Store;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

/**
 * @author ct.
 */
public class LoginServerController implements HttpHandler {

    final Store store;

    public LoginServerController(Store s) {
        this.store = s;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        JSONObject loginJson = new JSONObject(new JSONTokener(
                new InputStreamReader(httpExchange.getRequestBody(), "utf-8")));

        // attempt login
        Employee employee = store.loginAttempt(loginJson.getString("loginId"), loginJson.getString("password"));

        OutputStream os = httpExchange.getResponseBody();
        if(employee != null) {
            String employeeJson = POS_Server.gson.toJson(employee);
            httpExchange.sendResponseHeaders(200, employeeJson.length());
            os.write(employeeJson.getBytes());
        } else {

        }
        os.close();
    }
}
