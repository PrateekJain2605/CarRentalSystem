package com.risin.CarRental;

public class Customer {
    private String customerName;
    private String customerId;

    public Customer(String customerId , String customerName){
        this.customerName = customerName;
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String  getCustomerId() {
        return customerId;
    }
}
