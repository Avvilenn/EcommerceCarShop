package com.carShop.entity;


public class Car {
    private Long carId;
    private String model;
    private String color;
    private String type;
    private boolean inStock;
    private int price;
    private int yearOfProduction;

    public Car() {
    }

    public Car(String model, String color, String type, boolean inStock, int price, int yearOfProduction) {

        this.model = model;
        this.color = color;
        this.type = type;
        this.inStock = inStock;
        this.price = price;
        this.yearOfProduction = yearOfProduction;
        System.out.println("Class Car, new Car created  "+model+type+color+" Price  " + price+
                "  Year of production  " + yearOfProduction);
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Car car = (Car) o;

        if (yearOfProduction != car.yearOfProduction) return false;
        if (carId != null ? !carId.equals(car.carId) : car.carId != null) return false;
        if (!model.equals(car.model)) return false;
        if (color != null ? !color.equals(car.color) : car.color != null) return false;
        return type != null ? type.equals(car.type) : car.type == null;
    }

    @Override
    public int hashCode() {
        int result = carId != null ? carId.hashCode() : 0;
        result = 31 * result + model.hashCode();
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + yearOfProduction;
        return result;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId  =  " + carId +
                ",   model  = '" + model + '\'' +
                ",   color= '" + color + '\'' +
                ", type='" + type + '\'' +
                ", inStock=" + inStock +
                ", price=" + price +
                ", yearOfProduction=" + yearOfProduction +
                '}';
    }
}
