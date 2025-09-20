package model;

import javax.swing.*;

public class Appliance extends Product{

    public Appliance(double price, String name) {
        super(price, name);
    }

    @Override
    public String getDescription() {
        return "Appliance: " + getName() + ". Price: " + getPrice();
    }


}
