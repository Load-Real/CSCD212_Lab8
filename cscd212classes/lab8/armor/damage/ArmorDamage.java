package cscd212classes.lab8.armor.damage;

import cscd212interfaces.lab8.armor.ArmorItem;

public class ArmorDamage implements ArmorItem {
	private int armorPoints;
	private ArmorItem wrapperedArmorItem;
	
	public ArmorDamage(final int armorPoints, final ArmorItem wrapperedArmorItem) {
		if (armorPoints <= 0 || wrapperedArmorItem == null) throw new IllegalArgumentException("Bad Params in ArmorDamage Constructor");
		
		this.armorPoints = armorPoints;
		this.wrapperedArmorItem = wrapperedArmorItem;
	}
	
	@Override
	public int getArmorPoints() {
		return this.wrapperedArmorItem.getArmorPoints() - this.armorPoints;
	}
	
	@Override
	public String getDescription() {
		return this.wrapperedArmorItem.getDescription();
	}
}
