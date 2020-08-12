package nl.craftex.mt.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class Discord implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Psst, behind you :)");
		}else {
			if(cmd.getName().equalsIgnoreCase("discord")) {
				sender.sendMessage("§5Discord§8: §dhttps://discord.gg/C2gs9Bx");
			}
		}
		
		
		return true;
	}
	
}
