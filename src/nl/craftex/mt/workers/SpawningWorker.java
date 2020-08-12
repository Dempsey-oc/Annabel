package nl.craftex.mt.workers;

import org.bukkit.entity.Chicken;
import org.bukkit.entity.Cow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Rabbit;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import nl.craftex.mt.config.LocationManager;
import nl.craftex.mt.util.LocationType;
import nl.craftex.mt.util.MobCache;

public class SpawningWorker implements Runnable{

	MobCache mc = new MobCache();
	
	LocationManager lm = new LocationManager();
	
	public void run() {
		//if(lm.state(LocationType.SLAGER)) {
			for(LivingEntity e : mc.list()) {
				if(!lm.getLocation(LocationType.SLAGER).getWorld().getNearbyEntities(lm.getLocation(LocationType.SLAGER), 8, 8, 8).contains(e)) {
					e.teleport(lm.getLocation(LocationType.SLAGER));
				}
			}
			if(mc.size() <= 2) {
				//pig
				Pig p = (Pig) lm.getLocation(LocationType.SLAGER).getWorld().spawnEntity(lm.getLocation(LocationType.SLAGER), EntityType.PIG);
				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20, 99999),  true);
				mc.add(p);
				
			}else if(mc.size() > 2 && mc.size() <= 5) {
				//cow
				
				Cow c = (Cow)lm.getLocation(LocationType.SLAGER).getWorld().spawnEntity(lm.getLocation(LocationType.SLAGER), EntityType.COW);
				c.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20, 99999),  true);
				mc.add(c);
				
			}else if(mc.size() > 5 && mc.size() <= 8) {
				//chicken
				Chicken c = (Chicken) lm.getLocation(LocationType.SLAGER).getWorld().spawnEntity(lm.getLocation(LocationType.SLAGER), EntityType.CHICKEN);
				c.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20, 99999),  true);
				mc.add(c);
				
			}else if(mc.size() > 8 && mc.size() > 10) {
				// rabbit
				Rabbit r = (Rabbit) lm.getLocation(LocationType.SLAGER).getWorld().spawnEntity(lm.getLocation(LocationType.SLAGER), EntityType.RABBIT);
				r.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20, 99999),  true);
				mc.add(r);
				
			}
		//}
	}
	
}
