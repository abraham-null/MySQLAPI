package abraham;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionPoolManager {
	
    private final MySQLAPI plugin;
    
    private HikariDataSource dataSource;
    
    private String hostname;
    private String port;
    private String database;
    private String username;
    private String password;
    
    private int minimumConnections;
    private int maximumConnections;
    private long connectionTimeout;
    private String testQuery;
    
    public ConnectionPoolManager(MySQLAPI plugin) {
        this.plugin = plugin;
        init();
    }
 
    private void init() {
        hostname = "158.69.63.67";
        port = "3306";
        database = "demodb";
        username = "lasthero";
        password = "newt6345";
        
        minimumConnections = 3;
        maximumConnections = 5;
        connectionTimeout = 5000;
        //testQuery = plugin.getConfig().getSQLPoolTestQuery();
        setupPool();
    }
    
    private void setupPool() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(
                "jdbc:mysql://" +
                        hostname +
                        ":" +
                        port +
                        "/" +
                        database
        );
        config.setDriverClassName("com.mysql.jdbc.Driver");
        config.setUsername(username);
        config.setPassword(password);
        config.setMinimumIdle(minimumConnections);
        config.setMaximumPoolSize(maximumConnections);
        config.setConnectionTimeout(connectionTimeout);
        config.setConnectionTestQuery(testQuery);
        dataSource = new HikariDataSource(config);
    }
    

    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }
    
    
    public void close(Connection conn, PreparedStatement ps, ResultSet res) {
        if (conn != null) try { conn.close(); } catch (SQLException ignored) {}
        if (ps != null) try { ps.close(); } catch (SQLException ignored) {}
        if (res != null) try { res.close(); } catch (SQLException ignored) {}
    }
    
    public void closePool() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
        }
    }
}
