package com.smv.onlineBankingAPI.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:application.properties")
@EnableJpaRepositories(basePackages = "com.smv.onlineBankingAPI")
@EnableTransactionManagement
public class JpaConfig {

    @Value("${DB_USERNAME}")
    private String user;

    @Value("${DB_PASSWORD}")
    private String password;

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dateSource = new DriverManagerDataSource();
        dateSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dateSource.setUrl(env.getProperty("jdbc.url"));
        dateSource.setUsername(user);
        dateSource.setPassword(password);
        return dateSource;
    }
}
