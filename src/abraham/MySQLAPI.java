package abraham;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public class MySQLAPI extends JavaPlugin{
	
    private final Logger logger = getLogger();
    public SQLManager sql;
    public ConnectionPoolManager pool = null;

	@Override
	public void onDisable() {
		pool.closePool();
	}

	@Override
	public void onEnable() {
		createConfig();
		
		pool = new ConnectionPoolManager(this);
		
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
    
    private void createConfig() {
        try {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdirs();
            }
            File file = new File(getDataFolder(), "config.yml");
            if (!file.exists()) {
                getLogger().info("Config.yml not found, creating!");
                saveDefaultConfig();
            } else {
                getLogger().info("Config.yml found, loading!");
            }
        } catch (Exception e) {
            e.printStackTrace();

        }

    }

}
