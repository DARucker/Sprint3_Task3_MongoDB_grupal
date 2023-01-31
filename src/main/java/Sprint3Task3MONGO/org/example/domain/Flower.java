package Sprint3Task3MONGO.org.example.domain;

import org.bson.types.ObjectId;

public class Flower extends Product {

    private String color;

    public Flower(String name, double price, int quantity, String color) {
        super(name, price, quantity);
        this.color = color;
    }

    public Flower() {
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Flower{" +
                "color = " + color +
                ", " + super.toString();
    }
}
