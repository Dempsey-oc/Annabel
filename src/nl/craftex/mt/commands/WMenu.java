package nl.craftex.mt.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class WMenu implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(sender instanceof Player)) {
			if(cmd.getName().equalsIgnoreCase("wmenu")) {
				if(args.length == 0) {
					sender.sendMessage(ChatColor.RED + "Error");
				}else{
					if(Bukkit.getPlayer(args[0])!=null) {
						Player t = Bukkit.getPlayer(args[0]);
						Bukkit.dispatchCommand(t, "gwarps");
					}else {
						sender.sendMessage(ChatColor.RED + "Error");
					}
				}
			}
		}else {
			sender.sendMessage(ChatColor.RED + "Dit commando is niet voor spelers!");
		}
		
		return true;
	}
	
}
