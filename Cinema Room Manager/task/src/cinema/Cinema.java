package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of rows:");
        int row = sc.nextInt();
        if (row < 0) {
            System.out.println("Wrong input!");
            System.exit(0);
        }
        System.out.println("Enter the number of seats in each row:");
        int collum = sc.nextInt();
        if (collum < 0) {
            System.out.println("Wrong input!");
            System.exit(0);
        }
        String[][] a = new String[row + 1][collum + 1];
        for(int i = 0; i < row + 1; i++) {
            for(int j = 0; j < collum + 1; j++) {
                a[i][j] = "S";
            }
        }
        for(int i = 0; i < row + 1; i++) {
            a[i][0] = Integer.toString(i);
            for(int j = 0; j < collum + 1; j++) {
                a[0][j] = Integer.toString(j);
            }
        }
        int ans;
        int sum = 0;
        int number = 0;
        do {
            System.out.println();
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            ans = sc.nextInt();
            if(ans == 1) {
                showTheSeats(a, row, collum);
            } else  if (ans == 2) {
                int nrow;
                int ncollum;
                do {
                    System.out.println("Enter a row number:");
                    nrow = sc.nextInt();
                    if(nrow > row || nrow < 0) {
                        System.out.println("Wrong input!");
                        break;
                    }
                    System.out.println("Enter a seat number in that row:");
                    ncollum = sc.nextInt();
                    if(ncollum > collum || ncollum < 0) {
                        System.out.println("Wrong input!");
                        break;
                    }
                    if(a[nrow][ncollum].equals("B")) {
                        System.out.println("That ticket has already been purchased");
                    } else {
                        System.out.print("Ticket price: ");
                        if(row * collum <= 60) {
                            sum += 10;
                            number++;
                            System.out.println("$" + 10);
                        } else {
                            if (nrow <= row / 2) {
                                sum += 10;
                                number++;
                                System.out.println("$" + 10);
                            } else if (nrow > row / 2) {
                                sum += 8;
                                number++;
                                System.out.println("$" + 8);
                            }
                        }
                        a[nrow][ncollum] = "B";
                        break;
                    }
                } while(a[nrow][ncollum].equals(String.valueOf('B')));
            } else if (ans == 3) {
                statistics(row, collum, sum, number);
            }
        } while (ans != 0);
    }

    public static void showTheSeats(String[][] a, int row, int collum) {
        System.out.println("Cinema: ");
        a[0][0] = " ";
        for(int i = 0; i < row + 1; i++) {
            for(int j = 0; j < collum + 1; j++) {
                System.out.print(a[i][j] + " ");
            }
            System.out.println(" ");
        }
    }
    public static void statistics(int row, int collum, int sum, int number) {
        System.out.println("Number of purchased tickets: " + number);
        double percentage = (((double) number / ((double) row * (double) collum)) * 100);
        System.out.print("Percentage: ");
        System.out.printf("%.2f", percentage);
        System.out.println("%");
        System.out.println("Current income: $" + sum);
        int total;
        if(row * collum <= 60) {
            total = row * collum * 10;
        } else if (row % 2 == 0) {
            total = row / 2 * 10 * collum + row / 2 * 8 * collum;
        } else {
            total = row / 2 * 10 * collum + (row / 2 + 1) * 8 * collum;
        }
        System.out.println("Total income: $" + total);
    }
}