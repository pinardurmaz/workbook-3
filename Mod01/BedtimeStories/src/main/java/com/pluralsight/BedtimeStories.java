package com.pluralsight;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BedtimeStories {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);

        System.out.print("Enter the name of a story: ");
        String fileName = inputScanner.nextLine();

        File file = new File(fileName);

        try {
            Scanner fileScanner = new Scanner(file);
            int lineNumber = 1;

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (line.isEmpty()) {
                    System.out.println(lineNumber + ".");
                } else {
                    System.out.println(lineNumber + ". " + line);
                }
                lineNumber++;
            }

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + fileName);
        }

        inputScanner.close();
    }
}