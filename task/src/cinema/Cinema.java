package cinema;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = sc.nextInt();
        String[][] seatingPlan = initialSeating(seats, rows);
        showMenu(seatingPlan);
    }
    public static void maxProfitCalc(String[][] seatingPlan) {
        int rows = seatingPlan.length - 1;
        int seats = seatingPlan[0].length - 1;
        int totalSeats = rows * seats;
        if (totalSeats > 60) {
            int discountRows = (rows / 2);
            System.out.println(discountRows);
            System.out.println("Total income: $" + ((discountRows) * seats * 10 + (rows - discountRows) * seats * 8));
        } else if (totalSeats <= 60) {
            System.out.println("Total income: $" + rows * seats * 10);
        }
        System.out.println();
    }
    public static void soldTickets(String[][] seatingPlan){
        System.out.println();
        int soldTickets = 0;
        int currentIncome = 0;
        for (int i = 1; i < seatingPlan.length; i++) {
            for (int j = 1; j < seatingPlan[0].length; j++) {
                if (seatingPlan[i][j] == "B ") {
                    if ((seatingPlan.length - 1) * (seatingPlan[0].length - 1) > 60) {
                        if (((seatingPlan.length - 1) / 2) < i) {
                            currentIncome += 8;
                        } else {
                            currentIncome += 10;
                        }
                    } else if ((seatingPlan.length - 1) * (seatingPlan[0].length - 1) <= 60) {
                        currentIncome += 10;
                    }
                    soldTickets++;
                }
            }
        }
        System.out.println("Number of purchased tickets: " + soldTickets);
        if (soldTickets == 0) {
            System.out.println("Percentage: 0.00%");
        } else {
            System.out.printf("Percentage: %.2f%s \n", soldTicketsPercentage(seatingPlan.length - 1, seatingPlan[0].length - 1, soldTickets), "%");
        }
        System.out.println("Current income: $" + currentIncome);
    }
    public static double soldTicketsPercentage(int rows, int seats, int soldTickets) {
        return ((double) soldTickets / (rows * seats) * 100);
    }
    public static String[][] initialSeating(int seats, int rows) {
        int arrayRows = rows + 1;
        int arrayColumns = seats + 1;
        String[][] seatingPlan = new String[arrayRows][arrayColumns];
        for (int i = 0; i < seatingPlan.length; i++) {
            for (int j = 0; j < seatingPlan[0].length; j++) {
                if (i == 0 && j == 0) {
                    seatingPlan[i][j] = "  ";
                }
                if (i == 0 && j != 0) {
                    seatingPlan[i][j] = j + " ";
                }
                if (i != 0 && j == 0) {
                    seatingPlan[i][j] = i + " ";
                }
                if (i != 0 && j != 0) {
                    seatingPlan[i][j] = "S ";
                }
            }
        }
        System.out.println();
        return seatingPlan;
    }
    public static void printSeating(String[][] seatingPlan) {
        System.out.println();
        System.out.println("Cinema:");
        for (int i = 0; i < seatingPlan.length; i++) {
            for (int j = 0; j < seatingPlan[0].length; j++) {
                System.out.print(seatingPlan[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
    public static String[][] buyTicket(String[][] seatingPlan) {
        System.out.println();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a row number:");
        int buyRow = sc.nextInt();
        System.out.println("Enter a seat number in that row:");
        int buySeat = sc.nextInt();
        int totalSeats = (seatingPlan[0].length - 1) * (seatingPlan.length - 1);
        int price = 0;
        if (totalSeats > 60) {
            int discountRows = ((seatingPlan.length - 1) / 2);
            if (buyRow > discountRows) {
                price = 8;
            } else {
                price = 10;
            }
        } else if (totalSeats <= 60) {
            price = 10;
        }
        allocateSeats(buyRow, buySeat, seatingPlan);
        System.out.println("Ticket price: $" + price);
        System.out.println();
        return seatingPlan;
    }
    public static String[][] allocateSeats(int buyRow, int buySeat, String[][] seatingPlan) {
        if (buyRow > (seatingPlan.length - 1) || (seatingPlan[0].length - 1) < buySeat) {
            System.out.println("Wrong input!");
            buyTicket(seatingPlan);
        } else {
            if (seatingPlan[buyRow][buySeat] == "S ") {
                seatingPlan[buyRow][buySeat] = "B ";
            } else {
                System.out.println("That ticket has already been purchased!");
                buyTicket(seatingPlan);
            }
        }
        return seatingPlan;
    }
    public static void showMenu(String[][] seatingPlan){
        Scanner keyboard = new Scanner(System.in);
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        int button = keyboard.nextInt();
        resultMenu(button, seatingPlan);
    }

    public static void resultMenu(int button, String[][] seatingPlan) {
        switch (button) {
            case 0:
                break;
            case 1:
                printSeating(seatingPlan);
                showMenu(seatingPlan);
                break;
            case 2:
                seatingPlan = buyTicket(seatingPlan);
                showMenu(seatingPlan);
                break;
            case 3:
                soldTickets(seatingPlan);
                maxProfitCalc(seatingPlan);
                showMenu(seatingPlan);
                break;
            default:
                System.out.println("Wrong input!");
                showMenu(seatingPlan);
                break;
        }
    }
}