package cinema;

import java.util.Objects;
import java.util.Scanner;


public class Cinema {


    public static String[][] listRowSeats = {{" ", "1", "2", "3", "4", "5", "6", "7", "8", "9"},
            {"1", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S"},
            {"2", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S"},
            {"3", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S"},
            {"4", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S"},
            {"5", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S"},
            {"6", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S"},
            {"7", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S"},
            {"8", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S"},
            {"9", "S", "S", "S", "S", "S", "S", "S", "S", "S", "S"}};

    public static void main(String[] args) {

        inputRowSeat();

    }

    public static void inputRowSeat() {
        int cRows;
        int cSeats;
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        cRows = scan.nextInt();
        System.out.println("Enter the number of seats in each row:");
        cSeats = scan.nextInt();

        if (cRows >= 10 || cSeats >= 10) {
            System.out.println("Wrong Input!");
            inputRowSeat();
        }
        options(cRows, cSeats);

    }

    public static void options(int row, int seats) {
        int cOptions;
        Scanner scan = new Scanner(System.in);
        System.out.println(
                "\n1. Show the seats\n" +
                        "2. Buy a ticket\n" +
                        "3. Statistics\n" +
                        "0. Exit");
        cOptions = scan.nextInt();

        switch (cOptions) {
            case 0:
                exit();
                break;

            case 1:
                // show seats method
                showSeats(row, seats);
                break;

            case 2:
                // buy tickets
                buyTickets(row, seats);
                break;

            case 3:
                // Stats
                stats(row, seats);
                break;

            default:
                // repeats option
                options(row, seats);
                break;
        }
    }

    public static void showSeats(int row, int seats) {

        System.out.println("\nCinema:");

        for (int i = 0; i <= row; i++)
            for (int x = 0; x <= seats; x++) {
                System.out.print(Cinema.listRowSeats[i][x] + " ");

                if (x == seats) {
                    System.out.println("");
                }
            }
        options(row, seats);
    }

    public static void buyTickets(int rows, int seats) {
        int rowTicket;
        int seatTicket;
        Scanner scan = new Scanner(System.in);
        System.out.println("\nEnter a row number: ");
        rowTicket = scan.nextInt();
        System.out.println("Enter a seat number in that row: ");
        seatTicket = scan.nextInt();


        if (rowTicket > rows || seatTicket > seats) {
            System.out.println("Wrong Input!");
            buyTickets(rows, seats);
        }
        if (Cinema.listRowSeats[rowTicket][seatTicket] == "B") {
            System.out.println("That ticket has already been purchased!");
            buyTickets(rows, seats);
        }

        Cinema.listRowSeats[rowTicket][seatTicket] = "B";

        int totalNum = rows * seats;

        if (totalNum < 60) {
            System.out.println("Ticket price: $10");
        } else {

            if (rowTicket <= rows / 2) {
                System.out.println("Ticket Price: $10");
            } else {
                System.out.println("Ticket Price: $8");
            }
        }

        options(rows, seats);
    }

    public static void stats(int row, int seats) {
        int ticketBought = 0;
        double percentage;
        double percentageTotal;

        for (int i = 0; i <= row; i++)
            for (int x = 0; x <= seats; x++) {
                if (Cinema.listRowSeats[i][x] == "B") {
                    ++ticketBought;

                }
            }

        percentage = 100.0 / (row * seats);
        percentageTotal = percentage * ticketBought;

        int totalNum = row * seats;
        int numTotal = 0;
        int totalIncome = 0;

        for (int i = 0; i <= row; i++)
            for (int x = 0; x <= seats; x++) {

                if (Objects.equals(Cinema.listRowSeats[i][x], "B"))
                    if (totalNum < 60) {
                        numTotal = numTotal + 10;
                    } else {

                        if (i <= row / 2) {
                            numTotal = numTotal + 10;
                        } else {
                            numTotal = numTotal + 8;
                        }
                    }
            }

        for (int i = 0; i <= row; i++)
            for (int x = 0; x <= seats; x++) {
                if (totalNum < 60) {
                    totalIncome = totalNum * 10;
                } else {

                    if (i <= row / 2) {
                        totalIncome = totalNum * 10;

                    } else {
                        totalIncome = (totalNum * 9) - 9;
                    }
                }

            }

        System.out.println("Number of purchased tickets: " + ticketBought);
        System.out.printf("Percentage: %.2f%%%n", percentageTotal);
        System.out.println("Current Income: $" + numTotal);
        System.out.println("Total Income: $" + totalIncome);

        options(row, seats);

    }

    public static int exit () {
        return 0;
    }
}

