package fe.hsf301.ass.pojo;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ReviewId implements Serializable {

	private static final long serialVersionUID = 1L;
	private int customerID;
    private int carID;
    
	public ReviewId() {
		super();
	}
	public ReviewId(int customerID, int carID) {
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
    
    
}
