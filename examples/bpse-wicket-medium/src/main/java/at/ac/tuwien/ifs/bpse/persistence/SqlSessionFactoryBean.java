package at.ac.tuwien.ifs.bpse.persistence;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.sql.DataSource;

import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.TransactionIsolationLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.NestedIOException;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;

/**
 * SQL session factory that uses spring.
 * This class sourced from:
 * http://opensource.atlassian.com/confluence/oss/display/IBATIS/Spring+integration+with+iBATIS+3
 *
 * @author poitrac
 */
public class SqlSessionFactoryBean implements SqlSessionFactory {

    private final Logger logger = LoggerFactory.getLogger(SqlSessionFactoryBean.class);

    /**
     * iBATIS environment.
     */
    private Environment environment;
    /**
     * Location of iBATIS configuration files.
     */
    private org.springframework.core.io.Resource configLocation;
    /**
     * SQL session factory for iBATIS.
     */
    private SqlSessionFactory sqlSessionFactory;


    @PostConstruct
    public void buildFactory() throws IOException {
        logger.debug("Configuring ibatis with file: {}", configLocation);

        try {
            Reader reader = new InputStreamReader(configLocation.getInputStream());
            XMLConfigBuilder parser = new XMLConfigBuilder(reader);
            Configuration configuration = parser.parse();
            configuration.setEnvironment(environment);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        } catch (IOException e) {
            throw new NestedIOException("Failed to parse config resource: " + configLocation, e);
        }
    }
    
    @PreDestroy
    public void cleanFactory() {
        sqlSessionFactory = null;
    }

    public void setConfigLocation(org.springframework.core.io.Resource configLocation) {
        this.configLocation = configLocation;
    }
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
    public Configuration getConfiguration() {
        return sqlSessionFactory.getConfiguration();
    }
    public SqlSession openSession() {
        return openSession(this.getConfiguration().getDefaultExecutorType());
    }
    public SqlSession openSession(boolean autoCommit) {
        return openSession(this.getConfiguration().getDefaultExecutorType(), autoCommit);
    }
    public SqlSession openSession(Connection connection) {
        return openSession(this.getConfiguration().getDefaultExecutorType(), connection);
    }
    public SqlSession openSession(ExecutorType execType) {
        DataSource dataSource = environment.getDataSource();
        Connection connection;
        try {
            if (dataSource instanceof TransactionAwareDataSourceProxy) {
                connection = dataSource.getConnection();
            } else {
                connection = DataSourceUtils.doGetConnection(dataSource);
            }
            return sqlSessionFactory.openSession(execType, connection);
        } catch (SQLException ex) {
            throw (new SQLErrorCodeSQLExceptionTranslator(dataSource)).translate("iBATIS operation", null, ex);
        }
    }
    public SqlSession openSession(ExecutorType execType, boolean autoCommit) {
        return openSession(execType);
    }
    public SqlSession openSession(ExecutorType execType, Connection connection) {
        return sqlSessionFactory.openSession(execType, connection);
    }
	public SqlSession openSession(TransactionIsolationLevel arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	public SqlSession openSession(ExecutorType arg0,
			TransactionIsolationLevel arg1) {
		// TODO Auto-generated method stub
		return null;
	}

}