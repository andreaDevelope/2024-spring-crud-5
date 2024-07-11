package org.java.spring_test_e_commerce.db.service.ProductService;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.java.spring_test_e_commerce.db.pojo.Product;
import org.java.spring_test_e_commerce.db.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ProductService{
    @Autowired
    private ProductRepo pr;

    public List<Product> getAll(){
        return pr.findAll();
    }

    public Optional<Product> findProById(int id){
        return pr.findById(id);
    }

    public void save(Product p){
        pr.save(p);
    }

    public void delete(Product p){
        pr.delete(p);
    }

    @Transactional
    public Optional<Product> findProductByIdWithOrders(int id) {
        Optional<Product> optProduct = pr.findById(id);

        if (optProduct.isEmpty())
            return Optional.empty();

        Product product = optProduct.get();
        Hibernate.initialize(product.getOrders());

        return Optional.of(product);
    }

}
