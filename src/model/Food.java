package model;

import java.time.LocalDate;

public class Food extends Product {
    private LocalDate expirationDate;

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Food(double price, String name, LocalDate expirationDate) {
        super(price, name);
        this.expirationDate = expirationDate;
    }

    @Override
    public String getDescription() {
        return "Food: " + getName() + ". Price: " + getPrice() + ". Expiration: " + expirationDate;
    }

}
