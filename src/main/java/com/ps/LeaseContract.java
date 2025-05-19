package com.ps;

public class LeaseContract extends Contract {

    private double endingValue;
    private double leaseFee;
    private double vehiclePrice;


    public LeaseContract(String dateOfContract, String customerName, String customerEmail, Vehicle newVehicle) {
        super(dateOfContract, customerName, customerEmail, newVehicle);
        this.endingValue = vehiclePrice * 0.50;
        this.leaseFee = vehiclePrice * 0.07;
        this.vehiclePrice = newVehicle.getPrice();
    }

    public double getEndingValue() {
        return endingValue;
    }

    public void setEndingValue(double endingValue) {
        this.endingValue = endingValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }

    public double getVehiclePrice() {
        return vehiclePrice;
    }

    public void setVehiclePrice(double vehiclePrice) {
        this.vehiclePrice = vehiclePrice;
    }

    @Override
    public double getTotalPrice() {

        double leaseFee = vehiclePrice * 0.07;
        return leaseFee;
    }

    @Override
    public double getMonthlyPayment() {

        double principal = vehiclePrice - getExpectedEndingValue();
        double monthlyRate = 0.04 / 12;
        int months = 36;

        return (totalPrice * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -months));
    }
    public double getExpectedEndingValue()
    {
        return vehiclePrice * 0.50;
    }

    public void setExpectedEndingValue(double expectedEndingValue) {
    }


}