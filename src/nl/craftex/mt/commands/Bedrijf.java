package nl.craftex.mt.commands;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import eu.mrapik.companies.Companies;
import eu.mrapik.companies.company.Company;
import eu.mrapik.companies.employee.Employee;
import eu.mrapik.companies.employee.Group;
import net.md_5.bungee.api.ChatColor;
import nl.craftex.mt.Main;
import nl.craftex.mt.gui.FrontMenu;
import nl.craftex.mt.util.Utils;

public class Bedrijf implements CommandExecutor{

	/*
	 * 
	 *  /bedrijf
	 *  
	 * 		start (naam) //args0+1 -
	 *		stop (naam) //args0+1 -
	 * 		
	 * 		mederwerker (bedrijf) //args0+1 -
	 * 			aannemen (naam) //args2+3 //4 -
	 * 			ontsla (naam) //args2+3 //4 -
	 * 			lijst // args2 -
	 * 			promote (naam) // - 
	 * 			demote (naam) // - 
	 * 			
	 * 		bank (bedrijfsnaam) <- returns balance and further command help // args0+1 -
	 * 			stort (bedrag) //args2+3 //4 -
	 * 			pin	(bedrag) //args2+3 //4 -
	 * 		
	 * 		solliciteer (bedrijf) (solli) - 
	 * 		review (bedrijf) (1-10) (comment) -
	 * 			
	 * */
	FrontMenu fm = new FrontMenu();
	
	Random r = new Random();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Brudda, do you know di wae?!");
		}else {
			Player p = (Player)sender;
			if(cmd.getName().equalsIgnoreCase("bedrijf")) {
				if(args.length == 0) {
					//sender.sendMessage(ChatColor.RED + "|---[Bedrijf Help]---|");
					//sender.sendMessage(ChatColor.GRAY + "/bedrijf start (bedrijf)");
					//sender.sendMessage(ChatColor.GRAY + "/bedrijf stop (bedrijf)");
					//sender.sendMessage(ChatColor.GRAY + "/bedrijf medewerker (bedrijf)");
					//sender.sendMessage(ChatColor.GRAY + "/bedrijf betaal (bedrijf) (bedrag)");
					//sender.sendMessage(ChatColor.GRAY + "/bedrijf bank (bedrijf)");
					//sender.sendMessage(ChatColor.GRAY + "/bedrijf solliciteer (bedrijf) (bericht)");
					//sender.sendMessage(ChatColor.GRAY + "/bedrijf review (bedrijf) (1-10)");
					if(!Companies.getInstance().getCompaniesManager().getCompaniesByPlayer(p.getUniqueId()).isEmpty()) {
						fm.openList(p);
					}else if(!Companies.getInstance().getCompaniesManager().getCompaniesByEmployee(p.getUniqueId()).isEmpty()) {
						 for(Company c : Companies.getInstance().getCompaniesManager().getCompaniesByEmployee(p.getUniqueId())) {
							 Employee emp = Companies.getInstance().getEmployeesManager().getEmployee(p.getUniqueId(), c.getName());
							 if(emp.getGroup().equals(Group.MANAGER)) {
								 fm.openList(p);
							 }
						 }
					}else if(!Companies.getInstance().getCompaniesManager().getCompaniesByPlayer(p.getUniqueId()).isEmpty() && !Companies.getInstance().getCompaniesManager().getCompaniesByEmployee(p.getUniqueId()).isEmpty()) {
						fm.openList(p);
					}else{
						p.sendMessage(ChatColor.RED + "Je hebt nog geen bedrijf.");
						p.sendMessage(ChatColor.RED + "Gebruik: /bedrijf start (naam) om een bedrijf te starten!");
					}
				}else if(args.length == 1) {
					if(args[0].equalsIgnoreCase("start")) {
						sender.sendMessage(ChatColor.RED + "Gebruik: /bedrijf start (naam)");
					}else if(args[0].equalsIgnoreCase("stop")) {
						sender.sendMessage(ChatColor.RED + "Gebruik: /bedrijf stop (naam)");
					}else if(args[0].equalsIgnoreCase("medewerker") || args[0].equalsIgnoreCase("slaaf")) {
						sender.sendMessage(ChatColor.RED + "|---[Medewerker Help]---|");
						sender.sendMessage(ChatColor.GRAY + "/bedrijf medewerker (bedrijf) lijst");
						sender.sendMessage(ChatColor.GRAY + "/bedrijf medewerker (bedrijf) aannemen (naam)");
						sender.sendMessage(ChatColor.GRAY + "/bedrijf medewerker (bedrijf) ontsla (naam)");
						sender.sendMessage(ChatColor.GRAY + "/bedrijf medewerker (bedrijf) functie (naam) (rank)");
					}else if(args[0].equalsIgnoreCase("bank")) {
						sender.sendMessage(ChatColor.RED + "|---[Bank Help]---|");
						sender.sendMessage(ChatColor.GRAY + "/bedrijf bank (bedrijf) stort (bedrag)");
						sender.sendMessage(ChatColor.GRAY + "/bedrijf bank (bedrijf) pin (bedrag)");
					}else if(args[0].equalsIgnoreCase("review")) {
						sender.sendMessage(ChatColor.RED + "Gebruik: /bedrijf review (bedrijf) (1-10)");
					}else if(args[0].equalsIgnoreCase("solliciteer")) {
						sender.sendMessage(ChatColor.RED + "Gebruik: /bedrijf solliciteer (bedrijf) (solliciatie)");
					}else if(args[0].equalsIgnoreCase("betaal")) {
						sender.sendMessage(ChatColor.RED + "Gebruik: /bedrijf betaal (bedrijf) (bedrag)");
					}else if(args[0].equalsIgnoreCase("help")) {
						sender.sendMessage(ChatColor.RED + "|---[Bedrijf Help]---|");
						sender.sendMessage(ChatColor.GRAY + "/bedrijf start (bedrijf)");
						sender.sendMessage(ChatColor.GRAY + "/bedrijf stop (bedrijf)");
						sender.sendMessage(ChatColor.GRAY + "/bedrijf medewerker (bedrijf)");
						sender.sendMessage(ChatColor.GRAY + "/bedrijf betaal (bedrijf) (bedrag)");
						sender.sendMessage(ChatColor.GRAY + "/bedrijf bank (bedrijf)");
						sender.sendMessage(ChatColor.GRAY + "/bedrijf solliciteer (bedrijf) (bericht)");
						sender.sendMessage(ChatColor.GRAY + "/bedrijf review (bedrijf) (1-10)");
					}
				}else if(args.length == 2) {
					if(args[0].equalsIgnoreCase("start")) {
						if(Main.econ.getBalance(p) >= 5000) {
							Company comp = Companies.getInstance().getCompaniesManager().getCompany(args[1]);
							if(comp != null) {
								p.sendMessage(ChatColor.RED + "Dit bedrijf bestaat al!");
							}else {
								Bukkit.dispatchCommand(p, "cm create " + args[1] + " 5000");
								//Companies.getInstance().getCompaniesController().createCompany(p, 5000, args[1]);
								ItemStack i = new ItemStack(Material.LEATHER,1);
								ItemMeta im = i.getItemMeta();
								im.setDisplayName(ChatColor.GOLD + "Kvk pass");
								ArrayList<String> lore = new ArrayList<String>();
								Random r = new Random();
								int num1 = r.nextInt(9999);
								int num2 = r.nextInt(99);
								int num3 = r.nextInt(999);
								String kvk = String.valueOf(num1)+"-"+String.valueOf(num2)+"-"+String.valueOf(num3);
								lore.add("§7Bedrijf: §6" + args[1]);
								lore.add("§7Kvk: §6" + kvk);
								im.setLore(lore);
								i.setItemMeta(im);
								p.getInventory().addItem(i);
							}
						} else {
							p.sendMessage(ChatColor.RED + "Je hebt $5000 nodig om een bedrijf te starten!");
						}
					}else if(args[0].equalsIgnoreCase("stop")) {
						Bukkit.dispatchCommand(sender, "cm disband " + args[1]);
					}else if(args[0].equalsIgnoreCase("medewerker") || args[0].equalsIgnoreCase("slaaf")) {
						Company comp = Companies.getInstance().getCompaniesManager().getCompany(args[1]);
						if(comp != null) {
							Company c = Companies.getInstance().getCompaniesManager().getCompany(args[1]);
							if(c.getOwnerUUID().equals(p.getUniqueId().toString())) {
								sender.sendMessage(ChatColor.RED + "|----[Medewerker  help]----|");
								sender.sendMessage(ChatColor.GRAY + "/bedrijf medewerker (bedrijf) lijst");
								sender.sendMessage(ChatColor.GRAY + "/bedrijf medewerker (bedrijf) aannemen (speler)");
								sender.sendMessage(ChatColor.GRAY + "/bedrijf medewerker (bedrijf) ontsla (speler)");
								sender.sendMessage(ChatColor.GRAY + "/bedrijf medewerker (bedrijf) functie (naam) (rank)");
							}else {
								sender.sendMessage(ChatColor.RED + "Je bent geen eigenaar van dit bedrijf!");
							}
						} else {
							sender.sendMessage(ChatColor.RED + "Ongeldig bedrijf!");
						}
					}else if(args[0].equalsIgnoreCase("bank")) {
						Company comp = Companies.getInstance().getCompaniesManager().getCompany(args[1]);
						if(comp != null) {
							Company c  = Companies.getInstance().getCompaniesManager().getCompany(args[1]);
							if(p.getUniqueId().toString().equals(c.getOwnerUUID())) {
								sender.sendMessage(ChatColor.RED + "|---[Bank Help]---|");
								sender.sendMessage(ChatColor.GRAY + "Huidige balans van het bedrijf is: " + String.valueOf(c.getBalance()));
								sender.sendMessage(ChatColor.GRAY + "/bedrijf bank (bedrijf) stort (bedrag)");
								sender.sendMessage(ChatColor.GRAY + "/bedrijf bank (bedrijf) pin (bedrag)");
							}else {
								sender.sendMessage(ChatColor.RED + "Je bent niet de eigenaar van dit bedrijf!");
							}
						}else {
							sender.sendMessage(ChatColor.RED + "Ongeldig bedrijf!");
						}
					}else if(args[0].equalsIgnoreCase("review")) {
						sender.sendMessage(ChatColor.RED + "Gebruik: /bedrijf review (bedrijf) (1-10)");
					}else if(args[0].equalsIgnoreCase("solliciteer")) {
						sender.sendMessage(ChatColor.RED + "Gebruik: /bedrijf solliciteer (bedrijf) (bericht)");
					}else if(args[0].equalsIgnoreCase("betaal")) {
						sender.sendMessage(ChatColor.RED + "Gebruik: /bedrijf betaal (bedrijf) (bedrag)");
					}else if(args[0].equalsIgnoreCase("help")) {
						sender.sendMessage(ChatColor.RED + "|---[Bedrijf Help]---|");
						sender.sendMessage(ChatColor.GRAY + "/bedrijf start (bedrijf)");
						sender.sendMessage(ChatColor.GRAY + "/bedrijf stop (bedrijf)");
						sender.sendMessage(ChatColor.GRAY + "/bedrijf medewerker (bedrijf)");
						sender.sendMessage(ChatColor.GRAY + "/bedrijf betaal (bedrijf) (bedrag)");
						sender.sendMessage(ChatColor.GRAY + "/bedrijf bank (bedrijf)");
						sender.sendMessage(ChatColor.GRAY + "/bedrijf solliciteer (bedrijf) (bericht)");
						sender.sendMessage(ChatColor.GRAY + "/bedrijf review (bedrijf) (1-10)");
					}
				}else if(args.length == 3) {
					if(args[0].equalsIgnoreCase("start")) {
						if(Main.econ.getBalance(p) >= 5000) {
							Company comp = Companies.getInstance().getCompaniesManager().getCompany(args[1]);
							if(comp == null) {
								Companies.getInstance().getCompaniesController().createCompany(p, 5000, args[1]);
								ItemStack i = new ItemStack(Material.LEATHER,1);
								ItemMeta im = i.getItemMeta();
								im.setDisplayName(ChatColor.GOLD + "Kvk pass");
								ArrayList<String> lore = new ArrayList<String>();
								Random r = new Random();
								int num1 = r.nextInt(9999);
								int num2 = r.nextInt(99);
								int num3 = r.nextInt(999);
								String kvk = String.valueOf(num1)+"-"+String.valueOf(num2)+"-"+String.valueOf(num3);
								lore.add("§7Bedrijf: §6" + args[1]);
								lore.add("§7Kvk: §6" + kvk);
								im.setLore(lore);
								i.setItemMeta(im);
								p.getInventory().addItem(i);
							}else {
								p.sendMessage(ChatColor.RED + "Dit bedrijf bestaat al!");
							}
						} else {
							p.sendMessage(ChatColor.RED + "Je hebt $5000 nodig om een bedrijf te starten!");
						}
					}else if(args[0].equalsIgnoreCase("stop")) {
						Bukkit.dispatchCommand(sender, "cm disband " + args[1]);
					}else if(args[0].equalsIgnoreCase("medewerker") || args[0].equalsIgnoreCase("slaaf")) {
						Company comp = Companies.getInstance().getCompaniesManager().getCompany(args[1]);
						if(comp != null) {
							Company c = Companies.getInstance().getCompaniesManager().getCompany(args[1]);
							if(c.getOwnerUUID().equals(p.getUniqueId().toString())) {
								if(args[1].equalsIgnoreCase("lijst")) {
									ArrayList<Employee> werknemers = (ArrayList<Employee>)Companies.getInstance().getEmployeesManager().getEmployeesByCompany(c.getName());
									sender.sendMessage(ChatColor.RED + "|---[Medewerkers]---|");
									for(Employee e : werknemers) {
										sender.sendMessage(ChatColor.GRAY + "- " + e.getName());
									}
								}else {
									sender.sendMessage(ChatColor.RED + "|----[Medewerker  help]----|");
									sender.sendMessage(ChatColor.GRAY + "/bedrijf medewerker (bedrijf) lijst");
									sender.sendMessage(ChatColor.GRAY + "/bedrijf medewerker (bedrijf) aannemen (speler)");
									sender.sendMessage(ChatColor.GRAY + "/bedrijf medewerker (bedrijf) ontsla (speler)");
									sender.sendMessage(ChatColor.GRAY + "/bedrijf medewerker (bedrijf) promote (speler)");
									sender.sendMessage(ChatColor.GRAY + "/bedrijf medewerker (bedrijf) demote (speler)");
								}
							}else {
								sender.sendMessage(ChatColor.RED + "Je bent geen eigenaar van dit bedrijf!");
							}
						} else {
							sender.sendMessage(ChatColor.RED + "Ongeldig bedrijf!");
						}
					}else if(args[0].equalsIgnoreCase("bank")) {
						Company comp = Companies.getInstance().getCompaniesManager().getCompany(args[1]);
						if(comp != null) {
							Company c  = Companies.getInstance().getCompaniesManager().getCompany(args[1]);
							if(p.getUniqueId().toString().equals(c.getOwnerUUID())) {
								sender.sendMessage(ChatColor.RED + "|---[Bank Help]---|");
								sender.sendMessage(ChatColor.GRAY + "Huidige balans van het bedrijf is: " + String.valueOf(c.getBalance()));
								sender.sendMessage(ChatColor.GRAY + "/bedrijf bank (bedrijf) stort (bedrag)");
								sender.sendMessage(ChatColor.GRAY + "/bedrijf bank (bedrijf) pin (bedrag)");
							}else {
								sender.sendMessage(ChatColor.RED + "Je bent niet de eigenaar van dit bedrijf!");
							}
						}else {
							sender.sendMessage(ChatColor.RED + "Ongeldig bedrijf!");
						}
					}else if(args[0].equalsIgnoreCase("review")) {
						Company comp = Companies.getInstance().getCompaniesManager().getCompany(args[1]);
						if(comp != null) {
							Company c = Companies.getInstance().getCompaniesManager().getCompany(args[1]);
							if(!p.getUniqueId().toString().equals(c.getOwnerUUID())) {
								Bukkit.dispatchCommand(sender, "");
							}else {
								sender.sendMessage(ChatColor.RED + "Je kan geen reviews plaatsen op je eigen bedrijf!");
							}
						}else {
							sender.sendMessage(ChatColor.RED + "Ongeldig bedrijf!");
						}
					}else if(args[0].equalsIgnoreCase("solliciteer")) {
						sender.sendMessage(ChatColor.RED + "Gebruik: /bedrijf solliciteer (bedrijf) (bericht)");
					}else if(args[0].equalsIgnoreCase("betaal")) {
						Company c = Companies.getInstance().getCompaniesManager().getCompany(args[1]);
						if(c != null) {
							
							try {
								double d = Double.parseDouble(args[2]);
								if(Main.econ.getBalance(p) >= d) {
									Companies.getInstance().getCompaniesController().companyDeposit(p, c.getName(), d);
									Utils.sendToEmployees(c, "§8[§a"+c.getName()+"§8] §7"+p.getName()+" §aheeft $§7"+args[2]+" §abetaald!");
								}else {
									p.sendMessage(ChatColor.RED + "Je hebt niet genoeg geld op je bank staan!");
								}
							}catch(Exception e) {
								sender.sendMessage(ChatColor.RED + "Ongeldig bedrag!");
							}
						} else {
							sender.sendMessage(ChatColor.RED + "Ongeldig bedrijf!");
						}
					}else if(args[0].equalsIgnoreCase("help")) {
						sender.sendMessage(ChatColor.RED + "|---[Bedrijf Help]---|");
						sender.sendMessage(ChatColor.GRAY + "/bedrijf start (bedrijf)");
						sender.sendMessage(ChatColor.GRAY + "/bedrijf stop (bedrijf)");
						sender.sendMessage(ChatColor.GRAY + "/bedrijf medewerker (bedrijf)");
						sender.sendMessage(ChatColor.GRAY + "/bedrijf betaal (bedrijf) (bedrag)");
						sender.sendMessage(ChatColor.GRAY + "/bedrijf bank (bedrijf)");
						sender.sendMessage(ChatColor.GRAY + "/bedrijf solliciteer (bedrijf) (bericht)");
						sender.sendMessage(ChatColor.GRAY + "/bedrijf review (bedrijf) (1-10)");
					}
				}else if(args.length == 4) {
					if(args[0].equalsIgnoreCase("start")) {
						if(Main.econ.getBalance(p) >= 5000) {
							Company comp = Companies.getInstance().getCompaniesManager().getCompany(args[1]);
							if(comp == null) {
								Companies.getInstance().getCompaniesController().createCompany(p, 5000, args[1]);
								ItemStack i = new ItemStack(Material.LEATHER,1);
								ItemMeta im = i.getItemMeta();
								im.setDisplayName(ChatColor.GOLD + "Kvk pass");
								ArrayList<String> lore = new ArrayList<String>();
								Random r = new Random();
								int num1 = r.nextInt(9999);
								int num2 = r.nextInt(99);
								int num3 = r.nextInt(999);
								String kvk = String.valueOf(num1)+"-"+String.valueOf(num2)+"-"+String.valueOf(num3);
								lore.add("§7Bedrijf: §6" + args[1]);
								lore.add("§7Kvk: §6" + kvk);
								im.setLore(lore);
								i.setItemMeta(im);
								p.getInventory().addItem(i);
							}else {
								p.sendMessage(ChatColor.RED + "Dit bedrijf bestaat al!");
							}
						} else {
							p.sendMessage(ChatColor.RED + "Je hebt $5000 nodig om een bedrijf te starten!");
						}
					}else if(args[0].equalsIgnoreCase("stop")) {
						Bukkit.dispatchCommand(sender, "cm disband " + args[1]);
					}else if(args[0].equalsIgnoreCase("medewerker") || args[0].equalsIgnoreCase("slaaf")) {
						Company comp = Companies.getInstance().getCompaniesManager().getCompany(args[1]);
						if(comp != null) {
							Company c = Companies.getInstance().getCompaniesManager().getCompany(args[1]);
							if(args[2].equalsIgnoreCase("lijst")) {
								ArrayList<Employee> werknemers = (ArrayList<Employee>)Companies.getInstance().getEmployeesManager().getEmployeesByCompany(c.getName());
								sender.sendMessage(ChatColor.RED + "|---[Medewerkers]---|");
								for(Employee e : werknemers) {
									sender.sendMessage(ChatColor.GRAY + "- " + e.getName());
								}
							}else if(args[2].equalsIgnoreCase("aannemen")) {
								Bukkit.dispatchCommand(sender, "cm request accept " + args[1] + " " + args[3]);
							}else if(args[2].equalsIgnoreCase("onstla")) {
								Bukkit.dispatchCommand(sender, "cm staff kick " + args[1] + " " + args[3]);
							}else if(args[2].equalsIgnoreCase("promote")) {
								Bukkit.dispatchCommand(sender, "cm staff promote " + args[1] + " " + args[3]);
							}else if(args[2].equalsIgnoreCase("demote")) {
								Bukkit.dispatchCommand(sender, "cm staff demote " + args[1] + " " + args[3]);
							}
						}else {
							sender.sendMessage(ChatColor.RED + "Ongeldig bedrijf!");
						}
					}else if(args[0].equalsIgnoreCase("bank")) {
						Company comp = Companies.getInstance().getCompaniesManager().getCompany(args[1]);
						if(comp != null) {
							Company c = Companies.getInstance().getCompaniesManager().getCompany(args[1]);
							if(args[2].equalsIgnoreCase("pin")) {
								if(p.getUniqueId().toString().equals(c.getOwnerUUID())) {
									try {
										double req = Double.parseDouble(args[3]);
										if(c.getBalance() >= req) {
											Companies.getInstance().getCompaniesController().companyWithdraw(p, args[1], req);
											sender.sendMessage(ChatColor.RED + "Je hebt successvol " + args[3] + " gepind!");
										} else {
											sender.sendMessage(ChatColor.RED + "Saldo ontoereikend!");
										}
									}catch(Exception e) {
										sender.sendMessage(ChatColor.RED + "Ongeldig bedrag!");
									}
								}else {
									sender.sendMessage(ChatColor.RED + "Je bent niet de eigenaar van dit bedrijf!");
								}
							}else if(args[2].equalsIgnoreCase("stort")) {
								if(p.getUniqueId().toString().equals(c.getOwnerUUID())) {
									try {
										double req = Double.parseDouble(args[3]);
										if(Main.econ.getBalance(p) >= req) {
											Companies.getInstance().getCompaniesController().companyDeposit(p, args[1], req);
										} else {
											sender.sendMessage(ChatColor.RED + "Saldo ontoereikend!");
										}
									}catch(Exception e) {
										sender.sendMessage(ChatColor.RED + "Ongeldig bedrag!");
									}
								}else {
									sender.sendMessage(ChatColor.RED + "Je bent niet de eigenaar van dit bedrijf!");
								}								
							}
						}else {
							sender.sendMessage(ChatColor.RED + "Ongeldig bedrijf!");
						}
					}else if(args[0].equalsIgnoreCase("review")) {
						sender.sendMessage(ChatColor.RED + "Gebruik: /bedrijf review (bedrijf) (1-10) (comment)");
					}else if(args[0].equalsIgnoreCase("solliciteer")) {
						sender.sendMessage(ChatColor.RED + "Gebruik: /bedrijf solliciteer (bedrijf) (sollicitatie)");
					}else if(args[0].equalsIgnoreCase("betaal")) {
						Company c = Companies.getInstance().getCompaniesManager().getCompany(args[1]);
						if(c != null) {
							
							try {
								double d = Double.parseDouble(args[2]);
								if(Main.econ.getBalance(p) >= d) {
									Companies.getInstance().getCompaniesController().companyDeposit(p, c.getName(), d);
									Utils.sendToEmployees(c, "§8[§a"+c.getName()+"§8] §7"+p.getName()+" §aheeft $§7"+args[2]+" §abetaald!");
								}else {
									p.sendMessage(ChatColor.RED + "Je hebt niet genoeg geld op je bank staan!");
								}
							}catch(Exception e) {
								sender.sendMessage(ChatColor.RED + "Ongeldig bedrag!");
							}
						} else {
							sender.sendMessage(ChatColor.RED + "Ongeldig bedrijf!");
						}
					}
				}else if(args[0].equalsIgnoreCase("help")) {
					sender.sendMessage(ChatColor.RED + "|---[Bedrijf Help]---|");
					sender.sendMessage(ChatColor.GRAY + "/bedrijf start (bedrijf)");
					sender.sendMessage(ChatColor.GRAY + "/bedrijf stop (bedrijf)");
					sender.sendMessage(ChatColor.GRAY + "/bedrijf medewerker (bedrijf)");
					sender.sendMessage(ChatColor.GRAY + "/bedrijf betaal (bedrijf) (bedrag)");
					sender.sendMessage(ChatColor.GRAY + "/bedrijf bank (bedrijf)");
					sender.sendMessage(ChatColor.GRAY + "/bedrijf solliciteer (bedrijf) (bericht)");
					sender.sendMessage(ChatColor.GRAY + "/bedrijf review (bedrijf) (1-10)");
				}
			}else if(args.length > 4) {
				if(args[0].equalsIgnoreCase("start")) {
					if(Main.econ.getBalance(p) >= 5000) {
						Company comp = Companies.getInstance().getCompaniesManager().getCompany(args[1]);
						if(comp == null) {
							Companies.getInstance().getCompaniesController().createCompany(p, 5000, args[1]);
							ItemStack i = new ItemStack(Material.LEATHER,1);
							ItemMeta im = i.getItemMeta();
							im.setDisplayName(ChatColor.GOLD + "Kvk pass");
							ArrayList<String> lore = new ArrayList<String>();
							Random r = new Random();
							int num1 = r.nextInt(9999);
							int num2 = r.nextInt(99);
							int num3 = r.nextInt(999);
							String kvk = String.valueOf(num1)+"-"+String.valueOf(num2)+"-"+String.valueOf(num3);
							lore.add("§7Bedrijf: §6" + args[1]);
							lore.add("§7Kvk: §6" + kvk);
							im.setLore(lore);
							i.setItemMeta(im);
							p.getInventory().addItem(i);
						}else {
							p.sendMessage(ChatColor.RED + "Dit bedrijf bestaat al!");
						}
					} else {
						p.sendMessage(ChatColor.RED + "Je hebt $5000 nodig om een bedrijf te starten!");
					}
				}else if(args[0].equalsIgnoreCase("stop")) {
					Bukkit.dispatchCommand(sender, "cm disband " + args[1]);
				}else if(args[0].equalsIgnoreCase("medewerker") || args[0].equalsIgnoreCase("slaaf")) {
					Company comp = Companies.getInstance().getCompaniesManager().getCompany(args[1]);
					if(comp != null) {
						Company c = Companies.getInstance().getCompaniesManager().getCompany(args[1]);
						if(args[2].equalsIgnoreCase("lijst")) {
							ArrayList<Employee> werknemers = (ArrayList<Employee>)Companies.getInstance().getEmployeesManager().getEmployeesByCompany(c.getName());
							sender.sendMessage(ChatColor.RED + "|---[Medewerkers]---|");
							for(Employee e : werknemers) {
								sender.sendMessage(ChatColor.GRAY + "- " + e.getName());
							}
						}else if(args[2].equalsIgnoreCase("aannemen")) {
							Bukkit.dispatchCommand(sender, "cm request accept " + args[1] + " " + args[3]);
						}else if(args[2].equalsIgnoreCase("onstla")) {
							Bukkit.dispatchCommand(sender, "cm staff kick " + args[1] + " " + args[3]);
						}else if(args[2].equalsIgnoreCase("promote")) {
							Bukkit.dispatchCommand(sender, "cm staff promote " + args[1] + " " + args[3]);
						}else if(args[2].equalsIgnoreCase("demote")) {
							Bukkit.dispatchCommand(sender, "cm staff demote " + args[1] + " " + args[3]);
						}
					}else {
						sender.sendMessage(ChatColor.RED + "Ongeldig bedrijf!");
					}
				}else if(args[0].equalsIgnoreCase("bank")) {
					Company comp = Companies.getInstance().getCompaniesManager().getCompany(args[1]);
					if(comp != null) {
						Company c = Companies.getInstance().getCompaniesManager().getCompany(args[1]);
						if(args[2].equalsIgnoreCase("pin")) {
							if(p.getUniqueId().toString().equals(c.getOwnerUUID())) {
								try {
									double req = Double.parseDouble(args[3]);
									if(c.getBalance() >= req) {
										Companies.getInstance().getCompaniesController().companyWithdraw(p, args[1], req);
										sender.sendMessage(ChatColor.RED + "Je hebt successvol " + args[3] + " gepind!");
									} else {
										sender.sendMessage(ChatColor.RED + "Saldo ontoereikend!");
									}
								}catch(Exception e) {
									sender.sendMessage(ChatColor.RED + "Ongeldig bedrag!");
								}
							}else {
								sender.sendMessage(ChatColor.RED + "Je bent niet de eigenaar van dit bedrijf!");
							}
						}else if(args[2].equalsIgnoreCase("stort")) {
							if(p.getUniqueId().toString().equals(c.getOwnerUUID())) {
								try {
									double req = Double.parseDouble(args[3]);
									if(Main.econ.getBalance(p) >= req) {
										Companies.getInstance().getCompaniesController().companyDeposit(p, args[1], req);
									} else {
										sender.sendMessage(ChatColor.RED + "Saldo ontoereikend!");
									}
								}catch(Exception e) {
									sender.sendMessage(ChatColor.RED + "Ongeldig bedrag!");
								}
							}else {
								sender.sendMessage(ChatColor.RED + "Je bent niet de eigenaar van dit bedrijf!");
							}								
						}
					}else {
						sender.sendMessage(ChatColor.RED + "Ongeldig bedrijf!");
					}
				}else if(args[0].equalsIgnoreCase("review")) {
					StringBuilder str = new StringBuilder();
					for(int i = 2; i<args.length;i++) {
						str.append(args[i]);
						str.append(" ");
					}
					Bukkit.dispatchCommand(sender, "cm review create " + args[1] + " " + str.toString());
				}else if(args[0].equalsIgnoreCase("solliciteer")) {
					StringBuilder str = new StringBuilder();
					for(int i = 2; i<args.length;i++) {
						str.append(args[i]);
						str.append(" ");
					}
					Bukkit.dispatchCommand(sender, "cm request send " + args[1] + " 750 " + str.toString());
				}else if(args[0].equalsIgnoreCase("betaal")) {
					Company c = Companies.getInstance().getCompaniesManager().getCompany(args[1]);
					if(c != null) {
						
						try {
							double d = Double.parseDouble(args[2]);
							if(Main.econ.getBalance(p) >= d) {
								Companies.getInstance().getCompaniesController().companyDeposit(p, c.getName(), d);
								Utils.sendToEmployees(c, "§8[§a"+c.getName()+"§8] §7"+p.getName()+" §aheeft $§7"+args[2]+" §abetaald!");
							}else {
								p.sendMessage(ChatColor.RED + "Je hebt niet genoeg geld op je bank staan!");
							}
						}catch(Exception e) {
							sender.sendMessage(ChatColor.RED + "Ongeldig bedrag!");
						}
					} else {
						sender.sendMessage(ChatColor.RED + "Ongeldig bedrijf!");
					}
				}else if(args[0].equalsIgnoreCase("help")) {
					sender.sendMessage(ChatColor.RED + "|---[Bedrijf Help]---|");
					sender.sendMessage(ChatColor.GRAY + "/bedrijf start (bedrijf)");
					sender.sendMessage(ChatColor.GRAY + "/bedrijf stop (bedrijf)");
					sender.sendMessage(ChatColor.GRAY + "/bedrijf medewerker (bedrijf)");
					sender.sendMessage(ChatColor.GRAY + "/bedrijf betaal (bedrijf) (bedrag)");
					sender.sendMessage(ChatColor.GRAY + "/bedrijf bank (bedrijf)");
					sender.sendMessage(ChatColor.GRAY + "/bedrijf solliciteer (bedrijf) (bericht)");
					sender.sendMessage(ChatColor.GRAY + "/bedrijf review (bedrijf) (1-10)");
				}
			}
		}
		
		return true;
	}
	
}
