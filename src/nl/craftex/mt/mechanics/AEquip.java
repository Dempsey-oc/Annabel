package nl.craftex.mt.mechanics;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.ItemMeta;

import com.codingforcookies.armorequip.ArmorEquipEvent;

public class AEquip implements Listener{

	@EventHandler
	public void onArmorEquip(ArmorEquipEvent e) {
		if(e.getNewArmorPiece() != null) {
			ItemMeta im = e.getNewArmorPiece().getItemMeta();
			im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
			im.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
			im.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier("generic.armor", -10, AttributeModifier.Operation.ADD_NUMBER));
			e.getNewArmorPiece().setItemMeta(im);
		}
		/*ItemStack nmsStack = CraftItemStack.asNMSCopy(e.getNewArmorPiece());
		NBTTagCompound compound = (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();
		NBTTagList modifiers = new NBTTagList();
		NBTTagCompound armor = new NBTTagCompound();
		armor.set("Name", new NBTTagString("generic.armor"));
		armor.set("Amount", new NBTTagString("generic.armor"));
		*/
	}	
}
