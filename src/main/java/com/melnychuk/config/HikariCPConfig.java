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
        ds.setDriverClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        ds.setJdbcUrl("jdbc:mysql://sql11.freemysqlhosting.net:3306/sql11181888?autoReconnect=true&serverTimezone=UTC&useUnicode=true&characterEncoding=UTF-8&characterSetResults=UTF-8");
        ds.setUsername("sql11181888");
        ds.setPassword("NFneAhMxyh");
        ds.addDataSourceProperty("zeroDateTimeBehavior", "convertToNull");
        ds.addDataSourceProperty("autoReconnect", true);
        return ds;
    }
}