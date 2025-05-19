package com.ps;

public abstract class Contract {


    String dateOfContract;
    String customerName;
    String customerEmail;
    Vehicle vehicle;
    double totalPrice;
    double monthlyPayment;
    boolean isFinanced;


    public Contract(String dateOfContract, String customerName, String customerEmail, Vehicle vehicle) {
        this.dateOfContract = dateOfContract;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicle = vehicle;
        this.totalPrice = totalPrice;
        this.monthlyPayment = monthlyPayment;
        this.isFinanced = isFinanced;
    }

    public String getDateOfContract()
    {
        return dateOfContract;
    }

    public void setDateOfContract(String dateOfContract)
    {
        this.dateOfContract = dateOfContract;
    }

    public String getCustomerName()
    {
        return customerName;
    }

    public void setCustomerName(String customerName)
    {
        this.customerName = customerName;
    }

    public String getCustomerEmail()
    {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail)
    {
        this.customerEmail = customerEmail;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public boolean isFinanced() {
        return isFinanced;
    }

    public void setFinanced(boolean financed) {
        isFinanced = financed;
    }

    public abstract double getTotalPrice();

    public void setTotalPrice(double totalPrice) {
    }

    public abstract double getMonthlyPayment();

    public void setMonthlyPayment(double monthlyPayment) {
    }






}
