package nl.craftex.mt.mechanics;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import nl.craftex.mt.config.PlayerData;

public class Join implements Listener{

	PlayerData c = new PlayerData();
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent e) {
		c.setup(Bukkit.getPluginManager().getPlugin("CraftMT"), e.getPlayer());
		c.generateMultiplier(e.getPlayer());
		e.getPlayer().setFlying(false);
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + e.getPlayer().getName() + " suffix &a[" + String.valueOf(c.read(Bukkit.getPluginManager().getPlugin("CraftMT"), e.getPlayer()).getInt("level")) + "] ");
	
	}
	
}
