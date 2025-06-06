package com.ps;

import java.io.*;
import java.util.ArrayList;

public class ContractDataManager {

    public void saveContact(Contract contract) {
        ArrayList<Contract> contracts = new ArrayList<>();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("contracts.csv", true))) {
            StringBuilder stringBuilder = new StringBuilder();


            stringBuilder.append(contract instanceof SalesContract ? "SALE" : "LEASE").append("|");
            stringBuilder.append(contract.getDateOfContract()).append("|");
            stringBuilder.append(contract.getCustomerName()).append("|");
            stringBuilder.append(contract.getCustomerEmail()).append("|");

            Vehicle vehicle = contract.getVehicle();

            stringBuilder.append(vehicle.getVin()).append("|")
                    .append(vehicle.getYear()).append("|")
                    .append(vehicle.getMake()).append("|")
                    .append(vehicle.getModel()).append("|")
                    .append(vehicle.getVehicleType()).append("|")
                    .append(vehicle.getColor()).append("|")
                    .append(vehicle.getOdometer()).append("|")
                    .append(vehicle.getPrice()).append("|");


            if (contract instanceof SalesContract) {
                SalesContract salesContract = (SalesContract) contract;

                stringBuilder.append("SALE").append("|");
                stringBuilder.append(salesContract.getDateOfContract()).append("|");
                stringBuilder.append(salesContract.getCustomerName()).append("|");
                stringBuilder.append(salesContract.getCustomerEmail()).append("|");
                stringBuilder.append(salesContract.getVehicle()).append("|");
                stringBuilder.append(salesContract.getVehiclePrice()).append("|");
                stringBuilder.append(salesContract.getSalesTax()).append("|");
                stringBuilder.append(salesContract.getRecordingFee()).append("|");
                stringBuilder.append(salesContract.getProcessingFee()).append("|");
                stringBuilder.append(salesContract.getTotalPrice()).append("|");
                stringBuilder.append(salesContract.isFinanced() ? "YES" : "NO").append("|");
                stringBuilder.append(salesContract.getMonthlyPayment());

            } else if (contract instanceof LeaseContract) {
                LeaseContract leaseContract = (LeaseContract) contract;

                stringBuilder.append("LEASE").append("|");
                stringBuilder.append(leaseContract.getDateOfContract()).append("|");
                stringBuilder.append(leaseContract.getCustomerName()).append("|");
                stringBuilder.append(leaseContract.getCustomerEmail()).append("|");
                stringBuilder.append(leaseContract.getVehicle()).append("|");
                stringBuilder.append(leaseContract.getVehiclePrice()).append("|");
                stringBuilder.append(leaseContract.getExpectedEndingValue()).append("|");
                stringBuilder.append(leaseContract.getLeaseFee()).append("|");
                stringBuilder.append(leaseContract.getTotalPrice()).append("|");
                stringBuilder.append(leaseContract.getMonthlyPayment());
            }

            bufferedWriter.write(stringBuilder.toString());
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Contract> loadContracts() {
        ArrayList<Contract> contracts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("contracts.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] index = line.split("\\|");
                String type = index[0];

                String date = index[1];
                String name = index[2];
                String email = index[3];

                int vin = Integer.parseInt(index[4]);
                int year = Integer.parseInt(index[5]);
                String make = index[6];
                String model = index[7];
                String vehicleType = index[8];
                String color = index[9];
                int odometer = Integer.parseInt(index[10]);
                double price = Double.parseDouble(index[11]);

                Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);

                if (type.equals("SALE")) {
                    double salesTax = Double.parseDouble(index[12]);
                    double recordingFee = Double.parseDouble(index[13]);
                    double processingFee = Double.parseDouble(index[14]);
                    double totalPrice = Double.parseDouble(index[15]);
                    boolean isFinanced = index[16].equals("YES");
                    double monthlyPayment = Double.parseDouble(index[17]);

                    SalesContract salesContract = new SalesContract(date, name, email, vehicle, isFinanced);
                    salesContract.setSalesTax(salesTax);
                    salesContract.setRecordingFee(recordingFee);
                    salesContract.setProcessingFee(processingFee);
                    salesContract.setTotalPrice(totalPrice);
                    salesContract.setMonthlyPayment(monthlyPayment);

                    contracts.add(salesContract);
                } else if (type.equals("LEASE")) {
                    double expectedEndingValue = Double.parseDouble(index[12]);
                    double leaseFee = Double.parseDouble(index[13]);
                    double totalPrice = Double.parseDouble(index[14]);
                    double monthlyPayment = Double.parseDouble(index[15]);

                    LeaseContract leaseContract = new LeaseContract(date, name, email, vehicle);
                    leaseContract.setExpectedEndingValue(expectedEndingValue);
                    leaseContract.setLeaseFee(leaseFee);
                    leaseContract.setTotalPrice(totalPrice);
                    leaseContract.setMonthlyPayment(monthlyPayment);

                    contracts.add(leaseContract);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.err.println("Error loading contracts: " + e.getMessage());
        }
        return contracts;
    }
}