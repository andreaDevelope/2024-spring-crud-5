package org.java.spring_test_e_commerce.db.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.java.spring_test_e_commerce.db.pojo.Orders;
import org.java.spring_test_e_commerce.db.repo.OrdersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class OrderService {
    @Autowired
    private OrdersRepo or;

    public List<Orders> getAll(){
        return or.findAll();
    }

    public Optional<Orders> findOrdById(int id){
        return or.findById(id);
    }

    public void save(Orders o){
        or.save(o);
    }

    public void delete(Orders o){
        or.delete(o);
    }

    @Transactional
    public Optional<Orders> getOrderByIdWithProducts(int id) {

        Optional<Orders> optOrder = findOrdById(id);

        if (optOrder.isEmpty())
            return Optional.empty();

        Orders order = optOrder.get();
        Hibernate.initialize(order.getProducts());

        return Optional.of(order);
        
    }


}
