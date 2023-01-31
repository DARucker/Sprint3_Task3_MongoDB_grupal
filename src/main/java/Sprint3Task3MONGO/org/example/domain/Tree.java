package Sprint3Task3MONGO.org.example.domain;

import org.bson.types.ObjectId;

public class Tree extends Product{
    double high;

    public Tree(String name, double price, int quantity, double high) {
        super(name, price, quantity);
        this.high = high;
    }

    public Tree() {
    }

    public double getHigh() {
        return high;
    }

    public void setHigh(double high) {
        this.high = high;
    }

    @Override
    public String toString() {
        return "Tree{" +
                "high = " + high +
                ", " + super.toString();
    }
}
