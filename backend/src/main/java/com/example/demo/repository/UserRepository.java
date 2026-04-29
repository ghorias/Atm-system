package com.example.demo.repository;

import com.example.demo.model.User;
import com.example.demo.repository.mapper.UserRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper rowMapper = new UserRowMapper();

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    //hitta en user i databasen baserat på userId
    public User findByUserId(long userId) {
    String sql = "SELECT * FROM users WHERE user_id = ?";

    List<User> result = jdbcTemplate.query(sql, rowMapper, userId);

    return result.isEmpty() ? null : result.get(0);
}
//hitta alla users i databasen
public List<User> findAll() {
    String sql = "SELECT * FROM users";
    return jdbcTemplate.query(sql, rowMapper);
}
}