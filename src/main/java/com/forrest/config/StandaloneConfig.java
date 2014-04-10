package com.forrest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author David Gilmore
 * @date 11/30/13
 */
@Configuration
@ComponentScan("com.forrest.core")
public class StandaloneConfig {

    private DataSource dataSource() {
        return new org.apache.hadoop.hive.jdbc.HiveDataSource();
    }

    @Bean
    public JdbcTemplate hiveTemplate() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        JdbcTemplate hiveTemplate = new JdbcTemplate(dataSource());
        return hiveTemplate;
    }

    @Bean
    public JdbcTemplate impalaTemplate() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        JdbcTemplate impalaTemplate = new JdbcTemplate(dataSource());
        return impalaTemplate;
    }
}
