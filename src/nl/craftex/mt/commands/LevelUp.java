package nl.craftex.mt.commands;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import nl.craftex.mt.config.PlayerData;

public class LevelUp implements CommandExecutor {

	PlayerData c = new PlayerData();
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Skip");
		}else {
			if(cmd.getName().equalsIgnoreCase("levelup")) {
				if(sender.hasPermission("craftex.levelup")) {
					File f = c.getFile(Bukkit.getPluginManager().getPlugin("CraftMT"), (Player)sender);
					FileConfiguration conf = YamlConfiguration.loadConfiguration(f);
					conf.set("level", conf.getInt("level")+1);
					try {
						conf.save(f);
					}catch(Exception e) {
						e.printStackTrace();
					}
					YamlConfiguration.loadConfiguration(f);
				}
			}
		}
		
		return true;
	}
	
}
