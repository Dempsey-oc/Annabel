package nl.craftex.mt.mechanics;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;

public class Crafting implements Listener {

	@EventHandler
	public void onPrepareItemCraft(PrepareItemCraftEvent e) {
		for(HumanEntity he : e.getViewers()) {
			if(he instanceof Player) {
				Player t = (Player)he;
				if(!t.hasPermission("craftex.craft")) {
					e.getInventory().setResult(null);
				}
			}
		}
	}
	
}
