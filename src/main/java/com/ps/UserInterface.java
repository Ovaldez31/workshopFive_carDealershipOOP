package com.ps;

import org.w3c.dom.ls.LSOutput;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {

    private Dealership dealership;
    private Scanner scanner = new Scanner(System.in);

    private void init() {
        dealership = DealershipFileManager.getDealership();
    }

    public UserInterface() {
        init();
    }

    public void display() {

        System.out.println("Welcome to the dealership program");

        int mainMenuCommand;

        do {
            System.out.println("\n---Dealership App\n---");
            System.out.println("1)Find vehicles within a price range");
            System.out.println("2)Find vehicles by make");
            System.out.println("3)Find vehicles by year range");
            System.out.println("4)Find vehicles by color");
            System.out.println("5)Find vehicles by mileage range");
            System.out.println("6)Find vehicles by type");
            System.out.println("7)List ALL vehicles");
            System.out.println("8)Add a vehicle");
            System.out.println("9)Remove a vehicle");
            System.out.println("10)Create and save contract");
            System.out.println("0)Quit");

            System.out.println("Choose an option: ");
            mainMenuCommand = scanner.nextInt();
            scanner.nextLine();

            switch (mainMenuCommand) {
                case 1:
                    processGetByPriceRequest();
                    break;
                case 2:
                    processGetByMakeModelRequest();
                    break;
                case 3:
                    processGetByYearRequest();
                    break;
                case 4:
                    processGetByColorRequest();
                    break;
                case 5:
                    processGetByMileageRequest();
                    break;
                case 6:
                    processGetByVehicleTypeRequest();
                    break;
                case 7:
                    processGetAllVehiclesRequest();
                    break;
                case 8:
                    processAddVehicleRequest();
                    break;
                case 9:
                    processRemoveVehicleRequest();
                    break;
                case 10:
                    processCreateContractRequest();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Command not found, try again");
            }
        } while (mainMenuCommand != 0);
    }

    private void processGetByPriceRequest() {

        System.out.println("--------Display vehicles by price--------");
        System.out.print("Min: ");
        double min = scanner.nextDouble();

        System.out.print("Max: ");
        double max = scanner.nextDouble();

        ArrayList<Vehicle> filteredVehicles = dealership.vehiclesByPrice(min, max);

        if (filteredVehicles.isEmpty()) {
            System.out.println("No vehicles found in that price range.");
        } else {
            displayVehicles(filteredVehicles);
        }
    }


    private void processGetByMakeModelRequest() {
        System.out.println("Enter make: ");
        String make = scanner.nextLine();

        System.out.println("Enter model: ");
        String model = scanner.nextLine();

        ArrayList<Vehicle> filteredVehicles = dealership.vehiclesByMakeModel(make, model);

        if (filteredVehicles.isEmpty()) {
            System.out.println("No vehicles found with that make and model.");
        } else {
            displayVehicles(filteredVehicles);
        }
    }

    private void processGetByYearRequest() {
        System.out.print("Enter minimum year: ");
        int min = scanner.nextInt();

        System.out.print("Enter maximum year: ");
        int max = scanner.nextInt();

        ArrayList<Vehicle> filteredVehicles = dealership.vehiclesByYear(min, max);

        if (filteredVehicles.isEmpty()) {
            System.out.println("No vehicles found in that year range.");
        } else {
            displayVehicles(filteredVehicles);
        }
    }

    private void processGetByColorRequest() {
        System.out.print("Enter color: ");
        String color = scanner.nextLine();

        ArrayList<Vehicle> filteredVehicles = dealership.vehiclesByColor(color);

        if (filteredVehicles.isEmpty()) {
            System.out.println("No vehicles found with that color.");
        } else {
            displayVehicles(filteredVehicles);
        }
    }

    private void processGetByMileageRequest() {
        System.out.print("Enter minimum mileage: ");
        int min = scanner.nextInt();
        System.out.print("Enter maximum mileage: ");
        int max = scanner.nextInt();

        ArrayList<Vehicle> filteredVehicles = dealership.vehiclesByMileage(min, max);

        if (filteredVehicles.isEmpty()) {
            System.out.println("No vehicles found in that mileage range.");
        } else {
            displayVehicles(filteredVehicles);
        }
    }

    private void processGetByVehicleTypeRequest() {
        System.out.print("Enter vehicle type: ");
        scanner.nextLine();

        String type = "";
        ArrayList<Vehicle> filteredVehicles = dealership.getVehiclesByType(type);

        if (filteredVehicles.isEmpty()) {
            System.out.println("No vehicles found of type: " + type);
        } else {
            displayVehicles(filteredVehicles);
        }
    }

    private void processGetAllVehiclesRequest() {
        ArrayList<Vehicle> vehicles = dealership.getAllVehicles();
        System.out.println("---------\nPrinting all vehicles\n-----------");
        displayVehicles(vehicles);
    }

    private void processAddVehicleRequest() {
        System.out.print("Enter VIN: ");
        int vin = scanner.nextInt();

        System.out.print("Enter year: ");
        int year = scanner.nextInt();

        System.out.print("Enter make: ");
        String make = scanner.nextLine();

        System.out.print("Enter model: ");
        String model = scanner.nextLine();

        System.out.print("Enter vehicle type: ");
        String type = scanner.nextLine();

        System.out.print("Enter color: ");
        String color = scanner.nextLine();

        System.out.print("Enter odometer: ");
        int odometer = scanner.nextInt();

        System.out.print("Enter price: ");
        double price = scanner.nextDouble();

        Vehicle newVehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
        dealership.addVehicle(newVehicle);

    }

    private void processRemoveVehicleRequest() {
        System.out.println("-------- \nRemove a Vehicle\n --------");
        System.out.print("Enter VIN of vehicle to remove: ");
        int vin = scanner.nextInt();
        ArrayList<Vehicle> allVehicles = dealership.getAllVehicles();
        Vehicle vehicleToRemove = null;

        for (Vehicle v : allVehicles) {
            if (v.getVin() == vin) {
                vehicleToRemove = v;
                break;
            }
        }
        if (vehicleToRemove != null) {
            dealership.removeVehicle(vehicleToRemove);
            System.out.println("Vehicle removed successfully.");
        } else {
            System.out.println("Vehicle with VIN " + vin + " not found.");
        }
    }

    public static void displayVehicles(ArrayList<Vehicle> vehicles) {
        for (Vehicle vehicle : vehicles) {
            System.out.print(vehicle);
        }
    }
    private void processCreateContractRequest() {
        System.out.print("Enter contract type (SALE or LEASE): ");
        String contractType = scanner.nextLine().trim().toUpperCase();

        System.out.print("Enter contract date (YYYY-MM-DD): ");
        String date = scanner.nextLine().trim();

        System.out.print("Enter customer name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter customer email: ");
        String email = scanner.nextLine().trim();

        System.out.print("Enter vehicle VIN: ");
        int vin = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Enter vehicle year: ");
        int year = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Enter vehicle make: ");
        String make = scanner.nextLine().trim();

        System.out.print("Enter vehicle model: ");
        String model = scanner.nextLine().trim();

        System.out.print("Enter vehicle type: ");
        String type = scanner.nextLine().trim();

        System.out.print("Enter vehicle color: ");
        String color = scanner.nextLine().trim();

        System.out.print("Enter vehicle odometer reading: ");
        int odometer = Integer.parseInt(scanner.nextLine().trim());

        System.out.print("Enter vehicle price: ");
        double price = Double.parseDouble(scanner.nextLine().trim());

        Vehicle newVehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
        Contract contract;

        switch (contractType) {
            case "SALE":
                System.out.print("Will the customer finance the vehicle (yes/no)? ");
                String finance = scanner.nextLine().trim();
                boolean isFinanced = finance.equalsIgnoreCase("yes");
                contract = new SalesContract(date, name, email, newVehicle, isFinanced);
                break;

            case "LEASE":
                int currentYear = java.time.Year.now().getValue();
                if ((currentYear - year) > 3) {
                    System.out.println("Cannot lease a vehicle older than 3 years.");
                    return;
                }

                contract = new LeaseContract(date, name, email, newVehicle);
                break;

            default:
                System.out.println("Invalid contract type entered.");
                return;
        }

        ContractDataManager manager = new ContractDataManager();
        manager.saveContact(contract);
        System.out.println("Contract saved successfully!");

    }

}



