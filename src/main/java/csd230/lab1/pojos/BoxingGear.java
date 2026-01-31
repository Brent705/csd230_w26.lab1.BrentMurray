package csd230.lab1.pojos;

import java.util.Objects;

public abstract class BoxingGear extends Product {
    private String size = "";

    public BoxingGear() {
        super();
    }

    public BoxingGear(String size) {
        this.size = size;
    }

    @Override
    public void initialize() {
        System.out.println("Enter Size (e.g., S/M/L/XL or oz):");
        this.size = getInput("M");
    }

    protected void initSize() {
        System.out.println("Enter Size:");
        this.size = getInput("M");
    }

    @Override
    public void edit() {
        System.out.println("Edit Size [" + this.size + "]:");
        this.size = getInput(this.size);
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "BoxingGear{size='" + size + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BoxingGear)) return false;
        BoxingGear that = (BoxingGear) o;
        return Objects.equals(size, that.size);
    }

    @Override
    public int hashCode() {
        return Objects.hash(size);
    }
}