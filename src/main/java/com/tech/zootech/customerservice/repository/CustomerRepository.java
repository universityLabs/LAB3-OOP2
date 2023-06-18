package com.tech.zootech.customerservice.repository;

import com.tech.zootech.customerservice.domain.dto.CustomerFullName;
import com.tech.zootech.customerservice.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "select Customer from Customer c order by c.created desc")
    List<Customer> getAllByCreateDate();

    @Query("select new com.tech.zootech.customerservice.domain.dto." +
            "CustomerFullName(c.firstName, c.lastName) from Customer c")
    List<CustomerFullName> getNames();
}