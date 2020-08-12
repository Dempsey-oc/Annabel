package nl.craftex.mt.mechanics;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import nl.craftex.mt.config.PlayerData;
import nl.craftex.mt.util.Utils;

public class Fishing implements Listener{
	
	PlayerData c = new PlayerData();
	Plugin p = Bukkit.getPluginManager().getPlugin("CraftMT");
	
	@EventHandler
	public void onPlayerFish(PlayerFishEvent e) {
		
		if(e.getCaught() instanceof Item) {
			e.setCancelled(true);
			
			ItemStack fish1 = new ItemStack(Material.COD,1);
			ItemStack fish2 = new ItemStack(Material.SALMON,1);
			ItemStack fish3 = new ItemStack(Material.TROPICAL_FISH,1);
			ItemStack fish4 = new ItemStack(Material.PUFFERFISH,1);
			
			Random r = new Random();
			int res = r.nextInt(20);
			
			if(res<=5) {
				e.getPlayer().getInventory().addItem(fish1);
			}else if(res>5 && res<=10) {
				e.getPlayer().getInventory().addItem(fish2);
			}else if(res>10 && res<=15) {
				e.getPlayer().getInventory().addItem(fish3);
			}else if(res>15 && res <=20) {
				e.getPlayer().getInventory().addItem(fish4);
			}
			
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
			}else {
				int crn = c.setup(p, pl).getInt("exp");
				int ress = crn+Utils.rand(20, 50);
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cexp add " + pl.getName() + " " + String.valueOf(ress));
				if(pl.hasPermission("minetopia.fame")) {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cexp add " + pl.getName() + " " + String.valueOf(ress/2));
				}
			}
			
			
		}
		
	}
	
}
