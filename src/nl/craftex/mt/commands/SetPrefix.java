package nl.craftex.mt.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetPrefix implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.DARK_RED + "And iiiiiiiiiiiiiiiiiiiiiiiiiiii will alllllwwaaayyysss looovvveee yoooouuuuuu!!!!!");
		}else {
			if(cmd.getName().equalsIgnoreCase("setprefix")) {
				if(sender.hasPermission("craftex.setprefix")) {
					if(args.length < 2) {
						sender.sendMessage(ChatColor.RED + "Use: /setprefix <player> <prefix>");
						sender.sendMessage(ChatColor.RED + "Replace SPACE with _");
					}else if(args.length == 2) {
						Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " prefix \"" + args[1].replaceAll("_", " ") + " \"");
					}else if(args.length > 2) {
						sender.sendMessage(ChatColor.RED + "Use: /setprefix <player> <prefix>");
						sender.sendMessage(ChatColor.RED + "Replace SPACE with _");
					}
				}else {
					sender.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
				}
			}
		}
		
		
		return true;
	}
	
}
