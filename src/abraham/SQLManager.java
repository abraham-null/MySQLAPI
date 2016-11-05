package abraham;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;

public class SQLManager {
	
    private final MySQLAPI plugin;
    private final ConnectionPoolManager pool;
    
    public SQLManager(MySQLAPI plugin) {
        this.plugin = plugin;
        pool = new ConnectionPoolManager(plugin);
    }
    
    public void onDisable() {
        pool.closePool();
    }
    
    public void testQuery() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = pool.getConnection();
            ps = conn.prepareStatement("SELECT * FROM userProfiles");
            ResultSet result = ps.executeQuery();
            
            if (result.next())
            {
                String num = result.getString("UUID");
                Bukkit.getLogger().info(num);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            pool.close(conn, ps, null);
        }
    }

}
