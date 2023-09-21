import java.util.Scanner;
public class TipCalculator {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

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
        double totalBillCost = tipAmount + totalCost;
        double tipPerPerson = tipAmount / numPeople;
        double billPerPerson = costPerPerson + tipPerPerson;

        System.out.println("Total bill before tip: " + totalCost);
        System.out.println(tipPercent + "%");
        System.out.println(tipAmount);
        System.out.println(totalCost);
    }
}
