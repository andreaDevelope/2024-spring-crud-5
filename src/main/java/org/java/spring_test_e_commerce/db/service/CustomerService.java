package org.java.spring_test_e_commerce.db.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.java.spring_test_e_commerce.db.pojo.Customer;
import org.java.spring_test_e_commerce.db.pojo.Orders;
import org.java.spring_test_e_commerce.db.repo.CustomerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepo cr;

    @Autowired
    OrderService os;

    public List<Customer> getAll(){
        return cr.findAll();
    }

    public Optional<Customer> findCustById(int id){
        return cr.findById(id);
    }

    public void save(Customer c){
        cr.save(c);
    }

    public void delete(Customer c){
        cr.delete(c);
    }

    // PER OTTENERE I POST ASSOCIATI A UN USER
    @Transactional
    public Optional<Customer> getCustomerWhitOrdersById(int id) {
    Optional<Customer> optCustomer = findCustById(id);
    
        if (optCustomer.isEmpty())
            return Optional.empty();
    
        Hibernate.initialize(optCustomer.get().getOrders());
    
        return optCustomer;
    }

    // PER ELIMINARE L'UTENTE E TUTTI I POST ASSOCIATI
    @Transactional
    public void deleteCustomerAndOrders(int id) {
    Optional<Customer> optCustomer = findCustById(id);
        if (optCustomer.isPresent()) {
            Customer customerWithId = optCustomer.get();
            // Elimina tutti i post associati all'utente
            for (Orders orders : customerWithId.getOrders()) {
                os.delete(orders);
            }
            // Elimina l'utente
            cr.delete(customerWithId);
        }
    }

}
