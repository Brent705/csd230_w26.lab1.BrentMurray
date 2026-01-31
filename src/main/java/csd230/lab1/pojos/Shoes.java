package csd230.lab1.pojos;

import java.util.Objects;

public class Shoes extends BoxingGear {
    private String brand = "";
    private double price = 0.0;
    private boolean highTop = false;

    public Shoes() {
        super();
    }

    public Shoes(String size, String brand, double price, boolean highTop) {
        super(size);
        this.brand = brand;
        this.price = price;
        this.highTop = highTop;
    }

    @Override
    public void initialize() {
        super.initSize();

        System.out.println("Enter Brand:");
        this.brand = getInput("Adidas");

        System.out.println("High-Top (ankle support)? (true/false):");
        this.highTop = getInput(false);

        System.out.println("Enter Price:");
        this.price = getInput(0.0);
    }

    @Override
    public void edit() {
        super.edit();

        System.out.println("Edit Brand [" + this.brand + "]:");
        this.brand = getInput(this.brand);

        System.out.println("Edit High-Top [" + this.highTop + "]:");
        this.highTop = getInput(this.highTop);

        System.out.println("Edit Price [" + this.price + "]:");
        this.price = getInput(this.price);
    }

    @Override
    public void sellItem() {
        String type = highTop ? "High-Top" : "Low-Top";
        System.out.println("Selling Boxing Shoes: " + brand + " " + type + ", Size " + getSize() + " for $" + price);
    }

    @Override
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public boolean isHighTop() {
        return highTop;
    }

    public void setHighTop(boolean highTop) {
        this.highTop = highTop;
    }

    @Override
    public String toString() {
        return "Shoes{brand='" + brand + "', price=" + price + ", highTop=" + highTop + ", " + super.toString() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Shoes)) return false;
        if (!super.equals(o)) return false;
        Shoes shoes = (Shoes) o;
        return Double.compare(shoes.price, price) == 0 &&
                highTop == shoes.highTop &&
                Objects.equals(brand, shoes.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), brand, price, highTop);
    }
}