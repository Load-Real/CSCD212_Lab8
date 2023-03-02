package cscd212classes.lab5.lifeform;

import java.util.ArrayList;

import cscd212classes.lab8.armor.base.WoodBaseArmor;
import cscd212classes.lab8.armor.damage.ArmorDamage;
import cscd212interfaces.lab8.armor.ArmorItem;

/**
 * A Human is a LifeForm that can wear ArmorItems to negate incoming damage. A Human can also keep
 * ArmorItems in its inventory.
 */
public class Human extends LifeForm
{
   /**
    * The ArmorItem representing this Human's currently equipped ArmorItem.
    */
   private ArmorItem equippedArmor;

   /**
    * The ArrayList of ArmorItems representing this Human's inventory.
    */
   private ArrayList<ArmorItem> inventory;

   /**
    * The constructor to create a Human object which delegates the assignment of name,
    * currentLifePoints, and maxLifePoints to the parent constructor and then sets equippedArmor to
    * the passed parameter.
    * @param  name                     - this Human's name
    * @param  currentLifePoints        - this Human's current life points
    * @param  maxLifePoints            - this Human's maximum life points
    * @param  equippedArmor            - this Human's currently equipped ArmorItem
    * @throws IllegalArgumentException - if equippedArmor is null or if equippedArmor has 0 or less
    *                                  armor points
    */
   public Human(final String name, final int currentLifePoints, final int maxLifePoints,
         final ArmorItem equippedArmor)
   {
      super(name, currentLifePoints, maxLifePoints);

      if (equippedArmor == null || equippedArmor.getArmorPoints() <= 0)
         throw new IllegalArgumentException("Bad Params in Human Constructor");

      this.equippedArmor = equippedArmor;

      this.inventory = new ArrayList<ArmorItem>();
   }

   /**
    * The constructor to create a Human object which specifies name and currentLifePoints. Default
    * MaxLifePoints for human is 100, default armor item for human is WoodBaseArmor with no
    * enhancements.
    * @param name              - this Human's name
    * @param currentLifePoints - this Human's current life points
    */
   public Human(final String name, final int currentLifePoints)
   {
      this(name, currentLifePoints, 100, new WoodBaseArmor());
   }

   /**
    * Returns a reference to this Humans equipped armor item if it exists, and null otherwise.
    * @return ArmorItem - this human's currently equipped armor item if it exists or null otherwise
    */
   public ArmorItem getEquippedArmorItem()
   {
      return this.equippedArmor;
   }

   /**
    * Sets the Human's currently equipped armor to passed ArmorItem and adds the ArmorItem that was
    * previously equipped (if existing) to the Human's inventory. If the new Armor Item is already
    * in this Human's inventory, this method will remove it from the inventory before equipping.
    * @param  newEquippedArmorItem     - Representing this Human's new equipped armor item.
    * @throws IllegalArgumentException - if equippedArmor is null or if equippedArmor has 0 or less
    *                                  armor points
    */
   public void setEquippedArmorItem(final ArmorItem newEquippedArmorItem)
   {
      if (newEquippedArmorItem == null || newEquippedArmorItem.getArmorPoints() <= 0)
         throw new IllegalArgumentException("Bad Params in setEquippedArmorItem");

      if (this.equippedArmor != null)
         this.inventory.add(this.equippedArmor);

      this.inventory.remove(newEquippedArmorItem);

      this.equippedArmor = newEquippedArmorItem;

   }

   /**
    * Returns a reference to this Human's inventory.
    * @return - ArrayList of ArmorItems representing this Human's inventory
    */
   public ArrayList<ArmorItem> getInventory()
   {
      return this.inventory;
   }

   /**
    * Calls the parent's toString and if this Human is wearing armor, appends " and is wearing " +
    * {description of equipped armor} + " with " + {equipped armor's armor point value} + " armor
    * points". If this Human is not wearing armor, appends " and is not wearing any armor"
    * @return String describing this humans name, life points, and equippedArmor
    */
   @Override
   public String toString()
   {
      if (this.equippedArmor != null)
         return super.toString() + " and is wearing " + this.equippedArmor.getDescription()
               + " with " + this.equippedArmor.getArmorPoints() + " armor points";
      return super.toString() + " and is not wearing any armor";
   }

   /**
    * Applies the specified damage to this human, first reducing the equipped Armor Item's armor
    * points to 0 and then reducing the Human's life points when no armor exists. This method will
    * unequip and discard any worn armor that would have its armor value reduced to 0, meaning
    * this.equippedArmor will be set to null and no reference will be saved to the discarded item in
    * this class. Damage is applied to ArmorItems by wrapping the item in an ArmorDamage object
    * using the decorator pattern. Any reduction of life points is delegated to the parent takeHit
    * method.
    * @param  damage                   - the int representing damage being applied to this Human
    * @throws IllegalArgumentException - if the incoming damage is <= 0
    */
   @Override
   public void takeHit(final int damage)
   {
      if (damage <= 0)
         throw new IllegalArgumentException("Bad Params in takeHit");

      // human not wearing armor
      if (this.equippedArmor == null)
         super.takeHit(damage);

      // damage will not destroy armor
      else if (damage < this.equippedArmor.getArmorPoints())
         this.equippedArmor = new ArmorDamage(damage, this.equippedArmor);

      // damage will destroy armor and it should be discarded
      else
      {
         if(damage != this.equippedArmor.getArmorPoints()) //super takeHit throws exception on 0 damage
            super.takeHit(damage - this.equippedArmor.getArmorPoints());
         
         this.equippedArmor = null;
      }

   }

}
