import java.util.ArrayList;
import java.util.HashMap;

public class Inverntory {
    private ArrayList<Product> products = new ArrayList<>();
    private HashMap<String, Integer> stock = new HashMap<>();
    private ArrayList<Double> prices = new ArrayList<>();

    public HashMap<String, Integer> getStock() {
        return stock;
    }

    public void setStock(HashMap<String, Integer> stock) {
        this.stock = stock;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public ArrayList<Double> getPrices() {
        return prices;
    }

    public void setPrices(ArrayList<Double> prices) {
        this.prices = prices;
    }
}
