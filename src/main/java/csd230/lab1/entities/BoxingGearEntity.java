package csd230.lab1.entities;

import jakarta.persistence.Entity;

@Entity
public abstract class BoxingGearEntity extends ProductEntity {
    private String size;

    public BoxingGearEntity() {
    }

    public BoxingGearEntity(String size) {
        this.size = size;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "BoxingGearEntity{size='" + size + "', " + super.toString() + "}";
    }
}