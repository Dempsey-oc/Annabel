package nl.craftex.mt.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;

public class TexturePack implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(cmd.getName().equalsIgnoreCase("texturepack")) {
			sender.sendMessage(ChatColor.LIGHT_PURPLE + "Download link: " + ChatColor.GRAY + "http://dl.overcast.be/latest.zip");
		}
		return true;
	}
	
}
