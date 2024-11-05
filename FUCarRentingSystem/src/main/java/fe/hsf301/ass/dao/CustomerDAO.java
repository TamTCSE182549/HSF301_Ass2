package fe.hsf301.ass.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import fe.hsf301.ass.pojo.Customer;

public class CustomerDAO {
	
	private SessionFactory sessionFactory = null;
	private Configuration cf = null;

    public CustomerDAO(String fileConfig) {
        cf = new Configuration();
        sessionFactory = cf.configure(fileConfig).buildSessionFactory();
    }

    public void save(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(customer);
            tx.commit();
            System.out.println("Customer saved successfully");
        } catch (Exception e) {
            tx.rollback();
            System.out.println("Error: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public Customer findById(int customerID) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Customer.class, customerID);
        } finally {
            session.close();
        }
    }
    
    public Customer login(String email, String password) {
        Session session = sessionFactory.openSession();
        Customer customer = null;
        try {
            customer = session.createNativeQuery("select c.* from Customer c where c.email = ?1 and c.password = ?2", Customer.class)
            		.setParameter(1, email)
            		.setParameter(2, password)
            		.getSingleResult();
        } catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
            session.close();
        }
        return customer;
    }

    public void update(Customer customer) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
        	session.createNativeQuery("update Customer set "
        			+ "customerName = ?1, "
        			+ "mobile = ?2, "
        			+ "birthday = ?3, "
        			+ "identityCard = ?4, "
        			+ "licenceNumber = ?5, "
        			+ "licenceDate = ?6, "
        			+ "email = ?7 "
        			+ "where customerID = ?8")
        	.setParameter(1, customer.getCustomerName())
        	.setParameter(2, customer.getMobile())
        	.setParameter(3, customer.getBirthday())
        	.setParameter(4, customer.getIdentityCard())
        	.setParameter(5, customer.getLicenceNumber())
        	.setParameter(6, customer.getLicenceDate())
        	.setParameter(7, customer.getEmail())
        	.setParameter(8, customer.getCustomerID())
        	.executeUpdate();
        	tx.commit();
            System.out.println("Customer updated successfully");
        } catch (Exception e) {
            tx.rollback();
            System.out.println("Error: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public void delete(int customerID) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            Customer customer = session.get(Customer.class, customerID);
            session.delete(customer);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            System.out.println("Error: " + e.getMessage());
        } finally {
            session.close();
        }
    }
    
    public List<Customer> findAll() {
        Session session = sessionFactory.openSession();
        List<Customer> customers = null;
        try {
            customers = session.createQuery("from Customer", Customer.class).list();
        } catch (RuntimeException e) {
            throw e;
        } finally {
            session.close();
        }
        return customers;
    }
}
