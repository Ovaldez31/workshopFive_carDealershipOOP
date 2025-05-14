package com.ps;

import java.util.ArrayList;

public class Dealership {
    private String name;
    private String address;
    private String phone;

    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();
    }

    public ArrayList<Vehicle> vehiclesByPrice(double min, double max) {
        ArrayList<Vehicle> prices = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getPrice() >= min && vehicle.getPrice() <= max) {
                prices.add(vehicle);
            }
        }
        return prices;
    }
    public ArrayList<Vehicle> vehiclesByMakeModel(String make, String model) {
        ArrayList<Vehicle> makeModel = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getMake().equalsIgnoreCase(make) && vehicle.getModel().equalsIgnoreCase(model)) {
                makeModel.add(vehicle);
            }
        }
        return makeModel;
    }
    public ArrayList<Vehicle> vehiclesByYear(int min, int max) {
        ArrayList<Vehicle> vehicleYear = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
        if (vehicle.getYear() >= min && vehicle.getYear() <= max){
            vehicleYear.add(vehicle);
            }
        }
        return vehicleYear;
    }
    public ArrayList<Vehicle> vehiclesByColor(String color) {
        ArrayList<Vehicle> vehicleColor = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getColor().equalsIgnoreCase(color)) {
                vehicleColor.add(vehicle);
            }
        }
        return vehicleColor;
    }
    public ArrayList<Vehicle> vehiclesByMileage(int min, int max) {
            ArrayList<Vehicle> vehicleMileage = new ArrayList<>();
        for (Vehicle vehicle : inventory) {
            if (vehicle.getOdometer() >= min && vehicle.getOdometer() <= max) {
                    vehicleMileage.add(vehicle);
            }
        }
        return vehicleMileage;
    }

    public ArrayList<Vehicle> getVehiclesByType(String vehicleType) {
           ArrayList<Vehicle> result = new ArrayList<>();
         for (Vehicle vehicle : inventory) {
            if (vehicle.getVehicleType().equalsIgnoreCase(vehicleType)) {
                result.add(vehicle);
            }
         }
         return result;
    }

        public ArrayList<Vehicle> getAllVehicles () {
            return inventory;
        }

        public void addVehicle (Vehicle vehicle) {
            inventory.add(vehicle);
        }

        public void removeVehicle(Vehicle vehicle) {
        inventory.remove(vehicle);
        }




        public String getName () {
            return name;
        }

        public void setName (String name){
            this.name = name;
        }

        public String getAddress () {
            return address;
        }

        public void setAddress (String address){
            this.address = address;
        }

        public String getPhone () {
            return phone;
        }

        public void setPhone (String phone){
            this.phone = phone;
        }


}