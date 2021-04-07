package sk.martinek.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;



public class Currency {

    public static void main(String[] args) throws Exception {





    }

public static int apiCC() throws IOException {
    // Setting URL
    String url_str = "https://v6.exchangerate-api.com/v6/41795997b82e619cb9fb665f/latest/USD";

// Making Request
    URL url = new URL(url_str);
    HttpURLConnection request = (HttpURLConnection) url.openConnection();
    request.connect();

// Convert to JSON
    JsonParser jp = new JsonParser();
    JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
    JsonObject jsonobj = root.getAsJsonObject();

// Accessing object
    String req_result = jsonobj.get("result").getAsString();
    String base_code = jsonobj.get("base_code").getAsString();


    JsonElement fak = jsonobj.get("conversion_rates");
    JsonObject fok = fak.getAsJsonObject();


    String converzie = fok.get("USD").getAsString();
    System.out.println(converzie);
        return Integer.parseInt(converzie) ;
    }

}
