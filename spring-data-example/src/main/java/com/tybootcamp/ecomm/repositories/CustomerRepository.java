package com.tybootcamp.ecomm.repositories;

import com.tybootcamp.ecomm.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
    Customer findByName(String name);// select * from customer where name=?
}
