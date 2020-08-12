package nl.craftex.mt.mechanics;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import com.sk89q.worldguard.protection.regions.RegionQuery;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import nl.craftex.mt.Main;
import nl.craftex.mt.config.PlayerData;
import nl.craftex.mt.util.MoveCache;
import nl.craftex.mt.util.Utils;

public class Move implements Listener{

	PlayerData c = new PlayerData();
	Random r = new Random();
	
	Plugin p = Bukkit.getPluginManager().getPlugin("CraftMT");
	
	ArrayList<Player> entered = new ArrayList<Player>();
	
	MoveCache mc = new MoveCache();
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent ev) {
		if(Utils.reqExpMet(ev.getPlayer())) {
			Player pl = ev.getPlayer();
			if(!pl.isFlying()) {
				if((ev.getFrom().getX() != ev.getTo().getX()) || (ev.getFrom().getZ() != ev.getTo().getZ())) {
					File f = c.getFile(Bukkit.getPluginManager().getPlugin("CraftMT"), ev.getPlayer());
					FileConfiguration conf = YamlConfiguration.loadConfiguration(f);
					conf.set("level", conf.getInt("level")+1);
					conf.set("exp", 0);
					try {
						conf.save(f);
					}catch(Exception e) {
						e.printStackTrace();
					}
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "pex user " + pl.getName() + " suffix &a[" + String.valueOf(conf.getInt("level")) + "] ");
					YamlConfiguration.loadConfiguration(f);
					pl.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§aJe bent een level omhoog gehaan naar: §7" + String.valueOf(conf.getInt("level"))));
					//ev.getPlayer().sendMessage(ChatColor.GREEN + "You have leveled up to: " + ChatColor.GRAY  + String.valueOf(c.read(Bukkit.getPluginManager().getPlugin("CraftMT"), ev.getPlayer()).getInt("level")));
				}
			}
		} else {
			if(!mc.filled(ev.getPlayer())) {
				mc.add(ev.getPlayer(), 1);
			} else {
				mc.remove(ev.getPlayer());
				int crn = c.setup(p, ev.getPlayer()).getInt("exp");
				int res = crn+Utils.rand(10, 25);
				Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cexp add " + ev.getPlayer().getName() + " " + String.valueOf(res));
				if(ev.getPlayer().hasPermission("minetopia.fame")) {
					Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "cexp add " + ev.getPlayer().getName() + " " + String.valueOf(res/2));
				}
			}
		}
		
		
		if((ev.getFrom().getX() != ev.getTo().getX()) || (ev.getFrom().getZ() != ev.getTo().getZ())) {
			enterRegion(ev.getPlayer());
		
		}
		
	}
	
	public void enterRegion(Player player) {
        LocalPlayer localPlayer = Main.worldGuardPlugin.wrapPlayer(player);
        Location playerVector = localPlayer.getLocation();
        RegionContainer con = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionQuery rq = con.createQuery();
        ApplicableRegionSet arse = rq.getApplicableRegions(playerVector);
        for(ProtectedRegion pr : arse.getRegions()) {
        	if(pr.getId().contains("bushok") && !entered.contains(player)) {
        		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "wmenu " + player.getName());
        	}else if(!pr.getId().contains("bushok") && entered.contains(player)) {
        		entered.remove(player);
        	}
        }  
        
    }
	
}
