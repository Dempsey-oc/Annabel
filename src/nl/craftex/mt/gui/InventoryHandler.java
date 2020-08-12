package nl.craftex.mt.gui;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import eu.mrapik.companies.Companies;
import eu.mrapik.companies.company.Company;
import eu.mrapik.companies.employee.Employee;
import net.md_5.bungee.api.ChatColor;
import nl.craftex.mt.util.Modus;
import nl.craftex.mt.util.MojangAPI;

public class InventoryHandler implements Listener{

	FrontMenu fm = new FrontMenu();
	
	@EventHandler(priority=EventPriority.HIGHEST)
	public void onInventoryClick(InventoryClickEvent e) {
		if(e.getClickedInventory() != null) {
			if(e.getView().getTitle().contains("Bedrijfsmenu voor:")) {
				String[] company = e.getView().getTitle().split(" ");
				String resname = company[2];
				e.setCancelled(true);
				if(e.getCurrentItem() != null) {
					if(e.getCurrentItem().getType().equals(Material.BOOK)) {
						if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Forum")) {
							e.getWhoClicked().sendMessage(ChatColor.GREEN + "https://overcast.be");
						}else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Bedrijf overzetten")) {
							Company c = Companies.getInstance().getCompaniesManager().getCompany(resname);
							if(c.getOwnerUUID().equals(((Player)e.getWhoClicked()).getUniqueId().toString())) {
								fm.openTransfer((Player)e.getWhoClicked(), c);
							}else {
								((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Je bent niet de eigenaar van dit bedrijf!");
							}
						}
					}else if(e.getCurrentItem().getType().equals(Material.WRITABLE_BOOK)) {
						if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Sollicitaties")) {
							fm.openSollis((Player)e.getWhoClicked(), Companies.getInstance().getCompaniesManager().getCompany(resname));
						}
					}else if(e.getCurrentItem().getType().equals(Material.PLAYER_HEAD)){
						if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Bank")) {
							fm.openConfirm((Player)e.getWhoClicked(), Companies.getInstance().getCompaniesManager().getCompany(resname), Modus.BANK, null);
						}else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Personeel")) {
							fm.openStaff((Player)e.getWhoClicked(), Companies.getInstance().getCompaniesManager().getCompany(resname));
						}else if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Bedrijf")) {
							Bukkit.dispatchCommand((Player)e.getWhoClicked(), "cm setlocation " + resname);
						}
					}else if(e.getCurrentItem().getType().equals(Material.PAPER)) {
						if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Reviews")) {
							fm.openReviews((Player)e.getWhoClicked(), Companies.getInstance().getCompaniesManager().getCompany(resname));
						}
					}else if(e.getCurrentItem().getType().equals(Material.BARRIER)) {
						if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Verwijder")) {
							e.getView().close();
							fm.openConfirm((Player)e.getWhoClicked(), Companies.getInstance().getCompaniesManager().getCompany(resname), Modus.DISBAND, null);
						}
					}
				}
			}else if(e.getView().getTitle().contains("Bedrijf stoppen:")) {
				e.setCancelled(true);
				String[] company = e.getView().getTitle().split(" ");
				String resname = company[2];
				if(e.getCurrentItem() != null) {
					if(e.getCurrentItem().getType().equals(Material.EMERALD_BLOCK)) {
						Bukkit.dispatchCommand((Player)e.getWhoClicked(), "cm disband " + resname);
						e.getView().close();
					}else if(e.getCurrentItem().getType().equals(Material.REDSTONE_BLOCK)) {
						e.getView().close();
						fm.openGUI((Player)e.getWhoClicked(), Companies.getInstance().getCompaniesManager().getCompany(resname));
					}
				}
				
			}else if(e.getView().getTitle().contains("Mijn bedrijven:")) {
				e.setCancelled(true);
				if(e.getCurrentItem() != null) {
					if(e.getCurrentItem().getType().equals(Material.BOOK)) {
						Company c = Companies.getInstance().getCompaniesManager().getCompany(e.getCurrentItem().getItemMeta().getDisplayName());
						if(c != null) {
							fm.openGUI((Player)e.getWhoClicked(), c);
						} else {
							((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Oops, we hebben een error!");
						}
					}
				}
			}else if(e.getView().getTitle().contains("Reviews voor:")) {
				e.setCancelled(true);
				if(e.getCurrentItem().getType().equals(Material.BARRIER)) {
					String[] cmp = e.getView().getTitle().split(" ");
					String bedrijf = cmp[2];
					Company c = Companies.getInstance().getCompaniesManager().getCompany(bedrijf);
					if(c != null) {
						fm.openGUI((Player)e.getWhoClicked(), c);
					} else {
						((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Oops, we hebben een error!");
					}
				}
			}else if(e.getView().getTitle().contains("Werknemers voor:")) {
				e.setCancelled(true);
				if(e.getCurrentItem() != null) {
					if(e.getCurrentItem().getType().equals(Material.PLAYER_HEAD)) {
						String[] cmp = e.getView().getTitle().split(" ");
						String bedrijf = cmp[2];
						fm.openMStaff((Player)e.getWhoClicked(), Companies.getInstance().getCompaniesManager().getCompany(bedrijf), Companies.getInstance().getEmployeesManager().getEmployee(UUID.fromString(e.getCurrentItem().getItemMeta().getLore().get(2)), bedrijf));
					}else if(e.getCurrentItem().getType().equals(Material.BARRIER)) {
						String[] cmp = e.getView().getTitle().split(" ");
						String bedrijf = cmp[2];
						Company c = Companies.getInstance().getCompaniesManager().getCompany(bedrijf);
						if(c != null) {
							fm.openGUI((Player)e.getWhoClicked(), c);
						} else { 
							((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Oops, we hebben een error!");
						}
					}
				}
			}else if(e.getView().getTitle().contains("Opties voor:")) {
				e.setCancelled(true);
				if(e.getCurrentItem() != null) {
					if(e.getCurrentItem().getType().equals(Material.BARRIER)) {
						String[] cmp = e.getView().getTitle().split(" ");
						String bedrijf = cmp[2];
						Company c = Companies.getInstance().getCompaniesManager().getCompany(bedrijf);
						if(c != null) {
							fm.openStaff((Player)e.getWhoClicked(), c);
						} else {
							((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Oops, we hebben een error!");
						}
					}else if(e.getCurrentItem().getType().equals(Material.REDSTONE_BLOCK)) {
						if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Kick")) {
							//Kick
							String[] cmp = e.getView().getTitle().split(" ");
							String bedrijf = cmp[2];
							Company c = Companies.getInstance().getCompaniesManager().getCompany(bedrijf);
							if(c != null) {
								if(!c.getOwnerUUID().equals(MojangAPI.getParsedUID(cmp[4]))){
									Bukkit.dispatchCommand((Player)e.getWhoClicked(), "cm staff kick " + bedrijf + " " + cmp[4]);
									fm.openMStaff((Player)e.getWhoClicked(), Companies.getInstance().getCompaniesManager().getCompany(bedrijf), Companies.getInstance().getEmployeesManager().getEmployee(UUID.fromString(MojangAPI.getParsedUID(cmp[4])), bedrijf));
								} else {
									((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Dit is de eigenaar van het bedrijf!");
								}
							}else {
								((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Oops, we hebben een error!");
							}
						} else {
							// Demote
							String[] cmp = e.getView().getTitle().split(" ");
							String bedrijf = cmp[2];
							Company c = Companies.getInstance().getCompaniesManager().getCompany(bedrijf);
							if(c != null) {
								if(!c.getOwnerUUID().equals(MojangAPI.getParsedUID(cmp[4]))){
									Bukkit.dispatchCommand((Player)e.getWhoClicked(), "cm staff demote " + bedrijf + " " + cmp[4]);
								} else {
									((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Dit is de eigenaar van het bedrijf!");
								}
							}else {
								((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Oops, we hebben een error!");
							}
							
						}
					}else if(e.getCurrentItem().getType().equals(Material.EMERALD_BLOCK)) {
						String[] cmp = e.getView().getTitle().split(" ");
						String bedrijf = cmp[2];
						Company c = Companies.getInstance().getCompaniesManager().getCompany(bedrijf);
						if(c != null) {
							if(!c.getOwnerUUID().equals(MojangAPI.getParsedUID(cmp[4]))){
								Bukkit.dispatchCommand((Player)e.getWhoClicked(), "cm staff promote " + bedrijf + " " + cmp[4]);
							} else {
								((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Dit is de eigenaar van het bedrijf!");
							}
						}else {
							((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Oops, we hebben een error!");
						}
					}else if(e.getCurrentItem().getType().equals(Material.GOLD_INGOT)) {
						String[] cmp = e.getView().getTitle().split(" ");
						String bedrijf = cmp[2];
						Company c = Companies.getInstance().getCompaniesManager().getCompany(bedrijf);
						if(c != null) {
							fm.openMStaffW((Player)e.getWhoClicked(), c, Companies.getInstance().getEmployeesManager().getEmployee(UUID.fromString(e.getCurrentItem().getItemMeta().getLore().get(1)), bedrijf));
						}else {
							((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Oops, we hebben een error!");
						}
					}
				}
			}else if(e.getView().getTitle().contains("Loon voor:")) {
				e.setCancelled(true);
				if(e.getCurrentItem() != null) {
					if(e.getCurrentItem().getType().equals(Material.BARRIER)) {
						String[] cmp = e.getView().getTitle().split(" ");
						String bedrijf = cmp[2];
						Company c = Companies.getInstance().getCompaniesManager().getCompany(bedrijf);
						if(c != null) {
							fm.openStaff((Player)e.getWhoClicked(), c);
						} else {
							((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Oops, we hebben een error!");
						}
					}else if(e.getCurrentItem().getType().equals(Material.EMERALD_BLOCK)) {
						String[] cmp = e.getView().getTitle().split(" ");
						String bedrijf = cmp[2];
						Company c = Companies.getInstance().getCompaniesManager().getCompany(bedrijf);
						if(c != null) {
							Employee emp = Companies.getInstance().getEmployeesManager().getEmployee(UUID.fromString(cmp[4]), bedrijf);
							if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§a+ $1")) {
								double current = emp.getNextWage();
								double next = current + 1;
								emp.setNextWage(next);
								fm.openMStaffW((Player)e.getWhoClicked(), c, emp);
							}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§a+ $10")) {
								double current = emp.getNextWage();
								double next = current + 10;
								emp.setNextWage(next);
								fm.openMStaffW((Player)e.getWhoClicked(), c, emp);
							}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§a+ $100")) {
								double current = emp.getNextWage();
								double next = current + 100;
								emp.setNextWage(next);
								fm.openMStaffW((Player)e.getWhoClicked(), c, emp);
							}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§a+ $1000")) {
								double current = emp.getNextWage();
								double next = current + 1000;
								emp.setNextWage(next);
								fm.openMStaffW((Player)e.getWhoClicked(), c, emp);
							}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§a+ $10.000")) {
								double current = emp.getNextWage();
								double next = current + 10000;
								emp.setNextWage(next);
								fm.openMStaffW((Player)e.getWhoClicked(), c, emp);
							}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§a+ $100.000")) {
								double current = emp.getNextWage();
								double next = current + 100000;
								emp.setNextWage(next);
								fm.openMStaffW((Player)e.getWhoClicked(), c, emp);
							}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§a+ $1.000.000")) {
								double current = emp.getNextWage();
								double next = current + 1000000;
								emp.setNextWage(next);
								fm.openMStaffW((Player)e.getWhoClicked(), c, emp);
							}
						} else {
							((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Oops, we hebben een error!");
						}
					}else if(e.getCurrentItem().getType().equals(Material.REDSTONE_BLOCK)) {
						String[] cmp = e.getView().getTitle().split(" ");
						String bedrijf = cmp[2];
						Company c = Companies.getInstance().getCompaniesManager().getCompany(bedrijf);
						if(c != null) {
							Employee emp = Companies.getInstance().getEmployeesManager().getEmployee(UUID.fromString(cmp[4]), bedrijf);
							if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§4- $1")) {
								double current = emp.getNextWage();
								double next = current - 1;
								emp.setNextWage(next);
								fm.openMStaffW((Player)e.getWhoClicked(), c, emp);
							}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§4- $10")) {
								double current = emp.getNextWage();
								double next = current - 10;
								emp.setNextWage(next);
								fm.openMStaffW((Player)e.getWhoClicked(), c, emp);
							}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§4- $100")) {
								double current = emp.getNextWage();
								double next = current - 100;
								emp.setNextWage(next);
								fm.openMStaffW((Player)e.getWhoClicked(), c, emp);
							}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§4- $1000")) {
								double current = emp.getNextWage();
								double next = current - 1000;
								emp.setNextWage(next);
								fm.openMStaffW((Player)e.getWhoClicked(), c, emp);
							}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§4- $10.000")) {
								double current = emp.getNextWage();
								double next = current - 10000;
								emp.setNextWage(next);
								fm.openMStaffW((Player)e.getWhoClicked(), c, emp);
							}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§4- $100.000")) {
								double current = emp.getNextWage();
								double next = current - 100000;
								emp.setNextWage(next);
								fm.openMStaffW((Player)e.getWhoClicked(), c, emp);
							}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§4- $1.000.000")) {
								double current = emp.getNextWage();
								double next = current - 1000000;
								emp.setNextWage(next);
								fm.openMStaffW((Player)e.getWhoClicked(), c, emp);
							}
						} else {
							((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Oops, we hebben een error!");
						}
					}
				}
			}else if(e.getView().getTitle().contains("Bank voor:")) {
				e.setCancelled(true);
				if(e.getCurrentItem() != null) {
					if(e.getCurrentItem().getType().equals(Material.BARRIER)) {
						String[] cmp = e.getView().getTitle().split(" ");
						String bedrijf = cmp[2];
						Company c = Companies.getInstance().getCompaniesManager().getCompany(bedrijf);
						if(c != null) {
							fm.openConfirm((Player)e.getWhoClicked(), c, Modus.BANK, null);
						} else {
							((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Oops, we hebben een error!");
						}
					}else if(e.getCurrentItem().getType().equals(Material.EMERALD_BLOCK)) {
						String[] cmp = e.getView().getTitle().split(" ");
						String bedrijf = cmp[2];
						Company c = Companies.getInstance().getCompaniesManager().getCompany(bedrijf);
						if(c != null) {
							if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§a+ $1")) {
								Bukkit.dispatchCommand((Player)e.getWhoClicked(), "bedrijf bank " + bedrijf + " stort 1");
								fm.openBank((Player)e.getWhoClicked(), c);
							}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§a+ $10")) {
								Bukkit.dispatchCommand((Player)e.getWhoClicked(), "bedrijf bank " + bedrijf + " stort 10");
								fm.openBank((Player)e.getWhoClicked(), c);
							}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§a+ $100")) {
								Bukkit.dispatchCommand((Player)e.getWhoClicked(), "bedrijf bank " + bedrijf + " stort 100");
								fm.openBank((Player)e.getWhoClicked(), c);
							}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§a+ $1000")) {
								Bukkit.dispatchCommand((Player)e.getWhoClicked(), "bedrijf bank " + bedrijf + " stort 1000");
								fm.openBank((Player)e.getWhoClicked(), c);
							}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§a+ $10.000")) {
								Bukkit.dispatchCommand((Player)e.getWhoClicked(), "bedrijf bank " + bedrijf + " stort 10000");
								fm.openBank((Player)e.getWhoClicked(), c);
							}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§a+ $100.000")) {
								Bukkit.dispatchCommand((Player)e.getWhoClicked(), "bedrijf bank " + bedrijf + " stort 100000");
								fm.openBank((Player)e.getWhoClicked(), c);
							}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§a+ $1.000.000")) {
								Bukkit.dispatchCommand((Player)e.getWhoClicked(), "bedrijf bank " + bedrijf + " stort 1000000");
								fm.openBank((Player)e.getWhoClicked(), c);
							}
						} else {
							((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Oops, we hebben een error!");
						}
					}else if(e.getCurrentItem().getType().equals(Material.REDSTONE_BLOCK)) {
						String[] cmp = e.getView().getTitle().split(" ");
						String bedrijf = cmp[2];
						Company c = Companies.getInstance().getCompaniesManager().getCompany(bedrijf);
						if(c != null) {
							if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§4- $1")) {
								Bukkit.dispatchCommand((Player)e.getWhoClicked(), "bedrijf bank " + bedrijf + " pin 1");
								fm.openBank((Player)e.getWhoClicked(), c);
							}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§4- $10")) {
								Bukkit.dispatchCommand((Player)e.getWhoClicked(), "bedrijf bank " + bedrijf + " pin 10");
								fm.openBank((Player)e.getWhoClicked(), c);
							}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§4- $100")) {
								Bukkit.dispatchCommand((Player)e.getWhoClicked(), "bedrijf bank " + bedrijf + " pin 100");
								fm.openBank((Player)e.getWhoClicked(), c);
							}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§4- $1000")) {
								Bukkit.dispatchCommand((Player)e.getWhoClicked(), "bedrijf bank " + bedrijf + " pin 1000");
								fm.openBank((Player)e.getWhoClicked(), c);
							}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§4- $10.000")) {
								Bukkit.dispatchCommand((Player)e.getWhoClicked(), "bedrijf bank " + bedrijf + " pin 10000");
								fm.openBank((Player)e.getWhoClicked(), c);
							}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§4- $100.000")) {
								Bukkit.dispatchCommand((Player)e.getWhoClicked(), "bedrijf bank " + bedrijf + " pin 100000");
								fm.openBank((Player)e.getWhoClicked(), c);
							}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§4- $1.000.000")) {
								Bukkit.dispatchCommand((Player)e.getWhoClicked(), "bedrijf bank " + bedrijf + " pin 1000000");
								fm.openBank((Player)e.getWhoClicked(), c);
							}
						} else {
							((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Oops, we hebben een error!");
						}
					}
				}
			}else if(e.getView().getTitle().contains("Sollicitaties voor:")) {
				e.setCancelled(true);
				if(e.getCurrentItem() != null) {
					if(e.getCurrentItem().getType().equals(Material.BARRIER)) {
						String[] cmp = e.getView().getTitle().split(" ");
						String bedrijf = cmp[2];
						Company c = Companies.getInstance().getCompaniesManager().getCompany(bedrijf);
						if(c != null) {
							fm.openGUI((Player)e.getWhoClicked(), c);
						} else {
							((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Oops, we hebben een error!");
						}
					}else if(e.getCurrentItem().getType().equals(Material.WRITTEN_BOOK)) {
						String[] cmp = e.getView().getTitle().split(" ");
						String bedrijf = cmp[2];
						Company c = Companies.getInstance().getCompaniesManager().getCompany(bedrijf);
						if(c != null) {
							fm.openConfirm((Player)e.getWhoClicked(), c, Modus.HIRE, UUID.fromString(e.getCurrentItem().getItemMeta().getLore().get(2)));
						} else {
							((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Oops, we hebben een error!");
						}
					}
				}
			}else if(e.getView().getTitle().contains("Oordelen voor:")) {
				if(e.getCurrentItem() != null) {
					if(e.getCurrentItem().getType().equals(Material.BARRIER)) {
						String[] cmp = e.getView().getTitle().split(" ");
						String bedrijf = cmp[2];
						Company c = Companies.getInstance().getCompaniesManager().getCompany(bedrijf);
						if(c != null) {
							fm.openSollis((Player)e.getWhoClicked(), c);
						} else {
							((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Oops, we hebben een error!");
						}
					}else if(e.getCurrentItem().getType().equals(Material.EMERALD_BLOCK)) {
						String[] cmp = e.getView().getTitle().split(" ");
						String bedrijf = cmp[2];
						Company c = Companies.getInstance().getCompaniesManager().getCompany(bedrijf);
						if(c != null) {
							Bukkit.dispatchCommand((Player)e.getWhoClicked(), "bedrijf medewerker " + bedrijf + " aannemen " + cmp[4]);
							fm.openSollis((Player)e.getWhoClicked(), c);
						} else {
							((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Oops, we hebben een error!");
						}
					}else if(e.getCurrentItem().getType().equals(Material.REDSTONE_BLOCK)) {
						String[] cmp = e.getView().getTitle().split(" ");
						String bedrijf = cmp[2];
						Company c = Companies.getInstance().getCompaniesManager().getCompany(bedrijf);
						if(c != null) {
							Bukkit.dispatchCommand((Player)e.getWhoClicked(), "bedrijf medewerker " + bedrijf + " onstla " + cmp[4]);
							fm.openSollis((Player)e.getWhoClicked(), c);
						} else {
							((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Oops, we hebben een error!");
						}
					}
				}
			}else if(e.getView().getTitle().contains("Selectie voor:")) {
				e.setCancelled(true);
				if(e.getCurrentItem() != null) {
					if(e.getCurrentItem().getType().equals(Material.BARRIER)) {
						String[] cmp = e.getView().getTitle().split(" ");
						String bedrijf = cmp[2];
						Company c = Companies.getInstance().getCompaniesManager().getCompany(bedrijf);
						if(c != null) {
							fm.openGUI((Player)e.getWhoClicked(), c);
						} else {
							((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Oops, we hebben een error!");
						}
					}else if(e.getCurrentItem().getType().equals(Material.EMERALD)) {
						String[] cmp = e.getView().getTitle().split(" ");
						String bedrijf = cmp[2];
						Company c = Companies.getInstance().getCompaniesManager().getCompany(bedrijf);
						if(c != null) {
							fm.openCapital((Player)e.getWhoClicked(), c);
						} else {
							((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Oops, we hebben een error!");
						}
					}else if(e.getCurrentItem().getType().equals(Material.DIAMOND)) {
						String[] cmp = e.getView().getTitle().split(" ");
						String bedrijf = cmp[2];
						Company c = Companies.getInstance().getCompaniesManager().getCompany(bedrijf);
						if(c != null) {
							fm.openBank((Player)e.getWhoClicked(), c);
						} else {
							((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Oops, we hebben een error!");
						}
					}
				}
			}else if(e.getView().getTitle().contains("Kapitaal voor:")) {
				e.setCancelled(true);
				if(e.getCurrentItem() != null) {
					if(e.getCurrentItem().getType().equals(Material.EMERALD_BLOCK)) {
						if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§a+ $1")) {
							if(nl.craftex.mt.Main.econ.getBalance((Player)e.getWhoClicked()) >= 1) {
								String[] cmp = e.getView().getTitle().split(" ");
								String bedrijf = cmp[2];
								Company c = Companies.getInstance().getCompaniesManager().getCompany(bedrijf);
								if(c != null) {
									Companies.getInstance().getCompaniesController().companyAddCapital((Player)e.getWhoClicked(), bedrijf, 1);
									fm.openConfirm((Player)e.getWhoClicked(), c, Modus.BANK, null);
								} else {
									((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Oops, we hebben een error!");
								}
							}else {
								((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Je hebt niet genoeg op je rekening staan!");
							}
						}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§a+ $10")) {
							if(nl.craftex.mt.Main.econ.getBalance((Player)e.getWhoClicked()) >= 10) {
								String[] cmp = e.getView().getTitle().split(" ");
								String bedrijf = cmp[2];
								Company c = Companies.getInstance().getCompaniesManager().getCompany(bedrijf);
								if(c != null) {
									Companies.getInstance().getCompaniesController().companyAddCapital((Player)e.getWhoClicked(), bedrijf, 10);
									fm.openConfirm((Player)e.getWhoClicked(), c, Modus.BANK, null);
								} else {
									((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Oops, we hebben een error!");
								}
							}else {
								((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Je hebt niet genoeg op je rekening staan!");
							}
						}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§a+ $100")) {
							if(nl.craftex.mt.Main.econ.getBalance((Player)e.getWhoClicked()) >= 100) {
								String[] cmp = e.getView().getTitle().split(" ");
								String bedrijf = cmp[2];
								Company c = Companies.getInstance().getCompaniesManager().getCompany(bedrijf);
								if(c != null) {
									Companies.getInstance().getCompaniesController().companyAddCapital((Player)e.getWhoClicked(), bedrijf, 100);
									fm.openConfirm((Player)e.getWhoClicked(), c, Modus.BANK, null);
								} else {
									((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Oops, we hebben een error!");
								}
							}else {
								((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Je hebt niet genoeg op je rekening staan!");
							}
						}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§a+ $1000")) {
							if(nl.craftex.mt.Main.econ.getBalance((Player)e.getWhoClicked()) >= 1000) {
								String[] cmp = e.getView().getTitle().split(" ");
								String bedrijf = cmp[2];
								Company c = Companies.getInstance().getCompaniesManager().getCompany(bedrijf);
								if(c != null) {
									Companies.getInstance().getCompaniesController().companyAddCapital((Player)e.getWhoClicked(), bedrijf, 1000);
									fm.openConfirm((Player)e.getWhoClicked(), c, Modus.BANK, null);
								} else {
									((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Oops, we hebben een error!");
								}
							}else {
								((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Je hebt niet genoeg op je rekening staan!");
							}
						}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§a+ $10.000")) {
							if(nl.craftex.mt.Main.econ.getBalance((Player)e.getWhoClicked()) >= 10000) {
								String[] cmp = e.getView().getTitle().split(" ");
								String bedrijf = cmp[2];
								Company c = Companies.getInstance().getCompaniesManager().getCompany(bedrijf);
								if(c != null) {
									Companies.getInstance().getCompaniesController().companyAddCapital((Player)e.getWhoClicked(), bedrijf, 10000);
									fm.openConfirm((Player)e.getWhoClicked(), c, Modus.BANK, null);
								} else {
									((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Oops, we hebben een error!");
								}
							}else {
								((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Je hebt niet genoeg op je rekening staan!");
							}
						}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§a+ $100.000")) {
							if(nl.craftex.mt.Main.econ.getBalance((Player)e.getWhoClicked()) >= 100000) {
								String[] cmp = e.getView().getTitle().split(" ");
								String bedrijf = cmp[2];
								Company c = Companies.getInstance().getCompaniesManager().getCompany(bedrijf);
								if(c != null) {
									Companies.getInstance().getCompaniesController().companyAddCapital((Player)e.getWhoClicked(), bedrijf, 100000);
									fm.openConfirm((Player)e.getWhoClicked(), c, Modus.BANK, null);
								} else {
									((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Oops, we hebben een error!");
								}
							}else {
								((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Je hebt niet genoeg op je rekening staan!");
							}
						}else if(e.getCurrentItem().getItemMeta().getDisplayName().equals("§a+ $1.000.000")) {
							if(nl.craftex.mt.Main.econ.getBalance((Player)e.getWhoClicked()) >= 1000000) {
								String[] cmp = e.getView().getTitle().split(" ");
								String bedrijf = cmp[2];
								Company c = Companies.getInstance().getCompaniesManager().getCompany(bedrijf);
								if(c != null) {
									Companies.getInstance().getCompaniesController().companyAddCapital((Player)e.getWhoClicked(), bedrijf, 1000000);
									fm.openConfirm((Player)e.getWhoClicked(), c, Modus.BANK, null);
								} else {
									((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Oops, we hebben een error!");
								}
							}else {
								((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Je hebt niet genoeg op je rekening staan!");
							}
						}
					}else if(e.getCurrentItem().getType().equals(Material.BARRIER)) {
						String[] cmp = e.getView().getTitle().split(" ");
						String bedrijf = cmp[2];
						Company c = Companies.getInstance().getCompaniesManager().getCompany(bedrijf);
						if(c != null) {
							fm.openConfirm((Player)e.getWhoClicked(), c, Modus.BANK, null);
						} else {
							((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Oops, we hebben een error!");
						}
					}
				}
			}else if(e.getView().getTitle().contains("Bedrijf overzetten:")) {
				e.setCancelled(true);
				if(e.getCurrentItem() != null) {
					if(e.getCurrentItem().getType().equals(Material.PLAYER_HEAD)) {
						String[] cmp = e.getView().getTitle().split(" ");
						String bedrijf = cmp[2];
						Company c = Companies.getInstance().getCompaniesManager().getCompany(bedrijf);
						if(c != null) {
							c.setOwnerUUID(e.getCurrentItem().getItemMeta().getLore().get(0));
							e.getView().close();
							((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Bedrijf overgedragen naar " + e.getCurrentItem().getItemMeta().getDisplayName());;
						} else {
							((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Oops, we hebben een error!");
						}
					}else if(e.getCurrentItem().getType().equals(Material.BARRIER)) {
						String[] cmp = e.getView().getTitle().split(" ");
						String bedrijf = cmp[2];
						Company c = Companies.getInstance().getCompaniesManager().getCompany(bedrijf);
						if(c != null) {
							fm.openGUI((Player)e.getWhoClicked(), c);
						} else {
							((Player)e.getWhoClicked()).sendMessage(ChatColor.RED + "Oops, we hebben een error!");
						}
					}
				}
			}
		}
	}
	
}
