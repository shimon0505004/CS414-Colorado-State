package cs414.a5.eid.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import cs414.a5.eid.Customer;
import cs414.a5.eid.Store;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

public class CustomerPointUpdate_Server implements HttpHandler {
    private Store store;
    private Customer c;

    public CustomerPointUpdate_Server(Store s) {
        this.store = s;
        c = null;
    }

    @Override
    public void handle(HttpExchange arg0) throws IOException {
        // Retrieve request headers
        Headers reqHeaders = arg0.getRequestHeaders();
        Iterator<String> iterator = reqHeaders.keySet().iterator();
        System.out.println();
        while(iterator.hasNext()) {
            String key = iterator.next();
            List value = reqHeaders.get(key);

            System.out.printf("%s:", key);
            for(int i = 0; i < value.size(); i++) {
                System.out.printf(" %s", value.get(i));
            }
            System.out.print("\n");
        }

        InputStreamReader isr = new InputStreamReader(arg0.getRequestBody(), "utf-8");
        BufferedReader streamReader = new BufferedReader(isr);
        StringBuilder responseStrBuilder = new StringBuilder();

        String inputStr;
        while((inputStr = streamReader.readLine()) != null) {
            responseStrBuilder.append(inputStr);
        }

        System.out.println("Request received by Customer point updating server.");
        String membershipID = "";
        int point = 0;
        try {
            JSONObject object = new JSONObject(responseStrBuilder.toString());
            point = object.getInt("updatedPoint");
            membershipID = object.get("membershipID").toString();

            c = this.store.getMember(membershipID);
            c.setRewardsPoint(point);
            //c = new Customer(firstName, lastName, customerPhoneNumber, this.store);
        } catch(JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if(c != null) {
            System.out.println("Request received. Customer found with ID:" + c.getMemberShipNumber() + " and point" + store.getMember(membershipID).getRewardsPoint());
            Gson gson = new GsonBuilder().create();
            String customer = gson.toJson(c);
            arg0.sendResponseHeaders(200, customer.length());
            OutputStream os = arg0.getResponseBody();
            os.write(customer.getBytes());
            os.close();

        } else {
            System.out.println("Request received. But no customer point is updated");

        }
    }
}
