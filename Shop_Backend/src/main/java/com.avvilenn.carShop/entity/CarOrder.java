package com.avvilenn.carShop.entity;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarOrder {
    private long carOrderId;
    private Date dateOfOrder;
    private Person person;
    private List<CarOrderItem> items;

    public CarOrder() {
    }

    public CarOrder(long carOrderId, Date dateOfOrder, Person person, List<CarOrderItem> items) {
        this.carOrderId = carOrderId;
        this.dateOfOrder = dateOfOrder;
        this.person = person;
        this.items = items;
    }

    public long getCarOrderId() {
        return carOrderId;
    }

    public void setCarOrderId(long carOrderId) {
        this.carOrderId = carOrderId;
    }

    public Date getDateOfOrder() {
        return dateOfOrder;
    }

    public void setDateOfOrder(Date dateOfOrder) {
        this.dateOfOrder = dateOfOrder;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<CarOrderItem> getItems() {
        return items;
    }

    public void setItems(List<CarOrderItem> items) {
        this.items = items;
    }

    public void addItem(CarOrderItem item) {
        if(items == null) {
            items = new ArrayList<>();
        }
        items.add(item);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CarOrder carOrder = (CarOrder) o;

        if (carOrderId != carOrder.carOrderId) return false;
        if (dateOfOrder != null ? !dateOfOrder.equals(carOrder.dateOfOrder) : carOrder.dateOfOrder != null)
            return false;
        if (!person.equals(carOrder.person)) return false;
        return items != null ? items.equals(carOrder.items) : carOrder.items == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (carOrderId ^ (carOrderId >>> 32));
        result = 31 * result + (dateOfOrder != null ? dateOfOrder.hashCode() : 0);
        result = 31 * result + person.hashCode();
        result = 31 * result + (items != null ? items.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CarOrder{" +
                "carOrderId=" + carOrderId +
                ", dateOfOrder=" + dateOfOrder +
                ", person=" + person +
                ", items=" + items +
                '}';
    }
}
