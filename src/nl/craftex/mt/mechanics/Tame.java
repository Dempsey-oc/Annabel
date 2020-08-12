package nl.craftex.mt.mechanics;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTameEvent;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import nl.craftex.mt.config.PlayerData;
import nl.craftex.mt.util.Utils;

public class Tame implements Listener{

	PlayerData c = new PlayerData();
	Plugin p = Bukkit.getPluginManager().getPlugin("CraftMT");
	
	
	@EventHandler
	public void onEntityTame(EntityTameEvent e) {
		Player pl = Bukkit.getPlayer(e.getOwner().getUniqueId());
		if(Utils.reqExpMet(pl)) {
			int crn = c.setup(p, pl).getInt("level");
			c.setup(p, pl).set("level", crn+1);
			c.save(p, pl);
			c.setup(p, pl).set("exp", 0);
			c.save(p, pl);
			c.reload(p, pl);
			pl.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("�aJe bent een level omhoog gehaan naar: �7" + String.valueOf(c.setup(p, pl).getInt("level"))));
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + pl.getName() + " suffix &a[" + String.valueOf(c.setup(p, pl).getInt("level")) + "] ");
		} else {
			int crn = c.setup(p, pl).getInt("exp");
			int res = crn+Utils.rand(10, 25);
			Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cexp add " + pl.getName() + " " + String.valueOf(res));
			if(pl.hasPermission("minetopia.fame")) {
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cexp add " + pl.getName() + " " + String.valueOf(res/2));
			}
		}
	}
	
}
