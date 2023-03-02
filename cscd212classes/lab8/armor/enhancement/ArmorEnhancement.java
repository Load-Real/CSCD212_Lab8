package cscd212classes.lab8.armor.enhancement;

import cscd212classes.lab8.armor.base.ArmorBase;
import cscd212interfaces.lab8.armor.ArmorItem;

public abstract class ArmorEnhancement implements ArmorItem {
	private int armorPoints;
	private String description;
	private ArmorItem wrapperedArmorItem;
	
	public ArmorEnhancement(final int armorPoints, final String description, final ArmorItem wrapperedArmorItem) {
		if (description == null || wrapperedArmorItem == null || armorPoints <= 0 || description.isEmpty()) throw new IllegalArgumentException("Bad Params in ArmorEnhancement Constructor");
		
		this.armorPoints = armorPoints;
		this.description = description;
		this.wrapperedArmorItem = wrapperedArmorItem;
	}
	
	@Override
	public int getArmorPoints() {
		return wrapperedArmorItem.getArmorPoints() + this.armorPoints;
	}
	
	@Override
	public String getDescription() {
		if (wrapperedArmorItem instanceof ArmorBase) return wrapperedArmorItem.getDescription() + " with" + this.description;
		return wrapperedArmorItem.getDescription() + "," + this.description;
	}
}
