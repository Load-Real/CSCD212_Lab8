package cscd212tests.lab8;

import cscd212classes.lab8.armor.enhancement.ArmorEnhancement;
import cscd212interfaces.lab8.armor.ArmorItem;

public class TestArmorEnhancement extends ArmorEnhancement
{
   public TestArmorEnhancement(final int armorPoints, final String description,
         final ArmorItem wrapperedArmorItem)
   {
      super(armorPoints, description, wrapperedArmorItem);
   }
}
