package nl.craftex.mt;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.milkbowl.vault.economy.Economy;
import nl.craftex.mt.commands.Atm;
import nl.craftex.mt.commands.Bedrijf;
import nl.craftex.mt.commands.Cexp;
import nl.craftex.mt.commands.Color;
import nl.craftex.mt.commands.Cutil;
import nl.craftex.mt.commands.Discord;
import nl.craftex.mt.commands.Level;
import nl.craftex.mt.commands.Padd;
import nl.craftex.mt.commands.Pinfo;
import nl.craftex.mt.commands.Plots;
import nl.craftex.mt.commands.Premove;
import nl.craftex.mt.commands.SetPrefix;
import nl.craftex.mt.commands.SetSuffix;
import nl.craftex.mt.commands.TexturePack;
import nl.craftex.mt.commands.Vergunning;
import nl.craftex.mt.commands.WMenu;
import nl.craftex.mt.commands.Wholesale;
import nl.craftex.mt.config.LocationManager;
import nl.craftex.mt.gui.InventoryHandler;
import nl.craftex.mt.mechanics.AEquip;
import nl.craftex.mt.mechanics.Break;
import nl.craftex.mt.mechanics.CommandPreProcess;
import nl.craftex.mt.mechanics.Consume;
import nl.craftex.mt.mechanics.Crafting;
import nl.craftex.mt.mechanics.Death;
import nl.craftex.mt.mechanics.Fishing;
import nl.craftex.mt.mechanics.Interact;
import nl.craftex.mt.mechanics.Join;
import nl.craftex.mt.mechanics.Melt;
import nl.craftex.mt.mechanics.MobDeath;
import nl.craftex.mt.mechanics.Move;
import nl.craftex.mt.mechanics.Place;
import nl.craftex.mt.mechanics.Pvp;
import nl.craftex.mt.mechanics.Tame;
import nl.craftex.mt.util.MobCache;
import nl.craftex.mt.util.Utils;
import nl.craftex.mt.workers.PaymentWorker;
import nl.craftex.mt.workers.SpawningWorker;

public class Main extends JavaPlugin {
	
	public final String TOKEN = "NzEwNjA2MjU2NDY5MDQ5Mzc2.Xr26Fg.Zz0cxmRR4gdsmpVEYgychbWUIDo";
	public static JDA jda;
	
	public static Economy econ;
	public LocationManager lm = new LocationManager();
	
	public Plugin p = this;
	
	public void start() {
		try {
			jda = new JDABuilder(AccountType.BOT).setToken(TOKEN).build(); 
			jda.getPresence().setStatus(OnlineStatus.DO_NOT_DISTURB);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean setupEconomy() {
	   if (getServer().getPluginManager().getPlugin("Vault") == null) {
	       return false;
	   }
	   RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
	   if (rsp == null) {
	        return false;
	   }
	   econ = rsp.getProvider();
	   return econ != null;
	}
	
	public boolean setupCompanies() {
		if(getServer().getPluginManager().getPlugin("CompaniesReloaded") == null) {
			return false;
		}else {
			return true;
		}		
	}
	
	public static WorldGuardPlugin getWorldGuard() {
        Plugin plugin = Bukkit.getServer().getPluginManager().getPlugin("WorldGuard");

        if (plugin == null || !(plugin instanceof WorldGuardPlugin)) {
            return null;
        }

        return (WorldGuardPlugin) plugin;
    }
	
	public Main() {}
	
	@Override
	public void onEnable() {
		
		//start();
		
		if(!setupCompanies()) {
			getServer().getPluginManager().disablePlugin(this);
			System.err.println("CompaniesReloaded not found!");
		}
		
		if(!setupEconomy()) {
			getServer().getPluginManager().disablePlugin(this);
			System.err.println("Vault not found!");
		}
		
		if(!getDataFolder().exists()) {
			getDataFolder().mkdir();
		}
		
		// Commands
		getCommand("plots").setExecutor(new Plots());
		getCommand("padd").setExecutor(new Padd());
		getCommand("premove").setExecutor(new Premove());
		getCommand("pinfo").setExecutor(new Pinfo());
		getCommand("discord").setExecutor(new Discord());
		getCommand("level").setExecutor(new Level());
		getCommand("setprefix").setExecutor(new SetPrefix());
		getCommand("setsuffix").setExecutor(new SetSuffix());
		//getCommand("levelup").setExecutor(new LevelUp());
		getCommand("atm").setExecutor(new Atm());
		getCommand("pass").setExecutor(new Wholesale());
		getCommand("vergunning").setExecutor(new Vergunning());
		getCommand("wmenu").setExecutor(new WMenu());
		getCommand("bedrijf").setExecutor(new Bedrijf());
		getCommand("texturepack").setExecutor(new TexturePack());
		getCommand("color").setExecutor(new Color());
		getCommand("cexp").setExecutor(new Cexp());
		getCommand("cutil").setExecutor(new Cutil());
		
		//Events
		getServer().getPluginManager().registerEvents(new Consume(), this);
		getServer().getPluginManager().registerEvents(new Fishing(), this);
		getServer().getPluginManager().registerEvents(new Join(), this);
		getServer().getPluginManager().registerEvents(new Move(), this);
		//getServer().getPluginManager().registerEvents(new Chat(), this);
		getServer().getPluginManager().registerEvents(new Break(), this);
		getServer().getPluginManager().registerEvents(new Interact(), this);
		getServer().getPluginManager().registerEvents(new Place(), this);
		getServer().getPluginManager().registerEvents(new CommandPreProcess(), this);
		getServer().getPluginManager().registerEvents(new Crafting(), this);
		getServer().getPluginManager().registerEvents(new InventoryHandler(), this);
		getServer().getPluginManager().registerEvents(new Pvp(), this);
		getServer().getPluginManager().registerEvents(new Death(), this);
		getServer().getPluginManager().registerEvents(new Tame(), this);
		getServer().getPluginManager().registerEvents(new Melt(), this);
		getServer().getPluginManager().registerEvents(new MobDeath(), this);
		getServer().getPluginManager().registerEvents(new AEquip(), this);
		
		// Util configs
		lm.setup(this);
		
		//Workers
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new SpawningWorker(), 1000, 1000);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new PaymentWorker(), 36000, 36000);
		
		Utils.wapens.add(Material.FERMENTED_SPIDER_EYE);
		Utils.wapens.add(Material.GOLDEN_AXE);
	}
	
	MobCache mc = new MobCache();
	
	@Override
	public void onDisable() {
		
		if(!mc.list().isEmpty()) {
			System.out.print("Worker spawn cleanup.....");
			for(LivingEntity e : mc.list()) {
				e.damage(1000);
			}
		}
	}
	
	
	public static WorldGuardPlugin worldGuardPlugin = getWorldGuard();
	

	
}
