package com.navercorp.pinpoint.test.util;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.ConnectionImpl;
import com.mysql.jdbc.JDBC4Connection;

/**
 * @author hyungil.jeong
 */
public class MockConnection extends JDBC4Connection {
    
    private static final long serialVersionUID = 7131373901918172590L;
    private final String databaseName;
    
    public MockConnection(String hostToConnectTo, int portToConnectTo, Properties info, String databaseToConnectTo, String url) throws SQLException {
        super(hostToConnectTo, portToConnectTo, info, databaseToConnectTo, url);
        this.databaseName = databaseToConnectTo;
    }

    @Override
    public void createNewIO(boolean isForReconnect) throws SQLException {
        return;
    }
    
    @Override
    public boolean versionMeetsMinimum(int major, int minor, int subminor) throws SQLException {
        return true;
    }
    
    @Override
    public java.sql.PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency) throws SQLException {
        if (this.isClosed()) {
            try {
                Field isConnectedField = ConnectionImpl.class.getDeclaredField("isClosed");
                isConnectedField.setAccessible(true);
                isConnectedField.set(this, false);
            } catch (Exception e) {
                // bleh
            }
        }
        return new MockPreparedStatement(this, sql, databaseName);
    }
}
