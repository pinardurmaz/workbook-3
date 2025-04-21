package com.pluralsight;

import java.util.Scanner;
import java.util.Random;

public class FamousQuotes {
    public static void main(String[] args) {
        String[] quotes = {
                "Believe you can and you're halfway there.",
                "Do one thing every day that scares you.",
                "It always seems impossible until it's done.",
                "Success is not the key to happiness. Happiness is the key to success.",
                "The only way to do great work is to love what you do.",
                "Don't watch the clock; do what it does. Keep going.",
                "The future belongs to those who believe in the beauty of their dreams.",
                "I can, therefore I am.",
                "Turn your wounds into wisdom.",
                "Dream big and dare to fail."
        };

        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        boolean continueRunning = true;

        while (continueRunning) {
            try {
                System.out.print("Enter a number (1-10), or 0 for a random quote: ");
                int choice = scanner.nextInt();

                if (choice == 0) {
                    int randomIndex = random.nextInt(quotes.length);
                    System.out.println("Random Quote: " + quotes[randomIndex]);
                } else if (choice >= 1 && choice <= 10) {
                    System.out.println("Quote: " + quotes[choice - 1]);
                } else {
                    System.out.println("Number must be between 1 and 10.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // clear invalid input
            }

            System.out.print("Would you like to see another quote? (yes/no): ");
            String answer = scanner.next().toLowerCase();
            if (!answer.equals("yes")) {
                continueRunning = false;
                System.out.println("Goodbye!");
            }
        }
    }
}