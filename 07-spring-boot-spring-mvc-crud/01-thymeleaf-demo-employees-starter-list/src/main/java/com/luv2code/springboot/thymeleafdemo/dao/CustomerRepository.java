package com.luv2code.springboot.thymeleafdemo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luv2code.springboot.thymeleafdemo.entity.Customer;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	// that's it ... no need to write any code !

    // add a method to sort by last name
    public List<Customer> findAllByOrderByLastNameAsc();
	
}
