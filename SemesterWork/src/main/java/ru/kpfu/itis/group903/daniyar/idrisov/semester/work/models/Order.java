package ru.kpfu.itis.group903.daniyar.idrisov.semester.work.models;

import java.util.Objects;

public class Order {

    private Long id;
    private Long customerId;
    private Long itemId;
    private String itemName;
    private Long price;

    public Order(Long id, Long customerId, Long itemId, String itemName, Long price) {
        this.id = id;
        this.customerId = customerId;
        this.itemId = itemId;
        this.itemName = itemName;
        this.price = price;
    }

    public Order(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemName() { return itemName; }

    public void setItemName(String itemName) { this.itemName = itemName; }

    public Long getPrice() { return price; }

    public void setPrice(Long price) { this.price = price; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(customerId, order.customerId) &&
                Objects.equals(itemId, order.itemId) &&
                Objects.equals(itemName, order.itemName) &&
                Objects.equals(price, order.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, itemId, itemName, price);
    }


    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", itemId=" + itemId +
                ", itemName='" + itemName + '\'' +
                ", price=" + price +
                '}';
    }

}
