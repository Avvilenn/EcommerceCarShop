package com.avvilenn.carShop.entity;


public class CarOrderItem {
    private Long carOrderItemId;
    private CarOrder carOrder;
    private Car car;
    private Integer count;
    private Double price;
    private Person person;

    public CarOrderItem() {
    }

    public CarOrderItem(Long carOrderItemId, CarOrder carOrder, Car car, Integer count, Double price, Person person) {
        this.carOrderItemId = carOrderItemId;
        this.carOrder = carOrder;
        this.car = car;
        this.count = count;
        this.price = price;
        this.person = person;
    }

    public Long getCarOrderItemId() {
        return carOrderItemId;
    }

    public void setCarOrderItemId(Long carOrderItemId) {
        this.carOrderItemId = carOrderItemId;
    }

    public CarOrder getCarOrder() {
        return carOrder;
    }

    public void setCarOrder(CarOrder carOrder) {
        this.carOrder = carOrder;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarOrderItem that = (CarOrderItem) o;

        if (carOrderItemId != null ? !carOrderItemId.equals(that.carOrderItemId) : that.carOrderItemId != null)
            return false;
        if (!carOrder.equals(that.carOrder)) return false;
        if (!car.equals(that.car)) return false;
        if (count != null ? !count.equals(that.count) : that.count != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        return person.equals(that.person);
    }

    @Override
    public int hashCode() {
        int result = carOrderItemId != null ? carOrderItemId.hashCode() : 0;
        result = 31 * result + carOrder.hashCode();
        result = 31 * result + car.hashCode();
        result = 31 * result + (count != null ? count.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + person.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CarOrderItem{" +
                "carOrderItemId=" + carOrderItemId +
                ", carOrder=" + carOrder +
                ", car=" + car +
                ", count=" + count +
                ", price=" + price +
                ", person=" + person +
                '}';
    }
}
