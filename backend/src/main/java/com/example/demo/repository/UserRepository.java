package com.example.demo.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
// This class is responsible for interacting with the database to perform operations related to User entities.
public class UserRepository {

    private final JdbcTemplate jdbcTemplate; // JdbcTemplate is used to execute SQL queries and map the results to Java objects.
    private final UserRowMapper rowMapper = new UserRowMapper(); // UserRowMapper is responsible for mapping the result set from the database to a User object.

    public UserRepository(JdbcTemplate jdbcTemplate) { // Constructor that initializes the JdbcTemplate.
        this.jdbcTemplate = jdbcTemplate; // Assign the provided JdbcTemplate to the class field.
    }
// This method retrieves a User from the database based on the provided user ID.
    public User findByUserId(long userId) {
    String sql = "SELECT * FROM users WHERE user_id = ?"; // SQL query to select a user based on the user ID.

    List<User> result = jdbcTemplate.query(sql, rowMapper, userId); // Execute the query and map the result to a list of User objects using the UserRowMapper.

    return result.isEmpty() ? null : result.get(0);
}
// This method retrieves all users from the database.
public List<User> findAll() { 
    String sql = "SELECT * FROM users"; // SQL query to select all users from the database.
    return jdbcTemplate.query(sql, rowMapper); // Execute the query and map the results to a list of User objects using the UserRowMapper.
}
}