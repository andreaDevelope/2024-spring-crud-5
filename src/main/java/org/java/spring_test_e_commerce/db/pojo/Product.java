package org.java.spring_test_e_commerce.db.pojo;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 64, nullable = false)
    private String name;

    @Column(nullable = false )
    private int price;

    @Column(nullable = false)
    private int vat;

    @ManyToMany(mappedBy = "products")
    private List<Orders> orders;

    public Product(){

    }

    public Product(String name, int price, int vat){
        setId(id);
        setName(name);
        setPrice(price);
        setVat(vat);
        setOrders(orders);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getVat() {
        return vat;
    }

    public void setVat(int vat) {
        this.vat = vat;
    }

    public int getFullPrice() {
        boolean includeVat = true;
        if (includeVat) {
            return price + vat;
        } else {
            return price;
        }
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public void addOrder(Orders order) {
        orders.add(order);
    }

    @Override
    public String toString() {
        return "PRODUCT ID [" + getId() + "]" + " | name: " + getName() + " | price: " + getPrice() + " | vat: " + getVat();
    }
    
}
