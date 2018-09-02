package gr.pr.date_releases.hibernatetools;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class HibernateTools {
	private static SessionFactory sessionFactory;

	static {
		try {
			Configuration configuration = new Configuration();
			configuration.configure();

			sessionFactory = configuration.buildSessionFactory();
		}
		catch (Exception he){
			throw he;
		}
	}

	public static Session getSession() throws Exception {
		Session session;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (Exception ex) {
			session = sessionFactory.openSession();
		}

		return sessionFactory.openSession();
	}

	public static void insertEntity(Object entity) throws Exception {
		Session session = HibernateTools.getSession();
		Transaction transaction = session.beginTransaction();
		session.save(entity);
		transaction.commit();
		session.close();
	}

	public static void updateEntity(Object entity) throws Exception {
		Session session = getSession();
		Transaction tx = session.beginTransaction();
		session.update(entity);
		session.flush();
		tx.commit();
		session.close();
	}

	public static void deleteTableRow(Object entity) throws Exception {
		Session session = HibernateTools.getSession();
		Transaction tx = session.beginTransaction();
		session.delete(entity);
		session.flush();
		tx.commit();
		session.close();
	}
}
