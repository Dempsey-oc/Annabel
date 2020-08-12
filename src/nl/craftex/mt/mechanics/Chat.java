package nl.craftex.mt.mechanics;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import nl.craftex.mt.config.PlayerData;

public class Chat implements Listener{

	PlayerData c = new PlayerData();
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		FileConfiguration config = c.setup(Bukkit.getPluginManager().getPlugin("CraftMT"), e.getPlayer());
		//PermissionUser user = PermissionsEx.getUser(e.getPlayer());
		//String prefix = user.getPrefix();
		//String suffix = user.getSuffix();
		//String name = e.getPlayer().getName();
		String level = String.valueOf(config.getInt("level"));
		//String format = "";
		
		
		/*
		if(user.inGroup("Player")) {
			format = ChatColor.translateAlternateColorCodes('&', "&8Player");
		}else if(user.inGroup("VIP")) {
			format = ChatColor.translateAlternateColorCodes('&', "&5VIP");
		}else if(user.inGroup("Iron")) {
			format = ChatColor.translateAlternateColorCodes('&', "&7Iron");
		}else if(user.inGroup("Gold")) {
			format = ChatColor.translateAlternateColorCodes('&', "&eGold");
		}else if(user.inGroup("Diamond")) {
			format = ChatColor.translateAlternateColorCodes('&', "&bDiamond");
		}else if(user.inGroup("Emerald")) {
			format = ChatColor.translateAlternateColorCodes('&', "&aEmerald");
		}else if(user.inGroup("Obsidian")) {
			format = ChatColor.translateAlternateColorCodes('&', "&9Obsidian");
		}else if(user.inGroup("Helper")) {
			format = ChatColor.translateAlternateColorCodes('&', "&eHelper");
		}else if(user.inGroup("Mod")) {
			format = ChatColor.translateAlternateColorCodes('&', "&6Mod");
		}else if(user.inGroup("Admin")) {
			format = ChatColor.translateAlternateColorCodes('&', "&4Admin");
		}else if(user.inGroup("Developer")) {
			format = ChatColor.translateAlternateColorCodes('&', "&9Developer");
		}else if(user.inGroup("Builder")) {
			format = ChatColor.translateAlternateColorCodes('&', "&bBuilder");
		}else if(user.inGroup("Owner")) {
			format = ChatColor.translateAlternateColorCodes('&', "&4Owner");
		}else if(user.inGroup("Creator")) {
			format = ChatColor.translateAlternateColorCodes('&', "&4C&6r&ee&aa&bt&9o&dr");
		}
		e.setFormat("§a[" + level + "]§r " + format + " §r" + ChatColor.translateAlternateColorCodes('&', prefix) + " §7" + name + " " + ChatColor.translateAlternateColorCodes('&', suffix) + "§7> §r" + e.getMessage());
		*/
		
		e.getFormat().replace("{LEVEL}", level);
	}
	
}
