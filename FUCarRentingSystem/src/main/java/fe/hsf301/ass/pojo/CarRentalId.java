package fe.hsf301.ass.pojo;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

@Embeddable
public class CarRentalId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int customerID;
    private int carID;
    
	public CarRentalId() {
		super();
	}

	public CarRentalId(int customerID, int carID) {
		super();
		this.customerID = customerID;
		this.carID = carID;
	}

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public int getCarID() {
		return carID;
	}

	public void setCarID(int carID) {
		this.carID = carID;
	}

	@Override
	public String toString() {
		return "CarRentalId [customerID=" + customerID + ", carID=" + carID + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(carID, customerID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CarRentalId other = (CarRentalId) obj;
		return carID == other.carID && customerID == other.customerID;
	}
    
}
