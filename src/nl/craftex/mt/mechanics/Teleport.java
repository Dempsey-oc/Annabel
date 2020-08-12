package nl.craftex.mt.mechanics;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

import net.md_5.bungee.api.ChatColor;
import nl.craftex.mt.util.Utils;

public class Teleport implements Listener{

	@EventHandler
	public void onPlayerTeleport(PlayerTeleportEvent e) {
		if(!Utils.teleported.contains(e.getPlayer()) && !e.getPlayer().hasPermission("craftex.bypass")) {
			Utils.teleported.add(e.getPlayer());
			Bukkit.getScheduler().scheduleSyncDelayedTask(Bukkit.getPluginManager().getPlugin("CraftMT"), new Runnable() {
				public void run() {
					Utils.teleported.remove(e.getPlayer());
				}
			}, 60);
			
		}else {
			e.setCancelled(true);
			e.getPlayer().sendMessage(ChatColor.RED + "Je moet 3 seconden wachten om te teleporteren!");
		}
	}
	
}
