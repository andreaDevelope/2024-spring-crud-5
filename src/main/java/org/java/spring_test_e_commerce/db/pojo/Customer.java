package org.java.spring_test_e_commerce.db.pojo;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 64, nullable = false)
    private String name;

    @Column(length = 64, nullable = false)
    private String lastname;

    @Column(length = 64, nullable = false)
    private String e_mail;

    @Column(length = 20, nullable = false)
    private String phone;

    @OneToMany(mappedBy = "customer")
    private List<Orders> orders;

    public Customer(){

    }

    public Customer(String name, String lastname, String e_mail, String phone){
        setId(id);
        setName(name);
        setLastname(lastname);
        setE_mail(e_mail);
        setPhone(phone);
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getE_mail() {
        return e_mail;
    }

    public void setE_mail(String e_mail) {
        this.e_mail = e_mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "CUSTOMER ID [" + getId() + "]" + " | name: " + getName() + " | lastname: " + getLastname() + " | e-mail: " + getE_mail() + " | phone: " + getPhone();
    }

    

}
