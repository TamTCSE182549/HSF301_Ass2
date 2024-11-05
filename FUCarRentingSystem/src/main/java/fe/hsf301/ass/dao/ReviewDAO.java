package fe.hsf301.ass.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import fe.hsf301.ass.pojo.CarRentalId;
import fe.hsf301.ass.pojo.Review;
import fe.hsf301.ass.pojo.ReviewId;

public class ReviewDAO {
	private SessionFactory sessionFactory;

    public ReviewDAO(String hibernateConfig) {
        sessionFactory = new Configuration().configure(hibernateConfig).buildSessionFactory();
    }

    public void save(Review review) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.save(review);
            tx.commit();
            System.out.println("Review saved successfully");
        } catch (Exception e) {
            tx.rollback();
            System.out.println("Error: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public void delete(ReviewId reviewID) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            Review review = session.get(Review.class, reviewID);
            if (review != null) {
                session.delete(review);
                tx.commit();
                System.out.println("Review deleted successfully");
            }
        } catch (Exception e) {
            tx.rollback();
            System.out.println("Error: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public Review findById(ReviewId reviewId) {
        Session session = sessionFactory.openSession();
        Review review = null;
        try {
            review = session.get(Review.class, reviewId);
        } catch (RuntimeException e) {
            throw e;
        } finally {
            session.close();
        }
        return review;
    }

    public void update(Review review) {
        Session session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        try {
            session.update(review);
            tx.commit();
            System.out.println("Review updated successfully");
        } catch (Exception e) {
            tx.rollback();
            System.out.println("Error: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public List<Review> findAll() {
        Session session = sessionFactory.openSession();
        List<Review> reviews = null;
        try {
            reviews = session.createQuery("from Review", Review.class).list();
        } catch (RuntimeException e) {
            throw e;
        } finally {
            session.close();
        }
        return reviews;
    }
}
