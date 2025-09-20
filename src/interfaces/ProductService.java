package interfaces;

import model.Product;

public interface ProductService {
    public void addProduct(Product product, int quantity);
    public void listProducts();
    public boolean productExists(String productName);
    public int getStock(String productName);
    public String[] getAvailableProducts();
    public double getProductPrice(String productName);
    public void reduceStock(String productName, int quantity);
    public void orderProductsByPrice();
    public void searchProductByName(String productName);

}
