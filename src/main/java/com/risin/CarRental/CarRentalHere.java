package com.risin.CarRental;

public class CarRentalHere {
    public static void main(String[] args) {
        CarRentalSystem rentalSystem = new CarRentalSystem();

        Car car1 = new Car("C001" , "Mahindra" , "Thar" , 150.0);
        Car car2 = new Car("C002" , "Toyota" , "Camry" , 60.0);
        Car car3 = new Car("C003" , "Tata" , "Jaguar" , 190.0);

        rentalSystem.addCars(car1);
        rentalSystem.addCars(car2);
        rentalSystem.addCars(car3);

        rentalSystem.menu();
    }
}
