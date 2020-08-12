package nl.craftex.mt.commands;

import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import net.md_5.bungee.api.ChatColor;
import nl.craftex.mt.config.PlayerData;

public class Cexp implements CommandExecutor{

	PlayerData c = new PlayerData();
	Plugin p = Bukkit.getPluginManager().getPlugin("CraftMT");
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(sender instanceof Player)) {
			if(args.length == 3) {
				if(args[0].equalsIgnoreCase("set")) {
					if(Bukkit.getPlayer(args[1])!=null) {
						Player pl = Bukkit.getPlayer(args[1]);
						FileConfiguration conf = c.setup(p, pl);
						conf.set("exp", Integer.valueOf(args[2]));
						try {
							conf.save(c.getFile(p, pl));
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						sender.sendMessage(ChatColor.RED + "NO");
					}
				}else if(args[0].equalsIgnoreCase("add")) {
					if(Bukkit.getPlayer(args[1])!=null) {
						Player pl = Bukkit.getPlayer(args[1]);
						FileConfiguration conf = c.setup(p, pl);
						conf.set("exp", c.setup(p, pl).getInt("exp")+Integer.valueOf(args[2]));
						try {
							conf.save(c.getFile(p, pl));
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						sender.sendMessage(ChatColor.RED + "NO");
					}
				}else if(args[0].equalsIgnoreCase("remove")) {
					if(Bukkit.getPlayer(args[1])!=null) {
						Player pl = Bukkit.getPlayer(args[1]);
						FileConfiguration conf = c.setup(p, pl);
						conf.set("exp", c.setup(p, pl).getInt("exp")-Integer.valueOf(args[2]));
						try {
							conf.save(c.getFile(p, pl));
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						sender.sendMessage(ChatColor.RED + "NO");
					}
				}
			}
			
		}else {
			sender.sendMessage(ChatColor.RED + "Dit commando is niet voor spelers!");
		}
		
		return true;
	}
	
}
