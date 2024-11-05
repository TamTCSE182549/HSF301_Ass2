package fe.hsf301.ass.pojo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Account")
public class Account {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountID;
    
    @Column(nullable = false)
    private String accountName;
    
    @Column(nullable = false)
    private String role;
    
    @OneToMany(mappedBy = "account")
    private List<Customer> customers;

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public Account() {
		super();
	}

	public Account(int accountID, String accountName, String role, List<Customer> customers) {
		super();
		this.accountID = accountID;
		this.accountName = accountName;
		this.role = role;
		this.customers = customers;
	}

	public Account(String accountName, String role, List<Customer> customers) {
		super();
		this.accountName = accountName;
		this.role = role;
		this.customers = customers;
	}

	public Account(String accountName, String role) {
		super();
		this.accountName = accountName;
		this.role = role;
	}

	@Override
	public String toString() {
		return "Account [accountID=" + accountID + ", accountName=" + accountName + ", role=" + role + ", customers="
				+ customers + "]";
	}
}
