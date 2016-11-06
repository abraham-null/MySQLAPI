package abraham;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class TokenSQLManager {

	private final MySQLAPI plugin;

	public TokenSQLManager(MySQLAPI plugin) {
		this.plugin = plugin;
	}

	public void insertNewPlayerCount(String playerStringUUID, int tokenCount) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = plugin.pool.getConnection();
			ps = conn.prepareStatement(
					"INSERT INTO userProfiles (UUID, tokens) VALUES ('" + playerStringUUID + "', " + tokenCount + ");");
			ps.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			plugin.pool.close(conn, ps, null);
		}

	}

	public int getPlayerTokenCount(String playerStringUUID) {
		int tokenCount = 0;
		int i = 0;

		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = plugin.pool.getConnection();
			ps = conn.prepareStatement("SELECT * FROM userProfiles WHERE UUID = '" + playerStringUUID + "';");
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				i++;
				tokenCount = rs.getInt("tokens");
			}

			if (i == 0) {
				tokenCount = 0;
				insertNewPlayerCount(playerStringUUID, tokenCount);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			plugin.pool.close(conn, ps, null);
		}

		return tokenCount;

	}

}
