package sk.martinek.database;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import sk.martinek.gui.Main;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Database {


    // pripojenie na mongodb na mojom localhoste :D a vybrať db na použitie popripade novu vytvoriť
    private static final MongoClient mongoClient = new MongoClient();
    private static final MongoDatabase database = mongoClient.getDatabase("testovanie");;
   // MongoClient mongo = new MongoClient("localhost", 27017);
   // DB db = mongo.getDB("testovanie");


    private static MongoCollection<Document> test;
    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public void testMongo(String from, String to, Double result){
        Main mn = new Main();

        //database.createCollection("test");
        //database.createCollection("test", null);
        System.out.println(from);
        System.out.println(to);
        System.out.println(result);
        System.out.println("ide? :D");

        //Preparing a document
        Document document = new Document();
        document.append("from", from);
        document.append("to", to);
        document.append("rate", result);
        document.append("time of conversion", format.format(new Date()));
        //Inserting the document into the collection
        database.getCollection("test").insertOne(document);
        System.out.println("Document inserted successfully");
    }


/*
    // pridanie kristianovej funkcie
    public <T> void add(String from, String to, double value, T result){
        test = database.getCollection("currency");
        JSONObject object = new JSONObject();
        object.put("datetime", format.format(new Date()));
        object.put("value", value);
        object.put("from", from);
        object.put("to", to);
        object.put("result", result);
        System.out.println(object);
        Document doc = Document.parse(object.toJSONString());
        test.insertOne(doc);
    }

 */
}
