package cscd212tests.lab8;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

import java.util.ArrayList;
import java.util.Scanner;

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;
import org.junit.platform.launcher.listeners.TestExecutionSummary;
import org.junit.platform.launcher.listeners.TestExecutionSummary.Failure;

import cscd212classes.lab5.lifeform.Human;
import cscd212classes.lab8.armor.base.IronBaseArmor;
import cscd212classes.lab8.armor.base.ObsidianBaseArmor;
import cscd212classes.lab8.armor.base.WoodBaseArmor;
import cscd212classes.lab8.armor.enhancement.ChainMail;
import cscd212classes.lab8.armor.enhancement.ExtraPlating;
import cscd212classes.lab8.armor.enhancement.PaddedInnerLining;
import cscd212classes.lab8.armor.enhancement.SoftInnerLining;
import cscd212interfaces.lab8.armor.ArmorItem;

public class CSCD212Lab8TestMenu
{
   public static void startMenu()
   {
      Human player = new Human("Hugh Mann", 80);

      Scanner kb = new Scanner(System.in);

      String userChoice;

      do
      {
         do
         {
            System.out.print(player + "\n");
            System.out.print("\n");
            System.out.print("Please make a selection:\n");
            System.out.print("1) Craft Armor\n");
            System.out.print("2) View Inventory\n");
            System.out.print("3) Simulate Damage\n");
            System.out.print("4) Run JUnit Tests\n");
            System.out.print("5) Quit\n");

            userChoice = getUserChoice(kb, "1 2 3 4 5");
         } while (userChoice == null);
         switch (userChoice) {
            case "1":
               player.setEquippedArmorItem(makeArmor(kb));
               break;
            case "2":
               viewInventory(kb, player);
               break;
            case "3":
               simulateDamage(kb, player);
               break;
            case "4":
               System.out.println("Don't forget to add JUnit 5 to the build path");
               runJunitTests();
               break;
            case "5":
               System.out.print("Goodbye\n");
               break;

         }
      } while (!userChoice.trim().equalsIgnoreCase("5"));

   }

   /**
    * Displays information about the given Human's inventory and allows the user to select an item
    * to equip.
    * @param kb     - Scanner to keyboard
    * @param player - Human to view the inventory of
    */
   private static void viewInventory(final Scanner kb, final Human player)
   {
      String userChoice;
      String validChoices = "";
      ArrayList<ArmorItem> playerInventory = player.getInventory();

      if (player.getEquippedArmorItem() != null)
         System.out.print("Currently Equipped: " + player.getEquippedArmorItem().getDescription()
               + " has " + player.getEquippedArmorItem().getArmorPoints() + " armor points.\n");
      else
         System.out.print("No Armor Item currently equipped.\n");

      System.out.print("\n" + player.getName() + "'s inventory: \n\n");

      if (playerInventory.size() == 0)
         System.out.print("Inventory is empty.\n\n");

      else
      {
         do
         {

            for (int i = 0; i < playerInventory.size(); i++)
            {
               System.out.print(i + ") " + playerInventory.get(i).getDescription() + " has "
                     + playerInventory.get(i).getArmorPoints() + " armor points.\n");

               validChoices += i + " ";
            }

            System.out.print("\nChoose an item to equip or enter \"Q\" to Quit \n\n");

            userChoice = getUserChoice(kb, validChoices + " Q");

         } while (userChoice == null);

         if (!userChoice.equalsIgnoreCase("Q"))
            player.setEquippedArmorItem(playerInventory.get(Integer.parseInt(userChoice)));

      }
   }

   /**
    * Walks the user through Armor creation using menus and then returns the ArmorItem created.
    * @param  kb - Scanner to keyboard
    * @return    - ArmorItem which is decorated with the components the user specified.
    */
   private static ArmorItem makeArmor(final Scanner kb)
   {
      ArmorItem craftedArmor = chooseBase(kb);

      craftedArmor = chooseAddons(kb, craftedArmor);

      System.out.print("Your new armor is: " + craftedArmor.getDescription() + "\n");
      System.out.print("Your new armor has " + craftedArmor.getArmorPoints() + " armor points.\n");
      System.out.print("\n");
      return craftedArmor;

   }

   /**
    * Presents the user with choices for material from which to make their armor and returns a base
    * armor made from that material.
    * @param  kb - Scanner to keyboard
    * @return    - ArmorItem which is a Base item
    */
   private static ArmorItem chooseBase(final Scanner kb)
   {
      String userChoice;
      ArmorItem craftedArmor = null;

      System.out
            .print("Welcome to the armor shop! Please choose a base material to begin crafting:\n");

      do
      {
         System.out.print("Base Materials:\n");
         System.out.print("1) Wood (50 armor)\n");
         System.out.print("2) Iron (80 armor)\n");
         System.out.print("3) Obsidian (100 armor)\n");

         userChoice = getUserChoice(kb, "1 2 3");
      } while (userChoice == null);

      switch (userChoice) {
         case "1":
            craftedArmor = new WoodBaseArmor();
            break;
         case "2":
            craftedArmor = new IronBaseArmor();
            break;
         case "3":
            craftedArmor = new ObsidianBaseArmor();
            break;
      }
      return craftedArmor;
   }

   /**
    * Wraps the given ArmorItem in a series of ArmorItem components specified by the user. Presents
    * the user with a menu of choices and allows them to continually add components to their armor
    * until they choose to quit. The reference to this decorated ArmorItem is returned.
    * @param  kb        - Scanner to keyboard
    * @param  baseArmor - the armor item to be wrapped
    * @return           - ArmorItem which is decorated with the components the user specified.
    */
   private static ArmorItem chooseAddons(final Scanner kb, final ArmorItem baseArmor)
   {
      String userChoice;
      ArmorItem craftedArmor = baseArmor;

      System.out.print("Choose your addons:\n");
      do
      {
         do
         {
            System.out.print("Armor Components:\n");
            System.out.print("1) Chain mail (+15 armor)\n");
            System.out.print("2) Extra Plating (+10 armor)\n");
            System.out.print("3) Soft Inner Lining (+5 armor)\n");
            System.out.print("4) Padded Inner Lining (+7 armor)\n");
            System.out.print("5) Quit (I'm done adding things)\n");

            userChoice = getUserChoice(kb, "1 2 3 4 5");
         } while (userChoice == null);

         switch (userChoice) {
            case "1":
               craftedArmor = new ChainMail(craftedArmor);
               break;
            case "2":
               craftedArmor = new ExtraPlating(craftedArmor);
               break;
            case "3":
               craftedArmor = new SoftInnerLining(craftedArmor);
               break;
            case "4":
               craftedArmor = new PaddedInnerLining(craftedArmor);
               break;

         }
      } while (!userChoice.trim().equalsIgnoreCase("5"));
      return craftedArmor;
   }

   /**
    * Asks the user to select a damage amount and applies the damage to the passed Human.
    * @param kb     - Scanner to keyboard
    * @param player - Human to apply damage to
    */
   private static void simulateDamage(final Scanner kb, final Human player)
   {

      System.out.print("Enter a damage amount:\n");

      int damage = Integer.parseInt(kb.nextLine());

      player.takeHit(damage);

      if (player.getCurrentLifePoints() == 0)
      {
         System.out.print(player.getName() + " is now dead. Goodbye.\n");
         System.exit(0);
      }

   }

   /**
    * Helper method that returns the user's choice from a list of valid choices. If the user does
    * not select a valid choice, null is returned.
    * @param  kb           - Scanner to keyboard
    * @param  validChoices - a space separated list of valid choices
    * @return              String - the user's choice or null if they did not select from the given
    *                      options
    */
   private static String getUserChoice(final Scanner kb, final String validChoices)
   {
      if (kb == null || validChoices == null || validChoices.isBlank())
         throw new IllegalArgumentException("Bad Params getUserChoice");

      String answer = kb.nextLine().trim().toUpperCase();

      if (validChoices.contains(answer))
         return answer.toUpperCase();

      return null;
   }

   private static void runJunitTests()
   {

      LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
            .selectors(selectClass(ArmorTests.class)).build();
      Launcher launcher = LauncherFactory.create();
      SummaryGeneratingListener listener = new SummaryGeneratingListener();

      launcher.execute(request, listener);

      TestExecutionSummary summary = listener.getSummary();

      System.out.println();
      System.out.println("---------------------------------------------------------------");
      System.out.println("---------------------------------------------------------------");
      System.out.println("                        Armor Tests");
      System.out.println("---------------------------------------------------------------");
      System.out.println("---------------------------------------------------------------");
      System.out.println("# of tests succeeded: " + summary.getTestsSucceededCount());
      System.out.println("# of tests failed: " + summary.getTestsFailedCount());
      System.out.println("---------------------------------------------------------------");
      for (Failure fail : summary.getFailures())
      {
         System.out.println("---------------------------------------------------------------");
         System.out.println("Failed Test Display Name:");
         System.out.println(" - " + fail.getTestIdentifier().getDisplayName());
         System.out.println();
         System.out.println("Failed Test Exception Message:");
         System.out.println(" - " + fail.getException().getLocalizedMessage());
         System.out.println("---------------------------------------------------------------");
         System.out.println();
      }

      request = LauncherDiscoveryRequestBuilder.request().selectors(selectClass(HumanTests.class))
            .build();

      launcher = LauncherFactory.create();
      listener = new SummaryGeneratingListener();

      launcher.execute(request, listener);

      summary = listener.getSummary();

      System.out.println();
      System.out.println("---------------------------------------------------------------");
      System.out.println("---------------------------------------------------------------");
      System.out.println("                        Human Tests");
      System.out.println("---------------------------------------------------------------");
      System.out.println("---------------------------------------------------------------");
      System.out.println("# of tests succeeded: " + summary.getTestsSucceededCount());
      System.out.println("# of tests failed: " + summary.getTestsFailedCount());
      System.out.println("---------------------------------------------------------------");
      System.out.println("---------------------------------------------------------------");
      for (Failure fail : summary.getFailures())
      {

         System.out.println("Failed Test Display Name:");
         System.out.println(" - " + fail.getTestIdentifier().getDisplayName());
         System.out.println();
         System.out.println("Failed Test Exception Message:");
         System.out.println(" - " + fail.getException().getLocalizedMessage());
         System.out.println("---------------------------------------------------------------");
      }

   }

}
