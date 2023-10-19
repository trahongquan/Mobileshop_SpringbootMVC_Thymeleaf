package com.springbootmvcwithentity.demo.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MyService {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public MyService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Map<String, Object>> executeSelectQuery() {
        // Viết truy vấn SELECT của bạn ở đây
        String sql = "SELECT * FROM phones";
        return jdbcTemplate.queryForList(sql);
    }
}

