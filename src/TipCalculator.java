import java.util.Scanner;
public class TipCalculator {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        double totalCost = 0.0;
        double aCost = 0.0;

        do {
            totalCost += aCost;
            System.out.print("Enter a cost in dollars and cents (-1 to end):");
            aCost = scan.nextDouble();
        } while (aCost >= 0.0);

        System.out.println(aCost);
    }
}
