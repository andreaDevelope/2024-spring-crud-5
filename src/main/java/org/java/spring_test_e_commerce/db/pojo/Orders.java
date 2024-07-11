package org.java.spring_test_e_commerce.db.pojo;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Customer customer;

    @ManyToMany
    private List<Product> products;

    public Orders(){
        
    }

    public Orders(Customer customers){
        setId(id);
        setCustomer(customers);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    };

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products = products.stream().filter(t -> t.getId() != product.getId()).toList();
    }

    public int getFullPrice() {
        int totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.getFullPrice();
        }
        System.out.println(totalPrice);
        return totalPrice;
    }

    @Override
    public String toString() {
        return "ORDER ID [" + getId() + "]" + " | CUSTOMER associato: " + getCustomer() + " | fullprice of the products: ";
    }
}
