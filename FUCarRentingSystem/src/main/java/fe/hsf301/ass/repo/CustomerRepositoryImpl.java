package fe.hsf301.ass.repo;

import java.util.List;

import fe.hsf301.ass.dao.CustomerDAO;
import fe.hsf301.ass.pojo.Customer;

public class CustomerRepositoryImpl implements ICustomerRepository{
	
	private CustomerDAO customerDAO;

    public CustomerRepositoryImpl(String fileConfig) {
        customerDAO = new CustomerDAO(fileConfig);
    }

	@Override
	public void save(Customer customer) {
		// TODO Auto-generated method stub
		customerDAO.save(customer);
	}

	@Override
	public void delete(int customerID) {
		// TODO Auto-generated method stub
		customerDAO.delete(customerID);
	}

	@Override
	public Customer findById(int customerID) {
		// TODO Auto-generated method stub
		return customerDAO.findById(customerID);
	}

	@Override
	public void update(Customer customer) {
		// TODO Auto-generated method stub
		customerDAO.update(customer);
	}

	@Override
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		return customerDAO.findAll();
	}

	@Override
	public Customer login(String email, String password) {
		// TODO Auto-generated method stub
		return customerDAO.login(email, password);
	}

}
