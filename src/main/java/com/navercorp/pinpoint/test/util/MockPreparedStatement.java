package com.navercorp.pinpoint.test.util;

import java.sql.SQLException;
import java.util.Collections;

import com.mysql.jdbc.Buffer;
import com.mysql.jdbc.Field;
import com.mysql.jdbc.JDBC4PreparedStatement;
import com.mysql.jdbc.MySQLConnection;
import com.mysql.jdbc.ResultSetImpl;
import com.mysql.jdbc.ResultSetInternalMethods;
import com.mysql.jdbc.RowDataStatic;

/**
 * @author hyungil.jeong
 */
public class MockPreparedStatement extends JDBC4PreparedStatement {
    
    private final MySQLConnection conn;
    private final String catalog;

    public MockPreparedStatement(MySQLConnection conn, String id, String catalog) throws SQLException {
        super(conn, id, catalog);
        this.conn = conn;
        this.catalog = catalog;
    }

    @Override
    protected Buffer fillSendPacket() throws SQLException {
        return new Buffer(new byte[0]);
    }
    
    @Override
    protected ResultSetInternalMethods executeInternal(int maxRowsToRetrieve, Buffer sendPacket, boolean createStreamingResultSet, boolean queryIsSelectOnly,
            Field[] metadataFromCache, boolean isBatch) throws SQLException {
        return new ResultSetImpl(this.catalog, new Field[0], new RowDataStatic(Collections.emptyList()), this.conn, null);
    }
}
