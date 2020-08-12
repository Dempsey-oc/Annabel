package nl.craftex.mt.mechanics;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import net.md_5.bungee.api.ChatColor;

public class CommandPreProcess implements Listener{

	@EventHandler
	public void onPlayerCommandPreProcess(PlayerCommandPreprocessEvent e) {
		if(e.getMessage().startsWith("/bukkit:") || e.getMessage().startsWith("/spigot") || e.getMessage().startsWith("/?")) {
			e.setCancelled(true);
			e.getPlayer().sendMessage("§fPlugins (1): §aCraftMT");
		}else if(e.getMessage().startsWith("/help")) {
			Player p = e.getPlayer();
			e.setCancelled(true);
			if(e.getMessage().equalsIgnoreCase("/help 1")) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8------=&5Overcast Minetopia 1.14&8=---&7(&31/2&7)&8-------"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3/plots&7 open het plotmenu"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3/bedrijf &7open het bedrijfsmenu"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3/topkills &7bekijk de topkills"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3/vehicle &7open het voetuigenmenu"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3/texturepack &7ontvang een link naar de texturepack"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLet op: Je moet optifine hebben voor 1.14.4 voor een optimale speelervaring!"));
			}else if(e.getMessage().equalsIgnoreCase("/help 2")) {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8------=&5Overcast Minetopia 1.14&8=---&7(&32/2&7)&8-------"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3/website &7ontvang een link naar de website"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3/discord &7ontvang een link naar de discord"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3/buy &7ontvang een link naar de store"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3/msg {Speler} &7stuur een privebericht"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3/bal &7bekijk je geld"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3/spawn &7keer terug naar de spawn"));
			}else {
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8------=&5Overcast Minetopia 1.14&8=---&7(&31/2&7)&8-------"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3/plots&7 open het plotmenu"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3/bedrijf &7open het bedrijfsmenu"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3/topkills &7bekijk de topkills"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3/vehicle &7open het voetuigenmenu"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3/texturepack &7ontvang een link naar de texturepack"));
				p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLet op: Je moet optifine hebben voor 1.14.4 voor een optimale speelervaring!"));
			}
		}else if(e.getMessage().startsWith("/pl") && !e.getMessage().contains("/plots") && !e.getMessage().contains("/plugman") && !e.getMessage().contains("/pc") && !e.getMessage().contains("/pla")) {
			e.setCancelled(true);
			e.getPlayer().sendMessage("§fPlugins (1): §aCraftMT");
		}
	}
	
}
