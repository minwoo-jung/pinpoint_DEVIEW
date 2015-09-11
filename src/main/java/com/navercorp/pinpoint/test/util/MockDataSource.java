package com.navercorp.pinpoint.test.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.ConnectionFactory;

/**
 * @author hyungil.jeong
 */
public class MockDataSource extends BasicDataSource {

    private final String mockHost;
    private final int mockPort;
    private final Properties mockInfo;
    private final String mockDatabase;
    private final String mockUrl;

    public MockDataSource(String hostToConnectTo, int portToConnectTo, String databaseToConnectTo, String url) {
        this.mockHost = hostToConnectTo;
        this.mockPort = portToConnectTo;
        this.mockInfo = new Properties();
        this.mockDatabase = databaseToConnectTo;
        this.mockUrl = url;
    }
    
    @Override
    protected ConnectionFactory createConnectionFactory() throws SQLException {
        return new ConnectionFactory() {
            @Override
            public Connection createConnection() throws SQLException {
                return new MockConnection(mockHost, mockPort, mockInfo, mockDatabase, mockUrl);
            }
        };
    }

}
