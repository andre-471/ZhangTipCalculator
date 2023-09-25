import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TipCalculator {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int totalMenus = 0;
        double totalMenusCost = 0;
        double totalMenusTip = 0;
        double totalMenusBill = 0;
        Map<String, Integer> totalItems = new HashMap<>();

        do {
            double totalCost = 0;
            Map<String, Integer> items = new HashMap<>();

            System.out.print("How many people are in your group: ");
            int numPeople = scan.nextInt();
            scan.nextLine();

            System.out.print("What's the tip percentage? (0-100): ");
            int tipPercent = scan.nextInt();
            scan.nextLine();

            System.out.print("Enter a cost in dollars and cents (-1 to end): ");
            double aCost = scan.nextDouble();
            scan.nextLine();

            String aItem;
            int numItem;
            while (aCost != -1) {
                System.out.print("Enter the item: ");
                aItem = scan.nextLine();
                System.out.print("How many? ");
                numItem = scan.nextInt();
                scan.nextLine();

                totalCost += aCost * numItem;
                // https://docs.oracle.com/javase/8/docs/api/java/util/Map.html#merge-K-V-java.util.function.BiFunction-
                items.merge(aItem, numItem, Integer::sum);

                System.out.print("Enter a cost in dollars and cents (-1 to end): ");
                aCost = scan.nextDouble();
                scan.nextLine();
            }

            // cost is cost of food w/o tip
            // bill is cost + tip
            double costPerPerson = totalCost / numPeople;
            double tipAmount = totalCost * tipPercent / 100;
            double totalBill = tipAmount + totalCost;
            double tipPerPerson = tipAmount / numPeople;
            double billPerPerson = costPerPerson + tipPerPerson;

            System.out.println("==========================================");
            // https://stackoverflow.com/questions/2538787/how-to-print-a-float-with-2-decimal-places-in-java
            System.out.println("Total bill before tip: $" + String.format("%.2f", totalCost));
            System.out.println("Total percentage: " + tipPercent + "%");
            System.out.println("Total tip: $" + String.format("%.2f", tipAmount));
            System.out.println("Total bill with tip: $" + String.format("%.2f", totalBill));
            System.out.println("Per person cost before tip: $" + String.format("%.2f", costPerPerson));
            System.out.println("Tip per person: $" + String.format("%.2f", tipPerPerson));
            System.out.println("Total cost per person: $" + String.format("%.2f", billPerPerson));

            System.out.println("==========================================");
            System.out.println("Items ordered: ");
            // https://stackoverflow.com/questions/43015098/how-to-iterate-through-a-map-in-java
            // I mean you didn't say it had to based on input order
            for (Map.Entry<String, Integer> pair: items.entrySet()) {
                System.out.println(pair.getKey() + " x" + pair.getValue());
                totalItems.merge(pair.getKey(), pair.getValue(), Integer::sum);
            }

            totalMenus += 1;
            totalMenusCost += totalCost;
            totalMenusTip += tipAmount;
            totalMenusBill += totalBill;

            System.out.println("==========================================");
            System.out.print("Do you still want to calculate menus? (Y/N) ");
        } while ("Y".equalsIgnoreCase(scan.nextLine())); // repeat if user says (Y)es

        System.out.println("==========================================");
        System.out.println("==========================================");
        System.out.println("Total menus calculated: " + totalMenus);
        System.out.println("Total menus cost: $" + String.format("%.2f", totalMenusCost));
        System.out.println("Total menus tip: $" + String.format("%.2f", totalMenusTip));
        System.out.println("Total bills with tip: $" + String.format("%.2f", totalMenusBill));

        System.out.println("==========================================");
        System.out.println("Total items ordered: ");
        for (Map.Entry<String, Integer> pair: totalItems.entrySet()) {
            System.out.println(pair.getKey() + " x" + pair.getValue());
        }

        scan.close();
    }
}
