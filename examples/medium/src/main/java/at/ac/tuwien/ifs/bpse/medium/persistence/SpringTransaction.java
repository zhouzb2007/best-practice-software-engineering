package at.ac.tuwien.ifs.bpse.medium.persistence;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.ibatis.transaction.Transaction;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

/**
 * iBATIS 3 transaction that is managed by Spring.
 * This class sourced from:
 * http://opensource.atlassian.com/confluence/oss/display/IBATIS/Spring+integration+with+iBATIS+3
 *
 * @author poitrac
 */
public class SpringTransaction implements Transaction {

    private final DataSource dataSource;
    private final Connection connection;

    SpringTransaction(DataSource dataSource, Connection connection) {
        this.connection = connection;
        this.dataSource = dataSource;
    }

    public void close() throws SQLException {
        if (dataSource instanceof TransactionAwareDataSourceProxy) {
            connection.close();
        } else {
            DataSourceUtils.doReleaseConnection(connection, dataSource);
        }
    }
    public void commit() throws SQLException {
        // Commit is done elsewhere.
    }
    public Connection getConnection() {
        return connection;
    }
    public void rollback() throws SQLException {
        // Rollback is done elsewhere.
    }

}
