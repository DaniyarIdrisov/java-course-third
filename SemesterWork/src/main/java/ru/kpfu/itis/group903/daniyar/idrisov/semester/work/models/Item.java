package ru.kpfu.itis.group903.daniyar.idrisov.semester.work.models;

import java.util.Objects;

public class Item {

    private Long id;
    private String label;
    private String description;
    private String photoPath;
    private Long price;

    public Item(Long id, String label, String description, String photoPath, Long price) {
        this.id = id;
        this.label = label;
        this.description = description;
        this.photoPath = photoPath;
        this.price = price;
    }

    public Item() {

    }

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public Long getPrice() { return price; }

    public void setPrice(Long price) { this.price = price; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) &&
                Objects.equals(label, item.label) &&
                Objects.equals(description, item.description) &&
                Objects.equals(photoPath, item.photoPath) &&
                Objects.equals(price, item.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, label, description, photoPath, price);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", description='" + description + '\'' +
                ", photoPath='" + photoPath + '\'' +
                ", price=" + price +
                '}';
    }

}