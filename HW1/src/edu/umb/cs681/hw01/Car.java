package edu.umb.cs681.hw01;

import java.util.ArrayList;


public class Car {
        private String make, model;
        private int mileage, year;
        private float price;
        private int dominationCount;

        public Car(String make, String model, int year, int mileage, float price) {
            this.make = make;
            this.model = model;
            this.year = year;
            this.mileage = mileage;
            this.price = price;
        }

        public String getMake() {
            return make;
        }

        public String getModel() {
            return model;
        }

        public int getMileage() {
            return mileage;
        }

        public int getYear() {
            return year;
        }

        public float getPrice() {
            return price;
        }



        public void setDominationCount(ArrayList<Car> usedCars) {
            for (Car car : usedCars) {
                if ((car.getPrice() >= this.getPrice()) && (car.getMileage() >= this.getMileage())
                        && (car.getYear() <= this.getYear())) {
                    this.dominationCount++;
                }
            }
        }

        public int getDominationCount() {
            return dominationCount;
        }
    }
