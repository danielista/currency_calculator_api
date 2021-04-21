package sk.martinek.api;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;



public class AllOtherCurrencies {

    public static void main(String[] args) throws Exception {


    }


    // throws a RATE only to USD currency
    public double apiCC(String from) throws IOException {
            // Setting URL

            String url_str = "https://v6.exchangerate-api.com/v6/41795997b82e619cb9fb665f/latest/"+from;


        // Making Request
            URL url = new URL(url_str);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

        // Convert to JSON
            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject jsonobj = root.getAsJsonObject();

            JsonElement fak = jsonobj.get("conversion_rates");
            JsonObject fok = fak.getAsJsonObject();


            String converzie = fok.get("USD").getAsString();
            System.out.println(converzie);

            double kurz = Double.parseDouble(converzie);
            System.out.println(kurz);

            return kurz;
    }













    // multi curruency convertor
    //////////////////////////////////////////////////

    public double convertorApi(String from, String to) throws IOException {
        String pair_conversion = "https://v6.exchangerate-api.com/v6/41795997b82e619cb9fb665f/pair/"+from+"/"+to;

        // Making Request
        URL url = new URL(pair_conversion);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

        // Convert to JSON
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject jsonobj = root.getAsJsonObject();

        String kurza = jsonobj.get("conversion_rate").getAsString();
        double kurz = Double.parseDouble(kurza);

        System.out.println(kurz);
        return kurz;
    }


}
