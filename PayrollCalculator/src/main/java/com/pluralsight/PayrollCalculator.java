package com.pluralsight;

import java.io.*;
import java.util.*;

public class PayrollCalculator {
    public static void main(String[] args) {
//This file is in the project directory
        String fileName = "src/main/resources/employees.csv";

        try {
//FileReader and BufferedReader
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufReader = new BufferedReader(fileReader);

            String line;

//Read each line from the file
            while ((line = bufReader.readLine()) != null) {

                if (line.toLowerCase().startsWith("id|")) {
                    continue;
                }

// Split and parse
                String[] tokens = line.split("\\|");

                int id = Integer.parseInt(tokens[0]);
                String name = tokens[1];
                double hoursWorked = Double.parseDouble(tokens[2]);
                double payRate = Double.parseDouble(tokens[3]);

                Employee emp = new Employee(id, name, hoursWorked, payRate);

                System.out.printf("ID: %d | Name: %s | Gross Pay: $%.2f%n",
                        emp.getEmployeeId(), emp.getName(), emp.getGrossPay());
            }

            bufReader.close();  // Step 7: Close the file
        } catch (IOException e) {
            e.printStackTrace();  // If file not found or read error
        }
    }
}