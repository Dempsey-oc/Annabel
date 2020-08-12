package nl.craftex.mt.commands;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class Wholesale implements CommandExecutor{

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Aight, imma head out!");
		}else {
			if(cmd.getName().equalsIgnoreCase("pass")) {
				Player p = (Player)sender;
				if(!p.hasPermission("craftex.pass")) {
					p.sendMessage(ChatColor.RED + "You do not have permission to use this command!");
				}else {
					if(args.length == 0) {
						p.sendMessage(ChatColor.RED + "Usage: /pass <player> <branche>");
					}else if(args.length > 1) {
						if(Bukkit.getPlayer(args[0])!=null) {
							GregorianCalendar cl = new GregorianCalendar();
							int year = cl.get(Calendar.YEAR);
							int month = cl.get(Calendar.MONTH);
							int day = cl.get(Calendar.DAY_OF_MONTH);
							ItemStack i = new ItemStack(Material.LIME_DYE,1);
							ItemMeta meta = i.getItemMeta();
							meta.setDisplayName("§aInkoop Pass");
							ArrayList<String> lore = new ArrayList<String>();
							lore.add("§fNaam: §d" + args[0]);
							lore.add("§fBranche: §d" + args[1]);
							if(month == 11) {
								int finm = 1;
								lore.add("§7Geldig tot: " + String.valueOf(day) + "/" + String.valueOf(finm) + "/" + String.valueOf(year));
							}else {
								int finm = month+2;
								lore.add("§7Geldig tot: " + String.valueOf(day) + "/" + String.valueOf(finm) + "/" + String.valueOf(year));
							}
							meta.setLore(lore);
							i.setItemMeta(meta);
							p.getInventory().addItem(i);
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + args[0] + " add menu." + args[1].toLowerCase());
							p.sendMessage(ChatColor.GREEN + "Je hebt een inkoop pas gemaakt!");
						}else {
							p.sendMessage(ChatColor.DARK_RED + "Ongeldige speler!");
						}
					}else if(args.length == 1) {
						p.sendMessage(ChatColor.RED + "Usage: /pass <player> <branche>");
					}
				}
			}
		}
		
		return true;
	}
	
}