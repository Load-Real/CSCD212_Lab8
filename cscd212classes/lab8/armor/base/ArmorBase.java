package cscd212classes.lab8.armor.base;

import cscd212interfaces.lab8.armor.*;

public abstract class ArmorBase implements ArmorItem{
	private int armorPoints;
	private String description;
	
	public ArmorBase(final int armorPoints, final String description) {
		if (description == null || armorPoints <= 0 || description.isEmpty()) throw new IllegalArgumentException("Bad Params in ArmorBase Constructor");
		
		this.armorPoints = armorPoints;
		this.description = description;
	}
	
	@Override
	public int getArmorPoints() {
		return this.armorPoints;
	}
	
	@Override
	public String getDescription() {
		return this.description;
	}
}
