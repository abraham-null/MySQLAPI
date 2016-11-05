package abraham;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public class MySQLAPI extends JavaPlugin{
	
    private final Logger logger = getLogger();
    private SQLManager sql;

	@Override
	public void onDisable() {
		sql.onDisable();
	}

	@Override
	public void onEnable() {
		Bukkit.getLogger().info("              ");
		Bukkit.getLogger().info("              ");
		Bukkit.getLogger().info("initDatabase");
		Bukkit.getLogger().info("================");
		Bukkit.getLogger().info("              ");
		
		initDatabase();

		Bukkit.getLogger().info("              ");
		Bukkit.getLogger().info("================");
		Bukkit.getLogger().info("completed");
		Bukkit.getLogger().info("              ");
		Bukkit.getLogger().info("              ");
		
		Bukkit.getServer().getPluginManager().registerEvents(new testClass(this), this);
	}
	
    private void initDatabase() {
        sql = new SQLManager(this);
    }
    
    public SQLManager getSQLManager() {
        return sql;
    }

}
