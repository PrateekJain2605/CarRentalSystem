package com.risin.CarRental;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CarRentalSystem {

    private List<Car> cars;
    private List<Customer> customers;
    private List<RentalCar> rentalCars;

    public CarRentalSystem(){
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentalCars = new ArrayList<>();
    }

    public void addCars(Car car){
        cars.add(car);
    }

    public void addCustomers(Customer customer){
        customers.add(customer);
    }

    public void rentCar(Car car , Customer customer , int days){
        if (car.isAvailable()){
            car.rent();
            rentalCars.add(new RentalCar(car , customer , days));
        }
        else{
            System.out.println("Car is not Available for rent now");
        }
    }

    public void rentalCar(Car car){
        RentalCar rentalCarToRemove = null;
        for (RentalCar rentalCar : rentalCars){
            if (rentalCar.getCar() == car){
                rentalCarToRemove = rentalCar;
                break;
            }
        }

        if (rentalCarToRemove != null){
            car.returnCar();
            rentalCars.remove(rentalCarToRemove);
        }
        else {
            System.out.println("That's Car was not rented");
        }
    }

    public void menu(){
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("=== Car Rental System ===");
            System.out.println("1. Rent a Car");
            System.out.println("2. Return a Car");
            System.out.println("3. Exit");
            System.out.println("Enter Your Choice");

            byte choice = 0;

            try {
                choice = sc.nextByte();
            }
            catch (InputMismatchException e){
                System.out.println("Invalid choice. Please enter a valid option");
            }
            sc.nextLine();  // Consume NewLine

            if (choice == 1) {
                System.out.println("\n== Rent a Car ==\n");
                System.out.println("Enter Your name");
                String customerName = sc.nextLine();

                System.out.println("\nAvailable Car :-");
                for (Car car : cars) {
                    if (car.isAvailable()) {
                        System.out.println(car.getCarId() + " - " + car.getCarBrand() + " - " + car.getModel());
                    }
                }

                System.out.println("\nEnter a car Id which you want to rent :- ");
                String carId = sc.nextLine();

                System.out.println("Enter the number of days for rent ");
                int rentalDays = sc.nextInt();
                sc.nextLine();  // Consume newLine

                Customer newCustomer = new Customer("CUS" + (customers.size() + 1), customerName);
                addCustomers(newCustomer);

                Car selectedCar = null;
                for (Car car : cars) {
                    if (car.getCarId().equals(carId) && car.isAvailable()) {
                        selectedCar = car;
                        break;
                    }
                }

                if (selectedCar != null) {
                    double totalPrice = selectedCar.calculatePrice(rentalDays);
                    System.out.println("\n=== Rental Information ==\n");
                    System.out.println("CustomerID : " + newCustomer.getCustomerId());
                    System.out.println("Customer Name : " + newCustomer.getCustomerName());
                    System.out.println("Car : " + selectedCar.getCarBrand() + " " + selectedCar.getModel());
                    System.out.println("Rental Days : " + rentalDays);
                    System.out.printf("Total Price: $%.2f%n", totalPrice);

                    System.out.println("Confirm Detail (Y/N): ");
                    String confirm = sc.nextLine();

                    if (confirm.equalsIgnoreCase("Y")) {
                        rentCar(selectedCar, newCustomer, rentalDays);
                        System.out.println("\n=== Car Rented Successfully ===\n");
                    } else {
                        System.out.println("\nRented Canceled");
                    }
                } else {
                    System.out.println("Invalid car selection or car not available for rent");
                }
            } else if (choice == 2) {
                System.out.println("\n === Return a Car === \n");
                System.out.println("Enter the Car Id which you want to return :- ");
                String carId = sc.nextLine();

                Car carToReturn = null;
                for (Car car : cars) {
                    if (car.getCarId().equals(carId) && !car.isAvailable()) {
                        carToReturn = car;
                        break;
                    }
                }

                if (carToReturn != null) {
                    Customer customer = null;
                    for (RentalCar rentalCar : rentalCars) {
                        if (rentalCar.getCar() == carToReturn) {
                            customer = rentalCar.getCustomer();
                            break;
                        }
                    }

                    if (customer != null) {
                        rentalCar(carToReturn);
                        System.out.println("Car Returned Successfully by : " + customer.getCustomerName());
                    } else {
                        System.out.println("Car was not rented or rental information is missing");
                    }
                } else {
                    System.out.println("Invalid Car Id or car is not rented");
                }
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Invalid choice. Please enter a valid option");
            }
        }
        System.out.println("\n ===Thank you for using the car Rental System");


        }
    }
