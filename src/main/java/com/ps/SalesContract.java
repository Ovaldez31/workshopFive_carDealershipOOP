package com.ps;

public class SalesContract extends Contract{

    private double vehiclePrice;
    private boolean isFinanced;
    private double salesTax;
    private double recordingFee;
    private double processingFee;


    public SalesContract(String dateOfContract, String customerName, String customerEmail, Vehicle vehicle, boolean isFinanced) {
        super(dateOfContract, customerName, customerEmail, vehicle);

        this.vehicle = vehicle;
        this.isFinanced = isFinanced;
        this.vehiclePrice = vehicle.getPrice(); // or however you retrieve price
        this.salesTax = vehiclePrice * 0.05;
        this.recordingFee = 100.00;
        this.processingFee = vehiclePrice >= 10000 ? 495.00 : 295.00;
    }

    public double getVehiclePrice() {
        return vehiclePrice;
    }

    public void setVehiclePrice(double vehiclePrice) {
        this.vehiclePrice = vehiclePrice;
    }

    public boolean isFinanced()
    {
        return isFinanced;
    }

    public void setFinanced(boolean financed)
    {
        isFinanced = financed;
    }

    public double getSalesTax() {
        return salesTax;
    }

    public void setSalesTax(double salesTax) {
        this.salesTax = salesTax;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }


    @Override
    public double getTotalPrice() {

        return getVehiclePrice() + salesTax + recordingFee + processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        if (!isFinanced) return 0.00;


    double monthlyRate;
    int term;

        if (getVehicle().getPrice() >= 10000) {
        monthlyRate = 0.0425;
        term = 48;
    } else {
        monthlyRate = 0.0525;
        term = 24;
    }
        double loanAmount = getTotalPrice();
        return (loanAmount * monthlyRate) / (1 - Math.pow(1 + monthlyRate, -term));
    }

}