package at.ac.tuwien.ifs.bpse.medium.persistence;

import java.sql.Connection;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.TransactionFactory;

/**
 * Factory for Spring transactions.
 * This class sourced from:
 * http://opensource.atlassian.com/confluence/oss/display/IBATIS/Spring+integration+with+iBATIS+3
 *
 * @author poitrac
 */
public class SpringTransactionFactory implements TransactionFactory {

    /**
     * Connection's data source.
     */
    private final DataSource dataSource;

    SpringTransactionFactory(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Transaction newTransaction(Connection conn, boolean autoCommit) {
        return new SpringTransaction(dataSource, conn);
    }
    public void setProperties(Properties props) {
    }
}

