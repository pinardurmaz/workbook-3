package com.pluralsight;

import java.io.*;
import java.util.*;

public class PayrollCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the name of the employee file to process: ");
        String inputFileName = scanner.nextLine();

        System.out.print("Enter the name of the payroll file to create: ");
        String outputFileName = scanner.nextLine();

        List<Employee> employees = new ArrayList<>();

        try {
            FileReader fileReader = new FileReader(inputFileName);
            BufferedReader bufReader = new BufferedReader(fileReader);

            String line;

            while ((line = bufReader.readLine()) != null) {
                if (line.toLowerCase().startsWith("id|")) {
                    continue;
                }

                String[] tokens = line.split("\\|");
                int id = Integer.parseInt(tokens[0]);
                String name = tokens[1];
                double hoursWorked = Double.parseDouble(tokens[2]);
                double payRate = Double.parseDouble(tokens[3]);

                Employee emp = new Employee(id, name, hoursWorked, payRate);
                employees.add(emp);
            }

            bufReader.close();

            if (outputFileName.endsWith(".json")) {
                writeJsonFile(outputFileName, employees);
            } else {
                writeCsvFile(outputFileName, employees);
            }

            System.out.println("Payroll file created successfully!");

        } catch (IOException e) {
            System.out.println("File error: " + e.getMessage());
        }
    }

    public static void writeCsvFile(String fileName, List<Employee> employees) {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.println("id|name|gross pay");
            for (Employee emp : employees) {
                writer.printf("%d|%s|%.2f%n", emp.getEmployeeId(), emp.getName(), emp.getGrossPay());
            }
        } catch (IOException e) {
            System.out.println("Error writing CSV: " + e.getMessage());
        }
    }

    public static void writeJsonFile(String fileName, List<Employee> employees) {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            writer.println("[");
            for (int i = 0; i < employees.size(); i++) {
                Employee emp = employees.get(i);
                writer.printf("  { \"id\": %d, \"name\" : \"%s\", \"grossPay\" : %.2f }",
                        emp.getEmployeeId(), emp.getName(), emp.getGrossPay());
                if (i < employees.size() - 1) writer.println(",");
                else writer.println();
            }
            writer.println("]");
        } catch (IOException e) {
            System.out.println("Error writing JSON: " + e.getMessage());
        }
    }
}