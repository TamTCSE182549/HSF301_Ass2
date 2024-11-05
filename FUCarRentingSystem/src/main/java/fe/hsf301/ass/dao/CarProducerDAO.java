package fe.hsf301.ass.dao;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import fe.hsf301.ass.pojo.CarProducer;

public class CarProducerDAO {
	
	private SessionFactory sessionFactory = null;
	private Configuration cf = null;
	
	public CarProducerDAO(String configFile) {
		cf = new Configuration();
		sessionFactory = cf.configure(configFile).buildSessionFactory();
	}
	
	public void save(CarProducer producer) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(producer);
            tx.commit();
            System.out.println("CarProducer saved successfully");
        } catch (Exception e) {
            tx.rollback();
            System.out.println("Error: " + e.getMessage());
        } finally {
            session.close();
        }
    }
	
	public CarProducer findById(int producerID) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(CarProducer.class, producerID);
        } finally {
            session.close();
        }
    }

    public void update(CarProducer producer) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.update(producer);
            tx.commit();
            System.out.println("CarProducer updated successfully");
        } catch (Exception e) {
            tx.rollback();
            System.out.println("Error: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public void delete(int producerID) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            CarProducer producer = session.get(CarProducer.class, producerID);
            session.delete(producer);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            System.out.println("Error: " + e.getMessage());
        } finally {
            session.close();
        }
    }
    
    public List<CarProducer> findAll() {
        Session session = sessionFactory.openSession();
        List<CarProducer> carProducers = null;
        try {
        	carProducers = session.createQuery("from CarProducer", CarProducer.class).list();
        } catch (RuntimeException e) {
            throw e;
        } finally {
            session.close();
        }
        return carProducers;
    }
}
