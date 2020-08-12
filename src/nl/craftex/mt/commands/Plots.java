package nl.craftex.mt.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class Plots implements CommandExecutor{
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Psst, go fuck yourself x");
		}else {
			if(cmd.getName().equalsIgnoreCase("plots")) {
				if(args.length == 0) {
					Bukkit.dispatchCommand(sender, "arm gui");
				}else if(args.length == 1) {
					if(args[0].equalsIgnoreCase("accepteer")) {
						Bukkit.dispatchCommand(sender, "arm offer accept");
					}else if(args[0].equalsIgnoreCase("annuleer")) {
						Bukkit.dispatchCommand(sender, "arm offer reject");
					}else if(args[0].equalsIgnoreCase("negeer")) {
						Bukkit.dispatchCommand(sender, "arm offer cancel");
					}else if(args[0].equalsIgnoreCase("verkoop")) {
						sender.sendMessage(ChatColor.RED + "Gebruik: " + ChatColor.GRAY + "/plots verkoop <speler> <plot> <prijs>");
					}else if(args[0].equalsIgnoreCase("help")) {
						sender.sendMessage(ChatColor.GOLD + "|----------------------------------[PLOTS HELP]---------------------------------|");
						sender.sendMessage(ChatColor.GRAY + "/plots - Open het plots menu");
						sender.sendMessage(ChatColor.GRAY + "/plots accepteer - accepteer een verzoek");
						sender.sendMessage(ChatColor.GRAY + "/plots annuleer - annuleer een verzoek");
						sender.sendMessage(ChatColor.GRAY + "/plots negeer - negeer een verzoek");
						sender.sendMessage(ChatColor.GRAY + "/plots verkoop <speler> <plot> <prijs> - verzoek een speler om je plot te kopen!");
					} else {
						sender.sendMessage(ChatColor.GOLD + "|------------------------------[PLOTS HELP]-----------------------------|");
						sender.sendMessage(ChatColor.GRAY + "/plots - Open het plots menu");
						sender.sendMessage(ChatColor.GRAY + "/plots accepteer - accepteer een verzoek");
						sender.sendMessage(ChatColor.GRAY + "/plots annuleer - annuleer een verzoek");
						sender.sendMessage(ChatColor.GRAY + "/plots negeer - negeer een verzoek");
						sender.sendMessage(ChatColor.GRAY + "/plots verkoop <speler> <plot> <prijs> - verzoek een speler om je plot te kopen!");
					}
					
					/*
					 * accepteer
					 * annuleer
					 * negeer
					 * */
				}else if(args.length == 2) {
					if(args[0].equalsIgnoreCase("accepteer")) {
						Bukkit.dispatchCommand(sender, "arm offer accept");
					}else if(args[0].equalsIgnoreCase("annuleer")) {
						Bukkit.dispatchCommand(sender, "arm offer reject");
					}else if(args[0].equalsIgnoreCase("negeer")) {
						Bukkit.dispatchCommand(sender, "arm offer cancel");
					}else if(args[0].equalsIgnoreCase("verkoop")) {
						sender.sendMessage(ChatColor.RED + "Gebruik: " + ChatColor.GRAY + "/plots verkoop <speler> <plot> <prijs>");
					}else if(args[0].equalsIgnoreCase("help")) {
						sender.sendMessage(ChatColor.GOLD + "|------------------------------[PLOTS HELP]-----------------------------|");
						sender.sendMessage(ChatColor.GRAY + "/plots - Open het plots menu");
						sender.sendMessage(ChatColor.GRAY + "/plots accepteer - accepteer een verzoek");
						sender.sendMessage(ChatColor.GRAY + "/plots annuleer - annuleer een verzoek");
						sender.sendMessage(ChatColor.GRAY + "/plots negeer - negeer een verzoek");
						sender.sendMessage(ChatColor.GRAY + "/plots verkoop <speler> <plot> <prijs> - verzoek een speler om je plot te kopen!");
					} else {
						sender.sendMessage(ChatColor.GOLD + "|----------------------------------[PLOTS HELP]---------------------------------|");
						sender.sendMessage(ChatColor.GRAY + "/plots - Open het plots menu");
						sender.sendMessage(ChatColor.GRAY + "/plots accepteer - accepteer een verzoek");
						sender.sendMessage(ChatColor.GRAY + "/plots annuleer - annuleer een verzoek");
						sender.sendMessage(ChatColor.GRAY + "/plots negeer - negeer een verzoek");
						sender.sendMessage(ChatColor.GRAY + "/plots verkoop <speler> <plot> <prijs> - verzoek een speler om je plot te kopen!");
					}
					
				}else if(args.length == 3) {
					if(args[0].equalsIgnoreCase("accepteer")) {
						Bukkit.dispatchCommand(sender, "arm offer accept");
					}else if(args[0].equalsIgnoreCase("annuleer")) {
						Bukkit.dispatchCommand(sender, "arm offer reject");
					}else if(args[0].equalsIgnoreCase("negeer")) {
						Bukkit.dispatchCommand(sender, "arm offer cancel");
					}else if(args[0].equalsIgnoreCase("verkoop")) {
						sender.sendMessage(ChatColor.RED + "Gebruik: " + ChatColor.GRAY + "/plots verkoop <speler> <plot> <prijs>");
					}else if(args[0].equalsIgnoreCase("help")) {
						sender.sendMessage(ChatColor.GOLD + "|----------------------------------[PLOTS HELP]---------------------------------|");
						sender.sendMessage(ChatColor.GRAY + "/plots - Open het plots menu");
						sender.sendMessage(ChatColor.GRAY + "/plots accepteer - accepteer een verzoek");
						sender.sendMessage(ChatColor.GRAY + "/plots annuleer - annuleer een verzoek");
						sender.sendMessage(ChatColor.GRAY + "/plots negeer - negeer een verzoek");
						sender.sendMessage(ChatColor.GRAY + "/plots verkoop <speler> <plot> <prijs> - verzoek een speler om je plot te kopen!");
					} else {
						sender.sendMessage(ChatColor.GOLD + "|----------------------------------[PLOTS HELP]---------------------------------|");
						sender.sendMessage(ChatColor.GRAY + "/plots - Open het plots menu");
						sender.sendMessage(ChatColor.GRAY + "/plots accepteer - accepteer een verzoek");
						sender.sendMessage(ChatColor.GRAY + "/plots annuleer - annuleer een verzoek");
						sender.sendMessage(ChatColor.GRAY + "/plots negeer - negeer een verzoek");
						sender.sendMessage(ChatColor.GRAY + "/plots verkoop <speler> <plot> <prijs> - verzoek een speler om je plot te kopen!");
					}
					
				}else if(args.length == 4) {
					if(args[0].equalsIgnoreCase("accepteer")) {
						Bukkit.dispatchCommand(sender, "arm offer accept");
					}else if(args[0].equalsIgnoreCase("annuleer")) {
						Bukkit.dispatchCommand(sender, "arm offer reject");
					}else if(args[0].equalsIgnoreCase("negeer")) {
						Bukkit.dispatchCommand(sender, "arm offer cancel");
					}else if(args[0].equalsIgnoreCase("verkoop")) {
						Bukkit.dispatchCommand(sender, "arm offer " + args[1] + " " + args[2] + " " + args[3]);
					}else if(args[0].equalsIgnoreCase("help")) {
						sender.sendMessage(ChatColor.GOLD + "|----------------------------------[PLOTS HELP]---------------------------------|");
						sender.sendMessage(ChatColor.GRAY + "/plots - Open het plots menu");
						sender.sendMessage(ChatColor.GRAY + "/plots accepteer - accepteer een verzoek");
						sender.sendMessage(ChatColor.GRAY + "/plots annuleer - annuleer een verzoek");
						sender.sendMessage(ChatColor.GRAY + "/plots negeer - negeer een verzoek");
						sender.sendMessage(ChatColor.GRAY + "/plots verkoop <speler> <plot> <prijs> - verzoek een speler om je plot te kopen!");
					} else {
						sender.sendMessage(ChatColor.GOLD + "|----------------------------------[PLOTS HELP]---------------------------------|");
						sender.sendMessage(ChatColor.GRAY + "/plots - Open het plots menu");
						sender.sendMessage(ChatColor.GRAY + "/plots accepteer - accepteer een verzoek");
						sender.sendMessage(ChatColor.GRAY + "/plots annuleer - annuleer een verzoek");
						sender.sendMessage(ChatColor.GRAY + "/plots negeer - negeer een verzoek");
						sender.sendMessage(ChatColor.GRAY + "/plots verkoop <speler> <plot> <prijs> - verzoek een speler om je plot te kopen!");
					}
					
				}else if(args.length > 4) {
					sender.sendMessage(ChatColor.DARK_RED + "Ongeldig commando!");
				}
			}
		}
		
		return true;
	}
}
