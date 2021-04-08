package sk.martinek.api;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import java.net.HttpURLConnection;
import java.util.Set;


public class MediumApi {




    private String getJsonFromAPI() {
        String inline = "";
        try {

            URL url = new URL("http://api.exchangeratesapi.io/v1/latest?access_key=50300a83f913b25452f3d815436498db&format=1");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Getting the response code
            int responsecode = conn.getResponseCode();

            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {
                Scanner scanner = new Scanner(url.openStream());

                //Write all the JSON data into a string using a scanner
                while (scanner.hasNext()) {
                    inline += scanner.nextLine();
                }
                scanner.close();

                System.out.println(inline);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return inline;
    }

    private Map parseData(Set<String> rates){
        String inline=getJsonFromAPI();
        if(inline==null)
            return null;

        try {
            //Using the JSON simple library parse the string into a json object
            JSONParser parse = new JSONParser();
            JSONObject data_obj = (JSONObject) parse.parse(inline);

            //Get the required object from the above created object
            JSONObject obj = (JSONObject) data_obj.get("rates");

            Map<String,Double> maps = new HashMap<>();
            for(String temp:rates){
                if(obj.containsKey(temp)){
                    double value= (double)obj.get(temp);
                    maps.put(temp,value);
                }
            }
            return maps;

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    public Map getExchangeRates(Set<String> rates){
        if(rates==null || rates.size() ==0)
            return null;

        return parseData(rates);
    }

}
