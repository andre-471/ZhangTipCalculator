import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TipCalculator {
    // https://docs.oracle.com/javase/8/docs/api/java/text/DecimalFormat.html
    private static final DecimalFormat df = new DecimalFormat("$0.00");

    public static void main(String[] args) {
        df.setRoundingMode(RoundingMode.HALF_UP); // round up
        // variables
        Scanner scan = new Scanner(System.in);

        int totalMenus = 0;
        double totalMenusCost = 0;
        double totalMenusTip = 0;
        double totalMenusBill = 0;
        Map<String, Integer> totalItems = new HashMap<>();

        // loop for multiple calculation support
        do {
            // more variables
            double totalCost = 0;
            Map<String, Integer> items = new HashMap<>();

            // prompts user for info and stores it
            System.out.print("How many people are in your group: ");
            int numPeople = scan.nextInt();
            scan.nextLine();

            System.out.print("What's the tip percentage? (0-100): ");
            int tipPercent = scan.nextInt();
            scan.nextLine();

            // start of asking for item loop
            System.out.println();
            System.out.print("Enter the item (\"end\" to end calculation): ");
            String aItem = scan.nextLine();

            double aCost;
            int numItem;
            while (!"end".equalsIgnoreCase(aItem)) {  // if the user didn't just end the loop
                // more item info
                System.out.print("Enter a cost in dollars and cents: ");
                aCost = scan.nextDouble();
                scan.nextLine();
                System.out.print("How many? ");
                numItem = scan.nextInt();
                scan.nextLine();

                totalCost += aCost * numItem;  // add cost to totalCost
                /* https://docs.oracle.com/javase/8/docs/api/java/util/Map.html#merge-K-V-java.util.function.BiFunction-
                checks if item is in hashmap
                then adds numItem to current value or creates key and sets value to numItem
                */
                items.merge(aItem, numItem, Integer::sum);


                // prompt to be checked by while loop expression
                System.out.println();
                System.out.print("Enter the item (\"end\" to end calculation): ");
                aItem = scan.nextLine();
            }

            // cost is cost of food w/o tip
            // bill is cost + tip
            double costPerPerson = totalCost / numPeople;
            double tipAmount = totalCost * tipPercent / 100;
            double totalBill = tipAmount + totalCost;
            double tipPerPerson = tipAmount / numPeople;
            double billPerPerson = costPerPerson + tipPerPerson;

            // just printing out info for user
            System.out.println("==========================================");
            System.out.println("Total bill before tip: " + df.format(totalCost));
            System.out.println("Total percentage: " + tipPercent + "%");
            System.out.println("Total tip: " + df.format(tipAmount));
            System.out.println("Total bill with tip: " + df.format(totalBill));
            System.out.println("Per person cost before tip: " + df.format(costPerPerson));
            System.out.println("Tip per person: " + df.format(tipPerPerson));
            System.out.println("Total cost per person: " + df.format(billPerPerson));
            System.out.println("==========================================");
            System.out.println("Items ordered: ");
            // https://stackoverflow.com/questions/43015098/how-to-iterate-through-a-map-in-java
            // I mean you didn't say it had to based on input order (prints each item)
            for (Map.Entry<String, Integer> pair: items.entrySet()) {
                System.out.println(pair.getKey() + " x" + pair.getValue());
                // adds each item to another hashmap to display at the end
                totalItems.merge(pair.getKey(), pair.getValue(), Integer::sum);
            }

            // adds to variables to display at the end
            totalMenus += 1;
            totalMenusCost += totalCost;
            totalMenusTip += tipAmount;
            totalMenusBill += totalBill;

            System.out.println("==========================================");
            System.out.print("Do you want to calculate another menu? (Y/N) ");
        } while ("Y".equalsIgnoreCase(scan.nextLine())); // repeat if user says (Y)es

        // display total
        System.out.println("==========================================");
        System.out.println();
        System.out.println("Total menus calculated: " + totalMenus);
        System.out.println("Total menus cost: " + df.format(totalMenusCost));
        System.out.println("Total menus tip: " + df.format(totalMenusTip));
        System.out.println("Total bills with tip: " + df.format(totalMenusBill));
        // display total items
        System.out.println("==========================================");
        System.out.println("Total items ordered: ");
        for (Map.Entry<String, Integer> pair: totalItems.entrySet()) {
            System.out.println(pair.getKey() + " x" + pair.getValue());
        }

        scan.close(); // close the scanner please
    }
}
