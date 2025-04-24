package com.pluralsight;

import java.util.*;
import java.io.*;

public class SearchInventory {
    public static void main(String[] args) {
        ArrayList<Product> inventory = loadInventoryFromFile("src/main/resources/inventory.csv");
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nWhat do you want to do?");
            System.out.println("1- List all products");
            System.out.println("2- Lookup a product by its id");
            System.out.println("3- Find all products within a price range");
            System.out.println("4- Add a new product");
            System.out.println("5- Quit the application");
            System.out.print("Enter command: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // clear newline

            switch (choice) {
                case 1 -> listAllProducts(inventory);
                case 2 -> lookupProductById(inventory, scanner);
                case 3 -> findProductsInPriceRange(inventory, scanner);
                case 4 -> addNewProduct(inventory, scanner);
                case 5 -> System.out.println("Goodbye!");
                default -> System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 5);
    }

    public static ArrayList<Product> loadInventoryFromFile(String filename) {
        ArrayList<Product> inventory = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);
                inventory.add(new Product(id, name, price));
            }

            inventory.sort(Comparator.comparing(Product::getName)); // BONUS
        } catch (IOException e) {
            System.out.println("Error reading inventory file: " + e.getMessage());
        }
        return inventory;
    }

    public static void listAllProducts(ArrayList<Product> inventory) {
        if (inventory.isEmpty()) {
            System.out.println("No products in inventory.");
        } else {
            for (Product p : inventory) {
                System.out.println(p);
            }
        }
    }

    public static void lookupProductById(ArrayList<Product> inventory, Scanner scanner) {
        System.out.print("Enter product ID: ");
        int id = scanner.nextInt();
        for (Product p : inventory) {
            if (p.getId() == id) {
                System.out.println(p);
                return;
            }
        }
        System.out.println("Product not found.");
    }

    public static void findProductsInPriceRange(ArrayList<Product> inventory, Scanner scanner) {
        System.out.print("Enter minimum price: ");
        double min = scanner.nextDouble();
        System.out.print("Enter maximum price: ");
        double max = scanner.nextDouble();
        boolean found = false;
        for (Product p : inventory) {
            if (p.getPrice() >= min && p.getPrice() <= max) {
                System.out.println(p);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No products found in that price range.");
        }
    }

    public static void addNewProduct(ArrayList<Product> inventory, Scanner scanner) {
        System.out.print("Enter new product ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        inventory.add(new Product(id, name, price));

        inventory.sort(Comparator.comparing(Product::getName)); // keep it sorted
        System.out.println("Product added.");
    }
}