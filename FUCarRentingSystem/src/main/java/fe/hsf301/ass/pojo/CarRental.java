package fe.hsf301.ass.pojo;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

@Entity
@Table(name = "CarRental")
public class CarRental {

	@EmbeddedId
    private CarRentalId id;
    
    @Column(nullable = false)
    private LocalDate pickupDate;
    
    @Column(nullable = false)
    private LocalDate returnDate;
    
    @Column(nullable = false)
    private double rentPrice;
    
    @Column(nullable = false)
    private String status;
    
    @ManyToOne
    @MapsId("customerID")
    @JoinColumn(name = "customerID", nullable = false)
    private Customer customer;
    
    @ManyToOne
    @MapsId("carID")
    @JoinColumn(name = "carID", nullable = false)
    private Car car;

	public CarRental() {
		super();
	}

	public CarRental(CarRentalId id, LocalDate pickupDate, LocalDate returnDate, double rentPrice, String status,
			Customer customer, Car car) {
		super();
		this.id = id;
		this.pickupDate = pickupDate;
		this.returnDate = returnDate;
		this.rentPrice = rentPrice;
		this.status = status;
		this.customer = customer;
		this.car = car;
	}

	public CarRental(LocalDate pickupDate, LocalDate returnDate, double rentPrice, String status, Customer customer, Car car) {
		super();
		this.pickupDate = pickupDate;
		this.returnDate = returnDate;
		this.rentPrice = rentPrice;
		this.status = status;
		this.customer = customer;
		this.car = car;
	}

	public CarRental(LocalDate pickupDate, LocalDate returnDate, double rentPrice, String status) {
		super();
		this.pickupDate = pickupDate;
		this.returnDate = returnDate;
		this.rentPrice = rentPrice;
		this.status = status;
	}

	public CarRental(CarRentalId id, LocalDate pickupDate, LocalDate returnDate, double rentPrice, String status) {
		super();
		this.id = id;
		this.pickupDate = pickupDate;
		this.returnDate = returnDate;
		this.rentPrice = rentPrice;
		this.status = status;
	}

	public CarRentalId getId() {
		return id;
	}

	public void setId(CarRentalId id) {
		this.id = id;
	}

	public LocalDate getPickupDate() {
		return pickupDate;
	}

	public void setPickupDate(LocalDate pickupDate) {
		this.pickupDate = pickupDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}
	
	public Integer getCarID() {
		return car.getCarID();
	}
	
	public String getCarName() {
		return car.getCarName();
	}
	
	public Integer getCustomerID() {
		return customer.getCustomerID();
	}
	
	public String getCustomerName() {
		return customer.getCustomerName();
	}

	@Override
	public String toString() {
		return "CarRental [id=" + id + ", pickupDate=" + pickupDate + ", returnDate=" + returnDate + ", rentPrice="
				+ rentPrice + ", status=" + status + ", customer=" + customer + ", car=" + car + "]";
	}
}
