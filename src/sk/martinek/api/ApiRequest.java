package sk.martinek.api;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class ApiRequest {



    public Map getExchangeRates(Set<String> rates){




        return null;
    }




    public HttpURLConnection getConnection(URL url) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        return conn;
    }


    public void getJson() throws IOException {
        String url_str = "http://api.exchangeratesapi.io/v1/latest?access_key=50300a83f913b25452f3d815436498db&format=1";
        //String pair_conversion = "https://v6.exchangerate-api.com/v6/41795997b82e619cb9fb665f/pair/USD/EUR";

// Making Request
        URL url = new URL(url_str);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

// Convert to JSON
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject jsonobj = root.getAsJsonObject();

// Accessing object
        String req_result = jsonobj.get("rates").getAsString();
        String base_code = jsonobj.get("BTC").getAsString();


        JsonElement fak = jsonobj.get("rates");
        JsonObject fok = fak.getAsJsonObject();


        String converzie = fok.get("BTC").getAsString();
        System.out.println(converzie);
        //return Integer.parseInt(converzie) ;
    }

}
