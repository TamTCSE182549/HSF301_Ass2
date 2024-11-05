package fe.hsf301.ass.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Car")
public class Car {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int carID;
    
    @Column(nullable = false)
    private String carName;
    
    @Column(nullable = false)
    private int carModelYear;
    
    @Column(nullable = false)
    private String color;
    
    @Column(nullable = false)
    private int capacity;
    
    @Column(nullable = false)
    private String description;
    
    @Column(nullable = false)
    private LocalDate importDate;
    
    @Column(nullable = false)
    private double rentPrice;
    
    @Column(nullable = false)
    private String status;
    
    @ManyToOne
    @JoinColumn(name = "producerID", nullable = false)
    private CarProducer producer;

	public Car() {
		super();
	}

	public Car(int carID, String carName, int carModelYear, String color, int capacity, String description,
			LocalDate importDate, double rentPrice, String status, CarProducer producer) {
		super();
		this.carID = carID;
		this.carName = carName;
		this.carModelYear = carModelYear;
		this.color = color;
		this.capacity = capacity;
		this.description = description;
		this.importDate = importDate;
		this.rentPrice = rentPrice;
		this.status = status;
		this.producer = producer;
	}

	public Car(String carName, int carModelYear, String color, int capacity, String description, LocalDate importDate,
			double rentPrice, String status, CarProducer producer) {
		super();
		this.carName = carName;
		this.carModelYear = carModelYear;
		this.color = color;
		this.capacity = capacity;
		this.description = description;
		this.importDate = importDate;
		this.rentPrice = rentPrice;
		this.status = status;
		this.producer = producer;
	}

	public Car(String carName, int carModelYear, String color, int capacity, String description, LocalDate importDate,
			double rentPrice, String status) {
		super();
		this.carName = carName;
		this.carModelYear = carModelYear;
		this.color = color;
		this.capacity = capacity;
		this.description = description;
		this.importDate = importDate;
		this.rentPrice = rentPrice;
		this.status = status;
	}

	public int getCarID() {
		return carID;
	}

	public void setCarID(int carID) {
		this.carID = carID;
	}

	public String getCarName() {
		return carName;
	}

	public void setCarName(String carName) {
		this.carName = carName;
	}

	public int getCarModelYear() {
		return carModelYear;
	}

	public void setCarModelYear(int carModelYear) {
		this.carModelYear = carModelYear;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getImportDate() {
		return importDate;
	}

	public void setImportDate(LocalDate importDate) {
		this.importDate = importDate;
	}

	public double getRentPrice() {
		return rentPrice;
	}

	public void setRentPrice(double rentPrice) {
		this.rentPrice = rentPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public CarProducer getProducer() {
		return producer;
	}

	public void setProducer(CarProducer producer) {
		this.producer = producer;
	}
	
	public String getProducerName() {
		return producer.getProducerName();
	}

	@Override
	public String toString() {
		return "Car [carID=" + carID + ", carName=" + carName + ", carModelYear=" + carModelYear + ", color=" + color
				+ ", capacity=" + capacity + ", description=" + description + ", importDate=" + importDate
				+ ", rentPrice=" + rentPrice + ", status=" + status + ", producer=" + producer + "]";
	}
}
