package fe.hsf301.ass.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import fe.hsf301.ass.pojo.CarRental;
import fe.hsf301.ass.pojo.CarRentalId;

public class CarRentalDAO {
	private SessionFactory sessionFactory = null;
	private Configuration cf = null;

    public CarRentalDAO(String fileConfig) {
        cf = new Configuration();
        sessionFactory = cf.configure(fileConfig).buildSessionFactory();
    }

    public void save(CarRental carRental) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(carRental);
            tx.commit();
            System.out.println("CarRental saved successfully");
        } catch (Exception e) {
            tx.rollback();
            System.out.println("Error: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public CarRental findById(CarRentalId carRentalId) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(CarRental.class, carRentalId);
        } finally {
            session.close();
        }
    }

    public void update(CarRental carRental) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.update(carRental);
            tx.commit();
            System.out.println("CarRental updated successfully");
        } catch (Exception e) {
            tx.rollback();
            System.out.println("Error: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public void delete(CarRentalId carRentalId) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            CarRental carRental = session.get(CarRental.class, carRentalId);
            session.delete(carRental);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            System.out.println("Error: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public List<CarRental> findRentalsByDate(Date startDate, Date endDate) {
        Session session = sessionFactory.openSession();
        List<CarRental> rentals = null;
        try {
            rentals = session.createQuery("FROM CarRental WHERE pickupDate >= :startDate AND returnDate <= :endDate", CarRental.class)
                             .setParameter("startDate", startDate)
                             .setParameter("endDate", endDate)
                             .list();
        } finally {
            session.close();
        }
        return rentals;
    }
    
    public List<CarRental> findRentalsByCustomerID(Integer customerID) {
        Session session = sessionFactory.openSession();
        List<CarRental> rentals = null;
        try {
            rentals = session.createQuery("FROM CarRental WHERE customerID = :customerID", CarRental.class)
                             .setParameter("customerID", customerID)
                             .list();
        } finally {
            session.close();
        }
        return rentals;
    }
    
    public List<CarRental> findRentalsByCarID(Integer carID) {
        Session session = sessionFactory.openSession();
        List<CarRental> rentals = null;
        try {
            rentals = session.createQuery("FROM CarRental WHERE carID = :carID", CarRental.class)
                             .setParameter("carID", carID)
                             .list();
        } finally {
            session.close();
        }
        return rentals;
    }
    
    public List<CarRental> findAll() {
        Session session = sessionFactory.openSession();
        List<CarRental> carRentals = null;
        try {
            carRentals = session.createQuery("from CarRental", CarRental.class).getResultList();
        } catch (RuntimeException e) {
            throw e;
        } finally {
            session.close();
        }
        return carRentals;
    }
}
