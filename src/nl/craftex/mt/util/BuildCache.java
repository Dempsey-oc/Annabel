package nl.craftex.mt.util;

import java.util.HashMap;

import org.bukkit.entity.Player;

public class BuildCache {

	private HashMap<Player, Integer> stor = new HashMap<Player, Integer>();
	
	public BuildCache() {}
	
	public void add(Player player, int i) {
		if(!stor.containsKey(player)) {
			stor.put(player, i);
		}else {
			int c = stor.get(player);
			stor.put(player, c+i);
		}
	}
	
	public boolean filled(Player player) {
		if(stor.containsKey(player)) {
			return stor.get(player) >= 10;
		}else {
			return false;
		}
	}
	
	public void remove(Player player) {
		stor.remove(player);
	}
	
}
