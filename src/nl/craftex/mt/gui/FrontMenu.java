package nl.craftex.mt.gui;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import eu.mrapik.companies.Companies;
import eu.mrapik.companies.company.Company;
import eu.mrapik.companies.employee.Employee;
import eu.mrapik.companies.employee.Group;
import eu.mrapik.companies.jobrequest.JobRequest;
import eu.mrapik.companies.review.Review;
import net.md_5.bungee.api.ChatColor;
import nl.craftex.mt.util.Modus;
import nl.craftex.mt.util.MojangAPI;

public class FrontMenu {
	
	public static Inventory inv;
	
	ItemStack pifiller = new ItemStack(Material.PINK_STAINED_GLASS_PANE,1);
	ItemStack lfiller = new ItemStack(Material.LIGHT_BLUE_STAINED_GLASS_PANE,1);
	ItemStack pufiller = new ItemStack(Material.PURPLE_STAINED_GLASS_PANE,1);
	ItemStack whfiller = new ItemStack(Material.WHITE_STAINED_GLASS_PANE,1);
	
	ItemMeta pifm = pifiller.getItemMeta();
	ItemMeta lfm = lfiller.getItemMeta();
	ItemMeta pufm = pufiller.getItemMeta();
	ItemMeta whfm = whfiller.getItemMeta();
	
	public FrontMenu() {
		
		pifm.setDisplayName(" ");
		lfm.setDisplayName(" ");
		pufm.setDisplayName(" ");
		whfm.setDisplayName(" ");
		
		pifm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		lfm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		pufm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		whfm.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		
		pifm.addEnchant(Enchantment.DURABILITY, 1, true);
		lfm.addEnchant(Enchantment.DURABILITY, 1, true);
		pufm.addEnchant(Enchantment.DURABILITY, 1, true);
		whfm.addEnchant(Enchantment.DURABILITY, 1, true);
		
		pifiller.setItemMeta(pifm);
		lfiller.setItemMeta(lfm);
		pufiller.setItemMeta(pufm);
		whfiller.setItemMeta(whfm);
		
	}
	
	// pink
	// light_blue
	// purple
	// white
	
	public void openList(Player player) {
		System.out.println("Opened list for: " + player.getName());
		inv = Bukkit.createInventory(player, 36, "Mijn bedrijven:");
		// 0-9
		// 17-18
		// 26-35
		
		ItemStack fill = new ItemStack(Material.BLACK_STAINED_GLASS_PANE,1);
		ItemMeta fmeta = fill.getItemMeta();
		fmeta.setDisplayName(" ");
		fill.setItemMeta(fmeta);
		
		
		inv.setItem(0, fill);
		inv.setItem(1, fill);
		inv.setItem(2, fill);
		inv.setItem(3, fill);
		inv.setItem(4, fill);
		inv.setItem(5, fill);
		inv.setItem(6, fill);
		inv.setItem(7, fill);
		inv.setItem(8, fill);
		inv.setItem(9, fill);
		
		
		inv.setItem(17, fill);
		inv.setItem(18, fill);
		
		inv.setItem(26, fill);
		inv.setItem(27, fill);
		inv.setItem(28, fill);
		inv.setItem(29, fill);
		inv.setItem(30, fill);
		inv.setItem(31, fill);
		inv.setItem(32, fill);
		inv.setItem(33, fill);
		inv.setItem(34, fill);
		inv.setItem(35, fill);
		
		for(Company co : Companies.getInstance().getCompaniesManager().getCompaniesByPlayer(player.getUniqueId())) {
			if(co.getOwnerUUID().equals(player.getUniqueId().toString())) {
				ItemStack i = new ItemStack(Material.BOOK,1);
				ItemMeta iim = i.getItemMeta();
				iim.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				iim.addEnchant(Enchantment.DURABILITY, 1, true);
				iim.setDisplayName(co.getName());
				ArrayList<String> iil = new ArrayList<String>();
				iil.add(ChatColor.YELLOW + "Balance: " + ChatColor.WHITE + String.valueOf(co.getBalance()));
				iil.add(ChatColor.YELLOW + "Rank: " + ChatColor.WHITE + "Owner");
				iim.setLore(iil);
				i.setItemMeta(iim);
				
				inv.addItem(i);
			
			}else {
				if(Companies.getInstance().getEmployeesManager().getEmployeesByCompany(co.getName()).contains(Companies.getInstance().getEmployeesManager().getEmployee(player.getUniqueId(), co.getName()))) {
					Employee e = Companies.getInstance().getEmployeesManager().getEmployee(player.getUniqueId(), co.getName());
					if(e.getGroup().equals(Group.MANAGER)) {
						ItemStack i = new ItemStack(Material.BOOK,1);
						ItemMeta iim = i.getItemMeta();
						iim.addItemFlags(ItemFlag.HIDE_ENCHANTS);
						iim.addEnchant(Enchantment.DURABILITY, 1, true);
						iim.setDisplayName(co.getName());
						ArrayList<String> iil = new ArrayList<String>();
						iil.add(ChatColor.YELLOW + "Balance: " + ChatColor.WHITE + String.valueOf(co.getBalance()));
						iil.add(ChatColor.YELLOW + "Rank: " + ChatColor.WHITE + "Manager");
						iim.setLore(iil);
						i.setItemMeta(iim);
						
						inv.addItem(i);
					}
				}
				
			}
		}
		
		player.openInventory(inv);
	}
	
	public void openSollis(Player player, Company c) {
		inv = Bukkit.createInventory(player, 36, "Sollicitaties voor: " + c.getName());
		// 0-9
		// 17-18
		// 26-35
		
		ItemStack back = new ItemStack(Material.BARRIER,1);
		ItemMeta bmeta = back.getItemMeta();
		bmeta.setDisplayName(ChatColor.RED + "Ga terug");
		back.setItemMeta(bmeta);
		
		ItemStack fill = new ItemStack(Material.BLACK_STAINED_GLASS_PANE,1);
		ItemMeta fmeta = fill.getItemMeta();
		fmeta.setDisplayName(" ");
		fmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		fmeta.addEnchant(Enchantment.DURABILITY, 1, true);
		fill.setItemMeta(fmeta);
		
		inv.setItem(0, fill);
		inv.setItem(1, fill);
		inv.setItem(2, fill);
		inv.setItem(3, fill);
		inv.setItem(4, fill);
		inv.setItem(5, fill);
		inv.setItem(6, fill);
		inv.setItem(7, fill);
		inv.setItem(8, fill);
		inv.setItem(9, fill);
		
		
		inv.setItem(17, fill);
		inv.setItem(18, fill);
		
		inv.setItem(26, fill);
		inv.setItem(27, fill);
		inv.setItem(28, fill);
		inv.setItem(29, fill);
		inv.setItem(30, fill);
		inv.setItem(31, fill);
		inv.setItem(32, fill);
		inv.setItem(33, fill);
		inv.setItem(34, fill);
		inv.setItem(35, back);
		
	
		
		for(JobRequest j : Companies.getInstance().getJobRequestsManager().getJobRequestsByCompany(c.getName())) {
			ItemStack i = new ItemStack(Material.WRITTEN_BOOK,1);
			ItemMeta iim = i.getItemMeta();
			iim.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			iim.addEnchant(Enchantment.DURABILITY, 1, true);
			iim.setDisplayName(ChatColor.YELLOW + j.getName());
			ArrayList<String> iil = new ArrayList<String>();
			iil.add(ChatColor.YELLOW + "Loon eisen: §r$" + ChatColor.WHITE + String.valueOf(j.getWage()));
			iil.add(ChatColor.YELLOW + "Text: " + ChatColor.WHITE + j.getText());
			iil.add(MojangAPI.getParsedUID(j.getName()));
			iim.setLore(iil);
			i.setItemMeta(iim);
			
			inv.addItem(i);
		}
		
		
		player.openInventory(inv);
	}
	
	public void openCapital(Player player, Company c) {
		inv = Bukkit.createInventory(player, 27, "Kapitaal voor: " + c.getName());
		
		ItemStack fill = new ItemStack(Material.BLACK_STAINED_GLASS_PANE,1);
		ItemMeta fmeta = fill.getItemMeta();
		fmeta.setDisplayName(" ");
		fmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		fmeta.addEnchant(Enchantment.DURABILITY, 1, true);
		fill.setItemMeta(fmeta);
		
		ItemStack gi = new ItemStack(Material.GOLD_INGOT,1);
		ItemMeta gmeta = gi.getItemMeta();
		gmeta.setDisplayName("§eBank");
		ArrayList<String> glore = new ArrayList<String>();
		glore.add("§eKapitaal: §r$" + String.valueOf(Companies.getInstance().getCompaniesManager().getFreeCapital(c.getName())));
		gmeta.setLore(glore);
		gi.setItemMeta(gmeta);
		
		ItemStack back = new ItemStack(Material.BARRIER,1);
		ItemMeta bmeta = back.getItemMeta();
		bmeta.setDisplayName(ChatColor.RED + "Ga terug");
		back.setItemMeta(bmeta);
		
		ItemStack plus1 = new ItemStack(Material.EMERALD_BLOCK,1);
		ItemStack plus10 = new ItemStack(Material.EMERALD_BLOCK,1);
		ItemStack plus100 = new ItemStack(Material.EMERALD_BLOCK,1);
		ItemStack plus1000 = new ItemStack(Material.EMERALD_BLOCK,1);
		ItemStack plus10k = new ItemStack(Material.EMERALD_BLOCK,1);
		ItemStack plus100k  = new ItemStack(Material.EMERALD_BLOCK,1);
		ItemStack plus1Mil = new ItemStack(Material.EMERALD_BLOCK,1);
		
		ArrayList<String> l1 = new ArrayList<String>();
		ArrayList<String> l10 = new ArrayList<String>();
		ArrayList<String> l100 = new ArrayList<String>();
		ArrayList<String> l1k = new ArrayList<String>();
 		ArrayList<String> l10k = new ArrayList<String>();
 		ArrayList<String> l100k = new ArrayList<String>();
 		ArrayList<String> l1m = new ArrayList<String>();
 		
 		
 		ItemMeta m1 = plus1.getItemMeta();
 		ItemMeta m10 = plus10.getItemMeta();
 		ItemMeta m100 = plus100.getItemMeta();
 		ItemMeta m1k = plus1000.getItemMeta();
 		ItemMeta m10k = plus10k.getItemMeta();
 		ItemMeta m100k = plus100k.getItemMeta();
 		ItemMeta m1m = plus1Mil.getItemMeta();
 		
 		m1.setDisplayName("§a+ $1");
 		m10.setDisplayName("§a+ $10");
 		m100.setDisplayName("§a+ $100");
 		m1k.setDisplayName("§a+ $1000");
 		m10k.setDisplayName("§a+ $10.000");
 		m100k.setDisplayName("§a+ $100.000");
 		m1m.setDisplayName("§a+ $1.000.000");
 		
 		m1.setLore(l1);
 		m10.setLore(l10);
 		m100.setLore(l100);
 		m1k.setLore(l1k);
 		m10k.setLore(l10k);
 		m100k.setLore(l100k);
 		m1m.setLore(l1m);
 		
 		plus1.setItemMeta(m1);
 		plus10.setItemMeta(m10);
 		plus100.setItemMeta(m100);
 		plus1000.setItemMeta(m1k);
 		plus10k.setItemMeta(m10k);
 		plus100k.setItemMeta(m100k);
 		plus1Mil.setItemMeta(m1m);
 		
		inv.setItem(1, plus1);
		inv.setItem(2, plus10);
		inv.setItem(3, plus100);
		inv.setItem(4, plus1000);
		inv.setItem(5, plus10k);
		inv.setItem(6, plus100k);
		inv.setItem(7, plus1Mil);
 		
		inv.setItem(0, fill);
		
		inv.setItem(8, fill);
		inv.setItem(9, fill);
		inv.setItem(10, fill);
		inv.setItem(11, fill);
		inv.setItem(12, fill);
		inv.setItem(13, gi);
		inv.setItem(14, fill);
		inv.setItem(15, fill);
		inv.setItem(16, fill);
		inv.setItem(17, fill);
		
		inv.setItem(18, fill);
		inv.setItem(19, fill);
		inv.setItem(20, fill);
		inv.setItem(21, fill);
		inv.setItem(22, fill);
		inv.setItem(23, fill);
		inv.setItem(24, fill);
		inv.setItem(25, fill);
		inv.setItem(26, back);
		
		player.openInventory(inv);
	}
	
	public void openBank(Player player, Company c) {
		inv = Bukkit.createInventory(player, 27, "Bank voor: " + c.getName());
		
		ItemStack fill = new ItemStack(Material.BLACK_STAINED_GLASS_PANE,1);
		ItemMeta fmeta = fill.getItemMeta();
		fmeta.setDisplayName(" ");
		fmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		fmeta.addEnchant(Enchantment.DURABILITY, 1, true);
		fill.setItemMeta(fmeta);
		
		ItemStack fill2 = new ItemStack(Material.BLACK_CONCRETE,1);
		ItemMeta f2meta = fill.getItemMeta();
		f2meta.setDisplayName(" ");
		f2meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		f2meta.addEnchant(Enchantment.DURABILITY, 1, true);
		fill2.setItemMeta(f2meta);
		
		ItemStack gi = new ItemStack(Material.GOLD_INGOT,1);
		ItemMeta gmeta = gi.getItemMeta();
		gmeta.setDisplayName("§eBank");
		ArrayList<String> glore = new ArrayList<String>();
		glore.add("§eBalance: §r$" + String.valueOf(c.getBalance()));
		glore.add("§eBeschikbaar voor uitbetalingen: §r$" + String.valueOf(Companies.getInstance().getCompaniesManager().getFreeCapital(c.getName())));
		gmeta.setLore(glore);
		gi.setItemMeta(gmeta);
		
		ItemStack back = new ItemStack(Material.BARRIER,1);
		ItemMeta bmeta = back.getItemMeta();
		bmeta.setDisplayName(ChatColor.RED + "Ga terug");
		back.setItemMeta(bmeta);
		
		ItemStack plus1 = new ItemStack(Material.EMERALD_BLOCK,1);
		ItemStack plus10 = new ItemStack(Material.EMERALD_BLOCK,1);
		ItemStack plus100 = new ItemStack(Material.EMERALD_BLOCK,1);
		ItemStack plus1000 = new ItemStack(Material.EMERALD_BLOCK,1);
		ItemStack plus10k = new ItemStack(Material.EMERALD_BLOCK,1);
		ItemStack plus100k  = new ItemStack(Material.EMERALD_BLOCK,1);
		ItemStack plus1Mil = new ItemStack(Material.EMERALD_BLOCK,1);
		
		ArrayList<String> l1 = new ArrayList<String>();
		ArrayList<String> l10 = new ArrayList<String>();
		ArrayList<String> l100 = new ArrayList<String>();
		ArrayList<String> l1k = new ArrayList<String>();
 		ArrayList<String> l10k = new ArrayList<String>();
 		ArrayList<String> l100k = new ArrayList<String>();
 		ArrayList<String> l1m = new ArrayList<String>();
 		
 		
 		ItemMeta m1 = plus1.getItemMeta();
 		ItemMeta m10 = plus10.getItemMeta();
 		ItemMeta m100 = plus100.getItemMeta();
 		ItemMeta m1k = plus1000.getItemMeta();
 		ItemMeta m10k = plus10k.getItemMeta();
 		ItemMeta m100k = plus100k.getItemMeta();
 		ItemMeta m1m = plus1Mil.getItemMeta();
 		
 		m1.setDisplayName("§a+ $1");
 		m10.setDisplayName("§a+ $10");
 		m100.setDisplayName("§a+ $100");
 		m1k.setDisplayName("§a+ $1000");
 		m10k.setDisplayName("§a+ $10.000");
 		m100k.setDisplayName("§a+ $100.000");
 		m1m.setDisplayName("§a+ $1.000.000");
 		
 		m1.setLore(l1);
 		m10.setLore(l10);
 		m100.setLore(l100);
 		m1k.setLore(l1k);
 		m10k.setLore(l10k);
 		m100k.setLore(l100k);
 		m1m.setLore(l1m);
 		
 		plus1.setItemMeta(m1);
 		plus10.setItemMeta(m10);
 		plus100.setItemMeta(m100);
 		plus1000.setItemMeta(m1k);
 		plus10k.setItemMeta(m10k);
 		plus100k.setItemMeta(m100k);
 		plus1Mil.setItemMeta(m1m);
 		
 		
		ItemStack min1 = new ItemStack(Material.REDSTONE_BLOCK,1);
		ItemStack min10 = new ItemStack(Material.REDSTONE_BLOCK,1);
		ItemStack min100 = new ItemStack(Material.REDSTONE_BLOCK,1);
		ItemStack min1000 = new ItemStack(Material.REDSTONE_BLOCK,1);
		ItemStack min10k = new ItemStack(Material.REDSTONE_BLOCK,1);
		ItemStack min100k  = new ItemStack(Material.REDSTONE_BLOCK,1);
		ItemStack min1Mil = new ItemStack(Material.REDSTONE_BLOCK,1);
		
		ArrayList<String> lm1 = new ArrayList<String>();
		ArrayList<String> lm10 = new ArrayList<String>();
		ArrayList<String> lm100 = new ArrayList<String>();
		ArrayList<String> lm1k = new ArrayList<String>();
 		ArrayList<String> lm10k = new ArrayList<String>();
 		ArrayList<String> lm100k = new ArrayList<String>();
 		ArrayList<String> lm1m = new ArrayList<String>();
 		
 		
 		ItemMeta lmt1 = min1.getItemMeta();
 		ItemMeta lmt10 = min10.getItemMeta();
 		ItemMeta lmt100 = min100.getItemMeta();
 		ItemMeta lmt1k = min1000.getItemMeta();
 		ItemMeta lmt10k = min10k.getItemMeta();
 		ItemMeta lmt100k = min100k.getItemMeta();
 		ItemMeta lmt1m = min1Mil.getItemMeta();
 		
 		lmt1.setDisplayName("§4- $1");
 		lmt10.setDisplayName("§4- $10");
 		lmt100.setDisplayName("§4- $100");
 		lmt1k.setDisplayName("§4- $1000");
 		lmt10k.setDisplayName("§4- $10.000");
 		lmt100k.setDisplayName("§4- $100.000");
 		lmt1m.setDisplayName("§4- $1.000.000");
 		
 		lmt1.setLore(lm1);
 		lmt10.setLore(lm10);
 		lmt100.setLore(lm100);
 		lmt1k.setLore(lm1k);
 		lmt10k.setLore(lm10k);
 		lmt100k.setLore(lm100k);
 		lmt1m.setLore(lm1m);
 		
 		min1.setItemMeta(lmt1);
 		min10.setItemMeta(lmt10);
 		min100.setItemMeta(lmt100);
 		min1000.setItemMeta(lmt1k);
 		min10k.setItemMeta(lmt10k);
 		min100k.setItemMeta(lmt100k);
 		min1Mil.setItemMeta(lmt1m);
 		
 		if(c.getBalance() < 1) {
			inv.setItem(19, fill2);
			inv.setItem(20, fill2);
			inv.setItem(21, fill2);
			inv.setItem(22, fill2);
			inv.setItem(23, fill2);
			inv.setItem(24, fill2);
			inv.setItem(25, fill2);
		}else if(c.getBalance() >= 1 && c.getBalance() < 10) {
			inv.setItem(19, min1);
			inv.setItem(20, fill2);
			inv.setItem(21, fill2);
			inv.setItem(22, fill2);
			inv.setItem(23, fill2);
			inv.setItem(24, fill2);
			inv.setItem(25, fill2);
			
		}else if(c.getBalance() >= 10 && c.getBalance() < 100) {
			
			inv.setItem(19, min1);
			inv.setItem(20, min10);
			inv.setItem(21, fill2);
			inv.setItem(22, fill2);
			inv.setItem(23, fill2);
			inv.setItem(24, fill2);
			inv.setItem(25, fill2);
			
		}else if(c.getBalance() >= 100 && c.getBalance() < 1000) {
			
			inv.setItem(19, min1);
			inv.setItem(20, min10);
			inv.setItem(21, min100);
			inv.setItem(22, fill2);
			inv.setItem(23, fill2);
			inv.setItem(24, fill2);
			inv.setItem(25, fill2);
			
		}else if(c.getBalance() >= 1000 && c.getBalance() < 10000) {
			inv.setItem(19, min1);
			inv.setItem(20, min10);
			inv.setItem(21, min100);
			inv.setItem(22, min1000);
			inv.setItem(23, fill2);
			inv.setItem(24, fill2);
			inv.setItem(25, fill2);
			
		}else if(c.getBalance() >= 10000 && c.getBalance() < 100000) {
			
			inv.setItem(19, min1);
			inv.setItem(20, min10);
			inv.setItem(21, min100);
			inv.setItem(22, min1000);
			inv.setItem(23, min10k);
			inv.setItem(24, fill2);
			inv.setItem(25, fill2);
			
		}else if(c.getBalance() >= 100000 && c.getBalance() < 1000000) {
			
			inv.setItem(19, min1);
			inv.setItem(20, min10);
			inv.setItem(21, min100);
			inv.setItem(22, min1000);
			inv.setItem(23, min10k);
			inv.setItem(24, min100k);
			inv.setItem(25, fill2);
			
		}else if(c.getBalance() >= 1000000) {
			
			inv.setItem(19, min1);
			inv.setItem(20, min10);
			inv.setItem(21, min100);
			inv.setItem(22, min1000);
			inv.setItem(23, min10k);
			inv.setItem(24, min100k);
			inv.setItem(25, min1Mil);
			
		}else {
			inv.setItem(1, fill2);
			inv.setItem(2, fill2);
			inv.setItem(3, fill2);
			inv.setItem(4, fill2);
			inv.setItem(5, fill2);
			inv.setItem(6, fill2);
			inv.setItem(7, fill2);
		}
		
		
		inv.setItem(1, plus1);
		inv.setItem(2, plus10);
		inv.setItem(3, plus100);
		inv.setItem(4, plus1000);
		inv.setItem(5, plus10k);
		inv.setItem(6, plus100k);
		inv.setItem(7, plus1Mil);
 		
		inv.setItem(0, fill);
		inv.setItem(8, fill);
		inv.setItem(9, fill);
		inv.setItem(10, fill);
		inv.setItem(11, fill);
		inv.setItem(12, fill);
		inv.setItem(13, gi);
		inv.setItem(14, fill);
		inv.setItem(15, fill);
		inv.setItem(16, fill);
		inv.setItem(17, fill);
		inv.setItem(18, fill);
		//19
		//20
		//21
		//22
		//23
		//24
		//25
		inv.setItem(26, back);
		
		player.openInventory(inv);
	}
	
	public void openMStaffW(Player player, Company c, Employee e) {
		inv = Bukkit.createInventory(player, 27, "Loon voor: " + c.getName() + " : " + e.getUuid().toString());
		
		ItemStack fill = new ItemStack(Material.BLACK_STAINED_GLASS_PANE,1);
		ItemMeta fmeta = fill.getItemMeta();
		fmeta.setDisplayName(" ");
		fmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		fmeta.addEnchant(Enchantment.DURABILITY, 1, true);
		fill.setItemMeta(fmeta);
		
		ItemStack fill2 = new ItemStack(Material.BLACK_CONCRETE,1);
		ItemMeta f2meta = fill.getItemMeta();
		f2meta.setDisplayName(" ");
		f2meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		f2meta.addEnchant(Enchantment.DURABILITY, 1, true);
		fill2.setItemMeta(f2meta);
		
		ItemStack gi = new ItemStack(Material.GOLD_INGOT,1);
		ItemMeta gmeta = gi.getItemMeta();
		gmeta.setDisplayName("§eLoon");
		ArrayList<String> glore = new ArrayList<String>();
		glore.add("§eBalance: §r$" + String.valueOf(c.getBalance()));
		glore.add("§eHuidig: §r$" + String.valueOf(e.getNextWage()));
		glore.add(e.getUuid().toString());
		gmeta.setLore(glore);
		gi.setItemMeta(gmeta);
		
		ItemStack back = new ItemStack(Material.BARRIER,1);
		ItemMeta bmeta = back.getItemMeta();
		bmeta.setDisplayName(ChatColor.RED + "Ga terug");
		back.setItemMeta(bmeta);
		
		ItemStack plus1 = new ItemStack(Material.EMERALD_BLOCK,1);
		ItemStack plus10 = new ItemStack(Material.EMERALD_BLOCK,1);
		ItemStack plus100 = new ItemStack(Material.EMERALD_BLOCK,1);
		ItemStack plus1000 = new ItemStack(Material.EMERALD_BLOCK,1);
		ItemStack plus10k = new ItemStack(Material.EMERALD_BLOCK,1);
		ItemStack plus100k  = new ItemStack(Material.EMERALD_BLOCK,1);
		ItemStack plus1Mil = new ItemStack(Material.EMERALD_BLOCK,1);
		
		ArrayList<String> l1 = new ArrayList<String>();
		ArrayList<String> l10 = new ArrayList<String>();
		ArrayList<String> l100 = new ArrayList<String>();
		ArrayList<String> l1k = new ArrayList<String>();
 		ArrayList<String> l10k = new ArrayList<String>();
 		ArrayList<String> l100k = new ArrayList<String>();
 		ArrayList<String> l1m = new ArrayList<String>();
 		
 		
 		ItemMeta m1 = plus1.getItemMeta();
 		ItemMeta m10 = plus10.getItemMeta();
 		ItemMeta m100 = plus100.getItemMeta();
 		ItemMeta m1k = plus1000.getItemMeta();
 		ItemMeta m10k = plus10k.getItemMeta();
 		ItemMeta m100k = plus100k.getItemMeta();
 		ItemMeta m1m = plus1Mil.getItemMeta();
 		
 		m1.setDisplayName("§a+ $1");
 		m10.setDisplayName("§a+ $10");
 		m100.setDisplayName("§a+ $100");
 		m1k.setDisplayName("§a+ $1000");
 		m10k.setDisplayName("§a+ $10.000");
 		m100k.setDisplayName("§a+ $100.000");
 		m1m.setDisplayName("§a+ $1.000.000");
 		
 		m1.setLore(l1);
 		m10.setLore(l10);
 		m100.setLore(l100);
 		m1k.setLore(l1k);
 		m10k.setLore(l10k);
 		m100k.setLore(l100k);
 		m1m.setLore(l1m);
 		
 		plus1.setItemMeta(m1);
 		plus10.setItemMeta(m10);
 		plus100.setItemMeta(m100);
 		plus1000.setItemMeta(m1k);
 		plus10k.setItemMeta(m10k);
 		plus100k.setItemMeta(m100k);
 		plus1Mil.setItemMeta(m1m);
 		
 		
		ItemStack min1 = new ItemStack(Material.REDSTONE_BLOCK,1);
		ItemStack min10 = new ItemStack(Material.REDSTONE_BLOCK,1);
		ItemStack min100 = new ItemStack(Material.REDSTONE_BLOCK,1);
		ItemStack min1000 = new ItemStack(Material.REDSTONE_BLOCK,1);
		ItemStack min10k = new ItemStack(Material.REDSTONE_BLOCK,1);
		ItemStack min100k  = new ItemStack(Material.REDSTONE_BLOCK,1);
		ItemStack min1Mil = new ItemStack(Material.REDSTONE_BLOCK,1);
		
		ArrayList<String> lm1 = new ArrayList<String>();
		ArrayList<String> lm10 = new ArrayList<String>();
		ArrayList<String> lm100 = new ArrayList<String>();
		ArrayList<String> lm1k = new ArrayList<String>();
 		ArrayList<String> lm10k = new ArrayList<String>();
 		ArrayList<String> lm100k = new ArrayList<String>();
 		ArrayList<String> lm1m = new ArrayList<String>();
 		
 		
 		ItemMeta lmt1 = min1.getItemMeta();
 		ItemMeta lmt10 = min10.getItemMeta();
 		ItemMeta lmt100 = min100.getItemMeta();
 		ItemMeta lmt1k = min1000.getItemMeta();
 		ItemMeta lmt10k = min10k.getItemMeta();
 		ItemMeta lmt100k = min100k.getItemMeta();
 		ItemMeta lmt1m = min1Mil.getItemMeta();
 		
 		lmt1.setDisplayName("§4- $1");
 		lmt10.setDisplayName("§4- $10");
 		lmt100.setDisplayName("§4- $100");
 		lmt1k.setDisplayName("§4- $1000");
 		lmt10k.setDisplayName("§4- $10.000");
 		lmt100k.setDisplayName("§4- $100.000");
 		lmt1m.setDisplayName("§4- $1.000.000");
 		
 		lmt1.setLore(lm1);
 		lmt10.setLore(lm10);
 		lmt100.setLore(lm100);
 		lmt1k.setLore(lm1k);
 		lmt10k.setLore(lm10k);
 		lmt100k.setLore(lm100k);
 		lmt1m.setLore(lm1m);
 		
 		min1.setItemMeta(lmt1);
 		min10.setItemMeta(lmt10);
 		min100.setItemMeta(lmt100);
 		min1000.setItemMeta(lmt1k);
 		min10k.setItemMeta(lmt10k);
 		min100k.setItemMeta(lmt100k);
 		min1Mil.setItemMeta(lmt1m);
 		
		if(c.getBalance() >= 1 && c.getBalance() < 10) {
			inv.setItem(1, plus1);
			inv.setItem(2, fill2);
			inv.setItem(3, fill2);
			inv.setItem(4, fill2);
			inv.setItem(5, fill2);
			inv.setItem(6, fill2);
			inv.setItem(7, fill2);
			
		}else if(c.getBalance() >= 10 && c.getBalance() < 100) {
			
			inv.setItem(1, plus1);
			inv.setItem(2, plus10);
			inv.setItem(3, fill2);
			inv.setItem(4, fill2);
			inv.setItem(5, fill2);
			inv.setItem(6, fill2);
			inv.setItem(7, fill2);
			
		}else if(c.getBalance() >= 100 && c.getBalance() < 1000) {
			
			inv.setItem(1, plus1);
			inv.setItem(2, plus10);
			inv.setItem(3, plus100);
			inv.setItem(4, fill2);
			inv.setItem(5, fill2);
			inv.setItem(6, fill2);
			inv.setItem(7, fill2);
			
		}else if(c.getBalance() >= 1000 && c.getBalance() < 10000) {
			inv.setItem(1, plus1);
			inv.setItem(2, plus10);
			inv.setItem(3, plus100);
			inv.setItem(4, plus1000);
			inv.setItem(5, fill2);
			inv.setItem(6, fill2);
			inv.setItem(7, fill2);
			
		}else if(c.getBalance() >= 10000 && c.getBalance() < 100000) {
			
			inv.setItem(1, plus1);
			inv.setItem(2, plus10);
			inv.setItem(3, plus100);
			inv.setItem(4, plus1000);
			inv.setItem(5, plus10k);
			inv.setItem(6, fill2);
			inv.setItem(7, fill2);
			
			
		}else if(c.getBalance() >= 100000 && c.getBalance() < 1000000) {
			
			inv.setItem(1, plus1);
			inv.setItem(2, plus10);
			inv.setItem(3, plus100);
			inv.setItem(4, plus1000);
			inv.setItem(5, plus10k);
			inv.setItem(6, plus100k);
			inv.setItem(7, fill2);
			
			
		}else if(c.getBalance() >= 1000000) {
			
			inv.setItem(1, plus1);
			inv.setItem(2, plus10);
			inv.setItem(3, plus100);
			inv.setItem(4, plus1000);
			inv.setItem(5, plus10k);
			inv.setItem(6, plus100k);
			inv.setItem(7, plus1Mil);
			
			
		}else {
			inv.setItem(1, fill2);
			inv.setItem(2, fill2);
			inv.setItem(3, fill2);
			inv.setItem(4, fill2);
			inv.setItem(5, fill2);
			inv.setItem(6, fill2);
			inv.setItem(7, fill2);
		}
		
		
		inv.setItem(0, fill);
		inv.setItem(8, fill);
		inv.setItem(9, fill);
		inv.setItem(10, fill);
		inv.setItem(11, fill);
		inv.setItem(12, fill);
		inv.setItem(13, gi);
		inv.setItem(14, fill);
		inv.setItem(15, fill);
		inv.setItem(16, fill);
		inv.setItem(17, fill);
		inv.setItem(18, fill);
		//19
		//20
		//21
		//22
		//23
		//24
		//25
		
		if(e.getNextWage() >= 1 && e.getNextWage() < 10) {
			inv.setItem(19, min1);
			inv.setItem(20, fill2);
			inv.setItem(21, fill2);
			inv.setItem(22, fill2);
			inv.setItem(23, fill2);
			inv.setItem(24, fill2);
			inv.setItem(25, fill2);
		}else if(e.getNextWage() >= 10  && e.getNextWage() < 100) {
			inv.setItem(19, min1);
			inv.setItem(20, min10);
			inv.setItem(21, fill2);
			inv.setItem(22, fill2);
			inv.setItem(23, fill2);
			inv.setItem(24, fill2);
			inv.setItem(25, fill2);
		}else if(e.getNextWage() >= 100 && e.getNextWage() < 1000) {
			inv.setItem(19, min1);
			inv.setItem(20, min10);
			inv.setItem(21, min100);
			inv.setItem(22, fill2);
			inv.setItem(23, fill2);
			inv.setItem(24, fill2);
			inv.setItem(25, fill2);
		}else if(e.getNextWage() >= 1000 && e.getNextWage() < 10000) {
			inv.setItem(19, min1);
			inv.setItem(20, min10);
			inv.setItem(21, min100);
			inv.setItem(22, min1000);
			inv.setItem(23, fill2);
			inv.setItem(24, fill2);
			inv.setItem(25, fill2);
		}else if(e.getNextWage() >= 10000 && e.getNextWage() < 100000) {
			inv.setItem(19, min1);
			inv.setItem(20, min10);
			inv.setItem(21, min100);
			inv.setItem(22, min1000);
			inv.setItem(23, min10k);
			inv.setItem(24, fill2);
			inv.setItem(25, fill2);
		}else if(e.getNextWage() >= 100000 && e.getNextWage() < 1000000) {
			inv.setItem(19, min1);
			inv.setItem(20, min10);
			inv.setItem(21, min100);
			inv.setItem(22, min1000);
			inv.setItem(23, min10k);
			inv.setItem(24, min100k);
			inv.setItem(25, fill2);
		}else if(e.getNextWage() >= 1000000) {
			inv.setItem(19, min1);
			inv.setItem(20, min10);
			inv.setItem(21, min100);
			inv.setItem(22, min1000);
			inv.setItem(23, min10k);
			inv.setItem(24, min100k);
			inv.setItem(25, min1Mil);
		}else {
			inv.setItem(19, fill2);
			inv.setItem(20, fill2);
			inv.setItem(21, fill2);
			inv.setItem(22, fill2);
			inv.setItem(23, fill2);
			inv.setItem(24, fill2);
			inv.setItem(25, fill2);
		}
		
		
		
		inv.setItem(26, back);
		
		player.openInventory(inv);
	}
	
	public void openMStaff(Player player, Company c, Employee e) {
		inv = Bukkit.createInventory(player, 27, "Opties voor: " + c.getName() + " : " + e.getName());
		// 0-9
		// 17-18
		// 26-35
		
		ItemStack back = new ItemStack(Material.BARRIER,1);
		ItemMeta bmeta = back.getItemMeta();
		bmeta.setDisplayName(ChatColor.RED + "Ga terug");
		back.setItemMeta(bmeta);
		
		ItemStack fill = new ItemStack(Material.BLACK_STAINED_GLASS_PANE,1);
		ItemMeta fmeta = fill.getItemMeta();
		fmeta.setDisplayName("");
		fmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		fmeta.addEnchant(Enchantment.DURABILITY, 1, true);
		fill.setItemMeta(fmeta);
		
		ItemStack i = new ItemStack(Material.PLAYER_HEAD,1);
		SkullMeta iim = (SkullMeta) i.getItemMeta();
		iim.setOwningPlayer(Bukkit.getPlayer(e.getUuid()));
		iim.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		iim.addEnchant(Enchantment.DURABILITY, 1, true);
		iim.setDisplayName(ChatColor.YELLOW + e.getName());
		ArrayList<String> iil = new ArrayList<String>();
		iil.add(ChatColor.YELLOW + "Rank: " + ChatColor.WHITE + e.getGroup().toString());
		iil.add(ChatColor.YELLOW + "Loon: $" + ChatColor.WHITE + String.valueOf(e.getNextWage()));
		iim.setLore(iil);
		i.setItemMeta(iim);
		
		ItemStack wage = new ItemStack(Material.GOLD_INGOT,1);
		ItemMeta wmeta = wage.getItemMeta();
		wmeta.setDisplayName("§aLoon");
		ArrayList<String> wl = new ArrayList<String>();
		wl.add("§aHuidig: §r" + String.valueOf(e.getNextWage()));
		wl.add(e.getUuid().toString());
		wmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		wmeta.setLore(wl);
		wmeta.addEnchant(Enchantment.DURABILITY, 1, true);
		wage.setItemMeta(wmeta);
		
		ItemStack promote = new ItemStack(Material.EMERALD_BLOCK,1);
		ItemMeta pmeta = promote.getItemMeta();
		pmeta.setDisplayName("§aPromote");
		pmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		pmeta.addEnchant(Enchantment.DURABILITY, 1, true);
		promote.setItemMeta(pmeta);
		
		ItemStack demote = new ItemStack(Material.REDSTONE_BLOCK,1);
		ItemMeta dmeta = demote.getItemMeta();
		dmeta.setDisplayName("§4Demote");
		dmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		dmeta.addEnchant(Enchantment.DURABILITY, 1, true);
		demote.setItemMeta(dmeta);
		
		ItemStack kick = new ItemStack(Material.REDSTONE_BLOCK,1);
		ItemMeta kmeta = kick.getItemMeta();
		kmeta.setDisplayName("§4Kick speler");
		kmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		kmeta.addEnchant(Enchantment.DURABILITY, 1, true);
		kick.setItemMeta(kmeta);
		
		
		inv.setItem(0, fill);
		inv.setItem(1, fill);
		inv.setItem(2, fill);
		inv.setItem(3, fill);
		inv.setItem(4, fill);
		inv.setItem(5, fill);
		inv.setItem(6, fill);
		inv.setItem(7, fill);
		inv.setItem(8, fill);
		inv.setItem(9, fill);
		
		inv.setItem(10, i);
		inv.setItem(11, fill);
		inv.setItem(12, wage);
		inv.setItem(13, promote);
		inv.setItem(14, demote);
		inv.setItem(15, fill);
		inv.setItem(16, kick);
		
		
		inv.setItem(17, fill);
		inv.setItem(18, fill);
		inv.setItem(19, fill);
		inv.setItem(20, fill);
		inv.setItem(21, fill);
		inv.setItem(22, fill);
		inv.setItem(23, fill);
		inv.setItem(24, fill);
		inv.setItem(25, fill);
		inv.setItem(26, back);
		
		player.openInventory(inv);
	}
	
	public void openStaff(Player player, Company c) {
		inv = Bukkit.createInventory(player, 36, "Werknemers voor: " + c.getName());
		// 0-9
		// 17-18
		// 26-35
		
		ItemStack back = new ItemStack(Material.BARRIER,1);
		ItemMeta bmeta = back.getItemMeta();
		bmeta.setDisplayName(ChatColor.RED + "Ga terug");
		back.setItemMeta(bmeta);
		
		ItemStack fill = new ItemStack(Material.BLACK_STAINED_GLASS_PANE,1);
		ItemMeta fmeta = fill.getItemMeta();
		fmeta.setDisplayName(" ");
		fmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		fmeta.addEnchant(Enchantment.DURABILITY, 1, true);
		fill.setItemMeta(fmeta);
		
		inv.setItem(0, fill);
		inv.setItem(1, fill);
		inv.setItem(2, fill);
		inv.setItem(3, fill);
		inv.setItem(4, fill);
		inv.setItem(5, fill);
		inv.setItem(6, fill);
		inv.setItem(7, fill);
		inv.setItem(8, fill);
		inv.setItem(9, fill);
		
		
		inv.setItem(17, fill);
		inv.setItem(18, fill);
		inv.setItem(19, fill);
		inv.setItem(20, fill);
		inv.setItem(21, fill);
		inv.setItem(22, fill);
		inv.setItem(23, fill);
		inv.setItem(24, fill);
		inv.setItem(25, fill);
		inv.setItem(26, fill);
		inv.setItem(27, fill);
		inv.setItem(28, fill);
		inv.setItem(29, fill);
		inv.setItem(30, fill);
		inv.setItem(31, fill);
		inv.setItem(32, fill);
		inv.setItem(33, fill);
		inv.setItem(34, fill);
		inv.setItem(35, back);
		
		for(Employee e : Companies.getInstance().getEmployeesManager().getEmployeesByCompany(c.getName())) {
			ItemStack i = new ItemStack(Material.PLAYER_HEAD,1);
			SkullMeta iim = (SkullMeta) i.getItemMeta();
			iim.setOwningPlayer(Bukkit.getOfflinePlayer(e.getUuid()));
			iim.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			iim.addEnchant(Enchantment.DURABILITY, 1, true);
			iim.setDisplayName(e.getName());
			ArrayList<String> iil = new ArrayList<String>();
			iil.add(ChatColor.YELLOW + "Rank: " + ChatColor.WHITE + e.getGroup().toString());
			iil.add(ChatColor.YELLOW + "Loon: $" + ChatColor.WHITE + String.valueOf(e.getNextWage()));
			iil.add(e.getUuid().toString());
			iim.setLore(iil);
			i.setItemMeta(iim);
			
			inv.addItem(i);
		}
		
		
		player.openInventory(inv);
	}
	
	
	public void openTransfer(Player player, Company c) {
		inv = Bukkit.createInventory(player, 36, "Bedrijf overzetten: " + c.getName());
		// 0-9
		// 17-18
		// 26-35
		
		ItemStack back = new ItemStack(Material.BARRIER,1);
		ItemMeta bmeta = back.getItemMeta();
		bmeta.setDisplayName(ChatColor.RED + "Ga terug");
		back.setItemMeta(bmeta);
		
		ItemStack fill = new ItemStack(Material.BLACK_STAINED_GLASS_PANE,1);
		ItemMeta fmeta = fill.getItemMeta();
		fmeta.setDisplayName(" ");
		fmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		fmeta.addEnchant(Enchantment.DURABILITY, 1, true);
		fill.setItemMeta(fmeta);
		
		inv.setItem(0, fill);
		inv.setItem(1, fill);
		inv.setItem(2, fill);
		inv.setItem(3, fill);
		inv.setItem(4, fill);
		inv.setItem(5, fill);
		inv.setItem(6, fill);
		inv.setItem(7, fill);
		inv.setItem(8, fill);
		inv.setItem(9, fill);
		
		
		inv.setItem(17, fill);
		inv.setItem(18, fill);
		inv.setItem(26, fill);
		inv.setItem(27, fill);
		inv.setItem(28, fill);
		inv.setItem(29, fill);
		inv.setItem(30, fill);
		inv.setItem(31, fill);
		inv.setItem(32, fill);
		inv.setItem(33, fill);
		inv.setItem(34, fill);
		inv.setItem(35, back);
		
		for(Player p : Bukkit.getOnlinePlayers()) {
			if(!p.getUniqueId().toString().equals(c.getOwnerUUID())) {
				ItemStack i = new ItemStack(Material.PLAYER_HEAD,1);
				SkullMeta iim = (SkullMeta) i.getItemMeta();
				iim.setOwningPlayer(p);
				iim.addItemFlags(ItemFlag.HIDE_ENCHANTS);
				iim.addEnchant(Enchantment.DURABILITY, 1, true);
				iim.setDisplayName(p.getName());
				ArrayList<String> iil = new ArrayList<String>();
				iil.add(p.getUniqueId().toString());
				iim.setLore(iil);
				i.setItemMeta(iim);
				
				inv.addItem(i);
			}
		}
		
		
		player.openInventory(inv);
	}
	
	public void openReviews(Player player, Company c) {
		inv = Bukkit.createInventory(player, 36, "Reviews voor: " + c.getName());
		// 0-9
		// 17-18
		// 26-35
		
		ItemStack back = new ItemStack(Material.BARRIER,1);
		ItemMeta bmeta = back.getItemMeta();
		bmeta.setDisplayName(ChatColor.RED + "Ga terug");
		back.setItemMeta(bmeta);
		
		ItemStack fill = new ItemStack(Material.BLACK_STAINED_GLASS_PANE,1);
		ItemMeta fmeta = fill.getItemMeta();
		fmeta.setDisplayName(" ");
		fmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		fmeta.addEnchant(Enchantment.DURABILITY, 1, true);
		fill.setItemMeta(fmeta);
		
		inv.setItem(0, fill);
		inv.setItem(1, fill);
		inv.setItem(2, fill);
		inv.setItem(3, fill);
		inv.setItem(4, fill);
		inv.setItem(5, fill);
		inv.setItem(6, fill);
		inv.setItem(7, fill);
		inv.setItem(8, fill);
		inv.setItem(9, fill);
		
		
		inv.setItem(17, fill);
		inv.setItem(18, fill);
		
		inv.setItem(26, fill);
		inv.setItem(27, fill);
		inv.setItem(28, fill);
		inv.setItem(29, fill);
		inv.setItem(30, fill);
		inv.setItem(31, fill);
		inv.setItem(32, fill);
		inv.setItem(33, fill);
		inv.setItem(34, fill);
		inv.setItem(35, back);
		
		for(Review r : Companies.getInstance().getReviewsManager().getReviewsByCompany(c.getName())) {
			ItemStack i = new ItemStack(Material.BOOK,1);
			ItemMeta iim = i.getItemMeta();
			iim.addItemFlags(ItemFlag.HIDE_ENCHANTS);
			iim.addEnchant(Enchantment.DURABILITY, 1, true);
			iim.setDisplayName(ChatColor.YELLOW + r.getAuthor());
			ArrayList<String> iil = new ArrayList<String>();
			iil.add(ChatColor.YELLOW + "Rating: " + ChatColor.WHITE + String.valueOf(r.getStars()) + "/10");
			iil.add(ChatColor.YELLOW + "Text: " + ChatColor.WHITE + r.getShortText());
			iim.setLore(iil);
			i.setItemMeta(iim);
			
			inv.addItem(i);
		}
		
		
		player.openInventory(inv);
		
	}
	
	
	
	public void openGUI(Player player, Company company) {
		inv = Bukkit.createInventory(player, 27, "§dBedrijfsmenu voor: " + company.getName());
		
		/********************************
		 *
		 * 		ItemStacks & Meta
		 * 
		********************************/
		
		
		ItemStack forum = new ItemStack(Material.BOOK,1);
		ItemStack transfer = new ItemStack(Material.BOOK,1);
		ItemStack sollicitaties = new ItemStack(Material.WRITABLE_BOOK,1);
		ItemStack bank = new ItemStack(Material.PLAYER_HEAD,1);
		ItemStack eufd = new ItemStack(Material.PLAYER_HEAD,1);
		ItemStack sethq = new ItemStack(Material.PLAYER_HEAD,1);
		ItemStack reviews = new ItemStack(Material.PAPER,1);
		ItemStack delete = new ItemStack(Material.BARRIER,1);
		
		ItemMeta tmmeta = transfer.getItemMeta();
		tmmeta.setDisplayName("Bedrijf overzetten");
		transfer.setItemMeta(tmmeta);
		
		
		ItemMeta forumMeta = forum.getItemMeta();
		forumMeta.setDisplayName("§3Forum");
		ArrayList<String> forumLore = new ArrayList<String>();
		forumLore.add("Link naar het forum");
		forumMeta.setLore(forumLore);
		forum.setItemMeta(forumMeta);
		
		ItemMeta sollicitatieMeta = sollicitaties.getItemMeta();
		sollicitatieMeta.setDisplayName("§aSollicitaties");
		ArrayList<String> solliLore = new ArrayList<String>();
		solliLore.add("Bekijk de sollicitanten");
		sollicitatieMeta.setLore(solliLore);
		sollicitaties.setItemMeta(sollicitatieMeta);
		
		SkullMeta bankMeta = (SkullMeta)bank.getItemMeta();
		bankMeta.setOwningPlayer(Bukkit.getOfflinePlayer(UUID.fromString("abbbbfea-454a-4467-85b2-7d719e62d52e")));
		bankMeta.setDisplayName("§eBank");
		ArrayList<String> bankLore = new ArrayList<String>();
		bankLore.add("Bekijk de bank!");
		bankMeta.setLore(bankLore);
		bank.setItemMeta(bankMeta);
		
		SkullMeta psmeta = (SkullMeta)eufd.getItemMeta();
		psmeta.setOwningPlayer(Bukkit.getOfflinePlayer(player.getUniqueId()));
		psmeta.setDisplayName("§dPersoneel");
		ArrayList<String> pslore = new ArrayList<String>();
		pslore.add("Bekijk het personeel");
		psmeta.setLore(pslore);
		eufd.setItemMeta(psmeta);
		
		SkullMeta hqmeta = (SkullMeta)sethq.getItemMeta();
		hqmeta.setDisplayName("§9Bedrijf");
		hqmeta.setOwningPlayer(Bukkit.getOfflinePlayer(UUID.fromString("f3f5123e-5089-4ce7-a00c-0895ca2573db")));
		ArrayList<String> hqlore = new ArrayList<String>();
		hqlore.add("Set HQ");
		hqmeta.setLore(hqlore);
		sethq.setItemMeta(hqmeta);
		
		ItemMeta rvmeta = reviews.getItemMeta();
		rvmeta.setDisplayName("§6Reviews");
		ArrayList<String> rvlore = new ArrayList<String>();
		rvlore.add("Klik om de reviews te bekijken");
		rvmeta.setLore(rvlore);
		reviews.setItemMeta(rvmeta);
		
		
		ItemMeta delmeta = delete.getItemMeta();
		delmeta.setDisplayName("§4Verwijder Bedrijf!");
		ArrayList<String> delLore = new ArrayList<String>();
		delLore.add("Klik om je bedrijf te verwijderen!");
		delmeta.setLore(delLore);
		delete.setItemMeta(delmeta);
		
		/*******************************
		*
		*	Item Positions
		*
		********************************/
		
		inv.setItem(0, forum);
		inv.setItem(1, lfiller);
		inv.setItem(2, whfiller);
		inv.setItem(3, pufiller);
		inv.setItem(4, sollicitaties);
		inv.setItem(5, pifiller);
		inv.setItem(6, pufiller);
		inv.setItem(7, whfiller);
		inv.setItem(8, pufiller);
		
		/********************************/
		
		inv.setItem(9, pifiller);
		inv.setItem(10, pifiller);
		inv.setItem(11, lfiller);
		inv.setItem(12, bank);
		inv.setItem(13, eufd);
		inv.setItem(14, sethq);
		inv.setItem(15, pufiller);
		inv.setItem(16, pufiller);
		inv.setItem(17, pufiller);
		
		/*****************************/
		
		inv.setItem(18, transfer);
		inv.setItem(19, lfiller);
		inv.setItem(20, whfiller);
		inv.setItem(21, whfiller);
		inv.setItem(22, reviews);
		inv.setItem(23, pifiller);
		inv.setItem(24, pifiller);
		inv.setItem(25, pifiller);
		inv.setItem(26, delete);
		
		// open inv
		player.openInventory(inv);
		
	}
	
	public void openConfirm(Player player, Company company, Modus m, UUID e) {
		if(m.equals(Modus.DISBAND)) {
			inv = Bukkit.createInventory(player, 27, "§5Bedrijf stoppen: " + company.getName());
			
			
			ItemStack fill = new ItemStack(Material.BLACK_STAINED_GLASS_PANE,1);
			ItemMeta fmeta = fill.getItemMeta();
			fmeta.setDisplayName(" ");
			fill.setItemMeta(fmeta);
			
			ItemStack yes = new ItemStack(Material.EMERALD_BLOCK,1);
			ItemMeta ymeta = yes.getItemMeta();
			ymeta.setDisplayName(ChatColor.DARK_GREEN + "Bedrijf stoppen");
			ArrayList<String> ylore = new ArrayList<String>();
			ylore.add(ChatColor.GREEN + "Klik hier om je bedrijf te stoppen!");
			ymeta.setLore(ylore);
			yes.setItemMeta(ymeta);
			
			ItemStack no = new ItemStack(Material.REDSTONE_BLOCK, 1);
			ItemMeta nmeta = no.getItemMeta();
			nmeta.setDisplayName(ChatColor.DARK_RED + "Ga terug!");
			ArrayList<String> nlore = new ArrayList<String>();
			nlore.add(ChatColor.RED + "Klik hier om terug te gaan!");
			nmeta.setLore(nlore);
			no.setItemMeta(nmeta);
			
			inv.setItem(0, fill);
			inv.setItem(1, fill);
			inv.setItem(2, fill);
			inv.setItem(3, fill);
			inv.setItem(4, fill);
			inv.setItem(5, fill);
			inv.setItem(6, fill);
			inv.setItem(7, fill);
			inv.setItem(8, fill);
			
			inv.setItem(9, fill);
			inv.setItem(10, fill);
			inv.setItem(11, fill);
			inv.setItem(12, yes);
			inv.setItem(13, fill);
			inv.setItem(14, no);
			inv.setItem(15, fill);
			inv.setItem(16, fill);
			inv.setItem(17, fill);
			
			inv.setItem(18, fill);
			inv.setItem(19, fill);
			inv.setItem(20, fill);
			inv.setItem(21, fill);
			inv.setItem(22, fill);
			inv.setItem(23, fill);
			inv.setItem(24, fill);
			inv.setItem(25, fill);
			inv.setItem(26, fill);
			
			
			player.openInventory(inv);
		}else if(m.equals(Modus.HIRE)) {
			inv = Bukkit.createInventory(player, 27, "§5Oordelen voor: " + company.getName() + " : " + MojangAPI.getName(e.toString()));
			
			
			ItemStack fill = new ItemStack(Material.BLACK_STAINED_GLASS_PANE,1);
			ItemMeta fmeta = fill.getItemMeta();
			fmeta.setDisplayName(" ");
			fill.setItemMeta(fmeta);
			
			ItemStack yes = new ItemStack(Material.EMERALD_BLOCK,1);
			ItemMeta ymeta = yes.getItemMeta();
			ymeta.setDisplayName(ChatColor.DARK_GREEN + "Aannemen");
			ArrayList<String> ylore = new ArrayList<String>();
			ylore.add(ChatColor.GREEN + "Klik hier om de sollicitatie goed te keuren!");
			ymeta.setLore(ylore);
			yes.setItemMeta(ymeta);
			
			ItemStack no = new ItemStack(Material.REDSTONE_BLOCK, 1);
			ItemMeta nmeta = no.getItemMeta();
			nmeta.setDisplayName(ChatColor.DARK_RED + "Weigeren");
			ArrayList<String> nlore = new ArrayList<String>();
			nlore.add(ChatColor.RED + "Klik hier om de sollicitatie te weigeren!");
			nmeta.setLore(nlore);
			no.setItemMeta(nmeta);
			
			
			ItemStack back = new ItemStack(Material.BARRIER,1);
			ItemMeta bmeta = back.getItemMeta();
			bmeta.setDisplayName(ChatColor.RED + "Ga terug");
			back.setItemMeta(bmeta);
			
			inv.setItem(0, fill);
			inv.setItem(1, fill);
			inv.setItem(2, fill);
			inv.setItem(3, fill);
			inv.setItem(4, fill);
			inv.setItem(5, fill);
			inv.setItem(6, fill);
			inv.setItem(7, fill);
			inv.setItem(8, fill);
			
			inv.setItem(9, fill);
			inv.setItem(10, fill);
			inv.setItem(11, fill);
			inv.setItem(12, yes);
			inv.setItem(13, fill);
			inv.setItem(14, no);
			inv.setItem(15, fill);
			inv.setItem(16, fill);
			inv.setItem(17, fill);
			
			inv.setItem(18, fill);
			inv.setItem(19, fill);
			inv.setItem(20, fill);
			inv.setItem(21, fill);
			inv.setItem(22, fill);
			inv.setItem(23, fill);
			inv.setItem(24, fill);
			inv.setItem(25, fill);
			inv.setItem(26, back);
			
			player.openInventory(inv);
		}else if(m.equals(Modus.BANK)) {
			inv = Bukkit.createInventory(player, 27, "§5Selectie voor: " + company.getName());
			
			
			ItemStack fill = new ItemStack(Material.BLACK_STAINED_GLASS_PANE,1);
			ItemMeta fmeta = fill.getItemMeta();
			fmeta.setDisplayName(" ");
			fill.setItemMeta(fmeta);
			
			ItemStack yes = new ItemStack(Material.EMERALD,1);
			ItemMeta ymeta = yes.getItemMeta();
			ymeta.setDisplayName(ChatColor.DARK_GREEN + "Kapitaal");
			ArrayList<String> ylore = new ArrayList<String>();
			ylore.add(ChatColor.GREEN + "Geld voor loonsuitbetalingen!");
			ylore.add(ChatColor.RED + "Let op! Als dit op 0 komt te staan kan je bedrijf bankroet gaan!");
			ymeta.setLore(ylore);
			yes.setItemMeta(ymeta);
			
			ItemStack no = new ItemStack(Material.DIAMOND, 1);
			ItemMeta nmeta = no.getItemMeta();
			nmeta.setDisplayName(ChatColor.DARK_GREEN + "Bank");
			ArrayList<String> nlore = new ArrayList<String>();
			nlore.add(ChatColor.GREEN + "Geldbeheer voor je bedrijf!");
			nmeta.setLore(nlore);
			no.setItemMeta(nmeta);
			
			
			ItemStack back = new ItemStack(Material.BARRIER,1);
			ItemMeta bmeta = back.getItemMeta();
			bmeta.setDisplayName(ChatColor.RED + "Ga terug");
			back.setItemMeta(bmeta);
			
			inv.setItem(0, fill);
			inv.setItem(1, fill);
			inv.setItem(2, fill);
			inv.setItem(3, fill);
			inv.setItem(4, fill);
			inv.setItem(5, fill);
			inv.setItem(6, fill);
			inv.setItem(7, fill);
			inv.setItem(8, fill);
			
			inv.setItem(9, fill);
			inv.setItem(10, fill);
			inv.setItem(11, fill);
			inv.setItem(12, yes);
			inv.setItem(13, fill);
			inv.setItem(14, no);
			inv.setItem(15, fill);
			inv.setItem(16, fill);
			inv.setItem(17, fill);
			
			inv.setItem(18, fill);
			inv.setItem(19, fill);
			inv.setItem(20, fill);
			inv.setItem(21, fill);
			inv.setItem(22, fill);
			inv.setItem(23, fill);
			inv.setItem(24, fill);
			inv.setItem(25, fill);
			inv.setItem(26, back);
			
			player.openInventory(inv);
		}
	}

}
