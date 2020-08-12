package nl.craftex.mt.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class Atm implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Go home kyle, You're drunk!");
		}else {
			if(cmd.getName().equalsIgnoreCase("atm")) {
				if(sender.hasPermission("craftex.atm")) {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cc open atm " + sender.getName());
				}else {
					sender.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
				}
			}
		}
		
		return true;
	}
	
}
