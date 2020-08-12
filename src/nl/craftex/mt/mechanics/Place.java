package nl.craftex.mt.mechanics;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import nl.craftex.mt.config.PlayerData;
import nl.craftex.mt.util.BuildCache;
import nl.craftex.mt.util.Utils;

public class Place implements Listener{

	BuildCache bc = new BuildCache();
	PlayerData c = new PlayerData();
	Plugin p = Bukkit.getServer().getPluginManager().getPlugin("CraftMT");
	
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		if(e.getBlock().getType().equals(Material.RED_SANDSTONE_STAIRS)) {
			if(!e.getPlayer().hasPermission("craftex.atm.create")) {
				e.setCancelled(true);
			}
		}
		
		if(!e.isCancelled()) {
			if(!bc.filled(e.getPlayer())) {
				bc.add(e.getPlayer(), 1);
			}else {
				Player pl = e.getPlayer();
				if(Utils.reqExpMet(e.getPlayer())) {
					int crn = c.setup(p, pl).getInt("level");
					c.setup(p, pl).set("level", crn+1);
					c.save(p, pl);
					c.setup(p, pl).set("exp", 0);
					c.save(p, pl);
					YamlConfiguration.loadConfiguration(c.getFile(p, pl));
					pl.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§aJe bent een level omhoog gehaan naar: §7" + String.valueOf(c.setup(p, pl).getInt("level"))));
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + pl.getName() + " suffix &a[" + String.valueOf(c.setup(p, pl).getInt("level")) + "] ");
				} else {
					int crn = c.setup(p, pl).getInt("exp");
					c.setup(p, pl).set("exp", crn+Utils.rand(10, 35));
					c.save(p, pl);
					YamlConfiguration.loadConfiguration(c.getFile(p, pl));
				}
			}
		}
	}
	
}
