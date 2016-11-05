package abraham;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;

public class SQLManager {
	
    private final MySQLAPI plugin;
    
    
    public SQLManager(MySQLAPI plugin) {
        this.plugin = plugin;
    }
    
    
    public void testQuery() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = plugin.pool.getConnection();
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
        	plugin.pool.close(conn, ps, null);
        }
    }

}
