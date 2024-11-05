package fe.hsf301.ass.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import fe.hsf301.ass.pojo.Car;

public class CarDAO {
	
	private SessionFactory sessionFactory = null;
	private Configuration cf = null;

    public CarDAO(String fileConfig) {
        cf = new Configuration();
        sessionFactory = cf.configure(fileConfig).buildSessionFactory();
    }

    public void save(Car car) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(car);
            tx.commit();
            System.out.println("Car saved successfully");
        } catch (Exception e) {
            tx.rollback();
            System.out.println("Error: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public Car findById(int carID) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Car.class, carID);
        } finally {
            session.close();
        }
    }

    public void update(Car car) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.update(car);
            tx.commit();
            System.out.println("Car updated successfully");
        } catch (Exception e) {
            tx.rollback();
            System.out.println("Error: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public void delete(int carID) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            Car car = session.get(Car.class, carID);
            session.delete(car);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            System.out.println("Error: " + e.getMessage());
        } finally {
            session.close();
        }
    }
    
    public List<Car> findAll() {
        Session session = sessionFactory.openSession();
        List<Car> cars = null;
        try {
            cars = session.createQuery("from Car", Car.class).list();
        } catch (RuntimeException e) {
            throw e;
        } finally {
            session.close();
        }
        return cars;
    }
    
    public List<Car> findCarByProducerID(int producerID) {
        Session session = sessionFactory.openSession();
        List<Car> cars = null;
        try {
            cars = session.createQuery("from Car where producerID = :producerID", Car.class)
            		.setParameter("producerID", producerID)
            		.list();
        } catch (RuntimeException e) {
            throw e;
        } finally {
            session.close();
        }
        return cars;
    }
}
