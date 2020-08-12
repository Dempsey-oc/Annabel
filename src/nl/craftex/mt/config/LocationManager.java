package nl.craftex.mt.config;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import nl.craftex.mt.util.LocationType;

public class LocationManager {

	public LocationManager() {}
	
	
	Plugin pl = Bukkit.getPluginManager().getPlugin("CraftMT");
	
	
	public FileConfiguration setup(Plugin p) {
		
		if(!p.getDataFolder().exists()) {
			p.getDataFolder().mkdir();
		}
		
		File file = new File(p.getDataFolder(), "locations.yml");
		
		if(!file.exists()) {
			try {
				file.createNewFile();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		if(!config.contains("slager")) {
			config.set("slager.world", "world");
			config.set("slager.x", 0);
			config.set("slager.y", 0);
			config.set("slager.z", 0);
			config.set("slager.yaw", 0);
			config.set("slager.pitch", 0);
			config.set("slager.enabled", false);
			try {
				config.save(file);
				System.out.println("[CraftMT] Generated Default 'locations.yml'!");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		return config;
		
	}
	
	public File setup2(Plugin p) {
		
		if(!p.getDataFolder().exists()) {
			p.getDataFolder().mkdir();
		}
		
		File file = new File(p.getDataFolder(), "locations.yml");
		
		if(!file.exists()) {
			try {
				file.createNewFile();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		
		if(!config.contains("slager")) {
			config.set("slager.world", "world");
			config.set("slager.x", 0);
			config.set("slager.y", 0);
			config.set("slager.z", 0);
			config.set("slager.yaw", 0);
			config.set("slager.pitch", 0);
			config.set("slager.enabled", false);
			try {
				config.save(file);
				System.out.println("[CraftMT] Generated Default 'locations.yml'!");
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
		return file;
		
	}
	
	public boolean state(LocationType t) {
		if(t.equals(LocationType.SLAGER)) {
			if(setup(pl).getBoolean("slager.enabled")) {
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	}
	
	public void t(LocationType t) {
		if(t.equals(LocationType.SLAGER)) {
			if(setup(pl).getBoolean("slager.enabled")) {
				setup(pl).set("slager.enabled", false);
				try {
					setup(pl).save(setup2(pl));
				}catch(Exception e) {
					e.printStackTrace();
				}
				YamlConfiguration.loadConfiguration(setup2(pl));
			}else {
				setup(pl).set("slager.enabled", true);
				try {
					setup(pl).save(setup2(pl));
				}catch(Exception e) {
					e.printStackTrace();
				}
				YamlConfiguration.loadConfiguration(setup2(pl));
			}
		}
	}
	
	public void setLocation(LocationType t, Location loc) {
		if(t.equals(LocationType.SLAGER)) {
			setup(pl).set("slager.world", loc.getWorld().getName());
			try {
				setup(pl).save(setup2(pl));
			}catch(Exception e) {
				e.printStackTrace();
			}
			YamlConfiguration.loadConfiguration(setup2(pl));
			setup(pl).set("slager.x", loc.getX());
			try {
				setup(pl).save(setup2(pl));
			}catch(Exception e) {
				e.printStackTrace();
			}
			YamlConfiguration.loadConfiguration(setup2(pl));
			setup(pl).set("slager.y", loc.getY());
			try {
				setup(pl).save(setup2(pl));
			}catch(Exception e) {
				e.printStackTrace();
			}
			YamlConfiguration.loadConfiguration(setup2(pl));
			setup(pl).set("slager.z", loc.getZ());
			try {
				setup(pl).save(setup2(pl));
			}catch(Exception e) {
				e.printStackTrace();
			}
			YamlConfiguration.loadConfiguration(setup2(pl));
			setup(pl).set("slager.yaw", loc.getYaw());
			try {
				setup(pl).save(setup2(pl));
			}catch(Exception e) {
				e.printStackTrace();
			}
			YamlConfiguration.loadConfiguration(setup2(pl));
			setup(pl).set("slager.pitch", loc.getPitch());
			try {
				setup(pl).save(setup2(pl));
			}catch(Exception e) {
				e.printStackTrace();
			}
			YamlConfiguration.loadConfiguration(setup2(pl));
			setup(pl).set("slager.enabled", state(t));
			try {
				setup(pl).save(setup2(pl));
			}catch(Exception e) {
				e.printStackTrace();
			}
			YamlConfiguration.loadConfiguration(setup2(pl));
		}
	}
	
	public Location getLocation(LocationType t) {
		if(t.equals(LocationType.SLAGER)) {
			Location loc = new Location(Bukkit.getWorld(setup(pl).getString("slager.world")),setup(pl).getDouble("slager.x"),setup(pl).getDouble("slager.y"),setup(pl).getDouble("slager.z"),Float.valueOf(setup(pl).getString("slager.yaw")),Float.valueOf(setup(pl).getString("slager.pitch")));
			return loc;
		}else {
			return null;
		}
	}
	
}
