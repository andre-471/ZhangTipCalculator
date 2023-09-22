import java.util.Scanner;
public class TipCalculator {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int totalMenus = 1;
        double totalMenusCost = 0.0;
        double totalMenusTip = 0.0;
        double totalMenusBill = 0.0;

        do {
            double totalCost = 0.0;

            System.out.print("How many people are in your group: ");
            int numPeople = scan.nextInt();
            System.out.print("What's the tip percentage? (0-100): ");
            int tipPercent = scan.nextInt();
            scan.nextLine();

            double aCost = 0.0;
            while (aCost != -1) {
                totalCost += aCost;
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

            // https://stackoverflow.com/questions/2538787/how-to-print-a-float-with-2-decimal-places-in-java
            System.out.println("Total bill before tip: " + String.format("%.2f", totalCost));
            System.out.println(tipPercent + "%");
            System.out.println(String.format("%.2f", tipAmount));
            System.out.println(String.format("%.2f", totalBill));
            System.out.println(String.format("%.2f", costPerPerson));
            System.out.println(String.format("%.2f", tipPerPerson));
            System.out.println(String.format("%.2f", billPerPerson));

            totalMenus += 1;
            totalMenusCost += totalCost;
            totalMenusTip += tipAmount;
            totalMenusBill += totalBill;

            System.out.println("Y/N");
        } while ("Y".equalsIgnoreCase(scan.nextLine())); // repeat if user says (Y)es

        System.out.println(totalMenus);
        System.out.println(totalMenusCost);
        System.out.println(totalMenusTip);
        System.out.println(totalMenusBill);

        scan.close();
    }
}
