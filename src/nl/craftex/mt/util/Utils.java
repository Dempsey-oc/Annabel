package nl.craftex.mt.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import eu.mrapik.companies.Companies;
import eu.mrapik.companies.company.Company;
import eu.mrapik.companies.employee.Employee;
import nl.craftex.mt.config.PlayerData;

public class Utils {
	
	public static ArrayList<Material> wapens = new ArrayList<Material>();
	public static ArrayList<Player> teleported = new ArrayList<Player>();
	private static PlayerData c = new PlayerData();
	private static Plugin p = Bukkit.getServer().getPluginManager().getPlugin("CraftMT");
	
	public static UUID getUID(String name) {
		if(Bukkit.getPlayer(name) != null) {
			return Bukkit.getPlayer(name).getUniqueId();
		} else {
			return UUID.fromString(MojangAPI.getParsedUID(name));
		}
	}
	
	private static boolean diamond(Player player) {
		if(player.getInventory().getHelmet().getType().toString().contains("DIAMOND") || player.getInventory().getChestplate().getType().toString().contains("DIAMOND") || player.getInventory().getLeggings().getType().toString().contains("DIAMOND") || player.getInventory().getBoots().getType().toString().contains("DIAMOND")) {
			return true;
		}else {
			return false;
		}
	}
	
	
	private static boolean chain(Player player) {
		if(player.getInventory().getHelmet().getType().toString().contains("CHAINMAIL") || player.getInventory().getChestplate().getType().toString().contains("CHAINMAIL") || player.getInventory().getLeggings().getType().toString().contains("CHAINMAIL") || player.getInventory().getBoots().getType().toString().contains("CHAINMAIL")) {
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean illegal(Player player) {
		if(chain(player) || diamond(player)) {
			return true;
		}else if(chain(player) && diamond(player)){
			return true;
		}else {
			return false;
		}
	}

	private static Random r = new Random();
	public static int rand(int min, int max) {
		int res = r.nextInt(max);
		if(res <= min) {
			return min;
		}else {
			return res;
		}
	}
	
	
	public static boolean reqExpMet(Player player) {
		return c.setup(p, player).getInt("exp") >= ((c.setup(p, player).getInt("level")*150)+Utils.rand(100,450));
	}
	
	public static void reloadConfig(Plugin p) {
		if(p.getDataFolder().exists()) {
			File f = new File(p.getDataFolder(), "config.yml");
			if(f.exists()) {
				YamlConfiguration.loadConfiguration(f);
			}
			
		}
	}
	
	public static void sendToEmployees(Company c, String message) {
		for(Employee e : Companies.getInstance().getEmployeesManager().getEmployeesByCompany(c.getName())) {
			if(Bukkit.getPlayer(e.getUuid()) != null) {
				Bukkit.getPlayer(e.getUuid()).sendMessage(message);
			}
		}
	}
}
