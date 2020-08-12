package nl.craftex.mt.mechanics;

import org.bukkit.Material;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Rabbit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;

import nl.craftex.mt.util.MobCache;

public class MobDeath implements Listener{

	
	MobCache mc = new MobCache();
	
	@EventHandler
	public void onEntityDeath(EntityDeathEvent e) {
		if(e.getEntity() instanceof Cow) {
			e.getDrops().clear();
			e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(Material.COOKED_BEEF,1));
		}else if(e.getEntity() instanceof Rabbit) {
			e.getDrops().clear();
			e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), new ItemStack(Material.COOKED_RABBIT,1));
		}
		
		if(mc.contains(e.getEntity())) {
			mc.remove(e.getEntity());
		}
	}
	
}
