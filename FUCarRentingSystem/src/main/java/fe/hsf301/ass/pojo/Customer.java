package fe.hsf301.ass.pojo;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Customer")
public class Customer {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerID;
    
    private String customerName;
    
    private String mobile;
    
    private LocalDate birthday;
    
    private String identityCard;
    
    private String licenceNumber;
    
    private LocalDate licenceDate;
    
    private String email;
    
    private String password;
    
    @OneToOne
    @JoinColumn(name = "accountID")
    private Account account;

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public LocalDate getLicenceDate() {
		return licenceDate;
	}

	public void setLicenceDate(LocalDate licenceDate) {
		this.licenceDate = licenceDate;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public void setIdentityCard(String identityCard) {
		this.identityCard = identityCard;
	}

	public String getLicenceNumber() {
		return licenceNumber;
	}

	public void setLicenceNumber(String licenceNumber) {
		this.licenceNumber = licenceNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	public int getAccountID() {
		return account.getAccountID();
	}

	public Customer() {
		super();
	}

	public Customer(String customerName, String mobile, LocalDate birthday, String identityCard, String licenceNumber,
			LocalDate licenceDate, String email, String password) {
		super();
		this.customerName = customerName;
		this.mobile = mobile;
		this.birthday = birthday;
		this.identityCard = identityCard;
		this.licenceNumber = licenceNumber;
		this.licenceDate = licenceDate;
		this.email = email;
		this.password = password;
	}

	public Customer(String customerName, String mobile, LocalDate birthday, String identityCard, String licenceNumber,
			LocalDate licenceDate, String email, String password, Account account) {
		super();
		this.customerName = customerName;
		this.mobile = mobile;
		this.birthday = birthday;
		this.identityCard = identityCard;
		this.licenceNumber = licenceNumber;
		this.licenceDate = licenceDate;
		this.email = email;
		this.password = password;
		this.account = account;
	}

	public Customer(int customerID, String customerName, String mobile, LocalDate birthday, String identityCard,
			String licenceNumber, LocalDate licenceDate, String email) {
		super();
		this.customerID = customerID;
		this.customerName = customerName;
		this.mobile = mobile;
		this.birthday = birthday;
		this.identityCard = identityCard;
		this.licenceNumber = licenceNumber;
		this.licenceDate = licenceDate;
		this.email = email;
	}

	public Customer(String email, String password, Account account) {
		super();
		this.email = email;
		this.password = password;
		this.account = account;
	}

	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", customerName=" + customerName + ", mobile=" + mobile
				+ ", birthday=" + birthday + ", identityCard=" + identityCard + ", licenceNumber=" + licenceNumber
				+ ", licenceDate=" + licenceDate + ", email=" + email + ", password=" + password + ", account="
				+ account + "]";
	}
    
    
}
