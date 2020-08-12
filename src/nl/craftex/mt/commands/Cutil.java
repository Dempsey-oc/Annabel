package nl.craftex.mt.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import nl.craftex.mt.config.LocationManager;
import nl.craftex.mt.util.LocationType;

public class Cutil implements CommandExecutor{

	LocationManager lm = new LocationManager();
	
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "These commands yes? They not for u. Shooooo");
		} else {
			Player p = (Player)sender;
			if(cmd.getName().equalsIgnoreCase("cutil")) {
				if(p.hasPermission("craftex.bypass")) {
					if(args.length == 2) {
						if(args[0].equalsIgnoreCase("set")) {
							if(args[1].equalsIgnoreCase("slager")) {
								lm.setLocation(LocationType.SLAGER, p.getLocation());
								p.sendMessage(ChatColor.DARK_RED + "Locatie ingesteld voor worker: 'Slager'");
							}else {
								sender.sendMessage(ChatColor.RED + "Beschikbare workers: Slager");
							}
						}else if(args[0].equalsIgnoreCase("toggle")) {
							if(args[1].equalsIgnoreCase("slager")) {
								if(lm.state(LocationType.SLAGER)) {
									lm.t(LocationType.SLAGER);
									p.sendMessage(ChatColor.RED + "Worker disabled!");
								} else {
									lm.t(LocationType.SLAGER);
									p.sendMessage(ChatColor.GREEN + "Worker enabled!");
								}
							}else {
								sender.sendMessage(ChatColor.RED + "Beschikbare workers: Slager");
							}
						}
						p.sendMessage(ChatColor.RED + "/cutil set <job> - Zet worker location voor job");
						p.sendMessage(ChatColor.RED + "/cutil toggle <job> - Toggle worker location voor job");
					}else if(args.length == 1) {
						if(args[0].equalsIgnoreCase("cdspn")) {
							for(Player pls : Bukkit.getOnlinePlayers()) {
								pls.setDisplayName(pls.getName());
								sender.sendMessage(ChatColor.RED + "Naam gefixt voor: §7" + pls.getName());
							}
							
						}
					}
				}else {
					p.sendMessage(ChatColor.DARK_RED + "Je hebt geen toegang tot dit commando!");
				}
			}
		}
		
		return true;
	}
	
}
