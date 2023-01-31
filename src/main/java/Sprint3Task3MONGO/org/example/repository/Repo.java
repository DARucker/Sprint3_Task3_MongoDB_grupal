package Sprint3Task3MONGO.org.example.repository;

import Sprint3Task3MONGO.org.example.domain.*;
import org.bson.types.ObjectId;

import java.io.IOException;
import java.util.List;

public interface Repo {
    boolean findbyId(ObjectId id, String type);

    boolean findbyName(String name, String type);

    List<FlowerShop_stock> getAllProducts();
    List<Ticket> getAllSells();
    FlowerShop_stock getByName(String name, String type);
    void createFlower(Flower flower);
    void createTree(Tree tree);
    void createDeco(Decoration decoration);
    void createTicket(Ticket ticket);
    void updateTree(Tree tree, int quantity);
    void updateFlower(Flower flower, int quantity);
    void updateDeco(Decoration decoration, int quantity);

}
