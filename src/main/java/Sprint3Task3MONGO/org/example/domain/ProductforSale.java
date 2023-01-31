package Sprint3Task3MONGO.org.example.domain;

public class ProductforSale {

    private FlowerShop_stock product;
    private int quantity;

    public ProductforSale(FlowerShop_stock product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public ProductforSale() {
    }

     public FlowerShop_stock getProduct() {
        return this.product;
    }
    public double getPrice(){
        if (this.product.getDecoration() != null) {
            return product.getDecoration().getPrice();
        } else if (this.product.getFlower() != null) {
            return product.getFlower().getPrice();
        } else{
            return product.getTree().getPrice();
        }

    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
