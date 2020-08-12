package nl.craftex.mt.mechanics;

import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import net.md_5.bungee.api.ChatColor;
import nl.craftex.mt.util.MobCache;
import nl.craftex.mt.util.Utils;

public class Pvp implements Listener{

	MobCache mc = new MobCache();
	
	@EventHandler
	public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
		if(!(e.getEntity() instanceof ArmorStand) && !(e.getEntity() instanceof ItemFrame) && (e.getEntity() instanceof Player)) {
			if(e.getDamager() instanceof Player) {
				if(!Utils.wapens.contains(((Player)e.getDamager()).getInventory().getItemInMainHand().getType())) {
					e.setCancelled(true);
				}
				Player et = (Player)e.getEntity();
				//if(Utils.illegal(et)) {
				//	double dmg = e.getDamage();
				//	double dres = dmg*2.5;
				//	e.setDamage(dres);
				//}

			}
			
		} else {
			if(e.getEntity() instanceof LivingEntity) {
				if(mc.contains((LivingEntity)e.getEntity())) {
					if(e.getDamager() instanceof Player) {
						if(!((Player)e.getDamager()).hasPermission("minetopia.slager")) {
							e.setCancelled(true);
							((Player)e.getDamager()).sendMessage(ChatColor.RED + "Deze dieren zijn alleen te vermoorden door een slager!");
						}
					}
				}
			}
		}
		
		
	}
	
}
