package com.melnychuk.config;

import org.springframework.context.annotation.Configuration;

/**
 * Created by nico on 26.06.2017.
 */
 import javax.sql.DataSource;
 import org.springframework.context.annotation.Bean;

 import com.zaxxer.hikari.HikariDataSource;
 /**
 *
 * @author Nicolas Haiduchok
 */

@Configuration
public class HikariCPConfig {

    @Bean(destroyMethod = "close")
    public DataSource dataSource() {
        final HikariDataSource ds = new HikariDataSource();
        ds.setMaximumPoolSize(10);
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setJdbcUrl("jdbc:postgresql://ec2-54-228-235-185.eu-west-1.compute.amazonaws.com:5432/ddmtmeb9el11mc?sslmode=require&autoReconnect=true&serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8&characterSetResults=UTF-8");
        ds.setUsername("yszzpivdnvgjbp");
        ds.setPassword("06cd0cdf96dc7899caa400ca586f5b50416f1f5b8ea2c3e5b68c9fe0bcfc3cda");
/*
        ds.setJdbcUrl("jdbc:postgresql://127.0.0.1/test?autoReconnect=true&serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8&characterSetResults=UTF-8");
        ds.setUsername("postgres");
        ds.setPassword("root");
*/
        ds.addDataSourceProperty("zeroDateTimeBehavior", "convertToNull");
        ds.addDataSourceProperty("autoReconnect", true);
        ds.addDataSourceProperty("ssl.mode", "enable");
        return ds;
    }
}