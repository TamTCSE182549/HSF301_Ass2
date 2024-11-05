package fe.hsf301.ass.pojo;

import javax.persistence.Entity;
import javax.persistence.*;
import javax.persistence.Table;

@Entity
@Table(name = "CarProducer")
public class CarProducer {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int producerID;
    
    @Column(nullable = false, unique = true)
    private String producerName;
    
    @Column(nullable = false)
    private String address;
    
    @Column(nullable = false)
    private String country;

	public int getProducerID() {
		return producerID;
	}

	public void setProducerID(int producerID) {
		this.producerID = producerID;
	}

	public String getProducerName() {
		return producerName;
	}

	public void setProducerName(String producerName) {
		this.producerName = producerName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public CarProducer() {
		super();
	}

	public CarProducer(int producerID, String producerName, String address, String country) {
		super();
		this.producerID = producerID;
		this.producerName = producerName;
		this.address = address;
		this.country = country;
	}

	public CarProducer(String producerName, String address, String country) {
		super();
		this.producerName = producerName;
		this.address = address;
		this.country = country;
	}

	@Override
	public String toString() {
		return "CarProducer [producerID=" + producerID + ", producerName=" + producerName + ", address=" + address
				+ ", country=" + country + "]";
	}
	
	
}
