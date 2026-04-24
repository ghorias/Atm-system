package com.example.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.example.demo.model.User;
// This class is responsible for mapping the result set from the database to a User object.
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();

        user.setUserId(rs.getLong("user_id")); 
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setMail(rs.getString("mail"));
        user.setAdress(rs.getString("adress"));
        user.setPersonnummer(rs.getLong("personnummer"));

        return user;
    }
}