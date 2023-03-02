package cscd212tests.lab8;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import cscd212classes.lab8.armor.base.IronBaseArmor;
import cscd212classes.lab8.armor.damage.ArmorDamage;
import cscd212classes.lab8.armor.enhancement.*;
import cscd212classes.lab8.armor.base.*;
import cscd212interfaces.lab8.armor.ArmorItem;

public class ArmorTests
{
   private ArmorItem testArmor;

   @Nested
   @DisplayName("Armor Constructor Tests")
   public class ArmorConstructorTests
   {
      @Nested
      @DisplayName("ArmorBase concrete constructors work")
      public class ArmorBaseConstructorTests
      {
         @Nested
         @DisplayName("Wood")
         public class WoodTests
         {
            @BeforeEach
            public void setup()
            {
               testArmor = new WoodBaseArmor();

            }

            @Test
            @DisplayName("new WoodBaseArmor() works. getArmorPoints returns 50")
            public void getArmorPoints()
            {
               assertEquals(50, testArmor.getArmorPoints());
            }

            @Test
            @DisplayName("new WoodBaseArmor() works. getDescription returns \"Wood Armor Base\"")
            public void getDescription()
            {
               assertEquals("Wood Armor Base", testArmor.getDescription());
            }

         }

         @Nested
         @DisplayName("Iron")
         public class IronTests
         {
            @BeforeEach
            public void setup()
            {
               testArmor = new IronBaseArmor();

            }

            @Test
            @DisplayName("new IronBaseArmor() works. getArmorPoints returns 80")
            public void getArmorPoints()
            {
               assertEquals(80, testArmor.getArmorPoints());
            }

            @Test
            @DisplayName("new IronBaseArmor() works. getDescription returns \"Iron Armor Base\"")
            public void getDescription()
            {
               assertEquals("Iron Armor Base", testArmor.getDescription());
            }

         }

         @Nested
         @DisplayName("Obsidian")
         public class ObsidianTests
         {
            @BeforeEach
            public void setup()
            {
               testArmor = new ObsidianBaseArmor();

            }

            @Test
            @DisplayName("new ObsidianBaseArmor() works. getArmorPoints returns 100")
            public void getArmorPoints()
            {
               assertEquals(100, testArmor.getArmorPoints());
            }

            @Test
            @DisplayName("new ObsidianBaseArmor() works. getDescription returns \"Obsidian Armor Base\"")
            public void getDescription()
            {
               assertEquals("Obsidian Armor Base", testArmor.getDescription());
            }

         }
      }

      @Nested
      @DisplayName("ArmorComponent concrete constructors work")
      public class ArmorComponentConstructorTests
      {
         @Nested
         @DisplayName("ChainMail")
         public class ChainMailTests
         {
            @BeforeEach
            public void setup()
            {
               testArmor = new WoodBaseArmor();
               testArmor = new ChainMail(testArmor);

            }

            @Test
            @DisplayName("new ChainMail(new WoodBaseArmor()) works. getArmorPoints returns 65")
            public void getArmorPoints()
            {
               assertEquals(65, testArmor.getArmorPoints());
            }

            @Test
            @DisplayName("new ChainMail(new WoodBaseArmor()) works. getDescription returns \"Wood Armor Base with Chain Mail\"")
            public void getDescription()
            {
               assertEquals("Wood Armor Base with Chain Mail", testArmor.getDescription());
            }

         }

         @Nested
         @DisplayName("ExtraPlating")
         public class ExtraPlatingTests
         {
            @BeforeEach
            public void setup()
            {
               testArmor = new WoodBaseArmor();
               testArmor = new ExtraPlating(testArmor);

            }

            @Test
            @DisplayName("new ExtraPlating(new WoodBaseArmor()) works. getArmorPoints returns 65")
            public void getArmorPoints()
            {
               assertEquals(60, testArmor.getArmorPoints());
            }

            @Test
            @DisplayName("new ExtraPlating(new WoodBaseArmor()) works. getDescription returns \"Wood Armor Base with Extra Plating\"")
            public void getDescription()
            {
               assertEquals("Wood Armor Base with Extra Plating", testArmor.getDescription());
            }

         }

         @Nested
         @DisplayName("PaddedInnerLining")
         public class PaddedInnerLiningTests
         {
            @BeforeEach
            public void setup()
            {
               testArmor = new WoodBaseArmor();
               testArmor = new PaddedInnerLining(testArmor);

            }

            @Test
            @DisplayName("new PaddedInnerLining(new WoodBaseArmor()) works. getArmorPoints returns 57")
            public void getArmorPoints()
            {
               assertEquals(57, testArmor.getArmorPoints());
            }

            @Test
            @DisplayName("new PaddedInnerLining(new WoodBaseArmor()) works. getDescription returns \"Wood Armor Base with Padded Inner Lining\"")
            public void getDescription()
            {
               assertEquals("Wood Armor Base with Padded Inner Lining", testArmor.getDescription());
            }

         }

         @Nested
         @DisplayName("SoftInnerLining")
         public class SoftInnerLiningTests
         {
            @BeforeEach
            public void setup()
            {
               testArmor = new WoodBaseArmor();
               testArmor = new SoftInnerLining(testArmor);

            }

            @Test
            @DisplayName("new SoftInnerLining(new WoodBaseArmor()) works. getArmorPoints returns 55")
            public void getArmorPoints()
            {
               assertEquals(55, testArmor.getArmorPoints());
            }

            @Test
            @DisplayName("new SoftInnerLining(new WoodBaseArmor()) works. getDescription returns \"Wood Armor Base with Soft Inner Lining\"")
            public void getDescription()
            {
               assertEquals("Wood Armor Base with Soft Inner Lining", testArmor.getDescription());
            }

         }

      }

      @Nested
      @DisplayName("ArmorDamage constructor works")
      public class ArmorDamageConstructorTests
      {

         @BeforeEach
         public void setup()
         {
            testArmor = new WoodBaseArmor();
            testArmor = new ChainMail(testArmor);

         }

         @Test
         @DisplayName("Chainmail wrapped wood armor: getArmorPoints returns 35 when wrapped in 30 damage item")
         public void getArmorPoints()
         {
            testArmor = new ArmorDamage(30, testArmor);
            assertEquals(35, testArmor.getArmorPoints());
         }

         @Test
         @DisplayName("Chainmail wrapped wood armor: getDescription returns \"Wood Armor Base with Chain Mail\"")
         public void getDescription()
         {
            assertEquals("Wood Armor Base with Chain Mail", testArmor.getDescription());
         }

      }

      @Nested
      @DisplayName("Multiple wrappings work")
      public class MultipleWrappingTests
      {
         @Nested
         @DisplayName("Wood -> chain -> chain -> plating -> chain")
         public class WoodChainChainPlatingChainTest
         {

            @BeforeEach
            public void setup()
            {
               testArmor = new WoodBaseArmor();
               testArmor = new ChainMail(testArmor);
               testArmor = new ChainMail(testArmor);
               testArmor = new ExtraPlating(testArmor);
               testArmor = new ChainMail(testArmor);

            }

            @Test
            @DisplayName("Wood -> chain -> chain -> plating -> chain. Description is correct")
            public void getArmorPoints()
            {

               assertEquals(
                     "Wood Armor Base with Chain Mail, Chain Mail, Extra Plating, Chain Mail",
                     testArmor.getDescription());
            }

            @Test
            @DisplayName("Wood -> chain -> chain -> plating -> chain. Armor points is correct")
            public void getDescription()
            {

               assertEquals(105, testArmor.getArmorPoints());
            }
         }

         @Nested
         @DisplayName("Iron -> plating -> softLining -> plating -> paddedLining")
         public class IronPlateSoftPlatePadTest
         {

            @BeforeEach
            public void setup()
            {
               testArmor = new IronBaseArmor();
               testArmor = new ExtraPlating(testArmor);
               testArmor = new SoftInnerLining(testArmor);
               testArmor = new ExtraPlating(testArmor);
               testArmor = new PaddedInnerLining(testArmor);

            }

            @Test
            @DisplayName("Iron -> plating -> softLining -> plating -> paddedLining. Description is correct")
            public void getArmorPoints()
            {

               assertEquals(
                     "Iron Armor Base with Extra Plating, Soft Inner Lining, Extra Plating, Padded Inner Lining",
                     testArmor.getDescription());
            }

            @Test
            @DisplayName("Iron -> plating -> softLining -> plating -> paddedLining. Armor points is correct")
            public void getDescription()
            {

               assertEquals(112, testArmor.getArmorPoints());
            }
         }
      }

      @Nested
      @DisplayName("Preconditions")
      public class Preconditions
      {
         @Test
         @DisplayName("ArmorEnhancement constructor throws IllegalArgumentException if armorPoints is less than 0")
         public void armorEnhancementPreconditionArmorPointsLessThanZero()
         {
            assertThrows(IllegalArgumentException.class, () -> new TestArmorEnhancement(-1, "Test", new IronBaseArmor()));
         }
         
         @Test
         @DisplayName("ArmorEnhancement constructor throws IllegalArgumentException if armorPoints is 0")
         public void armorEnhancementPreconditionArmorPointsZero()
         {
            assertThrows(IllegalArgumentException.class, () -> new TestArmorEnhancement(0, "Test", new IronBaseArmor()));
         }
         
         @Test
         @DisplayName("ArmorEnhancement constructor throws IllegalArgumentException if description is null")
         public void armorEnhancementPreconditionDescriptionNull()
         {
            assertThrows(IllegalArgumentException.class, () -> new TestArmorEnhancement(10, null, new IronBaseArmor()));
         }
         
         @Test
         @DisplayName("ArmorEnhancement constructor throws IllegalArgumentException if description is empty")
         public void armorEnhancementPreconditionDescriptionEmpty()
         {
            assertThrows(IllegalArgumentException.class, () -> new TestArmorEnhancement(10, new String(""), new IronBaseArmor()));
         }
         
         @Test
         @DisplayName("ArmorEnhancement constructor throws IllegalArgumentException if wrapperedArmorItem is null")
         public void armorEnhancementPreconditioWrapperedArmorItemNulln()
         {
            assertThrows(IllegalArgumentException.class, () -> new TestArmorEnhancement(10, "Test", null));
         }
         
         @Test
         @DisplayName("ArmorBase constructor throws IllegalArgumentException if armorPoints is less than 0")
         public void armorBasePreconditionArmorPointsLessThanZero()
         {
            assertThrows(IllegalArgumentException.class, () -> new TestArmorBase(-1, "Test"));
         }
         
         @Test
         @DisplayName("ArmorBase constructor throws IllegalArgumentException if armorPoints is 0")
         public void armorBasePreconditionArmorPointsZero()
         {
            assertThrows(IllegalArgumentException.class, () -> new TestArmorBase(0, "Test"));
         }
         
         @Test
         @DisplayName("ArmorBase constructor throws IllegalArgumentException if description is null")
         public void armorBasePreconditionDescriptionNull()
         {
            assertThrows(IllegalArgumentException.class, () -> new TestArmorBase(10, null));
         }
         
         @Test
         @DisplayName("ArmorBase constructor throws IllegalArgumentException if description is empty")
         public void armorBasePreconditionDescriptionEmpty()
         {
            assertThrows(IllegalArgumentException.class, () -> new TestArmorBase(10, new String("")));
         }
         
         @Test
         @DisplayName("ArmorDamage constructor throws IllegalArgumentException if armorReduced is less than 0")
         public void armorDamagePreconditionArmorPointsLessThanZero()
         {
            assertThrows(IllegalArgumentException.class, () -> new ArmorDamage(-1, new IronBaseArmor()));
         }
         
         @Test
         @DisplayName("ArmorDamage constructor throws IllegalArgumentException if armorReduced is 0")
         public void armorDamagePreconditionArmorPointsZero()
         {
            assertThrows(IllegalArgumentException.class, () -> new ArmorDamage(0, new IronBaseArmor()));
         }
         
         
         @Test
         @DisplayName("ArmorDamage constructor throws IllegalArgumentException if wrapperedArmorItem is null")
         public void armorDamagePreconditionDescriptionEmpty()
         {
            assertThrows(IllegalArgumentException.class, () -> new ArmorDamage(10, null));
         }
         
      }

   }

}