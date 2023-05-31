package org.example.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseConfig {

    private static HikariDataSource dataSource;

    static {

        HikariConfig config = new HikariConfig();

        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/final");
        config.setUsername("root");
        config.setPassword("");

        dataSource = new HikariDataSource(config);
    }

    public static HikariDataSource getConnetion() {
        return dataSource;
    }
}
