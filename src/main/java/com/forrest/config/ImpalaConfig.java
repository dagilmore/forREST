package com.forrest.config;

import org.apache.hive.jdbc.HiveDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author David Gilmore
 * @date 11/30/13
 */
@PropertySource(value={"classpath:impala.properties"})
public class ImpalaConfig {

    @Autowired
    Environment env;

    //JDBC Client Config
    @Bean
    public HiveDriver hiveDriver() {
        return new HiveDriver();
    }

    @Bean
    public DataSource hiveDataSource() throws ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException {
        return new SimpleDriverDataSource(hiveDriver(), env.getProperty("impala.url"));
    }

    @Bean
    public JdbcTemplate hiveTemplate() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        JdbcTemplate hiveTemplate = new JdbcTemplate(hiveDataSource());
        return hiveTemplate;
    }

}