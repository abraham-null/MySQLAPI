package abraham;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class testClass implements Listener {
	
    private final MySQLAPI plugin;

    public testClass(MySQLAPI plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        plugin.getSQLManager().testQuery();
    }

}
