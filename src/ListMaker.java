import java.util.ArrayList;
import java.util.Scanner;

public class ListMaker {
  // Declare the ArrayList at the class level so helper methods can access it
  private static ArrayList<String> list = new ArrayList<>();

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    boolean done = false;
    String cmd = "";

    System.out.println("Welcome to ListMaker!");

    // Outer do-while repeat loop
    do {
      displayList();

      // Display the menu options
      System.out.println("\nMenu Options:");
      System.out.println("A - Add an item to the list");
      System.out.println("D - Delete an item from the list");
      System.out.println("I - Insert an item into the list");
      System.out.println("P - Print the list");
      System.out.println("Q - Quit the program");

      // Bullet-proofed menu choice using RegEx
      cmd = SafeInput.getRegExString(in, "Enter your choice", "[AaDdIiPpQq]").toUpperCase();

      // CMD switch case structure
      switch (cmd) {
        case "A":
          addItem(in);
          break;
        case "D":
          deleteItem(in);
          break;
        case "I":
          insertItem(in);
          break;
        case "P":
          printList();
          break;
        case "Q":
          done = quitProgram(in);
          break;
      }
    } while (!done);

    System.out.println("Goodbye!");
    in.close();
  }

  // Helper method to continuously show the numbered list
  private static void displayList() {
    System.out.println("\n=======================================");
    if (list.isEmpty()) {
      System.out.println("Your list is currently empty.");
    } else {
      // Prints a 1-indexed numbered list for the user
      for (int i = 0; i < list.size(); i++) {
        System.out.printf("%d. %s\n", i + 1, list.get(i));
      }
    }
    System.out.println("=======================================");
  }

  // Menu Action: Add
  private static void addItem(Scanner in) {
    String item = SafeInput.getNonZeroLenString(in, "Enter the item to add");
    list.add(item); // Always puts it at the end of the list
  }

  // Menu Action: Delete
  private static void deleteItem(Scanner in) {
    if (list.isEmpty()) {
      System.out.println("There is nothing to delete!");
      return;
    }
    // Prompts user for a 1-indexed number, then subtracts 1 for the 0-indexed ArrayList
    int index = SafeInput.getRangedInt(in, "Enter the item number to delete", 1, list.size());
    list.remove(index - 1);
    System.out.println("Item deleted.");
  }

  // Menu Action: Insert
  private static void insertItem(Scanner in) {
    if (list.isEmpty()) {
      System.out.println("The list is empty. Adding as the first item.");
      addItem(in);
      return;
    }
    // Allows insertion anywhere from position 1 up to the end of the list + 1
    int index = SafeInput.getRangedInt(in, "Enter the position number where you want to insert", 1, list.size() + 1);
    String item = SafeInput.getNonZeroLenString(in, "Enter the item to insert");
    list.add(index - 1, item);
  }

  // Menu Action: Print
  private static void printList() {
    // Just displays the list (re-uses our display method for consistency)
    displayList();
  }

  // Menu Action: Quit
  private static boolean quitProgram(Scanner in) {
    return SafeInput.getYNConfirm(in, "Are you sure you want to quit?");
  }
}