package nl.craftex.mt.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import nl.craftex.mt.config.PlayerData;

public class Level implements CommandExecutor{

	PlayerData c = new PlayerData();
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "I will find you, and i will kill you.");
		}else {
			if(cmd.getName().equalsIgnoreCase("level")) {
				Player p = (Player) sender;
				int lvl = c.read(Bukkit.getPluginManager().getPlugin("CraftMT"), p).getInt("level");
				p.sendMessage(ChatColor.LIGHT_PURPLE + "You are level: " + ChatColor.GRAY + String.valueOf(lvl));
			}
		}
		
		return true;
	}
	
}
