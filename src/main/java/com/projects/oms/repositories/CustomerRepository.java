package com.projects.oms.repositories;

import com.projects.oms.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>  {
    Optional<Customer> findByCustomerNumber(int customerNo);
}
