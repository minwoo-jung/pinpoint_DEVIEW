package com.navercorp.pinpoint.test.config;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hyungil.jeong
 */
@Configuration
public class DatabaseBeanConfiguration {

    @Bean(name = "databaseIdProvider")
    public DatabaseIdProvider databaseIdProvider() {
        return new DatabaseIdProvider() {

            @Override
            public void setProperties(Properties p) {
            }

            @Override
            public String getDatabaseId(DataSource dataSource) throws SQLException {
                Connection con = dataSource.getConnection();
                DatabaseMetaData metaData = con.getMetaData();
                return metaData.getDatabaseProductName();
            }

        };
    }

}
