package Sprint3Task3MONGO.org.example.repository;


import Sprint3Task3MONGO.org.example.domain.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Repository implements Repo{

    //region ATRIBUTES
    CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
    CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
    String uri = "mongodb+srv://PauCala:P4uC4l4br3s@cluster0.hsyllwj.mongodb.net/?retryWrites=true&w=majority";
    MongoClient mongoClient = MongoClients.create(uri);
    MongoDatabase database = mongoClient.getDatabase("FlowerShop").withCodecRegistry(pojoCodecRegistry);

    MongoCollection<FlowerShop_stock> stock_collection = database.getCollection("FlowerShop_stock", FlowerShop_stock.class);
    MongoCollection<Ticket> ticket_collection = database.getCollection("FlowerShop_tickets", Ticket.class);


    //endregion ATRIBUTE

    //region BUSQUEDAS
    //metodes per comprobar si un producte o ticket existeix (en el cas de name només per productes)
    @Override
    public boolean findbyId(ObjectId id, String type){
        boolean found = false;
        if (type.equals("Stock")){
           if(stock_collection.find(eq("FlowerShop_stock_id", id)) != null){
               found = true;
           }
        } else {
            if(ticket_collection.find(eq("FlowerShop_tickets_id", id)) != null){
                found = true;
            }
        }
        return found;
    }
    @Override
    public boolean findbyName(String name, String type){
        boolean found = false;
        if (type.equals("Flower")){
            if(stock_collection.find(eq("flower.name", name)) != null){
                found = true;
            }
        } else if (type.equals("Tree")){
            if(stock_collection.find(eq("tree.name", name)) != null){
                found = true;
            }
        } else {
            if(stock_collection.find(eq("decoration.name", name)) != null){
                found = true;
            }
        }
        return found;

    }
    // endregion BUSQUEDAS

    //region GET
    // metodes per obtindre un o més productes o ventes
    //rep una llista de Productes a l'stock de la botiga
    @Override
    public List<FlowerShop_stock> getAllProducts() {
        List<FlowerShop_stock> products = new ArrayList<>();
        stock_collection.find().into(products);
        return products;
    }

    @Override
    public List<Ticket> getAllSells() {
        List<Ticket> tickets = new ArrayList<>();
        ticket_collection.find().into(tickets);
        return tickets;
}

    @Override
    public FlowerShop_stock getByName(String name, String type) {
        FlowerShop_stock product;
        if (type.equals("Flower")){
            product = stock_collection.find(eq("flower.name", name)).first();
        } else if (type.equals("Tree")){
            product = (FlowerShop_stock) stock_collection.find(eq("tree.name", name));
        } else {
            product = (FlowerShop_stock) stock_collection.find(eq("decoration.name", name));
        }
        return product;
    }
    //endregion GET

    //region CREATE
    //aqueste metodes creen noves entrades a la base de dades convertint l'objecte que reben en un objecte FlowerShop_stock
    // d'aquesta manera tenim tot l'stock en una sola col·lecció
    @Override
    public void createFlower(Flower flower) {
        FlowerShop_stock product = new FlowerShop_stock(flower);
        stock_collection.insertOne(product);
    }
    @Override
    public void createTree(Tree tree) {
        FlowerShop_stock product = new FlowerShop_stock(tree);
        stock_collection.insertOne(product);
    }
    @Override
    public void createDeco(Decoration decoration){
        FlowerShop_stock product = new FlowerShop_stock(decoration);
        stock_collection.insertOne(product);

    }
    @Override
    public void createTicket(Ticket ticket) {
        ticket_collection.insertOne(ticket);
    }
    //endregion CREATE

    //region UPDATE
    @Override
    public void updateTree(Tree tree, int quantity){
        stock_collection.updateOne(Filters.eq("tree.name", tree.getName()), Updates.set("tree.quantity", quantity));
    }

    @Override
    public void updateFlower(Flower flower, int quantity) {
        stock_collection.updateOne(Filters.eq("flower.name", flower.getName()), Updates.set("flower.quantity", quantity));
    }

    @Override
    public void updateDeco(Decoration decoration, int quantity) {
        stock_collection.updateOne(Filters.eq("decoration.name", decoration.getName()), Updates.set("decoration.quantity", quantity));
    }
    //endregion UPDATE

    //region METODOS AUXILIARES
    /*public int assignId(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        int id = 0;
        reader.readLine();
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(";");
            id = Integer.valueOf(parts[0]);
        }
        id++;
        return id;
    }*/

    //endregion METODOS AUXILIARES
}
