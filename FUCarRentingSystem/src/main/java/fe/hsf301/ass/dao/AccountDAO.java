package fe.hsf301.ass.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import fe.hsf301.ass.pojo.Account;

public class AccountDAO {
	
	private SessionFactory sessionFactory;

    public AccountDAO(String fileConfig) {
        Configuration cf = new Configuration().configure("hibernate.cfg.xml");
        sessionFactory = cf.buildSessionFactory();
    }

    public void save(Account account) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(account);
            transaction.commit();
            System.out.println("Account successfully saved");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error saving account: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public void delete(int accountID) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            Account account = session.get(Account.class, accountID);
            if (account != null) {
                session.delete(account);
                transaction.commit();
                System.out.println("Account successfully deleted");
            } else {
                System.out.println("Account not found");
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error deleting account: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public Account findById(int accountID) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Account.class, accountID);
        } catch (Exception e) {
            System.out.println("Error finding account: " + e.getMessage());
            return null;
        } finally {
            session.close();
        }
    }

    public void update(Account account) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(account);
            transaction.commit();
            System.out.println("Account successfully updated");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error updating account: " + e.getMessage());
        } finally {
            session.close();
        }
    }

    public List<Account> findAll() {
        Session session = sessionFactory.openSession();
        try {
            return session.createQuery("from Account", Account.class).list();
        } catch (Exception e) {
            System.out.println("Error finding all accounts: " + e.getMessage());
            return null;
        } finally {
            session.close();
        }
    }
}
