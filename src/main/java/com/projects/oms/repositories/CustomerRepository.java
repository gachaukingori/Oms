package com.projects.oms.repositories;

import org.springframework.jdbc.core.PreparedStatementCreator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerRepository implements PreparedStatementCreator {

  private String query;
    public CustomerRepository(String query){
        this.query = query;
    }

    @Override
    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
        return con.prepareStatement(query);
    }

}
