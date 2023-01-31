package Sprint3Task3MONGO.org.example.domain;

import org.bson.types.ObjectId;

public class FlowerShop_stock {
    private ObjectId id;
    private Flower flower;
    private  Tree tree;
    private Decoration decoration;

    public FlowerShop_stock(Flower flower) {
        this.flower = flower;
    }

    public FlowerShop_stock(Tree tree) {
        this.tree = tree;
    }

    public FlowerShop_stock(Decoration decoration) {
        this.decoration = decoration;
    }

    public FlowerShop_stock() {
        this.id = ObjectId.get();
    }

    public Flower getFlower() {
        return flower;
    }

    public void setFlower(Flower flower) {
        this.flower = flower;
    }

    public Tree getTree() {
        return tree;
    }

    public ObjectId getId() {
        return id;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }

    public Decoration getDecoration() {
        return decoration;
    }

    public void setDecoration(Decoration decoration) {
        this.decoration = decoration;
    }


    @Override
    public String toString() {
        if (this.flower != null){
            return "Flower ID: " + this.id + " " + this.flower.toString();
        } else if (this.tree != null){
            return  "Tree ID: " + this.id + " " + this.tree.toString();
        } else {
            return "Decoration ID: " + this.id + " " + this.decoration.toString();
        }
    }
}
