package com.projects.oms.repositories;

import com.projects.oms.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>  {
}
