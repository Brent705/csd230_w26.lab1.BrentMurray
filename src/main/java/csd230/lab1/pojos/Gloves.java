package csd230.lab1.pojos;

import java.util.Objects;

public class Gloves extends BoxingGear {
    private String brand = "";
    private double price = 0.0;
    private int weightOz = 12;

    public Gloves() {
        super();
    }

    public Gloves(String size, String brand, double price, int weightOz) {
        super(size);
        this.brand = brand;
        this.price = price;
        this.weightOz = weightOz;
    }

    @Override
    public void initialize() {
        super.initSize();

        System.out.println("Enter Brand:");
        this.brand = getInput("Everlast");

        System.out.println("Enter Weight (oz) [8, 10, 12, 14, 16]:");
        this.weightOz = getInput(12);

        System.out.println("Enter Price:");
        this.price = getInput(0.0);
    }

    @Override
    public void edit() {
        super.edit();

        System.out.println("Edit Brand [" + this.brand + "]:");
        this.brand = getInput(this.brand);

        System.out.println("Edit Weight (oz) [" + this.weightOz + "]:");
        this.weightOz = getInput(this.weightOz);

        System.out.println("Edit Price [" + this.price + "]:");
        this.price = getInput(this.price);
    }

    @Override
    public void sellItem() {
        System.out.println("Selling Boxing Gloves: " + brand + " " + weightOz + "oz, Size " + getSize() + " for $" + price);
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

    public int getWeightOz() {
        return weightOz;
    }

    public void setWeightOz(int weightOz) {
        this.weightOz = weightOz;
    }

    @Override
    public String toString() {
        return "Gloves{brand='" + brand + "', price=" + price + ", weightOz=" + weightOz + ", " + super.toString() + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gloves)) return false;
        if (!super.equals(o)) return false;
        Gloves gloves = (Gloves) o;
        return Double.compare(gloves.price, price) == 0 &&
                weightOz == gloves.weightOz &&
                Objects.equals(brand, gloves.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), brand, price, weightOz);
    }
}