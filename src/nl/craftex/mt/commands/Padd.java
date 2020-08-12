package nl.craftex.mt.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class Padd implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Pssst, eat a cock");
		}else {
			if(cmd.getName().equalsIgnoreCase("padd")) {
				if(args.length == 0) {
					sender.sendMessage(ChatColor.RED + "Use: /padd <player>");
				}else if(args.length == 1) {
					Bukkit.dispatchCommand(sender, "arm addmember " + args[0]);
				}else if(args.length > 1) {
					sender.sendMessage(ChatColor.RED + "Use: /padd <player>");
				}
			}
		}
		return true;
	}
	
}
