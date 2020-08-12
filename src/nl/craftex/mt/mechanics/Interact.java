package nl.craftex.mt.mechanics;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;

public class Interact implements Listener{

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(e.getClickedBlock().getType().equals(Material.RED_SANDSTONE_STAIRS) || e.getClickedBlock().getType().equals(Material.PURPUR_STAIRS)){
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cc open atm " + e.getPlayer().getName());
			}else if(e.getClickedBlock().getType().equals(Material.ENCHANTING_TABLE)) {
				if(!e.getPlayer().hasPermission("craftex.enchant")) {
					e.setCancelled(true);
				}else if(e.getPlayer().getGameMode().equals(GameMode.CREATIVE) && !e.getPlayer().hasPermission("craftex.bypass")) {
					e.setCancelled(true);
					e.getPlayer().sendMessage(ChatColor.RED + "You cannot open inventories in creative!");
				}
			}else if(e.getItem() != null) {
				 if(e.getItem().getType().equals(Material.LIME_DYE)) {
					 ItemStack i = e.getItem();
					 GregorianCalendar cl = new GregorianCalendar();
					 ArrayList<String> lore = (ArrayList<String>)i.getItemMeta().getLore();
					 if(lore.get(0).contains(e.getPlayer().getName())) {
						String[] date = lore.get(2).split("/");
						String[] fnl = date[0].split(" ");
						String dag = fnl[2];
						if((Integer.valueOf(dag)>=(cl.get(Calendar.DAY_OF_MONTH)+1)) && (Integer.valueOf(date[1])>=(cl.get(Calendar.MONTH)+1)) || (Integer.valueOf(date[2])>=cl.get(Calendar.YEAR))) {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cc open wholesale " + e.getPlayer().getName());
						} else {
							String[] branche = lore.get(1).split(" ");
							String res = branche[1];
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + e.getPlayer() + " remove menu." + res.toLowerCase());
							e.getPlayer().sendMessage(ChatColor.RED + "Je pas is verlopen, ga naar de wholesale om een nieuwe aan te vragen!");
						}
					}
				 }
				
			}else if(e.getClickedBlock().getType().equals(Material.CHEST)) {
				if(e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
					if(!e.getPlayer().hasPermission("craftex.bypass")) {
						e.setCancelled(true);
						e.getPlayer().sendMessage(ChatColor.RED + "You cannot open inventories in creative!");
					}
				}
			}else if(e.getClickedBlock().getType().equals(Material.TRAPPED_CHEST)) {
				if(e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
					if(!e.getPlayer().hasPermission("craftex.bypass")) {
						e.setCancelled(true);
						e.getPlayer().sendMessage(ChatColor.RED + "You cannot open inventories in creative!");
					}
				}
			}else if(e.getClickedBlock().getType().equals(Material.ENDER_CHEST)) {
				if(e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
					if(!e.getPlayer().hasPermission("craftex.bypass")) {
						e.setCancelled(true);
						e.getPlayer().sendMessage(ChatColor.RED + "You cannot open inventories in creative!");
					}
				}
			}else if(e.getClickedBlock().getType().equals(Material.FURNACE)) {
				if(e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
					if(!e.getPlayer().hasPermission("craftex.bypass")) {
						e.setCancelled(true);
						e.getPlayer().sendMessage(ChatColor.RED + "You cannot open inventories in creative!");
					}
				}
			}else if(e.getClickedBlock().getType().equals(Material.BLAST_FURNACE)) {
				if(e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
					if(!e.getPlayer().hasPermission("craftex.bypass")) {
						e.setCancelled(true);
						e.getPlayer().sendMessage(ChatColor.RED + "You cannot open inventories in creative!");
					}
				}
			}else if(e.getClickedBlock().getType().equals(Material.DROPPER)) {
				if(e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
					if(!e.getPlayer().hasPermission("craftex.bypass")) {
						e.setCancelled(true);
						e.getPlayer().sendMessage(ChatColor.RED + "You cannot open inventories in creative!");
					}
				}
			}else if(e.getClickedBlock().getType().equals(Material.DISPENSER)) {
				if(e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
					if(!e.getPlayer().hasPermission("craftex.bypass")) {
						e.setCancelled(true);
						e.getPlayer().sendMessage(ChatColor.RED + "You cannot open inventories in creative!");
					}
				}
			}else if(e.getClickedBlock().getType().equals(Material.HOPPER)) {
				if(e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
					if(!e.getPlayer().hasPermission("craftex.bypass")) {
						e.setCancelled(true);
						e.getPlayer().sendMessage(ChatColor.RED + "You cannot open inventories in creative!");
					}
				}
			}else if(e.getClickedBlock().getType().equals(Material.ANVIL)) {
				if(e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
					if(!e.getPlayer().hasPermission("craftex.bypass")) {
						e.setCancelled(true);
						e.getPlayer().sendMessage(ChatColor.RED + "You cannot open inventories in creative!");
					}
				}
			}else if(e.getClickedBlock().getType().equals(Material.CHIPPED_ANVIL)) {
				if(e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
					if(!e.getPlayer().hasPermission("craftex.bypass")) {
						e.setCancelled(true);
						e.getPlayer().sendMessage(ChatColor.RED + "You cannot open inventories in creative!");
					}
				}
			}else if(e.getClickedBlock().getType().equals(Material.DAMAGED_ANVIL)) {
				if(e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
					if(!e.getPlayer().hasPermission("craftex.bypass")) {
						e.setCancelled(true);
						e.getPlayer().sendMessage(ChatColor.RED + "You cannot open inventories in creative!");
					}
				}
			}else if(e.getClickedBlock().getType().equals(Material.CHEST_MINECART)) {
				if(e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
					if(!e.getPlayer().hasPermission("craftex.bypass")) {
						e.setCancelled(true);
						e.getPlayer().sendMessage(ChatColor.RED + "You cannot open inventories in creative!");
					}
				}
			}else if(e.getClickedBlock().getType().equals(Material.FURNACE_MINECART)) {
				if(e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
					if(!e.getPlayer().hasPermission("craftex.bypass")) {
						e.setCancelled(true);
						e.getPlayer().sendMessage(ChatColor.RED + "You cannot open inventories in creative!");
					}
				}
			}else if(e.getClickedBlock().getType().equals(Material.HOPPER_MINECART)) {
				if(e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
					if(!e.getPlayer().hasPermission("craftex.bypass")) {
						e.setCancelled(true);
						e.getPlayer().sendMessage(ChatColor.RED + "You cannot open inventories in creative!");
					}
				}
			}else if(e.getClickedBlock().getType().equals(Material.GRINDSTONE)) {
				if(e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
					if(!e.getPlayer().hasPermission("craftex.bypass")) {
						e.setCancelled(true);
						e.getPlayer().sendMessage(ChatColor.RED + "You cannot open inventories in creative!");
					}
				}
			}else if(e.getClickedBlock().getType().equals(Material.STONECUTTER)) {
				if(e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
					if(!e.getPlayer().hasPermission("craftex.bypass")) {
						e.setCancelled(true);
						e.getPlayer().sendMessage(ChatColor.RED + "You cannot open inventories in creative!");
					}
				}
			}else if(e.getClickedBlock().getType().toString().contains("SHULKER_BOX")) {
				if(e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
					if(!e.getPlayer().hasPermission("craftex.bypass")) {
						e.setCancelled(true);
						e.getPlayer().sendMessage(ChatColor.RED + "You cannot open inventories in creative!");
					}
				}
			}
		}else if(e.getAction() == Action.RIGHT_CLICK_AIR) {
			if(e.getItem()  != null) {
				if(e.getItem().getType().equals(Material.LIME_DYE)) {
					ItemStack i = e.getItem();
					GregorianCalendar cl = new GregorianCalendar();
					ArrayList<String> lore = (ArrayList<String>)i.getItemMeta().getLore();
					if(lore.get(0).contains(e.getPlayer().getName())) {
						String[] date = lore.get(2).split("/");
						String[] fnl = date[0].split(" ");
						String dag = fnl[2];
						if((Integer.valueOf(dag)>=cl.get(Calendar.DAY_OF_MONTH)) && (Integer.valueOf(date[1])>=cl.get(Calendar.MONTH)) || (Integer.valueOf(date[2])>=cl.get(Calendar.YEAR))) {
							Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cc open wholesale " + e.getPlayer().getName());
						}
					}
					
				}
			}
		}
	}
	
}
