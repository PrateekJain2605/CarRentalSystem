package com.risin.CarRental;

public class Car {
    private String carBrand;
    private String carId;
    private String  model;
    private boolean isAvailable;
    private double carPricePerDay;

    public Car(String  carId , String carBrand , String  model , double carPricePerDay){
        this.carBrand = carBrand;
        this.carId = carId;
        this.model = model;
        this.carPricePerDay = carPricePerDay;
        isAvailable = true;
    }

    public String getCarBrand(){
        return carBrand;
    }

    public String getCarId() {
        return carId;
    }

    public double calculatePrice(int rentalDays) {
        return carPricePerDay * rentalDays ;
    }

    public String getModel() {
        return model;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void rent(){
        isAvailable = false;
    }

    public void returnCar(){
        isAvailable = true;
    }
}
