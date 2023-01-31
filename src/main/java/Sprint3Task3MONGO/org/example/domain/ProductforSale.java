package Sprint3Task3MONGO.org.example.domain;

public class ProductforSale {
    private int id;
    private Product product;
    private int quantity;

    public ProductforSale(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }
    public double getPrice(){
        return product.getPrice();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
