package net.mableton.mongodbatlas;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;

import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;

import org.bson.Document;
import java.util.Arrays;
import com.mongodb.Block;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main( String[] args ) {
        System.out.println( "Testing MongoDB Atlas Connection ..." );

        String mongodbHostname = System.getenv("MONGODB_HOSTNAME");
        String mongodbUsername = System.getenv("MONGODB_USERNAME");
        String mongodbPassword = System.getenv("MONGODB_PASSWORD");

        MongoClient mongoClient = MongoClients.create("mongodb+srv://" + mongodbUsername + ":" + mongodbPassword + "@" + mongodbHostname + "/test?retryWrites=true");

        MongoDatabase database = mongoClient.getDatabase("test");

        MongoCollection<Document> collection = database.getCollection("test");

        Document doc = new Document("name", "MongoDB")
                        .append("type", "database")
                        .append("count", 1)
                        .append("versions", Arrays.asList("v3.2", "v3.0", "v2.6"))
                        .append("info", new Document("x", 203).append("y", 102));
        System.out.println( "Inserting single document ..." );
        collection.insertOne(doc);

        List<Document> documents = new ArrayList<Document>();
        for (int i = 0; i < 100; i++) {
            documents.add(new Document("i", i));
        }
        System.out.println( "Inserting multiple documents ..." );
        collection.insertMany(documents);

        System.out.println( "Checking document count ..." );
        System.out.println(collection.countDocuments());

        System.out.println( "Finding first document in collection ..." );
        Document myDoc = collection.find().first();
        System.out.println(myDoc.toJson());

        System.out.println( "Exiting ..." );
        System.exit(0);
    }
}
