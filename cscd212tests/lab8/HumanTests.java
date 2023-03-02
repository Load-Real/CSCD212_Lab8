package cscd212tests.lab8;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import cscd212classes.lab5.lifeform.*;
import cscd212classes.lab8.armor.base.*;
import cscd212classes.lab8.armor.damage.ArmorDamage;
import cscd212interfaces.lab8.armor.*;

public class HumanTests
{
   private Human testHuman;
   private ArmorItem testArmor;

   @Nested
   @DisplayName("Human Constructor Tests")
   public class HumanConstructorTests
   {
      @Nested
      @DisplayName("Human constructor (String, int, int, ArmorItem)")
      public class HumanConstructorFourParamTests
      {
         @BeforeEach
         public void setup()
         {
            testArmor = new IronBaseArmor();
            testHuman = new Human("Test Human", 100, 150, testArmor);
         }

         @Test
         @DisplayName("Human constructor (\"Test Human\", 100, 150, testArmor) sets name correctly")
         public void name()
         {
            assertEquals("Test Human", testHuman.getName(), "getName");

         }

         @Test
         @DisplayName("Human constructor (\"Test Human\", 100, 150, testArmor) sets currentLifePoints correctly")
         public void currentLifePoints()
         {
            assertEquals(100, testHuman.getCurrentLifePoints(), "getCurrentLifePoints");
         }

         @Test
         @DisplayName("Human constructor (\"Test Human\", 100, 150, testArmor) sets equippedArmor correctly")
         public void equippedArmor()
         {
            assertSame(testArmor, testHuman.getEquippedArmorItem(), "getEquippedArmorItem");
         }

         @Test
         @DisplayName("Human constructor (\"Test Human\", 100, 150, testArmor) initializes inventory")
         public void humanConstructorInitializesInventory()
         {
            assertNotNull(testHuman.getInventory(), "getInventory");
         }
      }

      @Nested
      @DisplayName("Human constructor (\"Test Human\", 50)")
      public class HumanConstructorTwoParamTests
      {
         @BeforeEach
         public void setup()
         {
            testHuman = new Human("Test Human", 50);
         }

         @Test
         @DisplayName("Human constructor (\"Test Human\", 50) sets name correctly")
         public void name()
         {
            assertEquals("Test Human", testHuman.getName(), "getName");

         }

         @Test
         @DisplayName("Human constructor (\"Test Human\", 50) sets currentLifePoints correctly")
         public void currentLifePoints()
         {
            assertEquals(50, testHuman.getCurrentLifePoints(), "getCurrentLifePoints");
         }

         @Test
         @DisplayName("Human constructor (\"Test Human\", 50) sets default equippedArmor correctly")
         public void equippedArmor()
         {
            assertEquals("Wood Armor Base", testHuman.getEquippedArmorItem().getDescription(), "getEquippedArmorItem().getDescription()");
         }

         @Test
         @DisplayName("Human constructor (\"Test Human\", 50) initializes inventory")
         public void humanConstructorInitializesInventory()
         {
            assertNotNull(testHuman.getInventory(), "getInventory");
         }
      }
   }

   @Nested
   @DisplayName("Tests that require equippedArmor to be null")
   public class EquippedArmorNullTestss
   {

      @BeforeEach
      public void setup()
      {
         testArmor = new IronBaseArmor();
         testHuman = new Human("Test Human", 100, 150, testArmor);
         testHuman.takeHit(80);
         testArmor = new ObsidianBaseArmor();
      }

      @Test
      @DisplayName("If a Human's equippedArmor property is null, getEquippedArmorItem returns null")
      public void getEquippedArmorItemTest()
      {
         assertNull(testHuman.getEquippedArmorItem());
      }

      @Test
      @DisplayName("If a Human's equippedArmor property is null, setEquippedArmorItem sets equippedArmor to the passed item")
      public void setEquippedArmorItemTest()
      {
         testHuman.setEquippedArmorItem(testArmor);
         assertSame(testArmor, testHuman.getEquippedArmorItem());
      }

      @Test
      @DisplayName("If a Human's equippedArmor property is null, setEquippedArmorItem does not add null to the inventory ArrayList")
      public void setEquippedArmorItemDoesNotChangeInventory()
      {
         testHuman.setEquippedArmorItem(testArmor);
         assertEquals(0, testHuman.getInventory().size());
      }

      @Test
      @DisplayName("If a Human's equippedArmor property is null, toString prints correctly")
      public void toStringWorks()
      {
         assertEquals("Test Human has 100 life points and is not wearing any armor",
               testHuman.toString());
      }

      @Test
      @DisplayName("If a Human's equippedArmor property is null, takeHit reduces the Human's health points by the amount damaged when damage is less than HP")
      public void takeHitWorksLess()
      {
         testHuman.takeHit(50);
         assertEquals(50, testHuman.getCurrentLifePoints());
      }

      @Test
      @DisplayName("If a Human's equippedArmor property is null, takeHit reduces the Human's health points by the amount damaged when damage is equal to HP")
      public void takeHitWorksEqual()
      {
         testHuman.takeHit(100);
         assertEquals(0, testHuman.getCurrentLifePoints());
      }

      @Test
      @DisplayName("If a Human's equippedArmor property is null, takeHit reduces the Human's health pointsto zero when the damage is greater than HP")
      public void takeHitWorksMore()
      {
         testHuman.takeHit(1000);
         assertEquals(0, testHuman.getCurrentLifePoints());
      }
   }

   @Nested
   @DisplayName("Tests that require equippedArmor to not be null")
   public class EquippedArmorNotNullTests
   {
      @BeforeEach
      public void setup()
      {
         testArmor = new IronBaseArmor();
         testHuman = new Human("Test Human", 100, 150, testArmor);
      }

      @Test
      @DisplayName("If a Human's equippedArmor property is NOT null, getEquippedArmorItem returns the equippedArmor ArmorItem")
      public void getEquippedArmorItemTest()
      {
         assertSame(testArmor, testHuman.getEquippedArmorItem());
      }

      @Test
      @DisplayName("If a Human's equippedArmor property is NOT null, setEquippedArmorItem sets equippedArmor to the passed item")
      public void setEquippedArmorItemTest()
      {
         testArmor = new ObsidianBaseArmor();
         testHuman.setEquippedArmorItem(testArmor);
         assertSame(testArmor, testHuman.getEquippedArmorItem());
      }

      @Test
      @DisplayName("If a Human's equippedArmor property is NOT null, setEquippedArmorItem adds the former equippedArmor to the inventory ArrayList")
      public void setEquippedArmorItemDoesNotChangeInventory()
      {
         ArmorItem oldArmor = testHuman.getEquippedArmorItem();
         testArmor = new ObsidianBaseArmor();
         testHuman.setEquippedArmorItem(testArmor);
         assertSame(oldArmor, testHuman.getInventory().get(0));
      }

      @Test
      @DisplayName("If a Human's equippedArmor property is NOT null, toString prints correctly")
      public void toStringWorks()
      {
         assertEquals(
               "Test Human has 100 life points and is wearing Iron Armor Base with 80 armor points",
               testHuman.toString());
      }

      @Test
      @DisplayName("If a Human's equippedArmor property is NOT null, takeHit reduces the equippedArmor's points by the amount damaged when damage is less than armorPoints")
      public void takeHitWorksLess()
      {
         testHuman.takeHit(50);
         assertEquals(30, testHuman.getEquippedArmorItem().getArmorPoints());
      }

      @Test
      @DisplayName("If a Human's equippedArmor property is NOT null, takeHit reduces the Human's health by damage not applied when damage is greater than armorPoints")
      public void takeHitWorksMoreHealth()
      {
         testHuman.takeHit(100);
         assertEquals(80, testHuman.getCurrentLifePoints());
      }

      @Test
      @DisplayName("If a Human's equippedArmor property is NOT null, takeHit sets equippedArmor to null if its points are reduced to 0")
      public void takeHitWorksEquippedArmorNull()
      {
         testHuman.takeHit(80);
         assertNull(testHuman.getEquippedArmorItem());
      }
   }
   
   @Nested
   @DisplayName("Preconditions")
   public class Preconditions
   {
      @BeforeEach
      public void setup()
      {
         testArmor = new IronBaseArmor();
         testHuman = new Human("Test Human", 100, 150, testArmor);
      }

      @Test
      @DisplayName("Human constructor throws IllegalArgumentException if equippedArmor is null")
      public void humanPreconditionEquippedArmorNull()
      {
         assertThrows(IllegalArgumentException.class, () -> new Human("test", 100, 150, null));
      }
      
      @Test
      @DisplayName("Human constructor throws IllegalArgumentException if equippedArmor has less than 0 armor value")
      public void humanPreconditionEquippedArmorPointsLessThanZero()
      {
         ArmorItem testItem = new ArmorDamage(1000, new WoodBaseArmor());
         assertThrows(IllegalArgumentException.class, () -> new Human("test", 100, 150, testItem));
      }
      
      @Test
      @DisplayName("Human constructor throws IllegalArgumentException if equippedArmor has 0 armor value")
      public void humanPreconditionEquippedArmorPointsZero()
      {
         ArmorItem testItem = new ArmorDamage(50, new WoodBaseArmor());
         assertThrows(IllegalArgumentException.class, () -> new Human("test", 100, 150, testItem));
      }
      
      @Test
      @DisplayName("Human setEquippedArmorItem throws IllegalArgumentException if newEquippedArmorItem is null")
      public void humanPreconditionSetEquippedArmorNull()
      {
         assertThrows(IllegalArgumentException.class, () -> testHuman.setEquippedArmorItem(null));
      }
      
      @Test
      @DisplayName("Human setEquippedArmorItem throws IllegalArgumentException if newEquippedArmorItem has less than 0 armor value")
      public void humanPreconditionSetEquippedArmorPointsLessThanZero()
      {
         ArmorItem testItem = new ArmorDamage(1000, new WoodBaseArmor());
         assertThrows(IllegalArgumentException.class, () -> testHuman.setEquippedArmorItem(testItem));
      }
      
      @Test
      @DisplayName("Human setEquippedArmorItem throws IllegalArgumentException if newEquippedArmorItem has 0 armor value")
      public void humanPreconditionSetEquippedArmorPointsZero()
      {
         ArmorItem testItem = new ArmorDamage(50, new WoodBaseArmor());
         assertThrows(IllegalArgumentException.class, () -> testHuman.setEquippedArmorItem(testItem));
      }
      
   }
}
