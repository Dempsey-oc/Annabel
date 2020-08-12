package nl.craftex.mt.util;

import java.util.ArrayList;

import org.bukkit.entity.LivingEntity;

public class MobCache {

	ArrayList<LivingEntity> l = new ArrayList<LivingEntity>();
	
	public MobCache() {}
	
	public void add(LivingEntity e) {
		l.add(e);
	}
	
	public void remove(LivingEntity e) {
		l.remove(e);
	}
	
	public boolean contains(LivingEntity e) {
		if(l.isEmpty()) {
			return false;
		}else {
			return l.contains(e);
		}
	}
	
	public int size() {
		return l.size();
	}
	
	public ArrayList<LivingEntity> list() {
		return l;
	}
	
}
