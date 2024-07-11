package org.java.spring_test_e_commerce.db.repo;

import org.java.spring_test_e_commerce.db.pojo.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer>{

}
