package nl.craftex.mt.mechanics;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import nl.craftex.mt.Main;
import nl.craftex.mt.config.PlayerData;
import nl.craftex.mt.util.Utils;

public class Death implements Listener{

	PlayerData c = new PlayerData();
	Plugin p = Bukkit.getServer().getPluginManager().getPlugin("CraftMT");
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		ItemStack i = new ItemStack(Material.PLAYER_HEAD,1);
		SkullMeta sm = (SkullMeta)i.getItemMeta();
		sm.setOwningPlayer(e.getEntity());
		sm.setDisplayName(e.getEntity().getName() + "'s hoofd");
		i.setItemMeta(sm);
		e.getEntity().getLocation().getWorld().dropItem(e.getEntity().getLocation(), i);
		if(e.getEntity().getKiller() instanceof Player) {
			Player k = (Player)e.getEntity().getKiller();
			if(k.hasPermission("minetopia.slager")) {
				int rss = Utils.rand(500, 2500);
				Main.econ.depositPlayer(k, rss);
				k.sendMessage(ChatColor.GREEN + "Je hebt $"+String.valueOf(rss)+" ontvangen voor het doden van " + e.getEntity().getName());
			}
			
			Player pl = (Player)e.getEntity();
			if(Utils.reqExpMet(pl)) {
				int crn = c.setup(p, pl).getInt("level");
				c.setup(p, pl).set("level", crn+1);
				c.save(p, pl);
				c.setup(p, pl).set("exp", 0);
				c.save(p, pl);
				c.reload(p, pl);
				pl.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§aJe bent een level omhoog gehaan naar: §7" + String.valueOf(c.setup(p, pl).getInt("level"))));
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + pl.getName() + " suffix &a[" + String.valueOf(c.setup(p, pl).getInt("level")) + "] ");
			}else {
				int crn = c.setup(p, pl).getInt("exp");
				int res = crn+200;
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cexp add " + pl.getName() + " " + String.valueOf(res));
				if(pl.hasPermission("minetopia.fame")) {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cexp add " + pl.getName() + " " + String.valueOf(res/2));
				}
			}
			
		}
		
		
	}
	
}
