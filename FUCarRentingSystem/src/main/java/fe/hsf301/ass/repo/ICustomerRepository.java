package fe.hsf301.ass.repo;

import java.util.List;

import fe.hsf301.ass.pojo.Customer;

public interface ICustomerRepository {
	void save(Customer customer);
    void delete(int customerID);
    Customer findById(int customerID);
    void update(Customer customer);
    List<Customer> findAll();
    Customer login(String email, String password);
}
