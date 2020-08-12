package nl.craftex.mt.commands;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import dev.lone.itemsadder.api.ItemsAdder;
import net.md_5.bungee.api.ChatColor;

public class Vergunning implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Yeeeeehhhaaaaawwww!!!!!");
		}else {
			if(cmd.getName().equalsIgnoreCase("vergunning")) {
				Player p = (Player)sender;
				if(!p.hasPermission("craftex.vergunning")) {
					p.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
				}else {
					if(args.length == 0) {
						p.sendMessage(ChatColor.RED + "Usage: /vergunning <player> <limiet>");
					}else if(args.length > 1) {
						if(Bukkit.getPlayer(args[0])!=null) {
							GregorianCalendar cl = new GregorianCalendar();
							int year = cl.get(Calendar.YEAR);
							int month = cl.get(Calendar.MONTH);
							int day = cl.get(Calendar.DAY_OF_MONTH);
							ItemStack i = ItemsAdder.getCustomItem("green_coupon");
							ItemMeta meta = i.getItemMeta();
							meta.setDisplayName("§2Wapen Vergunning");
							ArrayList<String> lore = new ArrayList<String>();
							lore.add("§fNaam: §d" + args[0]);
							lore.add("§fLimiet: §d" + args[1]);
							if(month == 11) {
								int finm = 3;
								lore.add("§7Geldig tot: " + String.valueOf(day) + "/" + String.valueOf(finm) + "/" + String.valueOf(year));
							}else {
								int finm = month+4;
								lore.add("§7Geldig tot: " + String.valueOf(day) + "/" + String.valueOf(finm) + "/" + String.valueOf(year));
							}
							meta.setLore(lore);
							i.setItemMeta(meta);
							p.getInventory().addItem(i);
							p.sendMessage(ChatColor.GREEN + "Wapenvergunning aangemaakt!");
						}else {
							p.sendMessage(ChatColor.DARK_RED + "Invalid player!");
						}
					}else if(args.length == 1) {
						p.sendMessage(ChatColor.RED + "Usage: /vergunning <player> <limiet>");
					}
				}
			}
		}
		
		return true;
	}
	
}
